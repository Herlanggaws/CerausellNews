package com.herlangga.news.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.herlangga.core.ui.theme.TextGrey
import com.herlangga.core.ui.theme.TextTitle
import com.herlangga.core.ui.theme.caption
import com.herlangga.core.ui.theme.desc
import com.herlangga.core.ui.theme.title
import com.herlangga.news.utils.DateUtils.getReadableTime
import com.herlangga.news.utils.shimmerEffect

/**
 * Designed and developed by Herlangga Wicaksono on 19/01/25.
 * @LinkedIn (https://www.linkedin.com/in/herlangga-wicaksono-4072a5a2/)
 */
@Preview
@Composable
fun NewsItemShimmer(
	modifier: Modifier = Modifier
) {
	Surface(
		modifier = modifier,
		shape = RoundedCornerShape(8.dp)
	) {
		Column {
			Box(
				modifier = modifier
					.fillMaxWidth()
					.height(140.dp)
					.clip(RoundedCornerShape(8.dp))
					.shimmerEffect()
			)
			Spacer(Modifier.size(8.dp))
			Box(
				modifier = modifier
					.fillMaxWidth(0.6F)
					.padding(horizontal = 8.dp)
					.height(8.dp)
					.clip(RoundedCornerShape(8.dp))
					.shimmerEffect()
			)
			Spacer(Modifier.size(4.dp))
			Box(
				modifier = modifier
					.fillMaxWidth(0.6F)
					.padding(horizontal = 8.dp)
					.height(8.dp)
					.clip(RoundedCornerShape(8.dp))
					.shimmerEffect()
			)
			Spacer(Modifier.size(4.dp))
		}
	}
}