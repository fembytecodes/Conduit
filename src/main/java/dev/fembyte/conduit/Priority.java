package dev.fembyte.conduit;

public enum Priority {
    HIGHEST, HIGH, NORMAL, LOW, LOWEST;

    /**
     * Smaller value means earlier dispatch.
     */
    public int weight() {
        return this.ordinal();
    }
}
