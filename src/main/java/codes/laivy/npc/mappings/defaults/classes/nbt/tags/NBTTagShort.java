package codes.laivy.npc.mappings.defaults.classes.nbt.tags;

import codes.laivy.npc.mappings.defaults.VersionCompound;
import codes.laivy.npc.mappings.defaults.classes.nbt.NBTBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class NBTTagShort extends NBTBase {
    public NBTTagShort(short value) {
        this(laivynpc().getVersion().nbtTag(VersionCompound.NBTTag.SHORT, value).getValue());
    }

    /**
     * Construct a LaivyNPC NBTTagShort from an NMS NBTTagShort
     * @param value a NMS NBTTagShort
     */
    protected NBTTagShort(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull NBTTagShortClass getClassExecutor() {
        return (NBTTagShortClass) laivynpc().getVersion().getClassExec("NBTBase:NBTTagShort");
    }

    public static class NBTTagShortClass extends NBTBaseClass {
        public NBTTagShortClass(@NotNull String className) {
            super(className);
        }
    }
}
