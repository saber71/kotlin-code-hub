package decorators

import Function

// 成功状态转为指定的状态
class SuccessIs(val targetStatus: Status, fn: Function<Unit>? = null) : Behavior.Decorator(fn) {
    override fun run(): Status {
        val status = super.run()
        if (status == Status.SUCCESS) return targetStatus
        return status
    }
}
