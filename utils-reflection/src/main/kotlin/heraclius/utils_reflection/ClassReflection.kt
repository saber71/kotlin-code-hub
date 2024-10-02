package heraclius.utils_reflection

import heraclius.utils_dict.Dict
import heraclius.utils_type.TypeUtils
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible
import kotlin.reflect.jvm.javaType

object ClassReflection {
    fun <T : Any> newInstance(cls: KClass<T>, vararg args: Any): T {
        if (cls.objectInstance != null) return cls.objectInstance!!
        return cls.java.getConstructor(*args.map { TypeUtils.javaClass(it) }.toTypedArray()).newInstance(*args)
    }

    fun <T : Any> setProperty(inst: T, name: String, value: Any, throws: Boolean = false): Boolean {
        val kClass = inst::class
        val member = kClass.memberProperties.find { it.name == name }
        if (member == null) {
            if (throws) throw NoSuchFieldException("No such field $name")
            return false
        }
        if (member is KMutableProperty<*>) {
            member.isAccessible = true
            member.setter.call(inst, TypeUtils.to(member.returnType.javaType as Class<*>, value))
            return true
        } else if (throws)
            throw IllegalArgumentException("Property $name is not mutable")
        return false
    }

    fun <T : Any> setProperties(inst: T, keyValue: Map<Any, Any>, throws: Boolean = false): T {
        for (entry in keyValue) {
            val key = TypeUtils.to(String::class.java, entry.key)
            setProperty(inst, key, entry.value, throws)
        }
        return inst
    }

    fun <T : Any> setProperties(inst: T, dict: Dict, throws: Boolean = false): T {
        @Suppress("UNCHECKED_CAST")
        return setProperties(inst, dict.toMap() as Map<Any, Any>, throws)
    }

    fun <T : Any> assign(dst: T, vararg src: Any, throws: Boolean = false): T {
        for (item in src) {
            val srcKClass = src::class
            for (property in srcKClass.memberProperties) {
                setProperty(dst, property.name, property.getter.call(src) as Any, throws)
            }
        }
        return dst
    }
}
