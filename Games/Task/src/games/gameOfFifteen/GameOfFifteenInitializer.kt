package games.gameOfFifteen

import kotlin.random.Random

interface GameOfFifteenInitializer {
    /*
     * Even permutation of numbers 1..15
     * used to initialized the first 15 cells on a board.
     * The last cell is empty.
     */
    val initialPermutation: List<Int>
}

class RandomGameInitializer : GameOfFifteenInitializer {
    private val range = 1..15

    /*
     * Generate a random permutation from 1 to 15.
     * `shuffled()` function might be helpful.
     * If the permutation is not even, make it even (for instance,
     * by swapping two numbers).
     */
    override val initialPermutation by lazy {
        val shuffled = range.shuffled().toMutableList()
        val size = shuffled.size
        while (!isEven(shuffled)) {
            val (i, j) = Random.nextInt(size) to Random.nextInt(size)
            val (vi, vj) = shuffled[i] to shuffled[j]
            shuffled[j] = vi
            shuffled[i] = vj
        }
        shuffled
    }
}

