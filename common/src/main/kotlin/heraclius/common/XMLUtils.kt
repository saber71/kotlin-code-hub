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
    val tagNameMapChildren = Symbols.of<Map<String, List<Dict>>>("tagNameMapChildren")

    val forPropertyMapChild = Symbols.of<Map<Symbols.Symbol<String>, Dict>>("forPropertyMapChild")

    // 子节点作为父节点的指定属性
    val for_ = Symbols.of<String>("for")

    /**
     * 将XML [Element] 对象转换为字典格式
     * 主要用于将XML元素及其子元素、属性和文本内容转换为键值对的形式，便于后续处理和查询
     *
     * @param el 要转换的XML元素
     * @return 转换后的字典对象
     */
    fun toDict(el: Element): Dict {
        // 创建一个新的字典来存储XML元素的信息
        val dict = Dict()

        // 将XML元素的标签名作为键值对存储到字典中
        dict[tagName] = el.name

        // 创建一个可变映射，用于存储子元素中特定的“for”属性及其对应的字典
        val forPropertyMapChild = mutableMapOf<Symbols.Symbol<String>, Dict>()

        // 创建一个映射，用于存储子元素的标签名和对应的子元素字典列表
        val tagNameMapChildren = mutableMapOf<String, MutableList<Dict>>()
        
        // 将当前元素的所有子元素递归转换为字典格式，并收集到一个列表中
        val children = el.elements().map { toDict(it) }.toList()

        // 遍历所有子元素的字典，提取包含“for”属性的子元素，并将其存储到forChildMap中
        for (child in children) {
            if (for_ in child) {
                val forProperty = Symbols.of<String>(child[for_])
                forPropertyMapChild[forProperty] = child
            }
        }

        // 从子元素列表中过滤掉包含“for”属性的子元素，并将剩余的子元素存储到字典中
        dict[this.children] = children.filter { for_ !in it }.toList()

        // 遍历字典中的所有子元素，根据其标签名将它们分组存储到map中
        for (child in dict[this.children]) {
            val list = tagNameMapChildren[child[tagName]] ?: mutableListOf()
            tagNameMapChildren[child[tagName]] = list
            list.add(child)
        }

        // 将map转换为不可变映射，并存储到字典中，完成子元素的映射关系构建
        dict[this.tagNameMapChildren] = tagNameMapChildren.toMap()

        // 将forChildMap转换为不可变映射，并存储到字典中，完成特定“for”属性的子元素映射关系构建
        dict[this.forPropertyMapChild] = forPropertyMapChild.toMap()

        // 遍历XML元素的所有属性，将其转换为字典中的键值对，除非属性名为“tagName”或“children”
        for (attribute in el.attributes()) {
            if (attribute.name == "tagName" || attribute.name == "children")
                throw RuntimeException("不允许使用tagName和children作为属性名")
            dict[Symbols.of<String>(attribute.name)] = attribute.value
        }

        // 如果当前元素没有“value”键，则将其文本内容作为值存储到字典中
        if (value !in dict && el.textTrim.isNotEmpty()) {
            dict[value] = el.textTrim
        }

        // 返回转换后的字典
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
