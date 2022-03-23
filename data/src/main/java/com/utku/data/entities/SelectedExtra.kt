package com.utku.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class SelectedExtra(val extraName: String, val subselections: Subselections)
