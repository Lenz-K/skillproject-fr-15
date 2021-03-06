package alexasescape.constants;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Slot;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;
import java.util.Optional;

public enum Slots {
    PLAYER_NAME("PlayerName"), ITEM_NAME("ItemName");

    private final String slotName;

    Slots(String slotName) {
        this.slotName = slotName;
    }

    @JsonIgnore
    public String getSlotName() {
        return slotName;
    }

    public Optional<String> value(HandlerInput input) {
        Objects.requireNonNull(input, "Input to grab slot from must not be null!");

        final IntentRequest intentRequest = (IntentRequest) input.getRequestEnvelope().getRequest();
        final Optional<Slot> slot = Optional.ofNullable(intentRequest.getIntent())
                .map(Intent::getSlots)
                .map(slots -> slots.get(slotName));

        if (slot.isPresent() && slot.get().getValue() != null) {
            return Optional.of(slot.get().getValue());
        } else {
            return Optional.empty();
        }
    }
}
