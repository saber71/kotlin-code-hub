package heraclius.utils_command_sql_executor

import heraclius.utils_command.CommandExecutor
import heraclius.utils_command.CommandHandler
import heraclius.utils_command_sql.Select
import heraclius.utils_command_sql.Table

object SelectHandler : CommandHandler<Select, String> {
    override val handleFor: Class<Select>
        get() = Select::class.java

    override fun handle(command: Select): String {
        val fields =
            if (command.fields.isNotEmpty()) command.fields.map { CommandExecutor.execute<Any>(it) }
                .joinToString(", ") else CommandExecutor.execute(
                Table.Field("*")
            )
        var result = "SELECT $fields FROM ${CommandExecutor.execute<Any>(command.tableName)}"
        if (command.where.isNotEmpty()) {
            result += " WHERE ${command.where.map { CommandExecutor.execute<Any>(it) }.joinToString(" ")}"
        }
        if (command.groupBy != null) {
            result += " GROUP BY ${command.groupBy}"
        }
        if (command.limit != null) {
            result += " LIMIT ${command.limit}"
        }
        return result
    }
}
