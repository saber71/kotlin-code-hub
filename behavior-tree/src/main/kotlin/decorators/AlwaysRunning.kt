package decorators

import Function

// 始终运行
class AlwaysRunning(fn: Function<Unit>? = null) : Behavior.Decorator(fn) {
    override fun run(): Status {
        super.run()
        return Status.RUNNING
    }
}
