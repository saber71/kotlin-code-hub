package heraclius.behavior_tree.composites

import heraclius.behavior_tree.Behavior
import heraclius.behavior_tree.Status
import heraclius.common.Func

// 顺序节点，执行子节点直到一个失败或停止，可以记忆上次执行的位置
class MemorySequence(fn: Func<Unit>? = null) : Behavior.Composite(fn, true) {
    override fun _run(): Status {
        for (child in children.slice(_index..children.lastIndex)) {
            _index++
            val result = child.tick()
            if (result != Status.SUCCESS) return result
        }
        return Status.SUCCESS
    }
}
