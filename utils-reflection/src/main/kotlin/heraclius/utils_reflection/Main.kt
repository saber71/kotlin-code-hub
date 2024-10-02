package heraclius.utils_reflection

import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.jvm.javaType

data class User(val name: String, private var age: Int, val child: User? = null) {

    fun abc(name: String) {
        println(name)
    }

    fun abc(name: Int) {
        println(name)
    }
}


fun main() {
//    println(ClassFilter(ResourceClassesLoader("heraclius")).subClasses(ClassesLoader::class.java).classList)
    val user = ClassReflection.newInstance(User::class, "Heraclius", 18, User("aaa", 1))
    user.abc("dsa")
    val member = user::class.members.toList()[0]
    if (member is KMutableProperty) {
        println(member.returnType.javaType == Int::class.java)
        ClassReflection.setProperty(user, "age", "20")
    }
    println(user::class.members)
    println(user::class.memberFunctions)
}
