package codes.laivy.npc.mappings.defaults.classes.entity.monster.illagers;

import codes.laivy.npc.mappings.defaults.classes.entity.EntityLiving;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Evoker extends EntityLiving {
    public Evoker(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull Evoker.EvokerClass getClassExecutor() {
        return (EvokerClass) laivynpc().getVersion().getClassExec("Entity:Evoker");
    }

    public static class EvokerClass extends EntityLivingClass {
        public EvokerClass(@NotNull String className) {
            super(className);
        }
    }
}
