package com.example.guessnumber

import android.app.Activity
import android.content.Context
import android.content.Intent
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
    private val REQUEST_RECORD: Int=100
    val secretNumber=SecretNumber()
    val tag="MaterialActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)
        secretNumber.creatSecretNumber()
        id_count.setText(secretNumber.count.toString())

    }

    fun check(view: View){
        var message =getString(R.string.have_guess_number)
        id_count.setText(secretNumber.count.toString())
        if(id_number.text.isBlank()||id_number.length()!=4){
            message=getString(R.string.msg_no_Number)
        }else {
            secretNumber.validate(id_number.text.toString())
            if(!secretNumber.haveSameNumber(id_number.text.toString().toCharArray())) {
                if (secretNumber.msg_nAnB.equals("4A0B")) {
                    message += id_number.text.toString()
                } else {
                    message = secretNumber.msg_nAnB
                    secretNumber.count++
                }
            }else{
                message = getString(R.string.msg_cant_same)
            }
        }
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.title_msg))
            .setMessage(message)
            .setPositiveButton(R.string.btn_go) { dialogInterface, i ->
                if(secretNumber.msg_nAnB.equals( "4A0B")){
                    var intent= Intent(this,RecordActivity::class.java)
                    intent.putExtra("COUNT",secretNumber.count)
                    startActivityForResult(intent,REQUEST_RECORD)
                }
                id_count.setText(secretNumber.count.toString())
            }
            .show()
        fab.setOnClickListener { view ->
            restart()
        }
    }
    private fun restart() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.do_u_want_to_Restart))
            .setPositiveButton(R.string.btn_go) { dialog, which ->
                secretNumber.reset()
                id_number.setText("")
                id_count.setText(secretNumber.count.toString())
                secretNumber.msg_nAnB=""
            }
            .setNeutralButton(getString(R.string.cancel), null)
            .show()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            REQUEST_RECORD->{
                if(resultCode== Activity.RESULT_OK){
                    restart()
                    val nickname=data?.getStringExtra("NICKNAME")
                    Log.d(tag,nickname)
                }
            }
        }
    }
}
