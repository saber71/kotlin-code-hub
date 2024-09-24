package heraclius.terminal

import java.awt.Color

object Terminal {
    var defaultForegroundColor: Color = Color.BLACK
        set(value) {
            if (value == field) return
            field = value
            kotlin.io.print(ANSI.foregroundColor(value))
        }
    var defaultBackgroundColor: Color = Color.WHITE
        set(value) {
            if (value == field) return
            field = value
            kotlin.io.print(ANSI.backgroundColor(value))
        }

    init {
        kotlin.io.print(ANSI.foregroundColor(defaultForegroundColor))
        kotlin.io.print(ANSI.backgroundColor(defaultBackgroundColor))
    }

    fun println(
        vararg args: Any,
        joinSeparator: String = " ",
        foregroundColor: Color = defaultForegroundColor,
        backgroundColor: Color = defaultBackgroundColor,
        endReset: Boolean = false
    ): Terminal {
        kotlin.io.println(
            toString(
                *args,
                foregroundColor = foregroundColor,
                backgroundColor = backgroundColor,
                endReset = endReset,
                joinSeparator = joinSeparator
            )
        )
        return this
    }

    fun print(
        vararg args: Any,
        joinSeparator: String = " ",
        foregroundColor: Color = defaultForegroundColor,
        backgroundColor: Color = defaultBackgroundColor,
        endReset: Boolean = false
    ): Terminal {
        kotlin.io.print(
            toString(
                *args,
                foregroundColor = foregroundColor,
                backgroundColor = backgroundColor,
                endReset = endReset,
                joinSeparator = joinSeparator
            )
        )
        return this
    }

    fun toString(
        vararg args: Any,
        joinSeparator: String = " ",
        foregroundColor: Color = defaultForegroundColor,
        backgroundColor: Color = defaultBackgroundColor,
        endReset: Boolean = false
    ): String {
        var str = ANSI.foregroundColor(foregroundColor)
        str += ANSI.backgroundColor(backgroundColor)
        str += args.joinToString(joinSeparator)
        if (endReset) str += ANSI.RESET
        return str
    }
}
