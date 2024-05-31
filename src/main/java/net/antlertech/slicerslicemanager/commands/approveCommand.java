package net.antlertech.slicerslicemanager.commands;

import net.antlertech.slicerslicemanager.SQL.SQLUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.antlertech.slicerslicemanager.models.messages;

public class approveCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player p = (Player) sender;
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < args.length; i++) {
            builder.append(args[i]);
            builder.append(" ");
        }
        String approveTargets = builder.toString();
        approveTargets = approveTargets.stripTrailing();
        Player approveTarget = Bukkit.getServer().getPlayer(approveTargets);
        p.sendMessage("Approving " + approveTarget.getName());
        SQLUtil.approveSlice(approveTarget.getUniqueId());
        approveTarget.sendMessage(messages.getYourSliceApprovedMessage());
        p.sendMessage(messages.getApprovedSliceMessage(approveTarget));
        return true;
    }
}