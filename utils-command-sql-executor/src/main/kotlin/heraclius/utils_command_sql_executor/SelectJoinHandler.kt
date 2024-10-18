package heraclius.utils_command_sql_executor

import heraclius.utils_command.CommandExecutor
import heraclius.utils_command.CommandHandler
import heraclius.utils_command_sql.Select

object SelectJoinHandler : CommandHandler<Select.Join, String> {
    override val handleFor: Class<Select.Join>
        get() = Select.Join::class.java

    override fun handle(command: Select.Join): String {
        return "${command.type.value} ${CommandExecutor.execute<Any>(command.table)}${
            if (command.alias != null && command.alias!!.isNotEmpty()) " " + command.alias else ""
        } ${
            if (command.on != null) " " + CommandExecutor.execute(command.on!!) else ""
        }"
    }
}
