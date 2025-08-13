package com.fcerio.network

import com.fcerio.domain.Delivery
import com.fcerio.domain.Status

class NetworkData {
    companion object {
        val DELIVERIES = listOf<Delivery>(
            Delivery(
                id = 1,
                destinationAddress = "Cebu City",
                name = "Chair",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus laoreet hendrerit tellus, auctor laoreet nisi consectetur vitae. Phasellus ultricies justo quis dolor tincidunt finibus.",
                distance = 15.0,
                status = Status.ON_GOING,
                imageURL = "https://images.unsplash.com/photo-1498060059232-54fd57716ac6?q=80&w=2200&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                timeArrival = 600 // 10 minutes
            ),
            Delivery(
                id = 2,
                destinationAddress = "Cebu City 2",
                distance = 5.0,
                name = "Chair 123",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus laoreet hendrerit tellus, auctor laoreet nisi consectetur vitae. Phasellus ultricies justo quis dolor tincidunt finibus.",
                status = Status.ON_GOING,
                imageURL = "https://images.unsplash.com/photo-1498060059232-54fd57716ac6?q=80&w=2200&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                timeArrival = 300 // 5 minutes
            ),
            Delivery(
                id = 3,
                destinationAddress = "Cebu City 3",
                distance = 25.0,
                name = "Chair 555",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus laoreet hendrerit tellus, auctor laoreet nisi consectetur vitae. Phasellus ultricies justo quis dolor tincidunt finibus.",
                status = Status.ON_GOING,
                imageURL = "https://images.unsplash.com/photo-1498060059232-54fd57716ac6?q=80&w=2200&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                timeArrival = 900 // 15 minutes
            )
        )
    }
}