package codes.laivy.npc.mappings.utils.classes.nbt.tags;

import codes.laivy.npc.mappings.utils.VersionCompound;
import codes.laivy.npc.mappings.utils.classes.nbt.NBTBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class NBTTagIntArray extends NBTBase {
    public NBTTagIntArray(int[] value) {
        //noinspection PrimitiveArrayArgumentToVarargsMethod
        this(laivynpc().getVersion().nbtTag(VersionCompound.NBTTag.INT_ARRAY, value).getValue());
    }

    /**
     * Construct a LaivyNPC NBTTagIntArray from an NMS NBTTagIntArray
     * @param value a NMS NBTTagIntArray
     */
    protected NBTTagIntArray(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull NBTTagIntArrayClass getClassExecutor() {
        return (NBTTagIntArrayClass) laivynpc().getVersion().getClassExec("NBTBase:NBTTagIntArray");
    }

    public static class NBTTagIntArrayClass extends NBTBaseClass {
        public NBTTagIntArrayClass(@NotNull String className) {
            super(className, true);
        }
    }
}
