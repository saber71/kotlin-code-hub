package heraclius.utils_command_sql

import heraclius.utils_command.Command

object Value {
    data class Num(val value: Number) : Command
    data class Str(val value: String) : Command
    class NULL : Command
    class List(vararg val values: Command) : Command
}
