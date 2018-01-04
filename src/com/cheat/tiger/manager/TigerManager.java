package com.cheat.tiger.manager;

import com.cheat.tiger.TigerHack;
import com.cheat.tiger.database.Database;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.bukkit.Bukkit.getOnlinePlayers;
import static org.bukkit.Bukkit.getServer;

public class TigerManager {

    private List<Firework> fireworks = new ArrayList<>();

    public TigerManager() {

    }

    public void spawnFirework(Location location) {
        for (int i = 0; i < 2; i++) {
            Firework f = location.getWorld().spawn(location, Firework.class);
            FireworkMeta fm = f.getFireworkMeta();
            fm.addEffect(getColorFireworkEffect());
            f.setFireworkMeta(fm);
            fireworks.add(f);
        }
        Bukkit.getScheduler().runTaskLater(TigerHack.getInstance(), new Runnable() {
            public void run() {
                for (Firework f : fireworks)
                    f.detonate();
            }
        }, 2L);
    }

    private FireworkEffect getColorFireworkEffect() {
        FireworkEffect.Builder builder = FireworkEffect.builder();
        FireworkEffect effect = builder.flicker(false).trail(false).with(FireworkEffect.Type.BALL_LARGE).withColor(Color.RED).withFade(Color.RED).build();
        return effect;
    }
}
