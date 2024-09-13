package heraclius.behavior_tree.behaviors

import heraclius.behavior_tree.Behavior
import heraclius.behavior_tree.Status

// 始终运行
class Running : Behavior() {
    override fun _run(): Status {
        return Status.RUNNING
    }
}
