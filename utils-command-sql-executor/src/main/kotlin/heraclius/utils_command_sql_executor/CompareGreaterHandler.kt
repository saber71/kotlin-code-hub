package heraclius.utils_command_sql_executor

import heraclius.utils_command.CommandExecutor
import heraclius.utils_command.CommandHandler
import heraclius.utils_command_sql.Compare

object CompareGreaterHandler : CommandHandler<Compare.Greater, String> {
    override val handleFor: Class<Compare.Greater>
        get() = Compare.Greater::class.java

    override fun handle(command: Compare.Greater): String {
        return "${CommandExecutor.execute<Any>(command.left)}>${CommandExecutor.execute<Any>(command.right)}"
    }
}
