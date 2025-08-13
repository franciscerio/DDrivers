package com.fcerio.ddrivers.features.main.composable

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import com.fcerio.ddrivers.components.AppScaffold
import com.fcerio.ddrivers.components.AppSnackBar
import com.fcerio.ddrivers.components.navgraph.addHomeGraph
import com.fcerio.ddrivers.components.navigation.AppNavController
import com.fcerio.ddrivers.components.navigation.HomeSections
import com.fcerio.ddrivers.components.navigation.rememberAppNavController
import com.fcerio.ddrivers.components.rememberAppScaffoldState
import com.fcerio.ddrivers.features.main.MainViewModel

@Composable
fun MainContainerComposable(navController: AppNavController) {
    val appScaffoldState = rememberAppScaffoldState()
    val nestedNavController = rememberAppNavController()

    val viewModel = hiltViewModel<MainViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.navigateToSelectedTab) {
        uiState.navigateToSelectedTab.get(uiState)?.let {
            nestedNavController.navigateToBottomBarRoute(it.route)
        }
    }

    AppScaffold(
        bottomBar = {
            MainNavigationBarComposable(
                tabs = uiState.tabs,
                currentRoute = { uiState.selectedTab },
                navigateToRoute = {
                    viewModel.selectedTab(it)
                }
            )
        },
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(
                hostState = it,
                modifier = Modifier.systemBarsPadding(),
                snackbar = { snackbarData -> AppSnackBar(snackbarData) }
            )
        },
        snackBarHostState = appScaffoldState.snackBarHostState,
    ) { padding ->
        NavHost(
            navController = nestedNavController.navController,
            startDestination = HomeSections.HOME.route
        ) {
            addHomeGraph (
                modifier = Modifier
                    .padding(padding)
                    .consumeWindowInsets(padding),
                navController = { navController }
            )
        }
    }
}