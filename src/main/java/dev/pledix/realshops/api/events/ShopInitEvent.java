package dev.pledix.realshops.api.events;

import dev.pledix.realshops.api.IShop;
import org.bukkit.event.Cancellable;

public class ShopInitEvent extends ShopEvent implements Cancellable {

    private boolean cancelled;

    private final IShop shop;

    public ShopInitEvent(IShop shop) {
        this.shop = shop;
    }

    public IShop getShop() {
        return shop;
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