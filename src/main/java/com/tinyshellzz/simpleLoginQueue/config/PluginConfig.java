package com.tinyshellzz.simpleLoginQueue.config;


import org.bukkit.configuration.file.YamlConfiguration;

import static com.tinyshellzz.simpleLoginQueue.ObjectPool.plugin;

public class PluginConfig {
    public static String msg;

    private static ConfigWrapper configWrapper = new ConfigWrapper(plugin, "config.yml");
    public static void reload() {
        configWrapper.reloadConfig();

        YamlConfiguration config = configWrapper.getConfig();
        msg =  config.getString("msg");
    }
}
