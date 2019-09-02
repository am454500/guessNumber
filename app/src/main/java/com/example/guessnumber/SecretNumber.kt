package com.example.guessnumber

import android.util.Log
import java.util.*

class SecretNumber {
    var numberList = mutableListOf<Int>()
    var count = 1
    var Acount=0
    var Bcount=0
    var tag="SecretNumber"
    var secretAry= IntArray(4)
    var numberAry= CharArray(4)
    var numberAryStr= arrayOfNulls<String>(4)
    var checkedAry= booleanArrayOf(false,false,false,false)
    fun validate(number:String):String{
        numberAry= number.toCharArray()
        Log.d(tag,"add: "+numberAryStr[0])
        checkedAry=booleanArrayOf(false,false,false,false)
        rightPosAndNum(numberAry)
        rightNum(numberAry)
//        haveSameNumber(numberAry)
        count++
        return Acount.toString()+"A"+Bcount.toString()+"B"
    }
    fun creatSecretNumber(){
        numberList.clear()
        for(i in 0..9){
            numberList.add(i)
//            Log.d(tag,"list:"+numberList.indexOf(i).toString())
        }
        for(i in 0..3){
//            Log.d(tag,"listSize:"+numberList.size.toString())
            secretAry[i]=numberList.get(Random().nextInt(numberList.size))
            numberList.remove(secretAry[i])
//            Log.d(tag,secretAry[i].toString())
        }
    }
    fun rightPosAndNum(numberAry:CharArray):Int{
        Acount=0
        for (i in 0..3){
            if( numberAry[i].toString().toInt()==secretAry[i]){
                Acount++
                checkedAry[i]=true
            }
        }
        return  Acount
    }
    fun rightNum(numberAry:CharArray):Int{
        Bcount=0
        for (i in 0..3){
            for(j in 0..3){
                if(!checkedAry[i]&&i!=j&&numberAry[i].toString().toInt()==secretAry[j]){
                    Bcount++
                    checkedAry[i]=true
                }
            }
        }
        return Bcount
    }
    fun haveSameNumber(numberAry:CharArray):Boolean{
        var haveSameNumber=false
        for(i in 0..3){
            numberAryStr[i]=numberAry[i].toString()
        }
        for(i in 0..3){
            for(j in 0..3){
                if(i!=j&&numberAryStr[i].equals(numberAryStr[j])){
                    Log.d(tag,"i: "+numberAryStr[i]+" j:"+numberAryStr[j])
                    haveSameNumber=true
                }
            }
        }
        return haveSameNumber
    }
    fun reset(){
        creatSecretNumber()
        count = 1
        Acount=0
        Bcount=0
        checkedAry=booleanArrayOf(false,false,false,false)
    }
}
