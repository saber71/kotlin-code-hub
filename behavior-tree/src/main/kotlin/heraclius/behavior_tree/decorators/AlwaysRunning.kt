package heraclius.behavior_tree.decorators

import heraclius.behavior_tree.Behavior
import heraclius.behavior_tree.Status
import heraclius.common.Function

// 始终运行
class AlwaysRunning(fn: Function<Unit>? = null) : Behavior.Decorator(fn) {
    override fun _run(): Status {
        super._run()
        return Status.RUNNING
    }
}
