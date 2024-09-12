package heraclius.behavior_tree.behaviors

import heraclius.behavior_tree.Behavior
import heraclius.behavior_tree.Status

// 始终返回成功
class Success : Behavior() {
    override fun run(): Status {
        return Status.SUCCESS
    }
}
