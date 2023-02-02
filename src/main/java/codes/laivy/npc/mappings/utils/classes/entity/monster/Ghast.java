package codes.laivy.npc.mappings.utils.classes.entity.monster;

import codes.laivy.npc.mappings.utils.classes.entity.EntityLiving;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Ghast extends EntityLiving {
    public Ghast(@Nullable Object value) {
        super(value);
    }

    public boolean isAttacking() {
        return (byte) getDataWatcher().get((int) laivynpc().getVersion().getObject("Metadata:Ghast:Attacking")) == 1;
    }
    public void setAttacking(boolean flag) {
        getDataWatcher().set((int) laivynpc().getVersion().getObject("Metadata:Ghast:Attacking"), (byte) (flag ? 1 : 0));
    }

    @Override
    public @NotNull GhastClass getClassExecutor() {
        return (GhastClass) laivynpc().getVersion().getClassExec("Entity:Ghast");
    }

    public static class GhastClass extends EntityLivingClass {
        public GhastClass(@NotNull String className) {
            super(className);
        }
    }
}
