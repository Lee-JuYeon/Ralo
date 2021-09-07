package com.cavss.ralo.ui.views.main.memorial

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


class FowAdapter (fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    private var fragments : MutableList<Fragment> = mutableListOf<Fragment>()
    fun updateList(list : List<Fragment>){
        fragments.clear()
        fragments.addAll(list)
    }


    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}
