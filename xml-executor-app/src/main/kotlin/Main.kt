import heraclius.xml_executor.XMLExecutorPlugin
import org.reflections.Reflections

fun main() {
    val reflections = Reflections("heraclius")
    println(reflections.getSubTypesOf(XMLExecutorPlugin::class.java))
}
