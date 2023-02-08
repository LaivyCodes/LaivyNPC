package codes.laivy.npc.mappings.versions;

import codes.laivy.npc.mappings.Version;
import codes.laivy.npc.mappings.instances.EnumExecutor;
import codes.laivy.npc.mappings.instances.Executor;
import codes.laivy.npc.mappings.instances.FieldExecutor;
import codes.laivy.npc.mappings.instances.MethodExecutor;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.utils.classes.datawatcher.*;
import codes.laivy.npc.mappings.utils.classes.entity.*;
import codes.laivy.npc.mappings.utils.classes.entity.ambient.Bat;
import codes.laivy.npc.mappings.utils.classes.entity.ambient.Egg;
import codes.laivy.npc.mappings.utils.classes.entity.animal.*;
import codes.laivy.npc.mappings.utils.classes.entity.animal.horse.Horse;
import codes.laivy.npc.mappings.utils.classes.entity.boss.dragon.EnderDragon;
import codes.laivy.npc.mappings.utils.classes.entity.boss.dragon.EnderSignal;
import codes.laivy.npc.mappings.utils.classes.entity.boss.wither.Wither;
import codes.laivy.npc.mappings.utils.classes.entity.boss.wither.WitherSkull;
import codes.laivy.npc.mappings.utils.classes.entity.decoration.ArmorStand;
import codes.laivy.npc.mappings.utils.classes.entity.decoration.ItemFrame;
import codes.laivy.npc.mappings.utils.classes.entity.decoration.LeashKnot;
import codes.laivy.npc.mappings.utils.classes.entity.item.FallingBlock;
import codes.laivy.npc.mappings.utils.classes.entity.item.Item;
import codes.laivy.npc.mappings.utils.classes.entity.monster.*;
import codes.laivy.npc.mappings.utils.classes.entity.npc.Villager;
import codes.laivy.npc.mappings.utils.classes.enums.*;
import codes.laivy.npc.mappings.utils.classes.gameprofile.GameProfile;
import codes.laivy.npc.mappings.utils.classes.gameprofile.Property;
import codes.laivy.npc.mappings.utils.classes.gameprofile.PropertyMap;
import codes.laivy.npc.mappings.utils.classes.java.BooleanObjExec;
import codes.laivy.npc.mappings.utils.classes.java.IntegerObjExec;
import codes.laivy.npc.mappings.utils.classes.nbt.NBTBase;
import codes.laivy.npc.mappings.utils.classes.nbt.tags.*;
import codes.laivy.npc.mappings.utils.classes.others.chat.IChatBaseComponent;
import codes.laivy.npc.mappings.utils.classes.others.location.*;
import codes.laivy.npc.mappings.utils.classes.others.managers.PlayerInteractManager;
import codes.laivy.npc.mappings.utils.classes.others.objects.*;
import codes.laivy.npc.mappings.utils.classes.others.server.CraftServer;
import codes.laivy.npc.mappings.utils.classes.others.server.MinecraftServer;
import codes.laivy.npc.mappings.utils.classes.packets.*;
import codes.laivy.npc.mappings.utils.classes.scoreboard.CraftScoreboard;
import codes.laivy.npc.mappings.utils.classes.scoreboard.Scoreboard;
import codes.laivy.npc.mappings.utils.classes.scoreboard.ScoreboardTeam;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

import static codes.laivy.npc.LaivyNPC.laivynpc;
import static codes.laivy.npc.mappings.utils.classes.others.objects.ItemStack.getNMSItemStack;

public class V1_9_R1 extends V1_8_R3 {

