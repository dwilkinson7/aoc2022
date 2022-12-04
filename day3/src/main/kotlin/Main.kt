fun main() {
    println("Summing badge items...")
    val sum = getSumOfMisplacedItemPriorities("input.txt")
    println("Sum of badge items: $sum")
}

fun getSumOfMisplacedItemPriorities(fileName: String): Int {
    var prioritySum = 0
    val firstItemSet = HashSet<Char>()
    val secondItemSet = HashSet<Char>()
    var groupCounter = 1
    object {}.javaClass.getResourceAsStream(fileName)?.bufferedReader()?.forEachLine { line ->
        when(groupCounter) {
            1 -> line.forEach { firstItemSet.add(it) }
            2 -> line.forEach {
                if (firstItemSet.contains(it)) {
                    secondItemSet.add(it)
                }
            }
            3 -> run finalLoop@ {
                line.forEach {
                    if (secondItemSet.contains(it)) {
                        prioritySum += calculatePriority(it)
                        return@finalLoop
                    }
                }
            }
        }
        groupCounter++
        if (groupCounter == 4) {
            groupCounter = 1
            firstItemSet.clear()
            secondItemSet.clear()
        }
    }
    return prioritySum
}

fun calculatePriority(item: Char): Int {
    return with(item) {
        when {
            isLowerCase() -> code - 96
            else -> code - 38
        }
    }
}