package com.fcerio.ddrivers.components.navgraph

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.fcerio.ddrivers.components.navigation.AppNavController
import com.fcerio.ddrivers.components.navigation.HomeSections
import com.fcerio.ddrivers.features.home.composable.HomeScreenComposable

fun NavGraphBuilder.addHomeGraph(
    modifier: Modifier = Modifier,
    navController: () -> AppNavController
) {
    composable(HomeSections.HOME.route) { from ->
        // navController().navigateToTrackDetail(it, from)
        HomeScreenComposable(
            modifier
        )
    }
    composable(HomeSections.STATUS.route) { from ->
//        FavoritesScreen(
//            onTrackClickListener = {
//                navController().navigateToTrackDetail(it, from)
//            },
//            onTrackFavoriteClickListener = {
//            },
//            modifier
//        )
    }
}

fun NavGraphBuilder.composableWithCompositionLocal(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = route,
        arguments = arguments
    ) {
        content(it)
    }
}