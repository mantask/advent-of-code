package day12

import java.io.File

fun main() {

    val graph = mutableMapOf<String, MutableList<String>>()

    File("src/day12/full.in").forEachLine { line ->
        val (node1, node2) = line.split('-')
        graph.getOrPut(node1) { mutableListOf() }.add(node2)
        graph.getOrPut(node2) { mutableListOf() }.add(node1)
    }

    fun String.isSmall() =
        this[0].isLowerCase()

    fun traverse(visited: Set<String>, last: String, visitedTwice: String?): Int =
        graph[last]!!.sumOf { next ->
            when {
                next == "end" -> 1
                next == "start" -> 0
                next.isSmall() && visited.contains(next) && visitedTwice == null -> traverse(visited + next, next, next)
                next.isSmall() && visited.contains(next) -> 0
                else -> traverse(visited + next, next, visitedTwice)
            }
        }

    println(traverse(setOf("start"), "start", null))
}