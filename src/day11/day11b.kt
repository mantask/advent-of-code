package day11

import java.io.File
import java.lang.Integer.max
import java.lang.Integer.min

fun main() {

    fun Int.incOrFlash(flash: () -> Unit): Int {
        if (this == 9) flash()
        return if (this in 0..8) this + 1 else -1
    }

    val grid = File("src/day11/full.in").readLines().map { line ->
        line.toCharArray().map { it.digitToInt() }.toTypedArray()
    }.toTypedArray()

    val stack = ArrayDeque<Pair<Int, Int>>()

    repeat(1_000_000) { step ->

        var flashes = 0

        repeat(10) { x ->
            repeat(10) { y ->
                grid[x][y] = grid[x][y].incOrFlash { stack.add(Pair(x, y)) }
            }
        }

        while (stack.isNotEmpty()) {
            val (xx, yy) = stack.removeFirst()
            for (x in max(xx - 1, 0)..min(xx + 1, 9)) {
                for (y in max(yy - 1, 0)..min(yy + 1, 9)) {
                    grid[x][y] = grid[x][y].incOrFlash { stack.add(Pair(x, y)) }
                }
            }
            flashes++
        }

        if (flashes == 100) {
            println(step + 1)
            return
        }

        repeat(10) { x ->
            repeat(10) { y ->
                if (grid[x][y] == -1) grid[x][y] = 0
            }
        }
    }
}
