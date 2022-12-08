fun String.asCreateMove(): Triple<Int, Int, Int> {
    val cratesToMove = this.substringAfter("move ").substringBefore(" from ").toInt()
    val fromStack = this.substringAfter(" from ").substringBefore(" to ").toInt()
    val toStack = this.substringAfter(" to ").toInt()

    return Triple(cratesToMove, fromStack, toStack)
}

fun main() {
    val day = "Day05"

    fun populateStacksWithCrate(stacks: MutableMap<Int, MutableList<String>>, rowOfCrates: String) {
        if (rowOfCrates.indexOf(("[")) == -1) return

        rowOfCrates.toCharArray().toList().chunked(4).forEachIndexed { i, s ->
            val columnId = i + 1
            val stack = stacks.getOrDefault(columnId, mutableListOf())
            if (s[1].toString() != " ") {
                stack.add(0, s[1].toString())
                stacks[columnId] = stack
            }
        }
    }

    fun moveCreatesOnStacksUsingMover9000(stacks: MutableMap<Int, MutableList<String>>, moveCrates: String) {
        val (cratesToMove, fromStack, toStack) = moveCrates.asCreateMove()

        val createsToMove = (1..cratesToMove).map { stacks[fromStack]!!.removeLast() }
        stacks[toStack]!!.addAll(createsToMove)
//        println(stacks)
    }

    fun moveCreatesOnStacksUsingMover9001(stacks: MutableMap<Int, MutableList<String>>, moveCrates: String) {
        val (cratesToMove, fromStack, toStack) = moveCrates.asCreateMove()

        val createsToMove = (1..cratesToMove).map { stacks[fromStack]!!.removeLast() }.reversed()
        stacks[toStack]!!.addAll(createsToMove)
//        println(stacks)
    }

    fun loadAndMoveCrates(input: List<String>, crateMover: (MutableMap<Int, MutableList<String>>, String) -> Unit): MutableMap<Int, MutableList<String>> {
        var parsedCrates = false

        val stacks = mutableMapOf<Int, MutableList<String>>()
        input.forEach {
            when (parsedCrates) {
                false -> populateStacksWithCrate(stacks, it)
                true -> crateMover(stacks, it) //
            }

            if (it.isNullOrBlank()) {
                parsedCrates = true
            }
        }
        return stacks
    }

    fun part1(input: List<String>): String {
        val stacks = loadAndMoveCrates(input) { stacks, it ->
            moveCreatesOnStacksUsingMover9000(stacks, it)
        }
//        println(stacks)
        return stacks.keys.sorted().map { stacks[it]!!.last() }.joinToString("")
    }

    fun part2(input: List<String>): String {
        val stacks = loadAndMoveCrates(input) { stacks, it ->
            moveCreatesOnStacksUsingMover9001(stacks, it)
        }
//        println(stacks)
        return stacks.keys.sorted().map { stacks[it]!!.last() }.joinToString("")
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("${day}_test")
//    println(part1(testInput))
//    check(part1(testInput) == "CMZ")

    // test if implementation meets criteria from the description, like:
//    val testInput2 = readInput("${day}_test")
//    println(part2(testInput2))
//    check(part2(testInput2) == "MCD")

    val input = readInput(day)
    println(part1(input))
    println(part2(input))
    check(part1(input) == "TPGVQPFDH")
    check(part2(input) == "DMRDFRHHH")
}
