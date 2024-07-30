package net.antlertech.slicerslicemanager.listeners;

import net.antlertech.slicerslicemanager.SQL.SQLUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import java.sql.SQLException;
import java.util.UUID;

import static net.antlertech.slicerslicemanager.SlicerSliceManager.getPlugin;

public class joinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        UUID playerID = p.getUniqueId();
        try {
            SQLUtil.addPlayerIDToDatabase(playerID);
        } catch (SQLException ex) {
            p.kickPlayer("Hmm... We were not able to add you to the slices Database! Please try again!");
            getPlugin().getLogger().info("[ERROR]: Unable to add " + p.getDisplayName() + " to the slices Database!");
        }
    }
}