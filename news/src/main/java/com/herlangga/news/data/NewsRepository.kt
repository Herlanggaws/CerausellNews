package com.herlangga.news.data

import com.herlangga.news.data.remote.NewsService
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Designed and developed by Herlangga Wicaksono on 18/01/25.
 * @LinkedIn (https://www.linkedin.com/in/herlangga-wicaksono-4072a5a2/)
 */
@Singleton
class NewsRepository @Inject constructor(
	private val apiService: NewsService
) {
	suspend fun getNews() = apiService.getNews()
}