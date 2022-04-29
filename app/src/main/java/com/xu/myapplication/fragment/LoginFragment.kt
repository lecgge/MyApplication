package com.xu.myapplication.fragment

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.xu.myapplication.R
import com.xu.myapplication.databinding.FragmentLoginBinding
import com.xu.myapplication.network.UserNetWork
import com.xu.myapplication.viewmodel.LoginRegisterViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {

    lateinit var viewModel: LoginRegisterViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(inflater,R.layout.fragment_login,container,false)
        viewModel = ViewModelProvider(this).get(LoginRegisterViewModel::class.java)

        binding.loginviewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.loginToolbar.toolbar.title = "登陆"
        //密码编辑框显示*还是密码
        binding.passwordIshide.setOnClickListener { view ->
            if (binding.passwordIshide.isChecked) {
                //显示密码
                binding.editPassword.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
            } else {
                //显示*
                binding.editPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }

        binding.loginSub.setOnClickListener {
            viewModel.login(it)

        }

        //跳转注册页面
        binding.loginGoregister.setOnClickListener {  view ->
            view.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        return binding.root
    }


}