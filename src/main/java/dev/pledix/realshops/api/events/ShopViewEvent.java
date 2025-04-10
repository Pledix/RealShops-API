package dev.pledix.realshops.api.events;

import dev.pledix.realshops.api.IShop;
import org.bukkit.entity.Player;

public class ShopViewEvent extends ShopEvent {

    private final Player viewer;

    public ShopViewEvent(IShop shop, Player viewer) {
        super(shop);
        this.viewer = viewer;
    }

    public Player getViewer() {
        return viewer;
    }

}