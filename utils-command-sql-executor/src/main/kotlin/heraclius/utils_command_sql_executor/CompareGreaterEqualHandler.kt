package heraclius.utils_command_sql_executor

import heraclius.utils_command.CommandExecutor
import heraclius.utils_command.CommandHandler
import heraclius.utils_command_sql.Compare

object CompareGreaterEqualHandler : CommandHandler<Compare.GreaterEqual, String> {
    override val handleFor: Class<Compare.GreaterEqual>
        get() = Compare.GreaterEqual::class.java

    override fun handle(command: Compare.GreaterEqual): String {
        return "${CommandExecutor.execute<Any>(command.left)}>=${CommandExecutor.execute<Any>(command.right)}"
    }
}
