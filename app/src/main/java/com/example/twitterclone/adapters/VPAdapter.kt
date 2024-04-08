package com.example.twitterclone.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.twitterclone.SuggestedAccountFragment
import com.example.twitterclone.TweetFragment

class VPAdapter (fa: FragmentActivity): FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> SuggestedAccountFragment()
            else -> TweetFragment()
        }
    }
}