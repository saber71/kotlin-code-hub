package behaviors

// 始终失败
class Failure : Behavior() {
    override fun run(): Status {
        return Status.FAILURE
    }
}
