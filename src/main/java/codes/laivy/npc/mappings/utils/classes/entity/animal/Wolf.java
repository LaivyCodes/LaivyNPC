package codes.laivy.npc.mappings.utils.classes.entity.animal;

import codes.laivy.npc.mappings.utils.classes.entity.TameableLivingEntity;
import codes.laivy.npc.mappings.utils.classes.enums.EnumColorEnum;
import codes.laivy.npc.mappings.utils.classes.java.BooleanObjExec;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Wolf extends TameableLivingEntity {
    public Wolf(@Nullable Object value) {
        super(value);
    }

    public @NotNull EnumColorEnum.EnumColor getCollarColor() {
        return new EnumColorEnum.EnumColor((Enum<?>) laivynpc().getVersion().getMethodExec("Entity:Wolf:getCollarColor").invokeInstance(this));
    }
    public void setCollarColor(@NotNull EnumColorEnum.EnumColor color) {
        laivynpc().getVersion().getMethodExec("Entity:Wolf:setCollarColor").invokeInstance(this, color);
    }

    public boolean isAngry() {
        //noinspection DataFlowIssue
        return (boolean) laivynpc().getVersion().getMethodExec("Entity:Wolf:isAngry").invokeInstance(this);
    }
    public void setAngry(boolean flag) {
        laivynpc().getVersion().getMethodExec("Entity:Wolf:setAngry").invokeInstance(this, new BooleanObjExec(flag));
    }

    @Override
    public @NotNull WolfClass getClassExecutor() {
        return (WolfClass) laivynpc().getVersion().getClassExec("Entity:Wolf");
    }

    public static class WolfClass extends TameableLivingEntityClass {
        public WolfClass(@NotNull String className) {
            super(className);
        }
    }

}
