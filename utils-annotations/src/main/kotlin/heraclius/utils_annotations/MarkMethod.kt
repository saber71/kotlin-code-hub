package heraclius.utils_annotations

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class MarkMethod(vararg val components: String)
