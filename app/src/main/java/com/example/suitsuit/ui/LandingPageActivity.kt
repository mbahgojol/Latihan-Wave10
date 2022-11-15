package com.example.suitsuit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import com.example.suitsuit.databinding.ActivityLandingPageBinding
import com.example.suitsuit.fragment.AdapterViewPager2
import com.example.suitsuit.fragment.LandingPage1Fragment
import com.example.suitsuit.fragment.LandingPage2Fragment
import com.example.suitsuit.fragment.LandingPage3Fragment

class LandingPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLandingPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listFragment = mutableListOf(
            LandingPage1Fragment(),
            LandingPage2Fragment(),
            LandingPage3Fragment()
        )

        val adapterViewPager2 = AdapterViewPager2(this, listFragment)
        binding.viewpager2.adapter = adapterViewPager2
        binding.dotsIndicator.attachTo(binding.viewpager2)
        binding.viewpager2.registerOnPageChangeCallback(changeListenerViewPager2)

        binding.ivNext.setOnClickListener {
            Log.d("Click","Button next")
            val currentPosition = binding.viewpager2.currentItem
            binding.viewpager2.setCurrentItem(currentPosition + 1, true)
        }

    }

    private val changeListenerViewPager2 = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            Log.e("Posisi ke-", position.toString())
            binding.ivNext.isVisible = position != 2
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding.viewpager2.registerOnPageChangeCallback(changeListenerViewPager2)
    }

}