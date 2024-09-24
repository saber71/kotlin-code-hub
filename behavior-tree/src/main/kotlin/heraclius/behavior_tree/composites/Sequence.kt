package heraclius.behavior_tree.composites

import heraclius.behavior_tree.Behavior
import heraclius.behavior_tree.Status
import heraclius.common.Func

// 顺序节点，执行子节点直到一个失败或停止
class Sequence(fn: Func<Unit>? = null) : Behavior.Composite(fn) {
    override fun _run(): Status {
        for (child in children) {
            val result = child.tick()
            if (result != Status.SUCCESS) return result
        }
        return Status.SUCCESS
    }
}
