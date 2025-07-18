package games.studiohummingbird.prison.web

import react.FC
import react.Props
import react.dom.html.ReactHTML.details
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.ol
import react.dom.html.ReactHTML.output
import react.dom.html.ReactHTML.summary
import react.dom.html.ReactHTML.time
import react.use
import web.cssom.ClassName
import web.dom.ElementId
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
val DetailsLog = FC<Props> { props ->
    val events = use(EventsContext)
    val messages = events.map {
        Message(
            it.occurredAt,
            it.toString()
        )
    }
    val first = messages.firstOrNull()
    val sortedByTime = messages.asReversed()

    details {
        className = ClassName("messages")
        summary {
            output {
                htmlFor = ElementId("")
                +(sortedByTime.firstOrNull()?.textContent ?: "")
            }
        }
        ol {
            className = ClassName("padding-none")

            sortedByTime.forEachIndexed { index, message ->
                li {
                    className = ClassName("message-list-item")
                    key = "message-$index"
                    time {
                        dateTime = message.instant.toString()
                        +first?.let { (message.instant - it.instant).toIsoString() }
                    }
                    +message.textContent
                }
            }
        }
    }
}
