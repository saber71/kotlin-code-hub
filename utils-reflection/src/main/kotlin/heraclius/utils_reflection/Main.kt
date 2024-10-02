package heraclius.utils_reflection

import kotlin.reflect.KMutableProperty
import kotlin.reflect.jvm.javaType

data class User(val name: String, var age: Int, val child: User? = null)

fun main() {
//    println(ClassFilter(ResourceClassesLoader("heraclius")).subClasses(ClassesLoader::class.java).classList)
    val user = ClassReflection.newInstance(User::class, "Heraclius", 18, User("aaa", 1))
    val member = user::class.members.toList()[0]
    if (member is KMutableProperty) {
        println(member.returnType.javaType == Int::class.java)
        ClassReflection.setProperty(user, "age", "20")
    }
    println(user::class.members.toList()[1].returnType.javaType)
    println(user::class.members.toList()[2].returnType.javaType)
    println(user)
}
