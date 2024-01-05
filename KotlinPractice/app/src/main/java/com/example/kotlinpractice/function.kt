package com.example.kotlinpractice

import kotlin.math.max

fun largerNumber(num: Int, num2: Int): Int {
    return max(num, num2)
}

fun largerNumber2(num: Int, num2: Int) = max(num, num2)

fun main() {
    val largerNumber = largerNumber(2, 3)
    println(largerNumber)
    val largerNumber2 = largerNumber2(2, 3)
    println(largerNumber2)
}