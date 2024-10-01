package heraclius.utils_type

object StringUtils {
    fun firstUpper(string: String): String {
        return string.first().uppercase() + string.substring(1)
    }

    fun firstLower(string: String): String {
        return string.first().lowercase() + string.substring(1)
    }

    fun toList(string: String): List<String> {
        return string.toList().map { it.toString() }
    }
}
