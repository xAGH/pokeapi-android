package xagh.eam.pokeapi.services
import retrofit2.http.GET
import retrofit2.http.Query
import xagh.eam.pokeapi.domain.PokemonApiResponse

interface PokemonApiService {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset: Long = 10,
        @Query("limit") limit: Int = 10
    ): PokemonApiResponse
}
