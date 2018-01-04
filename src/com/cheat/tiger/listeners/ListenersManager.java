package com.cheat.tiger.listeners;

import com.cheat.tiger.listeners.bypass.PlayerVelocityListener;
import com.cheat.tiger.listeners.player.PlayerJoinListener;
import com.cheat.tiger.listeners.player.PlayerLoginListener;
import com.cheat.tiger.listeners.player.PlayerQuitListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class ListenersManager {

    private Plugin plugin;
    private PluginManager pluginManager;

    public ListenersManager(Plugin plugin){
        this.plugin = plugin;
        pluginManager = Bukkit.getPluginManager();
    }

    public void registerEvents(){
        pluginManager.registerEvents(new PlayerVelocityListener(), plugin);
        pluginManager.registerEvents(new PlayerLoginListener(), plugin);
        pluginManager.registerEvents(new PlayerJoinListener(), plugin);
        pluginManager.registerEvents(new PlayerQuitListener(), plugin);

    }
}
