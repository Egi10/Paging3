package id.buaja.paging3.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.buaja.paging3.data.source.RemoteDataSource
import id.buaja.paging3.data.source.network.ApiService
import javax.inject.Singleton

/**
 * Created by Julsapargi Nursam on 4/4/21.
 */

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Singleton
    @Provides
    fun provideDataSource(apiService: ApiService): RemoteDataSource {
        return RemoteDataSource(apiService)
    }
}