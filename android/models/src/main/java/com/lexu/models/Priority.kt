package com.lexu.models

enum class Priority private constructor(val value : Int){
    LOW(4), MEDIUM(3), HIGH(2), CRITICAL(1), BLOCKER(0)
}