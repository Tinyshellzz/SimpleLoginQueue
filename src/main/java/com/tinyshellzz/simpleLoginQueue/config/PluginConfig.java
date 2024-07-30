package com.tinyshellzz.simpleLoginQueue.config;


import com.tinyshellzz.simpleLoginQueue.ObjectPool;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.List;

import static com.tinyshellzz.simpleLoginQueue.ObjectPool.plugin;

public class PluginConfig {
    public static List<String> msg = new ArrayList<>();

    private static ConfigWrapper configWrapper = new ConfigWrapper(plugin, "config.yml");
    public static void reload() {
        configWrapper.reloadConfig();

        YamlConfiguration config = configWrapper.getConfig();
        List list = (List)config.get("msg");
        if(list != null) {
            for (Object o : list) {
                msg.add(o.toString());
            }
        }
    }
}
