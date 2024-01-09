package com.example.kotlinpractice

fun largerNumber3(num1: Int, num2: Int): Int {
    var value = 0
    if (num1 > num2) {
        value = num1
    } else {
        value = num2
    }
    return value
}

fun largerNumber4(num1: Int, num2: Int): Int {
    var value = 0
    value = if (num1 > num2) {
        num1
    } else {
        num2
    }
    return value
}

fun largerNumber5(num1: Int, num2: Int): Int = if (num1 > num2) {
    num1
} else {
    num2
}

fun largerNumber6(num1: Int, num2: Int): Int = if (num1 > num2) num1 else num2


fun main() {
    val largerNumber3 = largerNumber3(2, 3)
    println(largerNumber3)
}