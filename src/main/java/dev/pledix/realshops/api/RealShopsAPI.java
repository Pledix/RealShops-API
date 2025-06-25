package dev.pledix.realshops.api;

import java.util.List;
import java.util.UUID;

public interface RealShopsAPI {

    List<IShop> getShops();

    List<IShop> getShops(UUID uuid);

}