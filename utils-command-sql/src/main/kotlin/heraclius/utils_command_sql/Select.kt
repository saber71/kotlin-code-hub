package heraclius.utils_command_sql

import heraclius.utils_command.Command

class Select(val tableName: Table.Name) : Command {
    val fields = mutableListOf<Command>()
    val where = mutableListOf<Command>()
    var limit: Int? = null
    var groupBy: String? = null

    data class Sub(val statement: Select) : Command
}
