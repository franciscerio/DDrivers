package com.fcerio.ddrivers.features.home

import com.fcerio.common.android.base.BaseViewModel
import com.fcerio.common.android.base.Event
import com.fcerio.ddrivers.components.delivery.models.DeliveryUIModel
import com.fcerio.ddrivers.components.delivery.models.toMapUIModel
import com.fcerio.domain.Status
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class HomeViewModel @javax.inject.Inject constructor(
    interactor: HomeInteractor
) : BaseViewModel<HomeAction, HomeResult, HomeState>(interactor) {

    init {
        postAction(HomeAction.LoadToDeliveries)
    }

    override val defaultState: HomeState
        get() = HomeState(
            deliveries = emptyList(),
            isRefreshing = false,
            showUpdateDeliveryStatusDialog = Event.empty(),
            isDataReset = Event.empty()
        )

    fun showUpdateDeliveryStatusDialog(item: DeliveryUIModel) {
        postAction(HomeAction.ShowUpdateDeliveryStatusDialog(item))
    }

    fun hideUpdateDeliveryStatusDialog(status: Status, item: DeliveryUIModel) {
        postAction(HomeAction.HideUpdateDeliveryStatusDialog(status, item))
    }

    fun dismissDialog() {
        postAction(HomeAction.DismissDialog)
    }

    fun resetValues() {
        postAction(HomeAction.ResetValues)
    }

    override fun stateReducer(): (HomeState, HomeResult) -> HomeState =
        { prevState, result ->
            when (result) {
                is HomeResult.DeliveriesLoaded -> prevState.copy(
                    isDataReset = Event.empty(),
                    deliveries = result.items.map { it.toMapUIModel() })

                is HomeResult.ShowUpdateDeliveryStatusDialog -> prevState.copy(
                    showUpdateDeliveryStatusDialog = Event(result.item)
                )

                is HomeResult.HideUpdateDeliveryStatusDialog -> prevState.copy(
                    showUpdateDeliveryStatusDialog = Event.empty()
                )

                is HomeResult.ResetValuesSuccess -> prevState.copy(
                    isDataReset = Event(true)
                )
                is HomeResult.DismissDialogSuccess -> prevState.copy(
                    showUpdateDeliveryStatusDialog = Event.empty(),
                    isDataReset = Event.empty()
                )
            }
        }
}
