package com.herlangga.news.presentation

import com.herlangga.core.data.ViewState
import com.herlangga.news.domain.model.News

/**
 * Designed and developed by Herlangga Wicaksono on 18/01/25.
 * @LinkedIn (https://www.linkedin.com/in/herlangga-wicaksono-4072a5a2/)
 */
data class NewsUiState(
	val news: List<News> = emptyList(),
	val viewState: ViewState = ViewState.Loading
)