package com.cheat.tiger.cheat.protocol.movements;

import com.cheat.tiger.TigerPlayer;
import com.cheat.tiger.util.UtilCheat;
import com.cheat.tiger.util.UtilMath;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Fly implements Listener {

    private Map<UUID, Map.Entry<Long, Double>> ascensionTicks = new HashMap<>();

    @EventHandler
    public void checkAscention(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        TigerPlayer tigerPlayer = TigerPlayer.get(player);

        if(tigerPlayer.hasBypass()){
            return;
        }
        if (event.getFrom().getY() >= event.getTo().getY()) {
            return;
        }
        if (player.getAllowFlight()) {
            return;
        }
        if (player.getVehicle() != null) {
            return;
        }

        long time = System.currentTimeMillis();
        double totalBlocks = 0.0D;
        if (this.ascensionTicks.containsKey(player.getUniqueId())) {
            time = this.ascensionTicks.get(player.getUniqueId()).getKey();
            totalBlocks = this.ascensionTicks.get(player.getUniqueId()).getValue();
        }
        long ms = System.currentTimeMillis() - time;
        double offsetY = UtilMath.offset(UtilMath.getVerticalVector(event.getFrom().toVector()), UtilMath.getVerticalVector(event.getTo().toVector()));
        if (offsetY > 0.0D) {
            totalBlocks += offsetY;
        }
        if (UtilCheat.blocksNear(player)) {
            totalBlocks = 0.0D;
        }
        Location location = player.getLocation().subtract(0.0D, 1.0D, 0.0D);
        if (UtilCheat.blocksNear(location)) {
            totalBlocks = 0.0D;
        }
        double limit = 0.5D;
        if (player.hasPotionEffect(PotionEffectType.JUMP)) {
            for (PotionEffect effect : player.getActivePotionEffects()) {
                if (effect.getType().equals(PotionEffectType.JUMP)) {
                    int level = effect.getAmplifier() + 1;
                    limit += Math.pow(level + 4.2D, 2.0D) / 16.0D;
                    break;
                }
            }
        }
        if (totalBlocks > limit) {
            if (ms > 500L) {
                tigerPlayer.updateViolationFlyHack();
                time = System.currentTimeMillis();
            }
        } else {
            time = System.currentTimeMillis();
        }
        this.ascensionTicks.put(player.getUniqueId(), new AbstractMap.SimpleEntry<>(time, totalBlocks));
    }
}
