package com.todokanai.filemanager.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.todokanai.filemanager.databinding.FragmentOptionsBinding
import com.todokanai.filemanager.viewmodel.OptionsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OptionsFragment : Fragment() {

    private val binding by lazy{FragmentOptionsBinding.inflate(layoutInflater)}
    private val viewModel : OptionsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding.leftBtn.setOnClickListener { viewModel.leftBtn() }
        binding.middleBtn.setOnClickListener { viewModel.middleBtn() }
        binding.rightBtn.setOnClickListener { viewModel.rightBtn() }


        return binding.root
    }
}