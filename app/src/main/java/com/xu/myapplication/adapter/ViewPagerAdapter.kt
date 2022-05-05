package com.xu.myapplication.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(val fragments:List<Fragment>,
                       val fragmentManager: FragmentManager,
                       val lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount() = fragments.size?: 0

    override fun createFragment(position: Int) = fragments.get(position)
}
