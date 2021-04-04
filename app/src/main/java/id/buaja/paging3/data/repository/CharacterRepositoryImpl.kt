package id.buaja.paging3.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import id.buaja.paging3.data.source.RemoteDataSource
import id.buaja.paging3.domain.model.ResultsItem
import id.buaja.paging3.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Julsapargi Nursam on 4/4/21.
 */

class CharacterRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    CharacterRepository {
    /**
     * Bisa Lihat Dokumentasi Penggunakan PagingConfig Pada
     * https://developer.android.com/reference/kotlin/androidx/paging/PagingConfig
     */
    override fun getCharacter(): Flow<PagingData<ResultsItem>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { remoteDataSource }
        ).flow
    }
}