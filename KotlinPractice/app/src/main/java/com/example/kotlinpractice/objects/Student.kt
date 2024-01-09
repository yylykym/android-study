package com.example.kotlinpractice.objects

class Student(val sno: String, val grade: Int, name: String, age: Int) :Person(name, age) {

    constructor(name:String, age: Int):this("",0,name, age) {

    }

    constructor() : this("", 0) {
    }

    init {
        println("sno is $sno")
        println("grade is $grade")
    }
}