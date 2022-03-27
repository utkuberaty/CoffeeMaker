package com.utku.coffeemaker.ui.root_activity

import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import com.utku.coffeemaker.ui.scan.ScanFragmentDirections
import com.utku.data.entities.*
import com.utku.data.remote.Result
import com.utku.data.repository.CoffeeMakerRepository
import com.utku.data.util.TEST_COFFEE_MACHINE_ID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class RootViewModel(private val coffeeMakerRepository: CoffeeMakerRepository) : ViewModel() {

    private val _showProgress = MutableLiveData(false)
    val showProgress: LiveData<Boolean> by ::_showProgress

    private val _showError = MutableLiveData<String>()
    val showError: LiveData<String> by ::_showError

    private val _coffeeMaker = MutableLiveData<CoffeeMaker>()
    val coffeeMaker: LiveData<CoffeeMaker> by ::_coffeeMaker

    val selectedType = MutableLiveData<Types?>()
    val selectedSize = MutableLiveData<Sizes?>()
    val selectedExtra = MutableLiveData<Map<String, SelectedExtra>?>()

    val coffee: Coffee
        get() = Coffee(selectedType.value, selectedSize.value, selectedExtra.value)

    /*fun getCoffeeMakerDetail() {
        viewModelScope.launch {
            coffeeMakerRepository.getCofferMakerDetail(TEST_COFFEE_MACHINE_ID).onStart {
                _showProgress.value = true
            }.onCompletion { _showProgress.value = false }.collect {
                when (it) {
                    is Result.Success -> _coffeeMaker.value = it.data!!
                    is Result.Error -> _showError.value = it.exception
                }
            }
        }
    }*/

    fun getCoffeeMakerDetail(onSuccess: () -> Unit) {
        viewModelScope.launch {
            coffeeMakerRepository.getCofferMakerDetail(TEST_COFFEE_MACHINE_ID).onStart {
                _showProgress.value = true
            }.onCompletion { _showProgress.value = false }.collect {
                when (it) {
                    is Result.Success -> {
                        _coffeeMaker.value = it.data!!
                        onSuccess()
                    }
                    is Result.Error -> _showError.value = it.exception
                }
            }
        }
    }

    suspend fun delayedProgress(delay: Long = 1000, progress: () -> Unit) {
        _showProgress.value = true
        delay(delay)
        progress()
        _showProgress.value = false
    }

}