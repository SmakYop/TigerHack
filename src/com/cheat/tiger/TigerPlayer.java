package com.cheat.tiger;

import com.cheat.tiger.cheat.Cheat;
import com.cheat.tiger.config.TigerConfig;
import com.cheat.tiger.manager.AlertManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class TigerPlayer {

    public Player player;
    public String name;
    public boolean banned = false;
    public Location currentLocation;
    public Location lastLocation;
    public boolean isOnGround = true;
    public boolean isSneaking = false;
    public boolean isSprinting = false;
    public boolean isClimbing = false;
    private double violationFlyHack = 0.0D;
    private double violationSpeedHack = 0.0D;
    private double violationAntickockBack = 0.0D;
    private double violationForcefield_a = 0.0D;
    private double violationFastBow = 0.0D;
    private double violationFastFoodRegen = 0.0D;
    private double violationNoFall = 0.0D;
    public int walkTicks;
    public int sprintTicks;
    public int sneakTicks;
    public int jumpTicks;
    public int flyTicks;
    public int climbTicks;
    public int liquidAscencionTicks;
    public boolean justDied = false;
    public boolean justRespawned = false;
    public boolean justTeleported = false;
    public boolean justToggledFlight = false;
    public boolean justeJoin = false;
    public boolean justDamageFalling = false;
    public boolean justTakeVelocity = false;
    public int packetFly = 0;
    public int packetFall = 0;
    public int packetPosition = 0;
    public int packetPositionLook = 0;
    public int packetLook = 0;

    private static HashMap<Player, TigerPlayer> tigerPlayers = new HashMap<>();

    public TigerPlayer(Player player) {
        this.player = player;
        this.name = player.getName();
        this.currentLocation = player.getLocation();
        this.lastLocation = this.currentLocation;
        this.banned = false;
    }

    public static TigerPlayer get(Player player){
        return tigerPlayers.get(player);
    }

    public static void init(Player player){
        if(get(player) == null)
            tigerPlayers.put(player, new TigerPlayer(player));
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(this.name);
    }

    public String getPlayerName(){
        return this.name;
    }

    public boolean hasBypass(){
        if(justDied)
            return true;
        if(justRespawned)
            return true;
        if(justTeleported)
            return true;
        if(justToggledFlight)
            return true;
        if(justDamageFalling)
            return true;
        if(justeJoin)
            return true;
        if(justTakeVelocity)
            return true;
        return false;
    }

    /**
     * Reset des Ticks
     * @return
     */
    public void resetTicks() {
        this.sneakTicks = 0;
        this.walkTicks = 0;
        this.sprintTicks = 0;
        this.jumpTicks = 0;
        this.flyTicks = 0;
        this.climbTicks = 0;
        this.liquidAscencionTicks = 0;
    }

    /**
     * Violation Fly
     * @return
     */
    public void updateViolationFlyHack() {
        this.violationFlyHack++;

        if(violationFlyHack % 3 == 0){
            AlertManager alertManager = new AlertManager(this.player);
            alertManager.setCheat(Cheat.FLY);
            alertManager.setViolationLevel((int)this.violationFlyHack);
            alertManager.sendAlert();
        }

        if (this.violationFlyHack >= new TigerConfig().reportFlyHackViolation) {
            if(new TigerConfig().AUTO_BAN_ENABLED && new TigerConfig().FlyIsEnabled && new TigerConfig().FlyAutoban) {
                Cheat.FLY.ban(this);
                this.violationFlyHack = 0.0D;
            }
        }
    }

    /**
     * Violation Forcefield
     * @return
     */
    public void updateViolationForcefieldReach() {
        this.violationForcefield_a++;

        if(violationForcefield_a % 2 == 0){
            AlertManager alertManager = new AlertManager(this.player);
            alertManager.setCheat(Cheat.FORCEFIELD_A);
            alertManager.setViolationLevel((int)this.violationForcefield_a);
            alertManager.sendAlert();
        }

        if (this.violationForcefield_a >= new TigerConfig().reportForcefieldViolation_a) {
            if(new TigerConfig().AUTO_BAN_ENABLED && new TigerConfig().ForcefieldIsEnabled && new TigerConfig().ForcefieldAutoban) {
                Cheat.FORCEFIELD_A.ban(this);
                this.violationForcefield_a = 0.0D;
            }
        }
    }

    /**
     * Violation SpeedHack
     * @return
     */
    public void updateViolationSpeedHack() {
        this.violationSpeedHack++;

        if(violationSpeedHack % 2 == 0) {
            AlertManager alertManager = new AlertManager(this.player);
            alertManager.setCheat(Cheat.SPEEDHACK);
            alertManager.setViolationLevel((int)this.violationSpeedHack);
            alertManager.sendAlert();
        }

        if (this.violationSpeedHack >= new TigerConfig().reportSpeedHackViolation) {
            if(new TigerConfig().AUTO_BAN_ENABLED && new TigerConfig().SpeedHackIsEnabled && new TigerConfig().SpeedHackAutoban) {
                Cheat.SPEEDHACK.ban(this);
                this.violationSpeedHack = 0.0D;
        }
        }
    }

    /**
     * Violation NoFall
     * @return
     */
    public void updateViolationNoFall() {
        this.violationNoFall++;

        if(this.violationNoFall % 2 == 0) {
            AlertManager alertManager = new AlertManager(this.player);
            alertManager.setCheat(Cheat.NOFALL);
            alertManager.setViolationLevel((int)this.violationNoFall);
            alertManager.sendAlert();
        }

        if (this.violationNoFall >= new TigerConfig().reportNoFallViolation) {
            if(new TigerConfig().AUTO_BAN_ENABLED && new TigerConfig().NoFallIsEnabled && new TigerConfig().NoFallAutoban) {
                Cheat.NOFALL.ban(this);
                this.violationNoFall = 0.0D;
            }
        }
    }

    /**
     * Violation FastBow
     * @return
     */
    public void updateViolationFastBow() {
        this.violationFastBow++;

        if(this.violationFastBow % 3 == 0) {
            AlertManager alertManager = new AlertManager(this.player);
            alertManager.setCheat(Cheat.FASTBOW);
            alertManager.setViolationLevel((int)this.violationFastBow);
            alertManager.sendAlert();
        }

        if (this.violationFastBow >= new TigerConfig().reportRapidBowShootViolation) {
            if(new TigerConfig().AUTO_BAN_ENABLED && new TigerConfig().FastBowIsEnabled && new TigerConfig().FastBowAutoban) {
                Cheat.FASTBOW.ban(this);
                this.violationFastBow = 0.0D;
            }
        }
    }

    /**
     * Violation AntiVelocity
     * @return
     */
    public void updateViolationAntiVelocity() {
        this.violationAntickockBack++;
        if (this.violationAntickockBack >= new TigerConfig().reportAntiVelocityViolation) {
            if(new TigerConfig().AUTO_BAN_ENABLED && new TigerConfig().AntiknockbackIsEnabled && new TigerConfig().AntiknockbackAutoban) {
                Cheat.ANTIKNOCKBACK.ban(this);
                this.violationAntickockBack = 0.0D;
            }

        }
    }

    /**
     * Violation FastFoodRegen
     * @return
     */
    public void updateViolationFastFoodRegen(long violation) {
        this.violationFastFoodRegen += violation;
        if (this.violationFastFoodRegen >= new TigerConfig().reportFastFoodRegenViolation) {
            if(new TigerConfig().AUTO_BAN_ENABLED && new TigerConfig().FastFoodRegenIsEnabled && new TigerConfig().FastFoodRegenAutoban){
                Cheat.FASTFODDREGEN.ban(this);
                this.violationFastFoodRegen = 0.0D;
            }
        }
    }

    /**
     * Update données.
     */

    public void updateSneak() {
        this.sneakTicks += 1;
        this.isSneaking = true;
        this.isSprinting = false;
        this.isClimbing = false;
    }

    public void updateSprint() {
        this.sprintTicks += 1;
        this.isSneaking = false;
        this.isSprinting = true;
        this.isClimbing = false;
    }

    public void updateClimb() {
        this.climbTicks += 1;
        this.isClimbing = true;
    }

    public void updateWalk() {
        this.walkTicks += 1;
        this.isSneaking = false;
        this.isSprinting = false;
        this.isClimbing = false;
    }

    public boolean isOnGround() {
        this.isOnGround = false;
        Block block = this.currentLocation.getBlock().getRelative(0, -1, 0);
        if (this.currentLocation.subtract(0.0D, 0.1D, 0.0D).getBlock().getType().isSolid()) {
            this.isOnGround = true;
        } else if (this.player.isSneaking()) {
            for (int x = -1; x <= 1; x++) {
                for (int z = -1; z <= 1; z++) {
                    if ((x != 0) || (z != 0)) {
                        if (block.getRelative(x, 0, z).getType().isSolid()) {
                            this.isOnGround = true;
                            break;
                        }
                    }
                }
            }
        }
        Block block2 = this.currentLocation.getBlock();
        if (((block2.getType().equals(Material.LADDER)) || (block2.getType().equals(Material.VINE))) &&
                (!this.currentLocation.subtract(0.0D, 0.1D, 0.0D).getBlock().getType().isSolid())) {
            this.isOnGround = false;
        }
        return this.isOnGround;
    }

    public int getTotalMoveTicks() {
        return this.walkTicks + this.sprintTicks + this.sneakTicks + this.climbTicks;
    }

    /**
     * Get ping
     * @return
     */
    public int getPing(){
       return ((CraftPlayer) this.player).getHandle().ping;
    }
}
