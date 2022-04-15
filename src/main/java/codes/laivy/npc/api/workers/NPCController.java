package codes.laivy.npc.api.workers;

import codes.laivy.npc.api.types.INPC;
import codes.laivy.npc.utils.Validation;

public abstract class NPCController {

    private int viewDistance = 64;
    protected boolean isSpawned = false;
    private boolean canSpawn = false;

    private final INPC inpc;

    public NPCController(INPC inpc) {
        Validation.notNull(inpc, new NullPointerException("O NPC não pode ser nulo"));
        this.inpc = inpc;
    }

    public abstract void reCreate();

    public abstract void spawn();
    public abstract void despawn();
    public abstract void respawn();
    public abstract void hide();

    public boolean canSpawn() {
        return canSpawn;
    }
    public void setCanSpawn(boolean canSpawn) {
        this.canSpawn = canSpawn;
    }

    public boolean isSpawned() {
        return isSpawned;
    }

    public int getViewDistance() {
        return viewDistance;
    }
    public void setViewDistance(int viewDistance) {
        this.viewDistance = viewDistance;
    }

    public INPC getNPC() {
        return inpc;
    }

}
