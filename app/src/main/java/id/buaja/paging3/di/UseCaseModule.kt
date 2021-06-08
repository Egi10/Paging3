package id.buaja.paging3.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import id.buaja.paging3.domain.usecase.CharacterUseCase
import id.buaja.paging3.domain.usecase.CharacterUseCaseImpl

/**
 * Created by Julsapargi Nursam on 4/4/21.
 */

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {
    @Binds
    @ViewModelScoped
    abstract fun bindUseCase(
        characterUseCaseImpl: CharacterUseCaseImpl
    ): CharacterUseCase
}