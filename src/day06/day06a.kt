package day06

import java.io.File

fun main() {
    val fish = File("src/day06/full.in").readText()
        .split(",")
        .map { it.toInt() }
        .toMutableList()
    for (day in 1..80) {
        var new = 0
        fish.indices.forEach { i ->
            if (--fish[i] == -1) {
                new++
                fish[i] = 6
            }
        }
        repeat(new) { fish.add(8) }
//        println(fish)
    }
    println(fish.size)
}