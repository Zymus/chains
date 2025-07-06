package games.studiohummingbird.prison.web

import games.studiohummingbird.prison.Corridor
import games.studiohummingbird.prison.Prison
import react.createContext

val PrisonContext = createContext(Prison(Corridor()))
