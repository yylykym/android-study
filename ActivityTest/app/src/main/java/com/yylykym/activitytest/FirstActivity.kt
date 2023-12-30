package com.yylykym.activitytest

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.yylykym.activitytest.databinding.FirstLayoutBinding

class FirstActivity : AppCompatActivity() {

    private lateinit var firstLayoutBinding: FirstLayoutBinding;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firstLayoutBinding = FirstLayoutBinding.inflate(layoutInflater)
        // 设置布局
        setContentView(firstLayoutBinding.root)
        //注册回调
        val toOtherActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == Activity.RESULT_OK) {
                // 处理结果，如果需要
                val returnData: Intent? = it.data
                Log.d("FirstActivity", "returned data is ${returnData?.getStringExtra("data_return")}")
            }
        }
        firstLayoutBinding.button1.setOnClickListener {
            // 显示
//            val intent = Intent(this, SecondActivity::class.java)
            // 隐式
//            val intent = Intent("com.yylykym.activitytest.ACTION_START")
//            intent.addCategory("com.yylykym.activitytest.MY_CATEGORY")
//            val intent = Intent(Intent.ACTION_VIEW)
//            intent.data = Uri.parse("https://www.baidu.com")
//            startActivity(intent)
            // 传递数据
            val data = "Hello SecondActivity"
//            val intent = Intent(this, SecondActivity::class.java)
//            intent.putExtra("extra_data", data)
//            startActivity(intent)
            // 返回数据给上一个activity


            //跳转的时候使用
            val intent = Intent(this,SecondActivity::class.java)
            intent.putExtra("extra_data", data)
            toOtherActivity.launch(intent)


        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_item -> Toast.makeText(
                this, "You clicked Add",
                Toast.LENGTH_SHORT
            ).show()

            R.id.remove_item -> Toast.makeText(
                this, "You clicked Remove",
                Toast.LENGTH_SHORT
            ).show()
        }
        return true
    }
}