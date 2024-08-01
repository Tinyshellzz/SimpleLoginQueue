package com.tinyshellzz.simpleLoginQueue.listener;

import com.tinyshellzz.simpleLoginQueue.ObjectPool;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerJoinListener implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void handle(PlayerLoginEvent event) {
        ObjectPool.current_player_num += 1;
    }
}
