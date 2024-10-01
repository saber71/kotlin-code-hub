package heraclius.utils_reflection

import heraclius.utils_reflection.classses_loader.ClassesLoader
import heraclius.utils_reflection.classses_loader.DefaultClassesLoader

class ClassFilter(private val _classesLoader: ClassesLoader) {
    private val _lazyClassList = lazy { _classesLoader.load() }
    val classList: List<Class<*>> get() = _lazyClassList.value

    companion object {
        fun fromClassList(classList: List<Class<*>>): ClassFilter {
            return ClassFilter(DefaultClassesLoader(classList))
        }
    }

    fun subClasses(superClass: Class<*>): ClassFilter {
        return fromClassList(classList.filter { superClass.isAssignableFrom(it) && it != superClass })
    }
    
    fun annotatedWith(annotation: Class<out Annotation>): ClassFilter {
        return fromClassList(classList.filter { it.isAnnotationPresent(annotation) })
    }
}
