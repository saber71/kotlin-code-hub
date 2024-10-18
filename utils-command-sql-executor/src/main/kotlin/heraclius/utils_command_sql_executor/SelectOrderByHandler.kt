package heraclius.utils_command_sql_executor

import heraclius.utils_command.CommandExecutor
import heraclius.utils_command.CommandHandler
import heraclius.utils_command_sql.Select

object SelectOrderByHandler : CommandHandler<Select.OrderBy, String> {
    override val handleFor: Class<Select.OrderBy>
        get() = Select.OrderBy::class.java

    override fun handle(command: Select.OrderBy): String {
        return "ORDER BY ${command.fields.joinToString(", ") { CommandExecutor.execute<String>(it) }} ${if (command.asc) "ASC" else "DESC"}"
    }
}
