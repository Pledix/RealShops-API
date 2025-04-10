package dev.pledix.realshops.api.events;

import dev.pledix.realshops.api.IShop;

public class ShopExpireEvent extends ShopEvent {

    public ShopExpireEvent(IShop shop) {
        super(shop);
    }

}