package heraclius.fn_lang

import heraclius.common.Symbols
import heraclius.common.Utils
import heraclius.fn_lang.FnLangType.Dict
import heraclius.fn_lang.FnLangType.Number

private class DefineKeys {
    data class Data(val name: String, val type: FnLangType)

    private val _symbolMapData = mutableMapOf<Symbols.Symbol<*>, Data>()
    private val _nameMapData = mutableMapOf<String, Data>()

    fun define(data: Data) {
        val oldData = _nameMapData[data.name]
        if (oldData != null && oldData.type != data.type) throw RuntimeException("${data.name} already defined")
        _nameMapData[data.name] = data
        _symbolMapData[Symbols.of<Any>(data.name)] = data
    }

    fun defined(symbol: Symbols.Symbol<*>): Data {
        return _symbolMapData[symbol]
            ?: throw RuntimeException("${symbol.description()} not defined")
    }
}

private val DEFINE_KEYS = Symbols.of<DefineKeys>("DEFINE_KEYS")

object KeyValueInitializer : FnLangContext.Initializer {
    override fun init(ctx: heraclius.common.Dict) {
        ctx[DEFINE_KEYS] = DefineKeys()
    }
}

fun defineKey(name: FnLangString, type: FnLangString): FnLangVoid {
    return {
        val defineKeys = FnLangContext.current()[DEFINE_KEYS]
        defineKeys.define(DefineKeys.Data(fnLangString(name), FnLangType.valueOf(fnLangString(type))))
    }
}

fun key(name: FnLangString): FnLangSymbol {
    return { Symbols.get(fnLangString(name)) }
}

fun setValue(key: FnLangSymbol, value: FnLangAny): FnLangVoid {
    return {
        val ctx = FnLangContext.current()
        val symbol = fnLangSymbol(key)
        val keyData = FnLangContext.current()[DEFINE_KEYS].defined(symbol)
        val v = value()
        ctx[symbol] = when (keyData.type) {
            Number -> Utils.toNumber(v)
            FnLangType.Boolean -> Utils.toBoolean(v)
            FnLangType.String -> Utils.toString(v)
            Dict -> Utils.toDict(v)
        }
    }
}

fun getValue(key: FnLangSymbol, dict: FnLangDict = { FnLangContext.current() }): FnLangAny {
    return {
        val symbol = fnLangSymbol(key)
        val v = dict()[symbol]
        val keyData = FnLangContext.current()[DEFINE_KEYS].defined(symbol)
        when (keyData.type) {
            Number -> Utils.expectType(v, kotlin.Number::class.java)
            FnLangType.Boolean -> Utils.expectType(v, Boolean::class.java)
            FnLangType.String -> Utils.expectType(v, String::class.java)
            Dict -> Utils.expectType(v, Dict::class.java)
        }
    }
}

fun getNumber(key: FnLangSymbol, dict: FnLangDict = { FnLangContext.current() }): FnLangNumber {
    return {
        val symbol = fnLangSymbol(key)
        val v = dict()[symbol]
        val keyData = FnLangContext.current()[DEFINE_KEYS].defined(symbol)
        when (keyData.type) {
            Number -> Utils.expectType(v, kotlin.Number::class.java)
            else -> throw RuntimeException("$v is not a number")
        }
    }
}

fun getBoolean(key: FnLangSymbol, dict: FnLangDict = { FnLangContext.current() }): FnLangBoolean {
    return {
        val symbol = fnLangSymbol(key)
        val v = dict()[symbol]
        val keyData = FnLangContext.current()[DEFINE_KEYS].defined(symbol)
        when (keyData.type) {
            FnLangType.Boolean -> Utils.expectType(v, Boolean::class.java)
            else -> throw RuntimeException("$v is not a boolean")
        }
    }
}

fun getString(key: FnLangSymbol, dict: FnLangDict = { FnLangContext.current() }): FnLangString {
    return {
        val symbol = fnLangSymbol(key)
        val v = dict()[symbol]
        val keyData = FnLangContext.current()[DEFINE_KEYS].defined(symbol)
        when (keyData.type) {
            FnLangType.String -> Utils.expectType(v, String::class.java)
            else -> throw RuntimeException("$v is not a string")
        }
    }
}

fun getDict(key: FnLangSymbol, dict: FnLangDict = { FnLangContext.current() }): FnLangDict {
    return {
        val symbol = fnLangSymbol(key)
        val v = dict()[symbol]
        val keyData = FnLangContext.current()[DEFINE_KEYS].defined(symbol)
        when (keyData.type) {
            Dict -> Utils.expectType(v, heraclius.common.Dict::class.java)
            else -> throw RuntimeException("$v is not a dict")
        }
    }
}
