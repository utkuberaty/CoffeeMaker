package com.utku.coffeemaker.ui.selection.overview

import com.utku.base.ui.BaseFragment
import com.utku.coffeemaker.databinding.OverviewFragmentBinding
import com.utku.coffeemaker.ui.root_activity.RootViewModel
import com.utku.coffeemaker.databinding.RootActivityBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class OverviewFragment : BaseFragment<OverviewFragmentBinding>({
    OverviewFragmentBinding.inflate(it)
}) {

    override val viewModel: RootViewModel by viewModel()

}