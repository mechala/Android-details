package com.example.newrecycler

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class RandomUserViewModel (application: Application): AndroidViewModel(application) {

    private var randomUserDao:RandomUserDao

    init {
        randomUserDao= RandomUserDao.getInstance(this.getApplication())
    }

    fun addUser(){
        randomUserDao.addUser()
    }
    fun getUser():MutableLiveData<List<Person>>{
        return randomUserDao.getUsers()

    }
}