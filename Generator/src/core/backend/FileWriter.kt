package core.backend

import java.io.File

class FileWriter(private var fileName: String = "") {
    companion object {
        fun write(fileName: String, fileContents: String) {
            val file = File(fileName)
            file.writeText(fileContents)
        }
    }
}