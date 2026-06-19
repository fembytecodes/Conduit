package dev.fembyte.conduit;

public interface Cancellable extends Event {
    boolean isCancelled();

    void setCancelled(boolean cancelled);
}
