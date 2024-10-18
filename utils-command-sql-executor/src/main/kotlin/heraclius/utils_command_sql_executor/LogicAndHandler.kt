package heraclius.utils_command_sql_executor

import heraclius.utils_command.CommandExecutor
import heraclius.utils_command.CommandHandler
import heraclius.utils_command_sql.Logic

object LogicAndHandler : CommandHandler<Logic.And, String> {
    override val handleFor: Class<Logic.And>
        get() = Logic.And::class.java

    override fun handle(command: Logic.And): String {
        return command.commands.joinToString(" AND ") {
            val output = CommandExecutor.execute<String>(it)
            if (it is Logic.Interface) "(${output})" else output
        }
    }
}
