package codes.laivy.npc.mappings.defaults.classes.entity.monster;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class CaveSpider extends Spider {

    public CaveSpider(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull CaveSpiderClass getClassExecutor() {
        return (CaveSpiderClass) laivynpc().getVersion().getClassExec("Entity:CaveSpider");
    }

    public static class CaveSpiderClass extends SpiderClass {
        public CaveSpiderClass(@NotNull String className) {
            super(className);
        }
    }
}
