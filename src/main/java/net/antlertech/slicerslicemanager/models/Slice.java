package net.antlertech.slicerslicemanager.models;

import java.util.UUID;

public class Slice {
    private int xpos;
    private UUID playerID;

    public Slice(UUID playerID, int xpos) {
        this.playerID = playerID;
        this.xpos = xpos;
    }
    public int getXpos() {
        return xpos;
    }
    public UUID getPlayerID() {
        return playerID;
    }
}
