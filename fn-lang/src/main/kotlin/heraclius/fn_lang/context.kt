package heraclius.fn_lang

fun backupContext(fn: FnLangVoid): FnLangVoid {
    return {
        FnLangContext.backupCurrent()
        fn()
        FnLangContext.recoveryCurrent()
    }
}

fun addContext(dict: FnLangDict): FnLangVoid {
    return {
        FnLangContext.add(dict())
    }
}

fun popContext(): FnLangVoid {
    return {
        FnLangContext.pop()
    }
}
