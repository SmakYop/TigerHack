package com.cheat.tiger.cheat;

import com.cheat.tiger.TigerHack;
import com.cheat.tiger.cheat.protocol.combat.Forcefield;
import com.cheat.tiger.cheat.protocol.movements.Fly;
import com.cheat.tiger.cheat.protocol.movements.SecondFly;
import com.cheat.tiger.cheat.protocol.movements.SpeedHack;
import com.cheat.tiger.manager.LagManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class ProtocolManagement {

    private Plugin plugin;
    private PluginManager pluginManager;

    public ProtocolManagement(Plugin plugin){
        this.plugin = plugin;
        pluginManager = Bukkit.getPluginManager();
    }

    public void registerEvents(){
        pluginManager.registerEvents(new SpeedHack(), plugin);
        pluginManager.registerEvents(new Forcefield(), plugin);
        pluginManager.registerEvents(new Fly(), plugin);
        pluginManager.registerEvents(new SecondFly(), plugin);
    }

    public static void setupProtocols(){
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(TigerHack.getInstance(), new LagManager(), 100L, 1L);
    }
}
