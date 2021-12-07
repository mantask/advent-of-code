package day06

import java.io.File

fun main() {

    fun qty(n: Long, d: Long): Long =
        if (n > d) 1
        else qty(8, d - n - 1) + qty(6, d - n - 1)

    val fish = File("src/day06/full.in").readText()
        .split(",")
        .map { it.toLong() }
        .groupingBy { it }
        .eachCount()

    val sum = fish.keys.sumOf { qty(it, 256 - 1) * fish[it]!! }

    println(sum)
}