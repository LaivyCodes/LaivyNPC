package codes.laivy.npc.mappings.defaults.classes.entity.ambient;

import codes.laivy.npc.mappings.defaults.classes.entity.EntityLiving;
import codes.laivy.npc.mappings.defaults.classes.java.BooleanObjExec;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Bat extends EntityLiving {
    public Bat(@Nullable Object value) {
        super(value);
    }

    public boolean isAsleep() {
        return laivynpc().getVersion().isEntityBatAsleep(this);
    }
    public void setAsleep(boolean flag) {
        laivynpc().getVersion().setEntityBatAsleep(this, flag);
    }

    @Override
    public @NotNull BatClass getClassExecutor() {
        return (BatClass) laivynpc().getVersion().getClassExec("Entity:Bat");
    }

    public static class BatClass extends EntityLivingClass {
        public BatClass(@NotNull String className) {
            super(className);
        }
    }
}
