package codes.laivy.npc.mappings.defaults.classes.entity.monster;

import codes.laivy.npc.mappings.defaults.classes.entity.EntityLiving;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Witch extends EntityLiving {
    public Witch(@Nullable Object value) {
        super(value);
    }

    public static class WitchClass extends EntityLivingClass {
        public WitchClass(@NotNull String className) {
            super(className);
        }
    }
}
