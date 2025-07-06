package games.studiohummingbird.prison

import react.FC
import react.Props
import react.dom.html.ReactHTML.progress
import web.cssom.ClassName

external interface ExperienceBarProps : Props {
    var max: Int
    var value: Int
}

val ExperienceBar = FC<ExperienceBarProps> { props ->
    progress {
        className = ClassName("experience-bar")
        max = props.max.toDouble()
        value = props.value
    }
}
