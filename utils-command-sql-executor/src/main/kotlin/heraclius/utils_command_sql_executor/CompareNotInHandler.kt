package heraclius.utils_command_sql_executor

import heraclius.utils_command.CommandExecutor
import heraclius.utils_command.CommandHandler
import heraclius.utils_command_sql.Compare

object CompareNotInHandler : CommandHandler<Compare.NotIn, String> {
    override val handleFor: Class<Compare.NotIn>
        get() = Compare.NotIn::class.java

    override fun handle(command: Compare.NotIn): String {
        return "${CommandExecutor.execute<Any>(command.left)} NOT IN ${CommandExecutor.execute<Any>(command.right)}"
    }
}
