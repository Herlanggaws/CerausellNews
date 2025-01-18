package com.herlangga.news.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.herlangga.core.data.ViewState
import com.herlangga.core.ui.theme.Primary
import com.herlangga.core.ui.theme.title
import com.herlangga.news.R

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

	NewsScreen(
		uiState = uiState,
		modifier = modifier,

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
		modifier = modifier.fillMaxSize(),
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
						/*
						* To Do
						*/
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
		Text(
			modifier = Modifier.padding(innerPadding),
			text = ""
		)
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