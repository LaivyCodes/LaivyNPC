package codes.laivy.npc.mappings.utils.classes.entity.monster;

import codes.laivy.npc.mappings.utils.classes.entity.EntityLiving;
import codes.laivy.npc.mappings.utils.classes.java.BooleanObjExec;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Blaze extends EntityLiving {
    public Blaze(@Nullable Object value) {
        super(value);
    }

    public boolean isCharging() {
        //noinspection DataFlowIssue
        return (boolean) laivynpc().getVersion().getMethodExec("Entity:Blaze:isCharging").invokeInstance(this);
    }
    public void setCharging(boolean flag) {
        laivynpc().getVersion().getMethodExec("Entity:Blaze:setCharging").invokeInstance(this, new BooleanObjExec(flag));
    }

    @Override
    public @NotNull BlazeClass getClassExecutor() {
        return (BlazeClass) laivynpc().getVersion().getClassExec("Entity:Blaze");
    }

    public static class BlazeClass extends EntityLivingClass {
        public BlazeClass(@NotNull String className) {
            super(className);
        }
    }
}
