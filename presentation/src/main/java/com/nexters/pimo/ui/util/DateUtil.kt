package com.nexters.pimo.ui.util

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

object DateUtil {

    fun LocalDateTime.isToday() = this.toLocalDate().isEqual(LocalDate.now())

    fun LocalDateTime.toRelatively(): String {
        val now = LocalDateTime.now()

        return when {
            plusYears(1).isBeforeOrEqual(now) -> {
                "${ChronoUnit.YEARS.between(this, now)}년 전"
            }
            plusMonths(1).isBeforeOrEqual(now) -> {
                "${ChronoUnit.MONTHS.between(this, now)}달 전"
            }
            plusWeeks(1).isBeforeOrEqual(now) -> {
                "${ChronoUnit.WEEKS.between(this, now)}주 전"
            }
            plusDays(1).isBeforeOrEqual(now) -> {
                "${ChronoUnit.DAYS.between(this, now)}일 전"
            }
            plusHours(1).isBeforeOrEqual(now) -> {
                "${ChronoUnit.HOURS.between(this, now)}시간 전"
            }
            plusMinutes(1).isBeforeOrEqual(now) -> {
                "${ChronoUnit.MINUTES.between(this, now)}분 전"
            }
            else -> {
                "방금 전"
            }
        }
    }

    private fun LocalDateTime.isBeforeOrEqual(other: LocalDateTime) =
        isEqual(other) || isBefore(other)
}
