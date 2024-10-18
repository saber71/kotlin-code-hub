package heraclius.utils_command_sql_executor

import heraclius.utils_command.CommandExecutor
import heraclius.utils_command.CommandHandler
import heraclius.utils_command_sql.Compare

object CompareNotEqualHandler : CommandHandler<Compare.NotEqual, String> {
    override val handleFor: Class<Compare.NotEqual>
        get() = Compare.NotEqual::class.java

    override fun handle(command: Compare.NotEqual): String {
        return "${CommandExecutor.execute<Any>(command.left)} <> ${CommandExecutor.execute<Any>(command.right)}"
    }
}