    @Override
    protected boolean onLoad(@NotNull Class<? extends Version> version, @NotNull String key, @NotNull Executor executor) {
        if (version == V1_8_R3.class) {
            if (executor instanceof ClassExecutor && !(executor instanceof EnumExecutor)) {
                return false;
            }
        } else if (version == V1_8_R1.class) {
            if (executor instanceof MethodExecutor) {
                switch (key) {
                    case "Entity:Blaze:isCharging":
                        load(V1_9_R1.class, key, new MethodExecutor(getClassExec("Entity:Blaze"), ClassExecutor.BOOLEAN, "isBurning", "Gets the charged state of the Blaze"));
                        return false;
                    case "Entity:Enderman:isScreaming":
                        load(V1_9_R1.class, key, new MethodExecutor(getClassExec("Entity:Enderman"), ClassExecutor.BOOLEAN, "dc", "Gets the screaming state of a Enderman"));
                        return false;
                    case "Entity:Zombie:setVillagerType":
                        load(V1_9_R1.class, key, new MethodExecutor(getClassExec("Entity:Zombie"), ClassExecutor.VOID, "setVillagerType", "Sets the villager type of a Zombie", ClassExecutor.INT));
                        return false;
                    case "Entity:Enderman:setScreaming":
                    case "WatchableObject:getValue":
                    case "WatchableObject:setValue":
                        return false;
                    case "Entity:Horse:setType":
                        load(V1_9_R1.class, key, new MethodExecutor(getClassExec("Entity:Horse"), ClassExecutor.VOID, "setType", "Gets the horse type", getClassExec("EnumHorseType")));
                        return false;
                    case "Entity:Horse:getType":
                        load(V1_9_R1.class, key, new MethodExecutor(getClassExec("Entity:Horse"), getClassExec("EnumHorseType"), "getType", "Gets the horse type"));
                        return false;
                    case "Entity:Spider:isClimbing":
                        load(V1_9_R1.class, key, new MethodExecutor(getClassExec("Entity:Spider"), ClassExecutor.BOOLEAN, "o", "Gets the spider climbing state"));
                        return false;
                    case "World:getEntityById":
                        load(V1_9_R1.class, key, new MethodExecutor(getClassExec("World"), getClassExec("Entity"), "getEntity", "Gets a entity by its ID", ClassExecutor.INT));
                        return false;
                    default:
                        break;
                }
            } else if (executor instanceof FieldExecutor) {
                switch (key) {
                    case "DataWatcher:map":
                        load(V1_9_R1.class, key, new FieldExecutor(getClassExec("DataWatcher"), new ClassExecutor(Map.class), "c", "Gets the values of the data"));
                        return false;
                    default:
                        break;
                }
            }
        }

        return super.onLoad(version, key, executor);
    }

    @Override
    public @Nullable Zombie.Type getEntityZombieType(@NotNull Zombie zombie) {
        //noinspection DataFlowIssue
        return Zombie.Type.getById((int) getMethodExec("Entity:Zombie:getVillagerType").invokeInstance(zombie));
    }
    @Override
    public void setEntityZombieType(@NotNull Zombie zombie, Zombie.@Nullable Type type) {
        if (type != null && !type.isCompatible()) {
            throw new IllegalArgumentException("This zombie type '" + type.name() + "' isn't compatible with that version!");
        }

        if (type == null) type = Zombie.Type.NORMAL;

        laivynpc().getVersion().getMethodExec("Entity:Zombie:setVillagerType").invokeInstance(zombie, new IntegerObjExec(type.getId()));
    }

    @Override
    public boolean isEntityCreeperIgnited(@NotNull Creeper creeper) {
        //noinspection DataFlowIssue
        return (boolean) creeper.getDataWatcher().get(Creeper.IGNITED_METADATA());
    }
    @Override
    public void setEntityCreeperIgnited(@NotNull Creeper creeper, boolean flag) {
        creeper.getDataWatcher().set(Creeper.IGNITED_METADATA(), new BooleanObjExec(flag));
    }

    @Override
    public void setEntityGuardianTarget(@NotNull Guardian guardian, @Nullable EntityLiving entity) {
        guardian.getDataWatcher().set(Guardian.TARGET_METADATA(), new IntegerObjExec(entity == null ? 0 : entity.getId()));
    }

    @Override
    public void setEntityHorseType(@NotNull Horse horse, Horse.@NotNull Type type) {
        EnumHorseTypeEnum.EnumHorseType horseType;
        if (type == Horse.Type.HORSE) {
            horseType = EnumHorseTypeEnum.HORSE();
        } else if (type == Horse.Type.DONKEY) {
            horseType = EnumHorseTypeEnum.DONKEY();
        } else if (type == Horse.Type.MULE) {
            horseType = EnumHorseTypeEnum.MULE();
        } else if (type == Horse.Type.ZOMBIE) {
            horseType = EnumHorseTypeEnum.ZOMBIE();
        } else if (type == Horse.Type.SKELETON) {
            horseType = EnumHorseTypeEnum.SKELETON();
        } else {
            throw new IllegalArgumentException("Couldn't find this horse type '" + type.name() + "'");
        }

        getMethodExec("Entity:Horse:setType").invokeInstance(horse, horseType);
    }

    @Override
    public @NotNull Horse.Type getEntityHorseType(@NotNull Horse horse) {
        return Horse.Type.valueOf(new EnumHorseTypeEnum.EnumHorseType((Enum<?>) getMethodExec("Entity:Horse:getType").invokeInstance(horse)).name());
    }

    @Override
    public void setEntityEndermanScreaming(@NotNull Enderman enderman, boolean screaming) {
        DataWatcherObject object = new DataWatcherObject(getFieldExec("Entity:Enderman:DataWatcher:screaming").invokeStatic());
        enderman.getDataWatcher().set(object, new BooleanObjExec(screaming));
    }

