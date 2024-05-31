package net.antlertech.slicerslicemanager.listeners;

import net.antlertech.slicerslicemanager.SQL.SQLUtil;
import net.antlertech.slicerslicemanager.models.messages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import java.util.Objects;
import java.util.UUID;

public class blockRemoveListener implements Listener {
    @EventHandler
    public void onBlockRemove(BlockBreakEvent e) {
        Player p = e.getPlayer();
        UUID playerUUID = p.getUniqueId();
        int xpos = (int) e.getBlock().getLocation().getX();
        if (!p.hasPermission("slicerslicemanager.staff.bypass.all")) {
            String playerID = SQLUtil.getPlayerIDByXpos(String.valueOf(xpos));
            if (!Objects.equals(playerID, playerUUID.toString())) {
                p.sendMessage(messages.getNotTheirSliceMessage());
                e.setCancelled(true);
            }
        }
    }
}