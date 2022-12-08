inline infix fun String.intersect(other: String) = toSet() intersect other.toSet()
inline infix fun Set<Char>.intersect(other: String) = toSet() intersect other.toSet()

fun main() {

    fun valueOfItem(item: Char): Int = if (item.isLowerCase()) item - 'a' + 1 else item - 'A' + 27

    fun part1(input: List<String>): Int {
        return input.fold(0) { acc, rucksack ->
            val compartment1 = rucksack.substring(0, rucksack.length / 2)
            val compartment2 = rucksack.substring(rucksack.length / 2)
            val commonItem = compartment1 intersect compartment2
            acc + valueOfItem(commonItem.single())
        }
    }

    fun part2(input: List<String>): Int {
        return input.chunked(3).fold(0) { acc, groupRucksacks ->
            val (a, b, c) = groupRucksacks
            val badge = a intersect b intersect c
            acc + valueOfItem(badge.single())
        }
    }

    fun part1old2(input: List<String>): Int {
        return input.fold(0) { acc, rucksack ->
            val compartment1 = rucksack.toCharArray(0, rucksack.length / 2)
            val compartment2 = rucksack.toCharArray(rucksack.length / 2)
            val commonItem = compartment1.intersect(compartment2.toSet()).single()
            acc + valueOfItem(commonItem)
        }
    }

    fun part1old(input: List<String>): Int {
        return input.fold(0) { acc, rucksack ->
            val parts = rucksack.substring(0, rucksack.length / 2) to rucksack.substring(rucksack.length / 2)
            var inBoth: Char = 'a'
            parts.first.forEach { item ->
                if (parts.second.contains(item)) {
                    inBoth = item
                }
            }
            acc + valueOfItem(inBoth)
        }
    }

    fun part2Old2(input: List<String>): Int {
        return input.chunked(3).fold(0) { acc, groupRucksacks ->
            val badge = groupRucksacks[0].toCharArray()
                .intersect(groupRucksacks[1].toSet())
                .intersect(groupRucksacks[2].toSet())
                .single()
            acc + valueOfItem(badge)
        }
    }

    fun part2old(input: List<String>): Int {
        return (1..input.size / 3).map {
            val startIndex = (it - 1) * 3
            Triple(input[startIndex], input[startIndex + 1], input[startIndex + 2])
        }.fold(0) { acc, group ->
            var badge: Char = 'a'
            group.first.forEach { item ->
                if (group.second.contains(item) && group.third.contains(item)) {
                    badge = item
                }
            }
            acc + valueOfItem(badge)
        }
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day03_test")
//    println(part1(testInput))
//    check(part1(testInput) == 157)

    // test if implementation meets criteria from the description, like:
//    val testInput2 = readInput("Day03_test")
//    println(part2(testInput2))
//    check(part2(testInput2) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))

    check(part1(input) == 7446)
    check(part1old(input) == 7446)
    check(part2(input) == 2646)
}
