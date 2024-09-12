package heraclius.behavior_tree

// 定义行为树状态
enum class Status {
    SUCCESS,
    FAILURE,
    RUNNING,
    INVALID;

    // 判断是否为完成状态
    fun isFinish() = this == SUCCESS || this == FAILURE
}
