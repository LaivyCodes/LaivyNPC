package codes.laivy.npc.mappings.defaults.classes.entity.animal;

import codes.laivy.npc.mappings.defaults.classes.entity.EntityLiving;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Squid extends EntityLiving {
    public Squid(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull SquidClass getClassExecutor() {
        return (SquidClass) laivynpc().getVersion().getClassExec("Entity:Squid");
    }

    public static class SquidClass extends EntityLivingClass {
        public SquidClass(@NotNull String className) {
            super(className);
        }
    }
}
