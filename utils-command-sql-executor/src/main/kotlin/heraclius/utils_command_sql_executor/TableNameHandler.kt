package heraclius.utils_command_sql_executor

import heraclius.utils_command.CommandHandler
import heraclius.utils_command_sql.Table

object TableNameHandler : CommandHandler<Table.Name, String> {
    override val handleFor: Class<Table.Name>
        get() = Table.Name::class.java

    override fun handle(command: Table.Name): String {
        return command.value
    }
}
