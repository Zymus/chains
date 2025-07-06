package games.studiohummingbird.prison

/*
 * There are no cells where x^2 == y^2
 */
data class Cell(
    val corridor: Corridor,
    val key: Key,
)
