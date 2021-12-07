package day05

import java.io.File
import java.lang.Integer.max
import java.lang.Integer.min

fun main() {
    val board = Array(1_000) { IntArray(1_000) }
    var res = 0
    File("src/day05/full.in").forEachLine { line ->
        val (x1, y1, x2, y2) = line.split(",", " -> ").map { it.toInt() }
        if (x1 != x2 && y1 != y2) return@forEachLine
        println("($x1,$y1) -> ($x2,$y2)")
        for (x in min(x1, x2)..max(x1, x2)) {
            for (y in min(y1, y2)..max(y1, y2)) {
                if (board[x][y]++ == 1) res++
            }
        }
        //board.forEach { println(it.contentToString()) }
    }
    println(res)
}