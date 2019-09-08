package com.example.guessnumber

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_record.*

class RecordActivity : AppCompatActivity() {
    var tag="RecordActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)
        var count=intent.getIntExtra("COUNT",-1)
        id_count.setText(count.toString())
        btn_save.setOnClickListener{view ->
            val nickname=nickName.text.toString()
            getSharedPreferences("guess", Context.MODE_PRIVATE)
                .edit()
                .putInt("REC_COUNT",count)
                .putString("REC_NICKNAME",nickname)
                .apply()
            var rec_count=getSharedPreferences("guess", Context.MODE_PRIVATE)
                .getInt("REC_COUNT",-1)
            var rec_nickname=getSharedPreferences("guess", Context.MODE_PRIVATE)
                .getString("REC_NICKNAME",null)
            Log.d(tag,"rec_data: ${rec_count}/${rec_nickname}")
            var intent=Intent()
            intent.putExtra("NICKNAME",nickname)
            setResult(Activity.RESULT_OK,intent)
            finish()
            Log.d(tag,"this is branch firebase")
        }
    }
}
