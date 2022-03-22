package com.utku.coffeemaker.ui.scan

import android.os.Bundle
import android.util.Log
import android.view.View
import com.utku.base.ui.BaseFragment
import com.utku.base.util.TAG
import com.utku.coffeemaker.ui.root_activity.RootViewModel
import com.utku.coffeemaker.databinding.RootActivityBinding
import com.utku.coffeemaker.databinding.ScanFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScanFragment : BaseFragment<ScanFragmentBinding>({ ScanFragmentBinding.inflate(it) }) {

    override val viewModel: RootViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.selectCoffeeMachineImageView.setOnClickListener {
            Log.i(TAG, "coffee Maker selected")
        }
    }

}