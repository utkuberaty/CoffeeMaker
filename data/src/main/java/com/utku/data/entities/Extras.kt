package com.utku.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Extras (
	@SerialName("_id") val id : String = "",
	val name : String = "",
	val subselections : List<Subselections> = listOf()
)