package heraclius.behavior_tree.decorators

import heraclius.behavior_tree.Behavior
import heraclius.behavior_tree.BehaviorContext
import heraclius.behavior_tree.Status
import heraclius.common.Func

// 备份当前上下文
class BackupContext(fn: Func<Unit>? = null) : Behavior.Decorator(fn) {
    override fun _beforeRun() {
        BehaviorContext.backupCurrent()
    }

    override fun _afterRun(newStatus: Status) {
        BehaviorContext.recoveryCurrent()
        super._afterRun(newStatus)
    }
}
