package heraclius.behavior_tree.decorators

import heraclius.behavior_tree.Behavior
import heraclius.behavior_tree.Status
import heraclius.common.Function

// 始终返回失败
class AlwaysFailure(fn: Function<Unit>? = null) : Behavior.Decorator(fn) {
    override fun run(): Status {
        super.run()
        return Status.FAILURE
    }
}
