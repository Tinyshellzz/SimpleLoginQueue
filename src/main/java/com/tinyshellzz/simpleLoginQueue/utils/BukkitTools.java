package com.tinyshellzz.simpleLoginQueue.utils;

import org.bukkit.Bukkit;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.UUID;

public class BukkitTools {
    public static UUID getUUID(String player) {
        UUID mc_uuid = null;


        OfflinePlayer[] offlinePlayers = Bukkit.getServer().getOfflinePlayers();
        for (OfflinePlayer _p : offlinePlayers) {
            Player p = _p.getPlayer();
            if (p.getDisplayName().toLowerCase().equals(player)) {
                mc_uuid = p.getUniqueId();
            }
        }


        if(mc_uuid == null) {
            try {
                String[] ret = Tools.get_player(player);
                mc_uuid = UUID.fromString(
                        ret[0]
                                .replaceFirst(
                                        "(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)", "$1-$2-$3-$4-$5"
                                )
                );
            } catch (IOException e) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "PermissionManager： 网络错误");
            }
        }

        return mc_uuid;
    }
}
