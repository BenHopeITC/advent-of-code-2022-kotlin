fun List<Char>.areUnique(): Boolean {
    return this.size == this.toSet().size
}

fun String.firstUniqueSequenceOf(length: Int) = String(this.toCharArray().toList()
        .windowed(length)
        .first { it.areUnique() }
        .toCharArray())

fun main() {
    val day = "Day06"

    fun part1(input: String) = input.indexOf(input.firstUniqueSequenceOf(4)) + 4

    fun part2(input: String) = input.indexOf(input.firstUniqueSequenceOf(14)) + 14

    // test if implementation meets criteria from the description, like:
//    val testInput = readInputAsText("${day}_test")
//    println(part1(testInput))
//    check(part1(testInput) == 7)

    // test if implementation meets criteria from the description, like:
//    val testInput2 = readInputAsText("${day}_test")
//    println(part2(testInput2))
//    check(part2(testInput2) == 19)

    val input = readInputAsText(day)
    println(part1(input))
    println(part2(input))
    check(part1(input) == 1896)
    check(part2(input) == 3452)
}
