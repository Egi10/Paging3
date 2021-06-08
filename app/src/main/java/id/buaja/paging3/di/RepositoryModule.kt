package id.buaja.paging3.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.buaja.paging3.data.repository.CharacterRepositoryImpl
import id.buaja.paging3.domain.repository.CharacterRepository
import javax.inject.Singleton

/**
 * Created by Julsapargi Nursam on 4/4/21.
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindMainRepository(
        characterRepositoryImpl: CharacterRepositoryImpl
    ): CharacterRepository
}