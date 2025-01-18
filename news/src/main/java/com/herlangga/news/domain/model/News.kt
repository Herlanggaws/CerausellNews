package com.herlangga.news.domain.model

import com.herlangga.news.data.model.NewsResponse

/**
 * Designed and developed by Herlangga Wicaksono on 18/01/25.
 * @LinkedIn (https://www.linkedin.com/in/herlangga-wicaksono-4072a5a2/)
 */
data class News(
	val bannerUrl: String,
	val description: String,
	val id: String,
	val rank: Int,
	val timeCreated: Long,
	val title: String,
) {
	constructor(data: NewsResponse?) : this(
		bannerUrl = data?.bannerUrl.orEmpty(),
		description = data?.description.orEmpty(),
		id = data?.id.orEmpty(),
		rank = data?.rank ?: 0,
		timeCreated = data?.timeCreated ?: 0L,
		title = data?.title.orEmpty(),
	)
}
