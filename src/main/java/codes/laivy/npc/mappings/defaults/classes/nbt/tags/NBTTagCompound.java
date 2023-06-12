package codes.laivy.npc.mappings.defaults.classes.nbt.tags;

import codes.laivy.npc.mappings.defaults.VersionCompound;
import codes.laivy.npc.mappings.defaults.classes.java.StringObjExec;
import codes.laivy.npc.mappings.defaults.classes.nbt.NBTBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Set;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class NBTTagCompound extends NBTBase {

    public NBTTagCompound() {
        this(laivynpc().getVersion().getClassExec("NBTBase:NBTTagCompound").getConstructor().newInstance());
    }
    public NBTTagCompound(@NotNull NBTTagCompound compound) {
        this(compound.getValue());
        concatenate(compound);
    }

    /**
     * Construct a LaivyNPC NBTTagCompound from an NMS NBTTagCompound
     * @param value a NMS NBTTagCompound
     */
    public NBTTagCompound(@Nullable Object value) {
        super(value);
    }

    public void concatenate(@NotNull NBTTagCompound compound) {
        laivynpc().getVersion().nbtbaseConcatenate(this, compound);
    }

    @Override
    public @NotNull Object getValue() {
        if (super.getValue() == null) {
            setValue(laivynpc().getVersion().nbtTag(VersionCompound.NBTTag.COMPOUND).getValue());
        }

        return super.getValue();
    }

    public void set(@NotNull String key, @NotNull Object base) {
        this.set(key, laivynpc().getVersion().nbtTag(NBTBase.getTagType(base), base));
    }
    public void set(@NotNull String key, @NotNull NBTBase base) {
        laivynpc().getVersion().nbtCompound(VersionCompound.NBTCompoundAction.SET, this, new StringObjExec(key), base);
    }
    public NBTBase get(@NotNull String key) {
        @NotNull Object object = Objects.requireNonNull(laivynpc().getVersion().nbtCompound(VersionCompound.NBTCompoundAction.GET, this, new StringObjExec(key), null));
        VersionCompound.NBTTag tag = NBTBase.getTagType(object);

        if (tag == VersionCompound.NBTTag.BYTE) {
            return new NBTTagByte(object);
        } else if (tag == VersionCompound.NBTTag.BYTE_ARRAY) {
            return new NBTTagByteArray(object);
        } else if (tag == VersionCompound.NBTTag.COMPOUND) {
            return new NBTTagCompound(object);
        } else if (tag == VersionCompound.NBTTag.DOUBLE) {
            return new NBTTagDouble(object);
        } else if (tag == VersionCompound.NBTTag.FLOAT) {
            return new NBTTagFloat(object);
        } else if (tag == VersionCompound.NBTTag.INT) {
            return new NBTTagInt(object);
        } else if (tag == VersionCompound.NBTTag.INT_ARRAY) {
            return new NBTTagIntArray(object);
        } else if (tag == VersionCompound.NBTTag.LIST) {
            return new NBTTagList(object);
        } else if (tag == VersionCompound.NBTTag.LONG) {
            return new NBTTagLong(object);
        } else if (tag == VersionCompound.NBTTag.SHORT) {
            return new NBTTagShort(object);
        } else if (tag == VersionCompound.NBTTag.STRING) {
            return new NBTTagString(object);
        } else {
            throw new UnsupportedOperationException("Unsupported nbt tag: '" + tag + "', '" + object + "'");
        }
    }
    public void remove(@NotNull String key) {
        laivynpc().getVersion().nbtCompound(VersionCompound.NBTCompoundAction.REMOVE, this, new StringObjExec(key), null);
    }
    public boolean contains(@NotNull String key) {
        //noinspection ConstantConditions
        return (boolean) laivynpc().getVersion().nbtCompound(VersionCompound.NBTCompoundAction.CONTAINS, this, new StringObjExec(key), null);
    }
    public Set<String> keySet() {
        //noinspection unchecked
        return (Set<String>) laivynpc().getVersion().nbtCompound(VersionCompound.NBTCompoundAction.KEY_SET, this, null, null);
    }

    @Override
    public @NotNull NBTTagCompoundClass getClassExecutor() {
        return (NBTTagCompoundClass) laivynpc().getVersion().getClassExec("NBTBase:NBTTagCompound");
    }

    public static class NBTTagCompoundClass extends NBTBaseClass {
        public NBTTagCompoundClass(@NotNull String className) {
            super(className);
        }
    }
}
