package decorators

import Function

// 始终返回失败
class AlwaysFailure(fn: Function<Unit>? = null) : Behavior.Decorator(fn) {
    override fun run(): Status {
        super.run()
        return Status.FAILURE
    }
}
