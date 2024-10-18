package heraclius.utils_command_sql_executor

import heraclius.utils_command.CommandExecutor
import heraclius.utils_command.CommandHandler
import heraclius.utils_command_sql.Select

object SelectHandler : CommandHandler<Select, String> {
    override val handleFor: Class<Select>
        get() = Select::class.java

    override fun handle(command: Select): String {
        val fields =
            if (command.fields.isNotEmpty()) command.fields.map { CommandExecutor.execute<Any>(it) }.joinToString(", ")
            else "*"
        var result =
            "SELECT ${if (command.distinct) "DISTINCT" else ""} $fields FROM ${CommandExecutor.execute<Any>(command.tableName)}"
        if (command.join != null) {
            val on = if (command.join!!.on != null) CommandExecutor.execute<Any>(command.join!!.on!!) else ""
            result += " JOIN ${CommandExecutor.execute<Any>(command.join!!.table)} ON $on"
        }
        if (command.where != null) {
            result += " WHERE ${CommandExecutor.execute<Any>(command.where!!)}"
        }
        if (command.groupBy.isNotEmpty()) {
            result += " GROUP BY ${command.groupBy.joinToString(", ") { CommandExecutor.execute<String>(it) }}"
        }
        if (command.having != null) {
            result += " HAVING ${CommandExecutor.execute<Any>(command.having!!)}"
        }
        if (command.limit != null) {
            result += " LIMIT ${CommandExecutor.execute<Any>(command.limit!!)}"
        }
        if (command.orderBy != null) {
            result += " ORDER BY ${CommandExecutor.execute<Any>(command.orderBy!!)}"
        }
        return result
    }
}
