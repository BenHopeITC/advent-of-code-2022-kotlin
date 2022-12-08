fun main() {

    fun part3(input: List<String>) = input
        .map { (it[0] - 'A') to (it[2] - 'X') }
        .sumOf { (a, b) -> 3 * (4 + b - a).rem(3) + b + 1 }

    fun part1(input: List<String>): Int {

        val games = input.map {
            val actions = it.split(" ")
            actions[0] to actions[1]
        }

        val pointsForShape = games.map {
            when (it.second.uppercase()) {
                "X" -> 1
                "Y" -> 2
                "Z" -> 3
                else -> 0
            }
        }.sum()

        val pointsForResult = games.map {
            when (it.first.uppercase() to it.second.uppercase()) {
                "A" to "Z",
                "B" to "X",
                "C" to "Y"
                -> 0
                "A" to "X",
                "B" to "Y",
                "C" to "Z"
                -> 3
                else -> 6
            }
        }.sum()

        return pointsForShape + pointsForResult
    }

    fun part2(input: List<String>): Int {
        val games = input.map {
            val actions = it.split(" ")
            actions[0] to actions[1]
        }.map {
            it.first to when(it.second) {
                "X" -> if (it.first=="A") "C" else if (it.first=="B") "A" else "B"
                "Z" -> if (it.first=="A") "B" else if (it.first=="B") "C" else "A"
                else -> it.first
            }
        }

        val pointsForShape = games.map {
            when (it.second.uppercase()) {
                "A" -> 1
                "B" -> 2
                "C" -> 3
                else -> 0
            }
        }.sum()

        val pointsForResult = games.map {
            when (it.first.uppercase() to it.second.uppercase()) {
                "A" to "C",
                "B" to "A",
                "C" to "B"
                -> 0
                "A" to "A",
                "B" to "B",
                "C" to "C"
                -> 3
                else -> 6
            }
        }.sum()

        return pointsForShape + pointsForResult
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day02_test")
//    println(part1(testInput))
//    check(part1(testInput) == 15)

//    val testInput2 = readInput("Day02_test2")
//    println(part2(testInput2))
//    check(part2(testInput2) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
    println(part3(input))

    check(part1(input) == 11603)
    check(part2(input) == 12725)
}
