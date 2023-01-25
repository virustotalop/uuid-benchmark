package com.gmail.virustotalop.uuidbenchmark;

import java.util.UUID;

public class Player {

    private final UUID uuid;
    private int hash = 0;

    public Player(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUUID () {
        return this.uuid;
    }

    @Override
    public int hashCode() {
        if (this.hash == 0 || this.hash == 485) {
            this.hash = 485 + ((this.getUUID() != null) ? this.getUUID().hashCode() : 0);
        }
        return this.hash;
    }
}
