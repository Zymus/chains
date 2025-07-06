package games.studiohummingbird.prison.web

import games.studiohummingbird.prison.Reliquary
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import web.cssom.ClassName

external interface ReliquaryProps : Props {
    var reliquary: Reliquary
}

val Reliquary = FC<ReliquaryProps> { props ->
    div {
        className = ClassName("reliquary")
    }
}
