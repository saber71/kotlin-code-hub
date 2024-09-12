package decorators

import Function

// 始终返回成功
class AlwaysSuccess(fn: Function<Unit>? = null) : Behavior.Decorator(fn) {
    override fun run(): Status {
        super.run()
        return Status.SUCCESS
    }
}
