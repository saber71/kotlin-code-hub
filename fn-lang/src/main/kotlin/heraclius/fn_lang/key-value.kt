package heraclius.fn_lang

import heraclius.common.Symbols
import heraclius.common.Utils
import heraclius.fn_lang.FnLangType.Dict
import heraclius.fn_lang.FnLangType.Number

class DefineKeys {
    data class Data(val name: String, val type: FnLangType)

    val symbolMapData = mutableMapOf<Symbols.Symbol<*>, Data>()
    val nameMapData = mutableMapOf<String, Data>()

    fun define(data: Data) {
        val oldData = nameMapData[data.name]
        if (oldData != null && oldData.type != data.type) throw RuntimeException("${data.name} already defined")
        nameMapData[data.name] = data
        symbolMapData[Symbols.of<Any>(data.name)] = data
    }
}

val DEFINE_KEYS = Symbols.of<DefineKeys>("DEFINE_KEYS")

fun defineKey(name: FnLangString, type: FnLangString): FnLangVoid {
    return {
        val ctx = FnLangContext.current()
        val defineKeys = ctx[DEFINE_KEYS]
        defineKeys.define(DefineKeys.Data(name(), FnLangType.valueOf(type())))
    }
}

fun key(name: FnLangString): FnLangSymbol {
    return { Symbols.get(name()) }
}

fun setKeyValue(key: FnLangSymbol, value: FnLangAny): FnLangVoid {
    return {
        val ctx = FnLangContext.current()
        val symbol = key()
        val keyData = FnLangContext.current()[DEFINE_KEYS].symbolMapData[symbol]
            ?: throw RuntimeException("${symbol.description()} not defined")
        var v = value()
        v = when (keyData.type) {
            Number -> {
                Utils.toNumber(v)
            }

            FnLangType.Boolean -> {
                Utils.toBoolean(v)
            }

            FnLangType.String -> {
                Utils.toString(v)
            }

            Dict -> {
                Utils.toDict(v)
            }
        }
        ctx[symbol] = v
    }
}
