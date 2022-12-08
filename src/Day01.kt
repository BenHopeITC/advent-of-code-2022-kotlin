fun main() {

    fun calsPerElf(input: String): List<Int> {
        return input.split("\n\n").map(String::toInt)
    }

    fun part1(input: String): Int {
        return calsPerElf(input).max()
    }

    fun part2(input: String): Int {
        return calsPerElf(input).sorted().takeLast(3).sum()
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_test")
//    println(part1(testInput))
//    check(part1(testInput) == 24000)

    // test if implementation meets criteria from the description, like:
//    val testInput2 = readInput("Day01_test")
//    println(part2(testInput2))
//    check(part2(testInput2) == 45000)

    val input = readInputAsText("Day01")
    println(part1(input))
    println(part2(input))

    check(part1(input) == 70369)
    check(part2(input) == 203002)
}
