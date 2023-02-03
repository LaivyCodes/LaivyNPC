package codes.laivy.npc.mappings.utils.classes.entity.monster;

import codes.laivy.npc.mappings.utils.classes.entity.EntityLiving;
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

    public @Nullable Zombie.VillagerType getVillagerType() {
        return laivynpc().getVersion().getEntityZombieVillagerType(this);
    }
    public void setVillagerType(@Nullable Zombie.VillagerType type) {
        laivynpc().getVersion().setEntityZombieVillagerType(this, type);
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

    public enum VillagerType {
        NORMAL(-1),
        DESERT(0),
        JUNGLE(1),
        PLAINS(2),
        SAVANNA(3),
        SNOWY(4),
        SWAMP(5),
        TAIGA(6),
        ;

        private final int id;

        VillagerType(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public static @NotNull VillagerType getById(int id) {
            for (VillagerType type : VillagerType.values()) {
                if (type.getId() == id) {
                    return type;
                }
            }
            throw new NullPointerException("Couldn't find a villager type with id '" + id + "'");
        }
    }

}
