import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    print("123\u001b[10;10H\u001b[6n dsa456") // 发送请求光标位置的命令
//
//    // 读取控制台返回的光标位置
//    val cursorPosition = readCursorResponse()
//    println("光标位置: $cursorPosition")
}

/**
 * 读取控制台返回的光标位置
 *
 * @return 光标位置 (row, col) 的 Pair
 */
fun readCursorResponse(): Pair<Int, Int> {
    val reader = BufferedReader(InputStreamReader(System.`in`, Charsets.UTF_8))
    val response = reader.readLine()

    if (response == null) {
        throw RuntimeException("无法读取光标位置")
    }

    println("response: $response")

    val pattern = Regex("\\[([0-9]+);([0-9]+)R")
    val matchResult = pattern.find(response)

    if (matchResult != null) {
        val groups = matchResult.groups
        val row = groups.get(1)?.value?.toInt() ?: 0
        val col = groups.get(2)?.value?.toInt() ?: 0
        return Pair(row, col)
    } else {
        throw RuntimeException("无法解析光标位置")
    }
}
