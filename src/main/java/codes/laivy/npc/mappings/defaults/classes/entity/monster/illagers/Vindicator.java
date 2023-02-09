package codes.laivy.npc.mappings.defaults.classes.entity.monster.illagers;

import codes.laivy.npc.mappings.defaults.classes.entity.EntityLiving;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Vindicator extends EntityLiving {
    public Vindicator(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull VindicatorClass getClassExecutor() {
        return (VindicatorClass) laivynpc().getVersion().getClassExec("Entity:Vindicator");
    }

    public static class VindicatorClass extends EntityLivingClass {
        public VindicatorClass(@NotNull String className) {
            super(className);
        }
    }
}
