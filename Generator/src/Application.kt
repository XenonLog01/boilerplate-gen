import core.backend.FileWriter
import core.frontend.*

fun genLicnese(l: String, projectName: String) {
    when (l) {
        "MIT" -> {
            val name = ConsoleUtil.getStringInput("What should the name on the License be?")
            FileWriter.write("./res/LICENSE.md", genMitLicense(name))
        }
        "GNU" -> {
            val name = ConsoleUtil.getStringInput("What should the name on the License be?")
            FileWriter.write("./res/LICENSE.md", genGNUv3License(name, projectName))
        }
    }
}

fun genCFile(name: String) {
    val c = CFile(name)

    FileWriter.write("./${c.fileName}.c", c.code)
    FileWriter.write("./${c.header.fileName}.h", c.header.code)
}

fun main(){
    val prompt = "What Template do you want to use:"
    val opts = listOf(
        "C File",
        "Empty Project",
        "New C Project"
    )

    val res = ConsoleUtil.showOptionMenu(prompt, opts)

    when (res) {
        "C File" -> {
            val name = ConsoleUtil.getStringInput("What should the file be called?")
            genCFile(name)
        }
        "Empty Project" -> {
            val projectName = ConsoleUtil.getStringInput("What should the project's name be?")
            val license = ConsoleUtil.showOptionMenu(
        "What should the project license be?",
                 listOf("None", "MIT", "GNU v3")
            )

            val project = Project(projectName, license.uppercase())

            genLicnese(project.license, projectName)

            FileWriter.createDir("./src")
            FileWriter.write("./README.md", "# $projectName \n")
            FileWriter.write("./.gitignore", BASE_GITIGNORE)
        }
        "New C Project" -> {
            val projectName = ConsoleUtil.getStringInput("What should the project's name be?")
            val license = ConsoleUtil.showOptionMenu(
        "What should the project license be?",
                 listOf("None", "MIT", "GNU v3")
            )

            val project = Project(projectName, license.uppercase())

            genLicnese(project.license, projectName)

            FileWriter.createDir("./src")
            FileWriter.write("./README.md", "# $projectName \n")
            FileWriter.write("./.gitignore", BASE_GITIGNORE)

            val entryCode  =
                "#include <stdio.h>\n"                 +
                "\n"                                   +
                "int main(int argc, char *argv[]) {\n" +
                "\tprintf(\"Hello, %s!\", \"World\");\n" +
                "\treturn 0;\n"                        +
                "}"

            FileWriter.write("./src/Application.c", entryCode)
        }
    }

    println("You selected: $res")
}