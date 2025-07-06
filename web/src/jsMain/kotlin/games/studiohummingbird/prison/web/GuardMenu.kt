package games.studiohummingbird.prison.web

import kotlinx.css.li
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
    var onPray: (message: Message) -> Unit
}

@OptIn(ExperimentalTime::class)
val GuardMenu = FC<GuardMenuProps> { props ->
    val clock = use(ClockContext)
    menu {
        className = ClassName("guard-menu")
        actions.forEach { action ->
            li {
                button {
                    onClick = { event ->
                        props.onPray(Message(
                            clock.now(),
                            action
                        ))
                        console.log(action)
                    }
                    +action
                }
            }
        }
    }
}
