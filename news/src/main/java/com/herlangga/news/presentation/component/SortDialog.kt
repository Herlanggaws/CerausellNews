package com.herlangga.news.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.herlangga.core.ui.theme.Primary
import com.herlangga.core.ui.theme.TextTitle
import com.herlangga.core.ui.theme.desc
import com.herlangga.core.ui.theme.title
import com.herlangga.news.R
import com.herlangga.news.domain.model.SortType
import com.herlangga.news.presentation.NewsUiEvent
import com.herlangga.news.presentation.NewsUiState

/**
 * Designed and developed by Herlangga Wicaksono on 19/01/25.
 * @LinkedIn (https://www.linkedin.com/in/herlangga-wicaksono-4072a5a2/)
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortDialog(
	uiState: NewsUiState,
	modifier: Modifier = Modifier,
	onEvent: (NewsUiEvent) -> Unit = {}
) {
	AnimatedVisibility(visible = uiState.isShowSortDialog, modifier) {
		BasicAlertDialog(onDismissRequest = { onEvent(NewsUiEvent.OnSortOptionDismiss) }) {
			Surface(shape = RoundedCornerShape(16.dp), color = Color.White) {
				Column(modifier = Modifier.padding(16.dp)) {
					SortItem(
						uiState = uiState,
						text = stringResource(R.string.label_recent),
						type = SortType.RECENT,
						onEvent = onEvent
					)
					Spacer(Modifier.size(8.dp))
					SortItem(
						uiState = uiState,
						text = stringResource(R.string.label_popular),
						type = SortType.POPULAR,
						onEvent = onEvent
					)
				}
			}
		}
	}
}

@Composable
fun SortItem(
	uiState: NewsUiState,
	text: String,
	type: SortType,
	modifier: Modifier = Modifier,
	onEvent: (NewsUiEvent) -> Unit = {}
) {
	val isSelected = remember(uiState.sortType, type) {
		uiState.sortType == type
	}

	val onClick = remember {
		{
			onEvent(NewsUiEvent.OnSortSelected(type))
			onEvent(NewsUiEvent.OnSortOptionDismiss)
		}
	}

	val textColorTarget = if (isSelected) Primary else TextTitle

	val textColor by animateColorAsState(
		targetValue = textColorTarget,
		label = "Text Color"
	)

	Text(
		text = text,
		style = title,
		color = textColor,
		modifier = Modifier
			.fillMaxWidth()
			.clickable { onClick() }
	)
}