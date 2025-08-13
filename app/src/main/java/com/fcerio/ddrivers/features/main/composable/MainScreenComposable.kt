package com.fcerio.ddrivers.features.main.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument
import com.fcerio.common.compose.AppTheme
import com.fcerio.ddrivers.components.navgraph.composableWithCompositionLocal
import com.fcerio.ddrivers.components.navigation.HomeSections
import com.fcerio.ddrivers.components.navigation.MainDestinations
import com.fcerio.ddrivers.components.navigation.rememberAppNavController

@Composable
fun MainScreenComposable() {
    val navController = rememberAppNavController()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = AppTheme.colors.neutralsBackground
    ) {
        NavHost(
            navController = navController.navController,
            startDestination = HomeSections.HOME.route
        ) {
            composableWithCompositionLocal(
                route = HomeSections.HOME.route
            ) { _ ->
                MainContainerComposable(navController = navController)
            }

            composableWithCompositionLocal(
                "${MainDestinations.DELIVERY_DETAIL_ROUTE}/" +
                        "{${MainDestinations.DELIVERY_ID_KEY}}",
                arguments = listOf(
                    navArgument(MainDestinations.DELIVERY_ID_KEY) {
                        type = NavType.LongType
                    }
                )
            ) { backStackEntry ->
                val arguments = requireNotNull(backStackEntry.arguments)
                val trackId = arguments.getLong(MainDestinations.DELIVERY_ID_KEY)
//                    TrackDetailScreen(
//                        trackId = { trackId },
//                        upPress = navController::upPress
//                    )
            }
        }
    }
}
