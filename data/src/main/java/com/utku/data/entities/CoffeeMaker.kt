package com.utku.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class CoffeeMaker(
    val types: List<Types>,
    val sizes: List<Sizes>,
    val extras: List<Extras>
)

fun CoffeeMaker.sizesWithDetail(sizeIdList: List<String>) = sizes.filter { detailedSizes ->
    sizeIdList.any {
        it == detailedSizes.id
    }
}

fun CoffeeMaker.extrasWithDetail(extraIdList: List<String>) = extras.filter { detailedExtra ->
    extraIdList.any {
        it == detailedExtra.id
    }
}

