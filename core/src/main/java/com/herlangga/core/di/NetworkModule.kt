package com.herlangga.core.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.herlangga.core.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Designed and developed by Herlangga Wicaksono on 18/01/25.
 * @LinkedIn (https://www.linkedin.com/in/herlangga-wicaksono-4072a5a2/)
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
	@Provides
	fun provideDefaultGson(): Gson {
		return GsonBuilder().create()
	}

	@Provides
	@Singleton
	fun provideChuckerInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
		return ChuckerInterceptor.Builder(context)
			.apply {
				collector(
					ChuckerCollector(
						context = context,
						showNotification = BuildConfig.DEBUG,
						retentionPeriod = RetentionManager.Period.ONE_DAY
					)
				)
				maxContentLength(250_000L)
				alwaysReadResponseBody(false)
				if (!BuildConfig.DEBUG) {
					redactHeaders("Authorization", "Bearer")
					redactHeaders("Authorization", "Basic")
				}
			}
			.build()
	}

	@Provides
	@Singleton
	fun provideOkHttpClient(
		chuckerInterceptor: ChuckerInterceptor,
	): OkHttpClient {
		return if (BuildConfig.DEBUG) {
			OkHttpClient.Builder()
				.addInterceptor(chuckerInterceptor)
				.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
				.connectTimeout(120, TimeUnit.SECONDS)
				.readTimeout(120, TimeUnit.SECONDS)
				.build()
		} else {
			OkHttpClient.Builder()
				.connectTimeout(120, TimeUnit.SECONDS)
				.readTimeout(120, TimeUnit.SECONDS)
				.build()
		}
	}
}