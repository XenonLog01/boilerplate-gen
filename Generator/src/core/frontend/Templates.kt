package core.frontend

open class Project(val name: String, val license: String) {
}

open class Language(val filename: String) {
    open var code: String = ""
}

class CHeaderFile(val fileName: String): Language(fileName) {
    private val nameUpper = fileName.uppercase()

    override var code =
        "#ifndef ${nameUpper}_H\n" +
        "#define ${nameUpper}_H\n" +
        "\n"                       +
        "#endif"
}

class CFile(val fileName: String): Language(fileName) {
    override var code = "#include \"$fileName.h\""

    val header = CHeaderFile(fileName)
}

class EmptyProject(name: String, license: String): Project(name, license)
