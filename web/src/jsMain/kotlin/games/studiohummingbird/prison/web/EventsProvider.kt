package games.studiohummingbird.prison.web

import js.array.component1
import js.array.component2
import react.Dispatch
import react.FC
import react.PropsWithChildren
import react.ReducerInstance
import react.createContext
import react.useReducer
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
interface DomainEvent {
    val occurredAt: Instant
}

val EventsContext = createContext<List<DomainEvent>>(emptyList())

val AppendEventContext = createContext<Dispatch<DomainEvent>> {}

fun <TEvent : DomainEvent> useEventStore(): ReducerInstance<List<TEvent>, TEvent> {
    return useReducer(List<TEvent>::plus, emptyList())
}

val EventsProvider = FC<PropsWithChildren> { props ->
    val (state, dispatch) = useEventStore<DomainEvent>()

    EventsContext.Provider {
        value = state
        AppendEventContext.Provider {
            value = dispatch
            +props.children
        }
    }
}
