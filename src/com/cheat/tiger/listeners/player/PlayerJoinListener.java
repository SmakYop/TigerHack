package com.cheat.tiger.listeners.player;

import com.cheat.tiger.cheat.Cheat;
import com.cheat.tiger.config.TigerConfig;
import com.cheat.tiger.TigerHack;
import com.cheat.tiger.TigerPlayer;
import com.cheat.tiger.database.Database;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PlayerJoinListener implements Listener, PluginMessageListener {

    public static Set<UUID> vapers = new HashSet<>();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        TigerPlayer.init(player);

        final TigerPlayer tigerPlayer = TigerPlayer.get(event.getPlayer());
        tigerPlayer.justeJoin = true;
        new BukkitRunnable() {
            public void run() {
                tigerPlayer.justeJoin = false;
            }
        }.runTaskLater(TigerHack.getInstance(), 100L);

        if (new TigerConfig().DATABASE_ENABLED) {
            try {
                if (!Database.profileExists(player.getUniqueId())) {
                    Date nowDate = new Date();

                    UUID uuid = player.getUniqueId();
                    String name = player.getName();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String date = dateFormat.format(nowDate);

                    Database.createPlayerProfile(uuid, name, date);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        event.getPlayer().sendMessage("§8 §8 §1 §3 §3 §7 §8 ");
    }

    public void onPluginMessageReceived(String channel, Player player, byte[] data) {
        TigerHack.log(player.getName() + " connected with a Cracked Vape.");
        Cheat.VAPE.ban(TigerPlayer.get(player));
        vapers.add(player.getUniqueId());
    }
}
