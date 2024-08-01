package com.tinyshellzz.simpleLoginQueue.listener;

import com.tinyshellzz.simpleLoginQueue.ObjectPool;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerQuitListener implements Listener {
    @EventHandler
    public void onPlayerQuit(org.bukkit.event.player.PlayerQuitEvent event) {
        ObjectPool.current_player_num -= 1;
    }
}
