package codes.laivy.npc.mappings.utils.classes.nbt.tags;

import codes.laivy.npc.mappings.utils.VersionCompound;
import codes.laivy.npc.mappings.utils.classes.nbt.NBTBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class NBTTagByte extends NBTBase {
    public NBTTagByte(byte value) {
        super(laivynpc().getVersion().nbtTag(VersionCompound.NBTTag.BYTE, value).getValue());
    }

    /**
     * Construct a LaivyNPC NBTTagByte from an NMS NBTTagByte
     * @param value a NMS NBTTagByte
     */
    protected NBTTagByte(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull NBTTagByteClass getClassExecutor() {
        return (NBTTagByteClass) laivynpc().getVersion().getClassExec("NBTBase:NBTTagByte");
    }

    public static class NBTTagByteClass extends NBTBaseClass {
        public NBTTagByteClass(@NotNull String className) {
            super(className);
        }
    }
}
