package codes.laivy.npc.mappings.utils.classes.entity.boss.wither;

import codes.laivy.npc.mappings.utils.classes.entity.Entity;
import codes.laivy.npc.mappings.utils.classes.java.BooleanObjExec;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class WitherSkull extends Entity {
    public WitherSkull(@Nullable Object value) {
        super(value);
    }

    public boolean isCharged() {
        //noinspection DataFlowIssue
        return (boolean) laivynpc().getVersion().getMethodExec("Entity:WitherSkull:isCharged").invokeInstance(this);
    }
    public void setCharged(boolean flag) {
        laivynpc().getVersion().getMethodExec("Entity:WitherSkull:setCharged").invokeInstance(this, new BooleanObjExec(flag));
    }

    @Override
    public @NotNull WitherSkullClass getClassExecutor() {
        return (WitherSkullClass) laivynpc().getVersion().getClassExec("Entity:WitherSkull");
    }

    public static class WitherSkullClass extends EntityClass {
        public WitherSkullClass(@NotNull String className) {
            super(className);
        }
    }
}
