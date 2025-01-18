package com.herlangga.news.di

import com.herlangga.news.data.remote.NewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Designed and developed by Herlangga Wicaksono on 18/01/25.
 * @LinkedIn (https://www.linkedin.com/in/herlangga-wicaksono-4072a5a2/)
 */
@InstallIn(SingletonComponent::class)
@Module
class NewsServiceModule {
	@Provides
	@Singleton
	fun provideNewsWebApi(retrofit: Retrofit): NewsService =
		retrofit.create(NewsService::class.java)
}