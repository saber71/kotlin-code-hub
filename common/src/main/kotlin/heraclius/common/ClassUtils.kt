package heraclius.common

import java.io.File
import java.net.URI
import java.net.URL
import java.net.URLDecoder
import java.util.jar.JarFile

object ClassUtils {
    private val _classLoader: ClassLoader = Thread.currentThread().contextClassLoader

    /**
     * 获取指定包下的所有类。
     *
     * @param packageName 包名
     * @return 包含所有类的列表
     */
    fun findClassesInPackage(packageName: String): List<Class<*>> {
        val packagePath = packageName.replace('.', '/')
        val resources = _classLoader.getResources(packagePath).toList()

        return resources.mapNotNull { it.toURI() }
            .flatMap { uri ->
                when (uri.scheme) {
                    "file" -> findClassesInDirectory(File(uri), packageName)
                    "jar" -> findClassesInJarFile(URI.create(uri.toString().substringBefore("!/") + "!").toURL())
                    else -> emptyList()
                }
            }
    }

    private fun findClassesInDirectory(directory: File, packageName: String): List<Class<*>> {
        return directory.listFiles()?.asSequence()?.flatMap { file ->
            if (file.isDirectory) {
                findClassesInDirectory(file, "$packageName.${file.name}")
            } else {
                val className = "${packageName}.${file.nameWithoutExtension}".replace('/', '.')
                try {
                    listOf(Class.forName(className, false, _classLoader))
                } catch (e: ClassNotFoundException) {
                    emptyList()
                }
            }
        }?.toList() ?: emptyList()
    }

    private fun findClassesInJarFile(jarFileUrl: URL): List<Class<*>> {
        val jarFile = JarFile(URLDecoder.decode(jarFileUrl.file, Charsets.UTF_8))
        return jarFile.entries().asSequence()
            .filter { it.name.endsWith(".class") }
            .map { it.name.replace('/', '.').removeSuffix(".class") }
            .mapNotNull { className ->
                try {
                    Class.forName(className, false, _classLoader)
                } catch (e: ClassNotFoundException) {
                    null
                }
            }
            .toList()
    }
}
