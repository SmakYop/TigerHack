package com.cheat.tiger.listeners.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener{

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        if(PlayerJoinListener.vapers.contains(player))
            PlayerJoinListener.vapers.remove(player);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onPlayerKickEvent(PlayerKickEvent event){
        if(event.getReason().equalsIgnoreCase("Flying is not enabled on this server")){
            event.setCancelled(true);
        }
        if(event.getReason().equalsIgnoreCase("You are sending too many packets, :(")){
            event.setCancelled(true);
        }
    }
}
