package games.studiohummingbird.prison.web

import react.FC
import react.Props
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.menu
import react.use
import web.cssom.ClassName
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

val actions = listOf(
    "Find Cell",
    "Pray",
    "Burn Incense",
)

external interface GuardMenuProps : Props {
}

@OptIn(ExperimentalTime::class)
data class GuardAction(
    override val occurredAt: Instant,
    val action: String
) : DomainEvent

@OptIn(ExperimentalTime::class)
val GuardMenu = FC<GuardMenuProps> { props ->
    val clock = use(ClockContext)
    val appendEvent = use(AppendEventContext)

    menu {
        className = ClassName("guard-menu")
        actions.forEach { action ->
            li {
                key = "guard-menu-$action"
                button {
                    onClick = { event ->
                        appendEvent(GuardAction(
                            clock.now(),
                            action
                        ))
                    }
                    +action
                }
            }
        }
    }
}
