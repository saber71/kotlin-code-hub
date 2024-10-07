package heraclius.utils_command

interface CommandHandler<C : Command, R> {
    val handleFor: Class<C>

    fun handle(command: C): R
}
