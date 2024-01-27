package com.yylykym.jetpacktest

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.yylykym.jetpacktest.databinding.ActivityMainBinding
import com.yylykym.jetpacktest.room.AppDatabase
import com.yylykym.jetpacktest.viewmodel.MainViewModel
import com.yylykym.jetpacktest.lifecycle.MainViewModelFactory
import com.yylykym.jetpacktest.lifecycle.MyObserver
import workmanager.SimpleWorker
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private lateinit var mainBinding: ActivityMainBinding

    private lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(mainBinding.root)
        lifecycle.addObserver(MyObserver())
        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt("count_reserved", 0)

        viewModel =
            ViewModelProvider(this, MainViewModelFactory(countReserved))[MainViewModel::class.java]
        mainBinding.plusOneBtn.setOnClickListener {
            viewModel.plusOne()
        }
        mainBinding.clearBtn.setOnClickListener {
            viewModel.clear()
        }

        viewModel.counter.observe(this) { count ->
            mainBinding.infoText.text = count.toString()
        }

        mainBinding.getUserBtn.setOnClickListener {
            val userId = (0..10000).random().toString()
            viewModel.getUser(userId)
        }

        viewModel.user.observe(this) { user ->
            mainBinding.infoText.text = user.firstName
        }

        val userDao = AppDatabase.getDatabase(this).userDao()
        val user1 = User("Tom", "Brady", 40)
        val user2 = User("Tom", "Hanks", 63)
        mainBinding.addDataBtn.setOnClickListener {
            thread {
                userDao.apply {
                    insertUser(user1)
                    insertUser(user2)
                }
            }
        }
        mainBinding.updateDataBtn.setOnClickListener {
            thread {
                user1.age = 42
                userDao.updateUser(user1)
            }
        }
        mainBinding.deleteDataBtn.setOnClickListener {
            thread {
                userDao.deleteUserByLastName("Hanks")
            }
        }
        mainBinding.queryDataBtn.setOnClickListener {
            thread {
                for (user in userDao.loadAllUsers()) {
                    Log.d("MainActivity", user.toString())
                }
            }
        }

        mainBinding.doWorkBtn.setOnClickListener {
            val request = OneTimeWorkRequest.Builder(SimpleWorker::class.java).build()
            WorkManager.getInstance(this).enqueue(request)
        }

    }


    override fun onPause() {
        super.onPause()
        sp.edit {
            viewModel.counter.value?.let { putInt("count_reserved", it) }
        }
    }

}