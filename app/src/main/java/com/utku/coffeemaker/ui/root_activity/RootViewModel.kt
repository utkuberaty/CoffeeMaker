package com.utku.coffeemaker.ui.root_activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.utku.data.repository.CoffeeMakerRepository
import com.utku.data.util.TEST_COFFEE_MACHINE_ID

class RootViewModel(private val coffeeMakerRepository: CoffeeMakerRepository) : ViewModel() {

    val showProgress = MutableLiveData(false)

    fun getCoffeeMakerDetail() = coffeeMakerRepository.getCofferMakerDetail(
        TEST_COFFEE_MACHINE_ID
    ).asLiveData()
}