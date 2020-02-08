/*
 * Copyright (c) Bogdan Andrei Alexandru - 2017.
 */

package com.lexu.models

enum class Type private constructor(val value : Int){
    ISSUE(0),
    TASK(1),
    SUBTASK(2),
    QUESTION(3),
    STORY(4)
}