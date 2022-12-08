data class Tree(
    val treeHeight: Int,
    val treesLeft: List<Int>,
    val treesRight: List<Int>,
    val treesTop: List<Int>,
    val treesBottom: List<Int>
) {
    fun isVisible(): Boolean {
        if (treesLeft.isEmpty() || treesRight.isEmpty() || treesTop.isEmpty() || treesBottom.isEmpty()) {
            return true
        }
        if (treesLeft.none { it >= treeHeight } || treesRight.none { it >= treeHeight } || treesTop.none { it >= treeHeight } || treesBottom.none { it >= treeHeight }) {
            return true
        }
        return false // otherwise not visible
    }

    fun viewScore(): Int {
        val scoreLeft = treesLeft.indexOfFirst { it >= treeHeight }.toViewScore(treesLeft.size)
        val scoreRight = treesRight.indexOfFirst { it >= treeHeight }.toViewScore(treesRight.size)
        val scoreTop = treesTop.indexOfFirst { it >= treeHeight }.toViewScore(treesTop.size)
        val scoreBottom = treesBottom.indexOfFirst { it >= treeHeight }.toViewScore(treesBottom.size)

        return scoreLeft * scoreRight * scoreTop * scoreBottom
    }
}

private fun Int.toViewScore(treeCount: Int) = if (this == -1) treeCount else this + 1

fun main() {
    val day = "Day08"

    fun treesFrom(input: List<String>): List<Tree> {
        val columns = mutableMapOf<Int, String>()
        input.map { treeRow ->
            treeRow.toCharArray()
        }.forEach {
            it.forEachIndexed { i, c ->
                val currentVal = columns.getOrDefault(i, "")
                columns[i] = currentVal + c.toString()
            }
        }
        // .also { println(columns) }

        val treeObjs = mutableListOf<Tree>()
        input.mapIndexed { rowIndex, treeRow ->
            val trees = treeRow.toCharArray().map { it.toString().toInt() }

            trees.forEachIndexed { columnIndex, treeHeight ->
                val treesLeft = trees.subList(0, columnIndex).reversed()
                val treesRight = trees.subList(columnIndex + 1, trees.size)
                // println("$tree trees left: $treesLeft, right: $treesRight")

                val colTrees = columns[columnIndex]!!.toCharArray().map { it.toString().toInt() }
                val treesTop = colTrees.subList(0, rowIndex).reversed()
                val treesBottom = colTrees.subList(rowIndex + 1, colTrees.size)
                // println("$tree trees top: $treesTop, bottom: $treesBottom")

                treeObjs.add(Tree(treeHeight, treesLeft, treesRight, treesTop, treesBottom))
            }
        }
        return treeObjs
    }

    fun part1(input: List<String>): Int {
        return treesFrom(input).count { it.isVisible() }
    }

    fun part2(input: List<String>): Int {
        return treesFrom(input).maxOf { it.viewScore() }
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("${day}_test")
//    println(part1(testInput))
//    check(part1(testInput) == 21)

    // test if implementation meets criteria from the description, like:
//    val testInput2 = readInput("${day}_test")
//    println(part2(testInput2))
//    check(part2(testInput2) == 8)

    val input = readInput(day)
    println(part1(input))
    println(part2(input))
    check(part1(input) == 1715)
    check(part2(input) == 374400)
}
