package day09

import java.io.File

fun main() {
    val input = File("src/day09/full.in").readLines().map { line ->
        "9${line}9".map { it.digitToInt() }.toIntArray()
    }.toTypedArray()

    val h = arrayOf(
        IntArray(input[0].size) { 9 },
        *input,
        IntArray(input[0].size) { 9 },
    )
    val (xx, yy) = listOf(h.size, h[0].size)

    fun isLow(x: Int, y: Int): Boolean =
        h[x][y] < h[x - 1][y]
            && h[x][y] < h[x + 1][y]
            && h[x][y] < h[x][y - 1]
            && h[x][y] < h[x][y + 1]

    var res = 0
    for (x in 1 until xx) {
        for (y in 1 until yy) {
            if (isLow(x, y)) res += h[x][y] + 1
        }
    }
    println(res)
}