package heraclius.fn_lang

fun sequence(vararg fns: FnLangBoolean): FnLangBoolean {
    return {
        var index = 0
        for (fn in fns) {
            val result = fn()
            if (!result) break
            index++
        }
        index >= fns.size
    }
}

fun selector(vararg fns: FnLangBoolean): FnLangBoolean {
    return {
        var index = 0
        for (fn in fns) {
            val result = fn()
            if (result) break
            index++
        }
        index < fns.size
    }
}

fun parallel(vararg fns: FnLangBoolean): FnLangBoolean {
    return {
        val result = fns.map { it() }
        var existFalse = false
        for (b in result) {
            if (!b) {
                existFalse = true
                break
            }
        }
        !existFalse
    }
}
