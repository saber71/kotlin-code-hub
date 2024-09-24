package heraclius.behavior_tree.decorators

import heraclius.behavior_tree.Behavior
import heraclius.behavior_tree.Status
import heraclius.common.Func

// 错误时返回指定状态
class FailureIs(val targetStatus: Status, fn: Func<Unit>? = null) : Behavior.Decorator(fn) {
    override fun _run(): Status {
        val status = super._run()
        if (status == Status.FAILURE) return targetStatus
        return status
    }
}
