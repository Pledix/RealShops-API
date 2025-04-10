package dev.pledix.realshops.api.events;

import dev.pledix.realshops.api.IPaymentMethod;
import dev.pledix.realshops.api.IShop;
import dev.pledix.realshops.api.IShopItem;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.inventory.ItemStack;

public class ShopTradeEvent extends ShopEvent implements Cancellable {

    private boolean cancelled;

    private final Player buyer;
    private final IShopItem shopItem;

    public ShopTradeEvent(IShop shop, IShopItem shopItem, Player buyer) {
        super(shop);
        this.buyer = buyer;
        this.shopItem = shopItem;
    }

    public Player getBuyer() {
        return buyer;
    }

    public ItemStack getItemStack() {
        return shopItem.getItem();
    }

    public Object getPrice() {
        return shopItem.getPricing().getPrice();
    }

    public IPaymentMethod getPaymentMethod() {
        return shopItem.getPricing().getPaymentMethod();
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