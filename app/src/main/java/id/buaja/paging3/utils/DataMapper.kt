package id.buaja.paging3.utils

import id.buaja.paging3.data.source.response.CharacterResponse
import id.buaja.paging3.domain.model.Character
import id.buaja.paging3.domain.model.Info
import id.buaja.paging3.domain.model.ResultsItem

/**
 * Created by Julsapargi Nursam on 4/4/21.
 */

object DataMapper {
    fun mapResponseToDomain(characterResponse: CharacterResponse): Character {
        val resultsItem: MutableList<ResultsItem> = mutableListOf()
        characterResponse.results?.let { list ->
            list.map {
                val results = ResultsItem(
                    id = it.id ?: 0,
                    image = it.image ?: "",
                    gender = it.gender ?: "",
                    name = it.name ?: "",
                    status = it.status ?: ""
                )
                resultsItem.add(results)
            }
        }
        return Character(
            results = resultsItem,
            info = Info(
                pages = characterResponse.info?.pages,
                count = characterResponse.info?.count
            )
        )
    }
}