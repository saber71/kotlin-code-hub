package heraclius.utils_command_sql_executor

import heraclius.utils_command.CommandHandler
import heraclius.utils_command_sql.Table

object TableFieldHandler : CommandHandler<Table.Field, String> {
    override val handleFor: Class<Table.Field>
        get() = Table.Field::class.java

    override fun handle(command: Table.Field): String {
        if (command.alias != null) return command.name + " AS " + command.alias
        return command.name
    }
}
