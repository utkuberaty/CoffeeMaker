package com.utku.coffeemaker.ui.selection.extra

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
import com.utku.coffeemaker.databinding.ExtraFragmentBinding
import com.utku.coffeemaker.ui.root_activity.RootViewModel
import com.utku.coffeemaker.ui.selection.adapter.SelectionAdapter
import com.utku.data.entities.Extras
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ExtraFragment : BaseFragment<ExtraFragmentBinding>({ ExtraFragmentBinding.inflate(it) }) {

    private val extraList: MutableList<Extras> = mutableListOf()

    private val adapter by lazy { SelectionAdapter<Extras>(viewModel.selectedExtra.value) }

    override val viewModel: RootViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setOnClick()
        setExtraList()
        setExtraRecyclerView()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun setExtraList() {
        val typeExtraList = viewModel.coffeeMaker.value?.extras?.filter { size ->
            (viewModel.selectedType.value?.extras ?: listOf()).any { typeSizeId ->
                typeSizeId == size.id
            }
        }
        extraList.apply {
            clear()
            addAll(typeExtraList ?: listOf())
        }
        adapter.submitList(extraList)

        Log.i(TAG, "sizeList -> $typeExtraList")
    }

    private fun setOnClick() {
        viewBinding.apply {
            overviewButton.setOnClickListener {
                val selectedExtras = adapter.selectedExtras
                viewModel.selectedExtra.value = selectedExtras ?: mapOf()
                Log.i(TAG, "selectedExtras before navigate ==> $selectedExtras")
                navigateOverview()
            }
            backLinearLayout.setOnClickListener {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    private fun setExtraRecyclerView() {
        viewBinding.extraRecyclerView.apply {
            adapter = this@ExtraFragment.adapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun navigateOverview() {
        lifecycleScope.launch {
            viewModel.delayedProgress(2000) {
                findNavController().navigate(ExtraFragmentDirections.actionExtraFragmentToOverviewFragment())
            }
        }
    }
}