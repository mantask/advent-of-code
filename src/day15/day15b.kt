package day15

import java.io.File
import kotlin.math.min

fun main() {
    fun Int.shift() =
        this % 9 + 1

    val levels1x1 = File("src/day15/full.in").readLines().map { line ->
        line.toCharArray().map { it - '0' }.toTypedArray()
    }.toTypedArray()

    val y1 = levels1x1.size
    val x1 = levels1x1[0].size
    val y5 = y1 * 5
    val x5 = x1 * 5

    val levels5x5 = Array(y5) { Array(x5) { 0 } }
    for (y in 0 until y5) {
        for (x in 0 until x5) {
            levels5x5[y][x] = when {
                x < x1 && y < y1 -> levels1x1[y][x]
                x1 <= x -> levels5x5[y][x - x1].shift()
                else -> levels5x5[y - y1][x].shift()
            }
        }
    }

//    for (y in 0 until y5) {
//        for (x in 0 until x5) {
//            print(levels5x5[y][x])
//        }
//        println()
//    }

    val risk = Array(x5 + 1) { Integer.MAX_VALUE }
    risk[x5] = 0 // initially start from bottom right

    for (y in y5 - 1 downTo 0) {
        for (x in x5 - 1 downTo 0) {
            risk[x] = levels5x5[y][x] + min(risk[x + 1], risk[x])
        }
        risk[x5] = Integer.MAX_VALUE // never allow go right past border
//        risk.forEach { print("$it, ".padStart(6)) }
//        println()
    }

    println(risk[0] - levels5x5[0][0]) // 2926 (too high); expected: 2916
}