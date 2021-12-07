package day03

import java.io.File

fun main() {
    val MAX_LENGTH = 20

    var size = MAX_LENGTH
    val ones = IntArray(MAX_LENGTH)
    val zeros = IntArray(MAX_LENGTH)
    File("src/day03/full.in").forEachLine {
        for (i in it.indices) {
            when (it[i]) {
                '0' -> zeros[i]++
                '1' -> ones[i]++
            }
        }
        size = it.length
    }

    var g = 0
    var e = 0
    for (i in 0 until size) {
        print(if (ones[i] > zeros[i]) 1 else 0)
        g = (g shl 1) + if (ones[i] > zeros[i]) 1 else 0
        e = (e shl 1) + if (ones[i] < zeros[i]) 1 else 0
    }
    println()
    println(g * e)
}