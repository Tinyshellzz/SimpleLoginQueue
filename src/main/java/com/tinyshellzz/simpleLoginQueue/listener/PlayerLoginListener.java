package com.tinyshellzz.simpleLoginQueue.listener;

import com.tinyshellzz.simpleLoginQueue.ObjectPool;
import com.tinyshellzz.simpleLoginQueue.config.PluginConfig;
import com.tinyshellzz.simpleLoginQueue.service.QueueService;
import com.tinyshellzz.simpleLoginQueue.utils.Pair;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import static com.tinyshellzz.simpleLoginQueue.ObjectPool.queue;

public class PlayerLoginListener implements Listener {
    @EventHandler
    public synchronized void handle(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        if(player.hasPermission("essentials.joinfullserver")) {    // 拥有特殊权限, 直接放行
            return;
        }

        int index = QueueService.getIndex(player.getUniqueId());
        // 玩家不在队列里, 将玩家放入队列
        if(index == -1) {
            queue.add(new Pair<>(player.getUniqueId(), new Date()));
            index = queue.size() - 1;
        } else {
          queue.get(index).setSecond(new Date());
        }



        int max = Bukkit.getServer().getMaxPlayers();
        int current = Bukkit.getOnlinePlayers().size();
        // 服务器有空位, 放行队列里的玩家

        if(current < max) {
            if(QueueService.serverEmptyTime == null) {
                QueueService.serverEmptyTime = new Date();
            } else {
                // 超过10s没人登录, 就扩大waitLength
                QueueService.updateWaitLength();
            }

            boolean isInWaitList = QueueService.isInWaitList(index);
            if(isInWaitList == false) { // 玩家不在 isInWaitList 里
                disallowMsg(event, index);
            } else {    // 玩家在 isInWaitList 里. 放行玩家
                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "SimpleLoginQueue - 放行玩家: " + player.getDisplayName());
                queue.remove(index);
                QueueService.serverEmptyTime = null;
                QueueService.waitLength = 1;
            }
        } else {    // 服务器没有空位
            QueueService.serverEmptyTime = null;
            QueueService.waitLength = 1;
            disallowMsg(event, index);
        }
    }

    public void disallowMsg(PlayerLoginEvent event, int index) {
        StringBuilder msg = new StringBuilder();
        for(String str: PluginConfig.msg) {
            str = str.replaceAll("\\{index\\}", index + 1 + "");
            msg.append(str).append("\n");
        }

        event.disallow(PlayerLoginEvent.Result.KICK_OTHER, msg.toString());
    }
}
