package heraclius.utils_command_sql_executor

import heraclius.utils_command.CommandExecutor
import heraclius.utils_command.CommandHandler
import heraclius.utils_command_sql.Select

object SubSelectHandler : CommandHandler<Select.Sub, String> {
    override val handleFor: Class<Select.Sub>
        get() = Select.Sub::class.java

    override fun handle(command: Select.Sub): String {
        val alias = if (command.alias != null && command.alias!!.isNotEmpty()) " " + command.alias else ""
        return "(${CommandExecutor.execute<Any>(command.statement)})${alias}"
    }
}
