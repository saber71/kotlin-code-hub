import heraclius.terminal.ANSI

fun main() {
    println(ANSI.BOLD + "123av" + ANSI.RESET + "123")
    println(ANSI.UNDERLINE + "123av" + ANSI.RESET + "123")
    println(ANSI.BLINK + "123av" + ANSI.RESET + "123")
    println(ANSI.REVERSE + "123av" + ANSI.RESET + "123")
    println(ANSI.INVISIBLE + "123av" + ANSI.RESET + "123")
    println(ANSI.CROSSED_OUT + "123av" + ANSI.RESET + "123")
}
