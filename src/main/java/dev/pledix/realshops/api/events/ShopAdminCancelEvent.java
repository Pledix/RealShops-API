package dev.pledix.realshops.api.events;

import dev.pledix.realshops.api.IShop;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

public class ShopAdminCancelEvent extends ShopEvent implements Cancellable {

    private boolean cancelled;

    private final Player admin;

    public ShopAdminCancelEvent(IShop shop, Player admin) {
        super(shop);
        this.admin = admin;
    }

    public Player getAdmin() {
        return admin;
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