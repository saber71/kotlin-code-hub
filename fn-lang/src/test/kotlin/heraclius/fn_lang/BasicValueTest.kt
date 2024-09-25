package heraclius.fn_lang

import heraclius.common.Dict
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BasicValueTest {
    @Test
    fun string() {
        val v = "hello"
        assertEquals(v, heraclius.fn_lang.string(v)())
        assertEquals(v, heraclius.fn_lang.string({ v })())
    }

    @Test
    fun number() {
        val f = 12.21
        val i = 2030L
        assertEquals(f, heraclius.fn_lang.number(f.toString())())
        assertEquals(f, heraclius.fn_lang.number({ f.toString() })())
        assertEquals(i, heraclius.fn_lang.number(i.toString())())
        assertEquals(i, heraclius.fn_lang.number({ i.toString() })())
    }

    @Test
    fun dict() {
        val v = Dict()
        assertEquals(v, heraclius.fn_lang.dict(v)())
        assertEquals(v, heraclius.fn_lang.dict({ v })())
        assertEquals(v.toString(), heraclius.fn_lang.dict()().toString())
    }

    @Test
    fun boolean() {
        assertEquals(true, heraclius.fn_lang.boolean("true")())
        assertEquals(true, heraclius.fn_lang.boolean({ "true" })())
        assertEquals(false, heraclius.fn_lang.boolean("false")())
        assertEquals(false, heraclius.fn_lang.boolean({ "false" })())
    }

    @Test
    fun value() {
        assertEquals("123", heraclius.fn_lang.value("123")())
        assertEquals("123", heraclius.fn_lang.value({ "123" })())
        assertEquals(123, heraclius.fn_lang.value(123)())
        assertEquals(123, heraclius.fn_lang.value({ 123 })())
    }
}
