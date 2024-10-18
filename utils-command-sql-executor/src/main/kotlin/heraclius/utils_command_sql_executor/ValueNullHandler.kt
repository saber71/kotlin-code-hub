package heraclius.utils_command_sql_executor

import heraclius.utils_command.CommandHandler
import heraclius.utils_command_sql.Value

object ValueNullHandler : CommandHandler<Value.NULL, String> {
    override val handleFor: Class<Value.NULL>
        get() = Value.NULL::class.java

    override fun handle(command: Value.NULL): String {
        return "NULL"
    }
}
