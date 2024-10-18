package heraclius.utils_command_sql_executor

import heraclius.utils_command.CommandExecutor
import heraclius.utils_command.CommandHandler
import heraclius.utils_command_sql.Compare

object CompareBetweenHandler : CommandHandler<Compare.Between, String> {
    override val handleFor: Class<Compare.Between>
        get() = Compare.Between::class.java

    override fun handle(command: Compare.Between): String {
        return "${CommandExecutor.execute<Any>(command.left)} BETWEEN ${CommandExecutor.execute<Any>(command.right)}"
    }
}
