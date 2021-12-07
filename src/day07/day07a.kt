package day07

import java.io.File
import kotlin.math.absoluteValue

fun main() {
    val pos = File("src/day07/full.in").readText()
        .split(",")
        .map { it.toLong() }

    fun cost(i: Int) =
        pos.sumOf { (it - i).absoluteValue * ((it - i).absoluteValue + 1) / 2 }

    val res = pos.indices.minOf { cost(it) }

    println(res)
}