package games.studiohummingbird.prison

import react.FC
import react.Props
import react.dom.html.ReactHTML.details
import react.dom.html.ReactHTML.p
import react.dom.html.ReactHTML.strong
import react.dom.html.ReactHTML.summary
import web.cssom.ClassName

external interface ItemDetailsProps : Props {
    var name: String
    var description: String
}

val ItemDetails = FC<ItemDetailsProps> { props ->
    details {
        className = ClassName("item")
        summary {
            strong {
                +props.name
            }
        }
        p {
            +props.description
        }
    }
}
