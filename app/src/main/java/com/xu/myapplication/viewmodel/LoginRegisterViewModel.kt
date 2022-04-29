package com.xu.myapplication.viewmodel

import android.database.Observable
import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.*
import androidx.navigation.findNavController
import com.xu.myapplication.R
import com.xu.myapplication.entity.UserInfo
import com.xu.myapplication.network.ApiResponse
import com.xu.myapplication.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.security.KeyStore

class LoginRegisterViewModel : ViewModel() {

    var userInfo = MutableLiveData<ApiResponse<UserInfo>>()

    private val  uiScope = CoroutineScope(Dispatchers.IO)

    fun login(view : View) {
        viewModelScope.launch {
            userInfo.value = async {
                UserRepository.login(username.value.toString(), password.value.toString())
            }.await()
            if (userInfo.value!!.errorCode == 0) {
                view.findNavController().navigate(R.id.action_loginFragment_to_bottomNavFragment)
            }
        }
    }

    fun register(view : View){
        viewModelScope.launch {
            userInfo.value = async {
                UserRepository.register(
                    username.value.toString(),
                    password.value.toString(),
                    password2.value.toString()
                )
            }.await()
            if (userInfo.value?.errorCode == 0) {
                view?.findNavController()?.navigate(R.id.action_registerFragment_to_bottomNavFragment)
            }
        }
    }

    var username = MutableLiveData<String>()

    var password = MutableLiveData<String>()

    var password2 = MutableLiveData<String>()

    var isshowpassword = MutableLiveData<String>()

    var isshowpassword2 = MutableLiveData<String>()

    var clearVisible = object : ObservableInt(ObservableField<String>(username.value)) {
        override fun get(): Int {
            return if (username.value?.isEmpty() == true) {
                View.GONE
            } else {
                View.VISIBLE
            }
        }
    }

    var passwordVisible = object : ObservableInt(ObservableField<String>(password.value)) {
        override fun get(): Int {
            return if (password.value?.isEmpty() == true) {
                View.GONE
            } else {
                View.VISIBLE
            }
        }
    }

    var password2Visible = object : ObservableInt(ObservableField<String>(password2.value)) {
        override fun get(): Int {
            return if (password2.value?.isEmpty() == true) {
                View.GONE
            } else {
                View.VISIBLE
            }
        }
    }

    //清除账号
    fun usernameClear() {
        username.value = ""
    }

    //清除密码
    fun passwordClear(){
        password.value = ""
    }

    fun passwordClear2(){
        password2.value = ""
    }



}