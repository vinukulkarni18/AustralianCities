package com.vinod.australiancities.cities

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ActivityScenario
import com.vinod.australiancities.activities.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [33], application = HiltTestApplication::class)
@HiltAndroidTest
class CitiesScreenTest {
    @get:Rule
    val composeAndroidTestRule = createAndroidComposeRule<MainActivity>()

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Test
    fun testActivityCreation() {
        // Some test code here
        ActivityScenario.launch(MainActivity::class.java)
            .use { scenario ->
                scenario.onActivity { activity: MainActivity ->
                    composeAndroidTestRule.onAllNodesWithText("My Cities in Australia")[0].assertExists()
                    composeAndroidTestRule.onAllNodesWithText("New South Wales")[1].assertIsDisplayed()
                    composeAndroidTestRule.onAllNodesWithText("Victoria")[1].assertIsDisplayed()
                    composeAndroidTestRule.onAllNodesWithText("New South Wales")[1].assertHasClickAction()
                    composeAndroidTestRule.onAllNodesWithText("New South Wales")[1].performClick()
                    composeAndroidTestRule.onNode(hasText("Sydney")).assertIsDisplayed()
                }
            }
    }
}