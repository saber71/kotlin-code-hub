package heraclius.utils_command

import heraclius.utils_reflection.ClassFilter
import heraclius.utils_reflection.ClassReflection

object CommandExecutor {
    private val commandMapHandler = mutableMapOf<Class<Command>, CommandHandler<Command, *>>()

    init {
        for (clazz in ClassFilter.loadClasses().subClasses(CommandHandler::class.java).values) {
            @Suppress("UNCHECKED_CAST")
            val handler = ClassReflection.newInstance(clazz.kotlin) as CommandHandler<Command, Any>
            commandMapHandler[handler.handleFor] = handler
        }
    }

    fun <T> execute(command: Command?, defaultValue: T? = null): T {
        if (command == null) {
            if (defaultValue == null) throw RuntimeException("Command is null")
            return defaultValue
        }
        val handler = commandMapHandler[command::class.java]
            ?: throw IllegalArgumentException("No handler for command ${command::class.java}")
        @Suppress("UNCHECKED_CAST")
        return handler.handle(command) as T
    }
}
