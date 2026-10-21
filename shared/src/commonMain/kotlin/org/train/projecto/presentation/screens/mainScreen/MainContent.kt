package org.train.projecto.presentation.screens.mainScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.train.projecto.presentation.uiStates.MainUiState

@Composable
fun MainContent(
    state: MainUiState.Success,
    onSearchQueryChanged: (String) -> Unit,
    onOpenCollection: () -> Unit,
    onOpenPokemon: (Int) -> Unit,
    onOpenPokedex: () -> Unit,
    onOpenSettings: () -> Unit
) {
    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = true,
                    onClick = {},
                    icon = { Text("🏠") },
                    label = { Text("Home") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = onOpenPokedex,
                    icon = { Text("🔍") },
                    label = { Text("Pokédex") }
                )

            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Hello, ${state.nickname}",
                    style = MaterialTheme.typography.headlineSmall
                )

                TextButton(onClick = onOpenSettings) {
                    Text("👤")
                }
            }

            Spacer(Modifier.height(24.dp))

            OutlinedTextField(
                value = state.searchQuery,
                onValueChange = onSearchQueryChanged,
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                placeholder = { Text("Search Pokémon...") },
                leadingIcon = { Text("🔍") }
            )

            Spacer(Modifier.height(24.dp))

            if (state.searchQuery.isNotBlank()) {
                Text(
                    text = "Search results",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(Modifier.height(12.dp))

                PokemonRow(
                    pokemons = state.searchResults,
                    onOpenPokemon = onOpenPokemon
                )
            } else {
                Text(
                    text = "Recently discovered",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(Modifier.height(12.dp))

                PokemonRow(
                    pokemons = state.recentlyDiscover,
                    onOpenPokemon = onOpenPokemon
                )
            }

            Spacer(Modifier.height(28.dp))

            Text(
                text = "My Collection",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(Modifier.height(12.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onOpenCollection)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "🎒 ${state.collectionCount} Pokémon",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(
                            text = "Your collection",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    Text("View all →")
                }
            }
        }
    }
}