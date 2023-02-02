package codes.laivy.npc.mappings.utils.classes.entity.boss.wither;

import codes.laivy.npc.mappings.utils.classes.entity.EntityLiving;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Wither extends EntityLiving {
    public Wither(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull WitherClass getClassExecutor() {
        return (WitherClass) laivynpc().getVersion().getClassExec("Entity:Wither");
    }

    public static class WitherClass extends EntityLivingClass {
        public WitherClass(@NotNull String className) {
            super(className);
        }
    }
}
