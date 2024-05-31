package net.antlertech.slicerslicemanager;

import net.antlertech.slicerslicemanager.commands.approveCommand;
import net.antlertech.slicerslicemanager.commands.claimCommand;
import net.antlertech.slicerslicemanager.listeners.blockPlaceListener;
import net.antlertech.slicerslicemanager.listeners.blockRemoveListener;
import net.antlertech.slicerslicemanager.listeners.joinListener;
import org.bukkit.plugin.java.JavaPlugin;

import static net.antlertech.slicerslicemanager.SQL.SQLUtil.*;

public final class SlicerSliceManager extends JavaPlugin {
    private static SlicerSliceManager plugin;
    public static SlicerSliceManager getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new blockPlaceListener(), this);
        getServer().getPluginManager().registerEvents(new blockRemoveListener(), this);
        getServer().getPluginManager().registerEvents(new joinListener(), this);
        getCommand("claim").setExecutor(new claimCommand());
        getCommand("approve").setExecutor(new approveCommand());
        setUpSQL();
        getLogger().info("[INFO]: Slicer Slice Manager Has Started!");
    }

    @Override
    public void onDisable() {
        closeSQLConnection();
        getLogger().info("[INFO]: Slicer Slice Manager Has Stopped!");
    }

}
