package heraclius.behavior_tree.behaviors

import heraclius.behavior_tree.Behavior
import heraclius.behavior_tree.BehaviorContext
import heraclius.behavior_tree.DictGetter
import heraclius.behavior_tree.Status

// 设置上下文
class SetContext(val getter: DictGetter) : Behavior() {
    override fun _run(): Status {
        val result = getter() ?: return Status.FAILURE
        BehaviorContext.toTop(result)
        return Status.SUCCESS
    }
}
