package com.utku.coffeemaker.di

import com.utku.coffeemaker.ui.root_activity.RootViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { RootViewModel(get()) }
}