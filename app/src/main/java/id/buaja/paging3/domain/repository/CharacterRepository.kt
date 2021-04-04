package id.buaja.paging3.domain.repository

import androidx.paging.PagingData
import id.buaja.paging3.domain.model.ResultsItem
import kotlinx.coroutines.flow.Flow

/**
 * Created by Julsapargi Nursam on 4/4/21.
 */

interface CharacterRepository {
    fun getCharacter(): Flow<PagingData<ResultsItem>>
}