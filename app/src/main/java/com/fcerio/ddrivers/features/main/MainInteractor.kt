package com.fcerio.ddrivers.features.main

import com.fcerio.common.android.base.Interactor
import com.fcerio.ddrivers.App.Companion.DELIVERY
import com.fcerio.ddrivers.components.navigation.HomeSections
import com.fcerio.network.NetworkData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainInteractor @Inject constructor(
) : Interactor<MainAction, MainResult> {

    private fun loanDeliveryItems(): Flow<MainResult> {
        return flow {
            DELIVERY.tryEmit(NetworkData.DELIVERIES)
        }
    }

    private fun loadAllTabs(): Flow<MainResult> {
        return flow {
            DELIVERY.tryEmit(NetworkData.DELIVERIES)
            emit(MainResult.TabsLoaded(HomeSections.entries))
        }
    }

    private fun navigateToSelectedTab(tab: HomeSections): Flow<MainResult> {
        return flow {
            emit(MainResult.NavigateToSelectedTab(tab))
        }
    }

    override fun actionProcessor(actions: Flow<MainAction>): Flow<MainResult> {
        return merge(
            actions.filterIsInstance<MainAction.LoadDeliveryItems>().flatMapLatest { loanDeliveryItems() },
            actions.filterIsInstance<MainAction.LoadAllTabs>().flatMapLatest { loadAllTabs() },
            actions.filterIsInstance<MainAction.SelectedTab>().flatMapLatest { navigateToSelectedTab(it.tab) }
        )
    }
}
