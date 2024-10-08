package heraclius.utils_command_sql

import heraclius.utils_command.Command

object Compare {
    class Equal(val left: Command, val right: Command) : Command
    class Less(val left: Command, val right: Command) : Command
    class LessEqual(val left: Command, val right: Command) : Command
    class Greater(val left: Command, val right: Command) : Command
    class GreaterEqual(val left: Command, val right: Command) : Command
}
