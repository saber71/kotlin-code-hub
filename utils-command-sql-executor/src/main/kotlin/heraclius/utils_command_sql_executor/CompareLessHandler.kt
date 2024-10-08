package heraclius.utils_command_sql_executor

import heraclius.utils_command.CommandExecutor
import heraclius.utils_command.CommandHandler
import heraclius.utils_command_sql.Compare

object CompareLessHandler : CommandHandler<Compare.Less, String> {
    override val handleFor: Class<Compare.Less>
        get() = Compare.Less::class.java

    override fun handle(command: Compare.Less): String {
        return "${CommandExecutor.execute<Any>(command.left)}<${CommandExecutor.execute<Any>(command.right)}"
    }
}
