fun main() {
    println("Searching for packet start marker...")
    val sum = getLastIndexOfMessageStartMarker("input.txt")
    println("Index of last letter in packet start marker: $sum")
}

fun getLastIndexOfMessageStartMarker(fileName: String): Int {
    var index = 0
    val charDeque = ArrayDeque<Char>()
    object {}.javaClass.getResourceAsStream(fileName)?.bufferedReader()?.forEachLine { line ->
        for (char in line) {
            index++
            if (charDeque.contains(char)) {
                removeFirstCharsUntilMatching(charDeque, char)
            }
            charDeque.add(char)
            if (charDeque.size == 14) {
                break
            }
        }
    }
    return index
}

private fun removeFirstCharsUntilMatching(charDeque: ArrayDeque<Char>, char: Char) {
    while (charDeque.first() != char) {
        charDeque.removeFirst()
    }
    charDeque.removeFirst()
}