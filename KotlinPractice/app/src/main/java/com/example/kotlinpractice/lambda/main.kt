package com.example.kotlinpractice.lambda

fun main() {
    val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape", "Watermelon")
    val maxLengthFruit = list.maxBy { fruit -> fruit.length }

    val newList = list.map { it.uppercase() }
    for (fruit in newList) {
        println(fruit)
    }

    val filterList = list.filter { it.length <= 5 }
        .map { it.uppercase() }
    for (fruit in filterList) {
        println(fruit)
    }

    val anyResult = list.any { it.length <= 5 }
    val allResult = list.all { it.length <= 5 }
    println("anyResult is $anyResult, allResult is $allResult")

}