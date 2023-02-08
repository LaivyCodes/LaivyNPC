package codes.laivy.npc.mappings.defaults.classes.nbt.tags;

import codes.laivy.npc.mappings.defaults.VersionCompound;
import codes.laivy.npc.mappings.defaults.classes.nbt.NBTBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class NBTTagString extends NBTBase {
    public NBTTagString(@NotNull String string) {
        this(laivynpc().getVersion().nbtTag(VersionCompound.NBTTag.STRING, string).getValue());
    }

    /**
     * Construct a LaivyNPC NBTTagString from an NMS NBTTagString
     * @param value a NMS NBTTagString
     */
    protected NBTTagString(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull NBTTagStringClass getClassExecutor() {
        return (NBTTagStringClass) laivynpc().getVersion().getClassExec("NBTBase:NBTTagString");
    }

    public static class NBTTagStringClass extends NBTBaseClass {
        public NBTTagStringClass(@NotNull String className) {
            super(className);
        }
    }
}
