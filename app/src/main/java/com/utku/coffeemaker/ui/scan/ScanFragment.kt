package com.utku.coffeemaker.ui.scan

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.utku.base.ui.BaseFragment
import com.utku.coffeemaker.databinding.ScanFragmentBinding
import com.utku.coffeemaker.ui.root_activity.RootViewModel
import com.utku.data.remote.Result
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ScanFragment : BaseFragment<ScanFragmentBinding>({ ScanFragmentBinding.inflate(it) }) {

    override val viewModel: RootViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.selectCoffeeMachineImageView.setOnClickListener {
            getCoffeeMakerDetail()
        }
    }

    private fun getCoffeeMakerDetail() {
        viewModel.getCoffeeMakerDetail().observe(viewLifecycleOwner) {
            when (it) {
                is Result.Success -> {
                    viewModel.coffeeMaker.value = it.data
                    findNavController().navigate(
                        ScanFragmentDirections.actionScanFragmentToStyleFragment()
                    )
                }
                is Result.Error -> {
                    viewModel.showError.value = it.exception
                }
            }
        }
    }

}