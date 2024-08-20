package net.antlertech.slicerslicemanager.commands;

import net.antlertech.slicerslicemanager.SQL.SQLUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.antlertech.slicerslicemanager.models.messages;
import java.sql.SQLException;

public class approveCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(messages.getNoUserSpecifiedMessage());
            return true;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            builder.append(args[i]);
            builder.append(" ");
        };
        Player approveTargetPlayerObject = Bukkit.getServer().getPlayerExact(builder.toString().stripTrailing());
        if (approveTargetPlayerObject == null) {
            sender.sendMessage(messages.getPlayerNotFoundMessage());
            return true;
        }
        sender.sendMessage("Approving " + approveTargetPlayerObject.getName());
        try {
            SQLUtil.approveSlice(approveTargetPlayerObject.getUniqueId());
        } catch (SQLException e) {
            sender.sendMessage(messages.getErrorApprovingSliceMessage());
            return true;
        }
        approveTargetPlayerObject.sendMessage(messages.getYourSliceApprovedMessage());
        sender.sendMessage(messages.getApprovedSliceMessage(approveTargetPlayerObject));
        return true;
    }
}