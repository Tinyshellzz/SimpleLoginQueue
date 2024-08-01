package com.tinyshellzz.simpleLoginQueue.config;


import com.tinyshellzz.simpleLoginQueue.ObjectPool;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.List;

import static com.tinyshellzz.simpleLoginQueue.ObjectPool.plugin;

public class PluginConfig {
    public static List<String> msg = new ArrayList<>();

    public static int queue_time_out = 60;
    public static int waite_time_out = 10;
    public static int interval_time = 200;
    private static ConfigWrapper configWrapper = new ConfigWrapper(plugin, "config.yml");
    public static void reload() {
        configWrapper.reloadConfig();

        msg.clear();
        YamlConfiguration config = configWrapper.getConfig();
        List list = (List)config.get("msg");
        if(list != null) {
            for (Object o : list) {
                msg.add(o.toString());
            }
        }

        String config_queue_time_out = config.getString("queue_time_out");
        if(config_queue_time_out != null) {
            queue_time_out = Integer.parseInt(config_queue_time_out);
        }

        String config_waite_time_out = config.getString("wait_time_out");
        if(config_waite_time_out != null) {
            waite_time_out = Integer.parseInt(config_waite_time_out);
        }

        String config_interval_time = config.getString("interval_time");
        if(config_interval_time != null) {
            interval_time = Integer.parseInt(config_interval_time);
        }
    }
}
