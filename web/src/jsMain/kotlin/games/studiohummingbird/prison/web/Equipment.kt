package games.studiohummingbird.prison.web

import js.array.component1
import js.array.component2
import react.*
import react.dom.html.ReactHTML.div
import react.dom.svg.ReactSVG
import react.dom.svg.ReactSVG.animateMotion
import react.dom.svg.ReactSVG.animateTransform
import react.dom.svg.ReactSVG.circle
import react.dom.svg.ReactSVG.feDisplacementMap
import react.dom.svg.ReactSVG.feTurbulence
import react.dom.svg.ReactSVG.svg
import web.cssom.ClassName
import web.dom.ElementId
import web.events.EventHandler
import web.events.EventType
import web.events.addEventListener
import web.events.removeEventListener
import web.svg.SVGElement

val Equipment = FC<Props> { props ->
    val (state, dispatch) = useReducer<Boolean, String>({ state, action ->
        when (action) {
            "ended" -> true
            else -> state
        }
    }, false)
    val animateMotionRef = useRef<SVGElement>()

    val onEndEventAnimateMotion = useMemo(dispatch) {
        EventHandler {
            println("animateMotion ended")
            dispatch("ended")
        }
    }

    useEffectOnceWithCleanup {
        animateMotionRef.current?.addEventListener(EventType("endEvent"), onEndEventAnimateMotion)

        onCleanup {
            animateMotionRef.current?.removeEventListener(EventType("endEvent"), onEndEventAnimateMotion)
        }
    }

    div {
        className = ClassName("equipment")

        svg {
            height = 400.0
            width = 400.0
            xmlns = "http://www.w3.org/2000/svg"

            ReactSVG.filter {
                id = ElementId("displacementFilter")

                feTurbulence {
                    type = "turbulence"
                    baseFrequency = 0.13
                    numOctaves = 2
                    result = "turbulence"
                }

                feDisplacementMap {
                    `in` = "SourceGraphic"
                    in2 = "turbulence"
                    scale = 50.0
                    xChannelSelector = "R"
                    yChannelSelector = "G"
                }
            }

            circle {
                cx = 200.0
                cy = 200.0
                fill = "yellow"
                r = 50.0
                stroke = "red"
                strokeWidth = 1.0
                filter = "url(#displacementFilter)"
                visibility =
                    if (state) {
                        "visible"
                    }
                    else {
                        "hidden"
                    }
            }

            circle {
                cx = 0.0
                cy = 0.0
                fill = "red"
                r = 15.0
                stroke = "orange"
                strokeWidth = 1.0
                visibility =
                    if (state) {
                        "hidden"
                    }
                    else {
                        "visible"
                    }

                animateTransform {
                    attributeName = "transform"
                    attributeType = "xml"
                    begin = "0s"
                    dur = "1s"
                    from = "0 0"
                    to = "200 200"
                    ref = animateMotionRef
                    type = "translate"
                }
            }
        }
    }
}
