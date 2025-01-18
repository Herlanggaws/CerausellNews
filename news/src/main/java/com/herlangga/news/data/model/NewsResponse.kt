package com.herlangga.news.data.model

import com.google.gson.annotations.SerializedName

/**
 * Designed and developed by Herlangga Wicaksono on 18/01/25.
 * @LinkedIn (https://www.linkedin.com/in/herlangga-wicaksono-4072a5a2/)
 */
data class NewsResponse(
	@SerializedName("banner_url")
	val bannerUrl: String?,
	@SerializedName("description")
	val description: String?,
	@SerializedName("id")
	val id: String?,
	@SerializedName("rank")
	val rank: Int?,
	@SerializedName("time_created")
	val timeCreated: Long?,
	@SerializedName("title")
	val title: String?
)
