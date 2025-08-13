package com.fcerio.common.compose.components.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.fcerio.common.compose.AppTheme
import com.fcerio.common.compose.components.CardComposable
import com.fcerio.common.compose.components.TextComposable

@Composable
fun DeliveryStatusDialog(
    body: String,
    onDismissDialog: () -> Unit,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
) {
    Dialog (onDismissRequest = { onDismissDialog() }) {
        // Draw a rectangle shape with rounded corners inside the dialog
        CardComposable(
            modifier = Modifier
                .height(250.dp)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                TextComposable(
                    title = "Status update of $body",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.headlineMedium,
                    maxLines = 2,
                    textAlign = TextAlign.Center,
                    textColor = AppTheme.colors.textTertiary,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {

                    TextButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        TextComposable(
                            title = "Complete",
                            style = MaterialTheme.typography.labelMedium,
                            textColor = AppTheme.colors.textTertiary
                        )
                    }
                    TextButton(
                        onClick = { onConfirmation() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        TextComposable(
                            title = "Skip",
                            style = MaterialTheme.typography.labelMedium,
                            textColor = AppTheme.colors.textTertiary
                        )
                    }
                }
            }
        }
    }
}
