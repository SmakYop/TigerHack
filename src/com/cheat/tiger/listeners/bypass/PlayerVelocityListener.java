package com.cheat.tiger.listeners.bypass;

import com.cheat.tiger.TigerHack;
import com.cheat.tiger.TigerPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerVelocityListener implements Listener{

    @EventHandler
    public void onPlayerVelocity(PlayerVelocityEvent event){
        Player player = event.getPlayer();
        final TigerPlayer tigerPlayer = TigerPlayer.get(player);

        tigerPlayer.justTakeVelocity = true;
        new BukkitRunnable() {
            public void run() {
                tigerPlayer.justTakeVelocity = false;
            }
        }.runTaskLater(TigerHack.getInstance(), 20L);
    }
}
