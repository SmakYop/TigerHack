package com.cheat.tiger.cheat.protocol.movements;

import com.cheat.tiger.TigerPlayer;
import com.cheat.tiger.util.UtilCheat;
import com.cheat.tiger.util.UtilPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SecondFly implements Listener {

    private Map<UUID, Long> flyTicks = new HashMap<>();

    @EventHandler
    public void checkFly(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        TigerPlayer tigerPlayer = TigerPlayer.get(player);

        if(tigerPlayer.hasBypass()){
            return;
        }
        if (player.getAllowFlight()) {
            return;
        }
        if (player.getVehicle() != null) {
            return;
        }
        if (UtilPlayer.isInWater(player)) {
            return;
        }
        if (UtilCheat.isInWeb(player)) {
            return;
        }
        if (UtilCheat.blocksNear(player)) {
            if (this.flyTicks.containsKey(player.getUniqueId())) {
                this.flyTicks.remove(player.getUniqueId());
            }
            return;
        }
        if ((event.getTo().getX() == event.getFrom().getX()) &&
                (event.getTo().getZ() == event.getFrom().getZ())) {
            return;
        }
        if (event.getTo().getY() != event.getFrom().getY()) {
            if (this.flyTicks.containsKey(player.getUniqueId())) {
                this.flyTicks.remove(player.getUniqueId());
            }
            return;
        }
        long time = System.currentTimeMillis();
        if (this.flyTicks.containsKey(player.getUniqueId())) {
            time = this.flyTicks.get(player.getUniqueId());
        }
        long ms = System.currentTimeMillis() - time;
        if (ms > 500L) {
            this.flyTicks.remove(player.getUniqueId());
            tigerPlayer.updateViolationFlyHack();
            return;
        }
        this.flyTicks.put(player.getUniqueId(), time);
    }
}