    @Override
    public boolean isEntityGhastAttacking(@NotNull Ghast ghast) {
        //noinspection DataFlowIssue
        return (boolean) ghast.getDataWatcher().get(Ghast.ATTACKING_METADATA());
    }
    @Override
    public void setEntityGhastAttacking(@NotNull Ghast ghast, boolean flag) {
        ghast.getDataWatcher().set(Ghast.ATTACKING_METADATA(), new BooleanObjExec(flag));
    }

    @Override
    public @NotNull EntityEquipmentPacket createEquipmentPacket(@NotNull Entity entity, @NotNull EnumItemSlotEnum.EnumItemSlot slot, @NotNull org.bukkit.inventory.ItemStack item) {
        Object packet = getClassExec("PacketPlayOutEntityEquipment").getConstructor(ClassExecutor.INT, getClassExec("EnumItemSlot"), getClassExec("ItemStack")).newInstance(new IntegerObjExec(entity.getId()), slot.getEnum(), getNMSItemStack(item));
        return new EntityEquipmentPacket(packet);
    }

    @Override
    public @NotNull Map<@NotNull Integer, @NotNull VersionedDataWatcherObject> dataWatcherGetValues(@NotNull DataWatcher dataWatcher) {
        //noinspection unchecked
        Map<Integer, ?> fMap = (Map<Integer, ?>) getFieldExec("DataWatcher:map").invokeInstance(dataWatcher);
        Map<Integer, VersionedDataWatcherObject> map = new HashMap<>();
        assert fMap != null;

        for (Map.Entry<Integer, ?> e : fMap.entrySet()) {
            map.put(e.getKey(), new DataWatcherItem(e.getValue()));
        }

        return map;
    }

    @Override
    public @Nullable Entity getEntityInstance(Entity.@NotNull EntityType type, @NotNull org.bukkit.Location location) {
        if (location.getWorld() == null) {
            throw new NullPointerException("This location's world is null!");
        }

        Entity entity = super.getEntityInstance(type, location);
        if (type == Entity.EntityType.SHULKER) {
            Object object = getClassExec("Entity:Shulker").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new Shulker(object);
        }

        return entity;
    }

