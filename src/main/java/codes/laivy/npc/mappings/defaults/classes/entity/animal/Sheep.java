package codes.laivy.npc.mappings.defaults.classes.entity.animal;

import codes.laivy.npc.mappings.defaults.classes.entity.AgeableLivingEntity;
import codes.laivy.npc.mappings.defaults.classes.enums.EnumColorEnum;
import codes.laivy.npc.mappings.defaults.classes.java.BooleanObjExec;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Sheep extends AgeableLivingEntity {
    public Sheep(@Nullable Object value) {
        super(value);
    }

    public @NotNull EnumColorEnum.EnumColor getColor() {
        return laivynpc().getVersion().getEntitySheepColor(this);
    }
    public void setColor(@NotNull EnumColorEnum.EnumColor color) {
        laivynpc().getVersion().setEntitySheepColor(this, color);
    }

    public boolean isSheared() {
        //noinspection DataFlowIssue
        return (boolean) laivynpc().getVersion().getMethodExec("Entity:Sheep:isSheared").invokeInstance(this);
    }
    public void setSheared(boolean flag) {
        laivynpc().getVersion().getMethodExec("Entity:Sheep:setSheared").invokeInstance(this, new BooleanObjExec(flag));
    }

    @Override
    public @NotNull SheepClass getClassExecutor() {
        return (SheepClass) laivynpc().getVersion().getClassExec("Entity:Sheep");
    }

    public static class SheepClass extends AgeableLivingEntityClass {
        public SheepClass(@NotNull String className) {
            super(className);
        }
    }
}
