package codes.laivy.npc.mappings.versions;

import codes.laivy.npc.mappings.Version;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.fish.Fish;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.fish.Pufferfish;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.fish.Tropicalfish;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.*;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.illagers.IllagerWizard;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.skeleton.SkeletonWither;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.zombie.ZombieVillager;
import codes.laivy.npc.mappings.defaults.classes.entity.player.EntityPlayer;
import codes.laivy.npc.mappings.defaults.classes.entity.vehicle.Boat;
import codes.laivy.npc.mappings.defaults.classes.packets.info.action.IPlayerInfoAction;
import codes.laivy.npc.mappings.defaults.classes.packets.info.legacy.PlayerInfoActionLegacy;
import codes.laivy.npc.mappings.defaults.classes.packets.info.IPlayerInfoPacket;
import codes.laivy.npc.mappings.defaults.classes.packets.info.legacy.PlayerInfoPacket;
import codes.laivy.npc.mappings.instances.*;
import codes.laivy.npc.mappings.instances.classes.ClassConstructor;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.defaults.VersionCompound;
import codes.laivy.npc.mappings.defaults.classes.datawatcher.VersionedDataWatcherObject;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.*;
import codes.laivy.npc.mappings.defaults.classes.entity.boss.dragon.EnderDragon;
import codes.laivy.npc.mappings.defaults.classes.entity.boss.dragon.EnderSignal;
import codes.laivy.npc.mappings.defaults.classes.entity.boss.wither.Wither;
import codes.laivy.npc.mappings.defaults.classes.entity.boss.wither.WitherSkull;
import codes.laivy.npc.mappings.defaults.classes.entity.decoration.ItemFrame;
import codes.laivy.npc.mappings.defaults.classes.entity.decoration.LeashKnot;
import codes.laivy.npc.mappings.defaults.classes.entity.item.FallingBlock;
import codes.laivy.npc.mappings.defaults.classes.entity.item.Item;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.*;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.skeleton.Skeleton;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.zombie.Zombie;
import codes.laivy.npc.mappings.defaults.classes.entity.npc.Villager;
import codes.laivy.npc.mappings.defaults.classes.entity.npc.VillagerProfession;
import codes.laivy.npc.mappings.defaults.classes.enums.*;
import codes.laivy.npc.mappings.defaults.classes.datawatcher.DataWatcher;
import codes.laivy.npc.mappings.defaults.classes.datawatcher.WatchableObject;
import codes.laivy.npc.mappings.defaults.classes.entity.*;
import codes.laivy.npc.mappings.defaults.classes.entity.ambient.Bat;
import codes.laivy.npc.mappings.defaults.classes.entity.ambient.Egg;
import codes.laivy.npc.mappings.defaults.classes.entity.decoration.ArmorStand;
import codes.laivy.npc.mappings.defaults.classes.gameprofile.GameProfile;
import codes.laivy.npc.mappings.defaults.classes.gameprofile.Property;
import codes.laivy.npc.mappings.defaults.classes.gameprofile.PropertyMap;
import codes.laivy.npc.mappings.defaults.classes.java.*;
import codes.laivy.npc.mappings.defaults.classes.nbt.NBTBase;
import codes.laivy.npc.mappings.defaults.classes.nbt.tags.*;
import codes.laivy.npc.mappings.defaults.classes.others.chat.IChatBaseComponent;
import codes.laivy.npc.mappings.defaults.classes.others.location.*;
import codes.laivy.npc.mappings.defaults.classes.others.managers.PlayerInteractManager;
import codes.laivy.npc.mappings.defaults.classes.others.objects.*;
import codes.laivy.npc.mappings.defaults.classes.others.server.CraftServer;
import codes.laivy.npc.mappings.defaults.classes.others.server.MinecraftServer;
import codes.laivy.npc.mappings.defaults.classes.packets.*;
import codes.laivy.npc.mappings.defaults.classes.scoreboard.CraftScoreboard;
import codes.laivy.npc.mappings.defaults.classes.scoreboard.Scoreboard;
import codes.laivy.npc.mappings.defaults.classes.scoreboard.ScoreboardTeam;
import codes.laivy.npc.utils.ClassUtils;
import codes.laivy.npc.utils.NonLivingEntityType;
import com.google.common.collect.Multimap;
import io.netty.channel.Channel;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static codes.laivy.npc.LaivyNPC.laivynpc;
import static codes.laivy.npc.mappings.defaults.classes.others.objects.ItemStack.*;

public class V1_8_R1 extends Version {

    @Override
    protected boolean onLoad(@NotNull Class<? extends Version> version, @NotNull String key, @NotNull Executor executor) {
        return true;
    }

    @Override
    public boolean isEntityTypeSupported(Entity.@NotNull EntityType type) {
        return ClassUtils.isInstanceOf(getClass(), type.getSince());
    }

    /**
     * NBTTagCompound actions
     *
     * @param action set, remove, contains, and others...
     * @param object the NBTTagCompound instance
     * @param key the key (cannot be null if action != IS_EMPTY or action != KEY_SET)
     * @param value the value (cannot be null if action == SET)
     * @return if action == get will return the got NMS nbtbase value, if action == contains will return a boolean
     */
    @Override
    public @Nullable Object nbtCompound(@NotNull NBTCompoundAction action, @NotNull NBTTagCompound object, @Nullable StringObjExec key, @Nullable NBTBase value) {
        if (action != NBTCompoundAction.IS_EMPTY && action != NBTCompoundAction.KEY_SET && key == null) {
            throw new NullPointerException("The key cannot be null if action != IS_EMPTY or action != KEY_SET!");
        } if (action != NBTCompoundAction.SET && value != null) {
            throw new NullPointerException("The value needs to be null if action != SET");
        } if (action == NBTCompoundAction.SET && value == null) {
            throw new NullPointerException("The NBTBase cannot be null in SET actions!");
        }

        if (action == NBTCompoundAction.SET) {
            getMethodExec("NBTTagCompound:set").invokeInstance(object, key, value);
        } else if (action == NBTCompoundAction.REMOVE) {
            getMethodExec("NBTTagCompound:remove").invokeInstance(object, key);
        } else if (action == NBTCompoundAction.CONTAINS) {
            return getMethodExec("NBTTagCompound:contains").invokeInstance(object, key);
        } else if (action == NBTCompoundAction.IS_EMPTY) {
            return getMethodExec("NBTTagCompound:isEmpty").invokeInstance(object);
        } else if (action == NBTCompoundAction.GET) {
            return getMethodExec("NBTTagCompound:get").invokeInstance(object, key);
        } else if (action == NBTCompoundAction.KEY_SET) {
            return getMethodExec("NBTTagCompound:keySet").invokeInstance(object);
        } else {
            throw new IllegalStateException("Cannot invoke nbtCompound() method due to an rare and unknown error");
        }

        return null;
    }

    @Override
    public Object nbtList(@NotNull NBTListAction action, @NotNull NBTTagList object, @Nullable Object value) {
        if (value == null && action != NBTListAction.TRANSLATED_LIST && action != NBTListAction.CLEAR) {
            throw new NullPointerException("The value cannot be null if (action != TRANSLATED_LIST) or (action != CLEAR)");
        }

        if (action == NBTListAction.ADD) {
            if (value instanceof NBTBase) {
                FieldExecutor field = getFieldExec("NBTTagList:list");

                //noinspection unchecked
                List<Object> nbtBaseList = (List<Object>) field.invokeInstance(object);

                if (nbtBaseList != null) {
                    nbtBaseList.add(value);
                } else {
                    field.set(object, new ArrayList<Object>() {{add(value);}});
                }
            } else {
                throw new IllegalArgumentException("The value needs to be a '" + getClassExec("NBTBase:NBTTagList").getName() + "', and the passed is '" + value.getClass().getName() + "'");
            }
        } else if (action == NBTListAction.REMOVE) {
            if (value instanceof NBTBase) {
                FieldExecutor field = getFieldExec("NBTTagList:list");

                //noinspection unchecked
                List<Object> nbtBaseList = (List<Object>) field.invokeInstance(object);

                if (nbtBaseList != null) {
                    nbtBaseList.remove(value);
                }
            } else {
                throw new IllegalArgumentException("The value needs to be a '" + getClassExec("NBTBase:NBTTagList").getName() + "', and the passed is '" + value.getClass().getName() + "'");
            }
        } else if (action == NBTListAction.TRANSLATED_LIST) {
            FieldExecutor field = getFieldExec("NBTTagList:list");
            //noinspection unchecked
            List<Object> nbtBaseList = (List<Object>) field.invokeInstance(object);
            List<NBTBase> returnList = new ArrayList<>();

            if (nbtBaseList != null) {
                for (Object nbtbase : nbtBaseList) {
                    returnList.add(nbtTag(NBTBase.getTagType(nbtbase), nbtbase));
                }
                return returnList;
            } else {
                return new ArrayList<NBTBase>();
            }
        } else if (action == NBTListAction.CLEAR) {
            FieldExecutor field = getFieldExec("NBTTagList:list");
            field.set(object, new ArrayList<>());
        } else if (action == NBTListAction.SET) {
            if (value instanceof List) {
                FieldExecutor field = getFieldExec("NBTTagList:list");
                field.set(object, value);
            } else {
                throw new IllegalArgumentException("The parameter inserted is '" + value.getClass().getName() + "', and needs to be 'List'");
            }
        } else {
            throw new IllegalArgumentException("Cannot invoke nbtList() method due to an rare and unknown error");
        }

        return null;
    }

    @Override
    public void nbtbaseConcatenate(@NotNull NBTBase into, @NotNull Object from) {
        if (into instanceof NBTTagCompound && from instanceof NBTTagCompound) {
            NBTTagCompound i = (NBTTagCompound) into;
            NBTTagCompound f = (NBTTagCompound) from;

            for (String key : f.keySet()) {
                i.set(key, Objects.requireNonNull(nbtCompound(VersionCompound.NBTCompoundAction.GET, f, new StringObjExec(key), null)));
            }
        } else if (into instanceof NBTTagList && from instanceof List) {
            NBTTagList i = (NBTTagList) into;
            //noinspection unchecked
            List<NBTBase> f = (List<NBTBase>) from;

            List<Object> list = new ArrayList<>();
            for (NBTBase base : f) {
                list.add(base.getValue());
            }

            nbtList(NBTListAction.SET, i, list);
        } else {
            throw new IllegalArgumentException("Cannot concatenate NBT bases. Into: '" + into.getClassExecutor().getName() + "', From: '" + from.getClass().getName() + "'");
        }
    }

