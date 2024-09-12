package behaviors

// 始终运行
class Running : Behavior() {
    override fun run(): Status {
        return Status.RUNNING
    }
}
