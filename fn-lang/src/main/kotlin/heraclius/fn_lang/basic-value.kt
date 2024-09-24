package heraclius.fn_lang

import heraclius.common.Dict
import heraclius.common.Utils
import kotlin.reflect.KFunction

fun handleFunc(v: Any): Any {
    @Suppress("UNCHECKED_CAST")
    if (v is KFunction<*>) return (v as FnLangAny)()
    return v
}

fun string(value: String): FnLangString {
    return {
        val v = handleFunc(value)
        Utils.toString(v)
    }
}

fun number(value: Number): FnLangNumber {
    return {
        val v = handleFunc(value)
        Utils.toNumber(v)
    }
}

fun boolean(value: Any): FnLangBoolean {
    return {
        val v = handleFunc(value)
        Utils.toBoolean(v)
    }
}

fun dict(value: Any = Unit): FnLangDict {
    return {
        if (value == Unit) Dict()
        else {
            val v = handleFunc(value)
            Utils.toDict(v)
        }
    }
}

fun value(v: Any): FnLangAny {
    return { handleFunc(v) }
}
