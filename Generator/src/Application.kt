import core.backend.FileWriter
import core.frontend.CFile
import core.frontend.ConsoleUtil

fun main(){
    val prompt = "What Language:"
    val opts = listOf("C")

    var res = ConsoleUtil.showOptionMenu(prompt, opts)

    when (res) {
        "C" -> {
            val name = ConsoleUtil.getStringInput("What should the file be called?")
            val c = CFile(name)

            FileWriter.write("./res/${c.fileName}.c", c.code)
            FileWriter.write("./res/${c.header.fileName}.h", c.header.code)
        }
    }

    println("You selected: $res")
}