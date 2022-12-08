private fun String.isFileWithSize() = split(" ").first().toIntOrNull() != null

private fun String.isMoveToChildDirectory() = startsWith("\$ cd") && !endsWith("..")

private fun String.isMoveToParentDirectory() = startsWith("\$ cd") && (endsWith("..") || endsWith("/"))

private fun String.movePathOneBack() = substringBeforeLast("/").ifBlank { "/" }

fun main() {
    val day = "Day07"

    fun directoryContents(input: List<String>): MutableMap<String, Int> {
        val directoryContents = mutableMapOf<String, Int>()

        var currentDirectory = "/"
        input.forEach {
            when {
                it.isMoveToParentDirectory() -> currentDirectory = currentDirectory.movePathOneBack()
                it.isMoveToChildDirectory() -> currentDirectory =
                    "$currentDirectory/${it.split(" ").last()}".replace("//", "/")
                it.isFileWithSize() -> {
                    val fileSize = it.split(" ").first().toInt()

                    var directoryWithPath = currentDirectory
                    do {
                        val totalSize = directoryContents.getOrDefault(directoryWithPath, 0) + fileSize
                        directoryContents[directoryWithPath] = totalSize

                        if(directoryWithPath.equals("/")) break
                        directoryWithPath = directoryWithPath.movePathOneBack()
                    } while (true)
                }
            }
        }
        return directoryContents
    }

    fun part1(input: List<String>): Int {
        val directoryContents = directoryContents(input)
        return directoryContents.filter { it.value <= 100_000 }.values.sum()
    }

    fun part2(input: List<String>): Int {
        val foo = directoryContents(input)
        val totalFree = 70_000_000 - foo["/"]!!
        return foo.map { it.value }.sorted().first { totalFree+it > 30_000_000 }
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("${day}_test")
//    println(part1(testInput))
//    check(part1(testInput) == 95_437)

    // test if implementation meets criteria from the description, like:
//    val testInput2 = readInput("${day}_test")
//    println(part2(testInput2))
//    check(part2(testInput2) == 24_933_642)

    val input = readInput(day)
    println(part1(input))
    println(part2(input))
    check(part1(input) == 1_792_222)
    check(part2(input) == 1_112_963)
}
