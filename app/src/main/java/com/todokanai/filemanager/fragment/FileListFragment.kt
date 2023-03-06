package com.todokanai.filemanager.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.todokanai.filemanager.adapter.FileRecyclerAdapter
import com.todokanai.filemanager.databinding.FragmentFileListBinding
import com.todokanai.filemanager.viewmodel.FileListViewModel
import com.todokanai.filemanager.viewmodel.OptionsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FileListFragment : Fragment() {

    private val binding by lazy{ FragmentFileListBinding.inflate(layoutInflater)}
    private val viewModel : FileListViewModel by viewModels()
    private val oViewModel : OptionsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            viewModel.onBackPressed()
        }

        val adapter = FileRecyclerAdapter( {viewModel.onItemClick(requireContext(),it)},
            {viewModel.onItemLongClick(it)})
        binding.fileRecyclerView.adapter = adapter
        binding.fileRecyclerView.layoutManager = LinearLayoutManager(context)

        binding.run{
            moveBtn.setOnClickListener { viewModel.moveBtn() }
            copyBtn.setOnClickListener { viewModel.copyBtn() }
            renameBtn.setOnClickListener { viewModel.renameBtn() }
            deleteBtn.setOnClickListener { viewModel.deleteBtn() }
            moreBtn.setOnClickListener { viewModel.moreBtn() }
        }


        viewModel.run{
            currentPath.observe(viewLifecycleOwner){
                viewModel.prepareContents(it)
            }       // 현재 directory 값 observe
            fileList.observe(viewLifecycleOwner){
                if (it == null) {
                    viewModel.exit(requireActivity())
                } else if (it.isEmpty()){
                    binding.nofilesTextview.visibility = View.VISIBLE
                    adapter.filesAndFolders = it
                    adapter.notifyDataSetChanged()
                } else {
                    binding.nofilesTextview.visibility = View.INVISIBLE
                    adapter.filesAndFolders = it
                    adapter.notifyDataSetChanged()
                }
            }
        }

        oViewModel.bottomMenu.observe(viewLifecycleOwner){
            if(it==true){
                binding.menuConstraint.visibility = View.VISIBLE
            } else {
                binding.menuConstraint.visibility = View.GONE
            }
        }





        return binding.root
    }
}