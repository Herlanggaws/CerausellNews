package com.herlangga.news.data.remote

import com.herlangga.news.data.model.NewsResponse
import retrofit2.http.GET

/**
 * Designed and developed by Herlangga Wicaksono on 18/01/25.
 * @LinkedIn (https://www.linkedin.com/in/herlangga-wicaksono-4072a5a2/)
 */
interface NewsService {
	@GET("carousell_news.json")
	suspend fun getNews(): List<NewsResponse>
}