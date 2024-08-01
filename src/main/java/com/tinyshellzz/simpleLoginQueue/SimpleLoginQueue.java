package com.tinyshellzz.simpleLoginQueue;

import com.tinyshellzz.simpleLoginQueue.command.QueueCommand;
import com.tinyshellzz.simpleLoginQueue.config.PluginConfig;
import com.tinyshellzz.simpleLoginQueue.listener.PlayerLoginListener;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleLoginQueue extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "+++++++++++++++++++");

        init();
        register();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void init(){
        ObjectPool.plugin = this;
        ObjectPool.current_player_num = Bukkit.getOnlinePlayers().size();
        PluginConfig.reload();
    }

    public void register() {
        this.getServer().getPluginManager().registerEvents(new PlayerLoginListener(), this);

        this.getCommand("queue").setExecutor(new QueueCommand());
    }
}
