package codes.laivy.npc.api.player;

import codes.laivy.npc.utils.Validation;

public final class PlayerNPCAttributes {

    private final PlayerNPC playerNPC;

    public PlayerNPCAttributes(PlayerNPC playerNPC) {
        Validation.notNull(playerNPC, new NullPointerException("O NPC não pode ser nulo"));
        Validation.isTrue(playerNPC.getAttributes() != null, new IllegalStateException("Esse NPC já possui atributos"));

        this.playerNPC = playerNPC;
    }

    public PlayerNPC getPlayerNPC() {
        return playerNPC;
    }

}