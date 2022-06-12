package core.frontend

class ConsoleUtil {
    companion object {

        fun showOptionMenu(message: String, options: List<String>): String {
            println(message)

            for (i in options.indices) {
                println("${i+1}) ${options[i]}")
            }

            val optionsIdx = getChoice(options)

            return options[optionsIdx]
        }

        fun getStringInput(prompt: String): String {
            println(prompt)

            return readln()
        }

        private fun getChoice(options: List<String>): Int {
            print("Choice:\t")
            val option = readln()
            var optionsIdx = 0

            optionsIdx = try {
                option.toInt() - 1
            } catch (e: Exception) {
                println("That is not a valid character(numbers only)!")
                getChoice(options)
            }

            if (optionsIdx > options.size-1) {
                println("That is not an option!")
                optionsIdx = getChoice(options)
            }

            return optionsIdx
        }

    }
}
