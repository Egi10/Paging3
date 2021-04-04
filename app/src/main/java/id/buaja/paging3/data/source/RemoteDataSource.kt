package id.buaja.paging3.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import id.buaja.paging3.data.source.network.ApiService
import id.buaja.paging3.domain.model.ResultsItem
import id.buaja.paging3.utils.DataMapper
import javax.inject.Inject

/**
 * Created by Julsapargi Nursam on 4/4/21.
 */

class RemoteDataSource @Inject constructor(private val apiService: ApiService) :
    PagingSource<Int, ResultsItem>() {
    /**
     * Untuk mempelajari lebih lanjut tang refreshkey bisa langsung
     * https://developer.android.com/topic/libraries/architecture/paging/v3-migration?hl=id
     * https://developer.android.com/topic/libraries/architecture/paging/v3-paged-data
     */
    override fun getRefreshKey(state: PagingState<Int, ResultsItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultsItem> {
        /**
         * param.key pada saat reload pertama kali itu nilai defaultnya
         * adalah null
         */
        val position = params.key ?: 1

        return try {
            apiService.getCharacter(page = position).run {
                val data = DataMapper.mapResponseToDomain(this)

                LoadResult.Page(
                    data = data.results,
                    prevKey = if (position == 1) null else position - 1,
                    nextKey = if (position == this.info?.count) null else position + 1
                )
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}