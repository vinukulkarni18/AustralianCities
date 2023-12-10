package com.vinod.australiancitieslibrary.repositories

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.vinod.australiancitieslibrary.models.ResponseState
import dagger.hilt.android.testing.HiltTestApplication
import junit.framework.TestCase.assertEquals
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
class CitiesRepositoryTest {

    private var instrumentationContext: Context? = null

    @Before
    fun setup() {
        instrumentationContext = InstrumentationRegistry.getInstrumentation().context
    }

    @Test
    fun testRepository() = runTest {
        // success scenario
        val repository = spy(CitiesRepository(instrumentationContext!!))
        var response = repository.fetchCities()
        assertEquals(8, (response as ResponseState.Success).data.data!!.size)

        // error scenario
        whenever(repository.fetchCities()).thenReturn(ResponseState.Error(Throwable("Something went wrong")))
        response = repository.fetchCities()
        val message = (response as ResponseState.Error).error.message
        assertEquals("Something went wrong", message)
    }

    @After
    fun tearDown() {
        instrumentationContext = null
    }
}