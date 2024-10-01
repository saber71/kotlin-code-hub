package heraclius.utils_type

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class StringUtilsTest {
    @Test
    fun firstUpper() {
        assertEquals(StringUtils.firstUpper("abc"), "Abc")
        assertEquals(StringUtils.firstUpper("a"), "A")
    }

    @Test
    fun firstLower() {
        assertEquals(StringUtils.firstLower("Abc"), "abc")
        assertEquals(StringUtils.firstLower("A"), "a")
    }
}
