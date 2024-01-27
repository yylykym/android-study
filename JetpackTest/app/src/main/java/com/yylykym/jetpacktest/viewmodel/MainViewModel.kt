package com.yylykym.jetpacktest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.yylykym.jetpacktest.User
import com.yylykym.jetpacktest.room.Repository

class MainViewModel(countReserved: Int) : ViewModel() {

    val counter: LiveData<Int>
        get() = _counter

    private val _counter = MutableLiveData<Int>()

    private val userLiveData = MutableLiveData<User>()

    //    val userName: LiveData<String> = Transformations.map(userLiveData) { user ->
//        "${user.firstName} ${user.lastName}"
//    }
    private val userIdLiveData = MutableLiveData<String>()
    val user: LiveData<User> = Transformations.switchMap(userIdLiveData) { userId ->
        Repository.getUser(userId)
    }

    init {
        _counter.value = countReserved
    }

    fun plusOne() {
        val count = _counter.value ?: 0
        _counter.value = count + 1
    }

    fun clear() {
        _counter.value = 0
    }

//    fun getUser(userId: String): LiveData<User> {
//        return Repository.getUser(userId)
//    }

    fun getUser(userId: String) {
        userIdLiveData.value = userId
    }

}