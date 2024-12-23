package com.example.mobileapplication11

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobileapplication11.adapters.HighlightsAdapter
import com.example.mobileapplication11.adapters.ViewPagerAdapter
import com.example.mobileapplication11.databinding.FragmentProfileBinding
import com.example.mobileapplication11.databinding.FragmentScrollerBinding
import com.example.mobileapplication11.home.homePosts.HomePostsFragment
import com.example.mobileapplication11.models.Highlights
import com.example.mobileapplication11.profile.ProfileFragment
import com.example.mobileapplication11.profile.profileFragments.MainUserPostsFragment
import com.example.mobileapplication11.profile.profileFragments.MainUserTaggedFragment
import com.google.android.material.tabs.TabLayoutMediator

class Scroller : Fragment() {

    private lateinit var binding : FragmentScrollerBinding

    private val fList = listOf(
        textFragment.newInstance(),
        HomePostsFragment.newInstance(),
        ProfileFragment.newInstance()
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScrollerBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() = with(binding) {
        val adapter = ViewPagerAdapter(requireActivity(), fList)
        viewPager2.adapter = adapter
    }



    companion object {
        @JvmStatic
        fun newInstance() = Scroller()
    }
}