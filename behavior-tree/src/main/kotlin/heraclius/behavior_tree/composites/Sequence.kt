package heraclius.behavior_tree.composites

import heraclius.behavior_tree.Behavior
import heraclius.behavior_tree.Status
import heraclius.common.Function

// 顺序节点，执行子节点直到一个失败或停止
class Sequence(memory: Boolean = false, fn: Function<Unit>? = null) : Behavior.Composite(fn, memory) {
    override fun run(): Status {
        for (child in children.slice(index..children.lastIndex)) {
            index++
            val result = child.tick()
            if (result != Status.SUCCESS) return result
        }
        return Status.SUCCESS
    }
}
