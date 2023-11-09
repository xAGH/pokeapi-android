package xagh.eam.pokeapi.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        private const val BASE_URL = "https://pokeapi.co/api/v2/"
        private var apiService: PokemonApiService? = null

        val api: PokemonApiService
            get() {
                if (apiService == null) {
                    apiService = Retrofit.Builder().baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create()).build()
                        .create(PokemonApiService::class.java)
                }
                return apiService!!
            }
    }
}
