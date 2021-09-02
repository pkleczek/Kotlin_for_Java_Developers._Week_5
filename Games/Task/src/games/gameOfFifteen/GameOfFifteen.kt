package games.gameOfFifteen

import board.*
import games.game.Game

/*
 * Implement the Game of Fifteen (https://en.wikipedia.org/wiki/15_puzzle).
 * When you finish, you can play the game by executing 'PlayGameOfFifteen'.
 */
fun newGameOfFifteen(initializer: GameOfFifteenInitializer = RandomGameInitializer()): Game = GameOfFifteen(initializer)

class GameOfFifteen(private val initializer: GameOfFifteenInitializer) : Game {
    private val board = createGameBoard<Int?>(4)
    private val solution: List<Int?> by lazy {
        (1 until board.width * board.width).toMutableList<Int?>().also { it.add(null) }
    }

    override fun initialize() {
        initializer.initialPermutation.zip(board.cellsInOrder())
            .forEach { (value, cell) -> board[cell] = value }
    }

    override fun canMove(): Boolean {
        return true
    }

    override fun hasWon(): Boolean {
        return board.valuesInOrder() == solution
    }

    override fun processMove(direction: Direction) {
        val blank = board.find { it == null }!!
        board.run {
            val neighbour = blank.getNeighbour(direction.reversed())
            if (neighbour != null) {
                this[blank] = this[neighbour]
                this[neighbour] = null
            }
        }
    }

    override fun get(i: Int, j: Int): Int? {
        return board.run { get(getCell(i, j)) }
    }

    private fun SquareBoard.cellsInOrder(): List<Cell> {
        return (1..width).flatMap { i ->
            (1..width).map { j ->
                board.getCell(i, j)
            }
        }
    }

    private fun <T> GameBoard<T>.valuesInOrder(): List<T?> {
        return cellsInOrder().map { this[it] }
    }
}

