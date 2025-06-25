package dev.pledix.realshops.api.events;

import dev.pledix.realshops.api.IShop;

import java.util.List;

public class ShopExpireEvent extends ShopEvent {

    private List<IShop> expiredShops;

    public ShopExpireEvent(List<IShop> expiredShops) {
        this.expiredShops = expiredShops;
    }

    public List<IShop> getExpiredShops() {
        return expiredShops;
    }

}