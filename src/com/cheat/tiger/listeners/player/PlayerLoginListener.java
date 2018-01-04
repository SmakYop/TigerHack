package com.cheat.tiger.listeners.player;

import com.cheat.tiger.TigerHack;
import com.cheat.tiger.config.BansConfiguration;
import com.cheat.tiger.config.TigerConfig;
import com.cheat.tiger.database.Database;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.sql.SQLException;

public class PlayerLoginListener implements Listener{

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onPlayerLogin(PlayerLoginEvent event){
        Player player = event.getPlayer();
        if(new TigerConfig().DATABASE_ENABLED){
            try {
                if (Database.profileExistsIntoBans(player.getUniqueId())){
                    event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "§cYou are banned from this server!\nReason: " + Database.getBanReason(player.getUniqueId()));
                    TigerHack.log("[TigerHack Bans] " + player.getName() + " tried to join.");
                    return;
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            return;
        }
        if(new BansConfiguration().playerIsBanned(player.getName())){
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "§cYou are banned from this server!\nReason: " + new BansConfiguration().getBanReason(player.getName()));
            TigerHack.log("[TigerHack Bans] " + player.getName() + " tried to join.");
        }
    }
}
