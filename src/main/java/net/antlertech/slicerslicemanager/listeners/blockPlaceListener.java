package net.antlertech.slicerslicemanager.listeners;

import net.antlertech.slicerslicemanager.SQL.SQLUtil;
import net.antlertech.slicerslicemanager.models.messages;
import net.antlertech.slicerslicemanager.utils.checkForBannedBlocks;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import java.sql.SQLException;
import java.util.Objects;
import java.util.UUID;

public class blockPlaceListener implements Listener {
    String playerID;
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        UUID playerUUID = p.getUniqueId();
        int xpos = (int) e.getBlock().getLocation().getX();
        if (!p.hasPermission("slicerslicemanager.staff.bypass.all")) {
            boolean isBannedBlock = checkForBannedBlocks.checkForBannedBlocksOnPlace(e.getBlock());
            try {
                playerID = SQLUtil.getPlayerIDByXpos(String.valueOf(xpos));
            } catch (SQLException ex) {
                p.sendMessage(messages.getSQLErrorMessage());
                e.setCancelled(true);
                return;
            }
            if (isBannedBlock) {
                p.sendMessage(messages.getBannedBlockMessage());
                e.setCancelled(true);
            }
            if (!Objects.equals(playerID, playerUUID.toString()) || p.hasPermission("slicerslicemanager.staff.bypass.slice")) {
                p.sendMessage(messages.getNotTheirSliceMessage());
                e.setCancelled(true);
            }
        }
    }
}
