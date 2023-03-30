package codes.laivy.npc.mappings.defaults.classes.entity;

import codes.laivy.npc.mappings.defaults.classes.entity.player.EntityPlayer;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class CraftPlayer extends ObjectExecutor {
    public CraftPlayer(@Nullable Object value) {
        super(value);
    }

    @NotNull
    public EntityPlayer getHandle() {
        return new EntityPlayer(laivynpc().getVersion().getMethodExec("Entity:CraftPlayer:getHandle").invokeInstance(this));
    }

    @Override
    public @NotNull CraftPlayerClass getClassExecutor() {
        return (CraftPlayerClass) laivynpc().getVersion().getClassExec("CraftPlayer");
    }

    public static class CraftPlayerClass extends ClassExecutor {
        public CraftPlayerClass(@NotNull String className) {
            super(className);
        }
    }
}
