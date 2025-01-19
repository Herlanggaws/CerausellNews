package com.herlangga.news.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.herlangga.core.ui.theme.TextGrey
import com.herlangga.core.ui.theme.TextTitle
import com.herlangga.core.ui.theme.caption
import com.herlangga.core.ui.theme.desc
import com.herlangga.core.ui.theme.title
import com.herlangga.news.domain.model.News
import com.herlangga.news.presentation.NewsUiEvent
import com.herlangga.news.utils.DateUtils.getReadableTime

/**
 * Designed and developed by Herlangga Wicaksono on 19/01/25.
 * @LinkedIn (https://www.linkedin.com/in/herlangga-wicaksono-4072a5a2/)
 */
@Composable
fun NewsItem(
	news: News,
	modifier: Modifier = Modifier,
	onEvent: (NewsUiEvent) -> Unit = {}
) {
	val context = LocalContext.current
	val onClick = remember {
		{ onEvent(NewsUiEvent.OnNewsClick(news)) }
	}
	Surface(
		modifier.clickable(onClick = onClick),
		shape = RoundedCornerShape(8.dp)
	) {
		Column {
			AsyncImage(
				model = ImageRequest.Builder(context)
					.data(news.bannerUrl)
					.crossfade(true)
					.build(),
				contentDescription = "News Banner",
				contentScale = ContentScale.Crop,
				modifier = Modifier
					.fillMaxWidth()
					.height(140.dp)
					.padding(bottom = 12.dp)
			)
			Text(
				text = news.title,
				style = title,
				maxLines = 2,
				color = TextTitle,
				modifier = Modifier
					.padding(horizontal = 16.dp)
					.padding(bottom = 4.dp)
			)
			Text(
				text = news.description,
				style = desc,
				maxLines = 2,
				color = TextTitle,
				modifier = Modifier
					.padding(horizontal = 16.dp)
					.padding(bottom = 8.dp)
			)
			Text(
				text = getReadableTime(news.timeCreated),
				style = caption,
				color = TextGrey,
				modifier = Modifier
					.padding(horizontal = 16.dp)
					.padding(bottom = 12.dp)
			)
		}
	}
}

@Preview
@Composable
fun NewsItem() {
	NewsItem(
		news = News(
			bannerUrl = "https://search.yahoo.com/search?p=veniam",
			description = "test",
			id = "id",
			rank = 123,
			timeCreated = 123,
			title = "test"
		)
	)
}