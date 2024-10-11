package heraclius.utils_command_sql_executor

import heraclius.utils_command.CommandExecutor
import heraclius.utils_command.CommandHandler
import heraclius.utils_command_sql.Compare

object CompareInHandler : CommandHandler<Compare.In, String> {
    override val handleFor: Class<Compare.In>
        get() = Compare.In::class.java

    override fun handle(command: Compare.In): String {
        return "${CommandExecutor.execute<Any>(command.left)} IN ${CommandExecutor.execute<Any>(command.right)}"
    }
}
