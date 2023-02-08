package codes.laivy.npc.mappings.defaults.classes.nbt.tags;

import codes.laivy.npc.mappings.defaults.VersionCompound;
import codes.laivy.npc.mappings.defaults.classes.nbt.NBTBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class NBTTagInt extends NBTBase {
    public NBTTagInt(int value) {
        this(laivynpc().getVersion().nbtTag(VersionCompound.NBTTag.INT, value).getValue());
    }

    /**
     * Construct a LaivyNPC NBTTagInt from an NMS NBTTagInt
     * @param value a NMS NBTTagInt
     */
    protected NBTTagInt(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull NBTTagIntClass getClassExecutor() {
        return (NBTTagIntClass) laivynpc().getVersion().getClassExec("NBTBase:NBTTagInt");
    }

    public static class NBTTagIntClass extends NBTBaseClass {
        public NBTTagIntClass(@NotNull String className) {
            super(className);
        }
    }
}
