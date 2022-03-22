package com.utku.data.repository

import com.utku.data.entities.CoffeeMaker
import com.utku.data.entities.Sizes
import com.utku.data.entities.Types
import com.utku.data.remote.RemoteDataSource

class CoffeeMakerRepository(private val remoteDataSource: RemoteDataSource) : BaseRepository() {

    fun getCofferMakerDetail(coffeeMakerId: String) = performGetOperation(networkCall = {
        remoteDataSource.getCoffeeMakerDetail(coffeeMakerId)
    })


}