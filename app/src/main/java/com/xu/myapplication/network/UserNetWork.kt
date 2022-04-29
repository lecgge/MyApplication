package com.xu.myapplication.network

import android.util.Log
import com.xu.myapplication.service.ServiceCreator
import com.xu.myapplication.service.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object UserNetWork {

    suspend fun login(username:String,password:String) = ServiceCreator.create<UserService>().login(username, password).await()

    suspend fun register(username: String,password: String,password1: String) = ServiceCreator.create<UserService>().register(username,password,password1).await()

    suspend fun <T> Call<T>.await() : T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    Log.d("TAG", "onResponse: ${response.body().toString()}")
                    if (body != null) {
                        continuation.resume(body)
                    } else {
                        continuation.resumeWithException(RuntimeException("response body is null"))
                    }
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    t.printStackTrace()
                }

            })
        }
    }

}