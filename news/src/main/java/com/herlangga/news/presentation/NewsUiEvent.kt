package com.herlangga.news.presentation

import com.herlangga.news.domain.model.News
import com.herlangga.news.domain.model.SortType

/**
 * Designed and developed by Herlangga Wicaksono on 18/01/25.
 * @LinkedIn (https://www.linkedin.com/in/herlangga-wicaksono-4072a5a2/)
 */
sealed class NewsUiEvent {
	data object OnFetchNews : NewsUiEvent()
	class OnNewsClick(val news: News) : NewsUiEvent()
	data object OnSortOptionClicked : NewsUiEvent()
	data object OnSortOptionDismiss : NewsUiEvent()
	class OnSortSelected(val type: SortType) : NewsUiEvent()
}