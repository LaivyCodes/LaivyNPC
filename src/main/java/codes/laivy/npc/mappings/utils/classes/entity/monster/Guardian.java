package codes.laivy.npc.mappings.utils.classes.entity.monster;

import codes.laivy.npc.mappings.instances.FieldExecutor;
import codes.laivy.npc.mappings.utils.classes.datawatcher.DataWatcherObject;
import codes.laivy.npc.mappings.utils.classes.entity.EntityLiving;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Guardian extends EntityLiving {

    private @Nullable EntityLiving target;

    public Guardian(@Nullable Object value) {
        super(value);
    }

    public @Nullable EntityLiving getTarget() {
        return target;
    }
    public void setTarget(@Nullable EntityLiving living) {
        //      Gets the attacking metadata index debug
//        @NotNull DataWatcherObject object = new DataWatcherObject(new FieldExecutor(getClassExecutor(), laivynpc().getVersion().getClassExec("DataWatcherObject"), "a", "Gets a entity's datawatcher object") {{
//            load();
//        }}.invokeStatic());
//        Bukkit.broadcastMessage("Guardian Id: '" + object.getId() + "'");

        int id = 0;
        if (living != null) {
            id = living.getId();
        }

        getDataWatcher().set((int) laivynpc().getVersion().getObject("Metadata:Guardian:Target"), id);
        target = living;
    }

    @Override
    public @NotNull GuardianClass getClassExecutor() {
        return (GuardianClass) laivynpc().getVersion().getClassExec("Entity:Guardian");
    }

    public static class GuardianClass extends EntityLivingClass {
        public GuardianClass(@NotNull String className) {
            super(className);
        }
    }
}
