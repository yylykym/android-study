package com.yylykym.fragmenttest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.yylykym.fragmenttest.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), LeftFragment.OnButtonClickListener {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        val left = supportFragmentManager.findFragmentById(R.id.leftFrag) as LeftFragment
        left.setOnButtonClickListener(this)
    }

    override fun onButtonClick() {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
//        transaction.replace(mainBinding.rightLayout.id, RightFragment())
        transaction.commit()
    }
}