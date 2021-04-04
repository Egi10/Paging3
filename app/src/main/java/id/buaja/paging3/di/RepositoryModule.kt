package id.buaja.paging3.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.buaja.paging3.data.repository.CharacterRepositoryImpl
import id.buaja.paging3.data.source.RemoteDataSource
import id.buaja.paging3.domain.repository.CharacterRepository
import javax.inject.Singleton

/**
 * Created by Julsapargi Nursam on 4/4/21.
 */

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideMainRepository(dataSource: RemoteDataSource): CharacterRepository {
        return CharacterRepositoryImpl(dataSource)
    }
}