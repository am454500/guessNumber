package com.example.guessnumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val secretNumber=SecretNumber()
    val tag="MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(tag,"secretNum="+secretNumber.secret)
    }
    fun check(view:View){
        var message =getString(R.string.have_guess_number)
        var n:Int=0
        var diff=0
        if(id_number.text.isBlank()){
            message=getString(R.string.msg_no_Number)
        }else{
            n=id_number.text.toString().toInt()
            diff=secretNumber.validate(n)
            Log.d(tag,"number: " + n)
            if(diff<0) {
                message=getString(R.string.msg_bigger)
            }else if(diff>0){
                message=getString(R.string.msg_smaller)
            }
        }
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.title_msg))
            .setMessage(message)
            .setPositiveButton("OK",null)
            .show()
    }

}
