package codes.laivy.npc.mappings.utils.classes.entity.monster;

import codes.laivy.npc.mappings.instances.FieldExecutor;
import codes.laivy.npc.mappings.utils.classes.datawatcher.DataWatcherObject;
import codes.laivy.npc.mappings.utils.classes.entity.EntityLiving;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Ghast extends EntityLiving {
    public Ghast(@Nullable Object value) {
        super(value);
    }

    public boolean isAttacking() {
        //      Gets the attacking metadata index debug
        @NotNull DataWatcherObject object = new DataWatcherObject(new FieldExecutor(getClassExecutor(), laivynpc().getVersion().getClassExec("DataWatcherObject"), "a", "Gets a entity's datawatcher object") {{
            load();
        }}.invokeStatic());
        Bukkit.broadcastMessage("Ghast Id: '" + object.getId() + "'");

        return laivynpc().getVersion().isEntityGhastAttacking(this);
    }
    public void setAttacking(boolean flag) {
        laivynpc().getVersion().setEntityGhastAttacking(this, flag);
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
