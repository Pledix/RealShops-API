package dev.pledix.realshops.api.pricing;

import dev.pledix.realshops.api.IPaymentMethod;

public interface IPricing {

    IPaymentMethod getPaymentMethod();

    Object getPrice();

    String toString();

}