package heraclius.fn_lang

import heraclius.common.CommonContext
import heraclius.common.Dict
import heraclius.common.Utils

object FnLangContext : CommonContext() {
    init {
        val ctx = current()
        val initializers = Utils.getSubClassInstances(Initializer::class.java)
        for (initializer in initializers) {
            initializer.init(ctx)
        }
    }

    interface Initializer {
        fun init(ctx: Dict)
    }
}
