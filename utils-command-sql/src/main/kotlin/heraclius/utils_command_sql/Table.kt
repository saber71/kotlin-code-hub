package heraclius.utils_command_sql

import heraclius.utils_command.Command

object Table {
    class Name(val value: String) : Command

    class Field(val name: Value.Str, val alias: String? = null) : Command
}
