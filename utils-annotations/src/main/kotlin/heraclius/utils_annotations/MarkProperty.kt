package heraclius.utils_annotations

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class MarkProperty(vararg val components: String)
