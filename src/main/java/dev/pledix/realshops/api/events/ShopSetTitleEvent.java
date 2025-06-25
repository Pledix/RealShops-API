package dev.pledix.realshops.api.events;

import dev.pledix.realshops.api.IShop;
import org.bukkit.event.Cancellable;

public class ShopSetTitleEvent extends ShopEvent implements Cancellable {

    private boolean cancelled;

    private final IShop shop;

    private String title;

    public ShopSetTitleEvent(IShop shop, String title) {
        this.shop = shop;
        this.title = title;
    }

    public IShop getShop() {
        return shop;
    }

    public String getTitle() {
        return title;
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