package com.yylykym.listviewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.yylykym.listviewtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    private val data = listOf(
        "Apple", "Banana", "Orange", "Watermelon",
        "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango",
        "Apple", "Banana", "Orange", "Watermelon", "Pear", "Grape",
        "Pineapple", "Strawberry", "Cherry", "Mango"
    )

    private val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        initFruits() // 初始化水果数据
        val adapter = FruitAdapter(this, R.layout.fruit_item, fruitList)
        mainBinding.listView.adapter = adapter
        mainBinding.listView.setOnItemClickListener { _, _, position, _ ->
            val fruit = fruitList[position]
            Toast.makeText(this, fruit.name, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initFruits() {
        repeat(2) {
            fruitList.add(Fruit("Apple", R.drawable.apple))
            fruitList.add(Fruit("Banana", R.drawable.bananas))
            fruitList.add(Fruit("Orange", R.drawable.orange))
            fruitList.add(Fruit("Watermelon", R.drawable.watermelon))
            fruitList.add(Fruit("Dragon Fruit", R.drawable.dragon_fruit))
            fruitList.add(Fruit("Grape", R.drawable.grapes))
            fruitList.add(Fruit("Pineapple", R.drawable.pineapple))
            fruitList.add(Fruit("Strawberry", R.drawable.strawberry))
            fruitList.add(Fruit("Lemon", R.drawable.lemon))
            fruitList.add(Fruit("Mango", R.drawable.mango))
        }
    }

}