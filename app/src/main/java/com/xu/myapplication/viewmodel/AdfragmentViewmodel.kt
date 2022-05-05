package com.xu.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AdfragmentViewmodel : ViewModel() {

    var time = MutableLiveData<String>()

    init {
        time.value = "5"
    }
}