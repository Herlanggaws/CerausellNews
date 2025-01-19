package com.herlangga.news.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.herlangga.core.data.ViewState
import com.herlangga.core.ui.theme.Primary
import com.herlangga.core.ui.theme.PurpleGrey80
import com.herlangga.core.ui.theme.title
import com.herlangga.news.R
import com.herlangga.news.presentation.component.NewsEmptyOrError
import com.herlangga.news.presentation.component.NewsItem
import com.herlangga.news.presentation.component.NewsItemShimmer
import com.herlangga.news.presentation.component.SortDialog

/**
 * Designed and developed by Herlangga Wicaksono on 18/01/25.
 * @LinkedIn (https://www.linkedin.com/in/herlangga-wicaksono-4072a5a2/)
 */
@Composable
fun NewsScreen(
	modifier: Modifier = Modifier,
	viewModel: NewsScreenViewModel = hiltViewModel()
) {
	val lifecycleOwner = LocalLifecycleOwner.current
	val uiState by viewModel.uiState.collectAsStateWithLifecycle()

	LaunchedEffect(key1 = Unit) {
		viewModel.onEvent(NewsUiEvent.OnFetchNews)
		viewModel.uiEvent.flowWithLifecycle(lifecycleOwner.lifecycle)
			.collect(::onEvent)
	}

	val OnEvent = remember<(NewsUiEvent) -> Unit> {
		{
			viewModel.onEvent(it)
		}
	}

	NewsScreen(
		uiState = uiState,
		modifier = modifier,
		onEvent = OnEvent
	)

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(
	uiState: NewsUiState,
	modifier: Modifier = Modifier,
	onEvent: (NewsUiEvent) -> Unit = {}
) {
	Scaffold(
		modifier = modifier.background(PurpleGrey80),
		topBar = {
			TopAppBar(
				colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = Primary),
				title = {
					Text(
						text = stringResource(R.string.label_title),
						style = title,
						color = Color.White
					)
				},
				actions = {
					IconButton(onClick = {
						Log.i("elang","elang IconButton")
						onEvent(NewsUiEvent.OnSortOptionClicked)
					}) {
						Image(
							Icons.Filled.MoreVert,
							colorFilter = ColorFilter.tint(Color.White),
							contentDescription = "Menu"
						)
					}
				}
			)
		}
	) { innerPadding ->
		when (uiState.viewState) {
			is ViewState.Loading -> {
				NewsItemShimmer(modifier = Modifier.padding(innerPadding))
			}
			is ViewState.Error -> {
				NewsEmptyOrError(uiState.viewState.message)
			}
			else -> {
				if (uiState.news.isEmpty()) {
					NewsEmptyOrError(stringResource(R.string.label_empty_data))
				} else {
					LazyColumn(modifier = Modifier.padding(innerPadding)) {
						items(uiState.news) {news ->
							Spacer(Modifier.size(8.dp))
							NewsItem(
								news = news,
								modifier = Modifier.padding(horizontal = 8.dp),
								onEvent = onEvent
							)
						}
					}
				}

			}
		}
		SortDialog(uiState = uiState, modifier = modifier, onEvent = onEvent)
	}
}

fun onEvent(event: NewsUiEvent){

}

@Preview
@Composable
fun NewsScreenPreview() {
	val uiState = remember {
		NewsUiState(
			viewState = ViewState.Content,
		)
	}
	NewsScreen(
		uiState = uiState,
		modifier = Modifier,
		onEvent = {

		}
	)
}