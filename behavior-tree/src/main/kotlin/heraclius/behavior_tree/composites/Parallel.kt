package heraclius.behavior_tree.composites

import heraclius.behavior_tree.Behavior
import heraclius.behavior_tree.Status
import heraclius.common.Function

// 并行节点，执行全部子节点，如果有一个子节点失败则返回失败，如果有一个节点运行中则返回运行中，如果全部子节点成功则返回成功
class Parallel(memory: Boolean = false, fn: Function<Unit>? = null) : Behavior.Composite(fn, memory) {
    override fun _run(): Status {
        var result = Status.SUCCESS
        for (child in children) {
            val status = child.tick()
            if (status != Status.SUCCESS) {
                if (result != Status.FAILURE)
                    result = status
            }
        }
        return result
    }
}
