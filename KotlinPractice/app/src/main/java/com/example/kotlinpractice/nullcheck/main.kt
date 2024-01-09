package com.example.kotlinpractice.nullcheck

fun getTextLength(text: String?): Int {
    if (text != null) {
        return text.length
    }
    return 0
}

fun main() {
    
}