package com.fcerio.ddrivers.features.main

import com.fcerio.common.android.base.BaseViewModel
import com.fcerio.common.android.base.Event
import com.fcerio.ddrivers.components.navigation.HomeSections
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    interactor: MainInteractor
) : BaseViewModel<MainAction, MainResult, MainState>(interactor) {

    init {
        postAction(MainAction.LoadAllTabs)
        postAction(MainAction.LoadDeliveryItems)
    }

    override val defaultState: MainState
        get() = MainState(
            tabs = emptyList(),
            selectedTab = HomeSections.HOME,
            navigateToSelectedTab = Event.empty()
        )

    fun selectedTab(tab: HomeSections) {
        postAction(MainAction.SelectedTab(tab))
    }

    override fun stateReducer(): (MainState, MainResult) -> MainState = { prevState, result ->
        when (result) {
            is MainResult.TabsLoaded -> prevState.copy(tabs = result.tabs)
            is MainResult.NavigateToSelectedTab -> prevState.copy(
                selectedTab = result.tab,
                navigateToSelectedTab = Event(result.tab)
            )
            is MainResult.ItemsLoaded -> prevState.copy()
        }
    }
}
