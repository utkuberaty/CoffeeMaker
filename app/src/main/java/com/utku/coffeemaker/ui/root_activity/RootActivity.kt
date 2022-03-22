package com.utku.coffeemaker.ui.root_activity

import com.utku.base.ui.BaseActivity
import com.utku.coffemaker.databinding.RootActivityBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RootActivity : BaseActivity<RootActivityBinding>({ RootActivityBinding.inflate(it) }) {

    override val viewModel: RootViewModel by viewModel()

}