package games.studiohummingbird.prison.web

import react.FC
import react.Props
import react.dom.html.ReactHTML.a
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.nav
import react.dom.html.ReactHTML.ul
import web.cssom.ClassName

val HeaderNav = FC<Props> { props ->
    nav {
        className = ClassName("header-nav")
        ul {
            className = ClassName("margin-none padding-none")
            li {
                className = ClassName("header-nav-item")
                a {
                    href = "#"
                    +"Play"
                }
            }
            li {
                className = ClassName("header-nav-item")
                a {
                    href = "#help"
                    rel = "help"
                    +"Help"
                }
            }
        }
    }
}
