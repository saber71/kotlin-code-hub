package heraclius.behavior_tree.composites

import heraclius.behavior_tree.Behavior
import heraclius.behavior_tree.Status
import heraclius.common.Func

// 选择节点，执行子节点直到成功或停止
class Selector(fn: Func<Unit>? = null) : Behavior.Composite(fn) {
    override fun _run(): Status {
        for (child in children) {
            val result = child.tick()
            if (result != Status.FAILURE) return result
        }
        return Status.FAILURE
    }
}
