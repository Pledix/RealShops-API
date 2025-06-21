package dev.pledix.realshops.api;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;

public interface IShop {

    String getTitle();

    Location getLocation();

    OfflinePlayer getOwner();

}