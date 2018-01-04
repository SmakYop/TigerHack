package com.cheat.tiger.config;

import com.cheat.tiger.TigerHack;
import com.cheat.tiger.TigerPlayer;
import com.cheat.tiger.cheat.Cheat;
import com.cheat.tiger.manager.LagManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogsConfiguration {

    private File logsFile;
    private File playerFile;

    public LogsConfiguration() {

    }

    public void loadFilesLogs() {
        if (!TigerHack.getInstance().getDataFolder().exists())
            TigerHack.getInstance().getDataFolder().mkdir();

        this.logsFile = new File(TigerHack.getInstance().getDataFolder().getPath(), "logs");
        if (!logsFile.exists())
            this.logsFile.mkdir();
    }

    public void createPlayerFile(String playerName, Cheat cheat){
        if (!TigerHack.getInstance().getDataFolder().exists())
            TigerHack.getInstance().getDataFolder().mkdir();

        this.logsFile = new File(TigerHack.getInstance().getDataFolder().getPath(), "logs");
        if (!logsFile.exists())
            this.logsFile.mkdir();

        this.playerFile = new File(TigerHack.getInstance().getDataFolder().getPath() + File.separator + "logs", "log_" + playerName + ".data");
        if(!playerFile.exists()) {
            try {
                this.playerFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Date nowDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String date = dateFormat.format(nowDate);

        try {
            YamlConfiguration configuration = YamlConfiguration.loadConfiguration(playerFile);
            configuration.set("TotalLogs", "soon");
            configuration.set("Logs." + date + ".log_for_" + cheat.getCheatReason() + ".packets_send", "soon");
            configuration.set("Logs." + date + ".log_for_" + cheat.getCheatReason() + ".ping", TigerPlayer.get(Bukkit.getPlayer(playerName)).getPing());
            configuration.set("Logs." + date + ".log_for_" + cheat.getCheatReason() + ".tps", LagManager.getTPS());
            configuration.set("Logs." + date + ".log_for_" + cheat.getCheatReason() + ".server_name", Bukkit.getServerName());
            configuration.set("Logs." + date + ".log_for_" + cheat.getCheatReason() + ".reason", cheat.getCheatReason());
            configuration.save(playerFile);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public File getLogsFile(){
        return this.logsFile;
    }

    public File getPlayerFile(){
        return this.playerFile;
    }
}
