package xagh.eam.pokeapi.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import xagh.eam.pokeapi.R
import xagh.eam.pokeapi.ui.components.PokemonCard
import xagh.eam.pokeapi.ui.viewmodels.PokemonApiViewModel
import xagh.eam.pokeapi.ui.viewmodels.STATE

@Composable
fun PokemonApiScreen(
    paddingValues: PaddingValues, viewModel: PokemonApiViewModel
) {
    val pokemonList = viewModel.pokemonApiResponse.observeAsState(initial = listOf())
    val scrollState = rememberLazyGridState()
    var loadedAtEnd by remember { mutableStateOf(false) }
    val isItemAtTheEnd by remember {
        derivedStateOf {
            scrollState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == scrollState.layoutInfo.totalItemsCount - 1
        }
    }

    if (pokemonList.value.isEmpty() && viewModel.state != STATE.LOADING) {
        viewModel.getPokemon()
    }

    LaunchedEffect(isItemAtTheEnd) {
        if (isItemAtTheEnd && !loadedAtEnd && viewModel.state != STATE.LOADING) {
            loadedAtEnd = true
            viewModel.getPokemon()
            loadedAtEnd = false
        }
    }

    if (viewModel.state == STATE.LOADING) {
        Dialog(onDismissRequest = { }) {
            CircularProgressIndicator()
        }
    }

    if (viewModel.state == STATE.ERROR) {
        val context = LocalContext.current
        Toast.makeText(
            context, stringResource(id = R.string.fetching_error_message), Toast.LENGTH_LONG
        ).show()
    }

    LazyVerticalGrid(
        state = scrollState,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp),
        columns = GridCells.Fixed(2)
    ) {
        itemsIndexed(pokemonList.value) { index, pokemon -> PokemonCard(pokemon, index) }

    }
}