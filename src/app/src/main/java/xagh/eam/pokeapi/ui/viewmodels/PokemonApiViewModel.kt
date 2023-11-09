package xagh.eam.pokeapi.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import xagh.eam.pokeapi.domain.Pokemon
import xagh.eam.pokeapi.services.RetrofitClient

enum class STATE {
    LOADING, SUCCESS, ERROR, INITIAL
}

class PokemonApiViewModel : ViewModel() {
    private val _pokemonApiResponse = MutableLiveData<List<Pokemon>>()
    val pokemonApiResponse: LiveData<List<Pokemon>> get() = _pokemonApiResponse
    private var offset: Long by mutableLongStateOf(0)
    private val limit = 10

    var state by mutableStateOf(STATE.INITIAL)

    fun getPokemon() {
        viewModelScope.launch {
            try {
                state = STATE.LOADING
                val response = RetrofitClient.api.getPokemonList(offset, limit)
                if (_pokemonApiResponse.value.isNullOrEmpty()) {
                    _pokemonApiResponse.value = response.results
                } else {
                    _pokemonApiResponse.value = _pokemonApiResponse.value!!.plus(response.results)
                }
                Log.i("Pokemones", _pokemonApiResponse.value?.size.toString())
                offset += 10
                state = STATE.SUCCESS
            } catch (e: Exception) {
                state = STATE.ERROR
            }
        }
    }

}