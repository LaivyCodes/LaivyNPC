package codes.laivy.npc.mappings.defaults.classes.entity.monster;

import codes.laivy.npc.mappings.defaults.classes.entity.EntityLiving;
import codes.laivy.npc.mappings.defaults.classes.java.IntegerObjExec;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Slime extends EntityLiving {
    public Slime(@Nullable Object value) {
        super(value);
    }

    public int getSize() {
        return laivynpc().getVersion().getEntitySlimeSize(this);
    }
    public void setSize(int size) {
        laivynpc().getVersion().setEntitySlimeSize(this, size);
    }

    @Override
    public @NotNull SlimeClass getClassExecutor() {
        return (SlimeClass) laivynpc().getVersion().getClassExec("Entity:Slime");
    }

    public static class SlimeClass extends EntityLivingClass {
        public SlimeClass(@NotNull String className) {
            super(className);
        }
    }
}
