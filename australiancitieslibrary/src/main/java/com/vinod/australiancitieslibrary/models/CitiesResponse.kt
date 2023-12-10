package com.vinod.australiancitieslibrary.models

import com.google.gson.annotations.SerializedName

data class City(
    val city: String,
    val lat: String,
    val lng: String,
    val country: String,
    val iso2: String,
    @SerializedName("admin_name")
    val state: String,
    val capital: String,
    val population: String,
    @SerializedName("population_proper")
    val populationProper: String
)

data class CollapsableStates(val title: String, val cities: List<String>)

data class CitiesResponse(
    val data: ArrayList<CollapsableStates>? = null,
    val errorMessages: ArrayList<String>? = null
)