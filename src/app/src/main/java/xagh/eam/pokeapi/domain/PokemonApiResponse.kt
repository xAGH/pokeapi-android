package xagh.eam.pokeapi.domain

data class PokemonApiResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon>
)
