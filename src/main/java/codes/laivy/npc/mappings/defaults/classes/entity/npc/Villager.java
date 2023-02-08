package codes.laivy.npc.mappings.defaults.classes.entity.npc;

import codes.laivy.npc.mappings.defaults.classes.entity.EntityLiving;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Villager extends EntityLiving {
    public Villager(@Nullable Object value) {
        super(value);
    }

    public @NotNull VillagerProfession getProfession() {
        return laivynpc().getVersion().getEntityVillagerProfession(this);
    }
    public void setProfession(@NotNull VillagerProfession profession) {
        laivynpc().getVersion().setEntityVillagerProfession(this, profession);
    }

    public static class VillagerClass extends EntityLivingClass {
        public VillagerClass(@NotNull String className) {
            super(className);
        }
    }
}
