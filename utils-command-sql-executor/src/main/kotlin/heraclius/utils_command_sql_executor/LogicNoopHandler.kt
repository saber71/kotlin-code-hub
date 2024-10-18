package heraclius.utils_command_sql_executor

import heraclius.utils_command.CommandExecutor
import heraclius.utils_command.CommandHandler
import heraclius.utils_command_sql.Logic

object LogicNoopHandler : CommandHandler<Logic.Noop, String> {
    override val handleFor: Class<Logic.Noop>
        get() = Logic.Noop::class.java

    override fun handle(command: Logic.Noop): String {
        return CommandExecutor.execute(command.command)
    }
}
