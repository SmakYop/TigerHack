package com.cheat.tiger.manager;

import com.cheat.tiger.config.LogsConfiguration;
import com.cheat.tiger.config.TigerConfig;
import com.cheat.tiger.TigerPlayer;
import com.cheat.tiger.cheat.Cheat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class AlertManager {

    public Player playerCheat;
    private Cheat cheat;
    private TigerPlayer tigerPlayer;
    private int violationLevel;

    public AlertManager(Player playerCheat){
        this.playerCheat = playerCheat;
        this.tigerPlayer = TigerPlayer.get(playerCheat);
    }

    public Cheat getCheat(){
        return this.cheat;
    }

    public void setCheat(Cheat cheat){
        this.cheat = cheat;
    }

    public int getViolationLevel(){
        return this.violationLevel;
    }

    public void setViolationLevel(int violationLevel){
        this.violationLevel = violationLevel;
    }

    public void sendAlert(){

        new LogsConfiguration().createPlayerFile(this.tigerPlayer.getPlayerName(), cheat);

        for(Player players : Bukkit.getServer().getOnlinePlayers()){
            if(cheat == Cheat.FORCEFIELD_A){
                if(new TigerConfig().DEBUG_ENABLED){
                    Bukkit.broadcastMessage(new TigerConfig().MESSAGE_ALERT.replace("%cheatreason%", Cheat.FORCEFIELD_A.getCheatReason()).replace("%player%", this.tigerPlayer.getPlayerName()).replace("%prefixstaff%", new TigerConfig().PREFIX_STAFF)
                            .replace("%playerping%", "" + this.tigerPlayer.getPing()).replace("%violation%", "" + this.violationLevel));
                    return;
                }
                if(players.hasPermission("tigerhack.alert.view") && new TigerConfig().ALERT_FOR_STAFF_ENABLED && new TigerConfig().ForcefieldAlert) {
                    players.sendMessage(new TigerConfig().MESSAGE_ALERT.replace("%cheatreason%", Cheat.FORCEFIELD_A.getCheatReason()).replace("%player%", this.tigerPlayer.getPlayerName()).replace("%prefixstaff%", new TigerConfig().PREFIX_STAFF)
                            .replace("%playerping%", "" + this.tigerPlayer.getPing()).replace("%violation%", "" + this.violationLevel));
                }
            }

            else if(cheat == Cheat.FORCEFIELD_B){
                if(new TigerConfig().DEBUG_ENABLED){
                    Bukkit.broadcastMessage(new TigerConfig().MESSAGE_ALERT.replace("%cheatreason%", Cheat.FORCEFIELD_B.getCheatReason()).replace("%player%", this.tigerPlayer.getPlayerName()).replace("%prefixstaff%", new TigerConfig().PREFIX_STAFF)
                            .replace("%playerping%", "" + this.tigerPlayer.getPing()).replace("%violation%", "" + this.violationLevel));
                    return;
                }
                if(players.hasPermission("tigerhack.alert.view") && new TigerConfig().ALERT_FOR_STAFF_ENABLED && new TigerConfig().ForcefieldAlert) {
                    players.sendMessage(new TigerConfig().MESSAGE_ALERT.replace("%cheatreason%", Cheat.FORCEFIELD_B.getCheatReason()).replace("%player%", this.tigerPlayer.getPlayerName()).replace("%prefixstaff%", new TigerConfig().PREFIX_STAFF)
                            .replace("%playerping%", "" + this.tigerPlayer.getPing()).replace("%violation%", "" + this.violationLevel));
                }
            }

           else if(cheat == Cheat.FLY){
                if(new TigerConfig().DEBUG_ENABLED){
                    Bukkit.broadcastMessage(new TigerConfig().MESSAGE_ALERT.replace("%cheatreason%", Cheat.FLY.getCheatReason()).replace("%player%", this.tigerPlayer.getPlayerName()).replace("%prefixstaff%", new TigerConfig().PREFIX_STAFF)
                            .replace("%playerping%", "" + this.tigerPlayer.getPing()).replace("%violation%", "" + this.violationLevel));
                    return;
                }
                if(players.hasPermission("tigerhack.alert.view") && new TigerConfig().ALERT_FOR_STAFF_ENABLED && new TigerConfig().FlyAlert) {
                    players.sendMessage(new TigerConfig().MESSAGE_ALERT.replace("%cheatreason%", Cheat.FLY.getCheatReason()).replace("%player%", this.tigerPlayer.getPlayerName()).replace("%prefixstaff%", new TigerConfig().PREFIX_STAFF)
                            .replace("%playerping%", "" + this.tigerPlayer.getPing()).replace("%violation%", "" + this.violationLevel));
                }
            }

            else if(cheat == Cheat.FASTFODDREGEN){
                if(new TigerConfig().DEBUG_ENABLED){
                    Bukkit.broadcastMessage(new TigerConfig().MESSAGE_ALERT.replace("%cheatreason%", Cheat.FASTFODDREGEN.getCheatReason()).replace("%player%", this.tigerPlayer.getPlayerName()).replace("%prefixstaff%", new TigerConfig().PREFIX_STAFF)
                            .replace("%playerping%", "" + this.tigerPlayer.getPing()).replace("%violation%", "" + this.violationLevel));
                    return;
                }
                if(players.hasPermission("tigerhack.alert.view") && new TigerConfig().ALERT_FOR_STAFF_ENABLED && new TigerConfig().FastFoodRegenAlert) {
                    players.sendMessage(new TigerConfig().MESSAGE_ALERT.replace("%cheatreason%", Cheat.FASTFODDREGEN.getCheatReason()).replace("%player%", this.tigerPlayer.getPlayerName()).replace("%prefixstaff%", new TigerConfig().PREFIX_STAFF)
                            .replace("%playerping%", "" + this.tigerPlayer.getPing()).replace("%violation%", "" + this.violationLevel));
                }
            }

           else if(cheat == Cheat.NOFALL){
                if(new TigerConfig().DEBUG_ENABLED){
                    Bukkit.broadcastMessage(new TigerConfig().MESSAGE_ALERT.replace("%cheatreason%", Cheat.NOFALL.getCheatReason()).replace("%player%", this.tigerPlayer.getPlayerName()).replace("%prefixstaff%", new TigerConfig().PREFIX_STAFF)
                            .replace("%playerping%", "" + this.tigerPlayer.getPing()).replace("%violation%", "" + this.violationLevel));
                    return;
                }
                if(players.hasPermission("tigerhack.alert.view") && new TigerConfig().ALERT_FOR_STAFF_ENABLED && new TigerConfig().NoFallAlert) {
                    players.sendMessage(new TigerConfig().MESSAGE_ALERT.replace("%cheatreason%", Cheat.NOFALL.getCheatReason()).replace("%player%", this.tigerPlayer.getPlayerName()).replace("%prefixstaff%", new TigerConfig().PREFIX_STAFF)
                            .replace("%playerping%", "" + this.tigerPlayer.getPing()).replace("%violation%", "" + this.violationLevel));
                }
            }

            else if(cheat == Cheat.ANTIKNOCKBACK){
                if(new TigerConfig().DEBUG_ENABLED){
                    Bukkit.broadcastMessage(new TigerConfig().MESSAGE_ALERT.replace("%cheatreason%", Cheat.ANTIKNOCKBACK.getCheatReason()).replace("%player%", this.tigerPlayer.getPlayerName()).replace("%prefixstaff%", new TigerConfig().PREFIX_STAFF)
                            .replace("%playerping%", "" + this.tigerPlayer.getPing()).replace("%violation%", "" + this.violationLevel));
                    return;
                }
                if(players.hasPermission("tigerhack.alert.view") && new TigerConfig().ALERT_FOR_STAFF_ENABLED && new TigerConfig().AntiknockbackAlert) {
                    players.sendMessage(new TigerConfig().MESSAGE_ALERT.replace("%cheatreason%", Cheat.ANTIKNOCKBACK.getCheatReason()).replace("%player%", this.tigerPlayer.getPlayerName()).replace("%prefixstaff%", new TigerConfig().PREFIX_STAFF)
                            .replace("%playerping%", "" + this.tigerPlayer.getPing()).replace("%violation%", "" + this.violationLevel));
                }
            }

            else  if(cheat == Cheat.SPEEDHACK){
                if(new TigerConfig().DEBUG_ENABLED){
                    Bukkit.broadcastMessage(new TigerConfig().MESSAGE_ALERT.replace("%cheatreason%", Cheat.SPEEDHACK.getCheatReason()).replace("%player%", this.tigerPlayer.getPlayerName()).replace("%prefixstaff%", new TigerConfig().PREFIX_STAFF)
                            .replace("%playerping%", "" + this.tigerPlayer.getPing()).replace("%violation%", "" + this.violationLevel));
                    return;
                }
                if(players.hasPermission("tigerhack.alert.view") && new TigerConfig().ALERT_FOR_STAFF_ENABLED && new TigerConfig().SpeedHackAlert) {
                    players.sendMessage(new TigerConfig().MESSAGE_ALERT.replace("%cheatreason%", Cheat.SPEEDHACK.getCheatReason()).replace("%player%", this.tigerPlayer.getPlayerName()).replace("%prefixstaff%", new TigerConfig().PREFIX_STAFF)
                            .replace("%playerping%", "" + this.tigerPlayer.getPing()).replace("%violation%", "" + this.violationLevel));
                }
            }

            else  if(cheat == Cheat.FASTBOW){
                if(new TigerConfig().DEBUG_ENABLED){
                    Bukkit.broadcastMessage(new TigerConfig().MESSAGE_ALERT.replace("%cheatreason%", Cheat.FASTBOW.getCheatReason()).replace("%player%", this.tigerPlayer.getPlayerName()).replace("%prefixstaff%", new TigerConfig().PREFIX_STAFF)
                            .replace("%playerping%", "" + this.tigerPlayer.getPing()).replace("%violation%", "" + this.violationLevel));
                    return;
                }
                if(players.hasPermission("tigerhack.alert.view") && new TigerConfig().ALERT_FOR_STAFF_ENABLED && new TigerConfig().FastBowAlert) {
                    players.sendMessage(new TigerConfig().MESSAGE_ALERT.replace("%cheatreason%", Cheat.FASTBOW.getCheatReason()).replace("%player%", this.tigerPlayer.getPlayerName()).replace("%prefixstaff%", new TigerConfig().PREFIX_STAFF)
                            .replace("%playerping%", "" + this.tigerPlayer.getPing()).replace("%violation%", "" + this.violationLevel));
                }
            }
        }
    }
}
