package com.utku.coffeemaker.ui.selection.style

import com.utku.base.ui.BaseFragment
import com.utku.coffeemaker.ui.root_activity.RootViewModel
import com.utku.coffeemaker.databinding.RootActivityBinding
import com.utku.coffeemaker.databinding.StyleFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class StyleFragment : BaseFragment<StyleFragmentBinding>({ StyleFragmentBinding.inflate(it) }) {

    override val viewModel: RootViewModel by viewModel()

}