    @Override
    public @NotNull NBTBase nbtTag(@NotNull NBTTag tag, @NotNull Object... objects) {
        try {
            Object object;
            Constructor<?> constructor = null;

            if (objects.length > 0 && getClassExec("NBTBase").isReflectiveInstance(objects[0])) {
                object = objects[0];

                if (tag == NBTTag.BYTE) {
                    if (!getClassExec("NBTBase:NBTTagByte").isReflectiveInstance(objects[0])) {
                        constructor = NBTTagByte.class.getDeclaredConstructor(Object.class);
                    }
                } else if (tag == NBTTag.BYTE_ARRAY) {
                    if (!getClassExec("NBTBase:NBTTagByteArray").isReflectiveInstance(objects[0])) {
                        constructor = NBTTagByteArray.class.getDeclaredConstructor(Object.class);
                    }
                } else if (tag == NBTTag.COMPOUND) {
                    if (!getClassExec("NBTBase:NBTTagCompound").isReflectiveInstance(objects[0])) {
                        constructor = NBTTagCompound.class.getDeclaredConstructor(Object.class);
                    }
                } else if (tag == NBTTag.DOUBLE) {
                    if (!getClassExec("NBTBase:NBTTagDouble").isReflectiveInstance(objects[0])) {
                        constructor = NBTTagDouble.class.getDeclaredConstructor(Object.class);
                    }
                } else if (tag == NBTTag.FLOAT) {
                    if (!getClassExec("NBTBase:NBTTagFloat").isReflectiveInstance(objects[0])) {
                        constructor = NBTTagFloat.class.getDeclaredConstructor(Object.class);
                    }
                } else if (tag == NBTTag.INT) {
                    if (!getClassExec("NBTBase:NBTTagInt").isReflectiveInstance(objects[0])) {
                        constructor = NBTTagInt.class.getDeclaredConstructor(Object.class);
                    }
                } else if (tag == NBTTag.INT_ARRAY) {
                    if (!getClassExec("NBTBase:NBTTagIntArray").isReflectiveInstance(objects[0])) {
                        constructor = NBTTagIntArray.class.getDeclaredConstructor(Object.class);
                    }
                } else if (tag == NBTTag.LIST) {
                    if (!getClassExec("NBTBase:NBTTagList").isReflectiveInstance(objects[0])) {
                        constructor = NBTTagList.class.getDeclaredConstructor(Object.class);
                    }
                } else if (tag == NBTTag.LONG) {
                    if (!getClassExec("NBTBase:NBTTagLong").isReflectiveInstance(objects[0])) {
                        constructor = NBTTagLong.class.getDeclaredConstructor(Object.class);
                    }
                } else if (tag == NBTTag.SHORT) {
                    if (!getClassExec("NBTBase:NBTTagShort").isReflectiveInstance(objects[0])) {
                        constructor = NBTTagShort.class.getDeclaredConstructor(Object.class);
                    }
                } else if (tag == NBTTag.STRING) {
                    if (!getClassExec("NBTBase:NBTTagString").isReflectiveInstance(objects[0])) {
                        constructor = NBTTagString.class.getDeclaredConstructor(Object.class);
                    }
                } else {
                    throw new IllegalArgumentException("Cannot execute nbtTag²() due to an unknown and rare error");
                }

                if (constructor == null) {
                    throw new IllegalArgumentException("Wrong parameter type! To create a '" + tag.name() + "' base, the parameter needs to be '" + (tag.getCheckClass() != null ? tag.getCheckClass().getName() : tag.name()) + "', and you passed '" + objects[0].getClass().getName() + "'");
                }
            } else {
                if (objects.length == 0 && tag != NBTTag.LIST && tag != NBTTag.COMPOUND) {
                    throw new NullPointerException("Wrong number of arguments! To create a the '" + tag.name() + "' base you need atleast 1 base parameter!");
                } if (tag == NBTTag.COMPOUND && objects.length > 0 && !(getClassExec("NBTBase:NBTTagCompound").isReflectiveInstance(objects[0]))) {
                    throw new IllegalArgumentException("Wrong parameter type! To create a 'NBTTagCompound' base, the parameter needs to be 'NBTTagCompound', and you passed '" + objects[0].getClass().getName() + "'");
                } if (tag == NBTTag.LIST && objects.length > 0 && !(objects[0] instanceof List)) {
                    throw new IllegalArgumentException("Wrong parameter type! To create a 'NBTTagList' base, the parameter needs to be 'List', and you passed '" + objects[0].getClass().getName() + "'");
                }

                if (tag == NBTTag.BYTE) {
                    ClassConstructor c = getClassExec("NBTBase:NBTTagByte").getConstructor(ClassExecutor.BYTE);
                    object = c.newInstance(new ByteObjExec((byte) objects[0]));
                    constructor = NBTTagByte.class.getDeclaredConstructor(Object.class);
                } else if (tag == NBTTag.BYTE_ARRAY) {
                    ClassConstructor c = getClassExec("NBTBase:NBTTagByteArray").getConstructor(ClassExecutor.BYTE_ARRAY);
                    object = c.newInstance(new ByteArrayObjExec((byte[]) objects[0]));
                    constructor = NBTTagByteArray.class.getDeclaredConstructor(Object.class);
                } else if (tag == NBTTag.COMPOUND) {
                    ClassConstructor c = getClassExec("NBTBase:NBTTagCompound").getConstructor();
                    object = c.newInstance();
                    constructor = NBTTagCompound.class.getDeclaredConstructor(Object.class);
                } else if (tag == NBTTag.DOUBLE) {
                    ClassConstructor c = getClassExec("NBTBase:NBTTagDouble").getConstructor(ClassExecutor.DOUBLE);
                    object = c.newInstance(new DoubleObjExec((double) objects[0]));
                    constructor = NBTTagDouble.class.getDeclaredConstructor(Object.class);
                } else if (tag == NBTTag.FLOAT) {
                    ClassConstructor c = getClassExec("NBTBase:NBTTagFloat").getConstructor(ClassExecutor.FLOAT);
                    object = c.newInstance(new FloatObjExec((float) objects[0]));
                    constructor = NBTTagFloat.class.getDeclaredConstructor(Object.class);
                } else if (tag == NBTTag.INT) {
                    ClassConstructor c = getClassExec("NBTBase:NBTTagInt").getConstructor(ClassExecutor.INT);
                    object = c.newInstance(new IntegerObjExec((int) objects[0]));
                    constructor = NBTTagInt.class.getDeclaredConstructor(Object.class);
                } else if (tag == NBTTag.INT_ARRAY) {
                    ClassConstructor c = getClassExec("NBTBase:NBTTagIntArray").getConstructor(ClassExecutor.INT_ARRAY);
                    object = c.newInstance(new IntegerArrayObjExec((int[]) objects[0]));
                    constructor = NBTTagIntArray.class.getDeclaredConstructor(Object.class);
                } else if (tag == NBTTag.LIST) {
                    ClassConstructor c = getClassExec("NBTBase:NBTTagList").getConstructor();
                    object = c.newInstance();
                    constructor = NBTTagList.class.getDeclaredConstructor(Object.class);
                } else if (tag == NBTTag.LONG) {
                    ClassConstructor c = getClassExec("NBTBase:NBTTagLong").getConstructor(ClassExecutor.LONG);
                    object = c.newInstance(new LongObjExec((long) objects[0]));
                    constructor = NBTTagLong.class.getDeclaredConstructor(Object.class);
                } else if (tag == NBTTag.SHORT) {
                    ClassConstructor c = getClassExec("NBTBase:NBTTagShort").getConstructor(ClassExecutor.SHORT);
                    object = c.newInstance(new ShortObjExec((short) objects[0]));
                    constructor = NBTTagShort.class.getDeclaredConstructor(Object.class);
                } else if (tag == NBTTag.STRING) {
                    ClassConstructor c = getClassExec("NBTBase:NBTTagString").getConstructor(ClassExecutor.STRING);
                    object = c.newInstance(new StringObjExec((String) objects[0]));
                    constructor = NBTTagString.class.getDeclaredConstructor(Object.class);
                } else {
                    throw new IllegalStateException("Cannot invoke nbtTag() method due to an invalid tag parameter error");
                }
            }

            constructor.setAccessible(true);
            Object base = constructor.newInstance(object);

            if (base instanceof NBTTagCompound) {
                if (objects.length > 0) {
                    NBTTagCompound compound = (NBTTagCompound) base;
                    nbtbaseConcatenate(compound, objects[0]);
                }
            } else if (base instanceof NBTTagList) {
                if (objects.length > 0) {
                    NBTTagList list = (NBTTagList) base;
                    nbtbaseConcatenate(list, objects[0]);
                }
            }

            return (NBTBase) base;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public @Nullable Entity getEntityInstance(Entity.@NotNull EntityType type, @NotNull Location location) {
        Entity entity;
        if (type == Entity.EntityType.ARMOR_STAND) {
            Object object = getClassExec("Entity:ArmorStand").getConstructor(getClassExec("World"), ClassExecutor.DOUBLE, ClassExecutor.DOUBLE, ClassExecutor.DOUBLE).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle(), new DoubleObjExec(location.getX()), new DoubleObjExec(location.getBlockY()), new DoubleObjExec(location.getZ()));
            entity = new ArmorStand(object);
        } else if (type == Entity.EntityType.PIG) {
            Object object = getClassExec("Entity:Pig").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new Pig(object);
        } else if (type == Entity.EntityType.BAT) {
            Object object = getClassExec("Entity:Bat").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new Bat(object);
        } else if (type == Entity.EntityType.EGG) {
            Object object = getClassExec("Entity:Egg").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new Egg(object);
        } else if (type == Entity.EntityType.CHICKEN) {
            Object object = getClassExec("Entity:Chicken").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new Chicken(object);
        } else if (type == Entity.EntityType.OCELOT) {
            Object object = getClassExec("Entity:Ocelot").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new Ocelot(object);
        } else if (type == Entity.EntityType.CAT) {
            Object object = getClassExec("Entity:Ocelot").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new Cat(object);
        } else if (type == Entity.EntityType.HORSE) {
            Object object = getClassExec("Entity:Horse").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new Horse(object);
            setEntityAbstractHorseType((AbstractHorse) entity, AbstractHorse.Type.HORSE);
        } else if (type == Entity.EntityType.HORSE_DONKEY) {
            Object object = getClassExec("Entity:Horse").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new HorseDonkey(object);
            setEntityAbstractHorseType((AbstractHorse) entity, AbstractHorse.Type.DONKEY);
        } else if (type == Entity.EntityType.HORSE_MULE) {
            Object object = getClassExec("Entity:Horse").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new HorseMule(object);
            setEntityAbstractHorseType((AbstractHorse) entity, AbstractHorse.Type.MULE);
        } else if (type == Entity.EntityType.HORSE_SKELETON) {
            Object object = getClassExec("Entity:Horse").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new HorseSkeleton(object);
            setEntityAbstractHorseType((AbstractHorse) entity, AbstractHorse.Type.SKELETON);
        } else if (type == Entity.EntityType.HORSE_ZOMBIE) {
            Object object = getClassExec("Entity:Horse").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new HorseZombie(object);
            setEntityAbstractHorseType((AbstractHorse) entity, AbstractHorse.Type.ZOMBIE);
        } else if (type == Entity.EntityType.COW) {
            Object object = getClassExec("Entity:Cow").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new Cow(object);
        } else if (type == Entity.EntityType.IRON_GOLEM) {
            Object object = getClassExec("Entity:IronGolem").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new IronGolem(object);
        } else if (type == Entity.EntityType.RABBIT) {
            Object object = getClassExec("Entity:Rabbit").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new Rabbit(object);
        } else if (type == Entity.EntityType.SHEEP) {
            Object object = getClassExec("Entity:Sheep").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new Sheep(object);
        } else if (type == Entity.EntityType.SNOWMAN) {
            Object object = getClassExec("Entity:Snowman").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new Snowman(object);
        } else if (type == Entity.EntityType.SQUID) {
            Object object = getClassExec("Entity:Squid").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new Squid(object);
        } else if (type == Entity.EntityType.WOLF) {
            Object object = getClassExec("Entity:Wolf").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new Wolf(object);
        } else if (type == Entity.EntityType.ITEM_FRAME) {
            Object object = getClassExec("Entity:ItemFrame").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new ItemFrame(object);
        } else if (type == Entity.EntityType.LEASH_KNOT) {
            Object object = getClassExec("Entity:LeashKnot").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new LeashKnot(object);
        } else if (type == Entity.EntityType.ITEM) {
            Object object = getClassExec("Entity:Item").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new Item(object);
        } else if (type == Entity.EntityType.ENDER_DRAGON) {
            Object object = getClassExec("Entity:EnderDragon").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new EnderDragon(object);
        } else if (type == Entity.EntityType.ENDER_SIGNAL) {
            Object object = getClassExec("Entity:EnderSignal").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new EnderSignal(object);
        } else if (type == Entity.EntityType.WITHER) {
            Object object = getClassExec("Entity:Wither").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new Wither(object);
        } else if (type == Entity.EntityType.WITHER_SKULL) {
            Object object = getClassExec("Entity:WitherSkull").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new WitherSkull(object);
        } else if (type == Entity.EntityType.BLAZE) {
            Object object = getClassExec("Entity:Blaze").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new Blaze(object);
        } else if (type == Entity.EntityType.CREEPER) {
            Object object = getClassExec("Entity:Creeper").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new Creeper(object);
        } else if (type == Entity.EntityType.ENDERMAN) {
            Object object = getClassExec("Entity:Enderman").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new Enderman(object);
        } else if (type == Entity.EntityType.GHAST) {
            Object object = getClassExec("Entity:Ghast").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new Ghast(object);
        } else if (type == Entity.EntityType.GUARDIAN) {
            Object object = getClassExec("Entity:Guardian").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new Guardian(object);
        } else if (type == Entity.EntityType.SILVERFISH) {
            Object object = getClassExec("Entity:Silverfish").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new Silverfish(object);
        } else if (type == Entity.EntityType.SKELETON) {
            Object object = getClassExec("Entity:Skeleton").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new Skeleton(object);
        } else if (type == Entity.EntityType.SKELETON_WITHER) {
            Object object = getClassExec("Entity:Skeleton").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new SkeletonWither(object);
            setEntitySkeletonType((Skeleton) entity, Skeleton.Type.WITHER);
        } else if (type == Entity.EntityType.SLIME) {
            Object object = getClassExec("Entity:Slime").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new Slime(object);
        } else if (type == Entity.EntityType.SPIDER) {
            Object object = getClassExec("Entity:Spider").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new Spider(object);
        } else if (type == Entity.EntityType.WITCH) {
            Object object = getClassExec("Entity:Witch").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new Witch(object);
        } else if (type == Entity.EntityType.ZOMBIE) {
            Object object = getClassExec("Entity:Zombie").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new Zombie(object);
        } else if (type == Entity.EntityType.ZOMBIE_VILLAGER) {
            Object object = getClassExec("Entity:Zombie").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new ZombieVillager(object);
            setEntityZombieType((Zombie) entity, Zombie.Type.VILLAGER);
        } else if (type == Entity.EntityType.VILLAGER) {
            Object object = getClassExec("Entity:Villager").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new Villager(object);
        } else if (type == Entity.EntityType.BOAT) {
            Object object = getClassExec("Entity:Boat").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new Boat(object);
        } else if (type == Entity.EntityType.CAVE_SPIDER) {
            Object object = getClassExec("Entity:CaveSpider").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new CaveSpider(object);
        } else {
            return null;
        }

        return entity;
    }

    @Override
    public @NotNull FallingBlock createFallingBlockEntity(@NotNull Location location, @NotNull Material material) {
        Object object = getClassExec("Entity:FallingBlock").getConstructor(getClassExec("World"), ClassExecutor.DOUBLE, ClassExecutor.DOUBLE, ClassExecutor.DOUBLE, getClassExec("IBlockData")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle(), new DoubleObjExec(location.getX()), new DoubleObjExec(location.getY()), new DoubleObjExec(location.getZ()), new Block(getMethodExec("CraftMagicNumbers:getBlock").invokeStatic(new ObjectExecutor(material) {
            @Override
            public @NotNull ClassExecutor getClassExecutor() {
                return new ClassExecutor(Material.class);
            }
        })).getData());
        return new FallingBlock(object);
    }

    @Override
    public @NotNull EntityPose getPose(@NotNull Entity entity) {
        throw new UnsupportedOperationException("The pose functions is only allowed at the 1.14+");
    }

    @Override
    public void setPose(@NotNull Entity entity, @NotNull EntityPose pose) {
        throw new UnsupportedOperationException("The pose functions is only allowed at the 1.14+");
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public @NotNull Location getEntityLocation(@NotNull Entity entity) {
        return new Location(
                (org.bukkit.World) new World(getFieldExec("Entity:world").invokeInstance(entity)).getCraftWorld().getValue(),
                (double) getFieldExec("Entity:locX").invokeInstance(entity),
                (double) getFieldExec("Entity:locY").invokeInstance(entity),
                (double) getFieldExec("Entity:locZ").invokeInstance(entity)
        );
    }

    @Override
    public void setEntityLocation(@NotNull Entity entity, @NotNull Location location) {
        laivynpc().getVersion().getMethodExec("Entity:Entity:setLocation").invokeInstance(entity, new DoubleObjExec(location.getX()), new DoubleObjExec(location.getY()), new DoubleObjExec(location.getZ()), new FloatObjExec(location.getYaw()), new FloatObjExec(location.getPitch()));
    }

    @Override
    public @NotNull BlockPosition createBlockPosition(int x, int y, int z) {
        Object object = getClassExec("BlockPosition").getConstructor(ClassExecutor.INT, ClassExecutor.INT, ClassExecutor.INT).newInstance(new IntegerObjExec(x), new IntegerObjExec(y), new IntegerObjExec(z));
        return new BlockPosition(object);
    }

    @Override
    public @Nullable String getEntityCustomName(@NotNull Entity entity) {
        return (String) getMethodExec("Entity:Entity:getCustomName").invokeInstance(entity);
    }

    @Override
    public void setEntityCustomName(@NotNull Entity entity, @Nullable String customName) {
        getMethodExec("Entity:Entity:setCustomName").invokeInstance(entity, new StringObjExec(customName));
    }

    @Override
    public boolean isEntityInvisible(@NotNull Entity entity) {
        //noinspection DataFlowIssue
        return (boolean) laivynpc().getVersion().getMethodExec("Entity:Entity:isInvisible").invokeInstance(entity);
    }

    @Override
    public void setEntityInvisible(@NotNull Entity entity, boolean invisible) {
        laivynpc().getVersion().getMethodExec("Entity:Entity:setInvisible").invokeInstance(entity, new BooleanObjExec(invisible));
    }

    @Override
    public @NotNull Cat.Variant getEntityCatVariant(@NotNull Cat cat) {
        //noinspection DataFlowIssue
        int id = (int) getMethodExec("Entity:Ocelot:getCatType").invokeInstance(cat);

        Cat.Variant variant = null;
        for (Cat.Variant v : Cat.Variant.values()) {
            if (v.getId() == id) {
                variant = v;
                break;
            }
        }
        if (variant == null) {
            throw new NullPointerException("Couldn't get this variant id '" + id + "'");
        }

        return variant;
    }

    @Override
    public void setEntityCatVariant(@NotNull Cat cat, @NotNull Cat.Variant variant) {
        getMethodExec("Entity:Ocelot:setCatType").invokeInstance(cat, new IntegerObjExec(variant.getId()));
    }

    @Override
    public @NotNull Horse.Type getEntityAbstractHorseType(@NotNull AbstractHorse horse) {
        //noinspection DataFlowIssue
        return AbstractHorse.Type.getById((int) getMethodExec("Entity:Horse:getType").invokeInstance(horse));
    }
    @Override
    public void setEntityAbstractHorseType(@NotNull AbstractHorse horse, Horse.@NotNull Type type) {
        if (!type.isCompatible()) {
            throw new UnsupportedOperationException("This horse type '" + type.name() + "' is only compatible with '" + type.getSince().getSimpleName() + "' or higher");
        }

        getMethodExec("Entity:Horse:setType").invokeInstance(horse, new IntegerObjExec(type.getId()));
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public @NotNull HorseArmor getEntityHorseArmor(@NotNull Horse horse) {
        int index = (int) getObject("Metadata:Horse:Armor");
        return HorseArmor.getByData((int) horse.getDataWatcher().get(index));
    }

    @Override
    public void setEntityHorseArmor(@NotNull Horse horse, @NotNull HorseArmor armor) {
        if (!armor.isCompatible()) {
            throw new UnsupportedOperationException("This horse armor type isn't compatible with that version '" + armor.name() + "'");
        }

        int index = (int) getObject("Metadata:Horse:Armor");
        horse.getDataWatcher().set(index, armor.getData());
    }

    @Override
    public int getEntityHorseVariant(@NotNull Horse horse) {
        //noinspection DataFlowIssue
        return (int) getMethodExec("Entity:Horse:getVariant").invokeInstance(horse);
    }

    @Override
    public void setEntityHorseVariant(@NotNull Horse horse, int variant) {
        getMethodExec("Entity:Horse:setVariant").invokeInstance(horse, new IntegerObjExec(variant));
    }

    @Override
    public @NotNull Rabbit.Variant getEntityRabbitType(@NotNull Rabbit rabbit) {
        //noinspection DataFlowIssue
        return Rabbit.Variant.getById((int) getMethodExec("Entity:Rabbit:getVariant").invokeInstance(rabbit));
    }

    @Override
    public void setEntityRabbitType(@NotNull Rabbit rabbit, Rabbit.@NotNull Variant type) {
        getMethodExec("Entity:Rabbit:setVariant").invokeInstance(rabbit, new IntegerObjExec(type.getId()));
    }

    @Override
    public boolean isEntitySheepSheared(@NotNull Sheep sheep) {
        //noinspection DataFlowIssue
        return (boolean) laivynpc().getVersion().getMethodExec("Entity:Sheep:isSheared").invokeInstance(sheep);
    }

    @Override
    public void setEntitySheepSheared(@NotNull Sheep sheep, boolean sheared) {
        laivynpc().getVersion().getMethodExec("Entity:Sheep:setSheared").invokeInstance(sheep, new BooleanObjExec(sheared));
    }

    @Override
    public @NotNull EnumColorEnum.EnumColor getEntitySheepColor(@NotNull Sheep sheep) {
        return new EnumColorEnum.EnumColor((Enum<?>) Objects.requireNonNull(getMethodExec("Entity:Sheep:getColor").invokeInstance(sheep)));
    }

    @Override
    public void setEntitySheepColor(@NotNull Sheep sheep, EnumColorEnum.@NotNull EnumColor color) {
        getMethodExec("Entity:Sheep:setColor").invokeInstance(sheep, color);
    }

    @Override
    public @NotNull Skeleton.Type getEntitySkeletonType(@NotNull Skeleton skeleton) {
        //noinspection DataFlowIssue
        int value = (int) getMethodExec("Entity:Skeleton:getSkeletonType").invokeInstance(skeleton);

        if (value == 0) {
            return Skeleton.Type.NORMAL;
        } else if (value == 1) {
            return Skeleton.Type.WITHER;
        } else {
            throw new IllegalStateException("Couldn't find this skeleton type '" + value + "'");
        }
    }

    @Override
    public void setEntitySkeletonType(@NotNull Skeleton skeleton, Skeleton.@NotNull Type type) {
        if (!type.isCompatible()) {
            throw new UnsupportedOperationException("This skeleton type '" + type.name() + "' isn't compatible with that version!");
        }

        int value;
        if (type == Skeleton.Type.NORMAL) {
            value = 0;
        } else if (type == Skeleton.Type.WITHER) {
            value = 1;
        } else {
            throw new IllegalArgumentException("Couldn't find this skeleton type properties '" + type.name() + "'. Is it compatible with that version?");
        }

        getMethodExec("Entity:Skeleton:setSkeletonType").invokeInstance(skeleton, new IntegerObjExec(value));
    }

    @Override
    public @NotNull VillagerProfession getEntityVillagerProfession(@NotNull Villager villager) {
        //noinspection DataFlowIssue
        return new VillagerProfession(VillagerProfession.Type.getById((int) getMethodExec("Entity:Villager:getProfession").invokeInstance(villager)), 1);
    }

    @Override
    public void setEntityVillagerProfession(@NotNull Villager villager, @NotNull VillagerProfession profession) {
        getMethodExec("Entity:Villager:setProfession").invokeInstance(villager, new IntegerObjExec(profession.getType().getId()));
    }

    @Override
    public @NotNull Villager.Type getEntityVillagerType(@NotNull Villager villager) {
        throw new UnsupportedOperationException("The villager types are only available at 1.14+");
    }

    @Override
    public void setEntityVillagerType(@NotNull Villager villager, Villager.@NotNull Type type) {
        throw new UnsupportedOperationException("The villager types are only available at 1.14+");
    }

    @Override
    public @NotNull VillagerProfession getEntityZombieVillagerProfession(@NotNull ZombieVillager zombieVillager) {
        throw new UnsupportedOperationException("The zombie villager profession are only available at 1.9+");
    }

    @Override
    public void setEntityZombieVillagerProfession(@NotNull ZombieVillager zombieVillager, @NotNull VillagerProfession profession) {
        throw new UnsupportedOperationException("The zombie villager profession are only available at 1.9+");
    }

    @Override
    public @NotNull Villager.Type getEntityZombieVillagerType(@NotNull ZombieVillager zombieVillager) {
        throw new UnsupportedOperationException("The villager types are only available at 1.14+");
    }

    @Override
    public void setEntityZombieVillagerType(@NotNull ZombieVillager zombieVillager, Villager.@NotNull Type type) {
        throw new UnsupportedOperationException("The villager types are only available at 1.14+");
    }

    @Override
    public boolean isEntityEndermanScreaming(@NotNull Enderman enderman) {
        //noinspection DataFlowIssue
        return (boolean) getMethodExec("Entity:Enderman:isScreaming").invokeInstance(enderman);
    }

    @Override
    public void setEntityEndermanScreaming(@NotNull Enderman enderman, boolean screaming) {
        getMethodExec("Entity:Enderman:setScreaming").invokeInstance(enderman, new BooleanObjExec(screaming));
    }

    @Override
    public @Nullable Material getEntityEndermanCarryingMaterial(@NotNull Enderman enderman) {
        return new IBlockData(laivynpc().getVersion().getMethodExec("Entity:Enderman:getCarried").invokeInstance(enderman)).getBlock().getMaterial();
    }

    @Override
    public void setEntityEndermanCarriedMaterial(@NotNull Enderman enderman, @Nullable Material material) {
        if (material != null && !material.isBlock()) {
            throw new IllegalArgumentException("This material isn't a block");
        }

        if (material != null) {
            laivynpc().getVersion().getMethodExec("Entity:Enderman:setCarried").invokeInstance(enderman, getBlockData(material));
        } else {
            laivynpc().getVersion().getMethodExec("Entity:Enderman:setCarried").invokeInstance(enderman, new IBlockData(null));
        }
    }

    @Override
    public @Nullable Zombie.Type getEntityZombieType(@NotNull Zombie zombie) {
        throw new IllegalArgumentException("This method is only compatible with 1.9+");
    }

    @Override
    public void setEntityZombieType(@NotNull Zombie zombie, Zombie.@Nullable Type type) {
        if (type != null && !type.isCompatible()) {
            throw new IllegalArgumentException("This type isn't compatible with that version.");
        }

        getMethodExec("Entity:Zombie:setVillagerType").invokeInstance(zombie, new BooleanObjExec(type != null));
    }

    @Override
    public boolean isEntityZombieBaby(@NotNull Zombie zombie) {
        //noinspection DataFlowIssue
        return (boolean) getMethodExec("Entity:Zombie:isBaby").invokeInstance(zombie);
    }

    @Override
    public void setEntityZombieBaby(@NotNull Zombie zombie, boolean baby) {
        getMethodExec("Entity:Zombie:setBaby").invokeInstance(zombie, new BooleanObjExec(baby));
    }

    @Override
    public boolean isEntityCreeperIgnited(@NotNull Creeper creeper) {
        //noinspection DataFlowIssue
        return ((byte) creeper.getDataWatcher().get((int) getObject("Metadata:Creeper:Ignited"))) == 1;
    }
    @Override
    public void setEntityCreeperIgnited(@NotNull Creeper creeper, boolean flag) {
        creeper.getDataWatcher().set((int) getObject("Metadata:Creeper:Ignited"), (byte) (flag ? 1 : 0));
    }

    @Override
    public boolean isEntityCreeperPowered(@NotNull Creeper creeper) {
        //noinspection DataFlowIssue
        return (boolean) laivynpc().getVersion().getMethodExec("Entity:Creeper:isPowered").invokeInstance(creeper);
    }
    @Override
    public void setEntityCreeperPowered(@NotNull Creeper creeper, boolean powered) {
        laivynpc().getVersion().getMethodExec("Entity:Creeper:setPowered").invokeInstance(creeper, new BooleanObjExec(powered));
    }

    @Override
    public boolean isEntityGhastAttacking(@NotNull Ghast ghast) {
        //noinspection DataFlowIssue
        return ((byte) ghast.getDataWatcher().get((int) getObject("Metadata:Ghast:Attacking"))) == 1;
    }
    @Override
    public void setEntityGhastAttacking(@NotNull Ghast ghast, boolean flag) {
        ghast.getDataWatcher().set((int) getObject("Metadata:Ghast:Attacking"), (byte) (flag ? 1 : 0));
    }

    @Override
    public void setEntityGuardianTarget(@NotNull Guardian guardian, @Nullable EntityLiving entity) {
        int id = 0;
        if (entity != null) {
            id = entity.getId();
        }
        guardian.getDataWatcher().set((int) getObject("Metadata:Guardian:Target"), id);
    }

    @Override
    public int getEntitySlimeSize(@NotNull Slime slime) {
        //noinspection DataFlowIssue
        return (int) getMethodExec("Entity:Slime:getSize").invokeInstance(slime);
    }

    @Override
    public void setEntitySlimeSize(@NotNull Slime slime, int size) {
        getMethodExec("Entity:Slime:setSize").invokeInstance(slime, new IntegerObjExec(size));
    }

    @Override
    public boolean hasEntitySnowmanHat(@NotNull Snowman snowman) {
        throw new UnsupportedOperationException("The pumpkin hat of a snowman is only available at 1.9+");
    }

    @Override
    public void setEntitySnowmanHat(@NotNull Snowman snowman, boolean hat) {
        throw new UnsupportedOperationException("The pumpkin hat of a snowman is only available at 1.9+");
    }

    @Override
    public boolean hasEntityChestedHorseChest(@NotNull AbstractChestedHorse horse) {
        //noinspection DataFlowIssue
        return (boolean) getMethodExec("Entity:Horse:hasChest").invokeInstance(horse);
    }

    @Override
    public void setEntityChestedHorseChest(@NotNull AbstractChestedHorse horse, boolean chest) {
        getMethodExec("Entity:Horse:setHasChest").invokeInstance(horse, new BooleanObjExec(chest));
    }

    @Override
    public @NotNull Llama.Variant getEntityLlamaVariant(@NotNull Llama llama) {
        throw new UnsupportedOperationException("The llamas is only available at 1.11+");
    }

    @Override
    public void setEntityLlamaVariant(@NotNull Llama llama, Llama.@NotNull Variant variant) {
        throw new UnsupportedOperationException("The llamas is only available at 1.11+");
    }

    @Override
    public @Nullable EnumColorEnum.EnumColor getEntityLlamaCarpetColor(@NotNull Llama llama) {
        throw new UnsupportedOperationException("The llamas is only available at 1.11+");
    }

    @Override
    public void setEntityLlamaCarpetColor(@NotNull Llama llama, EnumColorEnum.@Nullable EnumColor color) {
        throw new UnsupportedOperationException("The llamas is only available at 1.11+");
    }

    @Override
    public @NotNull EnumSpellEnum.Spell getEntityIllagerWizardSpell(@NotNull IllagerWizard wizard) {
        throw new UnsupportedOperationException("The wizard spells is only available at 1.11+");
    }

    @Override
    public void setEntityIllagerWizardSpell(@NotNull IllagerWizard wizard, EnumSpellEnum.@NotNull Spell spell) {
        throw new UnsupportedOperationException("The wizard spells is only available at 1.11+");
    }

    @Override
    public @NotNull Parrot.Variant getEntityParrotVariant(@NotNull Parrot parrot) {
        throw new UnsupportedOperationException("The parrot is only available at 1.12+");
    }

    @Override
    public void setEntityParrotVariant(@NotNull Parrot parrot, Parrot.@NotNull Variant variant) {
        throw new UnsupportedOperationException("The parrot is only available at 1.12+");
    }

    @Override
    public @NotNull EnumColorEnum.EnumColor getEntityShulkerColor(@NotNull Shulker shulker) {
        throw new UnsupportedOperationException("The shulker color is only available at 1.11+");
    }

    @Override
    public void setEntityShulkerColor(@NotNull Shulker shulker, EnumColorEnum.@NotNull EnumColor variant) {
        throw new UnsupportedOperationException("The shulker color is only available at 1.11+");
    }

    @Override
    public boolean isEntitySpiderClimbing(@NotNull Spider spider) {
        //noinspection DataFlowIssue
        return (boolean) laivynpc().getVersion().getMethodExec("Entity:Spider:isClimbing").invokeInstance(spider);
    }
    @Override
    public void setEntitySpiderClimbing(@NotNull Spider spider, boolean climbing) {
        laivynpc().getVersion().getMethodExec("Entity:Spider:setClimbing").invokeInstance(spider, new BooleanObjExec(climbing));
    }

    @Override
    public boolean hasEntityDolphinFish(@NotNull Dolphin dolphin) {
        throw new UnsupportedOperationException("The dolphin is only available at 1.13+");
    }

    @Override
    public void setEntityDolphinFish(@NotNull Dolphin dolphin, boolean fish) {
        throw new UnsupportedOperationException("The dolphin is only available at 1.13+");
    }

    @Override
    public @NotNull Fish.Type getEntityFishType(@NotNull Fish fish) {
        throw new UnsupportedOperationException("The fishes are only available at 1.13+");
    }

    @Override
    public int getEntityPufferFishPuff(@NotNull Pufferfish fish) {
        throw new UnsupportedOperationException("The puffer fish is only available at 1.13+");
    }

    @Override
    public void setEntityPufferFishPuff(@NotNull Pufferfish fish, int puff) {
        throw new UnsupportedOperationException("The puffer fish is only available at 1.13+");
    }

    @Override
    public int getEntityTropicalFishVariant(@NotNull Tropicalfish fish) {
        throw new UnsupportedOperationException("The tropical fish is only available at 1.13+");
    }

    @Override
    public void setEntityTropicalFishVariant(@NotNull Tropicalfish fish, int variant) {
        throw new UnsupportedOperationException("The tropical fish is only available at 1.13+");
    }

    @Override
    public int getEntityPhantomSize(@NotNull Phantom phantom) {
        throw new UnsupportedOperationException("The phantom is only available at 1.13+");
    }

    @Override
    public void setEntityPhantomSize(@NotNull Phantom phantom, int variant) {
        throw new UnsupportedOperationException("The phantom is only available at 1.13+");
    }

    @Override
    public boolean hasEntityTurtleEgg(@NotNull Turtle turtle) {
        throw new UnsupportedOperationException("The turtle is only available at 1.13+");
    }

    @Override
    public void setEntityTurtleEgg(@NotNull Turtle turtle, boolean egg) {
        throw new UnsupportedOperationException("The turtle is only available at 1.13+");
    }

    @Override
    public boolean isEntityBlazeCharging(@NotNull Blaze blaze) {
        //noinspection DataFlowIssue
        return (boolean) laivynpc().getVersion().getMethodExec("Entity:Blaze:isCharging").invokeInstance(blaze);
    }

    @Override
    public void setEntityBlazeCharging(@NotNull Blaze blaze, boolean charging) {
        laivynpc().getVersion().getMethodExec("Entity:Blaze:setCharging").invokeInstance(blaze, new BooleanObjExec(charging));
    }

    @Override
    public boolean isEntityPolarBearStanding(@NotNull PolarBear bear) {
        throw new UnsupportedOperationException("The polar bear is only available at 1.10+");
    }

    @Override
    public void setEntityPolarBearStanding(@NotNull PolarBear bear, boolean standing) {
        throw new UnsupportedOperationException("The polar bear is only available at 1.10+");
    }

    @Override
    public boolean hasEntityPigSaddle(@NotNull Pig pig) {
        //noinspection DataFlowIssue
        return (boolean) laivynpc().getVersion().getMethodExec("Entity:Pig:hasSaddle").invokeInstance(pig);
    }
    @Override
    public void setEntityPigSaddle(@NotNull Pig pig, boolean saddle) {
        laivynpc().getVersion().getMethodExec("Entity:Pig:setSaddle").invokeInstance(pig, new BooleanObjExec(saddle));
    }

    @Override
    public boolean isEntityWolfAngry(@NotNull Wolf wolf) {
        //noinspection DataFlowIssue
        return (boolean) laivynpc().getVersion().getMethodExec("Entity:Wolf:isAngry").invokeInstance(wolf);
    }

    @Override
    public void setEntityWolfAngry(@NotNull Wolf wolf, boolean angry) {
        laivynpc().getVersion().getMethodExec("Entity:Wolf:setAngry").invokeInstance(wolf, new BooleanObjExec(angry));
    }

    @Override
    public @NotNull EnumColorEnum.EnumColor getEntityWolfCollarColor(@NotNull Wolf wolf) {
        return new EnumColorEnum.EnumColor((Enum<?>) Objects.requireNonNull(laivynpc().getVersion().getMethodExec("Entity:Wolf:getCollarColor").invokeInstance(wolf)));
    }

    @Override
    public void setEntityWolfCollarColor(@NotNull Wolf wolf, EnumColorEnum.@NotNull EnumColor color) {
        laivynpc().getVersion().getMethodExec("Entity:Wolf:setCollarColor").invokeInstance(wolf, color);
    }

    @Override
    public @Nullable ItemStack getEntityItemFrameItem(@NotNull ItemFrame itemFrame) {
        Object item = laivynpc().getVersion().getMethodExec("Entity:ItemFrame:getItem").invokeInstance(itemFrame);
        if (item == null) {
            return null;
        }
        return new codes.laivy.npc.mappings.defaults.classes.others.objects.ItemStack(item).getCraftItemStack().getItemStack();
    }

    @Override
    public void setEntityItemFrameItem(@NotNull ItemFrame itemFrame, @Nullable ItemStack itemStack) {
        if (itemStack != null) {
            laivynpc().getVersion().getMethodExec("Entity:ItemFrame:setItem").invokeInstance(itemFrame, codes.laivy.npc.mappings.defaults.classes.others.objects.ItemStack.getNMSItemStack(itemStack));
        } else {
            laivynpc().getVersion().getMethodExec("Entity:ItemFrame:setItem").invokeInstance(itemFrame, new codes.laivy.npc.mappings.defaults.classes.others.objects.ItemStack(null));
        }
    }

    @Override
    public int getEntityItemFrameRotation(@NotNull ItemFrame itemFrame) {
        //noinspection DataFlowIssue
        return (int) laivynpc().getVersion().getMethodExec("Entity:ItemFrame:getRotation").invokeInstance(itemFrame);
    }

    @Override
    public void setEntityItemFrameRotation(@NotNull ItemFrame itemFrame, int rotation) {
        laivynpc().getVersion().getMethodExec("Entity:ItemFrame:setRotation").invokeInstance(itemFrame, new IntegerObjExec(rotation));
    }

    @Override
    public boolean isEntityTamed(@NotNull TameableEntityLiving tameableEntity) {
        return (boolean) Objects.requireNonNull(laivynpc().getVersion().getMethodExec("Entity:Tameable:isTamed").invokeInstance(tameableEntity));
    }

    @Override
    public void setEntityTamed(@NotNull TameableEntityLiving tameableEntity, boolean tamed) {
        laivynpc().getVersion().getMethodExec("Entity:Tameable:setTamed").invokeInstance(tameableEntity, new BooleanObjExec(tamed));
    }

    @Override
    public boolean isEntitySitting(@NotNull TameableEntityLiving tameableEntity) {
        return (boolean) Objects.requireNonNull(laivynpc().getVersion().getMethodExec("Entity:Tameable:isSitting").invokeInstance(tameableEntity));
    }

    @Override
    public void setEntitySitting(@NotNull TameableEntityLiving tameableEntity, boolean sitting) {
        laivynpc().getVersion().getMethodExec("Entity:Tameable:setSitting").invokeInstance(tameableEntity, new BooleanObjExec(sitting));
    }

    @Override
    public @NotNull ItemStack getEntityItemItemStack(@NotNull Item item) {
        return new codes.laivy.npc.mappings.defaults.classes.others.objects.ItemStack(Objects.requireNonNull(laivynpc().getVersion().getMethodExec("Entity:Item:getItemStack").invokeInstance(item))).getCraftItemStack().getItemStack();
    }

    @Override
    public void setEntityItemItemStack(@NotNull Item entityItem, @NotNull ItemStack itemStack) {
        laivynpc().getVersion().getMethodExec("Entity:Item:setItemStack").invokeInstance(entityItem, codes.laivy.npc.mappings.defaults.classes.others.objects.ItemStack.getNMSItemStack(itemStack));
    }

    @Override
    public boolean isEntityWitherSkullCharged(@NotNull WitherSkull skull) {
        //noinspection DataFlowIssue
        return (boolean) laivynpc().getVersion().getMethodExec("Entity:WitherSkull:isCharged").invokeInstance(skull);
    }

    @Override
    public void setEntityWitherSkullCharged(@NotNull WitherSkull skull, boolean charged) {
        laivynpc().getVersion().getMethodExec("Entity:WitherSkull:setCharged").invokeInstance(skull, new BooleanObjExec(charged));
    }

    @Override
    public boolean isEntityBatAsleep(@NotNull Bat bat) {
        //noinspection DataFlowIssue
        return (boolean) laivynpc().getVersion().getMethodExec("Entity:Bat:isAsleep").invokeInstance(bat);
    }

    @Override
    public void setEntityBatAsleep(@NotNull Bat bat, boolean asleep) {
        laivynpc().getVersion().getMethodExec("Entity:Bat:setAsleep").invokeInstance(bat, new BooleanObjExec(asleep));
    }

    @Override
    public @NotNull EntityPlayer createPlayer(@NotNull GameProfile profile, @NotNull Location location) {
        if (location.getWorld() == null) {
            throw new NullPointerException("This location's world is null!");
        }

        PlayerInteractManager manager = new PlayerInteractManager(getClassExec("PlayerInteractManager").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle()));

        EntityPlayer player = new EntityPlayer(getClassExec("EntityPlayer").getConstructor(
                getClassExec("MinecraftServer"),
                getClassExec("WorldServer"),
                getClassExec("GameProfile"),
                getClassExec("PlayerInteractManager")
        ).newInstance(
                CraftServer.getCraftServer(Bukkit.getServer()).getServer(),
                CraftWorld.getCraftWorld(location.getWorld()).getHandle(),
                profile,
                manager
        ));
        player.setLocation(location);
        return player;
    }
    @Override
    public @NotNull GameProfile createGameProfile(@NotNull UUID uuid, @NotNull String name) {
        return new GameProfile(getClassExec("GameProfile").getConstructor(ClassExecutor.UUID, ClassExecutor.STRING).newInstance(new UUIDObjExec(uuid), new StringObjExec(name)));
    }

    @Override
    public @NotNull String getPlayerName(@NotNull EntityPlayer player) {
        return (String) Objects.requireNonNull(laivynpc().getVersion().getMethodExec("Entity:Human:getName").invokeInstance(player));
    }

    @Override
    public @NotNull Block getNMSBlock(@NotNull CraftBlock block) {
        return new Block(getMethodExec("CraftBlock:getNMSBlock").invokeInstance(block));
    }

    @Override
    public @Nullable String getPlayerTablistName(@NotNull EntityPlayer player) {
        Object object = getFieldExec("EntityPlayer:listName").invokeInstance(player);

        if (object == null) {
            return null;
        }

        return new IChatBaseComponent(object).getString();
    }

    @Override
    public void setPlayerTablistName(@NotNull EntityPlayer player, @Nullable String string) {
        getFieldExec("EntityPlayer:listName").set(player, (string != null ? IChatBaseComponent.convert(string) : null));
    }

    @Override
    public boolean putInPropertyMap(@NotNull PropertyMap propertyMap, @NotNull String string, @NotNull Property property) {
        //noinspection unchecked,DataFlowIssue
        ((Multimap<String, Object>) getMethodExec("PropertyMap:delegate").invokeInstance(propertyMap)).put(string, property.getValue());
        return true;
    }

    @Override
    public @NotNull Scoreboard getScoreboardFrom(org.bukkit.scoreboard.@NotNull Scoreboard scoreboard) {
        return new CraftScoreboard(getClassExec("CraftScoreboard").cast(scoreboard)).getHandle();
    }

    @Override
    public @NotNull ScoreboardTeam createScoreboardTeam(@NotNull Scoreboard scoreboard, @NotNull String name) {
        return new ScoreboardTeam(Objects.requireNonNull(getMethodExec("Scoreboard:createTeam").invokeInstance(scoreboard, new StringObjExec(name))));
    }

    @Override
    public @NotNull Vector3f getArmorStandPose(ArmorStand.@NotNull Pose pose, @NotNull ArmorStand stand) {
        Vector3f vector3f;

        if (pose == ArmorStand.Pose.HEAD) {
            vector3f = new Vector3f(getFieldExec("Entity:ArmorStand:headPose").invokeInstance(stand));
        } else if (pose == ArmorStand.Pose.BODY) {
            vector3f = new Vector3f(getFieldExec("Entity:ArmorStand:bodyPose").invokeInstance(stand));
        } else if (pose == ArmorStand.Pose.LEFT_ARM) {
            vector3f = new Vector3f(getFieldExec("Entity:ArmorStand:leftArmPose").invokeInstance(stand));
        } else if (pose == ArmorStand.Pose.RIGHT_ARM) {
            vector3f = new Vector3f(getFieldExec("Entity:ArmorStand:rightArmPose").invokeInstance(stand));
        } else if (pose == ArmorStand.Pose.LEFT_LEG) {
            vector3f = new Vector3f(getFieldExec("Entity:ArmorStand:leftLegPose").invokeInstance(stand));
        } else if (pose == ArmorStand.Pose.RIGHT_LEG) {
            vector3f = new Vector3f(getFieldExec("Entity:ArmorStand:rightLegPose").invokeInstance(stand));
        } else {
            throw new UnsupportedOperationException("Couldn't get this pose type '" + pose + "'");
        }

        return Objects.requireNonNull(vector3f);
    }

    @Override
    public void setArmorStandPose(ArmorStand.@NotNull Pose pose, @NotNull ArmorStand stand, @NotNull Vector3f vector3f) {
        if (pose == ArmorStand.Pose.HEAD) {
            getFieldExec("Entity:ArmorStand:headPose").set(stand, vector3f);
        } else if (pose == ArmorStand.Pose.BODY) {
            getFieldExec("Entity:ArmorStand:bodyPose").set(stand, vector3f);
        } else if (pose == ArmorStand.Pose.LEFT_ARM) {
            getFieldExec("Entity:ArmorStand:leftArmPose").set(stand, vector3f);
        } else if (pose == ArmorStand.Pose.RIGHT_ARM) {
            getFieldExec("Entity:ArmorStand:rightArmPose").set(stand, vector3f);
        } else if (pose == ArmorStand.Pose.LEFT_LEG) {
            getFieldExec("Entity:ArmorStand:leftLegPose").set(stand, vector3f);
        } else if (pose == ArmorStand.Pose.RIGHT_LEG) {
            getFieldExec("Entity:ArmorStand:rightLegPose").set(stand, vector3f);
        } else {
            throw new UnsupportedOperationException("Couldn't set this pose type '" + pose + "'");
        }
    }

    @Override
    public @NotNull Vector3f createVector3f(float x, float y, float z) {
        return new Vector3f(getClassExec("Vector3f").getConstructor(ClassExecutor.FLOAT, ClassExecutor.FLOAT, ClassExecutor.FLOAT).newInstance(new FloatObjExec(x), new FloatObjExec(y), new FloatObjExec(z)));
    }

    @Override
    public void sendPacket(@NotNull IPacket packet, @NotNull PlayerConnection... connections) {
        for (PlayerConnection connection : connections) {
            getMethodExec("PlayerConnection:sendPacket").invokeInstance(connection, new ObjectObjExec(packet.getPacket()));
        }
    }

    @Override
    public @NotNull IPlayerInfoAction getPlayerInfoAction(@NotNull Object object) {
        return new PlayerInfoActionLegacy((Enum<?>) object);
    }

    @Override
    public @NotNull EntitySpawnPacket createSpawnPacket(@NotNull Entity entity) {
        Object packet = getClassExec("PacketPlayOutSpawnEntity").getConstructor(getClassExec("Entity"), ClassExecutor.INT).newInstance(entity, new IntegerObjExec(NonLivingEntityType.getByEntity(entity).getTypeId()));
        return new EntitySpawnPacket(packet);
    }

    @Override
    public @NotNull Set<EntityDestroyPacket> createDestroyPacket(@NotNull Entity... entities) {
        int[] ids = new int[entities.length];
        int row = 0;
        for (Entity entity : entities) {
            ids[row] = entity.getId();
            row++;
        }

        Object packet = getClassExec("PacketPlayOutEntityDestroy").getConstructor(ClassExecutor.INT_ARRAY).newInstance(new IntegerArrayObjExec(ids));

        return new HashSet<EntityDestroyPacket>() {{
            add(new EntityDestroyPacket(packet));
        }};
    }

    @Override
    public @NotNull EntityMetadataPacket createMetadataPacket(@NotNull Entity entity, @NotNull DataWatcher dataWatcher, boolean b) {
        Object packet = getClassExec("PacketPlayOutEntityMetadata").getConstructor(ClassExecutor.INT, getClassExec("DataWatcher"), ClassExecutor.BOOLEAN).newInstance(new IntegerObjExec(entity.getId()), dataWatcher, new BooleanObjExec(b));
        return new EntityMetadataPacket(packet);
    }

    @Override
    public @NotNull EntityLivingSpawnPacket createSpawnLivingPacket(@NotNull EntityLiving entity) {
        Object packet = getClassExec("PacketPlayOutSpawnEntityLiving").getConstructor(getClassExec("EntityLiving")).newInstance(entity);
        return new EntityLivingSpawnPacket(packet);
    }

    @Override
    public @NotNull EntityNamedSpawnPacket createSpawnNamedPacket(@NotNull EntityHuman entity) {
        Object packet = getClassExec("PacketPlayOutNamedEntitySpawn").getConstructor(getClassExec("Entity:Human")).newInstance(entity);
        return new EntityNamedSpawnPacket(packet);
    }

    @Override
    public @NotNull IPlayerInfoPacket createPlayerInfoPacket(@NotNull IPlayerInfoAction action, @NotNull EntityPlayer player) {
        try {
            Object packet = getClassExec("PacketPlayOutPlayerInfo").getConstructor(getEnumExec("PacketPlayOutPlayerInfo:EnumPlayerInfoAction"), getClassExec("EntityPlayer", true)).getConstructor().newInstance(action.getValue(), player.getValueAsArray());
            return new PlayerInfoPacket(packet);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("PacketPlayOutPlayerInfo creation", e);
        }
    }

    @Override
    public @NotNull ScoreboardTeamPacket createScoreboardTeamPacket(@NotNull ScoreboardTeam team, boolean b) {
        Object packet = getClassExec("PacketPlayOutScoreboardTeam").getConstructor(getClassExec("ScoreboardTeam"), ClassExecutor.INT).newInstance(team, new IntegerObjExec((b ? 0 : 2)));
        return new ScoreboardTeamPacket(packet);
    }

    @Override
    public @NotNull Set<EntityEquipmentPacket> createEquipmentPacket(@NotNull Entity entity, @NotNull Map<EnumItemSlotEnum.@NotNull EnumItemSlot, @NotNull ItemStack> items) {
        Set<EntityEquipmentPacket> packets = new LinkedHashSet<>();

        for (Map.Entry<EnumItemSlotEnum.@NotNull EnumItemSlot, @NotNull ItemStack> entry : items.entrySet()) {
            EnumItemSlotEnum.EnumItemSlot slot = entry.getKey();
            ItemStack item = entry.getValue();

            if (slot == EnumItemSlotEnum.EnumItemSlot.OFFHAND) {
                throw new IllegalArgumentException("The offhand equipment slot is available only at 1.9+");
            }

            Object packet = getClassExec("PacketPlayOutEntityEquipment").getConstructor(ClassExecutor.INT, ClassExecutor.INT, getClassExec("ItemStack")).newInstance(new IntegerObjExec(entity.getId()), new IntegerObjExec(slot.getId()), getNMSItemStack(item));
            packets.add(new EntityEquipmentPacket(packet));
        }

        return packets;
    }

    @Override
    public @NotNull EntityTeleportPacket createTeleportPacket(@NotNull Entity entity) {
        Object packet = getClassExec("PacketPlayOutEntityTeleport").getConstructor(getClassExec("Entity")).newInstance(entity);
        return new EntityTeleportPacket(packet);
    }

    @Override
    public @NotNull EntityHeadRotationPacket createHeadRotationPacket(@NotNull Entity entity, int yaw) {
        Object packet = getClassExec("PacketPlayOutEntityHeadRotation").getConstructor(getClassExec("Entity"), ClassExecutor.BYTE).newInstance(entity, new ByteObjExec((byte) (yaw * 256 / 360)));
        return new EntityHeadRotationPacket(packet);
    }

    @Override
    public @NotNull EntityLookPacket createLookPacket(@NotNull Entity entity, double yaw, double pitch) {
        Object packet = getClassExec("PacketPlayOutEntityLook").getConstructor(ClassExecutor.INT, ClassExecutor.BYTE, ClassExecutor.BYTE, ClassExecutor.BOOLEAN).newInstance(new IntegerObjExec(entity.getId()), new ByteObjExec((byte) (yaw * 256 / 360)), new ByteObjExec((byte) (pitch * 256 / 360)), new BooleanObjExec(true));
        return new EntityLookPacket(packet);
    }

    @Override
    public @NotNull EntityUseInPacket.ActionEnum.Action getEntityUseInPacketAction(@NotNull EntityUseInPacket packet) {
        return new EntityUseInPacket.ActionEnum.Action((Enum<?>) Objects.requireNonNull(getFieldExec("PacketPlayInUseEntity:EnumEntityUseAction:action").invokeInstance(packet)));
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public boolean addToTeam(@NotNull Scoreboard scoreboard, @NotNull ScoreboardTeam team, @NotNull Entity entity) {
        if (entity instanceof EntityPlayer) {
            return (boolean) getMethodExec("Scoreboard:addToTeam").invokeInstance(scoreboard, new StringObjExec(team.getName()), new StringObjExec(((EntityPlayer) entity).getName()));
        } else {
            return (boolean) getMethodExec("Scoreboard:addToTeam").invokeInstance(scoreboard, new StringObjExec(team.getName()), new StringObjExec(entity.getId() + ""));
        }
    }

    @Override
    public void setPrefix(@NotNull ScoreboardTeam team, @NotNull String prefix) {
        laivynpc().getVersion().getMethodExec("ScoreboardTeam:setPrefix").invokeInstance(team, new StringObjExec(prefix));
    }

    @Override
    public @NotNull IChatBaseComponent stringToBaseComponent(@NotNull String string) {
        return new IChatBaseComponent(getMethodExec("ChatSerializer:convertToComponent").invokeStatic(new StringObjExec("{\"text\":\"" + string + "\"}")));
    }

    @Override
    public @NotNull String baseComponentToString(@NotNull IChatBaseComponent iChatBaseComponent) {
        return (String) Objects.requireNonNull(getMethodExec("ChatSerializer:convertToString").invokeInstance(iChatBaseComponent));
    }

    @Override
    public @NotNull Map<@NotNull Integer, @NotNull VersionedDataWatcherObject> dataWatcherGetValues(@NotNull DataWatcher dataWatcher) {
        //noinspection unchecked
        Map<Integer, ?> fMap = (Map<Integer, ?>) getFieldExec("DataWatcher:map").invokeInstance(dataWatcher);
        Map<Integer, VersionedDataWatcherObject> map = new HashMap<>();
        assert fMap != null;

        for (Map.Entry<Integer, ?> e : fMap.entrySet()) {
            map.put(e.getKey(), new WatchableObject(e.getValue()));
        }

        return map;
    }

    @Override
    public @NotNull IBlockData getBlockData(@NotNull Material material) {
        return new Block(laivynpc().getVersion().getMethodExec("CraftMagicNumbers:getBlock").invokeStatic(new ObjectExecutor(material) {
            @Override
            public @NotNull ClassExecutor getClassExecutor() {
                return new ClassExecutor(Material.class);
            }
        })).getData();
    }

    @Override
    public void loadClasses() {
        // NBT
        load(V1_8_R1.class, "NBTBase", new NBTBase.NBTBaseClass("net.minecraft.server.v1_8_R1.NBTBase"));

        load(V1_8_R1.class, "NBTBase:NBTTagByte", new NBTTagByte.NBTTagByteClass("net.minecraft.server.v1_8_R1.NBTTagByte"));
        load(V1_8_R1.class, "NBTBase:NBTTagByteArray", new NBTTagByteArray.NBTTagByteArrayClass("net.minecraft.server.v1_8_R1.NBTTagByteArray"));
        load(V1_8_R1.class, "NBTBase:NBTTagCompound", new NBTTagCompound.NBTTagCompoundClass("net.minecraft.server.v1_8_R1.NBTTagCompound"));
        load(V1_8_R1.class, "NBTBase:NBTTagDouble", new NBTTagDouble.NBTTagDoubleClass("net.minecraft.server.v1_8_R1.NBTTagDouble"));
        load(V1_8_R1.class, "NBTBase:NBTTagFloat", new NBTTagFloat.NBTTagFloatClass("net.minecraft.server.v1_8_R1.NBTTagFloat"));
        load(V1_8_R1.class, "NBTBase:NBTTagInt", new NBTTagInt.NBTTagIntClass("net.minecraft.server.v1_8_R1.NBTTagInt"));
        load(V1_8_R1.class, "NBTBase:NBTTagIntArray", new NBTTagIntArray.NBTTagIntArrayClass("net.minecraft.server.v1_8_R1.NBTTagIntArray"));
        load(V1_8_R1.class, "NBTBase:NBTTagList", new NBTTagList.NBTTagListClass("net.minecraft.server.v1_8_R1.NBTTagList"));
        load(V1_8_R1.class, "NBTBase:NBTTagLong", new NBTTagLong.NBTTagLongClass("net.minecraft.server.v1_8_R1.NBTTagLong"));
        load(V1_8_R1.class, "NBTBase:NBTTagShort", new NBTTagShort.NBTTagShortClass("net.minecraft.server.v1_8_R1.NBTTagShort"));
        load(V1_8_R1.class, "NBTBase:NBTTagString", new NBTTagString.NBTTagStringClass("net.minecraft.server.v1_8_R1.NBTTagString"));
        //

        // Packets
        load(V1_8_R1.class, "Packet", new Packet.PacketClass("net.minecraft.server.v1_8_R1.Packet"));
        load(V1_8_R1.class, "PacketPlayOutSpawnEntity", new EntitySpawnPacket.EntitySpawnPacketClass("net.minecraft.server.v1_8_R1.PacketPlayOutSpawnEntity"));
        load(V1_8_R1.class, "PacketPlayOutEntityDestroy", new EntityDestroyPacket.EntityDestroyPacketClass("net.minecraft.server.v1_8_R1.PacketPlayOutEntityDestroy"));
        load(V1_8_R1.class, "PacketPlayOutAnimation", new EntityAnimationPacket.EntityAnimationPacketClass("net.minecraft.server.v1_8_R1.PacketPlayOutAnimation"));
        load(V1_8_R1.class, "PacketPlayOutSpawnEntityLiving", new EntityLivingSpawnPacket.EntityLivingSpawnPacketClass("net.minecraft.server.v1_8_R1.PacketPlayOutSpawnEntityLiving"));
        load(V1_8_R1.class, "PacketPlayOutEntityMetadata", new EntityMetadataPacket.EntityMetadataPacketClass("net.minecraft.server.v1_8_R1.PacketPlayOutEntityMetadata"));
        load(V1_8_R1.class, "PacketPlayOutNamedEntitySpawn", new EntityNamedSpawnPacket.EntityNamedSpawnPacketClass("net.minecraft.server.v1_8_R1.PacketPlayOutNamedEntitySpawn"));
        load(V1_8_R1.class, "PacketPlayOutPlayerInfo", new PlayerInfoPacket.PlayerInfoPacketClass("net.minecraft.server.v1_8_R1.PacketPlayOutPlayerInfo"));
        load(V1_8_R1.class, "PacketPlayOutPlayerInfo:EnumPlayerInfoAction", new EnumPlayerInfoActionEnum.EnumPlayerInfoActionClass("net.minecraft.server.v1_8_R1.EnumPlayerInfoAction"));
        load(V1_8_R1.class, "PacketPlayOutScoreboardTeam", new ScoreboardTeamPacket.ScoreboardTeamPacketClass("net.minecraft.server.v1_8_R1.PacketPlayOutScoreboardTeam"));
        load(V1_8_R1.class, "PacketPlayOutEntityEquipment", new EntityEquipmentPacket.EntityEquipmentPacketClass("net.minecraft.server.v1_8_R1.PacketPlayOutEntityEquipment"));
        load(V1_8_R1.class, "PacketPlayOutEntityTeleport", new EntityTeleportPacket.EntityTeleportPacketClass("net.minecraft.server.v1_8_R1.PacketPlayOutEntityTeleport"));
        load(V1_8_R1.class, "PacketPlayOutEntityHeadRotation", new EntityHeadRotationPacket.EntityHeadRotationPacketClass("net.minecraft.server.v1_8_R1.PacketPlayOutEntityHeadRotation"));
        load(V1_8_R1.class, "PacketPlayOutEntityLook", new EntityLookPacket.EntityLookPacketClass("net.minecraft.server.v1_8_R1.PacketPlayOutEntityLook"));

        load(V1_8_R1.class, "PacketPlayInUseEntity", new EntityUseInPacket.EntityUseInPacketClass("net.minecraft.server.v1_8_R1.PacketPlayInUseEntity"));
        load(V1_8_R1.class, "PacketPlayInUseEntity:EnumEntityUseAction", new EntityUseInPacket.ActionEnum.ActionClass("net.minecraft.server.v1_8_R1.EnumEntityUseAction"));
        //

        // Server
        load(V1_8_R1.class, "MinecraftServer", new MinecraftServer.MinecraftServerClass("net.minecraft.server.v1_8_R1.MinecraftServer"));
        load(V1_8_R1.class, "WorldServer", new WorldServer.WorldServerClass("net.minecraft.server.v1_8_R1.WorldServer"));
        load(V1_8_R1.class, "CraftServer", new CraftServer.CraftServerClass("org.bukkit.craftbukkit.v1_8_R1.CraftServer"));
        //

        // Entity
        load(V1_8_R1.class, "Entity", new Entity.EntityClass("net.minecraft.server.v1_8_R1.Entity"));
        load(V1_8_R1.class, "EntityLiving", new EntityLiving.EntityLivingClass("net.minecraft.server.v1_8_R1.EntityLiving"));
        load(V1_8_R1.class, "Entity:Human", new Entity.EntityClass("net.minecraft.server.v1_8_R1.EntityHuman"));
        load(V1_8_R1.class, "CraftPlayer", new CraftPlayer.CraftPlayerClass("org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer"));
        load(V1_8_R1.class, "EntityPlayer", new EntityPlayer.EntityPlayerClass("net.minecraft.server.v1_8_R1.EntityPlayer"));

        load(V1_8_R1.class, "Entity:ArmorStand", new ArmorStand.ArmorStandClass("net.minecraft.server.v1_8_R1.EntityArmorStand"));
        load(V1_8_R1.class, "Entity:Pig", new Pig.PigClass("net.minecraft.server.v1_8_R1.EntityPig"));
        load(V1_8_R1.class, "Entity:Cow", new Cow.CowClass("net.minecraft.server.v1_8_R1.EntityCow"));
        load(V1_8_R1.class, "Entity:Ocelot", new Ocelot.OcelotClass("net.minecraft.server.v1_8_R1.EntityOcelot"));
        load(V1_8_R1.class, "Entity:Bat", new Bat.BatClass("net.minecraft.server.v1_8_R1.EntityBat"));
        load(V1_8_R1.class, "Entity:Egg", new Egg.EggClass("net.minecraft.server.v1_8_R1.EntityEgg"));
        load(V1_8_R1.class, "Entity:Chicken", new Chicken.ChickenClass("net.minecraft.server.v1_8_R1.EntityChicken"));
        load(V1_8_R1.class, "Entity:Horse", new AbstractHorse.AbstractHorseClass("net.minecraft.server.v1_8_R1.EntityHorse"));
        load(V1_8_R1.class, "Entity:IronGolem", new IronGolem.IronGolemClass("net.minecraft.server.v1_8_R1.EntityIronGolem"));
        load(V1_8_R1.class, "Entity:Rabbit", new Rabbit.RabbitClass("net.minecraft.server.v1_8_R1.EntityRabbit"));
        load(V1_8_R1.class, "Entity:Sheep", new Sheep.SheepClass("net.minecraft.server.v1_8_R1.EntitySheep"));
        load(V1_8_R1.class, "Entity:Snowman", new Snowman.SnowmanClass("net.minecraft.server.v1_8_R1.EntitySnowman"));
        load(V1_8_R1.class, "Entity:Squid", new Squid.SquidClass("net.minecraft.server.v1_8_R1.EntitySquid"));
        load(V1_8_R1.class, "Entity:Wolf", new Wolf.WolfClass("net.minecraft.server.v1_8_R1.EntityWolf"));
        load(V1_8_R1.class, "Entity:ItemFrame", new ItemFrame.ItemFrameClass("net.minecraft.server.v1_8_R1.EntityItemFrame"));
        load(V1_8_R1.class, "Entity:LeashKnot", new LeashKnot.LeashKnotClass("net.minecraft.server.v1_8_R1.EntityLeash"));
        load(V1_8_R1.class, "Entity:FallingBlock", new FallingBlock.FallingBlockClass("net.minecraft.server.v1_8_R1.EntityFallingBlock"));
        load(V1_8_R1.class, "Entity:Item", new Item.ItemClass("net.minecraft.server.v1_8_R1.EntityItem"));
        load(V1_8_R1.class, "Entity:EnderDragon", new EnderDragon.EnderDragonClass("net.minecraft.server.v1_8_R1.EntityEnderDragon"));
        load(V1_8_R1.class, "Entity:EnderSignal", new EnderSignal.EnderSignalClass("net.minecraft.server.v1_8_R1.EntityEnderSignal"));
        load(V1_8_R1.class, "Entity:Wither", new Wither.WitherClass("net.minecraft.server.v1_8_R1.EntityWither"));
        load(V1_8_R1.class, "Entity:WitherSkull", new WitherSkull.WitherSkullClass("net.minecraft.server.v1_8_R1.EntityWitherSkull"));
        load(V1_8_R1.class, "Entity:Blaze", new Blaze.BlazeClass("net.minecraft.server.v1_8_R1.EntityBlaze"));
        load(V1_8_R1.class, "Entity:Creeper", new Creeper.CreeperClass("net.minecraft.server.v1_8_R1.EntityCreeper"));
        load(V1_8_R1.class, "Entity:Enderman", new Enderman.EndermanClass("net.minecraft.server.v1_8_R1.EntityEnderman"));
        load(V1_8_R1.class, "Entity:Ghast", new Ghast.GhastClass("net.minecraft.server.v1_8_R1.EntityGhast"));
        load(V1_8_R1.class, "Entity:Guardian", new Guardian.GuardianClass("net.minecraft.server.v1_8_R1.EntityGuardian"));
        load(V1_8_R1.class, "Entity:Silverfish", new Silverfish.SilverfishClass("net.minecraft.server.v1_8_R1.EntitySilverfish"));
        load(V1_8_R1.class, "Entity:Skeleton", new Skeleton.SkeletonClass("net.minecraft.server.v1_8_R1.EntitySkeleton"));
        load(V1_8_R1.class, "Entity:Slime", new Slime.SlimeClass("net.minecraft.server.v1_8_R1.EntitySlime"));
        load(V1_8_R1.class, "Entity:Spider", new Spider.SpiderClass("net.minecraft.server.v1_8_R1.EntitySpider"));
        load(V1_8_R1.class, "Entity:Witch", new Witch.WitchClass("net.minecraft.server.v1_8_R1.EntityWitch"));
        load(V1_8_R1.class, "Entity:Zombie", new Zombie.ZombieClass("net.minecraft.server.v1_8_R1.EntityZombie"));
        load(V1_8_R1.class, "Entity:Villager", new Villager.VillagerClass("net.minecraft.server.v1_8_R1.EntityVillager"));
        load(V1_8_R1.class, "Entity:Boat", new Boat.BoatClass("net.minecraft.server.v1_8_R1.EntityBoat"));
        load(V1_8_R1.class, "Entity:CaveSpider", new CaveSpider.CaveSpiderClass("net.minecraft.server.v1_8_R1.EntityCaveSpider"));

        load(V1_8_R1.class, "Entity:Ageable", new AgeableEntityLiving.AgeableEntityLivingClass("net.minecraft.server.v1_8_R1.EntityAgeable"));
        load(V1_8_R1.class, "Entity:Tameable", new TameableEntityLiving.TameableEntityLivingClass("net.minecraft.server.v1_8_R1.EntityTameableAnimal"));
        // EntityPlayer
        load(V1_8_R1.class, "GameProfile", new GameProfile.GameProfileClass("com.mojang.authlib.GameProfile"));
        load(V1_8_R1.class, "PropertyMap", new PropertyMap.PropertyMapClass("com.mojang.authlib.properties.PropertyMap"));
        load(V1_8_R1.class, "Property", new Property.PropertyClass("com.mojang.authlib.properties.Property"));
        //

        // Managers
        load(V1_8_R1.class, "PlayerInteractManager", new PlayerInteractManager.PlayerInteractManagerClass("net.minecraft.server.v1_8_R1.PlayerInteractManager"));
        //

        // DataWatcher
        load(V1_8_R1.class, "DataWatcher", new DataWatcher.DataWatcherClass("net.minecraft.server.v1_8_R1.DataWatcher"));
        load(V1_8_R1.class, "WatchableObject", new WatchableObject.WatchableObjectClass("net.minecraft.server.v1_8_R1.WatchableObject"));
        //

        // Scoreboard
        load(V1_8_R1.class, "CraftScoreboard", new CraftScoreboard.CraftScoreboardClass("org.bukkit.craftbukkit.v1_8_R1.scoreboard.CraftScoreboard"));
        load(V1_8_R1.class, "Scoreboard", new Scoreboard.ScoreboardClass("net.minecraft.server.v1_8_R1.Scoreboard"));
        load(V1_8_R1.class, "ScoreboardTeam", new ScoreboardTeam.ScoreboardTeamClass("net.minecraft.server.v1_8_R1.ScoreboardTeam"));

        load(V1_8_R1.class, "ScoreboardTeamBase:EnumNameTagVisibility", new EnumNameTagVisibilityEnum.EnumNameTagVisibilityClass("net.minecraft.server.v1_8_R1.EnumNameTagVisibility"));
        //

        // Others
        load(V1_8_R1.class, "PlayerConnection", new PlayerConnection.PlayerConnectionClass("net.minecraft.server.v1_8_R1.PlayerConnection"));
        load(V1_8_R1.class, "NetworkManager", new NetworkManager.NetworkManagerClass("net.minecraft.server.v1_8_R1.NetworkManager"));

        load(V1_8_R1.class, "EnumChatFormat", new EnumChatFormatEnum.EnumChatFormatClass("net.minecraft.server.v1_8_R1.EnumChatFormat"));
        load(V1_8_R1.class, "EnumColor", new EnumColorEnum.EnumColorClass("net.minecraft.server.v1_8_R1.EnumColor"));
        //

        // Chat
        load(V1_8_R1.class, "IChatBaseComponent", new IChatBaseComponent.IChatBaseComponentClass("net.minecraft.server.v1_8_R1.IChatBaseComponent"));
        load(V1_8_R1.class, "ChatSerializer", new IChatBaseComponent.ChatSerializerClass("net.minecraft.server.v1_8_R1.ChatSerializer"));
        //

        // Objects
        load(V1_8_R1.class, "CraftWorld", new CraftWorld.CraftWorldClass("org.bukkit.craftbukkit.v1_8_R1.CraftWorld"));
        load(V1_8_R1.class, "World", new World.WorldClass("net.minecraft.server.v1_8_R1.World"));
        load(V1_8_R1.class, "Vector3f", new Vector3f.Vector3fClass("net.minecraft.server.v1_8_R1.Vector3f"));
        load(V1_8_R1.class, "Vec3D", new Vec3D.Vec3DClass("net.minecraft.server.v1_8_R1.Vec3D"));
        load(V1_8_R1.class, "BlockPosition", new BlockPosition.BlockPositionClass("net.minecraft.server.v1_8_R1.BlockPosition"));
        load(V1_8_R1.class, "CraftBlock", new CraftBlock.CraftBlockClass("org.bukkit.craftbukkit.v1_8_R1.block.CraftBlock"));
        load(V1_8_R1.class, "IBlockData", new IBlockData.IBlockDataClass("net.minecraft.server.v1_8_R1.IBlockData"));
        load(V1_8_R1.class, "Block", new codes.laivy.npc.mappings.defaults.classes.others.objects.Block.BlockClass("net.minecraft.server.v1_8_R1.Block"));
        load(V1_8_R1.class, "CraftMagicNumbers", new ClassExecutor("org.bukkit.craftbukkit.v1_8_R1.util.CraftMagicNumbers"));
        //

        // Items
        load(V1_8_R1.class, "ItemStack", new ItemStackClass("net.minecraft.server.v1_8_R1.ItemStack"));
        load(V1_8_R1.class, "CraftItemStack", new CraftItemStack.CraftItemStackClass("org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack"));
        //
    }

    @Override
    public void loadMethods() {
        // NBT
        load(V1_8_R1.class, "NBTTagCompound:set", new MethodExecutor(getClassExec("NBTBase:NBTTagCompound"), ClassExecutor.VOID, "set", "Sets a value inside a NBTTagCompound", ClassExecutor.STRING, getClassExec("NBTBase")));
        load(V1_8_R1.class, "NBTTagCompound:get", new MethodExecutor(getClassExec("NBTBase:NBTTagCompound"), getClassExec("NBTBase"), "get", "Gets a value inside a NBTTagCompound", ClassExecutor.STRING));
        load(V1_8_R1.class, "NBTTagCompound:remove", new MethodExecutor(getClassExec("NBTBase:NBTTagCompound"), ClassExecutor.VOID, "remove", "Removes a value from a NBTTagCompound", ClassExecutor.STRING));
        load(V1_8_R1.class, "NBTTagCompound:contains", new MethodExecutor(getClassExec("NBTBase:NBTTagCompound"), ClassExecutor.BOOLEAN, "hasKey", "Check if a NBTTagCompound contains a key", ClassExecutor.STRING));
        load(V1_8_R1.class, "NBTTagCompound:isEmpty", new MethodExecutor(getClassExec("NBTBase:NBTTagCompound"), ClassExecutor.BOOLEAN, "isEmpty", "Check if a NBTTagCompound is empty"));
        load(V1_8_R1.class, "NBTTagCompound:keySet", new MethodExecutor(getClassExec("NBTBase:NBTTagCompound"), new ClassExecutor(Set.class) {}, "c", "Gets a NBTTagCompound's keys"));
        //

        // Entity
        load(V1_8_R1.class, "Entity:CraftPlayer:getHandle", new MethodExecutor(getClassExec("CraftPlayer"), getClassExec("EntityPlayer"), "getHandle", "Gets the NMS EntityPlayer from a CraftPlayer"));
        load(V1_8_R1.class, "Entity:Entity:getId", new MethodExecutor(getClassExec("Entity"), ClassExecutor.INT, "getId", "Gets the entity id of a Entity"));
        load(V1_8_R1.class, "Entity:Entity:getDataWatcher", new MethodExecutor(getClassExec("Entity"), getClassExec("DataWatcher"), "getDataWatcher", "Gets the DataWatcher of a Entity"));
        load(V1_8_R1.class, "Entity:Entity:setLocation", new MethodExecutor(getClassExec("Entity"), ClassExecutor.VOID, "setLocation", "Sets the Entity's location", ClassExecutor.DOUBLE, ClassExecutor.DOUBLE, ClassExecutor.DOUBLE, ClassExecutor.FLOAT, ClassExecutor.FLOAT));
        load(V1_8_R1.class, "Entity:Human:getName", new MethodExecutor(getClassExec("Entity:Human"), ClassExecutor.STRING, "getName", "Gets the Entity's name"));
        load(V1_8_R1.class, "Entity:Entity:getCustomName", new MethodExecutor(getClassExec("Entity"), ClassExecutor.STRING, "getCustomName", "Gets the custom name of a Entity"));
        load(V1_8_R1.class, "Entity:Entity:setCustomName", new MethodExecutor(getClassExec("Entity"), ClassExecutor.VOID, "setCustomName", "Sets the custom name of a Entity", ClassExecutor.STRING));
        load(V1_8_R1.class, "Entity:Entity:isCustomNameVisible", new MethodExecutor(getClassExec("Entity"), ClassExecutor.BOOLEAN, "getCustomNameVisible", "Check if the Entity's custom name is visible"));
        load(V1_8_R1.class, "Entity:Entity:setCustomNameVisible", new MethodExecutor(getClassExec("Entity"), ClassExecutor.VOID, "setCustomNameVisible", "Sets the Entity's custom name visibility", ClassExecutor.BOOLEAN));
        load(V1_8_R1.class, "Entity:Entity:isInvisible", new MethodExecutor(getClassExec("Entity"), ClassExecutor.BOOLEAN, "isInvisible", "Checks if the entity is invisible"));
        load(V1_8_R1.class, "Entity:Entity:setInvisible", new MethodExecutor(getClassExec("Entity"), ClassExecutor.VOID, "setInvisible", "Sets the Entity's visibility mode", ClassExecutor.BOOLEAN));
        load(V1_8_R1.class, "Entity:EntityPlayer:getProfile", new MethodExecutor(getClassExec("EntityPlayer"), getClassExec("GameProfile"), "getProfile", "Gets the EntityPlayer's GameProfile"));
        // ArmorStand
        load(V1_8_R1.class, "Entity:ArmorStand:setBasePlate", new MethodExecutor(getClassExec("Entity:ArmorStand"), ClassExecutor.VOID, "setBasePlate", "Sets the base plate of a ArmorStand", ClassExecutor.BOOLEAN));
        load(V1_8_R1.class, "Entity:ArmorStand:hasBasePlate", new MethodExecutor(getClassExec("Entity:ArmorStand"), ClassExecutor.BOOLEAN, "hasBasePlate", "Checks if ArmorStand has base plate"));
        load(V1_8_R1.class, "Entity:ArmorStand:setArms", new MethodExecutor(getClassExec("Entity:ArmorStand"), ClassExecutor.VOID, "setArms", "Sets the arms of a ArmorStand", ClassExecutor.BOOLEAN));
        load(V1_8_R1.class, "Entity:ArmorStand:hasArms", new MethodExecutor(getClassExec("Entity:ArmorStand"), ClassExecutor.BOOLEAN, "hasArms", "Checks if ArmorStand has arms"));
        load(V1_8_R1.class, "Entity:ArmorStand:setSmall", new MethodExecutor(getClassExec("Entity:ArmorStand"), ClassExecutor.VOID, "setSmall", "Sets the small state of a ArmorStand", ClassExecutor.BOOLEAN));
        load(V1_8_R1.class, "Entity:ArmorStand:isSmall", new MethodExecutor(getClassExec("Entity:ArmorStand"), ClassExecutor.BOOLEAN, "isSmall", "Checks if ArmorStand is small"));
        // Ageable
        load(V1_8_R1.class, "Entity:Ageable:isBaby", new MethodExecutor(getClassExec("Entity:Ageable"), ClassExecutor.BOOLEAN, "isBaby", "Checks if a entity is a baby"));
        load(V1_8_R1.class, "Entity:Ageable:setAge", new MethodExecutor(getClassExec("Entity:Ageable"), ClassExecutor.VOID, "setAge", "Sets the baby state of a entity", ClassExecutor.INT));
        // Tameable
        load(V1_8_R1.class, "Entity:Tameable:isTamed", new MethodExecutor(getClassExec("Entity:Tameable"), ClassExecutor.BOOLEAN, "isTamed", "Checks if a animal is tamed"));
        load(V1_8_R1.class, "Entity:Tameable:setTamed", new MethodExecutor(getClassExec("Entity:Tameable"), ClassExecutor.VOID, "setTamed", "Sets the tamed state of an animal", ClassExecutor.BOOLEAN));
        load(V1_8_R1.class, "Entity:Tameable:isSitting", new MethodExecutor(getClassExec("Entity:Tameable"), ClassExecutor.BOOLEAN, "isSitting", "Checks if a animal is sitting"));
        load(V1_8_R1.class, "Entity:Tameable:setSitting", new MethodExecutor(getClassExec("Entity:Tameable"), ClassExecutor.VOID, "setSitting", "Sets the sitting state of an animal", ClassExecutor.BOOLEAN));
        // Pig
        load(V1_8_R1.class, "Entity:Pig:hasSaddle", new MethodExecutor(getClassExec("Entity:Pig"), ClassExecutor.BOOLEAN, "hasSaddle", "Checks if a pig has a saddle"));
        load(V1_8_R1.class, "Entity:Pig:setSaddle", new MethodExecutor(getClassExec("Entity:Pig"), ClassExecutor.VOID, "setSaddle", "Sets the saddle state of a pig", ClassExecutor.BOOLEAN));
        // Ocelot
        load(V1_8_R1.class, "Entity:Ocelot:getCatType", new MethodExecutor(getClassExec("Entity:Ocelot"), ClassExecutor.INT, "getCatType", "Gets the cat type of a Ocelot"));
        load(V1_8_R1.class, "Entity:Ocelot:setCatType", new MethodExecutor(getClassExec("Entity:Ocelot"), ClassExecutor.VOID, "setCatType", "Sets the cat type of a Ocelot", ClassExecutor.INT));
        // Rabbit
        load(V1_8_R1.class, "Entity:Rabbit:getVariant", new MethodExecutor(getClassExec("Entity:Rabbit"), ClassExecutor.INT, "cl", "Gets the variant of a Rabbit"));
        load(V1_8_R1.class, "Entity:Rabbit:setVariant", new MethodExecutor(getClassExec("Entity:Rabbit"), ClassExecutor.VOID, "r", "Sets the variant of a Rabbit", ClassExecutor.INT));
        // Sheep
        load(V1_8_R1.class, "Entity:Sheep:getColor", new MethodExecutor(getClassExec("Entity:Sheep"), getClassExec("EnumColor"), "getColor", "Gets the color of a Sheep"));
        load(V1_8_R1.class, "Entity:Sheep:setColor", new MethodExecutor(getClassExec("Entity:Sheep"), ClassExecutor.VOID, "setColor", "Sets the color of a Sheep", getClassExec("EnumColor")));

        load(V1_8_R1.class, "Entity:Sheep:isSheared", new MethodExecutor(getClassExec("Entity:Sheep"), ClassExecutor.BOOLEAN, "isSheared", "Checks if a sheep is sheared"));
        load(V1_8_R1.class, "Entity:Sheep:setSheared", new MethodExecutor(getClassExec("Entity:Sheep"), ClassExecutor.VOID, "setSheared", "Sets the sheared state of a Sheep", ClassExecutor.BOOLEAN));
        // Wolf
        load(V1_8_R1.class, "Entity:Wolf:getCollarColor", new MethodExecutor(getClassExec("Entity:Wolf"), getClassExec("EnumColor"), "getCollarColor", "Gets the collar color of a wolf"));
        load(V1_8_R1.class, "Entity:Wolf:setCollarColor", new MethodExecutor(getClassExec("Entity:Wolf"), ClassExecutor.VOID, "setCollarColor", "Sets the collar color of a wolf", getClassExec("EnumColor")));

        load(V1_8_R1.class, "Entity:Wolf:isAngry", new MethodExecutor(getClassExec("Entity:Wolf"), ClassExecutor.BOOLEAN, "isAngry", "Checks if a wolf is angry"));
        load(V1_8_R1.class, "Entity:Wolf:setAngry", new MethodExecutor(getClassExec("Entity:Wolf"), ClassExecutor.VOID, "setAngry", "Sets the angry state of a wolf", ClassExecutor.BOOLEAN));
        // ItemFrame
        load(V1_8_R1.class, "Entity:ItemFrame:getItem", new MethodExecutor(getClassExec("Entity:ItemFrame"), getClassExec("ItemStack"), "getItem", "Gets the item of the ItemFrame"));
        load(V1_8_R1.class, "Entity:ItemFrame:setItem", new MethodExecutor(getClassExec("Entity:ItemFrame"), ClassExecutor.VOID, "setItem", "Sets the item of a ItemFrame", getClassExec("ItemStack")));
        load(V1_8_R1.class, "Entity:ItemFrame:getRotation", new MethodExecutor(getClassExec("Entity:ItemFrame"), ClassExecutor.INT, "getRotation", "Gets the rotation of a ItemFrame"));
        load(V1_8_R1.class, "Entity:ItemFrame:setRotation", new MethodExecutor(getClassExec("Entity:ItemFrame"), ClassExecutor.VOID, "setRotation", "Sets the rotation of a ItemFrame", ClassExecutor.INT));
        // Item
        load(V1_8_R1.class, "Entity:Item:getItemStack", new MethodExecutor(getClassExec("Entity:Item"), getClassExec("ItemStack"), "getItemStack", "Gets the item of the Item"));
        load(V1_8_R1.class, "Entity:Item:setItemStack", new MethodExecutor(getClassExec("Entity:Item"), ClassExecutor.VOID, "setItemStack", "Sets the item of a Item", getClassExec("ItemStack")));
        // WitherSkull
        load(V1_8_R1.class, "Entity:WitherSkull:isCharged", new MethodExecutor(getClassExec("Entity:WitherSkull"), ClassExecutor.BOOLEAN, "isCharged", "Gets the charged state of the Wither skull"));
        load(V1_8_R1.class, "Entity:WitherSkull:setCharged", new MethodExecutor(getClassExec("Entity:WitherSkull"), ClassExecutor.VOID, "setCharged", "Sets the charged state of a Wither skull", ClassExecutor.BOOLEAN));
        // Blaze
        load(V1_8_R1.class, "Entity:Blaze:isCharging", new MethodExecutor(getClassExec("Entity:Blaze"), ClassExecutor.BOOLEAN, "n", "Gets the charged state of the Blaze"));
        load(V1_8_R1.class, "Entity:Blaze:setCharging", new MethodExecutor(getClassExec("Entity:Blaze"), ClassExecutor.VOID, "a", "Sets the charged state of a Blaze", ClassExecutor.BOOLEAN));
        // Creeper
        load(V1_8_R1.class, "Entity:Creeper:isPowered", new MethodExecutor(getClassExec("Entity:Creeper"), ClassExecutor.BOOLEAN, "isPowered", "Gets the powered state of the Creeper"));
        load(V1_8_R1.class, "Entity:Creeper:setPowered", new MethodExecutor(getClassExec("Entity:Creeper"), ClassExecutor.VOID, "setPowered", "Sets the powered state of a Creeper", ClassExecutor.BOOLEAN));
        // Enderman
        load(V1_8_R1.class, "Entity:Enderman:getCarried", new MethodExecutor(getClassExec("Entity:Enderman"), getClassExec("IBlockData"), "getCarried", "Gets the block of a Enderman"));
        load(V1_8_R1.class, "Entity:Enderman:setCarried", new MethodExecutor(getClassExec("Entity:Enderman"), ClassExecutor.VOID, "setCarried", "Sets the block of a Enderman", getClassExec("IBlockData")));
        load(V1_8_R1.class, "Entity:Enderman:isScreaming", new MethodExecutor(getClassExec("Entity:Enderman"), ClassExecutor.BOOLEAN, "cm", "Gets the screaming state of a Enderman"));
        load(V1_8_R1.class, "Entity:Enderman:setScreaming", new MethodExecutor(getClassExec("Entity:Enderman"), ClassExecutor.VOID, "a", "Sets the screaming state of a Enderman", ClassExecutor.BOOLEAN));
        // Skeleton
        load(V1_8_R1.class, "Entity:Skeleton:getSkeletonType", new MethodExecutor(getClassExec("Entity:Skeleton"), ClassExecutor.INT, "getSkeletonType", "Gets the skeleton type of the Skeleton"));
        load(V1_8_R1.class, "Entity:Skeleton:setSkeletonType", new MethodExecutor(getClassExec("Entity:Skeleton"), ClassExecutor.VOID, "setSkeletonType", "Sets the skeleton type of a Skeleton", ClassExecutor.INT));
        // Bat
        load(V1_8_R1.class, "Entity:Bat:isAsleep", new MethodExecutor(getClassExec("Entity:Bat"), ClassExecutor.BOOLEAN, "isAsleep", "Check if a bat is sleeping"));
        load(V1_8_R1.class, "Entity:Bat:setAsleep", new MethodExecutor(getClassExec("Entity:Bat"), ClassExecutor.VOID, "setAsleep", "Sets the sleeping state of a bat", ClassExecutor.BOOLEAN));
        // Horse
        load(V1_8_R1.class, "Entity:Horse:getType", new MethodExecutor(getClassExec("Entity:Horse"), ClassExecutor.INT, "getType", "Gets the horse type"));
        load(V1_8_R1.class, "Entity:Horse:setType", new MethodExecutor(getClassExec("Entity:Horse"), ClassExecutor.VOID, "setType", "Sets the horse type", ClassExecutor.INT));
        load(V1_8_R1.class, "Entity:Horse:setHasChest", new MethodExecutor(getClassExec("Entity:Horse"), ClassExecutor.VOID, "setHasChest", "Sets the chest state of a Horse", ClassExecutor.BOOLEAN));
        load(V1_8_R1.class, "Entity:Horse:hasChest", new MethodExecutor(getClassExec("Entity:Horse"), ClassExecutor.BOOLEAN, "hasChest", "Gets the chest state of a Horse"));
        load(V1_8_R1.class, "Entity:Horse:setVariant", new MethodExecutor(getClassExec("Entity:Horse"), ClassExecutor.VOID, "setVariant", "Sets the variant of a Horse", ClassExecutor.INT));
        load(V1_8_R1.class, "Entity:Horse:getVariant", new MethodExecutor(getClassExec("Entity:Horse"), ClassExecutor.INT, "getVariant", "Gets the variant of a Horse"));
        // Slime
        load(V1_8_R1.class, "Entity:Slime:getSize", new MethodExecutor(getClassExec("Entity:Slime"), ClassExecutor.INT, "getSize", "Gets the slime size"));
        load(V1_8_R1.class, "Entity:Slime:setSize", new MethodExecutor(getClassExec("Entity:Slime"), ClassExecutor.VOID, "setSize", "Sets the slime size", ClassExecutor.INT));
        // Spider
        load(V1_8_R1.class, "Entity:Spider:isClimbing", new MethodExecutor(getClassExec("Entity:Spider"), ClassExecutor.BOOLEAN, "n", "Gets the spider climbing state"));
        load(V1_8_R1.class, "Entity:Spider:setClimbing", new MethodExecutor(getClassExec("Entity:Spider"), ClassExecutor.VOID, "a", "Sets the spider climbing state", ClassExecutor.BOOLEAN));
        // Zombie
        load(V1_8_R1.class, "Entity:Zombie:setVillagerType", new MethodExecutor(getClassExec("Entity:Zombie"), ClassExecutor.VOID, "setVillager", "Sets the villager state of a Zombie", ClassExecutor.BOOLEAN));

        load(V1_8_R1.class, "Entity:Zombie:isBaby", new MethodExecutor(getClassExec("Entity:Zombie"), ClassExecutor.BOOLEAN, "isBaby", "Checks if a zombie is a baby"));
        load(V1_8_R1.class, "Entity:Zombie:setBaby", new MethodExecutor(getClassExec("Entity:Zombie"), ClassExecutor.VOID, "setBaby", "Sets the baby state of a zombie", ClassExecutor.BOOLEAN));
        // Villager
        load(V1_8_R1.class, "Entity:Villager:getProfession", new MethodExecutor(getClassExec("Entity:Villager"), ClassExecutor.INT, "getProfession", "Gets the profession of a Villager"));
        load(V1_8_R1.class, "Entity:Villager:setProfession", new MethodExecutor(getClassExec("Entity:Villager"), ClassExecutor.VOID, "setProfession", "Sets the profession of a Villager", ClassExecutor.INT));
        //

        // CraftItemStack
        load(V1_8_R1.class, "CraftItemStack:asCraftMirror", new MethodExecutor(getClassExec("CraftItemStack"), getClassExec("CraftItemStack"), "asCraftMirror", "Gets the CraftItemStack from a NMS ItemStack", getClassExec("ItemStack")));
        //

        // Scoreboard
        load(V1_8_R1.class, "Entity:EntityPlayer:getScoreboard", new MethodExecutor(getClassExec("EntityPlayer"), getClassExec("Scoreboard"), "getScoreboard", "Gets the Scoreboard from the EntityPlayer"));
        load(V1_8_R1.class, "Scoreboard:getTeam", new MethodExecutor(getClassExec("Scoreboard"), getClassExec("ScoreboardTeam"), "getTeam", "Gets a ScoreboardTeam from a Scoreboard", ClassExecutor.STRING));
        load(V1_8_R1.class, "Scoreboard:addToTeam", new MethodExecutor(getClassExec("Scoreboard"), ClassExecutor.BOOLEAN, "addPlayerToTeam", "Adds a EntityPlayer to a ScoreboardTeam", ClassExecutor.STRING, ClassExecutor.STRING));
        load(V1_8_R1.class, "Scoreboard:createTeam", new MethodExecutor(getClassExec("Scoreboard"), getClassExec("ScoreboardTeam"), "createTeam", "Creates a new team on a Scoreboard", ClassExecutor.STRING));
        load(V1_8_R1.class, "CraftScoreboard:getHandle", new MethodExecutor(getClassExec("CraftScoreboard"), getClassExec("Scoreboard"), "getHandle", "Gets the NMS Scoreboard from a CraftScoreboard"));
        // Team
        load(V1_8_R1.class, "ScoreboardTeam:getPlayerNameSet", new MethodExecutor(getClassExec("ScoreboardTeam"), ClassExecutor.COLLECTION, "getPlayerNameSet", "Gets the player's list of this ScoreboardTeam"));
        load(V1_8_R1.class, "ScoreboardTeam:setNameTagVisibity", new MethodExecutor(getClassExec("ScoreboardTeam"), ClassExecutor.VOID, "a", "Sets the nametag visibility of a ScoreboardTeam", getClassExec("ScoreboardTeamBase:EnumNameTagVisibility")));
        load(V1_8_R1.class, "ScoreboardTeam:setColor", new MethodExecutor(getClassExec("ScoreboardTeam"), ClassExecutor.VOID, "a", "Sets the color of a ScoreboardTeam", getClassExec("EnumChatFormat")));
        load(V1_8_R1.class, "ScoreboardTeam:setPrefix", new MethodExecutor(getClassExec("ScoreboardTeam"), ClassExecutor.VOID, "setPrefix", "Sets the prefix of a ScoreboardTeam", ClassExecutor.STRING));
        load(V1_8_R1.class, "ScoreboardTeam:getName", new MethodExecutor(getClassExec("ScoreboardTeam"), ClassExecutor.STRING, "getName", "Gets the name of a ScoreboardTeam"));
        //

        // ChatSerializer
        load(V1_8_R1.class, "ChatSerializer:convertToString", new MethodExecutor(getClassExec("IChatBaseComponent"), ClassExecutor.STRING, "getText", "Converts a IChatBaseComponent to a string"));
        load(V1_8_R1.class, "ChatSerializer:convertToComponent", new MethodExecutor(getClassExec("ChatSerializer"), getClassExec("IChatBaseComponent"), "a", "Converts a string to a IChatBaseComponent", ClassExecutor.STRING));
        //

        // GameProfile
        load(V1_8_R1.class, "GameProfile:getProperties", new MethodExecutor(getClassExec("GameProfile"), getClassExec("PropertyMap"), "getProperties", "Gets the properties of a GameProfile"));
        load(V1_8_R1.class, "GameProfile:getName", new MethodExecutor(getClassExec("GameProfile"), ClassExecutor.STRING, "getName", "Gets the name of a GameProfile"));
        load(V1_8_R1.class, "PropertyMap:delegate", new MethodExecutor(getClassExec("PropertyMap"), new ClassExecutor(Multimap.class), "delegate", "Gets the properties from a PropertyMap"));
        //

        // Location
        load(V1_8_R1.class, "CraftWorld:getHandle", new MethodExecutor(getClassExec("CraftWorld"), getClassExec("WorldServer"), "getHandle", "Gets the NMS WorldServer from a CraftWorld"));
        load(V1_8_R1.class, "World:getEntityById", new MethodExecutor(getClassExec("World"), getClassExec("Entity"), "a", "Gets a entity by its ID", ClassExecutor.INT));
        load(V1_8_R1.class, "World:getCraftWorld", new MethodExecutor(getClassExec("World"), getClassExec("CraftWorld"), "getWorld", "Gets the CraftWorld of a NMS World"));
        //

        // PlayerConnection & NetworkManager
        load(V1_8_R1.class, "PlayerConnection:sendPacket", new MethodExecutor(getClassExec("PlayerConnection"), ClassExecutor.VOID, "sendPacket", "Sends a packet to a player", getClassExec("Packet")));
        load(V1_8_R1.class, "PlayerConnection:getNetworkManager", new MethodExecutor(getClassExec("PlayerConnection"), getClassExec("NetworkManager"), "a", "Gets the NetworkManager from a PlayerConnection"));
        //

        // DataWatcher
        load(V1_8_R1.class, "WatchableObject:getValue", new MethodExecutor(getClassExec("WatchableObject"), ClassExecutor.OBJECT, "b", "Gets the object from a WatchableObject"));
        load(V1_8_R1.class, "WatchableObject:setValue", new MethodExecutor(getClassExec("WatchableObject"), ClassExecutor.VOID, "a", "Sets the object from a WatchableObject", ClassExecutor.OBJECT));
        //

        // Objects
        load(V1_8_R1.class, "CraftBlock:getNMSBlock", new MethodExecutor(getClassExec("CraftBlock"), getClassExec("Block"), "getNMSBlock", "Gets the NMS Block from a CraftBlock"));
        load(V1_8_R1.class, "CraftMagicNumbers:getBlock", new MethodExecutor(getClassExec("CraftMagicNumbers"), getClassExec("Block"), "getBlock", "Gets the NMS Block from a Bukkit Material", new ClassExecutor(Material.class)));
        load(V1_8_R1.class, "CraftMagicNumbers:getMaterial", new MethodExecutor(getClassExec("CraftMagicNumbers"), new ClassExecutor(Material.class), "getMaterial", "Gets the Bukkit material from a NMS Block", getClassExec("Block")));
        load(V1_8_R1.class, "Block:getData", new MethodExecutor(getClassExec("Block"), getClassExec("IBlockData"), "getBlockData", "Gets the data of a block"));
        load(V1_8_R1.class, "IBlockData:getBlock", new MethodExecutor(getClassExec("IBlockData"), getClassExec("Block"), "getBlock", "Gets the block of a data"));
        load(V1_8_R1.class, "CraftItemStack:asNMSCopy", new MethodExecutor(getClassExec("CraftItemStack"), getClassExec("ItemStack"), "asNMSCopy", "Gets a NMS ItemStack from a Craft ItemStack", ClassExecutor.ITEMSTACK));
        load(V1_8_R1.class, "CraftServer:getServer", new MethodExecutor(getClassExec("CraftServer"), getClassExec("MinecraftServer"), "getServer", "Gets the NMS MinecraftServer from a CraftServer"));
        //

        // EnumColor
        load(V1_8_R1.class, "EnumColor:getColorIndex", new MethodExecutor(getClassExec("EnumColor"), ClassExecutor.INT, "getColorIndex", "Gets the color index of a EnumColor"));
        load(V1_8_R1.class, "EnumColor:fromColorIndex", new MethodExecutor(getClassExec("EnumColor"), getClassExec("EnumColor"), "fromColorIndex", "Gets the color enum from the index of a EnumColor", ClassExecutor.INT));
        //

        // Location
        load(V1_8_R1.class, "Vector3f:getX", new MethodExecutor(getClassExec("Vector3f"), ClassExecutor.FLOAT, "getX", "Gets the X align of a Vector3f"));
        load(V1_8_R1.class, "Vector3f:getY", new MethodExecutor(getClassExec("Vector3f"), ClassExecutor.FLOAT, "getY", "Gets the Y align of a Vector3f"));
        load(V1_8_R1.class, "Vector3f:getZ", new MethodExecutor(getClassExec("Vector3f"), ClassExecutor.FLOAT, "getZ", "Gets the Z align of a Vector3f"));
        //

        // BlockPosition
        load(V1_8_R1.class, "BlockPosition:getX", new MethodExecutor(getClassExec("BlockPosition"), ClassExecutor.INT, "getX", "Gets the X position of a BlockPosition"));
        load(V1_8_R1.class, "BlockPosition:getY", new MethodExecutor(getClassExec("BlockPosition"), ClassExecutor.INT, "getY", "Gets the Y position of a BlockPosition"));
        load(V1_8_R1.class, "BlockPosition:getZ", new MethodExecutor(getClassExec("BlockPosition"), ClassExecutor.INT, "getZ", "Gets the Z position of a BlockPosition"));
        //
    }

    @Override
    public void loadFields() {
        // NBT
        load(V1_8_R1.class, "NBTTagList:list", new FieldExecutor(getClassExec("NBTBase:NBTTagList"), new ClassExecutor(List.class) {}, "list", "Gets the list of a NBTTagList"));
        //

        // Player
        load(V1_8_R1.class, "EntityPlayer:playerConnection", new FieldExecutor(getClassExec("EntityPlayer"), getClassExec("PlayerConnection"), "playerConnection", "Gets the PlayerConnection from the player"));
        load(V1_8_R1.class, "EntityPlayer:listName", new FieldExecutor(getClassExec("EntityPlayer"), getClassExec("IChatBaseComponent"), "listName", "Gets the entity player's tablist name"));
        load(V1_8_R1.class, "GameProfile:id", new FieldExecutor(getClassExec("GameProfile"), new ClassExecutor(UUID.class), "id", "Gets the UUID from GameProfile"));
        //

        // NetworkManager
        load(V1_8_R1.class, "NetworkManager:channel", new FieldExecutor(getClassExec("NetworkManager"), new ClassExecutor(Channel.class) {}, "i", "Gets the channel of the player"));
        //

        // PacketPlayInUseEntity
        load(V1_8_R1.class, "PacketPlayInUseEntity:entityId", new FieldExecutor(getClassExec("PacketPlayInUseEntity"), ClassExecutor.INT, "a", "Get the entity id of a PacketPlayInUseEntity"));
        load(V1_8_R1.class, "PacketPlayInUseEntity:EnumEntityUseAction:action", new FieldExecutor(getClassExec("PacketPlayInUseEntity"), getEnumExec("PacketPlayInUseEntity:EnumEntityUseAction"), "action", "Gets the action from entity in use packet"));
        //

        // ArmorStand
        load(V1_8_R1.class, "Entity:ArmorStand:headPose", new FieldExecutor(getClassExec("Entity:ArmorStand"), getClassExec("Vector3f"), "headPose", "Gets the head pose of an ArmorStand"));
        load(V1_8_R1.class, "Entity:ArmorStand:bodyPose", new FieldExecutor(getClassExec("Entity:ArmorStand"), getClassExec("Vector3f"), "bodyPose", "Gets the body pose of an ArmorStand"));
        load(V1_8_R1.class, "Entity:ArmorStand:leftArmPose", new FieldExecutor(getClassExec("Entity:ArmorStand"), getClassExec("Vector3f"), "leftArmPose", "Gets the left arm pose of an ArmorStand"));
        load(V1_8_R1.class, "Entity:ArmorStand:rightArmPose", new FieldExecutor(getClassExec("Entity:ArmorStand"), getClassExec("Vector3f"), "rightArmPose", "Gets the right arm pose of an ArmorStand"));
        load(V1_8_R1.class, "Entity:ArmorStand:leftLegPose", new FieldExecutor(getClassExec("Entity:ArmorStand"), getClassExec("Vector3f"), "rightLegPose", "Gets the left leg pose of an ArmorStand"));
        load(V1_8_R1.class, "Entity:ArmorStand:rightLegPose", new FieldExecutor(getClassExec("Entity:ArmorStand"), getClassExec("Vector3f"), "leftLegPose", "Gets the right leg pose of an ArmorStand"));
        //

        // Location
        load(V1_8_R1.class, "Entity:world", new FieldExecutor(getClassExec("Entity"), getClassExec("World"), "world", "Gets the world of an Entity"));
        load(V1_8_R1.class, "Entity:locX", new FieldExecutor(getClassExec("Entity"), ClassExecutor.DOUBLE, "locX", "Gets the X position of an Entity"));
        load(V1_8_R1.class, "Entity:locY", new FieldExecutor(getClassExec("Entity"), ClassExecutor.DOUBLE, "locY", "Gets the Y position of an Entity"));
        load(V1_8_R1.class, "Entity:locZ", new FieldExecutor(getClassExec("Entity"), ClassExecutor.DOUBLE, "locZ", "Gets the Z position of an Entity"));
        //

        // DataWatcher
        load(V1_8_R1.class, "DataWatcher:map", new FieldExecutor(getClassExec("DataWatcher"), new ClassExecutor(Map.class), "d", "Gets the values of the data"));
        //
    }

    @Override
    public void loadEnums() {
        // EnumPlayerInfoAction
        if (!V1_19_R2.class.isAssignableFrom(getClass())) {
            load(V1_8_R1.class, "PacketPlayOutPlayerInfo:EnumPlayerInfoAction", new EnumPlayerInfoActionEnum(getClassExec("PacketPlayOutPlayerInfo:EnumPlayerInfoAction")));
        }
        //

        // EnumNameTagVisilibityEnum
        load(V1_8_R1.class, "EnumNameTagVisibilityEnum:EnumNameTagVisibility", new EnumNameTagVisibilityEnum(getClassExec("ScoreboardTeamBase:EnumNameTagVisibility")));
        //

        // PacketPlayInUseEntity
        load(V1_8_R1.class, "PacketPlayInUseEntity:EnumEntityUseAction", new EntityUseInPacket.ActionEnum(getClassExec("PacketPlayInUseEntity:EnumEntityUseAction")));
        //

        load(V1_8_R1.class, "EnumColor", new EnumColorEnum(getClassExec("EnumColor")));

        load(V1_8_R1.class, "EnumChatFormat", new EnumChatFormatEnum(getClassExec("EnumChatFormat")));

    }

    @Override
    public void loadTexts() {
        // EnumPlayerInfoAction
        super.getTexts().put("PacketPlayOutPlayerInfo:EnumPlayerInfoAction:ADD_PLAYER", "ADD_PLAYER");
        super.getTexts().put("PacketPlayOutPlayerInfo:EnumPlayerInfoAction:REMOVE_PLAYER", "REMOVE_PLAYER");
        super.getTexts().put("PacketPlayOutPlayerInfo:EnumPlayerInfoAction:UPDATE_DISPLAY_NAME", "UPDATE_DISPLAY_NAME");
        //

        // EnumNameTagVisibility
        super.getTexts().put("ScoreboardTeamBase:EnumNameTagVisibility:NEVER", "NEVER");
        //

        // PacketPlayInUseEntity
        super.getTexts().put("PacketPlayInUseEntity:EnumEntityUseAction:INTERACT", "INTERACT");
        super.getTexts().put("PacketPlayInUseEntity:EnumEntityUseAction:ATTACK", "ATTACK");
        super.getTexts().put("PacketPlayInUseEntity:EnumEntityUseAction:INTERACT_AT", "INTERACT_AT");
        //

        // EnumTeamPush
        super.getTexts().put("ScoreboardTeam:EnumTeamPush:NEVER", "NEVER");
        super.getTexts().put("ScoreboardTeam:EnumTeamPush:ALWAYS", "ALWAYS");
    }

    @Override
    public void loadObjects() {
        super.getObjects().put("Metadata:Player:SkinParts", 10);
        super.getObjects().put("Metadata:Horse:Armor", 22);
        super.getObjects().put("Metadata:Ghast:Attacking", 16);
        super.getObjects().put("Metadata:Guardian:Target", 17);
        super.getObjects().put("Metadata:Creeper:Ignited", 18);
    }

    @Override
    public @NotNull String getName() {
        return "v1_8_R1";
    }

}
