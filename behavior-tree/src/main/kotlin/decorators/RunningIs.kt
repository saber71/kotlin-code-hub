package decorators

import Function

// 运行状态转换为指定状态
class RunningIs(val targetStatus: Status, fn: Function<Unit>? = null) : Behavior.Decorator(fn) {
    override fun run(): Status {
        val status = super.run()
        if (status == Status.RUNNING) return targetStatus
        return status
    }
}
