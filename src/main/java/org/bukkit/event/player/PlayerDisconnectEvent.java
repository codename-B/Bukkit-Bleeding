package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Called when a player disconnects from a server but is going to be on cooldown
 */
public class PlayerDisconnectEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();

    public PlayerDisconnectEvent(final Player who) {
        super(who);
    }

    public boolean isCancelled() {
        return player.getLogoutCooldownTicks() == 0;
    }

    public void setCancelled(boolean cancel) {
        player.setLogoutCooldownTicks(0);
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
