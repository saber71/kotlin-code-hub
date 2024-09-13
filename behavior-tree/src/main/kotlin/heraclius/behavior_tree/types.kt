package heraclius.behavior_tree

import heraclius.common.Dict
import heraclius.common.Function
import heraclius.common.Symbols

typealias DictGetter = Function<Dict?>
typealias DictChecker = Function<Boolean>
typealias DictListGetter = Function<List<Dict>>
typealias DictKeyGetter = Function<Symbols.Symbol<Any>?>
typealias DictValueGetter = Function<Any>
