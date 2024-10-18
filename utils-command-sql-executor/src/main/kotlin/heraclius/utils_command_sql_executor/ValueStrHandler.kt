package heraclius.utils_command_sql_executor

import heraclius.utils_command.CommandHandler
import heraclius.utils_command_sql.Value

object ValueStrHandler : CommandHandler<Value.Str, String> {
    override val handleFor: Class<Value.Str>
        get() = Value.Str::class.java

    override fun handle(command: Value.Str): String {
        return command.value
    }
}
