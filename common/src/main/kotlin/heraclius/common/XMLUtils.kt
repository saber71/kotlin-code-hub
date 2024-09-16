package heraclius.common

import org.dom4j.DocumentHelper
import org.dom4j.Element

/**
 * XMLUtils对象提供了一些实用函数，用于将XML元素转换为Dict对象。
 */
object XMLUtils {
    // 标签名称的键
    val tagName = Symbols.of<String>("tagName")

    // 标签值的键
    val value = Symbols.of<String>("value")

    // 子元素列表的键
    val children = Symbols.of<List<Dict>>("children")

    // 子元素映射的键
    val childrenMap = Symbols.of<Map<String, List<Dict>>>("childrenMap")

    // 子节点作为父节点的指定属性
    val for_ = Symbols.of<String>("for")

    /**
     * 将给定的XML元素转换为Dict对象。
     *
     * @param el 要转换的XML元素
     * @return 转换后的Dict对象
     */
    fun toDict(el: Element): Dict {
        val dict = Dict()
        dict[tagName] = el.name
        dict[children] = el.elements().map { toDict(it) }.toList()
        val map = mutableMapOf<String, MutableList<Dict>>()
        for (child in dict[children]) {
            val list = map[child[tagName]] ?: mutableListOf()
            map[child[tagName]] = list
            list.add(child)
        }
        dict[childrenMap] = map.toMap()
        for (attribute in el.attributes()) {
            if (attribute.name == "tagName" || attribute.name == "children")
                throw RuntimeException("不允许使用tagName和children作为属性名")
            dict[Symbols.of<String>(attribute.name)] = attribute.value
        }
        if (value !in dict) {
            dict[value] = el.textTrim
        }
        return dict
    }

    /**
     * 解析给定的XML文本，并将其转换为Dict对象。
     *
     * @param text 要解析的XML文本
     * @return 解析后的Dict对象
     */
    fun parse(text: String): Dict {
        return toDict(DocumentHelper.parseText(text).document.rootElement)
    }
}
