package games.studiohummingbird.prison.web

import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
data class Message(
    val instant: Instant,
    val textContent: String,
)
