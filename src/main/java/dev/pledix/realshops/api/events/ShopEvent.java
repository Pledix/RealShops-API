package dev.pledix.realshops.api.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public abstract class ShopEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    public ShopEvent() {}
    
    public ShopEvent(boolean isAsync) {
        super(isAsync);
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}