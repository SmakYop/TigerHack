package com.cheat.tiger.cheat.protocol.movements;

import com.cheat.tiger.config.TigerConfig;
import com.cheat.tiger.TigerPlayer;
import com.cheat.tiger.util.UtilCheat;
import com.cheat.tiger.util.UtilMath;
import com.cheat.tiger.util.UtilPlayer;
import com.cheat.tiger.util.UtilTime;
import org.bukkit.Location;
import org.bukkit.Material;
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

public class SpeedHack implements Listener {

    private Map<UUID, Map.Entry<Integer, Long>> speedTicks = new HashMap<>();
    private Map<UUID, Map.Entry<Integer, Long>> tooFastTicks = new HashMap<>();

    private boolean isOnIce(Player player) {
        Location playerLocation = player.getLocation();
        playerLocation.setY(playerLocation.getY() - 1.0D);
        if (playerLocation.getBlock().getType().equals(Material.ICE)) {
            return true;
        }
        playerLocation.setY(playerLocation.getY() - 1.0D);
        if (playerLocation.getBlock().getType().equals(Material.ICE)) {
            return true;
        }
        return false;
    }

    @EventHandler
    public void checkSpeed(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if ((event.getFrom().getX() == event.getTo().getX()) && (event.getFrom().getY() == event.getTo().getY()) &&
                (event.getFrom().getZ() == event.getFrom().getZ())) {
            return;
        }
        if(TigerPlayer.get(player).hasBypass())
            return;
        if (player.getAllowFlight()) {
            return;
        }
        if (player.getVehicle() != null) {
            return;
        }
        int count = 0;
        long time = UtilTime.nowlong();
        if (this.speedTicks.containsKey(player.getUniqueId())) {
            count = (Integer) ((Map.Entry) this.speedTicks.get(player.getUniqueId())).getKey();
            time = (Long) ((Map.Entry) this.speedTicks.get(player.getUniqueId())).getValue();
        }
        int TooFastCount = 0;
        if (this.tooFastTicks.containsKey(player.getUniqueId())) {
            double OffsetXZ = UtilMath.offset(UtilMath.getHorizontalVector(event.getFrom().toVector()), UtilMath.getHorizontalVector(event.getTo().toVector()));
            double LimitXZ;
            if ((UtilPlayer.isOnGround(player)) && (player.getVehicle() == null)) {
                LimitXZ = 0.33D;
            } else {
                LimitXZ = 0.4D;
            }
            if (UtilCheat.slabsNear(player.getLocation())) {
                LimitXZ += 0.05D;
            }
            Location b = UtilPlayer.getEyeLocation(player);
            b.add(0.0D, 1.0D, 0.0D);
            if ((b.getBlock().getType() != Material.AIR) && (!UtilCheat.canStandWithin(b.getBlock()))) {
                LimitXZ = 0.69D;
            }
            if (isOnIce(player)) {
                if ((b.getBlock().getType() != Material.AIR) && (!UtilCheat.canStandWithin(b.getBlock()))) {
                    LimitXZ = 1.0D;
                } else {
                    LimitXZ = 0.75D;
                }
            }
            float speed = player.getWalkSpeed();
            LimitXZ += (speed > 0.2F ? speed * 10.0F * 0.33F : 0.0F);
            for (PotionEffect effect : player.getActivePotionEffects()) {
                if (effect.getType().equals(PotionEffectType.SPEED)) {
                    if (player.isOnGround()) {
                        LimitXZ += 0.06D * (effect.getAmplifier() + 1);
                    } else {
                        LimitXZ += 0.02D * (effect.getAmplifier() + 1);
                    }
                }
            }
            if ((OffsetXZ > LimitXZ) && (!UtilTime.elapsed((Long) ((Map.Entry) this.tooFastTicks.get(player.getUniqueId())).getValue(), 150L))) {
                TooFastCount = (Integer) ((Map.Entry) this.tooFastTicks.get(player.getUniqueId())).getKey() + 1;
            } else {
                TooFastCount = 0;
            }
        }
        if (TooFastCount > 6) {
            TooFastCount = 0;
            count++;
        }
        if ((this.speedTicks.containsKey(player.getUniqueId())) &&
                (UtilTime.elapsed(time, 60000L))) {
            count = 0;
            time = UtilTime.nowlong();
        }
        if (count >= 4) {
            count = 0;

            if(new TigerConfig().SpeedHackIsEnabled){
               TigerPlayer.get(player).updateViolationSpeedHack();
            }
        }
        this.tooFastTicks.put(player.getUniqueId(), new AbstractMap.SimpleEntry<>(TooFastCount, System.currentTimeMillis()));
        this.speedTicks.put(player.getUniqueId(), new AbstractMap.SimpleEntry<>(count, time));
    }

}
