package dev.pledix.realshops.api.events;

import dev.pledix.realshops.api.IPaymentMethod;
import dev.pledix.realshops.api.IShop;
import dev.pledix.realshops.api.IShopItem;
import org.bukkit.inventory.ItemStack;

public class ShopPricingEvent extends ShopEvent {

    private final IShopItem shopItem;

    public ShopPricingEvent(IShop shop, IShopItem shopItem) {
        super(shop);
        this.shopItem = shopItem;
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

}