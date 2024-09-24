package heraclius.behavior_tree

import heraclius.common.Dict
import heraclius.common.Func
import heraclius.common.Symbols

typealias DictGetter = Func<Dict?>
typealias DictChecker = Func<Boolean>
typealias DictListGetter = Func<List<Dict>>
typealias DictKeyGetter = Func<Symbols.Symbol<Any>?>
typealias DictValueGetter = Func<Any>
