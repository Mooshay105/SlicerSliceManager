package net.antlertech.slicerslicemanager.commands;

import net.antlertech.slicerslicemanager.SQL.SQLUtil;
import net.antlertech.slicerslicemanager.models.messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.sql.SQLException;
import java.util.UUID;

public class claimCommand implements CommandExecutor {
    int statusCode;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            UUID playerUUID = p.getUniqueId();
            int xpos = (int) p.getLocation().getX();
            try {
                statusCode = SQLUtil.setPlayerIDByPlayerIDandXpos(playerUUID, xpos);
            } catch (SQLException ex) {
                p.sendMessage(messages.getSQLErrorMessage());
                return true;
            }
            if (statusCode == 0) {
                p.sendMessage(messages.getSliceClaimedMessage());
                return true;
            } else if (statusCode == 1) {
                p.sendMessage(messages.getPlayerNotAllowedToClaimNewSliceMessage());
                return true;
            } else if (statusCode == 2) {
                p.sendMessage(messages.getSliceIsAlreadyClaimedMessage());
                return true;
            }
            return true;
        }
        sender.sendMessage(messages.getConsoleMessage());
        return true;
    }
}
