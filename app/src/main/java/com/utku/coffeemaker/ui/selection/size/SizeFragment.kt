package com.utku.coffeemaker.ui.selection.size

import com.utku.base.ui.BaseFragment
import com.utku.coffeemaker.ui.root_activity.RootViewModel
import com.utku.coffeemaker.databinding.RootActivityBinding
import com.utku.coffeemaker.databinding.SizeFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SizeFragment : BaseFragment<SizeFragmentBinding>({ SizeFragmentBinding.inflate(it) }) {

    override val viewModel: RootViewModel by viewModel()

}