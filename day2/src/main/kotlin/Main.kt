fun main() {
    println("Calculating score...")
    val score = calculateScore("input.txt")
    println("Your score: $score")
}

fun calculateScore(fileName: String): Int {
    var currentScore = 0
    object {}.javaClass.getResourceAsStream(fileName)?.bufferedReader()?.forEachLine {
        val lineAsChars = it.toCharArray()
        val myChar = determineMoveFromDesiredOutcome(lineAsChars[0], lineAsChars[2])
        currentScore += determineRoundScore(lineAsChars[0], myChar)
    }
    return currentScore
}

fun determineRoundScore(opponentMove: Char, myMove: Char): Int {
    val opponentAction = charToAction(opponentMove)
    val myAction = charToAction(myMove)

    return actionToScore(myAction) + matchupScore(opponentAction, myAction)
}

fun charToAction(move: Char): Action {
    return when(move) {
        'A' -> Action.ROCK
        'B' -> Action.PAPER
        'C' -> Action.SCISSORS
        else -> Action.UNKNOWN
    }
}

fun actionToScore(action: Action): Int {
    return when(action) {
        Action.ROCK -> 1
        Action.PAPER -> 2
        Action.SCISSORS -> 3
        Action.UNKNOWN -> 0
    }
}

fun matchupScore(opponentAction: Action, myAction: Action): Int {
    return when {
        ((opponentAction.ordinal + 1) % 3 == myAction.ordinal) -> 6
        (opponentAction == myAction) -> 3
        else -> 0
    }
}

fun determineMoveFromDesiredOutcome(opponentMove: Char, desiredOutcome: Char): Char {
    // X is loss, Y is draw, Z is win
    return when(desiredOutcome) {
        'X' -> if (opponentMove == 'A') 'C' else opponentMove.dec()
        'Y' -> opponentMove
        'Z' -> if (opponentMove == 'C') 'A' else opponentMove.inc()
        else -> 'U'
    }
}