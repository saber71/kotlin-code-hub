package decorators

import Function

// 错误时返回指定状态
class FailureIs(val targetStatus: Status, fn: Function<Unit>? = null) : Behavior.Decorator(fn) {
    override fun run(): Status {
        val status = super.run()
        if (status == Status.FAILURE) return targetStatus
        return status
    }
}
