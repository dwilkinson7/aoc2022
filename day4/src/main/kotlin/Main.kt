fun main() {
    println("Finding ranges that overlap with the other...")
    val sum = getNumberOfContainedPairs("input.txt")
    println("Number of overlapping pairs: $sum")
}

fun getNumberOfContainedPairs(fileName: String): Int {
    var overlappingPairs = 0
    object {}.javaClass.getResourceAsStream(fileName)?.bufferedReader()?.forEachLine { line ->
        val ranges = line.split(',')
        val firstRange = ranges[0].split('-').map { it.toInt() }
        val secondRange = ranges[1].split('-').map { it.toInt() }

        if (doRangesOverlap(firstRange, secondRange)) {
            overlappingPairs++
        }
    }
    return overlappingPairs
}

private fun doRangesOverlap(
    firstRange: List<Int>,
    secondRange: List<Int>,
): Boolean {
    if ((firstRange[1] >= secondRange[0] && firstRange[0] <= secondRange[0])
        || (firstRange[1] >= secondRange[1] && firstRange[0] <= secondRange[1])
        || (firstRange[0] >= secondRange[0] && firstRange[0] <= secondRange[1])
        || (firstRange[1] >= secondRange[0] && firstRange[1] <= secondRange[1])) {
        return true
    }
    return false
}