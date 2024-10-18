package heraclius.utils_command_sql_executor

import heraclius.utils_command.CommandExecutor
import heraclius.utils_command.CommandHandler
import heraclius.utils_command_sql.Compare

object CompareIsHandler : CommandHandler<Compare.Is, String> {
    override val handleFor: Class<Compare.Is>
        get() = Compare.Is::class.java

    override fun handle(command: Compare.Is): String {
        return "${CommandExecutor.execute<Any>(command.left)} IS ${CommandExecutor.execute<Any>(command.right)}"
    }
}
