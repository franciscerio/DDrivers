package com.fcerio.ddrivers.components.delivery

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.fcerio.common.compose.AppTheme
import com.fcerio.common.compose.components.CardComposable
import com.fcerio.common.compose.components.TextComposable
import com.fcerio.common.compose.dimensions.Margins
import com.fcerio.ddrivers.components.delivery.models.DeliveryUIModel
import com.fcerio.domain.Delivery
import com.fcerio.domain.Status

class DeliveryComposablePreviewParameterProvider : PreviewParameterProvider<DeliveryUIModel> {

    override val values: Sequence<DeliveryUIModel> = sequenceOf(
        DeliveryUIModel(
            id = 1,
            destinationAddress = "{{ Test Destination }}",
            distance = "{{ 50 KM }}",
            name = "{{ Test Name }}",
            description = " {{ Test Description }}",
            status = Status.ON_GOING,
            imageURL = "https://images.unsplash.com/photo-1498060059232-54fd57716ac6?q=80&w=2200&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            readableTimeArrival = "500 ago",
            isStatusVisible = true
        )
    )
}

@Composable
@Preview(showBackground = true)
fun DeliveryComposable(
    @PreviewParameter(DeliveryComposablePreviewParameterProvider::class) model: DeliveryUIModel,
    onClicked: ((DeliveryUIModel) -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Margins.MICRO)
            .clickable {
                onClicked?.invoke(model)
            }
    ) {
        CardComposable(
            modifier = Modifier
                .wrapContentSize()
        ) {
            Box {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Image(
                        rememberAsyncImagePainter(model.imageURL),
                        contentDescription = "Hero Image",
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        TextComposable(
                            modifier = Modifier.padding(
                                start = Margins.MICRO,
                                end = Margins.MICRO,
                                top = Margins.MICRO
                            ),
                            style = AppTheme.typography.body1Bold,
                            title = model.name,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )

                        Row(
                            modifier = Modifier.padding(
                                start = Margins.MICRO,
                                end = Margins.MICRO,
                                top = Margins.MICRO
                            ),
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            TextComposable(
                                style = AppTheme.typography.subHeadline,
                                title = model.readableTimeArrival
                            )

                            TextComposable(
                                style = AppTheme.typography.subHeadline,
                                title = model.distance
                            )
                        }

                        TextComposable(
                            modifier = Modifier.padding(
                                start = Margins.MICRO,
                                end = Margins.MICRO,
                                top = Margins.MICRO
                            ),
                            style = AppTheme.typography.body1,
                            title = model.description,
                            textColor = AppTheme.colors.textTertiary
                        )
                    }
                }

                if (model.isStatusVisible) {
                    TextComposable(
                        modifier = Modifier
                            .padding(
                                start = Margins.MEDIUM,
                                end = Margins.MEDIUM,
                                top = Margins.MEDIUM
                            )
                            .align(Alignment.TopEnd),
                        style = AppTheme.typography.body1Bold,
                        title = model.status.name
                    )
                }
            }
        }
    }
}
