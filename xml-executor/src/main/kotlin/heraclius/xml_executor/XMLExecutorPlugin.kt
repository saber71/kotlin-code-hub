package heraclius.xml_executor

import org.dom4j.Element

/**
 * 插件基类，用于解析xml数据。
 *
 * @param moduleName 插件名称
 */
abstract class XMLExecutorPlugin(val moduleName: String) {
    // 处理关心的xml数据
    abstract fun parse(el: Element)
}
