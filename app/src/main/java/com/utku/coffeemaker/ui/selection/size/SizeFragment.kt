package com.utku.coffeemaker.ui.selection.size

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.utku.base.ui.BaseFragment
import com.utku.base.util.TAG
import com.utku.coffeemaker.databinding.SizeFragmentBinding
import com.utku.coffeemaker.ui.root_activity.RootViewModel
import com.utku.coffeemaker.ui.selection.adapter.SelectionAdapter
import com.utku.data.entities.Sizes
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SizeFragment : BaseFragment<SizeFragmentBinding>({ SizeFragmentBinding.inflate(it) }) {

    private val sizeList = mutableListOf<Sizes>()

    private val adapter by lazy {
        SelectionAdapter<Sizes>(onSelectedSelection = {
            viewModel.selectedSize.value = it
            navigateNext()
        })
    }

    override val viewModel: RootViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        backButtonClick()
        setSizeList()
        setSizeRecyclerView()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun backButtonClick() {
        viewBinding.backLinearLayout.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setSizeList() {
        val typeSizeList = viewModel.coffeeMaker.value?.sizes?.filter { size ->
            (viewModel.selectedType.value?.sizes ?: listOf()).any { typeSizeId ->
                typeSizeId == size.id
            }
        }
        sizeList.apply {
            clear()
            addAll(typeSizeList ?: listOf())
        }
        adapter.submitList(sizeList)
        Log.i(TAG, "sizeList -> $sizeList")
    }

    private fun setSizeRecyclerView() {
        viewBinding.sizeRecyclerView.apply {
            adapter = this@SizeFragment.adapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun navigateNext() {
        lifecycleScope.launch {
            viewModel.delayedProgress {
                findNavController().navigate(SizeFragmentDirections.actionSizeFragmentToExtraFragment())
            }
        }
    }
}