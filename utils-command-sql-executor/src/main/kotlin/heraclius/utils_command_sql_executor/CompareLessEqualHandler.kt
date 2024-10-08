package heraclius.utils_command_sql_executor

import heraclius.utils_command.CommandExecutor
import heraclius.utils_command.CommandHandler
import heraclius.utils_command_sql.Compare

object CompareLessEqualHandler : CommandHandler<Compare.LessEqual, String> {
    override val handleFor: Class<Compare.LessEqual>
        get() = Compare.LessEqual::class.java

    override fun handle(command: Compare.LessEqual): String {
        return "${CommandExecutor.execute<Any>(command.left)}<=${CommandExecutor.execute<Any>(command.right)}"
    }
}
