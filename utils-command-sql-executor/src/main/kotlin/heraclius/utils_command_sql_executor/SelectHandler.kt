package heraclius.utils_command_sql_executor

import heraclius.utils_command.CommandExecutor
import heraclius.utils_command.CommandHandler
import heraclius.utils_command_sql.Select
import heraclius.utils_command_sql.Table
import heraclius.utils_command_sql.Value

object SelectHandler : CommandHandler<Select, String> {
    override val handleFor: Class<Select>
        get() = Select::class.java

    override fun handle(command: Select): String {
        val fields =
            if (command.fields.isNotEmpty()) command.fields.map { CommandExecutor.execute<Any>(it) }.joinToString(", ")
            else CommandExecutor.execute(Table.Field(Value.Str("*")))
        var result = "SELECT $fields FROM ${CommandExecutor.execute<Any>(command.tableName)}"
        if (command.where != null) {
            result += " WHERE ${CommandExecutor.execute<Any>(command.where!!)}"
        }
        if (command.groupBy != null) {
            result += " GROUP BY ${CommandExecutor.execute<Any>(command.groupBy!!)}"
        }
        if (command.having != null) {
            result += " HAVING ${CommandExecutor.execute<Any>(command.having!!)}"
        }
        if (command.limit != null) {
            result += " LIMIT ${CommandExecutor.execute<Any>(command.limit!!)}"
        }
        return result
    }
}
