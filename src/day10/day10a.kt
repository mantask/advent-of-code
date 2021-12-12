package day10

import java.io.File

fun main() {

    val pairs = mapOf(
        ')' to '(',
        ']' to '[',
        '}' to '{',
        '>' to '<',
    )

    val points = mapOf(
        ')' to 3L,
        ']' to 57L,
        '}' to 1197L,
        '>' to 25137L,
    )

    val res = File("src/day10/full.in").readLines().map { line ->
        val stack = mutableListOf<Char>()
        line.forEach { c ->
            if (!pairs.containsKey(c)) {
                stack.add(c)
            } else if (pairs[c]!! != stack.removeLastOrNull()) {
                return@map points[c]!!
            }
        }
        return@map 0
    }.sum()
    println(res)
}