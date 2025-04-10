package dev.pledix.realshops.api;

public interface IPricing {

    IPaymentMethod getPaymentMethod();

    Object getPrice();

    IPricing clone();

    String toString();

}