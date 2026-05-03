package com.example.notesappproj.ui.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNavigate: () -> Unit) {

    LaunchedEffect(Unit) {
        delay(1800)
        onNavigate()
    }

    val infiniteTransition = rememberInfiniteTransition(label = "splash_pulse")
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(900, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alpha_pulse"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF0D0D1A), Color(0xFF1A1A2E))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "📝",
                fontSize = 64.sp,
                modifier = Modifier.alpha(alpha)
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Notes",
                fontSize = 42.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF9B6DFF)
            )
            Text(
                text = "Your thoughts, beautifully organized.",
                fontSize = 14.sp,
                color = Color(0xFF8888AA)
            )
            Spacer(Modifier.height(40.dp))
            Text(
                text = "Chris Harris Edmond",
                fontSize = 22.sp,
//                fontWeight = FontWeight.Bold,
                color = Color(0xFF9B6DFF)
            )
            Text(
                text = "12321270",
                fontSize = 14.sp,
                color = Color(0xFF8888AA)
            )
        }
    }
}
