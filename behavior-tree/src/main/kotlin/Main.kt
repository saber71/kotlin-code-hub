import behaviors.Failure
import behaviors.Success
import composites.*
import decorators.AlwaysFailure
import decorators.AlwaysSuccess

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
