package heraclius.utils_command_sql

import heraclius.utils_command.Command

object Table {
    class Name(val value: String, val alias: String? = null) : Command

    class Field(val name: String, val alias: String? = null) : Command
}
