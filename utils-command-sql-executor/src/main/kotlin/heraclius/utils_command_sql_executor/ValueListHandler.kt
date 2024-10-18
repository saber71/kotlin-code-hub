package heraclius.utils_command_sql_executor

import heraclius.utils_command.CommandExecutor
import heraclius.utils_command.CommandHandler
import heraclius.utils_command_sql.Value

object ValueListHandler : CommandHandler<Value.List, String> {
    override val handleFor: Class<Value.List>
        get() = Value.List::class.java

    override fun handle(command: Value.List): String {
        return "(${command.values.joinToString(", ") { CommandExecutor.execute(it) }})"
    }
}
