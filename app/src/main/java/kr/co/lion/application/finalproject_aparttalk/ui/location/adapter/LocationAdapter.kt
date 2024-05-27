package kr.co.lion.application.finalproject_aparttalk.ui.location.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class LocationAdapter(private val fragmentList: ArrayList<Fragment>, container: FragmentActivity) : FragmentStateAdapter(container) {
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}