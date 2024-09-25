package heraclius.fn_lang

import heraclius.common.Dict
import heraclius.common.Func
import heraclius.common.Symbols
import heraclius.common.Utils

enum class FnLangType {
    String,
    Number,
    Boolean,
    Dict
}

typealias FnLangAny = Func<Any>
typealias FnLangString = Func<String>
typealias FnLangNumber = Func<Number>
typealias FnLangBoolean = Func<Boolean>
typealias FnLangDict = Func<Dict>
typealias FnLangVoid = Func<Unit>
typealias FnLangSymbol = Func<Symbols.Symbol<Any>>

fun fnLangString(fn: Func<*>): String {
    return Utils.expectType(fn(), String::class.java)
}

fun fnLangNumber(fn: Func<*>): Number {
    return Utils.expectType(fn(), Number::class.java)
}

fun fnLangBoolean(fn: Func<*>): Boolean {
    return Utils.expectType(fn(), Boolean::class.java)
}

fun fnLangDict(fn: Func<*>): Dict {
    return Utils.expectType(fn(), Dict::class.java)
}

fun fnLangSymbol(fn: Func<*>): Symbols.Symbol<Any> {
    @Suppress("UNCHECKED_CAST")
    return Utils.expectType(fn(), Symbols.Symbol::class.java) as heraclius.common.Symbols.Symbol<Any>
}
