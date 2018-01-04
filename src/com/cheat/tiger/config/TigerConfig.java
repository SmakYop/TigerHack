package com.cheat.tiger.config;

import com.cheat.tiger.TigerHack;

public class TigerConfig {

    public TigerConfig() {

    }

    /**
     * Configuration général
     */

    public String PREFIX = TigerHack.getInstance().getConfig().getString("General.Prefix").replace('&', '§');
    public String PREFIX_STAFF = TigerHack.getInstance().getConfig().getString("General.PrefixStaff").replace('&', '§');
    public boolean DATABASE_ENABLED = TigerHack.getInstance().getConfig().getBoolean("Database.enabled");
    public String DATABASE_USER = TigerHack.getInstance().getConfig().getString("Database.user").replace('&', '§');
    public String DATABASE_PASSWORD = TigerHack.getInstance().getConfig().getString("Database.password").replace('&', '§');
    public String DATABASE_URL = TigerHack.getInstance().getConfig().getString("Database.url").replace('&', '§');
    public boolean AUTO_BAN_ENABLED = TigerHack.getInstance().getConfig().getBoolean("AutoBan");
    public boolean OP_PLAYER_CAN_BE_BANNED = TigerHack.getInstance().getConfig().getBoolean("OpPlayerCanBeBanned");
    public boolean REPORT_ENABLED = TigerHack.getInstance().getConfig().getBoolean("CommandForReport");
    public boolean ALERT_FOR_STAFF_ENABLED = TigerHack.getInstance().getConfig().getBoolean("AlertStaff");
    public boolean FIREWORK_ENABLED = TigerHack.getInstance().getConfig().getBoolean("Firework.enabled");
    public boolean BROADCAST_ENABLED = TigerHack.getInstance().getConfig().getBoolean("BroadcastOnBan.enabled");
    public String BROADCAST_MESSAGE = TigerHack.getInstance().getConfig().getString("BroadcastOnBan.message").replace('&', '§');
    public boolean DEBUG_ENABLED = TigerHack.getInstance().getConfig().getBoolean("debug");
    public String MESSAGE_ALERT = TigerHack.getInstance().getConfig().getString("Message.alertMessage").replace('&', '§');
    public String MESSAGE_NO_PERMISSION = TigerHack.getInstance().getConfig().getString("Message.noPermission").replace('&', '§');

    /**
     * Configuration des cheats
     */

    public boolean ForcefieldIsEnabled = TigerHack.getInstance().getConfig().getBoolean("CheatsConfiguration.Forcefield.enabled");
    public boolean ForcefieldAutoban = TigerHack.getInstance().getConfig().getBoolean("CheatsConfiguration.Forcefield.autoban");
    public boolean ForcefieldAlert = TigerHack.getInstance().getConfig().getBoolean("CheatsConfiguration.Forcefield.alert");
    private int ForcefieldMaxVL = TigerHack.getInstance().getConfig().getInt("CheatsConfiguration.Forcefield.VLForBan");

    public boolean FlyIsEnabled = TigerHack.getInstance().getConfig().getBoolean("CheatsConfiguration.Fly.enabled");
    public boolean FlyAutoban = TigerHack.getInstance().getConfig().getBoolean("CheatsConfiguration.Fly.autoban");
    public boolean FlyAlert = TigerHack.getInstance().getConfig().getBoolean("CheatsConfiguration.Fly.alert");
    private int FlyMaxVL = TigerHack.getInstance().getConfig().getInt("CheatsConfiguration.Fly.VLForBan");

    public boolean SpeedHackIsEnabled = TigerHack.getInstance().getConfig().getBoolean("CheatsConfiguration.SpeedHack.enabled");
    public boolean SpeedHackAutoban = TigerHack.getInstance().getConfig().getBoolean("CheatsConfiguration.SpeedHack.autoban");
    public boolean SpeedHackAlert = TigerHack.getInstance().getConfig().getBoolean("CheatsConfiguration.SpeedHack.alert");
    private int SpeedHackMaxVL = TigerHack.getInstance().getConfig().getInt("CheatsConfiguration.SpeedHack.VLForBan");

    public boolean NoFallIsEnabled = TigerHack.getInstance().getConfig().getBoolean("CheatsConfiguration.NoFall.enabled");
    public boolean NoFallAutoban = TigerHack.getInstance().getConfig().getBoolean("CheatsConfiguration.NoFall.autoban");
    public boolean NoFallAlert = TigerHack.getInstance().getConfig().getBoolean("CheatsConfiguration.NoFall.alert");
    private int NoFallMaxVL = TigerHack.getInstance().getConfig().getInt("CheatsConfiguration.NoFall.VLForBan");

    public boolean FastFoodRegenIsEnabled = TigerHack.getInstance().getConfig().getBoolean("CheatsConfiguration.FastFoodRegen.enabled");
    public boolean FastFoodRegenAutoban = TigerHack.getInstance().getConfig().getBoolean("CheatsConfiguration.FastFoodRegen.autoban");
    public boolean FastFoodRegenAlert = TigerHack.getInstance().getConfig().getBoolean("CheatsConfiguration.FastFoodRegen.alert");
    private int FastFoodRegenMaxVL = TigerHack.getInstance().getConfig().getInt("CheatsConfiguration.FastFoodRegen.VLForBan");

    public boolean AntiknockbackIsEnabled = TigerHack.getInstance().getConfig().getBoolean("CheatsConfiguration.Antiknockback.enabled");
    public boolean AntiknockbackAutoban = TigerHack.getInstance().getConfig().getBoolean("CheatsConfiguration.Antiknockback.autoban");
    public boolean AntiknockbackAlert = TigerHack.getInstance().getConfig().getBoolean("CheatsConfiguration.Antiknockback.alert");
    private int AntiknockbackMaxVL = TigerHack.getInstance().getConfig().getInt("CheatsConfiguration.Antiknockback.VLForBan");

    public boolean FastBowIsEnabled = TigerHack.getInstance().getConfig().getBoolean("CheatsConfiguration.FastBow.enabled");
    public boolean FastBowAutoban = TigerHack.getInstance().getConfig().getBoolean("CheatsConfiguration.FastBow.autoban");
    public boolean FastBowAlert = TigerHack.getInstance().getConfig().getBoolean("CheatsConfiguration.FastBow.alert");
    private int FastBowMaxVL = TigerHack.getInstance().getConfig().getInt("CheatsConfiguration.FastBow.VLForBan");

    /**
     * Déclencheur de report
     */
    public int reportForcefieldViolation_a = ForcefieldMaxVL;
    public int reportSpeedHackViolation = SpeedHackMaxVL;
    public int reportFlyHackViolation = FlyMaxVL;
    public int reportFastFoodRegenViolation = FastFoodRegenMaxVL;
    public int reportRapidBowShootViolation = FastBowMaxVL;
    public int reportAntiVelocityViolation = AntiknockbackMaxVL;
    public int reportNoFallViolation = NoFallMaxVL;
}
