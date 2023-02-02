package codes.laivy.npc.mappings.utils.classes.nbt;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import codes.laivy.npc.mappings.utils.VersionCompound;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public abstract class NBTBase extends ObjectExecutor {
    protected NBTBase(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull NBTBaseClass getClassExecutor() {
        return (NBTBaseClass) laivynpc().getVersion().getClassExec("NBTBase");
    }

    /**
     * This returns the NBTTag class of the object
     * @param base the nbt object
     * @return the object class of this nbt object
     */
    @NotNull
    public static VersionCompound.NBTTag getTagType(@NotNull Object base) {
        if (!laivynpc().getVersion().getClassExec("NBTBase").isReflectiveInstance(base)) {
            throw new IllegalArgumentException("This object isn't a NBTBase type! you passed a '" + base.getClass().getName() + "'");
        }

        VersionCompound.NBTTag tag;
        if (laivynpc().getVersion().getClassExec("NBTBase:NBTTagByte").isReflectiveInstance(base)) {
            tag = VersionCompound.NBTTag.BYTE;
        } else if (laivynpc().getVersion().getClassExec("NBTBase:NBTTagByteArray").isReflectiveInstance(base)) {
            tag = VersionCompound.NBTTag.BYTE_ARRAY;
        } else if (laivynpc().getVersion().getClassExec("NBTBase:NBTTagCompound").isReflectiveInstance(base)) {
            tag = VersionCompound.NBTTag.COMPOUND;
        } else if (laivynpc().getVersion().getClassExec("NBTBase:NBTTagDouble").isReflectiveInstance(base)) {
            tag = VersionCompound.NBTTag.DOUBLE;
        } else if (laivynpc().getVersion().getClassExec("NBTBase:NBTTagFloat").isReflectiveInstance(base)) {
            tag = VersionCompound.NBTTag.FLOAT;
        } else if (laivynpc().getVersion().getClassExec("NBTBase:NBTTagInt").isReflectiveInstance(base)) {
            tag = VersionCompound.NBTTag.INT;
        } else if (laivynpc().getVersion().getClassExec("NBTBase:NBTTagIntArray").isReflectiveInstance(base)) {
            tag = VersionCompound.NBTTag.INT_ARRAY;
        } else if (laivynpc().getVersion().getClassExec("NBTBase:NBTTagList").isReflectiveInstance(base)) {
            tag = VersionCompound.NBTTag.LIST;
        } else if (laivynpc().getVersion().getClassExec("NBTBase:NBTTagLong").isReflectiveInstance(base)) {
            tag = VersionCompound.NBTTag.LONG;
        } else if (laivynpc().getVersion().getClassExec("NBTBase:NBTTagShort").isReflectiveInstance(base)) {
            tag = VersionCompound.NBTTag.SHORT;
        } else if (laivynpc().getVersion().getClassExec("NBTBase:NBTTagString").isReflectiveInstance(base)) {
            tag = VersionCompound.NBTTag.STRING;
        } else {
            throw new IllegalArgumentException("Cannot identify that NBTTag '" + base.getClass().getName() + "'");
        }
        return tag;
    }

    public static class NBTBaseClass extends ClassExecutor {
        public NBTBaseClass(@NotNull String className, boolean array) {
            super(className, array);
        }
        public NBTBaseClass(@NotNull String className) {
            super(className);
        }
    }
}
