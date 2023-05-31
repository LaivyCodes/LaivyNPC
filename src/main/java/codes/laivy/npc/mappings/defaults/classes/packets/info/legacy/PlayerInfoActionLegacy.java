package codes.laivy.npc.mappings.defaults.classes.packets.info.legacy;

import codes.laivy.npc.mappings.defaults.classes.java.EnumObjExec;
import codes.laivy.npc.mappings.defaults.classes.packets.info.action.IPlayerInfoAction;
import org.jetbrains.annotations.NotNull;

public class PlayerInfoActionLegacy extends EnumObjExec implements IPlayerInfoAction {
    public PlayerInfoActionLegacy(@NotNull Enum<?> value) {
        super(value);
    }
}
