package codes.laivy.npc.mappings.versions;

import codes.laivy.npc.mappings.Version;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.illagers.Evoker;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.illagers.IllagerWizard;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.illagers.Vindicator;
import codes.laivy.npc.mappings.defaults.classes.entity.vehicle.Boat;
import codes.laivy.npc.mappings.defaults.classes.java.BooleanObjExec;
import codes.laivy.npc.mappings.defaults.classes.java.ByteObjExec;
import codes.laivy.npc.mappings.defaults.classes.java.IntegerObjExec;
import codes.laivy.npc.mappings.instances.EnumExecutor;
import codes.laivy.npc.mappings.instances.Executor;
import codes.laivy.npc.mappings.instances.FieldExecutor;
import codes.laivy.npc.mappings.instances.MethodExecutor;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.defaults.classes.datawatcher.DataWatcher;
import codes.laivy.npc.mappings.defaults.classes.datawatcher.DataWatcherItem;
import codes.laivy.npc.mappings.defaults.classes.datawatcher.DataWatcherObject;
import codes.laivy.npc.mappings.defaults.classes.entity.*;
import codes.laivy.npc.mappings.defaults.classes.entity.ambient.Bat;
import codes.laivy.npc.mappings.defaults.classes.entity.ambient.Egg;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.*;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.*;
import codes.laivy.npc.mappings.defaults.classes.entity.boss.dragon.EnderDragon;
import codes.laivy.npc.mappings.defaults.classes.entity.boss.dragon.EnderSignal;
import codes.laivy.npc.mappings.defaults.classes.entity.boss.wither.Wither;
import codes.laivy.npc.mappings.defaults.classes.entity.boss.wither.WitherSkull;
import codes.laivy.npc.mappings.defaults.classes.entity.decoration.ArmorStand;
import codes.laivy.npc.mappings.defaults.classes.entity.decoration.ItemFrame;
import codes.laivy.npc.mappings.defaults.classes.entity.decoration.LeashKnot;
import codes.laivy.npc.mappings.defaults.classes.entity.item.FallingBlock;
import codes.laivy.npc.mappings.defaults.classes.entity.item.Item;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.*;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.skeleton.Skeleton;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.skeleton.SkeletonStray;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.skeleton.SkeletonWither;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.zombie.Zombie;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.zombie.ZombieHusk;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.zombie.ZombieVillager;
import codes.laivy.npc.mappings.defaults.classes.entity.npc.Villager;
import codes.laivy.npc.mappings.defaults.classes.enums.*;
import codes.laivy.npc.mappings.defaults.classes.gameprofile.GameProfile;
import codes.laivy.npc.mappings.defaults.classes.gameprofile.Property;
import codes.laivy.npc.mappings.defaults.classes.gameprofile.PropertyMap;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class V1_11_R1 extends V1_10_R1 {

    @Override
    protected boolean onLoad(@NotNull Class<? extends Version> version, @NotNull String key, @NotNull Executor executor) {
        if (version == V1_10_R1.class) {
            if (executor instanceof ClassExecutor && !(executor instanceof EnumExecutor)) {
                return false;
            } else if (executor instanceof EnumExecutor) {
                switch (key) {
                    case "EnumSkeletonType":
                    case "EnumZombieType":
                        return false;
                    default:
                        break;
                }
            } else if (executor instanceof MethodExecutor) {
                switch (key) {
                    case "Entity:Skeleton:getSkeletonType":
                    case "Entity:Skeleton:setSkeletonType":
                    case "Entity:Zombie:getVillagerType":
                    case "Entity:Zombie:setVillagerType":
                        return false;
                    default:
                        break;
                }
            } else if (executor instanceof FieldExecutor) {
                switch (key) {
                    case "Metadata:Enderman:screaming":
                        load(V1_11_R1.class, key, new FieldExecutor(getClassExec("Entity:Enderman"), getClassExec("DataWatcherObject"), "bx", "Gets the enderman's screaming datawatcher object"));
                        return false;
                    case "Metadata:Horse:Armor":
                        load(V1_11_R1.class, key, new FieldExecutor(getClassExec("Entity:Horse"), getClassExec("DataWatcherObject"), "bI", "Gets the horse armor DataWatcherObject"));
                        return false;
                    case "Metadata:Horse:Variant":
                        load(V1_11_R1.class, key, new FieldExecutor(getClassExec("Entity:Horse"), getClassExec("DataWatcherObject"), "bH", "Gets the horse variant DataWatcherObject"));
                        return false;
                    case "Metadata:Zombie:Baby":
                        load(V1_11_R1.class, key, new FieldExecutor(getClassExec("Entity:Zombie"), getClassExec("DataWatcherObject"), "bw", "Gets the zombie baby DataWatcherObject"));
                        return false;
                    case "Metadata:PolarBear:Standing":
                        load(V1_11_R1.class, key, new FieldExecutor(getClassExec("Entity:PolarBear"), getClassExec("DataWatcherObject"), "bw", "Gets the polar bear's standing DataWatcherObject"));
                        return false;
                    case "Metadata:Zombie:Villager:Profession":
                        load(V1_11_R1.class, key, new FieldExecutor(getClassExec("Entity:Zombie:Villager"), getClassExec("DataWatcherObject"), "c", "Gets the zombie villager profession DataWatcherObject"));
                        return false;
                    case "Metadata:Pig:Saddle":
                        load(V1_11_R1.class, key, new FieldExecutor(getClassExec("Entity:Pig"), getClassExec("DataWatcherObject"), "bw", "Gets the pig saddle DataWatcherObject"));
                        return false;
                    case "Metadata:Wolf:Angry":
                        load(V1_11_R1.class, key, new FieldExecutor(getClassExec("Entity:Wolf"), getClassExec("DataWatcherObject"), "bB", "Gets the wolf angry DataWatcherObject"));
                        return false;
                    case "Metadata:Wolf:CollarColor":
                        load(V1_11_R1.class, key, new FieldExecutor(getClassExec("Entity:Wolf"), getClassExec("DataWatcherObject"), "bC", "Gets the wolf collar color DataWatcherObject"));
                        return false;
                    default:
                        break;
                }
            }
        } else if (version == V1_8_R1.class) {
            if (executor instanceof MethodExecutor) {
                switch (key) {
                    case "Entity:Slime:setSize":
                        load(V1_11_R1.class, key, new MethodExecutor(getClassExec("Entity:Slime"), ClassExecutor.VOID, "setSize", "Sets the slime size", ClassExecutor.INT, ClassExecutor.BOOLEAN));
                        return false;
                    case "Entity:Horse:setHasChest":
                        load(V1_11_R1.class, key, new MethodExecutor(getClassExec("Entity:Horse:Abstract:Chested"), ClassExecutor.VOID, "setCarryingChest", "Sets the chest state of a Horse", ClassExecutor.BOOLEAN));
                        return false;
                    case "Entity:Horse:hasChest":
                        load(V1_11_R1.class, key, new MethodExecutor(getClassExec("Entity:Horse:Abstract:Chested"), ClassExecutor.BOOLEAN, "isCarryingChest", "Gets the chest state of a Horse"));
                        return false;
                    default:
                        break;
                }
            }
        } else if (version == V1_9_R1.class) {
            if (executor instanceof EnumExecutor) {
                switch (key) {
                    case "EnumZombieType":
                    case "EnumSkeletonType":
                    case "EnumHorseType":
                        return false;
                    default:
                        break;
                }
            } else if (executor instanceof MethodExecutor) {
                switch (key) {
                    case "Entity:Snowman:hasPumpkinHat":
                        load(V1_11_R1.class, key, new MethodExecutor(getClassExec("Entity:Snowman"), ClassExecutor.BOOLEAN, "hasPumpkin", "Gets the pumpkin hat state of a Snowman"));
                        return false;
                    case "Entity:Snowman:setPumpkinHat":
                        load(V1_11_R1.class, key, new MethodExecutor(getClassExec("Entity:Snowman"), ClassExecutor.VOID, "setHasPumpkin", "Sets the pumpkin hat state of a Snowman", ClassExecutor.BOOLEAN));
                        return false;
                    case "Entity:Horse:getType":
                    case "Entity:Horse:setType":
                        return false;
                    default:
                        break;
                }
            } else if (executor instanceof FieldExecutor) {
                if (key.equals("Metadata:Guardian:Target")) {
                    load(V1_11_R1.class, key, new FieldExecutor(getClassExec("Entity:Guardian"), getClassExec("DataWatcherObject"), "bA", "Gets the Guardian target DataWatcherObject"));
                    return false;
                }
            }
        }

        return super.onLoad(version, key, executor);
    }

    @Override
    public @Nullable Entity getEntityInstance(Entity.@NotNull EntityType type, @NotNull org.bukkit.Location location) {
        Entity entity = null;
        if (type == Entity.EntityType.HORSE) {
            Object object = getClassExec("Entity:Horse").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new Horse(object);
        } else if (type == Entity.EntityType.HORSE_DONKEY) {
            Object object = getClassExec("Entity:Horse:Donkey").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new HorseDonkey(object);
        } else if (type == Entity.EntityType.HORSE_MULE) {
            Object object = getClassExec("Entity:Horse:Mule").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new HorseMule(object);
        } else if (type == Entity.EntityType.HORSE_SKELETON) {
            Object object = getClassExec("Entity:Horse:Skeleton").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new HorseSkeleton(object);
        } else if (type == Entity.EntityType.HORSE_ZOMBIE) {
            Object object = getClassExec("Entity:Horse:Zombie").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new HorseZombie(object);
        } else if (type == Entity.EntityType.SKELETON_WITHER) {
            Object object = getClassExec("Entity:Skeleton:Wither").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new SkeletonWither(object);
        } else if (type == Entity.EntityType.SKELETON_STRAY) {
            Object object = getClassExec("Entity:Skeleton:Stray").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new SkeletonStray(object);
        } else if (type == Entity.EntityType.ZOMBIE_VILLAGER) {
            Object object = getClassExec("Entity:Zombie:Villager").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new ZombieVillager(object);
        } else if (type == Entity.EntityType.ZOMBIE_HUSK) {
            Object object = getClassExec("Entity:Zombie:Husk").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new ZombieHusk(object);
        } else if (type == Entity.EntityType.LLAMA) {
            Object object = getClassExec("Entity:Llama").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new Llama(object);
        } else if (type == Entity.EntityType.VINDICATOR) {
            Object object = getClassExec("Entity:Vindicator").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new Vindicator(object);
        } else if (type == Entity.EntityType.VEX) {
            Object object = getClassExec("Entity:Vex").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new Vex(object);
        } else if (type == Entity.EntityType.EVOKER) {
            Object object = getClassExec("Entity:Evoker").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new Evoker(object);
        }

        if (entity == null) {
            entity = super.getEntityInstance(type, location);
        }

        return entity;
    }

    @Override
    public void setEntityWizardSpell(@NotNull IllagerWizard wizard, EnumSpellEnum.@NotNull Spell spell) {
        if (wizard instanceof Evoker) {
            wizard.getDataWatcher().set(IllagerWizard.SPELL_METADATA(), new ByteObjExec((byte) spell.getValue()));
        } else {
            throw new UnsupportedOperationException("This version (1.11) only supports Evokers as wizards");
        }
    }
    @Override
    public @NotNull EnumSpellEnum.Spell getEntityWizardSpell(@NotNull IllagerWizard wizard) {
        if (wizard instanceof Evoker) {
            return EnumSpellEnum.Spell.getByValue(((Byte) Objects.requireNonNull(wizard.getDataWatcher().get(IllagerWizard.SPELL_METADATA()))).intValue());
        } else {
            throw new UnsupportedOperationException("This version (1.11) only supports Evokers as wizards");
        }
    }

    @Override
    public void setEntitySlimeSize(@NotNull Slime slime, int size) {
        getMethodExec("Entity:Slime:setSize").invokeInstance(slime, new IntegerObjExec(size), new BooleanObjExec(false));
    }

    @Override
    public @NotNull Skeleton.Type getEntitySkeletonType(@NotNull Skeleton skeleton) {
        if (skeleton instanceof SkeletonWither) {
            return Skeleton.Type.WITHER;
        } else if (skeleton instanceof SkeletonStray) {
            return Skeleton.Type.STRAY;
        } else {
            return Skeleton.Type.NORMAL;
        }
    }

    @Override
    public void setEntitySkeletonType(@NotNull Skeleton skeleton, Skeleton.@NotNull Type type) {
        throw new IllegalStateException("This version doesn't supports entity skeleton type changing");
    }

    @Override
    public @NotNull Horse.Type getEntityAbstractHorseType(@NotNull AbstractHorse horse) {
        if (horse instanceof HorseDonkey) {
            return AbstractHorse.Type.DONKEY;
        } else if (horse instanceof HorseMule) {
            return AbstractHorse.Type.MULE;
        } else if (horse instanceof HorseZombie) {
            return AbstractHorse.Type.ZOMBIE;
        } else if (horse instanceof HorseSkeleton) {
            return AbstractHorse.Type.SKELETON;
        } else if (horse instanceof Llama) {
            return AbstractHorse.Type.LLAMA;
        } else {
            return AbstractHorse.Type.HORSE;
        }
    }
    @Override
    public void setEntityAbstractHorseType(@NotNull AbstractHorse horse, Horse.@NotNull Type type) {
        throw new IllegalStateException("This version doesn't supports entity horse type changing");
    }

    @Override
    public @NotNull Zombie.Type getEntityZombieType(@NotNull Zombie zombie) {
        if (zombie instanceof ZombieHusk) {
            return Zombie.Type.HUSK;
        } else if (zombie instanceof ZombieVillager) {
            return Zombie.Type.VILLAGER;
        } else {
            return Zombie.Type.NORMAL;
        }
    }
    @Override
    public void setEntityZombieType(@NotNull Zombie zombie, Zombie.@Nullable Type type) {
        throw new IllegalStateException("This version doesn't supports entity horse type changing");
    }

    @Override
    public @NotNull EnumColorEnum.EnumColor getEntityShulkerColor(@NotNull Shulker shulker) {
        DataWatcherObject metadata = new DataWatcherObject(getFieldExec("Metadata:Shulker:Color").invokeStatic());
        return EnumColorEnum.fromColorIndex(((Byte) Objects.requireNonNull(shulker.getDataWatcher().get(metadata))).intValue());
    }
    @Override
    public void setEntityShulkerColor(@NotNull Shulker shulker, EnumColorEnum.@NotNull EnumColor variant) {
        DataWatcherObject metadata = new DataWatcherObject(getFieldExec("Metadata:Shulker:Color").invokeStatic());
        shulker.getDataWatcher().set(metadata, new ByteObjExec((byte) variant.getColorIndex()));
    }

    @Override
    public void setEntityLlamaVariant(@NotNull Llama llama, Llama.@NotNull Variant variant) {
        if (!variant.isCompatible()) {
            throw new UnsupportedOperationException("This llama variant '" + variant.name() + "' is available only at '" + variant.getSince().getSimpleName() + "' or higher!");
        }

        llama.getDataWatcher().set(Llama.VARIANT_METADATA(), new IntegerObjExec(variant.getId()));
    }
    @Override
    public @NotNull Llama.Variant getEntityLlamaVariant(@NotNull Llama llama) {
        //noinspection DataFlowIssue
        return Llama.Variant.getById((int) llama.getDataWatcher().get(Llama.VARIANT_METADATA()));
    }

    @Override
    public void setEntityLlamaCarpetColor(@NotNull Llama llama, EnumColorEnum.@Nullable EnumColor color) {
        int index = -1;
        if (color != null) {
            index = color.getColorIndex();
        }

        llama.getDataWatcher().set(Llama.CARPET_COLOR_METADATA(), new IntegerObjExec(index));
    }
    @Override
    public EnumColorEnum.@Nullable EnumColor getEntityLlamaCarpetColor(@NotNull Llama llama) {
        //noinspection DataFlowIssue
        int index = (int) llama.getDataWatcher().get(Llama.CARPET_COLOR_METADATA());

        if (index == -1) {
            return null;
        }

        return EnumColorEnum.fromColorIndex(index);
    }

    @Override
    public void loadClasses() {
        load(V1_11_R1.class, "WatchableObject", new ClassExecutor.BrokenClassExecutor());
        load(V1_11_R1.class, "EnumSkeletonType", new ClassExecutor.BrokenClassExecutor());
        load(V1_11_R1.class, "EnumHorseType", new ClassExecutor.BrokenClassExecutor());
        load(V1_11_R1.class, "EnumZombieType", new ClassExecutor.BrokenClassExecutor());

        load(V1_11_R1.class, "NBTBase", new NBTBase.NBTBaseClass("net.minecraft.server.v1_11_R1.NBTBase"));

        load(V1_11_R1.class, "NBTBase:NBTTagByte", new NBTTagByte.NBTTagByteClass("net.minecraft.server.v1_11_R1.NBTTagByte"));
        load(V1_11_R1.class, "NBTBase:NBTTagByteArray", new NBTTagByteArray.NBTTagByteArrayClass("net.minecraft.server.v1_11_R1.NBTTagByteArray"));
        load(V1_11_R1.class, "NBTBase:NBTTagCompound", new NBTTagCompound.NBTTagCompoundClass("net.minecraft.server.v1_11_R1.NBTTagCompound"));
        load(V1_11_R1.class, "NBTBase:NBTTagDouble", new NBTTagDouble.NBTTagDoubleClass("net.minecraft.server.v1_11_R1.NBTTagDouble"));
        load(V1_11_R1.class, "NBTBase:NBTTagFloat", new NBTTagFloat.NBTTagFloatClass("net.minecraft.server.v1_11_R1.NBTTagFloat"));
        load(V1_11_R1.class, "NBTBase:NBTTagInt", new NBTTagInt.NBTTagIntClass("net.minecraft.server.v1_11_R1.NBTTagInt"));
        load(V1_11_R1.class, "NBTBase:NBTTagIntArray", new NBTTagIntArray.NBTTagIntArrayClass("net.minecraft.server.v1_11_R1.NBTTagIntArray"));
        load(V1_11_R1.class, "NBTBase:NBTTagList", new NBTTagList.NBTTagListClass("net.minecraft.server.v1_11_R1.NBTTagList"));
        load(V1_11_R1.class, "NBTBase:NBTTagLong", new NBTTagLong.NBTTagLongClass("net.minecraft.server.v1_11_R1.NBTTagLong"));
        load(V1_11_R1.class, "NBTBase:NBTTagShort", new NBTTagShort.NBTTagShortClass("net.minecraft.server.v1_11_R1.NBTTagShort"));
        load(V1_11_R1.class, "NBTBase:NBTTagString", new NBTTagString.NBTTagStringClass("net.minecraft.server.v1_11_R1.NBTTagString"));
        //

        // Packets
        load(V1_11_R1.class, "Packet", new Packet.PacketClass("net.minecraft.server.v1_11_R1.Packet"));
        load(V1_11_R1.class, "PacketPlayOutSpawnEntity", new EntitySpawnPacket.EntitySpawnPacketClass("net.minecraft.server.v1_11_R1.PacketPlayOutSpawnEntity"));
        load(V1_11_R1.class, "PacketPlayOutEntityDestroy", new EntityDestroyPacket.EntityDestroyPacketClass("net.minecraft.server.v1_11_R1.PacketPlayOutEntityDestroy"));
        load(V1_11_R1.class, "PacketPlayOutAnimation", new EntityAnimationPacket.EntityAnimationPacketClass("net.minecraft.server.v1_11_R1.PacketPlayOutAnimation"));
        load(V1_11_R1.class, "PacketPlayOutSpawnEntityLiving", new EntityLivingSpawnPacket.EntityLivingSpawnPacketClass("net.minecraft.server.v1_11_R1.PacketPlayOutSpawnEntityLiving"));
        load(V1_11_R1.class, "PacketPlayOutEntityMetadata", new EntityMetadataPacket.EntityMetadataPacketClass("net.minecraft.server.v1_11_R1.PacketPlayOutEntityMetadata"));
        load(V1_11_R1.class, "PacketPlayOutNamedEntitySpawn", new EntityNamedSpawnPacket.EntityNamedSpawnPacketClass("net.minecraft.server.v1_11_R1.PacketPlayOutNamedEntitySpawn"));
        load(V1_11_R1.class, "PacketPlayOutPlayerInfo", new PlayerInfoPacket.PlayerInfoPacketClass("net.minecraft.server.v1_11_R1.PacketPlayOutPlayerInfo"));
        load(V1_11_R1.class, "PacketPlayOutPlayerInfo:EnumPlayerInfoAction", new EnumPlayerInfoActionEnum.EnumPlayerInfoActionClass("net.minecraft.server.v1_11_R1.PacketPlayOutPlayerInfo$EnumPlayerInfoAction"));
        load(V1_11_R1.class, "PacketPlayOutScoreboardTeam", new ScoreboardTeamPacket.ScoreboardTeamPacketClass("net.minecraft.server.v1_11_R1.PacketPlayOutScoreboardTeam"));
        load(V1_11_R1.class, "PacketPlayOutEntityEquipment", new EntityEquipmentPacket.EntityEquipmentPacketClass("net.minecraft.server.v1_11_R1.PacketPlayOutEntityEquipment"));
        load(V1_11_R1.class, "PacketPlayOutEntityTeleport", new EntityTeleportPacket.EntityTeleportPacketClass("net.minecraft.server.v1_11_R1.PacketPlayOutEntityTeleport"));
        load(V1_11_R1.class, "PacketPlayOutEntityHeadRotation", new EntityHeadRotationPacket.EntityHeadRotationPacketClass("net.minecraft.server.v1_11_R1.PacketPlayOutEntityHeadRotation"));
        load(V1_11_R1.class, "PacketPlayOutEntityLook", new EntityLookPacket.EntityLookPacketClass("net.minecraft.server.v1_11_R1.PacketPlayOutEntity$PacketPlayOutEntityLook"));

        load(V1_11_R1.class, "PacketPlayInUseEntity", new EntityUseInPacket.EntityUseInPacketClass("net.minecraft.server.v1_11_R1.PacketPlayInUseEntity"));
        load(V1_11_R1.class, "PacketPlayInUseEntity:EnumEntityUseAction", new EntityUseInPacket.ActionEnum.ActionClass("net.minecraft.server.v1_11_R1.PacketPlayInUseEntity$EnumEntityUseAction"));
        //

        // Server
        load(V1_11_R1.class, "MinecraftServer", new MinecraftServer.MinecraftServerClass("net.minecraft.server.v1_11_R1.MinecraftServer"));
        load(V1_11_R1.class, "WorldServer", new WorldServer.WorldServerClass("net.minecraft.server.v1_11_R1.WorldServer"));
        load(V1_11_R1.class, "CraftServer", new CraftServer.CraftServerClass("org.bukkit.craftbukkit.v1_11_R1.CraftServer"));
        //

        // Entity
        load(V1_11_R1.class, "Entity", new Entity.EntityClass("net.minecraft.server.v1_11_R1.Entity"));
        load(V1_11_R1.class, "EntityLiving", new EntityLiving.EntityLivingClass("net.minecraft.server.v1_11_R1.EntityLiving"));
        load(V1_11_R1.class, "Entity:Human", new Entity.EntityClass("net.minecraft.server.v1_11_R1.EntityHuman"));
        load(V1_11_R1.class, "CraftPlayer", new CraftPlayer.CraftPlayerClass("org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer"));
        load(V1_11_R1.class, "EntityPlayer", new EntityPlayer.EntityPlayerClass("net.minecraft.server.v1_11_R1.EntityPlayer"));

        load(V1_11_R1.class, "Entity:ArmorStand", new ArmorStand.ArmorStandClass("net.minecraft.server.v1_11_R1.EntityArmorStand"));
        load(V1_11_R1.class, "Entity:Pig", new Pig.PigClass("net.minecraft.server.v1_11_R1.EntityPig"));
        load(V1_11_R1.class, "Entity:Cow", new Cow.CowClass("net.minecraft.server.v1_11_R1.EntityCow"));
        load(V1_11_R1.class, "Entity:Ocelot", new Ocelot.OcelotClass("net.minecraft.server.v1_11_R1.EntityOcelot"));
        load(V1_11_R1.class, "Entity:Bat", new Bat.BatClass("net.minecraft.server.v1_11_R1.EntityBat"));
        load(V1_11_R1.class, "Entity:Egg", new Egg.EggClass("net.minecraft.server.v1_11_R1.EntityEgg"));
        load(V1_11_R1.class, "Entity:Chicken", new Chicken.ChickenClass("net.minecraft.server.v1_11_R1.EntityChicken"));
        load(V1_11_R1.class, "Entity:Horse", new AbstractHorse.AbstractHorseClass("net.minecraft.server.v1_11_R1.EntityHorse"));
        load(V1_11_R1.class, "Entity:IronGolem", new IronGolem.IronGolemClass("net.minecraft.server.v1_11_R1.EntityIronGolem"));
        load(V1_11_R1.class, "Entity:Rabbit", new Rabbit.RabbitClass("net.minecraft.server.v1_11_R1.EntityRabbit"));
        load(V1_11_R1.class, "Entity:Sheep", new Sheep.SheepClass("net.minecraft.server.v1_11_R1.EntitySheep"));
        load(V1_11_R1.class, "Entity:Snowman", new Snowman.SnowmanClass("net.minecraft.server.v1_11_R1.EntitySnowman"));
        load(V1_11_R1.class, "Entity:Squid", new Squid.SquidClass("net.minecraft.server.v1_11_R1.EntitySquid"));
        load(V1_11_R1.class, "Entity:Wolf", new Wolf.WolfClass("net.minecraft.server.v1_11_R1.EntityWolf"));
        load(V1_11_R1.class, "Entity:ItemFrame", new ItemFrame.ItemFrameClass("net.minecraft.server.v1_11_R1.EntityItemFrame"));
        load(V1_11_R1.class, "Entity:LeashKnot", new LeashKnot.LeashKnotClass("net.minecraft.server.v1_11_R1.EntityLeash"));
        load(V1_11_R1.class, "Entity:FallingBlock", new FallingBlock.FallingBlockClass("net.minecraft.server.v1_11_R1.EntityFallingBlock"));
        load(V1_11_R1.class, "Entity:Item", new Item.ItemClass("net.minecraft.server.v1_11_R1.EntityItem"));
        load(V1_11_R1.class, "Entity:EnderDragon", new EnderDragon.EnderDragonClass("net.minecraft.server.v1_11_R1.EntityEnderDragon"));
        load(V1_11_R1.class, "Entity:EnderSignal", new EnderSignal.EnderSignalClass("net.minecraft.server.v1_11_R1.EntityEnderSignal"));
        load(V1_11_R1.class, "Entity:Wither", new Wither.WitherClass("net.minecraft.server.v1_11_R1.EntityWither"));
        load(V1_11_R1.class, "Entity:WitherSkull", new WitherSkull.WitherSkullClass("net.minecraft.server.v1_11_R1.EntityWitherSkull"));
        load(V1_11_R1.class, "Entity:Blaze", new Blaze.BlazeClass("net.minecraft.server.v1_11_R1.EntityBlaze"));
        load(V1_11_R1.class, "Entity:Creeper", new Creeper.CreeperClass("net.minecraft.server.v1_11_R1.EntityCreeper"));
        load(V1_11_R1.class, "Entity:Enderman", new Enderman.EndermanClass("net.minecraft.server.v1_11_R1.EntityEnderman"));
        load(V1_11_R1.class, "Entity:Ghast", new Ghast.GhastClass("net.minecraft.server.v1_11_R1.EntityGhast"));
        load(V1_11_R1.class, "Entity:Guardian", new Guardian.GuardianClass("net.minecraft.server.v1_11_R1.EntityGuardian"));
        load(V1_11_R1.class, "Entity:Silverfish", new Silverfish.SilverfishClass("net.minecraft.server.v1_11_R1.EntitySilverfish"));
        load(V1_11_R1.class, "Entity:Skeleton", new Skeleton.SkeletonClass("net.minecraft.server.v1_11_R1.EntitySkeleton"));
        load(V1_11_R1.class, "Entity:Slime", new Slime.SlimeClass("net.minecraft.server.v1_11_R1.EntitySlime"));
        load(V1_11_R1.class, "Entity:Spider", new Spider.SpiderClass("net.minecraft.server.v1_11_R1.EntitySpider"));
        load(V1_11_R1.class, "Entity:Witch", new Witch.WitchClass("net.minecraft.server.v1_11_R1.EntityWitch"));
        load(V1_11_R1.class, "Entity:Zombie", new Zombie.ZombieClass("net.minecraft.server.v1_11_R1.EntityZombie"));
        load(V1_11_R1.class, "Entity:Villager", new Villager.VillagerClass("net.minecraft.server.v1_11_R1.EntityVillager"));
        load(V1_11_R1.class, "Entity:Shulker", new Shulker.ShulkerClass("net.minecraft.server.v1_11_R1.EntityShulker"));
        load(V1_11_R1.class, "Entity:PolarBear", new PolarBear.PolarBearClass("net.minecraft.server.v1_11_R1.EntityPolarBear"));
        load(V1_11_R1.class, "Entity:Boat", new Boat.BoatClass("net.minecraft.server.v1_11_R1.EntityBoat"));
        load(V1_11_R1.class, "Entity:CaveSpider", new CaveSpider.CaveSpiderClass("net.minecraft.server.v1_11_R1.EntityCaveSpider"));

        load(V1_11_R1.class, "Entity:Ageable", new AgeableEntityLiving.AgeableEntityLivingClass("net.minecraft.server.v1_11_R1.EntityAgeable"));
        load(V1_11_R1.class, "Entity:Tameable", new TameableEntityLiving.TameableEntityLivingClass("net.minecraft.server.v1_11_R1.EntityTameableAnimal"));
        // EntityPlayer
        load(V1_11_R1.class, "GameProfile", new GameProfile.GameProfileClass("com.mojang.authlib.GameProfile"));
        load(V1_11_R1.class, "PropertyMap", new PropertyMap.PropertyMapClass("com.mojang.authlib.properties.PropertyMap"));
        load(V1_11_R1.class, "Property", new Property.PropertyClass("com.mojang.authlib.properties.Property"));
        //

        // Managers
        load(V1_11_R1.class, "PlayerInteractManager", new PlayerInteractManager.PlayerInteractManagerClass("net.minecraft.server.v1_11_R1.PlayerInteractManager"));
        //

        // DataWatcher
        load(V1_11_R1.class, "DataWatcher", new DataWatcher.DataWatcherClass("net.minecraft.server.v1_11_R1.DataWatcher"));
        load(V1_11_R1.class, "DataWatcherObject", new DataWatcherObject.DataWatcherObjectClass("net.minecraft.server.v1_11_R1.DataWatcherObject"));
        load(V1_11_R1.class, "DataWatcher:Item", new DataWatcherItem.DataWatcherItemClass("net.minecraft.server.v1_11_R1.DataWatcher$Item"));
        //

        // Scoreboard
        load(V1_11_R1.class, "CraftScoreboard", new CraftScoreboard.CraftScoreboardClass("org.bukkit.craftbukkit.v1_11_R1.scoreboard.CraftScoreboard"));
        load(V1_11_R1.class, "Scoreboard", new Scoreboard.ScoreboardClass("net.minecraft.server.v1_11_R1.Scoreboard"));

        load(V1_11_R1.class, "ScoreboardTeam", new ScoreboardTeam.ScoreboardTeamClass("net.minecraft.server.v1_11_R1.ScoreboardTeam"));
        load(V1_11_R1.class, "ScoreboardTeam:EnumTeamPush", new EnumTeamPushEnum.EnumTeamPushClass("net.minecraft.server.v1_11_R1.ScoreboardTeamBase$EnumTeamPush"));

        load(V1_11_R1.class, "ScoreboardTeamBase:EnumNameTagVisibility", new EnumNameTagVisibilityEnum.EnumNameTagVisibilityClass("net.minecraft.server.v1_11_R1.ScoreboardTeamBase$EnumNameTagVisibility"));
        //

        // Others
        load(V1_11_R1.class, "PlayerConnection", new PlayerConnection.PlayerConnectionClass("net.minecraft.server.v1_11_R1.PlayerConnection"));
        load(V1_11_R1.class, "NetworkManager", new NetworkManager.NetworkManagerClass("net.minecraft.server.v1_11_R1.NetworkManager"));

        load(V1_11_R1.class, "EnumChatFormat", new EnumChatFormatEnum.EnumChatFormatClass("net.minecraft.server.v1_11_R1.EnumChatFormat"));
        load(V1_11_R1.class, "EnumColor", new EnumColorEnum.EnumColorClass("net.minecraft.server.v1_11_R1.EnumColor"));
        load(V1_11_R1.class, "EnumItemSlot", new EnumItemSlotEnum.EnumItemSlotClass("net.minecraft.server.v1_11_R1.EnumItemSlot"));
        load(V1_11_R1.class, "EnumDirection", new EnumDirectionEnum.EnumDirectionClass("net.minecraft.server.v1_11_R1.EnumDirection"));
        //

        // Chat
        load(V1_11_R1.class, "IChatBaseComponent", new IChatBaseComponent.IChatBaseComponentClass("net.minecraft.server.v1_11_R1.IChatBaseComponent"));
        load(V1_11_R1.class, "ChatSerializer", new IChatBaseComponent.ChatSerializerClass("net.minecraft.server.v1_11_R1.IChatBaseComponent$ChatSerializer"));
        //

        // Objects
        load(V1_11_R1.class, "CraftWorld", new CraftWorld.CraftWorldClass("org.bukkit.craftbukkit.v1_11_R1.CraftWorld"));
        load(V1_11_R1.class, "World", new World.WorldClass("net.minecraft.server.v1_11_R1.World"));
        load(V1_11_R1.class, "Vector3f", new Vector3f.Vector3fClass("net.minecraft.server.v1_11_R1.Vector3f"));
        load(V1_11_R1.class, "Vec3D", new Vec3D.Vec3DClass("net.minecraft.server.v1_11_R1.Vec3D"));
        load(V1_11_R1.class, "BlockPosition", new BlockPosition.BlockPositionClass("net.minecraft.server.v1_11_R1.BlockPosition"));
        load(V1_11_R1.class, "CraftBlock", new CraftBlock.CraftBlockClass("org.bukkit.craftbukkit.v1_11_R1.block.CraftBlock"));
        load(V1_11_R1.class, "IBlockData", new IBlockData.IBlockDataClass("net.minecraft.server.v1_11_R1.IBlockData"));
        load(V1_11_R1.class, "Block", new Block.BlockClass("net.minecraft.server.v1_11_R1.Block"));
        load(V1_11_R1.class, "CraftMagicNumbers", new ClassExecutor("org.bukkit.craftbukkit.v1_11_R1.util.CraftMagicNumbers"));
        //

        // Items
        load(V1_11_R1.class, "ItemStack", new ItemStack.ItemStackClass("net.minecraft.server.v1_11_R1.ItemStack"));
        load(V1_11_R1.class, "CraftItemStack", new CraftItemStack.CraftItemStackClass("org.bukkit.craftbukkit.v1_11_R1.inventory.CraftItemStack"));
        //

        // Entity horse
        load(V1_11_R1.class, "Entity:Horse:Abstract", new AbstractHorse.AbstractHorseClass("net.minecraft.server.v1_11_R1.EntityHorseAbstract"));
        load(V1_11_R1.class, "Entity:Horse:Abstract:Chested", new AbstractChestedHorse.AbstractChestedHorseClass("net.minecraft.server.v1_11_R1.EntityHorseChestedAbstract"));
        load(V1_11_R1.class, "Entity:Horse:Donkey", new HorseDonkey.HorseDonkeyClass("net.minecraft.server.v1_11_R1.EntityHorseDonkey"));
        load(V1_11_R1.class, "Entity:Horse:Mule", new HorseMule.HorseMuleClass("net.minecraft.server.v1_11_R1.EntityHorseMule"));
        load(V1_11_R1.class, "Entity:Horse:Skeleton", new HorseSkeleton.HorseSkeletonClass("net.minecraft.server.v1_11_R1.EntityHorseSkeleton"));
        load(V1_11_R1.class, "Entity:Horse:Zombie", new HorseZombie.HorseZombieClass("net.minecraft.server.v1_11_R1.EntityHorseZombie"));
        // Entity skeleton
        load(V1_11_R1.class, "Entity:Skeleton:Wither", new SkeletonWither.SkeletonWitherClass("net.minecraft.server.v1_11_R1.EntitySkeletonWither"));
        load(V1_11_R1.class, "Entity:Skeleton:Stray", new SkeletonStray.SkeletonStrayClass("net.minecraft.server.v1_11_R1.EntitySkeletonStray"));
        // Entity zombie
        load(V1_11_R1.class, "Entity:Zombie:Villager", new ZombieVillager.ZombieVillagerClass("net.minecraft.server.v1_11_R1.EntityZombieVillager"));
        load(V1_11_R1.class, "Entity:Zombie:Husk", new ZombieHusk.ZombieHuskClass("net.minecraft.server.v1_11_R1.EntityZombieHusk"));
        // Entity vindicator
        load(V1_11_R1.class, "Entity:Vindicator", new Vindicator.VindicatorClass("net.minecraft.server.v1_11_R1.EntityVindicator"));
        // Entity evoker
        load(V1_11_R1.class, "Entity:Evoker", new Evoker.EvokerClass("net.minecraft.server.v1_11_R1.EntityEvoker"));
        // Entity vex
        load(V1_11_R1.class, "Entity:Vex", new Vex.VexClass("net.minecraft.server.v1_11_R1.EntityVex"));
        // Entity llama
        load(V1_11_R1.class, "Entity:Llama", new Llama.LlamaClass("net.minecraft.server.v1_11_R1.EntityLlama"));
        //
    }

    @Override
    public void loadFields() {
        super.loadFields();

        load(V1_11_R1.class, "Metadata:IllagerWizard:Spell", new FieldExecutor(getClassExec("Entity:Evoker"), getClassExec("DataWatcherObject"), "a", "Gets the illager illusioner's spell DataWatcherObject"));
        load(V1_11_R1.class, "Metadata:Shulker:Color", new FieldExecutor(getClassExec("Entity:Shulker"), getClassExec("DataWatcherObject"), "bw", "Gets the shulker's color DataWatcherObject"));
        load(V1_11_R1.class, "Metadata:Llama:CarpetColor", new FieldExecutor(getClassExec("Entity:Llama"), getClassExec("DataWatcherObject"), "bH", "Gets the llama's carpet color DataWatcherObject"));
        load(V1_11_R1.class, "Metadata:Llama:Variant", new FieldExecutor(getClassExec("Entity:Llama"), getClassExec("DataWatcherObject"), "bI", "Gets the llama's variant DataWatcherObject"));
    }

    @Override
    public @NotNull String getName() {
        return "v1_11_R1";
    }

}
