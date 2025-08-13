package com.fcerio.ddrivers.features.home

import com.fcerio.common.android.base.Event
import com.fcerio.common.android.base.UiStateWithId
import com.fcerio.common.android.base.UiStateWithIdDelegate
import com.fcerio.ddrivers.components.delivery.models.DeliveryUIModel
import com.fcerio.domain.Delivery
import com.fcerio.domain.Status


data class HomeState(
    val deliveries: List<DeliveryUIModel>,
    val isRefreshing: Boolean,
    val showUpdateDeliveryStatusDialog: Event<DeliveryUIModel>,
    val isDataReset: Event<Boolean>
) : UiStateWithId by UiStateWithIdDelegate()

sealed interface HomeAction {
    data object LoadToDeliveries : HomeAction
    data object ResetValues : HomeAction
    data class ShowUpdateDeliveryStatusDialog(val item: DeliveryUIModel) : HomeAction
    data class HideUpdateDeliveryStatusDialog(val status: Status, val item: DeliveryUIModel) :
        HomeAction
    data object DismissDialog : HomeAction
}

sealed interface HomeResult {
    data class DeliveriesLoaded(val items: List<Delivery>) : HomeResult
    data class ShowUpdateDeliveryStatusDialog(val item: DeliveryUIModel) : HomeResult
    data object HideUpdateDeliveryStatusDialog : HomeResult
    data object ResetValuesSuccess : HomeResult
    data object DismissDialogSuccess : HomeResult
}
