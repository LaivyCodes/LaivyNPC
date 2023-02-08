package codes.laivy.npc.mappings.defaults;

import codes.laivy.npc.mappings.defaults.classes.nbt.NBTBase;
import codes.laivy.npc.mappings.defaults.classes.java.StringObjExec;
import codes.laivy.npc.mappings.defaults.classes.nbt.tags.NBTTagCompound;
import codes.laivy.npc.mappings.defaults.classes.nbt.tags.NBTTagList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface VersionCompound {

    // NBT
    //
    @Nullable Object nbtCompound(@NotNull NBTCompoundAction action, @NotNull NBTTagCompound object, @Nullable StringObjExec key, @Nullable NBTBase value);
    @Nullable Object nbtList(@NotNull NBTListAction action, @NotNull NBTTagList object, @Nullable Object value);

    /**
     * Concatenate two NBTBases into one
     *
     * @param into The NBTBase that will receive the values
     * @param from The NBTBase that will give the values
     */
    void nbtbaseConcatenate(@NotNull NBTBase into, @NotNull Object from);

    /**
     * Returns a new instance of an NBTBase
     *
     * @param tag the tag type (you can get the NBTTag from an NMS NBTBase using {@link NBTBase#getTagType(Object)}
     * @param objects the constructor's parameters
     * @return a NBTBase
     */
    @NotNull
    NBTBase nbtTag(@NotNull NBTTag tag, @NotNull Object... objects);

    enum NBTTag {
        BYTE(byte.class),
        BYTE_ARRAY(byte[].class),
        COMPOUND(null),
        DOUBLE(double.class),
        FLOAT(float.class),
        INT(int.class),
        INT_ARRAY(int[].class),
        LIST(List.class),
        LONG(long.class),
        SHORT(short.class),
        STRING(String.class),
        ;

        private final Class<?> checkClass;

        NBTTag(@Nullable Class<?> checkClass) {
            this.checkClass = checkClass;
        }

        /**
         * Some cases, that check class can be null
         * Like: COMPOUND
         *
         * @return the check class, you can use this to check if the values are at the correct instance
         */
        @Nullable
        public Class<?> getCheckClass() {
            return checkClass;
        }
    }

    enum NBTListAction {
        ADD,
        REMOVE,
        TRANSLATED_LIST,
        SET,
        CLEAR,
        ;
    }
    enum NBTCompoundAction {
        SET,
        REMOVE,
        CONTAINS,
        IS_EMPTY,
        GET,
        KEY_SET,
        ;
    }
    //
    // NBT

}
