import core.backend.FileWriter
import core.frontend.*

fun main(){
    val prompt = "What Template do you want to use:"
    val opts = listOf("C File",
                      "Empty Project")

    val res = ConsoleUtil.showOptionMenu(prompt, opts)

    when (res) {
        "C File" -> {
            val name = ConsoleUtil.getStringInput("What should the file be called?")
            val c = CFile(name)

            FileWriter.write("./${c.fileName}.c", c.code)
            FileWriter.write("./${c.header.fileName}.h", c.header.code)
        }
        "Empty Project" -> {
            val projectName = ConsoleUtil.getStringInput("What should the project's name be?")
            val license = ConsoleUtil.showOptionMenu(
        "What should the project license be?",
                 listOf("None", "MIT", "GNU v3")
            )

            val project = EmptyProject(projectName, license.uppercase())

            FileWriter.createDir("./res/src")

            when (project.license) {
                "MIT" -> {
                    val name = ConsoleUtil.getStringInput("What should the name on the License be?")
                    FileWriter.write("./res/LICENSE.md", genMitLicense(name))
                }
                "GNU" -> {
                    val name = ConsoleUtil.getStringInput("What should the name on the License be?")
                    FileWriter.write("./res/LICENSE.md", genGNUv3License(name, project.name))
                }
            }

            FileWriter.write("./res/README.md", "# $projectName \n")
            FileWriter.write("./res/.gitignore", BASE_GITIGNORE)
        }
    }

    println("You selected: $res")
}