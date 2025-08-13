package com.fcerio.ddrivers.features.home

import android.content.SharedPreferences
import com.fcerio.common.android.base.Interactor
import com.fcerio.ddrivers.App
import com.fcerio.ddrivers.components.delivery.models.DeliveryUIModel
import com.fcerio.domain.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import javax.inject.Inject
import androidx.core.content.edit
import com.fcerio.ddrivers.App.Companion.DELIVERY
import com.fcerio.network.NetworkData

class HomeInteractor @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : Interactor<HomeAction, HomeResult> {

    private fun loanAllDeliveryStops(): Flow<HomeResult> {
        return App.DELIVERY.asStateFlow()
            .map {
                val allEntries: Map<String, *> = sharedPreferences.all

                val sortItems = it.sortedBy { it.timeArrival }
                if (allEntries.isNotEmpty()) {
                    HomeResult.DeliveriesLoaded(sortItems.filter { !allEntries.contains(it.id.toString()) })
                } else {
                    HomeResult.DeliveriesLoaded(sortItems)
                }
            }
    }

    private fun showUpdateDeliveryStatusDialog(item: DeliveryUIModel): Flow<HomeResult> {
        return flow {
            emit(HomeResult.ShowUpdateDeliveryStatusDialog(item))
        }
    }

    // Update status in DB or call API to update status
    private fun hideUpdateDeliveryStatusDialog(
        status: Status,
        item: DeliveryUIModel
    ): Flow<HomeResult> {
        return flow {
            val items = App.DELIVERY.asStateFlow().value.filter { it.id != item.id }
            App.DELIVERY.tryEmit(items)
            sharedPreferences.edit { putBoolean(item.id.toString(), true) }
            loanAllDeliveryStops()
            emit(HomeResult.HideUpdateDeliveryStatusDialog)
        }
    }

    private fun resetValues(): Flow<HomeResult> {
        return flow {
            sharedPreferences.edit { clear() }
            // TODO
            DELIVERY.tryEmit(NetworkData.DELIVERIES)
            emit(HomeResult.ResetValuesSuccess)
        }
    }

    private fun dismissDialog(): Flow<HomeResult> {
        return flow {
            emit(HomeResult.DismissDialogSuccess)
        }
    }

    override fun actionProcessor(actions: Flow<HomeAction>): Flow<HomeResult> {
        return merge(
            actions.filterIsInstance<HomeAction.LoadToDeliveries>()
                .flatMapLatest { loanAllDeliveryStops() },
            actions.filterIsInstance<HomeAction.ShowUpdateDeliveryStatusDialog>()
                .flatMapLatest { showUpdateDeliveryStatusDialog(it.item) },
            actions.filterIsInstance<HomeAction.HideUpdateDeliveryStatusDialog>()
                .flatMapLatest { hideUpdateDeliveryStatusDialog(it.status, it.item) },
            actions.filterIsInstance<HomeAction.ResetValues>().flatMapLatest { resetValues() },
            actions.filterIsInstance<HomeAction.DismissDialog>().flatMapLatest { dismissDialog() }
        )
    }
}
