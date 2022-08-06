package day15

import java.io.File
import kotlin.math.min

fun main() {
    val levels = File("src/day15/full.in").readLines().map { line ->
        line.toCharArray().map { it - '0' }.toTypedArray()
    }.toTypedArray()

    val yy = levels.size
    val xx = levels[0].size

    val risk = Array(xx + 1) { Integer.MAX_VALUE }
    risk[xx] = 0 // initially start from bottom right

    for (y in yy - 1 downTo 0) {
        for (x in xx - 1 downTo 0) {
            risk[x] = levels[y][x] + min(risk[x + 1], risk[x])
        }
        risk[xx] = Integer.MAX_VALUE // never allow go right past border
    }

    println(risk[0] - levels[0][0]) // 581
}