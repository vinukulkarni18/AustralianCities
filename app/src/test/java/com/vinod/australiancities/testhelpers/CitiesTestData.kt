package com.vinod.australiancities.testhelpers

import com.vinod.australiancitieslibrary.models.CollapsableStates

object CitiesTestData {
    fun getStateCitiesTestData(): ArrayList<CollapsableStates> {
        return arrayListOf(
            CollapsableStates(
                "New South Wales", getCitiesName("New South Wales")
            ),
            CollapsableStates(
                "Victoria", getCitiesName("Victoria")
            ),
            CollapsableStates(
                "Queensland", getCitiesName("Queensland")
            )
        )
    }

    private fun getCitiesName(stateName: String): List<String> {
        return when (stateName) {
            "New South Wales" -> listOf("Sydney", "Central Coast", "Wollongong")
            "Victoria" -> listOf("Sydney", "Central Coast", "Wollongong")
            "Queensland" -> listOf("Sydney", "Central Coast", "Wollongong")
            else -> listOf()
        }
    }
}