    @Override
    public @NotNull Map<String, ClassExecutor> getClasses() {
        if (super.getClasses().isEmpty()) {
            load(V1_9_R1.class, "WatchableObject", new ClassExecutor.BrokenClassExecutor());

            load(V1_9_R1.class, "NBTBase", new NBTBase.NBTBaseClass("net.minecraft.server.v1_9_R1.NBTBase"));

            load(V1_9_R1.class, "NBTBase:NBTTagByte", new NBTTagByte.NBTTagByteClass("net.minecraft.server.v1_9_R1.NBTTagByte"));
            load(V1_9_R1.class, "NBTBase:NBTTagByteArray", new NBTTagByteArray.NBTTagByteArrayClass("net.minecraft.server.v1_9_R1.NBTTagByteArray"));
            load(V1_9_R1.class, "NBTBase:NBTTagCompound", new NBTTagCompound.NBTTagCompoundClass("net.minecraft.server.v1_9_R1.NBTTagCompound"));
            load(V1_9_R1.class, "NBTBase:NBTTagDouble", new NBTTagDouble.NBTTagDoubleClass("net.minecraft.server.v1_9_R1.NBTTagDouble"));
            load(V1_9_R1.class, "NBTBase:NBTTagFloat", new NBTTagFloat.NBTTagFloatClass("net.minecraft.server.v1_9_R1.NBTTagFloat"));
            load(V1_9_R1.class, "NBTBase:NBTTagInt", new NBTTagInt.NBTTagIntClass("net.minecraft.server.v1_9_R1.NBTTagInt"));
            load(V1_9_R1.class, "NBTBase:NBTTagIntArray", new NBTTagIntArray.NBTTagIntArrayClass("net.minecraft.server.v1_9_R1.NBTTagIntArray"));
            load(V1_9_R1.class, "NBTBase:NBTTagList", new NBTTagList.NBTTagListClass("net.minecraft.server.v1_9_R1.NBTTagList"));
            load(V1_9_R1.class, "NBTBase:NBTTagLong", new NBTTagLong.NBTTagLongClass("net.minecraft.server.v1_9_R1.NBTTagLong"));
            load(V1_9_R1.class, "NBTBase:NBTTagShort", new NBTTagShort.NBTTagShortClass("net.minecraft.server.v1_9_R1.NBTTagShort"));
            load(V1_9_R1.class, "NBTBase:NBTTagString", new NBTTagString.NBTTagStringClass("net.minecraft.server.v1_9_R1.NBTTagString"));
            //

            // Packets
            load(V1_9_R1.class, "Packet", new Packet.PacketClass("net.minecraft.server.v1_9_R1.Packet"));
            load(V1_9_R1.class, "PacketPlayOutSpawnEntity", new EntitySpawnPacket.EntitySpawnPacketClass("net.minecraft.server.v1_9_R1.PacketPlayOutSpawnEntity"));
            load(V1_9_R1.class, "PacketPlayOutEntityDestroy", new EntityDestroyPacket.EntityDestroyPacketClass("net.minecraft.server.v1_9_R1.PacketPlayOutEntityDestroy"));
            load(V1_9_R1.class, "PacketPlayOutAnimation", new EntityAnimationPacket.EntityAnimationPacketClass("net.minecraft.server.v1_9_R1.PacketPlayOutAnimation"));
            load(V1_9_R1.class, "PacketPlayOutSpawnEntityLiving", new EntityLivingSpawnPacket.EntityLivingSpawnPacketClass("net.minecraft.server.v1_9_R1.PacketPlayOutSpawnEntityLiving"));
            load(V1_9_R1.class, "PacketPlayOutEntityMetadata", new EntityMetadataPacket.EntityMetadataPacketClass("net.minecraft.server.v1_9_R1.PacketPlayOutEntityMetadata"));
            load(V1_9_R1.class, "PacketPlayOutNamedEntitySpawn", new EntityNamedSpawnPacket.EntityNamedSpawnPacketClass("net.minecraft.server.v1_9_R1.PacketPlayOutNamedEntitySpawn"));
            load(V1_9_R1.class, "PacketPlayOutPlayerInfo", new PlayerInfoPacket.PlayerInfoPacketClass("net.minecraft.server.v1_9_R1.PacketPlayOutPlayerInfo"));
            load(V1_9_R1.class, "PacketPlayOutPlayerInfo:EnumPlayerInfoAction", new EnumPlayerInfoActionEnum.EnumPlayerInfoActionClass("net.minecraft.server.v1_9_R1.PacketPlayOutPlayerInfo$EnumPlayerInfoAction"));
            load(V1_9_R1.class, "PacketPlayOutScoreboardTeam", new ScoreboardTeamPacket.ScoreboardTeamPacketClass("net.minecraft.server.v1_9_R1.PacketPlayOutScoreboardTeam"));
            load(V1_9_R1.class, "PacketPlayOutEntityEquipment", new EntityEquipmentPacket.EntityEquipmentPacketClass("net.minecraft.server.v1_9_R1.PacketPlayOutEntityEquipment"));
            load(V1_9_R1.class, "PacketPlayOutEntityTeleport", new EntityTeleportPacket.EntityTeleportPacketClass("net.minecraft.server.v1_9_R1.PacketPlayOutEntityTeleport"));
            load(V1_9_R1.class, "PacketPlayOutEntityHeadRotation", new EntityHeadRotationPacket.EntityHeadRotationPacketClass("net.minecraft.server.v1_9_R1.PacketPlayOutEntityHeadRotation"));
            load(V1_9_R1.class, "PacketPlayOutEntityLook", new EntityLookPacket.EntityLookPacketClass("net.minecraft.server.v1_9_R1.PacketPlayOutEntity$PacketPlayOutEntityLook"));

            load(V1_9_R1.class, "PacketPlayInUseEntity", new EntityUseInPacket.EntityUseInPacketClass("net.minecraft.server.v1_9_R1.PacketPlayInUseEntity"));
            load(V1_9_R1.class, "PacketPlayInUseEntity:EnumEntityUseAction", new EntityUseInPacket.ActionEnum.ActionClass("net.minecraft.server.v1_9_R1.PacketPlayInUseEntity$EnumEntityUseAction"));
            //

            // Server
            load(V1_9_R1.class, "MinecraftServer", new MinecraftServer.MinecraftServerClass("net.minecraft.server.v1_9_R1.MinecraftServer"));
            load(V1_9_R1.class, "WorldServer", new WorldServer.WorldServerClass("net.minecraft.server.v1_9_R1.WorldServer"));
            load(V1_9_R1.class, "CraftServer", new CraftServer.CraftServerClass("org.bukkit.craftbukkit.v1_9_R1.CraftServer"));
            //

            // Entity
            load(V1_9_R1.class, "Entity", new Entity.EntityClass("net.minecraft.server.v1_9_R1.Entity"));
            load(V1_9_R1.class, "EntityLiving", new EntityLiving.EntityLivingClass("net.minecraft.server.v1_9_R1.EntityLiving"));
            load(V1_9_R1.class, "Entity:Human", new Entity.EntityClass("net.minecraft.server.v1_9_R1.EntityHuman"));
            load(V1_9_R1.class, "CraftPlayer", new CraftPlayer.CraftPlayerClass("org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer"));
            load(V1_9_R1.class, "EntityPlayer", new EntityPlayer.EntityPlayerClass("net.minecraft.server.v1_9_R1.EntityPlayer"));

            load(V1_9_R1.class, "Entity:ArmorStand", new ArmorStand.ArmorStandClass("net.minecraft.server.v1_9_R1.EntityArmorStand"));
            load(V1_9_R1.class, "Entity:Pig", new Pig.PigClass("net.minecraft.server.v1_9_R1.EntityPig"));
            load(V1_9_R1.class, "Entity:Cow", new Cow.CowClass("net.minecraft.server.v1_9_R1.EntityCow"));
            load(V1_9_R1.class, "Entity:Ocelot", new Ocelot.OcelotClass("net.minecraft.server.v1_9_R1.EntityOcelot"));
            load(V1_9_R1.class, "Entity:Bat", new Bat.BatClass("net.minecraft.server.v1_9_R1.EntityBat"));
            load(V1_9_R1.class, "Entity:Egg", new Egg.EggClass("net.minecraft.server.v1_9_R1.EntityEgg"));
            load(V1_9_R1.class, "Entity:Chicken", new Chicken.ChickenClass("net.minecraft.server.v1_9_R1.EntityChicken"));
            load(V1_9_R1.class, "Entity:Horse", new Horse.HorseClass("net.minecraft.server.v1_9_R1.EntityHorse"));
            load(V1_9_R1.class, "Entity:IronGolem", new IronGolem.IronGolemClass("net.minecraft.server.v1_9_R1.EntityIronGolem"));
            load(V1_9_R1.class, "Entity:Rabbit", new Rabbit.RabbitClass("net.minecraft.server.v1_9_R1.EntityRabbit"));
            load(V1_9_R1.class, "Entity:Sheep", new Sheep.SheepClass("net.minecraft.server.v1_9_R1.EntitySheep"));
            load(V1_9_R1.class, "Entity:Snowman", new Snowman.SnowmanClass("net.minecraft.server.v1_9_R1.EntitySnowman"));
            load(V1_9_R1.class, "Entity:Squid", new Squid.SquidClass("net.minecraft.server.v1_9_R1.EntitySquid"));
            load(V1_9_R1.class, "Entity:Wolf", new Wolf.WolfClass("net.minecraft.server.v1_9_R1.EntityWolf"));
            load(V1_9_R1.class, "Entity:ItemFrame", new ItemFrame.ItemFrameClass("net.minecraft.server.v1_9_R1.EntityItemFrame"));
            load(V1_9_R1.class, "Entity:LeashKnot", new LeashKnot.LeashKnotClass("net.minecraft.server.v1_9_R1.EntityLeash"));
            load(V1_9_R1.class, "Entity:FallingBlock", new FallingBlock.FallingBlockClass("net.minecraft.server.v1_9_R1.EntityFallingBlock"));
            load(V1_9_R1.class, "Entity:Item", new Item.ItemClass("net.minecraft.server.v1_9_R1.EntityItem"));
            load(V1_9_R1.class, "Entity:EnderDragon", new EnderDragon.EnderDragonClass("net.minecraft.server.v1_9_R1.EntityEnderDragon"));
            load(V1_9_R1.class, "Entity:EnderSignal", new EnderSignal.EnderSignalClass("net.minecraft.server.v1_9_R1.EntityEnderSignal"));
            load(V1_9_R1.class, "Entity:Wither", new Wither.WitherClass("net.minecraft.server.v1_9_R1.EntityWither"));
            load(V1_9_R1.class, "Entity:WitherSkull", new WitherSkull.WitherSkullClass("net.minecraft.server.v1_9_R1.EntityWitherSkull"));
            load(V1_9_R1.class, "Entity:Blaze", new Blaze.BlazeClass("net.minecraft.server.v1_9_R1.EntityBlaze"));
            load(V1_9_R1.class, "Entity:Creeper", new Creeper.CreeperClass("net.minecraft.server.v1_9_R1.EntityCreeper"));
            load(V1_9_R1.class, "Entity:Enderman", new Enderman.EndermanClass("net.minecraft.server.v1_9_R1.EntityEnderman"));
            load(V1_9_R1.class, "Entity:Ghast", new Ghast.GhastClass("net.minecraft.server.v1_9_R1.EntityGhast"));
            load(V1_9_R1.class, "Entity:Guardian", new Guardian.GuardianClass("net.minecraft.server.v1_9_R1.EntityGuardian"));
            load(V1_9_R1.class, "Entity:Silverfish", new Silverfish.SilverfishClass("net.minecraft.server.v1_9_R1.EntitySilverfish"));
            load(V1_9_R1.class, "Entity:Skeleton", new Skeleton.SkeletonClass("net.minecraft.server.v1_9_R1.EntitySkeleton"));
            load(V1_9_R1.class, "Entity:Slime", new Slime.SlimeClass("net.minecraft.server.v1_9_R1.EntitySlime"));
            load(V1_9_R1.class, "Entity:Spider", new Spider.SpiderClass("net.minecraft.server.v1_9_R1.EntitySpider"));
            load(V1_9_R1.class, "Entity:Witch", new Witch.WitchClass("net.minecraft.server.v1_9_R1.EntityWitch"));
            load(V1_9_R1.class, "Entity:Zombie", new Zombie.ZombieClass("net.minecraft.server.v1_9_R1.EntityZombie"));
            load(V1_9_R1.class, "Entity:Villager", new Villager.VillagerClass("net.minecraft.server.v1_9_R1.EntityVillager"));
            load(V1_9_R1.class, "Entity:Shulker", new Shulker.ShulkerClass("net.minecraft.server.v1_9_R1.EntityShulker"));

            load(V1_9_R1.class, "Entity:Ageable", new AgeableLivingEntity.AgeableLivingEntityClass("net.minecraft.server.v1_9_R1.EntityAgeable"));
            load(V1_9_R1.class, "Entity:Tameable", new TameableLivingEntity.TameableLivingEntityClass("net.minecraft.server.v1_9_R1.EntityTameableAnimal"));
            // EntityPlayer
            load(V1_9_R1.class, "GameProfile", new GameProfile.GameProfileClass("com.mojang.authlib.GameProfile"));
            load(V1_9_R1.class, "PropertyMap", new PropertyMap.PropertyMapClass("com.mojang.authlib.properties.PropertyMap"));
            load(V1_9_R1.class, "Property", new Property.PropertyClass("com.mojang.authlib.properties.Property"));
            //

            // Managers
            load(V1_9_R1.class, "PlayerInteractManager", new PlayerInteractManager.PlayerInteractManagerClass("net.minecraft.server.v1_9_R1.PlayerInteractManager"));
            //

            // DataWatcher
            load(V1_9_R1.class, "DataWatcher", new DataWatcher.DataWatcherClass("net.minecraft.server.v1_9_R1.DataWatcher"));
            load(V1_9_R1.class, "DataWatcher:Item", new DataWatcherItem.DataWatcherItemClass("net.minecraft.server.v1_9_R1.DataWatcher$Item"));
            load(V1_9_R1.class, "DataWatcherObject", new DataWatcherObject.DataWatcherObjectClass("net.minecraft.server.v1_9_R1.DataWatcherObject"));
            //

            // Scoreboard
            load(V1_9_R1.class, "CraftScoreboard", new CraftScoreboard.CraftScoreboardClass("org.bukkit.craftbukkit.v1_9_R1.scoreboard.CraftScoreboard"));
            load(V1_9_R1.class, "Scoreboard", new Scoreboard.ScoreboardClass("net.minecraft.server.v1_9_R1.Scoreboard"));

            load(V1_9_R1.class, "ScoreboardTeam", new ScoreboardTeam.ScoreboardTeamClass("net.minecraft.server.v1_9_R1.ScoreboardTeam"));
            load(V1_9_R1.class, "ScoreboardTeam:EnumTeamPush", new EnumTeamPushEnum.EnumTeamPushClass("net.minecraft.server.v1_9_R1.ScoreboardTeamBase$EnumTeamPush"));

            load(V1_9_R1.class, "ScoreboardTeamBase:EnumNameTagVisibility", new EnumNameTagVisibilityEnum.EnumNameTagVisibilityClass("net.minecraft.server.v1_9_R1.ScoreboardTeamBase$EnumNameTagVisibility"));
            //

            // Others
            load(V1_9_R1.class, "PlayerConnection", new PlayerConnection.PlayerConnectionClass("net.minecraft.server.v1_9_R1.PlayerConnection"));
            load(V1_9_R1.class, "NetworkManager", new NetworkManager.NetworkManagerClass("net.minecraft.server.v1_9_R1.NetworkManager"));

            load(V1_9_R1.class, "EnumChatFormat", new EnumChatFormatEnum.EnumChatFormatClass("net.minecraft.server.v1_9_R1.EnumChatFormat"));
            load(V1_9_R1.class, "EnumColor", new EnumColorEnum.EnumColorClass("net.minecraft.server.v1_9_R1.EnumColor"));
            load(V1_9_R1.class, "EnumItemSlot", new EnumItemSlotEnum.EnumItemSlotClass("net.minecraft.server.v1_9_R1.EnumItemSlot"));
            load(V1_9_R1.class, "EnumDirection", new EnumDirectionEnum.EnumDirectionClass("net.minecraft.server.v1_9_R1.EnumDirection"));
            //

            // Chat
            load(V1_9_R1.class, "IChatBaseComponent", new IChatBaseComponent.IChatBaseComponentClass("net.minecraft.server.v1_9_R1.IChatBaseComponent"));
            load(V1_9_R1.class, "ChatSerializer", new IChatBaseComponent.ChatSerializerClass("net.minecraft.server.v1_9_R1.IChatBaseComponent$ChatSerializer"));
            //

            // Objects
            load(V1_9_R1.class, "CraftWorld", new CraftWorld.CraftWorldClass("org.bukkit.craftbukkit.v1_9_R1.CraftWorld"));
            load(V1_9_R1.class, "World", new World.WorldClass("net.minecraft.server.v1_9_R1.World"));
            load(V1_9_R1.class, "Vector3f", new Vector3f.Vector3fClass("net.minecraft.server.v1_9_R1.Vector3f"));
            load(V1_9_R1.class, "BlockPosition", new BlockPosition.BlockPositionClass("net.minecraft.server.v1_9_R1.BlockPosition"));
            load(V1_9_R1.class, "CraftBlock", new CraftBlock.CraftBlockClass("org.bukkit.craftbukkit.v1_9_R1.block.CraftBlock"));
            load(V1_9_R1.class, "IBlockData", new IBlockData.IBlockDataClass("net.minecraft.server.v1_9_R1.IBlockData"));
            load(V1_9_R1.class, "Block", new Block.BlockClass("net.minecraft.server.v1_9_R1.Block"));
            load(V1_9_R1.class, "CraftMagicNumbers", new ClassExecutor("org.bukkit.craftbukkit.v1_9_R1.util.CraftMagicNumbers"));
            //

            // Items
            load(V1_9_R1.class, "ItemStack", new ItemStack.ItemStackClass("net.minecraft.server.v1_9_R1.ItemStack"));
            load(V1_9_R1.class, "CraftItemStack", new CraftItemStack.CraftItemStackClass("org.bukkit.craftbukkit.v1_9_R1.inventory.CraftItemStack"));
            //

            // Entity horse
            load(V1_9_R1.class, "EnumHorseType", new EnumHorseTypeEnum.EnumHorseTypeClass("net.minecraft.server.v1_9_R1.EnumHorseType"));
            //
        }
        
        return super.getClasses();
    }

