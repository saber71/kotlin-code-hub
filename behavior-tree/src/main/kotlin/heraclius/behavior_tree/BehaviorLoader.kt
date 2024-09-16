package heraclius.behavior_tree

import heraclius.behavior_tree.behaviors.*
import heraclius.behavior_tree.composites.MemorySelector
import heraclius.behavior_tree.composites.MemorySequence
import heraclius.behavior_tree.composites.Parallel
import heraclius.behavior_tree.composites.Selector
import heraclius.behavior_tree.decorators.*
import heraclius.common.Dict
import heraclius.common.Function
import heraclius.common.Utils
import heraclius.common.XMLUtils

/**
 * 行为树的加载器类，用于从描述字典中加载行为树节点。
 * @param names 加载器响应的标签名称数组。
 */
abstract class BehaviorLoader(vararg names: String) {
    companion object {
        // Lazy initialization of loaders to improve performance and ensure thread safety.
        private val _loaders = lazy { Utils.getSubClassInstances(BehaviorLoader::class.java) }

        // Map of loader names to their instances for quick lookup.
        private val _loaderMap = lazy {
            val m = mutableMapOf<String, BehaviorLoader>()
            for (loader in _loaders.value) {
                for (name in loader._names) {
                    if (m.containsKey(name)) throw RuntimeException("Duplicate loader found: $name")
                    m[name] = loader
                }
            }
            return@lazy m
        }

        /**
         * 根据字典加载行为树节点。
         * @param dict 描述行为树节点的字典。
         * @return 加载的行为树节点或函数。
         */
        fun handle(dict: Dict): Result {
            val tagName = dict[XMLUtils.tagName]
            val loader = _loaderMap.value[tagName] ?: throw RuntimeException("No loader found for tag: $tagName")
            return loader.load(dict)
        }
    }

    /**
     * 加载结果类，用于封装加载的行为或函数。
     * @param value 加载的行为或函数。
     */
    class Result(val value: Any) {
        /**
         * 将结果值转换为Behavior类型。
         * @return 转换后的Behavior对象。
         */
        fun behavior(): Behavior {
            if (this.value !is Behavior) throw RuntimeException("Result.behavior() is only for Behavior return values")
            return this.value
        }

        /**
         * 将结果值转换为指定的函数类型。
         * @return 转换后的函数对象。
         */
        fun <V> function(): V {
            if (this.value !is Function0<*>) throw RuntimeException("Result.function() is only for Function0 return values")
            @Suppress("UNCHECKED_CAST")
            return this.value as V
        }
    }

    /**
     * 默认的Behavior加载器，处理基本的行为树节点。
     */
    class Default : BehaviorLoader(
        "Failure",
        "Running",
        "SetContext",
        "SetKeyValue",
        "Success",
        "MemorySelector",
        "MemorySequence",
        "Parallel",
        "Selector",
        "Sequence",
        "AlwaysFailure",
        "AlwaysRunning",
        "AlwaysSuccess",
        "BackupContext",
        "FailureIs",
        "RunningIs",
        "SuccessIs",
    ) {
        override fun load(dict: Dict): Result {
            val childrenMap = dict[XMLUtils.childrenMap]
            val children = dict[XMLUtils.children]
            val tagName = dict[XMLUtils.tagName]
            val fn = { dict[XMLUtils.children].forEach { load(it) } }

            return Result(
                when (tagName) {
                    "Failure" -> Failure()
                    "Running" -> Running()
                    "Success" -> Success()

                    "FailureIs" -> FailureIs(
                        handle(
                            childrenMap["status"]?.last() ?: throw RuntimeException("找不到名为status的子节点")
                        ).function<Function<Status>>()(),
                        fn
                    )

                    "SuccessIs" -> SuccessIs(
                        handle(
                            childrenMap["status"]?.last() ?: throw RuntimeException("找不到名为status的子节点")
                        ).function<Function<Status>>()(),
                        fn
                    )

                    "RunningIs" -> RunningIs(
                        handle(
                            childrenMap["status"]?.last() ?: throw RuntimeException("找不到名为status的子节点")
                        ).function<Function<Status>>()(),
                        fn
                    )

                    "SetContext" -> SetContext(handle(children.last()).function())
                    "SetKeyValue" -> SetKeyValue(
                        handle(
                            childrenMap["key"]?.last() ?: throw RuntimeException("找不到名为key的子节点")
                        ).function(),
                        handle(
                            childrenMap["value"]?.last() ?: throw RuntimeException("找不到名为value的子节点")
                        ).function()
                    )

                    "BackupContext" -> BackupContext(fn)
                    "AlwaysFailure" -> AlwaysFailure(fn)
                    "AlwaysSuccess" -> AlwaysSuccess(fn)
                    "AlwaysRunning" -> AlwaysRunning(fn)
                    "MemorySelector" -> MemorySelector(fn)
                    "MemorySequence" -> MemorySequence(fn)
                    "Parallel" -> Parallel(fn)
                    "Selector" -> Selector(fn)
                    "Sequence" -> heraclius.behavior_tree.composites.Sequence(fn)

                    else -> throw RuntimeException("不支持的标签: ${dict[XMLUtils.tagName]}")
                }
            )
        }
    }

    /**
     * 默认的函数加载器，用于加载key和value节点。
     */
    class DefaultFunction : BehaviorLoader("key", "value") {
        override fun load(dict: Dict): Result {
            return Result({ dict[XMLUtils.value] })
        }
    }

    /**
     * 状态函数加载器，用于加载状态节点。
     */
    class StatusFunction : BehaviorLoader("status") {
        override fun load(dict: Dict): Result {
            return Result({ Status.valueOf(dict[XMLUtils.value].uppercase()) })
        }
    }

    private val _names = names.toList()

    /**
     * 从字典加载行为树节点或函数。
     * @param dict 描述行为树节点或函数的字典。
     * @return 加载的结果封装在Result对象中。
     */
    abstract fun load(dict: Dict): Result
}
