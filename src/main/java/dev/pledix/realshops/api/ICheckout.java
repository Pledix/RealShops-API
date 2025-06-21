package dev.pledix.realshops.api;

import dev.pledix.realshops.api.pricing.IItemPricing;
import dev.pledix.realshops.api.pricing.IMoneyPricing;

import java.util.Map;

public interface ICheckout {

    Map<String, IItemPricing> getItems();

    IMoneyPricing getMoney();

    boolean isEmpty();

}
