package day01

import java.io.File

fun main() {
    var prev = Int.MAX_VALUE
    var res = 0
    File("src/day01.in").forEachLine {
        val num = it.toInt()
        if (num > prev) res++
        prev = num
    }
    println(res) // 1374
}