package xagh.eam.pokeapi.ui.components

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.palette.graphics.Palette
import coil.ImageLoader
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import coil.request.SuccessResult
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun PokemonImage(imageUrl: String) {
    var dominantColor by remember { mutableStateOf(Color.White) }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var loaded by remember { mutableStateOf(false) }
    var bitmap: Bitmap? by remember { mutableStateOf(null) }

    coroutineScope.launch {
        val loader = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(imageUrl)
            .allowHardware(false)
            .build()
        val result = (loader.execute(request) as SuccessResult).drawable
        bitmap = (result as BitmapDrawable).bitmap
        loaded = true
    }

    Box(
        modifier = Modifier
            .size(200.dp)
            .clip(RoundedCornerShape(32.dp))
            .background(dominantColor)
    ) {
        if (loaded) {
            val palette = Palette.Builder(bitmap!!).generate()
            val dominantSwatch = palette.dominantSwatch
            dominantColor = dominantSwatch?.rgb?.let { Color(it) } ?: Color.White
            SubcomposeAsyncImage(
                model = imageUrl,
                contentDescription = null,
                loading = { CircularProgressIndicator() },
                modifier = Modifier.fillMaxSize()
            )
        } else {
            CircularProgressIndicator()
        }

    }

}