package com.fcerio.ddrivers.components.delivery.models

import com.fcerio.domain.Delivery
import com.fcerio.domain.Status

data class DeliveryUIModel(
    val id: Long,
    val destinationAddress: String,
    val distance: String,
    val name: String,
    val description: String,
    val status: Status,
    val imageURL: String,
    val isStatusVisible: Boolean,
    val readableTimeArrival: String
)

fun Delivery.toMapUIModel(): DeliveryUIModel {
    return DeliveryUIModel(
        id = id,
        destinationAddress = destinationAddress,
        name = name,
        description = description,
        distance = "$distance KM",
        status = status,
        imageURL = imageURL,
        isStatusVisible = false,
        readableTimeArrival = "$timeArrival ago"
    )
}