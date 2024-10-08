package heraclius.utils_command_sql_executor

import heraclius.utils_command.CommandHandler
import heraclius.utils_command_sql.Table
import heraclius.utils_type.StringUtils

object TableFieldHandler : CommandHandler<Table.Field, String> {
    override val handleFor: Class<Table.Field>
        get() = Table.Field::class.java

    override fun handle(command: Table.Field): String {
        val fieldName = StringUtils.underscoreCase(command.name)
        if (command.alias != null) return fieldName + " as " + command.alias
        return fieldName
    }
}
