package codes.laivy.npc.mappings.defaults.classes.entity.vehicle;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Boat extends Entity {
    public Boat(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull BoatClass getClassExecutor() {
        return (BoatClass) laivynpc().getVersion().getClassExec("Entity:Boat");
    }

    public static class BoatClass extends EntityClass {
        public BoatClass(@NotNull String className) {
            super(className);
        }
    }
}
