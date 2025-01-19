package com.herlangga.news.utils

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit

/**
 * Designed and developed by Herlangga Wicaksono on 19/01/25.
 * @LinkedIn (https://www.linkedin.com/in/herlangga-wicaksono-4072a5a2/)
 */
object DateUtils {
	fun getReadableTime(timestamp: Long): String {
		val now = LocalDateTime.now()
		val dateTime =
			LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.systemDefault())
		val days = ChronoUnit.DAYS.between(dateTime, now)
		val weeks = ChronoUnit.WEEKS.between(dateTime, now)
		val months = ChronoUnit.MONTHS.between(dateTime, now)
		val years = ChronoUnit.YEARS.between(dateTime, now)

		return when {
			days < 7 -> "$days day${if (days != 1L) "s" else ""} ago"
			weeks < 5 -> "$weeks week${if (weeks != 1L) "s" else ""} ago"
			months < 12 -> "$months month${if (months != 1L) "s" else ""} ago"
			else -> "$years year${if (years != 1L) "s" else ""} ago"
		}
	}
}