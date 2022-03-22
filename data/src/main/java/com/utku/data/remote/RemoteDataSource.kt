package com.utku.data.remote

class RemoteDataSource(private val coffeeMakerService: CoffeeMakerService) : Call() {

    suspend fun getCoffeeMakerDetail(coffeeMakerId: String) =
        call { coffeeMakerService.getCoffeeMakerDetail(coffeeMakerId) }
}