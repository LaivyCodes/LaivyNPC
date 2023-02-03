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
//          Gets the ignited metadata index debug
//        @NotNull DataWatcherObject object = new DataWatcherObject(new FieldExecutor(getClassExecutor(), laivynpc().getVersion().getClassExec("DataWatcherObject"), "c", "Gets a entity's datawatcher object") {{
//            load();
//        }}.invokeStatic());
//        Bukkit.broadcastMessage("Creeper Id: '" + object.getId() + "'");

        return laivynpc().getVersion().isEntityCreeperIgnited(this);
    }
    public void setIgnited(boolean flag) {
        laivynpc().getVersion().setEntityCreeperIgnited(this, flag);
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
