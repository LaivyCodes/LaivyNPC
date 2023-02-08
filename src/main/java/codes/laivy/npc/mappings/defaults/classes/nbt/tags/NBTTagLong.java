package codes.laivy.npc.mappings.defaults.classes.nbt.tags;

import codes.laivy.npc.mappings.defaults.VersionCompound;
import codes.laivy.npc.mappings.defaults.classes.nbt.NBTBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class NBTTagLong extends NBTBase {
    public NBTTagLong(long value) {
        this(laivynpc().getVersion().nbtTag(VersionCompound.NBTTag.LONG, value).getValue());
    }

    /**
     * Construct a LaivyNPC NBTTagLong from an NMS NBTTagLong
     * @param value a NMS NBTTagLong
     */
    protected NBTTagLong(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull NBTTagLongClass getClassExecutor() {
        return (NBTTagLongClass) laivynpc().getVersion().getClassExec("NBTBase:NBTTagLong");
    }

    public static class NBTTagLongClass extends NBTBaseClass {
        public NBTTagLongClass(@NotNull String className) {
            super(className);
        }
    }
}
