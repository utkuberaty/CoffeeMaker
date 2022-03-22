package com.utku.coffeemaker.ui.selection.extra

import com.utku.base.ui.BaseFragment
import com.utku.coffeemaker.databinding.ExtraFragmentBinding
import com.utku.coffeemaker.ui.root_activity.RootViewModel
import com.utku.coffeemaker.databinding.RootActivityBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExtraFragment : BaseFragment<ExtraFragmentBinding>({ ExtraFragmentBinding.inflate(it) }) {

    override val viewModel: RootViewModel by viewModel()

}