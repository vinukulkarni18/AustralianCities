package com.vinod.australiancities.cities

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.vinod.australiancities.R
import androidx.hilt.navigation.compose.hiltViewModel
import com.vinod.australiancities.utils.DisplayErrorMessage
import com.vinod.australiancities.utils.DisplayLoadingScreen
import com.vinod.australiancitieslibrary.models.CollapsableStates
import com.vinod.australiancitieslibrary.models.ResponseState
import com.vinod.australiancitieslibrary.utils.DECIMAL_ONE
import com.vinod.australiancitieslibrary.utils.DECIMAL_TWO
import com.vinod.australiancitieslibrary.utils.EMPTY
import com.vinod.australiancitieslibrary.utils.MaterialIconDimension

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayCitiesListScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Scaffold(topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.my_cities_in_australia))
                }
            )
        }) {
            DisplayCityListData(it)
        }
    }
}

@Composable
fun DisplayCityListData(
    paddingValues: PaddingValues,
    cityViewModel: CitiesViewModel = hiltViewModel()
) {
    val isLoadingProgress = remember { mutableStateOf(false) }
    when (val cityResponseState = cityViewModel.citiesDataResponse.collectAsState().value) {

        is ResponseState.Loading -> {
            LaunchedEffect(Unit) {
                isLoadingProgress.value = true
            }
        }

        is ResponseState.Success -> {
            isLoadingProgress.value = false
            val cities = cityResponseState.data
            if (cities.data.isNullOrEmpty()) {
                DisplayErrorMessage(
                    paddingValues,
                    stringResource(id = R.string.something_went_wrong)
                )
            } else {
                cities.data?.let {
                    CollapsableStatesList(
                        it,
                        modifier = Modifier.padding(
                            vertical = paddingValues.calculateTopPadding(),
                            horizontal = dimensionResource(id = R.dimen.spacing20x)
                        )
                    )
                }
            }
        }

        is ResponseState.Error -> {
            isLoadingProgress.value = false
            DisplayErrorMessage(
                paddingValues,
                cityResponseState.error.message
                    ?: stringResource(id = R.string.something_went_wrong)
            )
        }

        else -> Unit
    }
    DisplayLoadingScreen(isLoading = isLoadingProgress.value)
    LaunchedEffect(Unit) {
        cityViewModel.fetchCities()
    }
}

@Composable
fun CollapsableStatesList(
    citiesList: ArrayList<CollapsableStates>,
    modifier: Modifier = Modifier,
) {
    val collapsedState = remember(citiesList) { citiesList.map { true }.toMutableStateList() }
    LazyColumn(modifier) {
        citiesList.forEachIndexed { i, dataItem ->
            val collapsed = collapsedState[i]
            item(key = "header_$i") {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable {
                            collapsedState[i] = !collapsed
                        },
                ) {
                    DisplayStateSectionRow(this, dataItem.title, collapsed)
                }
                Divider()
            }
            if (!collapsed) {
                items(dataItem.cities) { cityName ->
                    DisplayCityRow(cityName)
                }
            }
        }
    }
}

@Composable
fun DisplayStateSectionRow(rowScope: RowScope, stateName: String, isSectionCollapsed: Boolean) {
    rowScope.apply {
        Text(
            stateName,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(
                    horizontal = dimensionResource(id = R.dimen.spacing20x),
                    vertical = dimensionResource(id = R.dimen.spacing10x)
                )
                .weight(DECIMAL_ONE),
        )
        Icon(
            Icons.Default.run {
                if (isSectionCollapsed)
                    KeyboardArrowDown
                else
                    KeyboardArrowUp
            },
            contentDescription = EMPTY,
            tint = Color.LightGray,
            modifier = Modifier.weight(DECIMAL_TWO)
        )
    }
}


@Composable
fun DisplayCityRow(cityName: String) {
    Row {
        Spacer(modifier = Modifier.size(MaterialIconDimension.dp))
        Text(
            cityName,
            modifier = Modifier
                .padding(vertical = dimensionResource(id = R.dimen.spacing10x))
        )
    }
    Divider()
}