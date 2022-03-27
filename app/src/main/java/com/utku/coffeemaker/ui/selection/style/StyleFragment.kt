package com.utku.coffeemaker.ui.selection.style

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.utku.base.ui.BaseFragment
import com.utku.coffeemaker.databinding.StyleFragmentBinding
import com.utku.coffeemaker.ui.root_activity.RootViewModel
import com.utku.coffeemaker.ui.selection.adapter.SelectionAdapter
import com.utku.data.entities.Sizes
import com.utku.data.entities.Types
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class StyleFragment : BaseFragment<StyleFragmentBinding>({ StyleFragmentBinding.inflate(it) }) {

    private val typeList: MutableList<Types> = mutableListOf()

    private val adapter by lazy {
        SelectionAdapter<Types>(onSelectedSelection = {
            viewModel.selectedType.value = it
            navigateNext()
        })
    }

    override val viewModel: RootViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().onBackPressedDispatcher.addCallback(this) {}
        setTypeList()
        setStyleRecyclerView()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun setTypeList() {
        typeList.apply {
            clear()
            addAll(viewModel.coffeeMaker.value?.types ?: listOf())
        }
        adapter.submitList(typeList)
    }

    private fun setStyleRecyclerView() {
        viewBinding.styleRecyclerView.apply {
            adapter = this@StyleFragment.adapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun navigateNext() {
        lifecycleScope.launch {
            viewModel.delayedProgress {
                findNavController().navigate(StyleFragmentDirections.actionStyleFragmentToSizeFragment())
            }
        }
    }

}