package net.antlertech.slicerslicemanager.utils;

import org.bukkit.block.Block;

public class checkForBannedBlocks {
    public static boolean checkForBannedBlocksOnPlace(Block block) {
        if (block.getType().name().equals("STICKY_PISTON")) {
            return true;
        } else if (block.getType().name().equals("PISTON")) {
            return true;
        } else if (block.getType().name().equals("DISPENSER")) {
            return true;
        } else if (block.getType().name().equals("DROPPER")) {
            return true;
        } else if (block.getType().name().equals("TNT")) {
            return true;
        } else if (block.getType().name().equals("END_CRYSTAL")) {
            return true;
        }
        return false;
    }
}
