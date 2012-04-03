package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

/**
 * Called when a player joins a server while on cooldown
 */
public class PlayerReconnectEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();

    public PlayerReconnectEvent(final Player who) {
        super(who);
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
