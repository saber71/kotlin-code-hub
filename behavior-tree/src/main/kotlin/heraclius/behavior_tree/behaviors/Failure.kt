package heraclius.behavior_tree.behaviors

import heraclius.behavior_tree.Behavior
import heraclius.behavior_tree.Status

// 始终失败
class Failure : Behavior() {
    override fun run(): Status {
        return Status.FAILURE
    }
}
