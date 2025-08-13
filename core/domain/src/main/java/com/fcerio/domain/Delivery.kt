package com.fcerio.domain

data class Delivery(
    val id: Long,
    val destinationAddress: String,
    val name: String,
    val description: String,
    val distance: Double,
    val status: Status,
    val imageURL: String,
    // in seconds
    val timeArrival: Long
)

enum class Status {
    COMPLETED, SKIPPED, ON_GOING
}