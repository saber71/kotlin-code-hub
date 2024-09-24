package heraclius.fn_lang

import heraclius.common.Dict
import heraclius.common.Func
import heraclius.common.Symbols

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
