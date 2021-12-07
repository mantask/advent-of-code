package day04

import java.io.File
import java.util.regex.Pattern

fun main() {
    data class Position(
        val b: Int,
        val x: Int,
        val y: Int,
    )

    val lines = File("src/day04/full.in").readText().lines()
    val positions = mutableMapOf<Int, MutableList<Position>>()
    val boards = mutableMapOf<Int, MutableSet<Int>>()
    for (offset in 2..lines.size step 6) {
        val b = (5 + offset - 1) / 6
        boards[b] = mutableSetOf()
        for (y in 0..4) {
            lines[offset + y]
                .split(Pattern.compile("\\s+"))
                .filter { it.isNotBlank() }
                .map { it.toInt() }
                .forEachIndexed { x, num ->
                    boards[b]?.add(num)
                    positions.getOrPut(num, ::mutableListOf).add(Position(b, x, y))
                }
        }
    }

    val boardCount = (lines.size - 1) / 6 + 1
    val bx = Array(boardCount) { IntArray(5) { 0 } }
    val by = Array(boardCount) { IntArray(5) { 0 } }

    lines[0]
        .split(",")
        .map { it.toInt() }
        .forEach { num ->
            println("num=$num")
            positions[num]?.forEach { pos ->
                boards[pos.b]?.remove(num)
                if (++bx[pos.b][pos.x] == 5 || ++by[pos.b][pos.y] == 5) {
                    val sum = boards[pos.b]?.sum() ?: 0
                    println("sum=${sum}, num=${num}, res=${sum * num}")
                    return
                }
            }
        }
}