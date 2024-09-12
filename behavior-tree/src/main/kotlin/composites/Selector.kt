package composites

import Function

// 选择节点，执行子节点直到成功或停止
class Selector(memory: Boolean = false, fn: Function<Unit>? = null) : Behavior.Composite(fn, memory) {
    override fun run(): Status {
        for (child in children.slice(index..children.lastIndex)) {
            index++
            val result = child.tick()
            if (result != Status.FAILURE) return result
        }
        return Status.FAILURE
    }
}
