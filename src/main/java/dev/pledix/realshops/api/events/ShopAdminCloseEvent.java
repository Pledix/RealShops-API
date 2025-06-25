package dev.pledix.realshops.api.events;

import dev.pledix.realshops.api.IShop;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

public class ShopAdminCloseEvent extends ShopEvent implements Cancellable {

    private boolean cancelled;

    private final IShop shop;

    private final Player admin;

    public ShopAdminCloseEvent(IShop shop, Player admin) {
        this.shop = shop;
        this.admin = admin;
    }

    public Player getAdmin() {
        return admin;
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