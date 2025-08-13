package com.fcerio.ddrivers.components.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fcerio.ddrivers.R

enum class HomeSections(
    val title: String,
    val icon: Int,
    val route: String
) {

    HOME("Home", R.drawable.ic_menu_home, "home/explore"),
    STATUS("Status", R.drawable.ic_menu_heart, "home/status"),
}

object MainDestinations {

    const val DELIVERY_DETAIL_ROUTE = "delivery"
    const val DELIVERY_ID_KEY = "trackId"
}

@Composable
fun rememberAppNavController(
    navController: NavHostController = rememberNavController()
): AppNavController = remember(navController) {
    AppNavController(navController)
}

/**
 * Responsible for holding UI Navigation logic.
 */
@Stable
class AppNavController(
    val navController: NavHostController
) {

    fun upPress() {
        navController.navigateUp()
    }

    fun navigateToBottomBarRoute(route: String) {
        if (route != navController.currentDestination?.route) {
            navController.navigate(route) {
                launchSingleTop = true
                restoreState = true
                popUpTo(findStartDestination(navController.graph).id) {
                    saveState = true
                }
            }
        }
    }

    fun navigateToTrackDetail(deliveryID: Long, from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            navController.navigate("${MainDestinations.DELIVERY_DETAIL_ROUTE}/$deliveryID")
        }
    }
}

private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED

private val NavGraph.startDestination: NavDestination?
    get() = findNode(startDestinationId)

private tailrec fun findStartDestination(graph: NavDestination): NavDestination {
    return if (graph is NavGraph) findStartDestination(graph.startDestination!!) else graph
}
