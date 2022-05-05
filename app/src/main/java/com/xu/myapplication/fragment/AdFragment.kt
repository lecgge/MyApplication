package com.xu.myapplication.fragment

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.xu.myapplication.R
import com.xu.myapplication.databinding.FragmentAdBinding
import com.xu.myapplication.viewmodel.AdfragmentViewmodel
import kotlin.concurrent.timerTask


class AdFragment : Fragment() {
    var count = 6000L
    var max = 5

    lateinit var myview : View
    lateinit var binding: FragmentAdBinding
    lateinit var timeCount: TimeCount


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentAdBinding>(inflater,R.layout.fragment_ad,container,false)
        var viewmodel = ViewModelProvider(this).get(AdfragmentViewmodel::class.java)

        binding.adviewModel = viewmodel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.progressbar.max = max
        binding.progressbar.progress = max

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        timeCount = TimeCount(view,count,1000)
        timeCount.start()
    }

    inner class TimeCount(var view: View?, var millisInFuture : Long, var countDownInterval : Long) : CountDownTimer(millisInFuture, countDownInterval){
        override fun onTick(millisUntilFinished: Long) {
            binding.text.text = "${millisUntilFinished /1000}秒"
            max -= 1
            binding.progressbar.progress = max
        }

        override fun onFinish() {
            view?.findNavController()?.navigate(R.id.action_adFragment_to_loginFragment)
        }

    }

}