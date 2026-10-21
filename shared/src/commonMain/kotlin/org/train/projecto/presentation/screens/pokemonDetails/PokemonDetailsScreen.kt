package org.train.projecto.presentation.screens.pokemonDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.train.projecto.domain.models.PokemonDetails
import org.train.projecto.presentation.uiStates.PokemonDetailsUiState
import org.train.projecto.presentation.localization.LocalStrings

private const val MAX_STAT_VALUE = 255f

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailsScreen(
    uiState: PokemonDetailsUiState,
    onBack: () -> Unit,
    onToggleCollection: () -> Unit,
    modifier: Modifier = Modifier
) {
    val strings = LocalStrings.current
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = strings.back
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (uiState) {
                is PokemonDetailsUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                is PokemonDetailsUiState.Error -> {
                    Text(
                        text = uiState.message,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(24.dp)
                    )
                }

                is PokemonDetailsUiState.Success -> {
                    PokemonDetailsContent(
                        details = uiState.pokemonDetails,
                        isInCollection = uiState.isInCollection,
                        onToggleCollection = onToggleCollection
                    )
                }
            }
        }
    }
}

@Composable
private fun PokemonDetailsContent(
    details: PokemonDetails,
    isInCollection: Boolean,
    onToggleCollection: () -> Unit
) {
    val strings = LocalStrings.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Name
        Text(
            text = details.name.replaceFirstChar { it.uppercase() },
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(16.dp))

        // Image
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.size(180.dp)
        ) {
            AsyncImage(
                model = details.sprites,
                contentDescription = details.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
        }

        Spacer(Modifier.height(16.dp))

        // Number
        Text(
            text = strings.pokemonNumber(details.id),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(Modifier.height(12.dp))

        // Types
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            details.types.forEach { type ->
                SuggestionChip(
                    onClick = {},
                    label = { Text(type.replaceFirstChar { it.uppercase() }) }
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        // Collection button
        Button(
            onClick = onToggleCollection,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 14.dp)
        ) {
            Text(if (isInCollection) strings.removeFromCollection else strings.addToCollection)
        }

        Spacer(Modifier.height(24.dp))

        // Body sections (left-aligned)
        Column(modifier = Modifier.fillMaxWidth()) {
            SectionTitle(strings.about)
            InfoRow(label = strings.height, value = strings.heightValue(details.heightM))
            InfoRow(label = strings.weight, value = strings.weightValue(details.weightKg))

            Spacer(Modifier.height(20.dp))

            SectionTitle(strings.abilities)
            details.abilities.forEach { ability ->
                Text(
                    text = "• ${ability.replaceFirstChar { it.uppercase() }}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
            }

            Spacer(Modifier.height(20.dp))

            SectionTitle(strings.baseStats)
            details.stats.forEach { stat ->
                StatRow(name = stat.stat, value = stat.baseStat)
            }

            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.width(110.dp)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
private fun StatRow(name: String, value: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name.replaceFirstChar { it.uppercase() },
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.width(80.dp)
        )
        LinearProgressIndicator(
            progress = { (value / MAX_STAT_VALUE).coerceIn(0f, 1f) },
            modifier = Modifier
                .weight(1f)
                .height(10.dp)
                .clip(RoundedCornerShape(5.dp))
        )
        Text(
            text = value.toString(),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.End,
            modifier = Modifier.width(44.dp)
        )
    }
}
