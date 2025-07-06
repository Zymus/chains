package games.studiohummingbird.prison.web

import games.studiohummingbird.prison.Corridor
import games.studiohummingbird.prison.MainViewport
import games.studiohummingbird.prison.Prison
import games.studiohummingbird.prison.Sin
import react.FC
import react.Props
import react.StrictMode
import react.create
import react.dom.client.createRoot
import react.useCallback
import react.useState
import web.dom.ElementId
import web.dom.document

fun main() {
    val header = document.getElementById(ElementId("header")) ?: error("couldn't find header element")
    createRoot(header).render(HeaderNav.create())

    val root = document.getElementById(ElementId("main-content")) ?: error("couldn't find root element")
    createRoot(root).render(App.create())
}

val App = FC<Props> { props ->
    val (messages, setMessages) = useState(emptyList<Message>())
    val addMessage = useCallback(messages, setMessages) { message: Message ->
        setMessages(messages + message)
    }

    StrictMode {
        PrisonContext {
            value = Prison(
                Corridor()
            )

            MainViewport {
            }

            GuardMenu {
                onPray = addMessage
            }

            Sin.entries.forEach { sinEntry ->
                SinMeter {
                    sin = sinEntry
                }
            }
            MessageContext {
                value = messages
                DetailsLog {
                }
            }
        }
    }
}
