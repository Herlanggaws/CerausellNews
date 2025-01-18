package com.herlangga.news.domain.usecase

import com.herlangga.news.data.NewsRepository
import com.herlangga.news.domain.model.News
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Designed and developed by Herlangga Wicaksono on 18/01/25.
 * @LinkedIn (https://www.linkedin.com/in/herlangga-wicaksono-4072a5a2/)
 */
@Singleton
class NewsUseCase @Inject constructor(private val repo: NewsRepository) {
	suspend operator fun invoke(): List<News> {
		return repo.getNews().map(::News)
	}
}