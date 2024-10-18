package heraclius.utils_command_sql

import heraclius.utils_command.Command

object Compare {
    class Equal(val left: Command, val right: Command) : Command
    class NotEqual(val left: Command, val right: Command) : Command
    class Between(val left: Command, val right: Command) : Command
    class NotBetween(val left: Command, val right: Command) : Command
    class Like(val left: Command, val right: Command) : Command
    class NotLike(val left: Command, val right: Command) : Command
    class Less(val left: Command, val right: Command) : Command
    class LessEqual(val left: Command, val right: Command) : Command
    class Greater(val left: Command, val right: Command) : Command
    class GreaterEqual(val left: Command, val right: Command) : Command
    class In(val left: Command, val right: Command) : Command
    class NotIn(val left: Command, val right: Command) : Command
    class Is(val left: Command, val right: Command) : Command
}
