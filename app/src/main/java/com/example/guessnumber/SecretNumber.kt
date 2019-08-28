package com.example.guessnumber

import java.util.*

class SecretNumber {
    var secret = Random().nextInt(10)+1
    var count = 1

    fun validate(number:Int):Int{
        count++
        return number-secret
    }
    fun reset(){
        secret = Random().nextInt(10)+1
        count = 1
    }
}
