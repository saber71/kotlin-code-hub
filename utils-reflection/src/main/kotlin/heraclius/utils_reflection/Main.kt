package heraclius.utils_reflection

import heraclius.utils_reflection.classses_loader.ClassesLoader
import heraclius.utils_reflection.classses_loader.ResourceClassesLoader

fun main() {
    println(ClassFilter(ResourceClassesLoader("heraclius")).subClasses(ClassesLoader::class.java).classList)
}
