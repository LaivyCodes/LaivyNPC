package codes.laivy.npc.mappings.defaults.classes.entity.monster;

import codes.laivy.npc.mappings.defaults.classes.datawatcher.DataWatcherObject;
import codes.laivy.npc.mappings.defaults.classes.entity.EntityLiving;
import codes.laivy.npc.mappings.defaults.classes.enums.EnumDirectionEnum;
import codes.laivy.npc.mappings.defaults.classes.java.ByteObjExec;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Shulker extends EntityLiving {

    public static final DataWatcherObject DIRECTION_WATCHER_OBJECT = new DataWatcherObject(laivynpc().getVersion().getFieldExec("Entity:Shulker:DataWatcherObject:Direction").invokeStatic());
    public static final DataWatcherObject PEEK_WATCHER_OBJECT = new DataWatcherObject(laivynpc().getVersion().getFieldExec("Entity:Shulker:DataWatcherObject:Peek").invokeStatic());

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
