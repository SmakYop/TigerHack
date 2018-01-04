package com.cheat.tiger.config;

import com.cheat.tiger.TigerHack;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class BansConfiguration {

    private File bansFile;

    public BansConfiguration() {

    }

    public void loadFileBans() {
        try {
            if (!TigerHack.getInstance().getDataFolder().exists()) TigerHack.getInstance().getDataFolder().mkdir();
            this.bansFile = new File(TigerHack.getInstance().getDataFolder().getPath(), "bans.yml");
            if (!bansFile.exists()) bansFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveBannedPlayer(UUID uuid, String name, String reason, String date, String serverName, int ping) {
        try {
            File file = new File(TigerHack.getInstance().getDataFolder(), "bans.yml");
            if (!file.exists()) {
                return;
            }

            FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
            configuration.set("Bans." + name + ".uuid", uuid.toString());
            configuration.set("Bans." + name + ".reason", reason);
            configuration.set("Bans." + name + ".date", date);
            configuration.set("Bans." + name + ".server_name", serverName);
            configuration.set("Bans." + name + ".ping", ping);
            configuration.save(file);

            TigerHack.log("[TigerHack Bans Configuration] Player added!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean playerIsBanned(String playerName) {
        File file = new File(TigerHack.getInstance().getDataFolder(), "bans.yml");
        if (!file.exists()) {
            return false;
        }

        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        if(configuration.get("Bans." + playerName) != null) return true;

        return false;
    }

    public String getBanReason(String playerName){
        File file = new File(TigerHack.getInstance().getDataFolder(), "bans.yml");
        if (!file.exists()) {
            return "";
        }

        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        return configuration.getString("Bans." + playerName + ".reason");
    }
}
