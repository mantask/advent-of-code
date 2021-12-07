package day02

import java.io.File

fun main() {
    data class Position(
        val x: Long = 0,
        val y: Long = 0,
    ) {
        val result: Long get() = x * y
        fun forward(diff: Long) = Position(x + diff, y)
        fun down(diff: Long) = Position(x, y + diff)
        fun up(diff: Long) = Position(x, y - diff)
    }

    var pos = Position()
    File("src/day02.full.in").forEachLine {
        val (dir, diff) = it.split(" ")
        pos = when (dir) {
            "forward" -> pos.forward(diff.toLong())
            "down" -> pos.down(diff.toLong())
            "up" -> pos.up(diff.toLong())
            else -> pos
        }
    }
    println(pos.result)
}