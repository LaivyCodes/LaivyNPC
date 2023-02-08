package codes.laivy.npc.mappings.defaults.classes.entity.monster;

import codes.laivy.npc.mappings.defaults.classes.entity.EntityLiving;
import codes.laivy.npc.mappings.defaults.classes.java.BooleanObjExec;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Spider extends EntityLiving {
    public Spider(@Nullable Object value) {
        super(value);
    }

    public boolean isClimbing() {
        //noinspection DataFlowIssue
        return (boolean) laivynpc().getVersion().getMethodExec("Entity:Spider:isClimbing").invokeInstance(this);
    }
    public void setClimbing(boolean flag) {
        laivynpc().getVersion().getMethodExec("Entity:Spider:setClimbing").invokeInstance(this, new BooleanObjExec(flag));
    }

    @Override
    public @NotNull SpiderClass getClassExecutor() {
        return (SpiderClass) laivynpc().getVersion().getClassExec("Entity:Spider");
    }

    public static class SpiderClass extends EntityLiving.EntityLivingClass {
        public SpiderClass(@NotNull String className) {
            super(className);
        }
    }
}
