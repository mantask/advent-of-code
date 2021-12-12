package day09

import java.io.File

fun main() {
    val input = File("src/day09/full.in").readLines().map { line ->
        "9${line}9".map { it.digitToInt() }.toIntArray()
    }.toTypedArray()

    val (xx, yy) = listOf(input.size + 2, input[0].size)
    val h = arrayOf(
        IntArray(yy) { 9 },
        *input,
        IntArray(yy) { 9 },
    )

    fun count(x: Int, y: Int): Int {
        h[x][y] = 9
        var res = 1
        if (h[x + 1][y] < 9) res += count(x + 1, y)
        if (h[x - 1][y] < 9) res += count(x - 1, y)
        if (h[x][y - 1] < 9) res += count(x, y - 1)
        if (h[x][y + 1] < 9) res += count(x, y + 1)
        return res
    }

    val sizes = mutableListOf<Int>()
    for (x in 1 until xx) {
        for (y in 1 until yy) {
            if (h[x][y] < 9) sizes.add(count(x, y))
        }
    }
    println(sizes.sortedDescending().take(3).reduce(Int::times))
}