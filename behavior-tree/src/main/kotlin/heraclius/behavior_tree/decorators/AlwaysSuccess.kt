package heraclius.behavior_tree.decorators

import heraclius.behavior_tree.Behavior
import heraclius.behavior_tree.Status
import heraclius.common.Func

// 始终返回成功
class AlwaysSuccess(fn: Func<Unit>? = null) : Behavior.Decorator(fn) {
    override fun _run(): Status {
        super._run()
        return Status.SUCCESS
    }
}
