package com.vinod.australiancitieslibrary.repositories

import android.content.Context
import com.google.gson.Gson
import com.vinod.australiancitieslibrary.models.CitiesResponse
import com.vinod.australiancitieslibrary.models.City
import com.vinod.australiancitieslibrary.models.CollapsableStates
import com.vinod.australiancitieslibrary.models.ResponseState
import com.vinod.australiancitieslibrary.utils.EMPTY
import com.vinod.australiancitieslibrary.utils.JSON_FILE_NAME
import com.vinod.australiancitieslibrary.utils.fromJson
import com.vinod.australiancitieslibrary.utils.getJsonDataFromAsset
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class CitiesRepository(@ActivityContext private val context: Context) {

    suspend fun fetchCities(): ResponseState<CitiesResponse> {
        try {
            val citiesList = fetchCitiesFromLocalDataSource()
            val collapsableCitiesList = ArrayList<CollapsableStates>()
            citiesList?.groupBy {
                it.state
            }?.entries?.forEach {
                collapsableCitiesList.add(
                    CollapsableStates(
                        title = it.key,
                        cities = it.value.map { entry -> entry.city })
                )
            }
            return ResponseState.Success(CitiesResponse(data = collapsableCitiesList))
        } catch (ex: Exception) {
            ex.printStackTrace()
            return ResponseState.Error(ex)
        }
    }

    private suspend fun fetchCitiesFromLocalDataSource(): ArrayList<City>? = withContext(IO) {
        val cityJson = JSON_FILE_NAME.getJsonDataFromAsset(context) ?: EMPTY
        try {
            Gson().fromJson<ArrayList<City>>(cityJson)
        } catch (ex: Exception) {
            ex.printStackTrace()
            null
        }
    }
}