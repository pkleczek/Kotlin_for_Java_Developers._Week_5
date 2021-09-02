package games.gameOfFifteen

/*
 * This function should return the parity of the permutation.
 * true - the permutation is even
 * false - the permutation is odd
 * https://en.wikipedia.org/wiki/Parity_of_a_permutation

 * If the game of fifteen is started with the wrong parity, you can't get the correct result
 *   (numbers sorted in the right order, empty cell at last).
 * Thus the initial permutation should be correct.
 */

fun isEven(permutation: List<Int>): Boolean {
    var inverts = 0
    val len = permutation.size
    for (i in 0 until len) {
        for (j in i + 1 until len) {
            val pi = permutation[i]
            val pj = permutation[j]
            if (pi > pj) {
                inverts += 1
            }
        }
    }
    return inverts % 2 == 0
}