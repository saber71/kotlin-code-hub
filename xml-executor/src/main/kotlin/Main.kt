import org.dom4j.DocumentHelper
import org.dom4j.Element

fun main() {
    val doc = DocumentHelper.parseText(
        """
        <root>
            <child prop="value">
                child-text
                <child2>
                    <child3>
                        child3-text
                    </child3>
                </child2>
            </child>
        </root>
    """.trimIndent()
    )
    recursive(doc.rootElement)
}

fun recursive(el: Element) {
    println(el.name)
    for (attribute in el.attributes()) {
        println("attribute:" + attribute.name + "-" + attribute.value)
    }
    println(el.textTrim)
    for (element in el.elements()) {
        recursive(element)
    }
}
