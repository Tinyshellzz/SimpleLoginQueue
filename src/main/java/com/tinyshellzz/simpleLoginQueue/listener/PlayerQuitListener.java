package com.tinyshellzz.simpleLoginQueue.listener;

import com.tinyshellzz.simpleLoginQueue.ObjectPool;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerQuitListener implements Listener {
    @EventHandler
    public void onPlayerQuit(org.bukkit.event.player.PlayerQuitEvent event) {
        // 睡一小会儿
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ObjectPool.current_player_num -= 1;
    }
}
