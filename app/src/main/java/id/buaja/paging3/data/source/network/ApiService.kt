package id.buaja.paging3.data.source.network

import id.buaja.paging3.data.source.response.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Julsapargi Nursam on 4/4/21.
 */

interface ApiService {
    @GET("character")
    suspend fun getCharacter(
        @Query("page") page: Int
    ): CharacterResponse
}