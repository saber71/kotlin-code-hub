package heraclius.utils_command_sql

import heraclius.utils_command.Command

class Select(val tableName: Table.Name) : Command {
    val fields = mutableListOf<Command>()
    val where: Logic.Interface? = null
    var limit: Value.Num? = null
    var groupBy: Table.Field? = null
    var having: Logic.Interface? = null

    data class Sub(val statement: Select, val alias: String? = null) : Command
}
