package heraclius.behavior_tree

import heraclius.common.Dict
import heraclius.common.SlotStack

object BehaviorContext : SlotStack<Dict>() {
    fun current(): Dict {
        return top() ?: throw RuntimeException("current context is empty")
    }
}
