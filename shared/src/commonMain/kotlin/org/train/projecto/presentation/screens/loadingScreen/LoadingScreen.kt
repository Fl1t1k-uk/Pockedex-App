package org.train.projecto.presentation.screens.loadingScreen
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoadingScreen() {
    val infiniteTransition = rememberInfiniteTransition()

    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1800,
                easing = LinearEasing
            )
        )
    )

    val infiniteProgress by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 900,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Poké Ball
        Box(
            modifier = Modifier
                .size(100.dp)
                .rotate(rotation),
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier.fillMaxSize()
            ) {
                val radius = size.minDimension / 2
                val center = Offset(
                    x = size.width / 2,
                    y = size.height / 2
                )

                // Верхня половина
                drawArc(
                    color = Color(0xFFE53935),
                    startAngle = 180f,
                    sweepAngle = 180f,
                    useCenter = true,
                    size = Size(radius * 2, radius * 2),
                    topLeft = Offset(
                        center.x - radius,
                        center.y - radius
                    )
                )

                // Нижня половина
                drawArc(
                    color = Color.White,
                    startAngle = 0f,
                    sweepAngle = 180f,
                    useCenter = true,
                    size = Size(radius * 2, radius * 2),
                    topLeft = Offset(
                        center.x - radius,
                        center.y - radius
                    )
                )

                // Центральна лінія
                drawLine(
                    color = Color.Black,
                    start = Offset(center.x - radius, center.y),
                    end = Offset(center.x + radius, center.y),
                    strokeWidth = 7.dp.toPx()
                )

                // Зовнішній контур
                drawCircle(
                    color = Color.Black,
                    radius = radius,
                    center = center,
                    style = Stroke(width = 4.dp.toPx())
                )

                // Центральна кнопка
                drawCircle(
                    color = Color.Black,
                    radius = radius * 0.24f,
                    center = center
                )

                drawCircle(
                    color = Color.White,
                    radius = radius * 0.15f,
                    center = center
                )
            }
        }

        Spacer(modifier = Modifier.height(36.dp))

        Text(
            text = "POCKETDEX",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 4.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Preparing your adventure...",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
        )

        Spacer(modifier = Modifier.height(32.dp))

        LinearProgressIndicator(
            progress = { infiniteProgress },
            modifier = Modifier
                .width(180.dp)
                .height(6.dp)
                .clip(CircleShape),
            color = Color(0xFFE53935),
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Gotta catch 'em all!",
            style = MaterialTheme.typography.bodyMedium,
            fontStyle = FontStyle.Italic,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
        )
    }
}