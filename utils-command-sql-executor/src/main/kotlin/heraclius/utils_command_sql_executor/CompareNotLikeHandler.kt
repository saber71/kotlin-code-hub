package heraclius.utils_command_sql_executor

import heraclius.utils_command.CommandExecutor
import heraclius.utils_command.CommandHandler
import heraclius.utils_command_sql.Compare

object CompareNotLikeHandler : CommandHandler<Compare.NotLike, String> {
    override val handleFor: Class<Compare.NotLike>
        get() = Compare.NotLike::class.java

    override fun handle(command: Compare.NotLike): String {
        return "${CommandExecutor.execute<Any>(command.left)} NOT LIKE ${CommandExecutor.execute<Any>(command.right)}"
    }
}
