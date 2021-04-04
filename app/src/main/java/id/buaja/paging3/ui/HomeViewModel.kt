package id.buaja.paging3.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import id.buaja.paging3.domain.model.ResultsItem
import id.buaja.paging3.domain.usecase.CharacterUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Julsapargi Nursam on 4/4/21.
 */

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCase: CharacterUseCase) : ViewModel() {
    fun getCharacter(): Flow<PagingData<ResultsItem>> {
        return useCase
            .getCharacter()
            .cachedIn(viewModelScope)
    }
}