package dev.pledix.realshops.api;

import dev.pledix.realshops.api.pricing.IPricing;
import org.bukkit.inventory.ItemStack;

public interface IShopItem {

    ItemStack getItem();

    IPricing getPricing();

}