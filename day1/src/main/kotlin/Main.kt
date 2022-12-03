import java.util.PriorityQueue

fun main() {
    println("Counting calories...")
    val sum = getSumOfThreeLargestCalories("input.txt")
    println("Sum of three largest calories: $sum")
}

fun getSumOfThreeLargestCalories(fileName: String): Int {
    val heap = PriorityQueue<Int>()
    var currentlyCountedCalories = 0
    object {}.javaClass.getResourceAsStream(fileName)?.bufferedReader()?.forEachLine {
        if (it.isNotEmpty()) {
            currentlyCountedCalories += it.toInt()
        } else {
            heap.add(currentlyCountedCalories)
            // Only maintain top 3 values
            if (heap.size > 3) {
                heap.remove()
            }
            currentlyCountedCalories = 0
        }
    }
    return heap.sum()
}
