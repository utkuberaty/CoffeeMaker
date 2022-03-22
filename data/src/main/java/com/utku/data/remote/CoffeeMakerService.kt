package com.utku.data.remote

import com.utku.data.entities.CoffeeMaker
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CoffeeMakerService {

    @GET("coffee-machine/{id}")
    suspend fun getCoffeeMakerDetail(@Path("id") coffeeMakerId: String): Response<CoffeeMaker>

}