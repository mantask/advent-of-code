package day08

import java.io.File

fun main() {

    fun String.toBin() =
        "abcdefg".toCharArray().fold(0) { num, c ->
            (num shl 1) + if (this.contains(c)) 1 else 0
        }

    fun String.toWords() =
        this.split(" ")
            .filter { it.isNotBlank() }
            .map { it.toCharArray().sorted().joinToString("") }

    fun <K, V> Map<K, V>.key(value: V) =
        this.keys.first { this[it] == value }

    var res = 0
    File("src/day08/full.in").forEachLine { line ->
        val (part1, part2) = line.split("|")
        val patterns = part1.toWords()
        val digits = mutableMapOf(
            patterns.first { it.length == 2 } to 1,
            patterns.first { it.length == 3 } to 7,
            patterns.first { it.length == 4 } to 4,
            patterns.first { it.length == 7 } to 8,
        )

        val one = digits.key(1).toBin()
        val four = digits.key(4).toBin()
        patterns.filter { it.length == 5 }.forEach {
            digits[it] = when {
                it.toBin() and one == one -> 3
                it.toBin() and (four xor one) == (four xor one) -> 5
                else -> 2
            }
        }

        val three = digits.key(4).toBin()
        patterns.filter { it.length == 6 }.forEach {
            digits[it] = when {
                it.toBin() and three == three -> 9
                it.toBin() and one == one -> 0
                else -> 6
            }
        }

        res += part2.toWords().joinToString("") { digits[it].toString() }.toInt()
    }
    println(res)
}