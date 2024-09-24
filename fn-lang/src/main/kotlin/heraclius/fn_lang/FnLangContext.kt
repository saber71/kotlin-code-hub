package heraclius.fn_lang

import heraclius.common.CommonContext

object FnLangContext : CommonContext() {
    init {
        this.current()[DEFINE_KEYS] = DefineKeys()
    }
}
