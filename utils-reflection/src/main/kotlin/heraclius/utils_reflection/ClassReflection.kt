package heraclius.utils_reflection

import heraclius.utils_dict.Dict
import heraclius.utils_type.TypeUtils
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty
import kotlin.reflect.jvm.javaType

object ClassReflection {
    fun <T : Any> newInstance(cls: KClass<T>, vararg args: Any): T {
        if (cls.objectInstance != null) return cls.objectInstance!!
        return cls.java.getConstructor(*args.map { TypeUtils.javaClass(it) }.toTypedArray()).newInstance(*args)
    }

    fun <T : Any> setProperty(inst: T, name: String, value: Any) {
        val kClass = inst::class
        val member = kClass.members.find { it.name == name }
        if (member == null) throw IllegalArgumentException("No such property: $name")
        if (member is KMutableProperty)
            member.setter.call(inst, TypeUtils.to(member.returnType.javaType as Class<*>, value))
        else
            throw IllegalArgumentException("${member.name} is not a mutable property")
    }

    fun <T : Any> setProperties(inst: T, keyValue: Map<Any, Any>): T {
        for (entry in keyValue) {
            val key = TypeUtils.to(String::class.java, entry.key)
            setProperty(inst, key, entry.value)
        }
        return inst
    }

    fun <T : Any> setProperty(inst: T, dict: Dict): T {
        @Suppress("UNCHECKED_CAST")
        return setProperties(inst, dict.toMap() as Map<Any, Any>)
    }
}
