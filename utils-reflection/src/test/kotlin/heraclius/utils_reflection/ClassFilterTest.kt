package heraclius.utils_reflection

import heraclius.utils_reflection.classses_loader.*
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class ClassFilterTest {
    @Test
    fun subClasses() {
        val classFilter = ClassFilter(ResourceClassesLoader("heraclius"))
        val subClass = classFilter.subClasses(ClassesLoader::class.java)
        assertContentEquals(
            subClass.values,
            mutableListOf(
                DefaultClassesLoader::class.java,
                FileClassesLoader::class.java,
                JarClassesLoader::class.java,
                LocalJarClassesLoader::class.java,
                ResourceClassesLoader::class.java
            )
        )
    }

    @Test
    fun annotatedWith() {
        val classFilter = ClassFilter(ResourceClassesLoader("heraclius"))
        val subClass = classFilter.annotatedWith(Annotates.Class::class.java)
        assertContentEquals(subClass.values, mutableListOf(ClassWithAnnotation::class.java))
    }

    @Annotates.Class
    class ClassWithAnnotation
}
