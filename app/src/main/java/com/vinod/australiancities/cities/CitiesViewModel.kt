package com.vinod.australiancities.cities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinod.australiancitieslibrary.models.CitiesResponse
import com.vinod.australiancitieslibrary.models.ResponseState
import com.vinod.australiancitieslibrary.repositories.CitiesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(private val citiesRepository: CitiesRepository) :
    ViewModel() {

    private val _citiesResponse by lazy {
        MutableStateFlow<ResponseState<CitiesResponse>>(ResponseState.Idle)
    }
    val citiesDataResponse: StateFlow<ResponseState<CitiesResponse>> = _citiesResponse

    fun fetchCities() {
        _citiesResponse.value = ResponseState.Idle
        viewModelScope.launch {
            _citiesResponse.value = citiesRepository.fetchCities()
        }
    }
}