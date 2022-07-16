package day14

import java.io.File

fun main() {

    var poly = ""
    val pairs = mutableMapOf<String, Char>()

    var lineNo = 0
    File("src/day14/full.in").forEachLine { line ->
        when {
            lineNo == 0 -> poly = "$line "
            lineNo >= 2 -> {
                val (pair, insert) = line.split(" -> ")
                pairs[pair] = insert.first()
            }
        }
        lineNo++
    }

    fun insert(s: String) = sequence {
        for (i in s.indices) {
            yield(s[i])
            if (i != s.lastIndex) pairs["${s[i]}${s[i + 1]}"]?.let { yield(it) }
        }
    }

    repeat(10) {
        poly = insert(poly).joinToString("")
    }

    val counts = poly.trimEnd().toCharArray().groupBy { it }.map { it.value.size }.sorted()
    println(counts.last() - counts.first())
}