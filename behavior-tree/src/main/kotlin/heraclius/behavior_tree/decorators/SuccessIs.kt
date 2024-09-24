package heraclius.behavior_tree.decorators

import heraclius.behavior_tree.Behavior
import heraclius.behavior_tree.Status
import heraclius.common.Func

// 成功状态转为指定的状态
class SuccessIs(val targetStatus: Status, fn: Func<Unit>? = null) : Behavior.Decorator(fn) {
    override fun _run(): Status {
        val status = super._run()
        if (status == Status.SUCCESS) return targetStatus
        return status
    }
}
