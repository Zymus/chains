package games.studiohummingbird.prison.web

import react.createContext
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
val ClockContext = createContext<Clock>(Clock.System)
