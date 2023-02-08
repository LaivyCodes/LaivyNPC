package codes.laivy.npc.mappings.defaults.classes.nbt.tags;

import codes.laivy.npc.mappings.defaults.VersionCompound;
import codes.laivy.npc.mappings.defaults.classes.nbt.NBTBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class NBTTagDouble extends NBTBase {
    public NBTTagDouble(double value) {
        this(laivynpc().getVersion().nbtTag(VersionCompound.NBTTag.DOUBLE, value).getValue());
    }

    /**
     * Construct a LaivyNPC NBTTagDouble from an NMS NBTTagDouble
     * @param value a NMS NBTTagDouble
     */
    protected NBTTagDouble(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull NBTTagDoubleClass getClassExecutor() {
        return (NBTTagDoubleClass) laivynpc().getVersion().getClassExec("NBTBase:NBTTagDouble");
    }

    public static class NBTTagDoubleClass extends NBTBaseClass {
        public NBTTagDoubleClass(@NotNull String className) {
            super(className);
        }
    }
}
