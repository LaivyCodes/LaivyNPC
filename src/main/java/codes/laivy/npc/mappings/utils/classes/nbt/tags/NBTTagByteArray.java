package codes.laivy.npc.mappings.utils.classes.nbt.tags;

import codes.laivy.npc.mappings.utils.VersionCompound;
import codes.laivy.npc.mappings.utils.classes.nbt.NBTBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class NBTTagByteArray extends NBTBase {
    public NBTTagByteArray(byte[] value) {
        //noinspection PrimitiveArrayArgumentToVarargsMethod
        super(laivynpc().getVersion().nbtTag(VersionCompound.NBTTag.BYTE_ARRAY, value).getValue());
    }

    /**
     * Construct a LaivyNPC NBTTagByteArray from an NMS NBTTagByteArray
     * @param value a NMS NBTTagByteArray
     */
    protected NBTTagByteArray(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull NBTTagByteArrayClass getClassExecutor() {
        return (NBTTagByteArrayClass) laivynpc().getVersion().getClassExec("NBTBase:NBTTagByteArray");
    }

    public static class NBTTagByteArrayClass extends NBTBaseClass {
        public NBTTagByteArrayClass(@NotNull String className) {
            super(className);
        }
    }
}
