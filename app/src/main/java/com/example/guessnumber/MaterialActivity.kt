package com.example.guessnumber

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.id_number

import kotlinx.android.synthetic.main.activity_material.*
import kotlinx.android.synthetic.main.content_material.*

class MaterialActivity : AppCompatActivity() {
    val secretNumber=SecretNumber()
    val tag="MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)
        Log.d(tag,"secretNum="+secretNumber.secret)
        id_count.setText(secretNumber.count.toString())
    }
    fun check(view: View){
        var message =getString(R.string.have_guess_number)
        var n:Int=0
        var diff=0
        id_count.setText(secretNumber.count.toString())
        if(id_number.text.isBlank()){
            message=getString(R.string.msg_no_Number)
        }else {
            n=id_number.text.toString().toInt()
            diff=secretNumber.validate(n)
            Log.d(tag,"number: " + n)
            if(diff<0) {
                message=getString(R.string.msg_bigger)
            }else if(diff>0){
                message=getString(R.string.msg_smaller)
            }else {
                if (secretNumber.count < 3) {
                    message = getString(R.string.msg_awesome) + secretNumber.secret
                }
            }
        }
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.title_msg))
            .setMessage(message)
            .setPositiveButton(R.string.btn_go,null)
            .show()
        fab.setOnClickListener { view ->
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.do_u_want_to_Restart))
                .setPositiveButton(R.string.btn_go,{dialog, which ->
                    secretNumber.reset()
                    id_number.setText("")
                    id_count.setText(secretNumber.count.toString())
                })
                .setNeutralButton(getString(R.string.cancel),null)
                .show()
        }
    }

}
