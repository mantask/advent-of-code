package day08

import java.io.File

fun main() {
    var res = 0
    File("src/day08/full.in").forEachLine { line ->
        val (_, part2) = line.split("|")
        res += part2.split(" ").count { it.length in setOf(2, 3, 4, 7) }
    }
    println(res)
}