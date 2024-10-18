package heraclius.utils_command_sql_executor

import heraclius.utils_command.CommandExecutor
import heraclius.utils_command.CommandHandler
import heraclius.utils_command_sql.Logic

object LogicBracketHandler : CommandHandler<Logic.Bracket, String> {
    override val handleFor: Class<Logic.Bracket>
        get() = Logic.Bracket::class.java

    override fun handle(command: Logic.Bracket): String {
        return "(${CommandExecutor.execute<Any>(command.command)})"
    }
}
