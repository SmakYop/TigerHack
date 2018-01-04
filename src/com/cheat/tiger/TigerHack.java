package com.cheat.tiger;

import com.cheat.tiger.cheat.ProtocolManagement;
import com.cheat.tiger.commands.TigerCommand;
import com.cheat.tiger.config.BansConfiguration;
import com.cheat.tiger.config.LogsConfiguration;
import com.cheat.tiger.config.TigerConfig;
import com.cheat.tiger.database.Database;
import com.cheat.tiger.listeners.ListenersManager;
import com.cheat.tiger.listeners.player.PlayerJoinListener;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public class TigerHack extends JavaPlugin{

    private static TigerHack instance;
    private static ProtocolManager protocolManager;

    @Override
    public void onEnable() {
        TigerHack.instance = this;
        TigerHack.protocolManager = ProtocolLibrary.getProtocolManager();

        this.getCommand("tigerhack").setExecutor(new TigerCommand());
        new ListenersManager(this).registerEvents();
        new ProtocolManagement(this).registerEvents();
        new LogsConfiguration().loadFilesLogs();

        ProtocolManagement.setupProtocols();
        sendConfigurationMessage();
        saveDefaultConfig();

        PlayerJoinListener playerJoinListener = new PlayerJoinListener();
        getServer().getMessenger().registerIncomingPluginChannel(this, "LOLIMAHCKER", playerJoinListener);

        if(this.getConfig().getBoolean("Database.enabled")) {
            connectDatabase();
            return;
        }

        new BansConfiguration().loadFileBans();
    }

    @Override
    public void onDisable() {
        TigerHack.instance = null;
    }

    public static TigerHack getInstance(){
        return TigerHack.instance;
    }

    public static void log(String log){
        instance.getLogger().info(log);
    }

    private void connectDatabase(){
        if (getConfig().getBoolean("Database.enabled")) {
            String url = new TigerConfig().DATABASE_URL;
            String user = new TigerConfig().DATABASE_USER;
            String pass = new TigerConfig().DATABASE_PASSWORD;
            Database.setAuth(url, user, pass);
            try {
                Database.connect();
                Database.createTableTigerHack();
                Database.createTableTigerHackBans();
            } catch (SQLException e) {
                log("[TigerHack Database Connection]" + e.getMessage());
            }
        }
    }

    private void sendConfigurationMessage(){
        log("---  TigerHack | Anticheat  ---");
        log("");
        log("Plugin Enabled: true");
        if(new TigerConfig().DATABASE_ENABLED)
            log("Database: enabled");
        else
            log("Database: disabled");
        log("Bungeecord: disabled");
        if(new TigerConfig().AUTO_BAN_ENABLED)
            log("AutoBan: enabled");
        else
            log("Autoban: disabled");
        if(new TigerConfig().OP_PLAYER_CAN_BE_BANNED)
            log("OpPlayerCanBeBanned: enabled");
        else
            log("OpPlayerCanBeBanned: disabled");
        if(new TigerConfig().REPORT_ENABLED)
            log("Report: enabled");
        else
            log("Report: disabled");
        if(new TigerConfig().ALERT_FOR_STAFF_ENABLED)
            log("Alert: enabled");
        else
            log("Alert: disabled");
        log("");
        log("---  TigerHack | Anticheat  ---");
    }

    public static ProtocolManager getProtocolManager(){
        return protocolManager;
    }
}
