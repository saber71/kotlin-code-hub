package heraclius.behavior_tree.decorators

import heraclius.behavior_tree.Behavior
import heraclius.behavior_tree.Status
import heraclius.common.Function

// 运行状态转换为指定状态
class RunningIs(val targetStatus: Status, fn: Function<Unit>? = null) : Behavior.Decorator(fn) {
    override fun _run(): Status {
        val status = super._run()
        if (status == Status.RUNNING) return targetStatus
        return status
    }
}
