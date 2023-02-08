package codes.laivy.npc.mappings.defaults.classes.entity.boss.dragon;

import codes.laivy.npc.mappings.defaults.classes.entity.EntityLiving;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class EnderDragon extends EntityLiving {
    public EnderDragon(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull EnderDragonClass getClassExecutor() {
        return (EnderDragonClass) laivynpc().getVersion().getClassExec("Entity:EnderDragon");
    }

    public static class EnderDragonClass extends EntityLivingClass {
        public EnderDragonClass(@NotNull String className) {
            super(className);
        }
    }
}
