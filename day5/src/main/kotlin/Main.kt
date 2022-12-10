import java.util.Stack

fun main() {
    println("Finding top crates...")
    val topCrates = getTopCrates("input.txt")
    println("Top crates: $topCrates")
}

fun getTopCrates(fileName: String): String {
    val (stackList, instructions) = parseInput(fileName)
    executeInstructions(instructions, stackList)
    return stackList.fold("") { result, next -> result + next.peek() }
}

private fun executeInstructions(
    instructions: List<Array<Int>>,
    stackList: List<Stack<Char>>
) {
    val tempStack = Stack<Char>()
    for (instruction in instructions) {
        for (i in 1..instruction[0]) {
            val itemToMove = stackList[instruction[1] - 1].pop()
            tempStack.push(itemToMove)
        }
        while (tempStack.isNotEmpty()) {
            stackList[instruction[2] - 1].push(tempStack.pop())
        }
    }
}

// Returns initial stacks, and list of instructions
fun parseInput(fileName: String): Pair<List<Stack<Char>>, List<Array<Int>>> {
    val stackList = ArrayList<Stack<Char>>()
    val instructions = ArrayList<Array<Int>>()
    object {}.javaClass.getResourceAsStream(fileName)?.bufferedReader()?.forEachLine { line ->
        if (line.isNotEmpty()) {
            when (line.trim()[0]) {
                '[' -> parseStackLine(line.toCharArray(), stackList)
                'm' -> parseInstructionLine(line, instructions)
                else -> Unit
            }
        }
    }
    for (stack in stackList) {
        stack.reverse()
    }
    return Pair(stackList, instructions)
}

fun parseStackLine(chars: CharArray, stackList: ArrayList<Stack<Char>>) {
    for (i in chars.indices) {
        when (i % 4) {
            1 -> {
                if (stackList.size == (i.floorDiv(4))) {
                    stackList.add(Stack<Char>())
                }
                if (chars[i] != ' ') {
                    stackList[i.floorDiv(4)].add(chars[i])
                }
            }
        }
    }
    println("Stacks: $stackList")
}

fun parseInstructionLine(line: String, instructions: ArrayList<Array<Int>>) {
    val numbers = Regex("[0-9]+").findAll(line).map { it.value.toInt() }.toList().toTypedArray()
    instructions.add(numbers)
}