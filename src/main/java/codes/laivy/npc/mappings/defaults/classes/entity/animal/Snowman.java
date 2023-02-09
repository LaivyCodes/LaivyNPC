package codes.laivy.npc.mappings.defaults.classes.entity.animal;

import codes.laivy.npc.mappings.defaults.classes.entity.EntityLiving;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Snowman extends EntityLiving {
    public Snowman(@Nullable Object value) {
        super(value);
    }

    public boolean hasPumpkinHat() {
        return laivynpc().getVersion().hasEntitySnowmanHat(this);
    }
    public void setPumpkinHat(boolean flag) {
        laivynpc().getVersion().setEntitySnowmanHat(this, flag);
    }

    @Override
    public @NotNull SnowmanClass getClassExecutor() {
        return (SnowmanClass) laivynpc().getVersion().getClassExec("Entity:Snowman");
    }

    public static class SnowmanClass extends EntityLivingClass {
        public SnowmanClass(@NotNull String className) {
            super(className);
        }
    }
}
