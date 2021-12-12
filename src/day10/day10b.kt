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
        '(' to 1L,
        '[' to 2L,
        '{' to 3L,
        '<' to 4L,
    )

    val scores = File("src/day10/full.in").readLines().map { line ->
        val stack = mutableListOf<Char>()
        line.forEach { c ->
            if (!pairs.containsKey(c)) {
                stack.add(c)
            } else if (pairs[c]!! != stack.removeLastOrNull()) {
                return@map -1L
            }
        }
        return@map stack.reversed().fold(0L) { acc, c -> acc * 5 + points[c]!! }
    }.filter { it != -1L }
    println(scores.sorted()[scores.size / 2])
}