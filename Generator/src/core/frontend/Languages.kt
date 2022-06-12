package core.frontend

import core.backend.FileType

abstract class Language {
    abstract val code: String
    abstract val fileName: String
}

class CHeaderFile(override val fileName: String): Language() {
    private val nameUpper = fileName.uppercase()

    override val code =
        "#ifndef ${nameUpper}_H\n" +
        "#define ${nameUpper}_H\n" +
        "\n"                       +
        "#endif"
}

class CFile(override val fileName: String): Language() {
    override val code = "#include \"$fileName.h\""

    val header = CHeaderFile(fileName)
}
