package heraclius.behavior_tree

import heraclius.common.Function

/**
 * 行为类，提供基础的行为处理逻辑。
 * 可以被继承以实现不同的行为策略。
 */
open class Behavior() {
    // 伴侣对象，用于存储静态信息和操作
    companion object {
        // 用于维护行为的堆栈，支持行为的嵌套定义
        private val stacks = mutableListOf<Behavior>()

        // 代表无操作的 heraclius.behavior_tree.Behavior 实例，用于默认行为
        val NOOP = Behavior()
    }

    // 初始化时自动管理行为堆栈
    init {
        if (stacks.isNotEmpty()) {
            val last = stacks.last()
            // 如果栈顶是 Composite 类型，则将当前行为添加为其子行为
            if (last is Composite) {
                last.children.add(this)
            } else if (last is Decorator) {
                // 如果栈顶是 Decorator 类型，则将当前行为设置为其子行为
                last.child = this
            } else {
                // 如果栈顶不是 Composite 或 Decorator 类型，抛出异常
                throw RuntimeException("栈顶的节点不是组合节点或装饰节点")
            }
        }
    }

    /**
     * 执行行为，并在执行前后进行处理。
     * 此方法模拟了模板方法设计模式，子类可以重载特定部分的行为。
     */
    fun tick(): Status {
        beforeRun() // 执行行为之前的处理
        afterRun(run()) // 执行核心行为逻辑，并在之后进行处理
        return status
    }

    // 行为的状态，子类可以根据状态调整行为逻辑
    protected var status = Status.INVALID

    // 在行为执行之前执行的操作，子类可重载
    protected open fun beforeRun() {}

    // 行为的核心逻辑，子类应重载此方法以定义具体行为
    protected open fun run(): Status {
        return Status.INVALID
    }

    // 在行为执行之后执行的操作，子类可重载
    protected open fun afterRun(newStatus: Status) {
        status = newStatus
    }

    // 初始化函数，允许外部在行为初始化时执行特定操作
    protected fun init(fn: Function<Unit>?) {
        if (fn != null) {
            stacks.add(this)
            fn()
            stacks.remove(this)
        }
    }

    /**
     * Composite 类，用于组合多种行为。
     * 实现了行为树中的组合模式。
     */
    open class Composite(fn: Function<Unit>? = null, val memory: Boolean = false) : Behavior() {
        val children = mutableListOf<Behavior>() // 子行为列表
        protected var index = 0 // 当前子节点索引

        init {
            init(fn)
        }

        // 在行为执行后处理，根据新状态更新子行为的状态
        override fun afterRun(newStatus: Status) {
            if ((newStatus != Status.RUNNING || !memory) && status != Status.INVALID) {
                for (child in children) {
                    child.afterRun(Status.INVALID)
                }
                index = 0
            }
            super.afterRun(newStatus)
        }
    }

    /**
     * Decorator 类，用于装饰单个行为。
     * 实现了行为树中的装饰模式。
     */
    open class Decorator(fn: Function<Unit>? = null) : Behavior() {
        var child = NOOP // 默认子行为

        init {
            init(fn)
        }

        override fun run(): Status {
            return child.tick()
        }

        // 在行为执行后处理，根据新状态更新子行为的状态
        override fun afterRun(newStatus: Status) {
            if (newStatus != Status.RUNNING) {
                child.afterRun(Status.INVALID)
            }
            super.afterRun(newStatus)
        }
    }
}
