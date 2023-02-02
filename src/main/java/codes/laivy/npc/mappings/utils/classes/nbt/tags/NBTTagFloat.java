package codes.laivy.npc.mappings.utils.classes.nbt.tags;

import codes.laivy.npc.mappings.utils.VersionCompound;
import codes.laivy.npc.mappings.utils.classes.nbt.NBTBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class NBTTagFloat extends NBTBase {
    public NBTTagFloat(float value) {
        this(laivynpc().getVersion().nbtTag(VersionCompound.NBTTag.FLOAT, value).getValue());
    }

    /**
     * Construct a LaivyNPC NBTTagFloat from an NMS NBTTagFloat
     * @param value a NMS NBTTagFloat
     */
    protected NBTTagFloat(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull NBTTagFloatClass getClassExecutor() {
        return (NBTTagFloatClass) laivynpc().getVersion().getClassExec("NBTBase:NBTTagFloat");
    }

    public static class NBTTagFloatClass extends NBTBaseClass {
        public NBTTagFloatClass(@NotNull String className) {
            super(className);
        }
    }
}
