package day13

import java.io.File

fun main() {

    fun Pair<Int, Int>.fold(axis: Char, pos: Int) =
        when (axis) {
            'x' -> if (pos < first) Pair(2 * pos - first, second) else this
            'y' -> if (pos < second) Pair(first, 2 * pos - second) else this
            else -> throw Exception()
        }

    var points = mutableSetOf<Pair<Int, Int>>()
    val folds = mutableListOf<Pair<Char, Int>>()
    var readingPoints = true
    File("src/day13/full.in").forEachLine { line ->
        if (line.isEmpty()) {
            readingPoints = false
        } else if (readingPoints) {
            val (x, y) = line.split(",").map { it.toInt() }
            points.add(Pair(x, y))
        } else {
            val (axis, pos) = line.removePrefix("fold along ").split("=")
            folds.add(Pair(axis[0], pos.toInt()))
        }
    }

    folds.forEach { (axis, pos) ->
        points = points.mapTo(mutableSetOf()) { it.fold(axis, pos) }
        println(points.size)
    }

    val xx = points.maxOf { it.first }
    val yy = points.maxOf { it.second }
    val board = Array(xx + 1) { Array(yy + 1) { ' ' } }
    points.forEach { (x, y) -> board[x][y] = '#' }
    for (y in 0..yy) {
        for (x in 0..xx) {
            print(board[x][y])
        }
        println()
    }

    // FJAHJGAH
}