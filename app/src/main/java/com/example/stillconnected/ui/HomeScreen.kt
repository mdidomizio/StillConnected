package com.example.stillconnected.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stillconnected.ConnectivityObserver
import com.example.stillconnected.R
import com.example.stillconnected.ui.theme.StackSansHeadline

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
) {
    val connectivityStatus by viewModel.connectivityStatus.collectAsState()

    val image: Painter = when (connectivityStatus) {
        ConnectivityObserver.Status.Lost -> painterResource(R.drawable.no_connection)
        ConnectivityObserver.Status.Available -> painterResource(R.drawable.connected)
        ConnectivityObserver.Status.AirplaneMode -> painterResource(R.drawable.flight_mode)
    }

    val text = when (connectivityStatus) {
        ConnectivityObserver.Status.Lost -> stringResource(R.string.not_connected_message)
        ConnectivityObserver.Status.Available -> stringResource(R.string.connected_message)
        ConnectivityObserver.Status.AirplaneMode -> stringResource(R.string.airplane_mode_message)
    }

    val baseColor = colorResource(id = R.color.surface)

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier.size(300.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(220.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                baseColor.copy(alpha = 1f),
                                baseColor.copy(alpha = 0.2f)
                            )
                        ),
                        shape = CircleShape
                    )
            )
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier.size(300.dp),
                contentScale = ContentScale.Fit
            )
        }

        Text(
            text = text,
            fontFamily = StackSansHeadline,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            color = colorResource(id = R.color.text_primary),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 32.dp)
        )
    }
}
