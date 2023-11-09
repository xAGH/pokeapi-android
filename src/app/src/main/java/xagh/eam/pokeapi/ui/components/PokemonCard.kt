package xagh.eam.pokeapi.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import xagh.eam.pokeapi.domain.Pokemon

@Composable
fun PokemonCard(pokemon: Pokemon, index: Int) {
    Box {
        PokemonImage(imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${index + 1}.png")
        Text(
            text = pokemon.name.replaceFirstChar { it.titlecase() },
            color = Color.Black,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 20.sp,
                shadow = Shadow(
                    color = Color.White,
                    offset = Offset(2.0f, 2.0f),
                    blurRadius = 5f
                )
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )
    }
}