package games.studiohummingbird.prison

import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import web.cssom.ClassName

external interface MapProps : Props {
}

val Map = FC<MapProps> { props ->
    div {
        className = ClassName("map")
    }
}
