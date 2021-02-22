package com.dyckster.app.tests

import androidx.test.core.app.ActivityScenario
import com.agoda.kakao.screen.Screen
import com.dyckster.app.screen.BitcoinChartScreen
import com.dyckster.app.screen.BitcoinChartTypeListScreen
import com.dyckster.base.ui.chart.screen.BitcoinChartActivity
import com.dyckster.base.ui.list.screen.BitcoinChartListActivity
import com.dyckster.chart.R
import com.kaspersky.kaspresso.testcases.api.testcaserule.TestCaseRule
import org.junit.Rule
import org.junit.Test

class BitcoinChartUiTest {

    @get:Rule
    val testCaseRule = TestCaseRule(javaClass.simpleName)

    private val chartTypeListScreen = BitcoinChartTypeListScreen()
    private val chartScreen = BitcoinChartScreen()

    @Test
    fun shouldOpenChartScreen() {
        testCaseRule.run {
            step("Open chart type list screen") {
                ActivityScenario.launch(BitcoinChartListActivity::class.java)
            }
            step("Check that chart type list has items") {
                chartTypeListScreen.typeList {
                    isDisplayed()
                    hasSize(5)
                }
            }
            step("Open chart screen") {
                chartTypeListScreen.typeList {
                    emptyChildAt(0) {
                        click()
                    }
                }
            }
            step("Check that chart screen is opened") {
                device.activities.isCurrent(BitcoinChartActivity::class.java)
            }
        }
    }

    @Test
    fun shouldShowChartDataOnChartClick() {
        testCaseRule.run {
            step("Open chart type list screen") {
                ActivityScenario.launch(BitcoinChartListActivity::class.java)
            }
            step("Open chart screen") {
                chartTypeListScreen.typeList {
                    emptyChildAt(0) {
                        click()
                    }
                }
            }
            step("Check that selected value info is not visible and tip is visible") {
                chartScreen {
                    chartDragTip.isDisplayed()
                    selectedValueAmount.isNotDisplayed()
                }
            }
            step("Click on chart") {
                chartScreen {
                    Screen.idle(500L)
                    chartView.click()
                }
            }
            step("Check that selected value is shown and tip is not") {
                chartScreen {
                    chartDragTip.isNotDisplayed()
                    selectedValueAmount.isDisplayed()
                }
            }
        }
    }

    @Test
    fun shouldCheckThatDescriptionIsVisible() {
        testCaseRule.run {
            step("Open chart type list screen") {
                ActivityScenario.launch(BitcoinChartListActivity::class.java)
            }
            step("Open chart screen") {
                chartTypeListScreen.typeList {
                    emptyChildAt(0) {
                        click()
                    }
                }
            }
            step("Check that chart description is visible") {
                chartScreen {
                    chartDescription.isDisplayed()
                }
            }
        }
    }

    @Test
    fun shouldChangeTimespan() {
        testCaseRule.run {
            step("Open chart type list screen") {
                ActivityScenario.launch(BitcoinChartListActivity::class.java)
            }
            step("Open chart screen") {
                chartTypeListScreen.typeList {
                    emptyChildAt(0) {
                        click()
                    }
                }
            }
            step("Select week timespan") {
                chartScreen {
                    timespansChipGroup.selectChip(R.id.chart_timespan_week)
                }
            }
        }
    }
}