package dev.pledix.realshops.api.events;

import dev.pledix.realshops.api.IShop;
import org.bukkit.event.Cancellable;

public class ShopCreateEvent extends ShopEvent implements Cancellable {

    private boolean cancelled;

    public ShopCreateEvent(IShop shop) {
        super(shop);
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

}