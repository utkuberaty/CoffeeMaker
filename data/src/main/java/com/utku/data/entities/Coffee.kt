package com.utku.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class Coffee(
    val types: Types?,
    val sizes: Sizes?,
    val selectedExtra: Map<String, SelectedExtra>?
)
