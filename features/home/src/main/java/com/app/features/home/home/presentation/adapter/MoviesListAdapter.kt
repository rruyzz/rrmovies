package com.app.features.home.home.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class MoviesListAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

//    override fun getItemCount(): Int {
//        return 4
//    }
//
//    override fun createFragment(position: Int): Fragment {
//        when (position) {
//            0 -> return CatFragment()
//            1 -> return DogFragment()
//        }
//        return BirdFragment()
//    }

    private val fragmentList = ArrayList<Fragment>()

    fun addFragment(fragment: Fragment) {
        fragmentList.add(fragment)
    }

    override fun getItemCount() = fragmentList.size

    override fun createFragment(position: Int) = fragmentList[position]
}