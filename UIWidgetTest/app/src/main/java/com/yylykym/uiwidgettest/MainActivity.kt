package com.yylykym.uiwidgettest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.yylykym.uiwidgettest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mainLayoutBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainLayoutBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainLayoutBinding.root)
        mainLayoutBinding.button.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view.id) {
            mainLayoutBinding.button.id->{
                val inputText = mainLayoutBinding.editText.text.toString()
                Toast.makeText(this, inputText, Toast.LENGTH_SHORT).show()
                mainLayoutBinding.imageView.setImageResource(R.drawable.image2)
                if (mainLayoutBinding.progressBar.visibility == View.VISIBLE) {
                    mainLayoutBinding.progressBar.visibility = View.GONE
                } else {
                    mainLayoutBinding.progressBar.visibility = View.VISIBLE
                }
                mainLayoutBinding.progressBar.progress = mainLayoutBinding.progressBar.progress + 10
                AlertDialog.Builder(this).apply {
                    setTitle("This is Dialog")
                    setMessage("Something important.")
                    setCancelable(false)
                    setPositiveButton("OK") { dialog, which ->
                    }
                    setNegativeButton("Cancel") { dialog, which ->
                    }
                    show()
                }
            }
        }
    }

}