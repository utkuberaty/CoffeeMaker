package com.utku.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Types(
    @SerialName("_id") val id: String = "",
    val name: String = "",
    val sizes: List<String> = listOf(),
    val extras: List<String> = listOf()
)