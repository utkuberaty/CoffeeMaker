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
import com.utku.data.entities.Types
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class StyleFragment : BaseFragment<StyleFragmentBinding>({ StyleFragmentBinding.inflate(it) }) {

    private val typeList: MutableList<Types> = mutableListOf()

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
    }

    private fun setStyleRecyclerView() {
        viewBinding.styleRecyclerView.apply {
            adapter = SelectionAdapter(typeList) {
                viewModel.selectedType.value = it
                navigateNext()
            }
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun navigateNext() {
        lifecycleScope.launch {
            viewModel.showProgress.value = true
            delay(1000)
            viewModel.showProgress.value = false
            findNavController().navigate(StyleFragmentDirections.actionStyleFragmentToSizeFragment())
        }
    }

}