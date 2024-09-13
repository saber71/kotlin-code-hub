package heraclius.behavior_tree

import heraclius.common.Dict
import heraclius.common.SlotStack
import heraclius.common.Utils

// 行为树上下文
object BehaviorContext : SlotStack<Dict>() {
    // 获取当前上下文对象
    fun current(): Dict {
        return top() ?: throw RuntimeException("current context is empty")
    }

    // 备份当前上下文
    fun backupCurrent(): BehaviorContext {
        val cur = current()
        val dict = Utils.new(cur.javaClass).extend(cur)
        toTop(dict)
        return this
    }

    // 恢复当前上下文
    fun recoveryCurrent(): BehaviorContext {
        recovery(current())
        return this
    }
}
