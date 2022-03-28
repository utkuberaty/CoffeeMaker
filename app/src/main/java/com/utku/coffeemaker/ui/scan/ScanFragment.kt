package com.utku.coffeemaker.ui.scan

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.utku.base.ui.BaseFragment
import com.utku.coffeemaker.databinding.ScanFragmentBinding
import com.utku.coffeemaker.ui.root_activity.RootViewModel
import com.utku.data.util.TEST_COFFEE_MACHINE_ID
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScanFragment : BaseFragment<ScanFragmentBinding>({ ScanFragmentBinding.inflate(it) }) {

    override val viewModel: RootViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.selectCoffeeMachineImageView.setOnClickListener {
            findNavController().navigate(
                ScanFragmentDirections.actionScanFragmentToStyleFragment(TEST_COFFEE_MACHINE_ID)
            )
        }
    }
}