package com.yylykym.recyclerviewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.yylykym.recyclerviewtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    private val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        initFruits() // 初始化水果数据
        val layoutManager = StaggeredGridLayoutManager(
            3,
            StaggeredGridLayoutManager.VERTICAL
        )
        mainBinding.recyclerView.layoutManager = layoutManager
        val adapter = FruitAdapter(fruitList)
        mainBinding.recyclerView.adapter = adapter
    }

    private fun initFruits() {
        repeat(2) {
            fruitList.add(Fruit(getRandomLengthString("Apple"), R.drawable.apple))
            fruitList.add(Fruit(getRandomLengthString("Banana"), R.drawable.bananas))
            fruitList.add(Fruit(getRandomLengthString("Orange"), R.drawable.orange))
            fruitList.add(Fruit(getRandomLengthString("Watermelon"), R.drawable.watermelon))
            fruitList.add(Fruit(getRandomLengthString("Dragon Fruit"), R.drawable.dragon_fruit))
            fruitList.add(Fruit(getRandomLengthString("Grape"), R.drawable.grapes))
            fruitList.add(Fruit(getRandomLengthString("Pineapple"), R.drawable.pineapple))
            fruitList.add(Fruit(getRandomLengthString("Strawberry"), R.drawable.strawberry))
            fruitList.add(Fruit(getRandomLengthString("Lemon"), R.drawable.lemon))
            fruitList.add(Fruit(getRandomLengthString("Mango"), R.drawable.mango))
        }
    }

    private fun getRandomLengthString(str: String): String {
        val n =  (1..20).random()
        val builder = StringBuilder()
        repeat(n) {
            builder.append(str)
        }
        return builder.toString()
    }

}