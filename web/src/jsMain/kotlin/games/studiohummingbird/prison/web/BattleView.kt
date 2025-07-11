package games.studiohummingbird.prison.web

import js.array.component1
import js.array.component2
import react.*
import react.dom.events.MouseEventHandler
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.meter
import web.cssom.ClassName
import web.dom.ElementId
import web.html.HTMLButtonElement
import kotlin.math.max
import kotlin.math.min
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
sealed interface BattleAction : DomainEvent

@OptIn(ExperimentalTime::class)
data class PlayerClickedEvent(
    override val occurredAt: Instant
) : BattleAction

@OptIn(ExperimentalTime::class)
data class DemonClickedEvent(
    override val occurredAt: Instant
) : BattleAction

interface BattleState {
    val player: Double
    val demon: Double

    operator fun plus(action: BattleAction): BattleState
}

data class ActiveBattleState(
    override val player: Double,
    override val demon: Double,
) : BattleState {
    override fun plus(action: BattleAction): BattleState =
        when (action) {
            is PlayerClickedEvent -> copy(player = min(100.0, player + 1.0))
            is DemonClickedEvent -> copy(demon = max(0.0, demon - 1.0))
        }.let { state ->
            if (player == 0.0 || demon == 0.0) {
                BattleCompleteState(player, demon)
            } else {
                state
            }
        }
}

data class BattleCompleteState(
    override val player: Double,
    override val demon: Double,
) : BattleState {
    override fun plus(action: BattleAction): BattleState = this
}

object InitialBattleState : BattleState {
    override val player: Double = 100.0
    override val demon: Double = 99.0

    override fun plus(action: BattleAction): BattleState =
        ActiveBattleState(player = player, demon = demon) + action
}

@OptIn(ExperimentalTime::class)
val BattleView = FC<Props> { props ->
    val clock = use(ClockContext)
    val appendEvent = use(AppendEventContext)
    val (battleActions, storeBattleAction) = useEventStore<BattleAction>()
    val (battle, applyBattleAction) = useReducer(BattleState::plus, InitialBattleState)

    useEffect(battleActions, applyBattleAction) {
        console.log("useEffect battleActions")
        val lastAction = battleActions.lastOrNull()
        if (lastAction != null) {
            applyBattleAction(lastAction)
            appendEvent(lastAction)
        }
    }

    val onClickPlayer: MouseEventHandler<HTMLButtonElement> = useMemo(clock, storeBattleAction) {
        { mouseEvent ->
            val event = PlayerClickedEvent(
                clock.now()
            )
            storeBattleAction(event)
        }
    }

    val onClickDemon: MouseEventHandler<HTMLButtonElement> = useMemo(clock, storeBattleAction) {
        { mouseEvent ->
            val event = DemonClickedEvent(
                clock.now()
            )
            storeBattleAction(event)
        }
    }

    div {
        className = ClassName("battle-view")

        div {
            className = ClassName("flex-column")
            id = ElementId("target-player")

            button {
                className = ClassName("target-button")
                onClick = onClickPlayer
                +"Player"
            }

            meter {
                low = 1.0
                max = 100.0
                value = battle.player
            }
        }

        div {
            className = ClassName("flex-column")

            button {
                className = ClassName("target-button")
                onClick = onClickDemon
                +"Demon"
            }

            meter {
                low = 99.0
                max = 100.0
                value = battle.demon
            }
        }
    }
}
