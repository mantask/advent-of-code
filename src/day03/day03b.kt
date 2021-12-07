package day03

import java.io.File

fun main() {
    operator fun Int.get(i: Int): Boolean {
        val mask = 1 shl i
        return this and mask == mask
    }

    var count = 0
    var len = 20
    val nums = IntArray(1_000)
    File("src/day03/full.in").forEachLine {
        nums[count++] = it.toInt(2)
        len = it.length
    }

    var oxy = nums.take(count).toList()
    for (bit in len - 1 downTo 0) {
        val one = oxy.count { it[bit] } * 2 >= oxy.size
        println("${oxy.count { it[bit] }} -- $one")
        oxy = oxy.filter { one == it[bit] }
        if (oxy.size == 1) break
    }
    println(oxy)

    var eps = nums.take(count).toList()
    for (bit in len - 1 downTo 0) {
        val one = eps.count { it[bit] } * 2 < eps.size
        println("${eps.count { it[bit] }} -- $one")
        eps = eps.filter { one == it[bit] }
        if (eps.size == 1) break
    }
    println(eps)

    println(oxy[0] * eps[0])
}