package com.utku.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Subselections(
    @SerialName("_id") val id: String,
    val name: String
)