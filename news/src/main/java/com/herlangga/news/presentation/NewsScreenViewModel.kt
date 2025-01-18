package com.herlangga.news.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.herlangga.news.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Designed and developed by Herlangga Wicaksono on 18/01/25.
 * @LinkedIn (https://www.linkedin.com/in/herlangga-wicaksono-4072a5a2/)
 */
@HiltViewModel
class NewsScreenViewModel @Inject constructor(
	private val useCase: NewsUseCase
): ViewModel() {

	private val _uiState = MutableStateFlow(NewsUiState())
	val uiState get() = _uiState.asStateFlow()

	private val _uiEvent = Channel<NewsUiEvent>()

	val uiEvent get() = _uiEvent.receiveAsFlow()

	fun onEvent(event: NewsUiEvent) = viewModelScope.launch {
		when (event) {
			NewsUiEvent.OnFetchNews -> {
				onFetchNews()
			}
			else -> {
				_uiEvent.send(event)
			}
		}
	}

	fun onFetchNews() {
		viewModelScope.launch {
			val news = useCase.invoke()
			Log.i("elang","elang $news")
		}
	}

}