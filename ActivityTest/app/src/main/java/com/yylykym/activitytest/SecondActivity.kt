package com.yylykym.activitytest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.yylykym.activitytest.databinding.SecondLayoutBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var secondLayoutBinding: SecondLayoutBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        secondLayoutBinding  = SecondLayoutBinding.inflate(layoutInflater)
        setContentView(secondLayoutBinding.root)
        val extraData = intent.getStringExtra("extra_data")
        Log.d("SecondActivity", "extra data is $extraData")
        secondLayoutBinding.button2.setOnClickListener{
            val intent = Intent()
            intent.putExtra("data_return", "Hello FirstActivity")
            setResult(RESULT_OK, intent)
            finish()
        }

    }
}