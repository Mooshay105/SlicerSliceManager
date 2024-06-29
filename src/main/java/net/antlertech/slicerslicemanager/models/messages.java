package net.antlertech.slicerslicemanager.models;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class messages {
    private static final String yourSliceApprovedMessage = ChatColor.GREEN + "" + ChatColor.BOLD + "YAY! " + ChatColor.GRAY + "Your slice has been approved!";
    private static final String sliceClaimedMessage = ChatColor.GREEN + "" + ChatColor.BOLD + "YAY! " + ChatColor.GRAY + "You have claimed a slice!";
    private static final String sliceIsAlreadyClaimedMessage = ChatColor.RED + "" + ChatColor.BOLD + "HEY! " + ChatColor.GRAY + "This slice is already claimed!";
    private static final String playerNotAllowedToClaimNewSliceMessage = ChatColor.RED + "" + ChatColor.BOLD + "HEY! " + ChatColor.GRAY + "You are not allowed to claim a slice new slice! Please get a staff member to approve it for you!";
    private static final String notTheirSliceMessage = ChatColor.RED + "" + ChatColor.BOLD + "HEY! " + ChatColor.GRAY + "That is not your slice!";
    private static final String bannedBlockMessage = ChatColor.RED + "" + ChatColor.BOLD + "HEY! " + ChatColor.GRAY + "That is an illegal block!";
    private static final String consoleMessage = ChatColor.RED + "" + ChatColor.BOLD + "HEY! " + ChatColor.GRAY + "Do Not Run This Command As Console!";
    private static final String noUserSpecifiedMessage = ChatColor.RED + "" + ChatColor.BOLD + "HEY! " + ChatColor.GRAY + "You need to specify a user!";
    private static final String SQLErrorMessage = ChatColor.RED + "" + ChatColor.BOLD + "Hmm... " + ChatColor.GRAY + "There was an error with the SQL database! Please try again!";
    private static final String errorClaimingSliceMessage = ChatColor.RED + "" + ChatColor.BOLD + "Hmm... " + ChatColor.GRAY + "There was an error claiming your slice! Please try again!";
    private static final String errorApprovingSliceMessage = ChatColor.RED + "" + ChatColor.BOLD + "Hmm... " + ChatColor.GRAY + "There was an error approving the slice! Please try again!";

    public static String getApprovedSliceMessage(Player player) {
        String approvedSliceMessage = ChatColor.GREEN + "" + ChatColor.BOLD + "YAY! " + ChatColor.GRAY + "You have approved " + player.getName() + " slice!";
        return approvedSliceMessage;
    }
    public static String getYourSliceApprovedMessage() {
        return yourSliceApprovedMessage;
    }
    public static String getSliceClaimedMessage() {
        return sliceClaimedMessage;
    }
    public static String getSliceIsAlreadyClaimedMessage() {
        return sliceIsAlreadyClaimedMessage;
    }
    public static String getNotTheirSliceMessage() {
        return notTheirSliceMessage;
    }
    public static String getBannedBlockMessage() {
        return bannedBlockMessage;
    }
    public static String getPlayerNotAllowedToClaimNewSliceMessage() {
        return playerNotAllowedToClaimNewSliceMessage;
    }
    public static String getConsoleMessage() {
        return consoleMessage;
    }
    public static String getNoUserSpecifiedMessage() {
        return noUserSpecifiedMessage;
    }
    public static String getSQLErrorMessage() {
        return SQLErrorMessage;
    }
    public static String getErrorClaimingSliceMessage() {
        return errorClaimingSliceMessage;
    }
    public static String getErrorApprovingSliceMessage() {
        return errorApprovingSliceMessage;
    }
}