    @Override
    public @NotNull Map<String, MethodExecutor> getMethods() {
        if (!super.getMethods().containsKey("Entity:isGlowing")) {
            load(V1_9_R1.class, "Entity:isGlowing", new MethodExecutor(getClassExec("Entity"), ClassExecutor.BOOLEAN, "aM", "Gets the glowing state of a Entity"));
            load(V1_9_R1.class, "Entity:setGlowing", new MethodExecutor(getClassExec("Entity"), ClassExecutor.VOID, "f", "Sets the glowing state of a Entity", ClassExecutor.BOOLEAN));

            load(V1_9_R1.class, "ScoreboardTeam:getCollision", new MethodExecutor(getClassExec("ScoreboardTeam"), getClassExec("ScoreboardTeam:EnumTeamPush"), "k", "Gets the collision state of the ScoreboardTeam"));
            load(V1_9_R1.class, "ScoreboardTeam:setCollision", new MethodExecutor(getClassExec("ScoreboardTeam"), ClassExecutor.VOID, "a", "Sets the collision state of the ScoreboardTeam", getClassExec("ScoreboardTeam:EnumTeamPush")));

            load(V1_9_R1.class, "Entity:Snowman:hasPumpkinHat", new MethodExecutor(getClassExec("Entity:Snowman"), ClassExecutor.BOOLEAN, "o", "Gets the pumpkin hat state of a Snowman"));
            load(V1_9_R1.class, "Entity:Snowman:setPumpkinHat", new MethodExecutor(getClassExec("Entity:Snowman"), ClassExecutor.VOID, "a", "Sets the pumpkin hat state of a Snowman", ClassExecutor.BOOLEAN));

            load(V1_9_R1.class, "DataWatcher:get:DataWatcherObject", new MethodExecutor(getClassExec("DataWatcher"), ClassExecutor.OBJECT, "get", "Gets a datawatcher value by it object", getClassExec("DataWatcherObject")));
            load(V1_9_R1.class, "DataWatcher:set:DataWatcherObject", new MethodExecutor(getClassExec("DataWatcher"), ClassExecutor.VOID, "set", "Sets a datawatcher value of a object", getClassExec("DataWatcherObject"), ClassExecutor.OBJECT));

            load(V1_9_R1.class, "Entity:Zombie:getVillagerType", new MethodExecutor(getClassExec("Entity:Zombie"), ClassExecutor.INT, "getVillagerType", "Gets the villager type of a zombie"));

            load(V1_9_R1.class, "DataWatcher:Item:get", new MethodExecutor(getClassExec("DataWatcher:Item"), ClassExecutor.OBJECT, "b", "Gets the value of a datawatcher item"));
            load(V1_9_R1.class, "DataWatcher:Item:set", new MethodExecutor(getClassExec("DataWatcher:Item"), ClassExecutor.VOID, "a", "Sets the value of a datawatcher item", ClassExecutor.OBJECT));

            load(V1_9_R1.class, "DataWatcherObject:getId", new MethodExecutor(getClassExec("DataWatcherObject"), ClassExecutor.INT, "a", "Gets the DataWatcher object's id"));
        }

        return super.getMethods();
    }

