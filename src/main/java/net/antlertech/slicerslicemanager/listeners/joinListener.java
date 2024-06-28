package net.antlertech.slicerslicemanager.listeners;

import net.antlertech.slicerslicemanager.SQL.SQLUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class joinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        UUID playerID = p.getUniqueId();
        boolean added = SQLUtil.addPlayerIDToDatabase(playerID);
        if (!added) {
            p.kickPlayer("Hmm... We were not able to add you to our Database! Please try again!");
        }
    }
}