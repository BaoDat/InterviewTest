package com.datdang.interviewtest.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.datdang.interviewtest.databinding.ActivityMainBinding
import com.datdang.interviewtest.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = { inflater -> ActivityMainBinding.inflate(inflater) }
    override val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}