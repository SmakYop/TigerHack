package com.cheat.tiger.cheat;

import com.cheat.tiger.config.TigerConfig;
import com.cheat.tiger.TigerHack;
import com.cheat.tiger.TigerPlayer;
import com.cheat.tiger.database.Database;
import com.cheat.tiger.manager.TigerManager;
import org.bukkit.Bukkit;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public enum  Cheat {

    FORCEFIELD_A(1, "Forcefield (Reach)"),
    FORCEFIELD_B(2, "Forcefield (Aimbot)"),
    FLY(3, "Fly"),
    FASTFODDREGEN(4, "FastFoodRegen"),
    NOFALL(5, "NoFall"),
    ANTIKNOCKBACK(6, "AntiKnockback"),
    SNEAK(7, "Sneak"),
    SPEEDHACK(8, "SpeedHack"),
    FASTBOW(9, "FastBow"),
    VAPE(10, "Cracked Vape");

    private int id;
    private String reason;
    private String bite;
    private String banReason = "Unfair Advantage";

    Cheat(int id, String reason){
        this.id = id;
        this.reason = reason;
    }

    public int getCheatId(){
        return id;
    }

    public String getCheatReason(){
        return reason;
    }

    public String getBanReason(){
        return this.banReason;
    }

    public void ban(TigerPlayer tigerPlayer){
        Date nowDate = new Date();
        UUID uuid = tigerPlayer.getPlayer().getUniqueId();
        String name = tigerPlayer.getPlayer().getName();
        String reason = this.banReason;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = dateFormat.format(nowDate);
        String serverName = Bukkit.getServerName();
        int ping = tigerPlayer.getPing();

        if(new TigerConfig().DATABASE_ENABLED){
            try{
                if(!Database.profileExistsIntoBans(tigerPlayer.getPlayer().getUniqueId())){
                    Database.createPlayerBanProfile(uuid, name, reason, date, serverName, ping);
                }
            }catch (SQLException e){
                e.printStackTrace();
            }

            if(new TigerConfig().BROADCAST_ENABLED)
                Bukkit.getServer().broadcastMessage(new TigerConfig().BROADCAST_MESSAGE.replace("%reason%", this.banReason).replace("%player%", tigerPlayer.getPlayerName()).replace('&', '§'));
            if(new TigerConfig().FIREWORK_ENABLED)
                new TigerManager().spawnFirework(tigerPlayer.getPlayer().getLocation());
            TigerHack.log(tigerPlayer.getPlayerName() + " has been banned for: " + this.reason);
            tigerPlayer.getPlayer().kickPlayer("§cYou are banned from this server!\n§cReason: " + this.banReason);
            return;
        }

        if(new TigerConfig().BROADCAST_ENABLED)
            Bukkit.getServer().broadcastMessage(new TigerConfig().BROADCAST_MESSAGE.replace("%reason%", this.banReason).replace("%player%", tigerPlayer.getPlayerName()).replace('&', '§'));
        if(new TigerConfig().FIREWORK_ENABLED)
            new TigerManager().spawnFirework(tigerPlayer.getPlayer().getLocation());
        TigerHack.log(tigerPlayer.getPlayerName() + " has been banned for: " + this.reason);
        //tigerPlayer.getPlayer().kickPlayer("§cYou are banned from this server!\n§cReason: " + this.banReason);
        //new BansConfiguration().saveBannedPlayer(uuid, name, reason, date, serverName, ping);
    }
}
