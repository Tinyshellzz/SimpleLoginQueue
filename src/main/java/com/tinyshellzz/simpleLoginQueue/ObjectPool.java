package com.tinyshellzz.simpleLoginQueue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tinyshellzz.simpleLoginQueue.utils.Pair;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.yaml.snakeyaml.Yaml;

import java.util.*;

public class ObjectPool {
    public static SimpleLoginQueue plugin;

    public final static Gson gson;

    static {
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .create();
    }

    public final static Yaml yaml = new Yaml();

    public static ArrayList<Pair<UUID, Date>> queue = new ArrayList<>();
}
