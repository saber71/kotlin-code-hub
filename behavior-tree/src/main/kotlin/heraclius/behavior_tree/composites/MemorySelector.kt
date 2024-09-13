package heraclius.behavior_tree.composites

import heraclius.behavior_tree.Behavior
import heraclius.behavior_tree.Status
import heraclius.common.Function

// 选择节点，执行子节点直到成功或停止，可以记忆上次执行的节点
class MemorySelector(fn: Function<Unit>? = null) : Behavior.Composite(fn, true) {
    override fun _run(): Status {
        for (child in children.slice(_index..children.lastIndex)) {
            _index++
            val result = child.tick()
            if (result != Status.FAILURE) return result
        }
        return Status.FAILURE
    }
}
