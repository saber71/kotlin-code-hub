package heraclius.utils_command_sql

import heraclius.utils_command.Command

object Logic {
    interface Interface : Command

    class And(vararg cmd: Command) : Interface {
        val commands = cmd.toList()
    }

    class Or(vararg cmd: Command) : Interface {
        val commands = cmd.toList()
    }

    data class Bracket(val command: Command) : Interface

    data class Noop(val command: Command) : Interface
}
