package heraclius.utils_type

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class ConvertTypeTest {
    @Test
    fun to() {
        ConvertType.register(
            ConvertType.Converter(Int::class.java, null, listOf(TestConvertType::class.java))
        )
        ConvertType.register(
            ConvertType.Converter(Double::class.java, null, listOf(TestConvertType::class.java))
        )
        assertEquals(ConvertType.to(Int::class.java, "20"), 20)
        assertEquals(ConvertType.to(Double::class.java, "20"), 20.0)
        assertEquals(ConvertType.to(Int::class.java, TestConvertType("20")), 20)
        assertEquals(ConvertType.to(Double::class.java, TestConvertType("20")), 20.0)
        assertContentEquals(
            ConvertType.to(List::class.java, TestConvertType("20")), mutableListOf("2", "0")
        )
    }

    data class TestConvertType(val value: String) {
        fun toInt(): Int {
            return value.toInt()
        }

        fun toDouble(): Double {
            return value.toDouble()
        }

        fun toList(): List<Any> {
            return StringUtils.toList(value)
        }
    }
}
