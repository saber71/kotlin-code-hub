package heraclius.fn_lang

import heraclius.common.Dict
import heraclius.common.Utils

private fun _handleFunc(v: Any): Any {
    @Suppress("UNCHECKED_CAST")
    if (v is Function<*>) return (v as FnLangAny)()
    return v
}

fun string(value: Any): FnLangString {
    return {
        val v = _handleFunc(value)
        Utils.toString(v)
    }
}

fun number(value: Any): FnLangNumber {
    return {
        val v = _handleFunc(value)
        Utils.toNumber(v)
    }
}

fun boolean(value: Any): FnLangBoolean {
    return {
        val v = _handleFunc(value)
        Utils.toBoolean(v)
    }
}

fun dict(value: Any = Unit): FnLangDict {
    return {
        if (value == Unit) Dict()
        else {
            val v = _handleFunc(value)
            Utils.toDict(v)
        }
    }
}

fun list(value: Any = Unit): FnLangList {
    return {
        if (value == Unit) listOf<Any>()
        else {
            val v = _handleFunc(value)
            Utils.toList(v)
        }
    }
}

fun value(v: Any): FnLangAny {
    return { _handleFunc(v) }
}
