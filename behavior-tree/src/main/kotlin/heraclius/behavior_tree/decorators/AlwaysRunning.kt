package heraclius.behavior_tree.decorators

import heraclius.behavior_tree.Behavior
import heraclius.behavior_tree.Status
import heraclius.common.Function

// 始终运行
class AlwaysRunning(fn: Function<Unit>? = null) : Behavior.Decorator(fn) {
    override fun run(): Status {
        super.run()
        return Status.RUNNING
    }
}
