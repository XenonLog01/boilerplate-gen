package core.backend

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

class FileWriter {
    companion object {
        fun write(fileName: String, fileContents: String) {
            val file = File(fileName)
            file.writeText(fileContents)
        }

        fun createDir(dirPath: String) {
            Files.createDirectory(Paths.get(dirPath))
        }
    }
}