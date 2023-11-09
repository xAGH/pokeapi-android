package xagh.eam.pokeapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import xagh.eam.pokeapi.ui.screens.PokemonApiScreen
import xagh.eam.pokeapi.ui.theme.PokeApiTheme
import xagh.eam.pokeapi.ui.viewmodels.PokemonApiViewModel

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<PokemonApiViewModel>()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokeApiTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(title = { Text("Pokemon Api") })
                    }
                ) {
                    PokemonApiScreen(it, viewModel)
                }
            }
        }
    }
}