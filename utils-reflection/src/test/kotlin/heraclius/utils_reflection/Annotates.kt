package heraclius.utils_reflection

object Annotates {
    @Target(AnnotationTarget.CLASS)
    @Retention(AnnotationRetention.RUNTIME)
    annotation class Class
}
