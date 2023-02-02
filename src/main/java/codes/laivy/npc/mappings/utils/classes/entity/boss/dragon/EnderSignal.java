package codes.laivy.npc.mappings.utils.classes.entity.boss.dragon;

import codes.laivy.npc.mappings.utils.classes.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class EnderSignal extends Entity {
    public EnderSignal(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull EnderSignalClass getClassExecutor() {
        return (EnderSignalClass) laivynpc().getVersion().getClassExec("Entity:EnderSignal");
    }

    public static class EnderSignalClass extends EntityClass {
        public EnderSignalClass(@NotNull String className) {
            super(className);
        }
    }
}
