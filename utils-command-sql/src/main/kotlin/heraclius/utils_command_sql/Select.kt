package heraclius.utils_command_sql

import heraclius.utils_command.Command

class Select(val tableName: Table.Name, val distinct: Boolean = false) : Command {
    val fields = mutableListOf<Command>()
    val join: Join? = null
    val where: Logic.Interface? = null
    var limit: Value.Num? = null
    var groupBy = mutableListOf<Table.Field>()
    var having: Logic.Interface? = null
    var orderBy: OrderBy? = null

    enum class JoinType(val value: String) {
        INNER("INNER JOIN"), LEFT("LEFT JOIN"), RIGHT("RIGHT JOIN"), FULL("FULL OUTER JOIN")
    }

    data class Sub(val statement: Select, val alias: String? = null) : Command

    data class Join(
        val table: Command,
        val alias: String? = null,
        val on: Logic.Interface? = null,
        val type: JoinType = JoinType.INNER
    ) : Command

    class OrderBy(vararg val fields: Table.Field, val asc: Boolean = true) : Command
}
