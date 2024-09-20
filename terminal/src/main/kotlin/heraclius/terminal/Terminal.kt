package heraclius.terminal

import java.awt.Color

object Terminal {
    var defaultForegroundColor: Color = Color.BLACK
        set(value) {
            if (value == field) return
            field = value
            kotlin.io.print(toForegroundANSI(value))
        }
    var defaultBackgroundColor: Color = Color.WHITE
        set(value) {
            if (value == field) return
            field = value
            kotlin.io.print(toBackgroundANSI(value))
        }
    const val RESET = "\u001b[0m"

    init {
        kotlin.io.print(toForegroundANSI(defaultForegroundColor))
        kotlin.io.print(toBackgroundANSI(defaultBackgroundColor))
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

    fun toForegroundANSI(color: Color): String {
        return "\u001b[38;2;${color.red};${color.green};${color.blue}m"
    }

    fun toBackgroundANSI(color: Color): String {
        return "\u001b[48;2;${color.red};${color.green};${color.blue}m"
    }

    fun toString(
        vararg args: Any,
        joinSeparator: String = " ",
        foregroundColor: Color = defaultForegroundColor,
        backgroundColor: Color = defaultBackgroundColor,
        endReset: Boolean = false
    ): String {
        var str = toForegroundANSI(foregroundColor)
        str += toBackgroundANSI(backgroundColor)
        str += args.joinToString(joinSeparator)
        if (endReset) str += RESET
        return str
    }
}
