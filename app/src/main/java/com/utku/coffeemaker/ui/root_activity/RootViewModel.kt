package com.utku.coffeemaker.ui.root_activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.utku.data.entities.*
import com.utku.data.repository.CoffeeMakerRepository
import com.utku.data.util.TEST_COFFEE_MACHINE_ID
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

class RootViewModel(private val coffeeMakerRepository: CoffeeMakerRepository) : ViewModel() {

    val showProgress = MutableLiveData(false)
    val showError = MutableLiveData<String>()

    val coffeeMaker = MutableLiveData<CoffeeMaker?>()

    val selectedType = MutableLiveData<Types?>()
    val selectedSize = MutableLiveData<Sizes?>()
    val selectedExtra = MutableLiveData<Map<String,SelectedExtra>?>()

    val coffee: Coffee
    get() = Coffee(selectedType.value, selectedSize.value, selectedExtra.value)

    fun getCoffeeMakerDetail() =
        coffeeMakerRepository.getCofferMakerDetail(TEST_COFFEE_MACHINE_ID).onStart {
            showProgress.value = true
        }.onCompletion { showProgress.value = false }.asLiveData()

}