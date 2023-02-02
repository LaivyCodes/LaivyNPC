package codes.laivy.npc.mappings.utils.classes.entity.monster;

import codes.laivy.npc.mappings.utils.classes.entity.EntityLiving;
import codes.laivy.npc.mappings.utils.classes.java.BooleanObjExec;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Creeper extends EntityLiving {
    public Creeper(@Nullable Object value) {
        super(value);
    }

    public boolean isPowered() {
        //noinspection DataFlowIssue
        return (boolean) laivynpc().getVersion().getMethodExec("Entity:Creeper:isPowered").invokeInstance(this);
    }
    public void setPowered(boolean flag) {
        laivynpc().getVersion().getMethodExec("Entity:Creeper:setPowered").invokeInstance(this, new BooleanObjExec(flag));
    }

    public boolean isIgnited() {
        return ((byte) getDataWatcher().get((int) laivynpc().getVersion().getObject("Metadata:Creeper:Ignited"))) != 0;
    }
    public void setIgnited(boolean flag) {
        getDataWatcher().set((int) laivynpc().getVersion().getObject("Metadata:Creeper:Ignited"), (byte) (flag ? 1 : 0));
    }

    @Override
    public @NotNull CreeperClass getClassExecutor() {
        return (CreeperClass) laivynpc().getVersion().getClassExec("Entity:Creeper");
    }

    public static class CreeperClass extends EntityLivingClass {
        public CreeperClass(@NotNull String className) {
            super(className);
        }
    }
}
