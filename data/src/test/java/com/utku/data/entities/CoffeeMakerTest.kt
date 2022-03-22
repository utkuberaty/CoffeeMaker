package com.utku.data.entities

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Test

class CoffeeMakerTest {

    private val jsonData = """ {
  "types": [ {"_id": "50","name": "Ristretto", "sizes": ["3","4"], "extras": ["9"]}],
  "sizes": [{"_id": 2,"name": "Large"}, {"_id": 3,"name": "Mid"}, {"_id": 4,"name": "Small"}],
  "extras": [{"_id": 9,"name": "Select the amount of sugar","subselections": [{"_id": 20,"name": "A lot"},{"_id": 21,"name": "Normal"}]}]
  }"""

    private val json = Json {
        prettyPrint = true
        isLenient = true
        coerceInputValues = true
        ignoreUnknownKeys = true
    }

    var coffeeMaker: CoffeeMaker? = null

    @Before
    fun setData() {
        coffeeMaker = json.decodeFromString(jsonData)
    }

    @Test
    fun `check that is null`() {
        assert(coffeeMaker != null)
    }

    @Test
    fun `test coffeeMaker extensions`() {

        val typeSizes = coffeeMaker?.let {
            it.sizesWithDetail(it.types[0].sizes)
        }

        val typeExtras = coffeeMaker?.let {
            it.extrasWithDetail(it.types[0].extras)
        }
        println("typeExtras => $typeExtras \n typeSizes => $typeSizes")
        assert(typeSizes?.size == 2 && typeExtras?.size == 1)
    }

}