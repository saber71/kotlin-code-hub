package heraclius.utils_reflection

class ClassReflection<T>(val cls: Class<T>) {
    fun setProperty(name: String, value: Any): ClassReflection<T> {

        return this
    }
}
