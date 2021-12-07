package day05

import java.io.File

fun main() {
    var res = 0
    val board = Array(1_000) { IntArray(1_000) }
    File("src/day05/full.in").forEachLine { line ->
        var (x1, y1, x2, y2) = line.split(",", " -> ").map { it.toInt() }
        println("($x1,$y1) -> ($x2,$y2)")
        while (true) {
            if (board[x1][y1]++ == 1) res++
            if (x1 == x2 && y1 == y2) break
            if (x1 < x2) x1++
            if (x1 > x2) x1--
            if (y1 < y2) y1++
            if (y1 > y2) y1--
        }
        //board.forEach { println(it.contentToString()) }
    }
    println(res)
}