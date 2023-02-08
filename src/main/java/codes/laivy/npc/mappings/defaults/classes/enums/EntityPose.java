package codes.laivy.npc.mappings.defaults.classes.enums;

public enum EntityPose {

    STANDING(false),
    SLEEPING(false),

    FALL_FLYING(true),
    SWIMMING(true),
    CROUCHING(true),
    ;

    private final boolean playerPose;

    EntityPose(boolean playerPose) {
        this.playerPose = playerPose;
    }

    public boolean isPlayerPose() {
        return playerPose;
    }
}
