package day14

import java.io.File

typealias Freq = Map<Char, Long>

const val ITERATIONS = 40

fun main() {

    val pairs = mutableMapOf<String, Char>()
    var poly = ""

    var lineNo = 0
    File("src/day14/full.in").forEachLine { line ->
        when {
            lineNo == 0 -> poly = "$line*"
            lineNo >= 2 -> {
                val (pair, insert) = line.split(" -> ")
                pairs[pair] = insert.first()
            }
        }
        lineNo++
    }

    operator fun Freq.plus(that: Freq): Freq =
        LinkedHashMap(this).apply {
            that.entries.forEach { (c, count) -> put(c, count + getOrDefault(c, 0)) }
        }

    val cache = mutableMapOf<String, Freq>()

    fun freq(i: Int, c1: Char, c2: Char): Freq {
        val key = "$i$c1$c2"
        if (!cache.containsKey(key)) {
            val c = pairs["$c1$c2"]
            cache[key] =
                if (i == 0 || c == null) mapOf(c1 to 1)
                else freq(i - 1, c1, c) + freq(i - 1, c, c2)
        }
        return cache[key]!!
    }

    val res = (0 until poly.lastIndex)
        .map { freq(ITERATIONS, poly[it], poly[it + 1]) }
        .reduce { acc, freq -> acc + freq }
        .map { it.value }
        .sortedDescending()

    println(res.first() - res.last())
}