    @Override
    public @NotNull Map<String, FieldExecutor> getFields() {
        if (!super.getFields().containsKey("Entity:Enderman:DataWatcher:screaming")) {
            load(V1_9_R1.class, "Entity:Enderman:DataWatcher:screaming", new FieldExecutor(getClassExec("Entity:Enderman"), getClassExec("DataWatcherObject"), "bw", "Gets the enderman's screaming DataWatcher's object"));

            load(V1_9_R1.class, "Entity:Shulker:DataWatcherObject:Direction", new FieldExecutor(getClassExec("Entity:Shulker"), getClassExec("DataWatcherObject"), "a", "Get shulker's direction DataWatcher's object"));
            load(V1_9_R1.class, "Entity:Shulker:DataWatcherObject:Peek", new FieldExecutor(getClassExec("Entity:Shulker"), getClassExec("DataWatcherObject"), "c", "Get shulker's color DataWatcher's object"));

            load(V1_9_R1.class, "Metadata:Ghast:Attacking", new FieldExecutor(getClassExec("Entity:Ghast"), getClassExec("DataWatcherObject"), "a", "Gets the Ghast attacking DataWatcherObject"));
            load(V1_9_R1.class, "Metadata:Guardian:Target", new FieldExecutor(getClassExec("Entity:Guardian"), getClassExec("DataWatcherObject"), "b", "Gets the Guardian target DataWatcherObject"));
            load(V1_9_R1.class, "Metadata:Creeper:Ignited", new FieldExecutor(getClassExec("Entity:Creeper"), getClassExec("DataWatcherObject"), "c", "Gets the Creeper ignited DataWatcherObject"));
        }

        return super.getFields();
    }

