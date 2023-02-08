package codes.laivy.npc.mappings.defaults.classes.nbt.tags;

import codes.laivy.npc.mappings.defaults.VersionCompound;
import codes.laivy.npc.mappings.defaults.classes.nbt.NBTBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class NBTTagList extends NBTBase {
    public NBTTagList(List<NBTBase> value) {
        this(laivynpc().getVersion().nbtTag(VersionCompound.NBTTag.LIST, value).getValue());
    }
    public NBTTagList(NBTTagList value) {
        this(laivynpc().getVersion().nbtTag(VersionCompound.NBTTag.LIST).getValue());
        concatenate(value);
    }

    /**
     * Construct a LaivyNPC NBTTagList from an NMS NBTTagList
     * @param value a NMS NBTTagList
     */
    protected NBTTagList(@Nullable Object value) {
        super(value);
    }

    public void concatenate(@NotNull NBTTagList compound) {
        laivynpc().getVersion().nbtbaseConcatenate(this, compound.getValue());
    }

    @Override
    public @NotNull Object getValue() {
        if (super.getValue() == null) {
            setValue(laivynpc().getVersion().nbtTag(VersionCompound.NBTTag.LIST).getValue());
        }

        return super.getValue();
    }

    @Override
    public @NotNull NBTTagListClass getClassExecutor() {
        return (NBTTagListClass) laivynpc().getVersion().getClassExec("NBTBase:NBTTagList");
    }

    public static class NBTTagListClass extends NBTBaseClass {
        public NBTTagListClass(@NotNull String className) {
            super(className);
        }
    }
}
