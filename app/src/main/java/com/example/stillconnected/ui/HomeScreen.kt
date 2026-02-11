package com.example.stillconnected.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stillconnected.R
import com.example.stillconnected.ui.theme.StackSansHeadline

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    //connectivityState: ConnectivityState
) {
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
                painter = painterResource(R.drawable.no_connection),
                contentDescription = null,
                modifier = Modifier.size(300.dp),
                contentScale = ContentScale.Fit
            )

        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.airplane_mode_message),
            fontFamily = StackSansHeadline,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            color = colorResource(id = R.color.text_primary),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 32.dp)
        )
    }
}