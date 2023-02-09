package codes.laivy.npc.mappings.defaults.classes.entity.monster;

import codes.laivy.npc.mappings.defaults.classes.entity.EntityLiving;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Vex extends EntityLiving {
    public Vex(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull VexClass getClassExecutor() {
        return (VexClass) laivynpc().getVersion().getClassExec("Entity:Vex");
    }

    public static class VexClass extends EntityLivingClass {
        public VexClass(@NotNull String className) {
            super(className);
        }
    }
}
