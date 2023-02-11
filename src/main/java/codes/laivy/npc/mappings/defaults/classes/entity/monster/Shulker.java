package codes.laivy.npc.mappings.defaults.classes.entity.monster;

import codes.laivy.npc.mappings.defaults.classes.datawatcher.DataWatcherObject;
import codes.laivy.npc.mappings.defaults.classes.entity.EntityLiving;
import codes.laivy.npc.mappings.defaults.classes.enums.EnumColorEnum;
import codes.laivy.npc.mappings.defaults.classes.enums.EnumDirectionEnum;
import codes.laivy.npc.mappings.defaults.classes.java.ByteObjExec;
import codes.laivy.npc.mappings.versions.V1_11_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Shulker extends EntityLiving {

    public static final DataWatcherObject DIRECTION_WATCHER_OBJECT = new DataWatcherObject(laivynpc().getVersion().getFieldExec("Metadata:Shulker:Direction").invokeStatic());
    public static final DataWatcherObject PEEK_WATCHER_OBJECT = new DataWatcherObject(laivynpc().getVersion().getFieldExec("Metadata:Shulker:Peek").invokeStatic());

    public Shulker(@Nullable Object value) {
        super(value);
    }

    public @NotNull EnumDirectionEnum.EnumDirection getDirection() {
        return new EnumDirectionEnum.EnumDirection((Enum<?>) Objects.requireNonNull(getDataWatcher().get(DIRECTION_WATCHER_OBJECT)));
    }
    public void setDirection(@NotNull EnumDirectionEnum.EnumDirection direction) {
        getDataWatcher().set(DIRECTION_WATCHER_OBJECT, direction);
    }

    public int getPeek() {
        return (byte) Objects.requireNonNull(getDataWatcher().get(PEEK_WATCHER_OBJECT));
    }
    public void setPeek(int peek) {
        getDataWatcher().set(PEEK_WATCHER_OBJECT, new ByteObjExec((byte) peek));
    }

    public @NotNull EnumColorEnum.EnumColor getColor() {
        if (!ReflectionUtils.isCompatible(V1_11_R1.class)) {
            throw new UnsupportedOperationException("Shulker colors is only available at 1.11+");
        }
        return laivynpc().getVersion().getEntityShulkerColor(this);
    }
    public void setColor(@NotNull EnumColorEnum.EnumColor color) {
        if (!ReflectionUtils.isCompatible(V1_11_R1.class)) {
            throw new UnsupportedOperationException("Shulker colors is only available at 1.11+");
        }
        laivynpc().getVersion().setEntityShulkerColor(this, color);
    }

    @Override
    public @NotNull ShulkerClass getClassExecutor() {
        return (ShulkerClass) laivynpc().getVersion().getClassExec("Entity:Shulker");
    }

    public static class ShulkerClass extends EntityLivingClass {
        public ShulkerClass(@NotNull String className) {
            super(className);
        }
    }
}
