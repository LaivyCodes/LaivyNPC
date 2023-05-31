package codes.laivy.npc.mappings.defaults.classes.packets.info.refactored;

import codes.laivy.npc.mappings.defaults.classes.java.EnumObjExec;
import codes.laivy.npc.mappings.defaults.classes.packets.info.action.IPlayerInfoAction;
import codes.laivy.npc.mappings.instances.EnumExecutor;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerInfoAction extends EnumExecutor {
    public PlayerInfoAction(@Nullable ClassExecutor executor) {
        super(executor);
    }

    public static final class PlayerInfoActionEnum extends EnumObjExec implements IPlayerInfoAction {
        public PlayerInfoActionEnum(@NotNull Enum<?> value) {
            super(value);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof PlayerInfoActionEnum) {
                PlayerInfoActionEnum e = (PlayerInfoActionEnum) obj;
                return e.getValue().equals(getValue());
            } else {
                return super.equals(obj);
            }
        }
    }

    public static final class PlayerInfoActionClass extends ClassExecutor {
        public PlayerInfoActionClass(@NotNull String className) {
            super(className);
        }
    }
}
