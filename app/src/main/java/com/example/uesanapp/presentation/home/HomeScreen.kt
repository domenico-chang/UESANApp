package com.example.uesanapp.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.uesanapp.data.local.CountryEntity
import com.example.uesanapp.data.repository.LocalRepository
import kotlinx.coroutines.launch

val initialCountries = listOf(
    CountryEntity("Colombia", 1, "https://flagcdn.com/w320/co.png", false),
    CountryEntity("Francia", 2, "https://flagcdn.com/w320/fr.png", false),
    CountryEntity("Brasil", 3, "https://flagcdn.com/w320/br.png", false),
    CountryEntity("España", 4, "https://flagcdn.com/w320/es.png", false),
    CountryEntity("Portugal", 5, "https://flagcdn.com/w320/pt.png", false),
    CountryEntity("Argentina", 6, "https://flagcdn.com/w320/ar.png", false),
    CountryEntity("Japón", 7, "https://flagcdn.com/w320/jp.png", false),
    CountryEntity("Perú", 8, "https://flagcdn.com/w320/pe.png", false)
)

@Composable
fun HomeScreen(repository: LocalRepository) {
    val countries by repository.getCountries().collectAsState(initial = emptyList())
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        repository.initializeCountries(initialCountries)
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Ranking FIFA 2026",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(countries) { country ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            contentDescription = country.name,
                            modifier = Modifier.size(64.dp),
                            contentScale = ContentScale.Crop,
                            painter = rememberAsyncImagePainter(country.imageUrl)
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                country.name,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text("Ranking FIFA 2026: ${country.ranking}")
                        }

                        IconButton(
                            onClick = {
                                scope.launch {
                                    repository.updateFavorite(
                                        country.name,
                                        !country.isFavorite
                                    )
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if (country.isFavorite) {
                                    Icons.Filled.Star
                                } else {
                                    Icons.Outlined.StarBorder
                                },
                                contentDescription = "Favorito"
                            )
                        }
                    }
                }
            }
        }
    }
}