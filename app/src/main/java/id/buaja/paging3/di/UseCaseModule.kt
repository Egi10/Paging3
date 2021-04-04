package id.buaja.paging3.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import id.buaja.paging3.domain.repository.CharacterRepository
import id.buaja.paging3.domain.usecase.CharacterUseCase
import id.buaja.paging3.domain.usecase.CharacterUseCaseImpl

/**
 * Created by Julsapargi Nursam on 4/4/21.
 */

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    @ViewModelScoped
    fun provideUseCase(
        repository: CharacterRepository
    ): CharacterUseCase {
        return CharacterUseCaseImpl(repository)
    }
}