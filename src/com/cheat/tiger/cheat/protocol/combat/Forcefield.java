package com.cheat.tiger.cheat.protocol.combat;

import com.cheat.tiger.config.TigerConfig;
import com.cheat.tiger.TigerPlayer;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Iterator;

public class Forcefield implements Listener{

    public Forcefield() {

    }

    @EventHandler
    public void onEntityHit(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
            Player damager = (Player) e.getDamager();
            Player victim = (Player) e.getEntity();

            if(damager.getGameMode().equals(GameMode.CREATIVE))
                return;

            double reach = getEyeLocation(damager).distance(victim.getLocation());
            TigerPlayer tigerPlayer = TigerPlayer.get(damager);
            if (tigerPlayer == null) return;
            double maxreach = 4.3 + victim.getVelocity().length() * 4.0D;
            int ping = ((CraftPlayer) damager).getHandle().ping;
            if ((ping >= 200) && (ping < 250))
                maxreach += 0.4;
            else if ((ping >= 250) && (ping < 300))
                maxreach += 0.8;
            else if ((ping >= 300) && (ping < 350))
                maxreach += 1.2;
            else if ((ping >= 350) && (ping < 400))
                maxreach += 1.5;

            else if (ping > 400) {
                maxreach += 4;
            }

            if(damager.hasPotionEffect(PotionEffectType.SPEED)) {
                int level = 0;
                Iterator iterator = damager.getActivePotionEffects().iterator();
                while(iterator.hasNext()) {
                    PotionEffect Effect = (PotionEffect)iterator.next();
                    if(Effect.getType().equals(PotionEffectType.SPEED)) {
                        level = Effect.getAmplifier();
                        break;
                    }
                }
                switch(level) {
                    case 0:
                        break;
                    case 1:
                        maxreach += 0.1;
                        break;
                    case 2:
                        maxreach += 0.4;
                        break;
                    default:
                        return;
                }
            }

            if (damager.getVelocity().length() > 0.08D) {
                maxreach += damager.getVelocity().length();
            }
            if (damager.getLocation().getY() > victim.getLocation().getY()) {
                double difference = damager.getLocation().getY() - victim.getLocation().getY();
                maxreach += difference / 4.0D;
            } else if (victim.getLocation().getY() > damager.getLocation().getY()) {
                double difference = victim.getLocation().getY() - damager.getLocation().getY();
                maxreach += difference / 4.0D;
            }
            if (reach > maxreach && new TigerConfig().ForcefieldIsEnabled) {
                tigerPlayer.updateViolationForcefieldReach();

            }
        }
    }

    private static Location getEyeLocation(Player player) {
        Location eye = player.getLocation();
        eye.setY(eye.getY() + player.getEyeHeight());
        return eye;
    }
}
