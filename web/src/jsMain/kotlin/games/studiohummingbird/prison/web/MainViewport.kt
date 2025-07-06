package games.studiohummingbird.prison

import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import web.cssom.ClassName

val MainViewport = FC<Props> { props ->
    div {
        className = ClassName("viewport")

        div {
            className = ClassName("panel side")
        }

        div {
            className = ClassName("panel center")
        }

        div {
            className = ClassName("panel side")
        }
    }
}
