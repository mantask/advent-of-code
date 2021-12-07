package day04

import java.io.File

fun main() {
    data class Position(
        val b: Int,
        val x: Int,
        val y: Int,
    )

    fun String.forEachInt(action: (index: Int, num: Int) -> Unit) {
        Regex("[0-9]+").findAll(this)
            .map { it.value.toInt() }
            .forEachIndexed(action)
    }

    val lines = File("src/day04/full.in").readText().lines()
    val positions = mutableMapOf<Int, MutableList<Position>>()
    val boards = mutableMapOf<Int, MutableSet<Int>>()
    for (offset in 2..lines.size step 6) {
        val b = (5 + offset - 1) / 6
        boards[b] = mutableSetOf()
        for (y in 0..4) {
            lines[offset + y].forEachInt { x, num ->
                boards[b]?.add(num)
                positions.getOrPut(num, ::mutableListOf).add(Position(b, x, y))
            }
        }
    }

    val boardCount = (lines.size - 1) / 6 + 1
    val bx = Array(boardCount) { IntArray(5) { 0 } }
    val by = Array(boardCount) { IntArray(5) { 0 } }

    lines[0].forEachInt { _, num ->
        // println("num=$num")
        positions[num]
            ?.filter { boards.containsKey(it.b) }
            ?.forEach { pos ->
                boards[pos.b]!!.remove(num)
                if (++bx[pos.b][pos.x] == 5 || ++by[pos.b][pos.y] == 5) {
                    val sum = boards[pos.b]!!.sum()
                    println("num=${num}, sum=${sum}, res=${sum * num}")
                    boards.remove(pos.b)
                }
            }
    }
}