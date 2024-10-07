package heraclius.utils_reflection

import heraclius.utils_reflection.classses_loader.ClassesLoader
import heraclius.utils_reflection.classses_loader.DefaultClassesLoader
import heraclius.utils_reflection.classses_loader.ResourceClassesLoader

class ClassFilter(private val _classesLoader: ClassesLoader) {
    private val _lazyValues = lazy { _classesLoader.load() }
    val values: List<Class<*>> get() = _lazyValues.value

    companion object {
        private val loadClasses = lazy { ClassFilter(ResourceClassesLoader("heraclius")) }

        fun fromClassList(classList: List<Class<*>>): ClassFilter {
            return ClassFilter(DefaultClassesLoader(classList))
        }

        fun loadClasses(): ClassFilter {
            return loadClasses.value
        }
    }

    fun subClasses(superClass: Class<*>): ClassFilter {
        return fromClassList(values.filter { superClass.isAssignableFrom(it) && it != superClass })
    }

    fun annotatedWith(annotation: Class<out Annotation>): ClassFilter {
        return fromClassList(values.filter { it.isAnnotationPresent(annotation) })
    }
}
