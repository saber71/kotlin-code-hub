package heraclius.utils_command_sql_executor

import heraclius.utils_command.CommandExecutor
import heraclius.utils_command.CommandHandler
import heraclius.utils_command_sql.Compare

object CompareLikeHandler : CommandHandler<Compare.Like, String> {
    override val handleFor: Class<Compare.Like>
        get() = Compare.Like::class.java

    override fun handle(command: Compare.Like): String {
        return "${CommandExecutor.execute<Any>(command.left)} LIKE ${CommandExecutor.execute<Any>(command.right)}"
    }
}
