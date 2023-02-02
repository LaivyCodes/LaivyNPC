package codes.laivy.npc.mappings.utils.classes.entity.monster;

import codes.laivy.npc.mappings.utils.classes.entity.EntityLiving;
import codes.laivy.npc.mappings.utils.classes.java.BooleanObjExec;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Zombie extends EntityLiving {
    public Zombie(@Nullable Object value) {
        super(value);
    }

    public boolean isVillager() {
        //noinspection DataFlowIssue
        return (boolean) laivynpc().getVersion().getMethodExec("Entity:Zombie:isVillager").invokeInstance(this);
    }
    public void setVillager(boolean flag) {
        laivynpc().getVersion().getMethodExec("Entity:Zombie:setVillager").invokeInstance(this, new BooleanObjExec(flag));
    }

    @Override
    public @NotNull Zombie.ZombieClass getClassExecutor() {
        return (ZombieClass) laivynpc().getVersion().getClassExec("Entity:Zombie");
    }

    public static class ZombieClass extends EntityLivingClass {
        public ZombieClass(@NotNull String className) {
            super(className);
        }
    }
}
