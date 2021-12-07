package day01

import java.io.File

fun main() {
    var prev = Int.MAX_VALUE
    var curr = 0
    var pos = 0
    val nums = IntArray(3)
    var line = 0

    var res = 0
    File("src/day01.in").forEachLine {
        curr -= nums[pos]
        nums[pos] = it.toInt()
        curr += nums[pos]
        if (line > 2 && curr > prev) res++

        pos = pos.next()
        prev = curr
        line++
    }
    println(res) // 1374
}

fun Int.next() =
    (this + 1) % 3