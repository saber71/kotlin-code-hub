package heraclius.utils_command_sql_executor

import heraclius.utils_command.CommandExecutor
import heraclius.utils_command.CommandHandler
import heraclius.utils_command_sql.Compare

object CompareNotBetweenHandler : CommandHandler<Compare.NotBetween, String> {
    override val handleFor: Class<Compare.NotBetween>
        get() = Compare.NotBetween::class.java

    override fun handle(command: Compare.NotBetween): String {
        return "${CommandExecutor.execute<Any>(command.left)} NOT BETWEEN ${CommandExecutor.execute<Any>(command.right)}"
    }
}
