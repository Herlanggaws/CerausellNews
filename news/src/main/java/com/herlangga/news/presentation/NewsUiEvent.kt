package com.herlangga.news.presentation

/**
 * Designed and developed by Herlangga Wicaksono on 18/01/25.
 * @LinkedIn (https://www.linkedin.com/in/herlangga-wicaksono-4072a5a2/)
 */
sealed class NewsUiEvent {
	data object OnFetchNews : NewsUiEvent()
}