package com.example.suitsuit.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class AdapterViewPager2(fa: FragmentActivity, private val dataFragments: MutableList<Fragment>) :
    FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = dataFragments.size

    override fun createFragment(position: Int): Fragment = dataFragments[position]
}