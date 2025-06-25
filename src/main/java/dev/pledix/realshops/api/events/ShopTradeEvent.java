package dev.pledix.realshops.api.events;

import dev.pledix.realshops.api.IShop;
import dev.pledix.realshops.api.IShopItem;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

public class ShopTradeEvent extends ShopEvent implements Cancellable {

    private boolean cancelled;

    private final IShop shop;

    private final Player buyer;
    private final IShopItem shopItem;

    public ShopTradeEvent(IShop shop, IShopItem shopItem, Player buyer) {
        this.shop = shop;
        this.buyer = buyer;
        this.shopItem = shopItem;
    }

    public IShop getShop() {
        return shop;
    }

    public Player getBuyer() {
        return buyer;
    }

    public IShopItem getShopItem() {
        return shopItem;
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