    @Override
    public @NotNull Map<String, EnumExecutor> getEnums() {
        if (!super.getEnums().containsKey("ScoreboardTeam:EnumTeamPush")) {
            // Others
            load(V1_9_R1.class, "EnumItemSlot", new EnumItemSlotEnum(getClassExec("EnumItemSlot")));
            load(V1_9_R1.class, "EnumDirection", new EnumDirectionEnum(getClassExec("EnumDirection")));
            // Scoreboard
            load(V1_9_R1.class, "ScoreboardTeam:EnumTeamPush", new EnumTeamPushEnum(getClassExec("ScoreboardTeam:EnumTeamPush")));
            // Entity horse
            load(V1_9_R1.class, "EnumHorseType", new EnumHorseTypeEnum(getClassExec("EnumHorseType")));
            //
        }

        return super.getEnums();
    }

    @Override
    public @NotNull Map<String, String> getTexts() {
        if (!super.getTexts().containsKey("EnumHorseType:HORSE")) {
            super.getTexts().put("EnumHorseType:HORSE", "HORSE");
            super.getTexts().put("EnumHorseType:DONKEY", "DONKEY");
            super.getTexts().put("EnumHorseType:MULE", "MULE");
            super.getTexts().put("EnumHorseType:ZOMBIE", "ZOMBIE");
            super.getTexts().put("EnumHorseType:SKELETON", "SKELETON");

            super.getTexts().put("EnumItemSlot:MAINHAND", "MAINHAND");
            super.getTexts().put("EnumItemSlot:OFFHAND", "OFFHAND");
            super.getTexts().put("EnumItemSlot:HEAD", "HEAD");
            super.getTexts().put("EnumItemSlot:CHEST", "CHEST");
            super.getTexts().put("EnumItemSlot:LEGS", "LEGS");
            super.getTexts().put("EnumItemSlot:FEET", "FEET");
        }

        return super.getTexts();
    }

    @Override
    public @NotNull Map<String, Object> getObjects() {
        Map<String, Object> map = super.getObjects();

        super.getObjects().put("Metadata:Player:SkinParts", 13);

        return map;
    }

    @Override
    public @NotNull String getName() {
        return "v1_9_R1";
    }

}
