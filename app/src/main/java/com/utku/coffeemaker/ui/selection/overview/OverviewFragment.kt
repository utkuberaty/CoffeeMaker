package com.utku.coffeemaker.ui.selection.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.utku.base.ui.BaseFragment
import com.utku.coffeemaker.R
import com.utku.coffeemaker.databinding.OverviewFragmentBinding
import com.utku.coffeemaker.ui.root_activity.RootViewModel
import com.utku.coffeemaker.ui.selection.adapter.SelectionAdapter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class OverviewFragment : BaseFragment<OverviewFragmentBinding>({
    OverviewFragmentBinding.inflate(it)
}) {

    override val viewModel: RootViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        onBackButton()
        setSelectedSizeAndType()
        setOverviewRecyclerView()
        brewCoffee()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun onBackButton() {
        viewBinding.backLinearLayout.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setSelectedSizeAndType() {
        viewBinding.apply {
            viewModel.coffee.sizes?.apply {
                val typeImageRes = when (name.lowercase()) {
                    "Large" -> R.drawable.large
                    "Venti" -> R.drawable.medium
                    "Tall" -> R.drawable.small
                    else -> R.drawable.small
                }
                sizeSelection.apply {
                    selectionImageView.setImageResource(typeImageRes)
                    selectionNameTextView.text = name
                    divider.visibility = View.VISIBLE
                    selectionEditTextView.visibility = View.VISIBLE
                    selectionEditTextView.setOnClickListener {
                        navigateScreenTo(
                            OverviewFragmentDirections.actionOverviewFragmentToSizeFragment()
                        )
                    }
                }
            }

            viewModel.coffee.types?.apply {
                val typeImageRes = when (name) {
                    "Espresso" -> R.drawable.espresso
                    "Cappuccino" -> R.drawable.cappuchino
                    else -> R.drawable.espresso
                }
                typeSelection.apply {
                    selectionImageView.setImageResource(typeImageRes)
                    selectionNameTextView.text = name
                    divider.visibility = View.VISIBLE
                    selectionEditTextView.visibility = View.VISIBLE
                    selectionEditTextView.setOnClickListener {
                        navigateScreenTo(
                            OverviewFragmentDirections.actionOverviewFragmentToStyleFragment()
                        )
                    }
                }
            }

        }
    }

    private fun brewCoffee() {
        viewBinding.brewCoffeeButton.setOnClickListener {
            lifecycleScope.launch {
                viewModel.showProgress.value = true
                delay(2000)
                viewModel.showProgress.value = false
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle(R.string.congratulation)
                    .setMessage(R.string.finish_text)
                    .setPositiveButton(R.string.thanks) { dialog, _ ->
                        dialog.dismiss()
                        navigateScreenTo(OverviewFragmentDirections.actionOverviewFragmentToScanFragment())
                    }.show()
            }
        }
    }

    private fun navigateScreenTo(directions: NavDirections) {
        findNavController().navigate(directions)
    }

    private fun setOverviewRecyclerView() {
        viewBinding.overviewRecyclerView.apply {
            adapter = SelectionAdapter(
                viewModel.coffee.selectedExtra?.values?.toList() ?: listOf(),
                onExtraEditSelected = {
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                })
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }
}