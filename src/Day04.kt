fun main() {
    val day = "Day04"

    fun part1(input: List<String>): Int {
        return input.fold(0) { accFullyContains, elfPair ->
            val fullyContains = elfPair
                .split(",")
                .map { elfSectionsStr ->
                    val nums = elfSectionsStr.split("-")
                    nums[0].toInt()..nums[1].toInt()
                }
                .chunked(2)
                .all { elfSections ->
                    val common = elfSections[0].intersect(elfSections[1])
                    elfSections[0].count() == common.count() || elfSections[1].count() == common.count()
                }

            val increment = if (fullyContains) 1 else 0
            accFullyContains + increment
        }
    }

    fun part2(input: List<String>): Int {
        return input.fold(0) { accFullyContains, elfPair ->
            val fullyContains = elfPair
                .split(",")
                .map { elfSectionsStr ->
                    val nums = elfSectionsStr.split("-")
                    nums[0].toInt()..nums[1].toInt()
                }
                .chunked(2)
                .all { elfSections ->
                    elfSections[0].intersect(elfSections[1]).isNotEmpty()
                }

            val increment = if (fullyContains) 1 else 0
            accFullyContains + increment
        }
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("${day}_test")
//    println(part1(testInput))
//    check(part1(testInput) == 2)

    // test if implementation meets criteria from the description, like:
//    val testInput2 = readInput("${day}_test")
//    println(part2(testInput2))
//    check(part2(testInput2) == 4)

    val input = readInput(day)
    println(part1(input))
    println(part2(input))
    check(part1(input) == 459)
    check(part2(input) == 779)
}
