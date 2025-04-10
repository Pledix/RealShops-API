package dev.pledix.realshops.api.events;

import dev.pledix.realshops.api.IShop;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public abstract class ShopEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final IShop shop;

    public ShopEvent(IShop shop) {
        this.shop = shop;
    }

    public ShopEvent(IShop shop, boolean isAsync) {
        super(isAsync);
        this.shop = shop;
    }

    public IShop getShop() {
        return shop;
    }

    public OfflinePlayer getOwner() {
        return shop.getOwner();
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}