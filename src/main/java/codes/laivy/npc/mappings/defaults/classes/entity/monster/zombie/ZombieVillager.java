package codes.laivy.npc.mappings.defaults.classes.entity.monster.zombie;

import codes.laivy.npc.mappings.defaults.classes.datawatcher.DataWatcherObject;
import codes.laivy.npc.mappings.defaults.classes.entity.npc.Villager;
import codes.laivy.npc.mappings.versions.V1_11_R1;
import codes.laivy.npc.mappings.versions.V1_14_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class ZombieVillager extends Zombie {

    public static @NotNull DataWatcherObject DATA_METADATA() {
        if (ReflectionUtils.isCompatible(V1_14_R1.class)) {
            return new DataWatcherObject(laivynpc().getVersion().getFieldExec("Metadata:ZombieVillager:Data").invokeStatic());
        } else {
            throw new IllegalStateException("This metadata object is compatible only with 1.14+");
        }
    }

    public ZombieVillager(@Nullable Object value) {
        super(value);
    }

    // 1.14+ ONLY!!
    public @NotNull Villager.Type getVillagerType() {
        return laivynpc().getVersion().getEntityZombieVillagerType(this);
    }
    // 1.14+ ONLY!!
    public void setVillagerType(@NotNull Villager.Type type) {
        laivynpc().getVersion().setEntityZombieVillagerType(this, type);
    }

    @Override
    public @NotNull ZombieClass getClassExecutor() {
        if (ReflectionUtils.isCompatible(V1_11_R1.class)) {
            return (ZombieVillagerClass) laivynpc().getVersion().getClassExec("Entity:Zombie:Villager");
        } else {
            return (ZombieClass) laivynpc().getVersion().getClassExec("Entity:Zombie");
        }
    }

    public static class ZombieVillagerClass extends ZombieClass {
        public ZombieVillagerClass(@NotNull String className) {
            super(className);
        }
    }
}
