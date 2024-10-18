package heraclius.utils_command_sql_executor

import heraclius.utils_command.CommandExecutor
import heraclius.utils_command.CommandHandler
import heraclius.utils_command_sql.Logic

object LogicOrHandler : CommandHandler<Logic.Or, String> {
    override val handleFor: Class<Logic.Or>
        get() = Logic.Or::class.java

    override fun handle(command: Logic.Or): String {
        return command.commands.joinToString(" OR ") {
            val output = CommandExecutor.execute<String>(it)
            if (it is Logic.Interface) "(${output})" else output
        }
    }
}
