package com.vinod.australiancities.cities

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.vinod.australiancities.testhelpers.CitiesTestData
import com.vinod.australiancitieslibrary.models.CitiesResponse
import com.vinod.australiancitieslibrary.models.ResponseState
import com.vinod.australiancitieslibrary.repositories.CitiesRepository
import dagger.hilt.android.testing.HiltTestApplication
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.spy
import org.mockito.kotlin.whenever
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [33], application = HiltTestApplication::class)
class CitiesViewModelTest {
    private var instrumentationContext: Context? = null

    @Before
    fun setup() {
        instrumentationContext = InstrumentationRegistry.getInstrumentation().context
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testViewModel() = runTest {
        testSuccessScenario()
        testFailureScenario()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun testSuccessScenario() = runTest {
        val repository = spy(CitiesRepository(instrumentationContext!!))
        whenever(repository.fetchCities()).thenReturn(
            ResponseState.Success(
                data = CitiesResponse(
                    data = CitiesTestData.getStateCitiesTestData()
                )
            )
        )
        val viewModel = CitiesViewModel(repository)
        viewModel.fetchCities()
        assertEquals(
            3,
            (viewModel.citiesDataResponse.value as ResponseState.Success).data.data!!.size
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun testFailureScenario() = runTest {
        val repository = spy(CitiesRepository(instrumentationContext!!))
        whenever(repository.fetchCities()).thenReturn(
            ResponseState.Error(Throwable("Something went wrong"))
        )
        val viewModel = CitiesViewModel(repository)
        viewModel.fetchCities()
        val message = (viewModel.citiesDataResponse.value as ResponseState.Error).error.message
        assertEquals("Something went wrong", message)
    }

    @After
    fun tearDown() {
        instrumentationContext = null
    }
}