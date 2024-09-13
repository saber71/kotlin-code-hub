package heraclius.behavior_tree.behaviors

import heraclius.behavior_tree.*

// 设置键值对到上下文中
class SetKeyValue(val keyGetter: DictKeyGetter, val valueGetter: DictValueGetter) : Behavior() {
    override fun _run(): Status {
        val key = keyGetter() ?: return Status.FAILURE
        BehaviorContext.current()[key] = valueGetter()
        return Status.SUCCESS
    }
}
