package net.antlertech.slicerslicemanager.listeners;

import net.antlertech.slicerslicemanager.SQL.SQLUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class joinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        UUID playerID = e.getPlayer().getUniqueId();
        SQLUtil.addPlayerIDToDatabase(playerID);
    }
}