package com.fcerio.ddrivers.features.main

import com.fcerio.common.android.base.Event
import com.fcerio.common.android.base.UiStateWithId
import com.fcerio.common.android.base.UiStateWithIdDelegate
import com.fcerio.ddrivers.components.navigation.HomeSections

data class MainState(
    val tabs: List<HomeSections>,
    val selectedTab: HomeSections,
    val navigateToSelectedTab: Event<HomeSections>
) : UiStateWithId by UiStateWithIdDelegate()

sealed interface MainAction {
    data object LoadDeliveryItems : MainAction
    data object LoadAllTabs : MainAction
    data class SelectedTab(val tab: HomeSections) : MainAction
}

sealed interface MainResult {
    data object ItemsLoaded : MainResult
    data class TabsLoaded(val tabs: List<HomeSections>) : MainResult
    data class NavigateToSelectedTab(val tab: HomeSections) : MainResult
}
