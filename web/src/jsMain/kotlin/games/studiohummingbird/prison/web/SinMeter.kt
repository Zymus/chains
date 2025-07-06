package games.studiohummingbird.prison.web

import games.studiohummingbird.prison.Sin
import react.FC
import react.Props
import react.dom.html.ReactHTML.label
import react.dom.html.ReactHTML.meter
import react.dom.html.ReactHTML.span

external interface SinMeterProps : Props {
    var sin: Sin
}

val SinMeter = FC<SinMeterProps> { props ->
    label {
        span {
            +props.sin.name
        }
        meter {
            low = 26.0
            high = 52.0
            min = 0.0
            max = 100.0
            optimum = 0.0
            value = 99.0
        }
    }
}
