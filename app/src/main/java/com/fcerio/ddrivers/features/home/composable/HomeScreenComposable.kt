package com.fcerio.ddrivers.features.home.composable

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fcerio.common.compose.AppTheme
import com.fcerio.common.compose.components.TitleBarComposable
import com.fcerio.common.compose.components.dialog.DeliveryStatusDialog
import com.fcerio.common.compose.dimensions.Margins
import com.fcerio.ddrivers.R
import com.fcerio.ddrivers.components.delivery.DeliveryComposable
import com.fcerio.ddrivers.features.home.HomeViewModel
import com.fcerio.domain.Status

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenComposable(
    modifier: Modifier
) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    uiState.showUpdateDeliveryStatusDialog.get(uiState)?.let {
        DeliveryStatusDialog(
            body = it.name,
            onConfirmation = {
                viewModel.hideUpdateDeliveryStatusDialog(status = Status.COMPLETED, it)
            },
            onDismissRequest = {
                viewModel.hideUpdateDeliveryStatusDialog(status = Status.SKIPPED, it)
            },
            onDismissDialog = {
                viewModel.dismissDialog()
            }
        )
    }

    uiState.isDataReset.get(uiState)?.let {
        Toast.makeText(context, "Reset data successfully!", Toast.LENGTH_SHORT).show()
    }

    val interactionSource = remember { MutableInteractionSource() }

    Surface(modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.neutralsBackground)
        ) {
            // Modifier the title bar, TopAppBar to it will check the StatusBar height
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AppTheme.colors.neutralsBackground,
                    titleContentColor = AppTheme.colors.primaryDefault,
                ),
                title = {
                    TitleBarComposable(
                        modifier = Modifier
                            .wrapContentWidth()
                            .clickable(
                                onClick = {
                                    viewModel.resetValues()
                                },
                                interactionSource = interactionSource,
                                indication = null
                            ),
                        title = stringResource(R.string.home)
                    )
                }
            )
            LazyColumn(
                contentPadding = PaddingValues(
                    start = Margins.LARGE,
                    end = Margins.LARGE,
                    top = Margins.SMALL,
                    bottom = Margins.SMALL
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 1.dp),
                content = {
                    items(items = uiState.deliveries) {
                        DeliveryComposable(
                            model = it,
                            onClicked = {
                                viewModel.showUpdateDeliveryStatusDialog(it)
                            }
                        )
                    }
                }
            )
        }
    }
}
