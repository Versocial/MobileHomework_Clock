package com.example.mobilehomework_clock

import ClockFragment
import TextClockFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.clock_pager)
        val pagerAdapter = ClockPagerAdapter(this)
        viewPager.adapter = pagerAdapter
    }



    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            super.onBackPressed()
        } else {
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }

    private inner class ClockPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        private val NUM_PAGES:Int=2;

        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment {
            val fragment: Fragment = if (position % 2 == 1) {
                ClockFragment()
            } else {
                TextClockFragment()
            }
            return fragment
        }
    }
}