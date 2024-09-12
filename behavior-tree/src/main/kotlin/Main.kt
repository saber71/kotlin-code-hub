import heraclius.behavior_tree.behaviors.Failure
import heraclius.behavior_tree.behaviors.Success
import heraclius.behavior_tree.composites.Selector
import heraclius.behavior_tree.decorators.AlwaysFailure
import heraclius.behavior_tree.decorators.AlwaysSuccess

fun main() {
    val root = Selector(true) {
        AlwaysFailure {
            Success()
        }
        AlwaysSuccess {
            Failure()
        }
    }
    print(root.tick())
    print(root)
}
