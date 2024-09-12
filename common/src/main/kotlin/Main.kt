fun main() {
    println(Symbols.of<Number>("123"))
    println(Symbols.get<Number>("123"))
    for (i in 1..5) {
        println("i = $i")
    }
    val list = mutableListOf<Number>(1, 2, 3)
    println(list.reversed())
    println(list)
}
