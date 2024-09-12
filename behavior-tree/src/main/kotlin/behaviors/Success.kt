package behaviors

// 始终返回成功
class Success : Behavior() {
    override fun run(): Status {
        return Status.SUCCESS
    }
}
