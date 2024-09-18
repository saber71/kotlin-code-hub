import heraclius.behavior_tree.behaviors.Failure
import heraclius.behavior_tree.behaviors.Success
import heraclius.behavior_tree.composites.Selector
import heraclius.behavior_tree.decorators.AlwaysFailure
import heraclius.behavior_tree.decorators.AlwaysSuccess
import heraclius.common.Utils
import heraclius.common.XMLUtils
import java.nio.file.Paths

fun main() {
    val root = Selector {
        AlwaysFailure {
            Success()
        }
        AlwaysSuccess {
            Failure()
        }
    }
    println(root.tick())
    val dict = XMLUtils.parse(Utils.readResourceAsText(Paths.get("/test.xml")).trimIndent())
    println(dict)
}
