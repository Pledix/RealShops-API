package dev.pledix.realshops.api;

import org.bukkit.inventory.ItemStack;

public interface IShopItem {

    ItemStack getItem();

    IPricing getPricing();

    Object clone();

}
