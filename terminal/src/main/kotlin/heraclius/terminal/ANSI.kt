package heraclius.terminal

import java.awt.Color

object ANSI {
    const val RESET = "\u001b[0m"
    const val BOLD = "\u001b[1m"
    const val UNDERLINE = "\u001b[4m"
    const val BLINK = "\u001b[5m"
    const val REVERSE = "\u001b[7m"
    const val INVISIBLE = "\u001b[8m"
    const val CROSSED_OUT = "\u001b[9m"

    fun foregroundColor(color: Color): String {
        return "\u001b[38;2;${color.red};${color.green};${color.blue}m"
    }

    fun backgroundColor(color: Color): String {
        return "\u001b[48;2;${color.red};${color.green};${color.blue}m"
    }
}
