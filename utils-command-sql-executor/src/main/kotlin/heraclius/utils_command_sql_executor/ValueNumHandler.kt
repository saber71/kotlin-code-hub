package heraclius.utils_command_sql_executor

import heraclius.utils_command.CommandHandler
import heraclius.utils_command_sql.Value

object ValueNumHandler : CommandHandler<Value.Num, String> {
    override val handleFor: Class<Value.Num>
        get() = Value.Num::class.java

    override fun handle(command: Value.Num): String {
        return command.value.toString()
    }
}
