package com.cheat.tiger.commands;

import com.cheat.tiger.config.TigerConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TigerCommand implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        Player player = (Player) commandSender;
        if(player == null) return false;

        if(!player.hasPermission("tiger.command") || !player.hasPermission("tiger.*") || !player.isOp()){
            player.sendMessage(new TigerConfig().MESSAGE_NO_PERMISSION);
            return false;
        }

        return false;
    }
}
