package codes.laivy.npc.mappings.defaults.classes.entity.animal.horse;

import codes.laivy.npc.mappings.versions.V1_11_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class AbstractChestedHorse extends AbstractHorse {
    public AbstractChestedHorse(@Nullable Object value) {
        super(value);
    }

    public void setChest(boolean flag) {
        laivynpc().getVersion().setEntityChestedHorseChest(this, flag);
    }
    public boolean hasChest() {
        return laivynpc().getVersion().hasEntityChestedHorseChest(this);
    }

    @Override
    public @NotNull AbstractHorseClass getClassExecutor() {
        if (ReflectionUtils.isCompatible(V1_11_R1.class)) {
            return (AbstractChestedHorseClass) laivynpc().getVersion().getClassExec("Entity:Horse:Abstract:Chested");
        } else {
            return (AbstractHorseClass) laivynpc().getVersion().getClassExec("Entity:Horse");
        }
    }

    public static class AbstractChestedHorseClass extends AbstractHorseClass {
        public AbstractChestedHorseClass(@NotNull String className) {
            super(className);
        }
    }

}
