package codes.laivy.npc.mappings.versions;

import codes.laivy.npc.mappings.Version;
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
import codes.laivy.npc.mappings.defaults.classes.entity.monster.illagers.Evoker;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.illagers.IllagerWizard;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.illagers.Illusioner;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.illagers.Vindicator;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.skeleton.Skeleton;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.skeleton.SkeletonStray;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.skeleton.SkeletonWither;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.zombie.Zombie;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.zombie.ZombieHusk;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.zombie.ZombieVillager;
import codes.laivy.npc.mappings.defaults.classes.entity.npc.Villager;
import codes.laivy.npc.mappings.defaults.classes.entity.player.EntityPlayer;
import codes.laivy.npc.mappings.defaults.classes.entity.vehicle.Boat;
import codes.laivy.npc.mappings.defaults.classes.enums.*;
import codes.laivy.npc.mappings.defaults.classes.gameprofile.GameProfile;
import codes.laivy.npc.mappings.defaults.classes.gameprofile.Property;
import codes.laivy.npc.mappings.defaults.classes.gameprofile.PropertyMap;
import codes.laivy.npc.mappings.defaults.classes.java.IntegerObjExec;
import codes.laivy.npc.mappings.defaults.classes.nbt.NBTBase;
import codes.laivy.npc.mappings.defaults.classes.nbt.tags.*;
import codes.laivy.npc.mappings.defaults.classes.others.chat.IChatBaseComponent;
import codes.laivy.npc.mappings.defaults.classes.others.location.*;
import codes.laivy.npc.mappings.defaults.classes.others.managers.PlayerInteractManager;
import codes.laivy.npc.mappings.defaults.classes.others.objects.*;
import codes.laivy.npc.mappings.defaults.classes.others.server.CraftServer;
import codes.laivy.npc.mappings.defaults.classes.others.server.MinecraftServer;
import codes.laivy.npc.mappings.defaults.classes.packets.*;
import codes.laivy.npc.mappings.defaults.classes.packets.info.legacy.PlayerInfoPacket;
import codes.laivy.npc.mappings.defaults.classes.scoreboard.CraftScoreboard;
import codes.laivy.npc.mappings.defaults.classes.scoreboard.Scoreboard;
import codes.laivy.npc.mappings.defaults.classes.scoreboard.ScoreboardTeam;
import codes.laivy.npc.mappings.instances.EnumExecutor;
import codes.laivy.npc.mappings.instances.Executor;
import codes.laivy.npc.mappings.instances.FieldExecutor;
import codes.laivy.npc.mappings.instances.MethodExecutor;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class V1_12_R1 extends V1_11_R1 {

    @Override
    protected boolean onLoad(@NotNull Class<? extends Version> version, @NotNull String key, @NotNull Executor executor) {
        if (version == V1_11_R1.class) {
            if (executor instanceof ClassExecutor && !(executor instanceof EnumExecutor)) {
                return false;
            } else if (executor instanceof FieldExecutor) {
                switch (key) {
                    case "Metadata:Enderman:screaming":
                        load(V1_12_R1.class, key, new FieldExecutor(getClassExec("Entity:Enderman"), getClassExec("DataWatcherObject"), "by", "Gets the enderman's screaming datawatcher object"));
                        return false;
                    case "Metadata:Guardian:Target":
                        load(V1_12_R1.class, key, new FieldExecutor(getClassExec("Entity:Guardian"), getClassExec("DataWatcherObject"), "bB", "Gets the Guardian target DataWatcherObject"));
                        return false;
                    case "Metadata:Shulker:Color":
                        load(V1_12_R1.class, key, new FieldExecutor(getClassExec("Entity:Shulker"), getClassExec("DataWatcherObject"), "COLOR", "Gets the shulker's color DataWatcherObject"));
                        return false;
                    case "Metadata:Horse:Armor":
                        load(V1_12_R1.class, key, new FieldExecutor(getClassExec("Entity:Horse"), getClassExec("DataWatcherObject"), "bJ", "Gets the horse armor DataWatcherObject"));
                        return false;
                    case "Metadata:Horse:Variant":
                        load(V1_12_R1.class, key, new FieldExecutor(getClassExec("Entity:Horse"), getClassExec("DataWatcherObject"), "bI", "Gets the horse variant DataWatcherObject"));
                        return false;
                    case "Metadata:Llama:CarpetColor":
                        load(V1_12_R1.class, key, new FieldExecutor(getClassExec("Entity:Llama"), getClassExec("DataWatcherObject"), "bI", "Gets the llama's carpet color DataWatcherObject"));
                        return false;
                    case "Metadata:Llama:Variant":
                        load(V1_12_R1.class, key, new FieldExecutor(getClassExec("Entity:Llama"), getClassExec("DataWatcherObject"), "bJ", "Gets the llama's variant DataWatcherObject"));
                        return false;
                    case "Metadata:Zombie:Baby":
                        load(V1_12_R1.class, key, new FieldExecutor(getClassExec("Entity:Zombie"), getClassExec("DataWatcherObject"), "bx", "Gets the zombie baby DataWatcherObject"));
                        return false;
                    case "Metadata:PolarBear:Standing":
                        load(V1_12_R1.class, key, new FieldExecutor(getClassExec("Entity:PolarBear"), getClassExec("DataWatcherObject"), "bx", "Gets the polar bear's standing DataWatcherObject"));
                        return false;
                    case "Metadata:Pig:Saddle":
                        load(V1_12_R1.class, key, new FieldExecutor(getClassExec("Entity:Pig"), getClassExec("DataWatcherObject"), "bx", "Gets the pig saddle DataWatcherObject"));
                        return false;
                    case "Metadata:Wolf:Angry":
                        load(V1_12_R1.class, key, new FieldExecutor(getClassExec("Entity:Wolf"), getClassExec("DataWatcherObject"), "bC", "Gets the wolf angry DataWatcherObject"));
                        return false;
                    case "Metadata:Wolf:CollarColor":
                        load(V1_12_R1.class, key, new FieldExecutor(getClassExec("Entity:Wolf"), getClassExec("DataWatcherObject"), "bD", "Gets the wolf collar color DataWatcherObject"));
                        return false;
                    case "Metadata:IllagerWizard:Spell":
                        return false;
                    default:
                        break;
                }
            }
        } else if (version == V1_8_R1.class) {
            if (executor instanceof MethodExecutor) {
                if (key.equals("ScoreboardTeam:setColor")) {
                    load(V1_12_R1.class, key, new MethodExecutor(getClassExec("ScoreboardTeam"), ClassExecutor.VOID, "setColor", "Sets the color of a ScoreboardTeam", getClassExec("EnumChatFormat")));
                    return false;
                }
            }
        } else if (version == V1_10_R1.class) {
            if (executor instanceof MethodExecutor) {
                if (key.equals("Entity:isGlowing")) {
                    load(V1_12_R1.class, key, new MethodExecutor(getClassExec("Entity"), ClassExecutor.BOOLEAN, "aW", "Gets the glowing state of a Entity"));
                    return false;
                }
            }
        }

        return super.onLoad(version, key, executor);
    }

    @Override
    public @Nullable Entity getEntityInstance(Entity.@NotNull EntityType type, @NotNull org.bukkit.Location location) {
        Entity entity = super.getEntityInstance(type, location);
        if (type == Entity.EntityType.ILLUSIONER) {
            Object object = getClassExec("Entity:Illusioner").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new Illusioner(object);
        } else if (type == Entity.EntityType.PARROT) {
            Object object = getClassExec("Entity:Parrot").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new Parrot(object);
        }

        return entity;
    }

    @Override
    public void setEntityIllagerWizardSpell(@NotNull IllagerWizard wizard, EnumSpellEnum.@NotNull Spell spell) {
        getMethodExec("Entity:IllagerWizard:setSpell").invokeInstance(wizard, spell.getEnum());
    }
    @Override
    public @NotNull EnumSpellEnum.Spell getEntityIllagerWizardSpell(@NotNull IllagerWizard wizard) {
        return EnumSpellEnum.Spell.fromEnum(new EnumSpellEnum.EnumSpellExec((Enum<?>) Objects.requireNonNull(getMethodExec("Entity:IllagerWizard:getSpell").invokeInstance(wizard))));
    }

    @Override
    public void setEntityParrotVariant(@NotNull Parrot parrot, Parrot.@NotNull Variant variant) {
        getMethodExec("Entity:Parrot:setVariant").invokeInstance(parrot, new IntegerObjExec(variant.getData()));
    }

    @Override
    public @NotNull Parrot.Variant getEntityParrotVariant(@NotNull Parrot parrot) {
        //noinspection DataFlowIssue
        return Parrot.Variant.getByData((int) getMethodExec("Entity:Parrot:getVariant").invokeInstance(parrot));
    }

    @Override
    public void loadClasses() {
        load(V1_12_R1.class, "WatchableObject", new ClassExecutor.BrokenClassExecutor());
        load(V1_12_R1.class, "EnumSkeletonType", new ClassExecutor.BrokenClassExecutor());
        load(V1_12_R1.class, "EnumHorseType", new ClassExecutor.BrokenClassExecutor());
        load(V1_12_R1.class, "EnumZombieType", new ClassExecutor.BrokenClassExecutor());

        load(V1_12_R1.class, "NBTBase", new NBTBase.NBTBaseClass("net.minecraft.server.v1_12_R1.NBTBase"));

        load(V1_12_R1.class, "NBTBase:NBTTagByte", new NBTTagByte.NBTTagByteClass("net.minecraft.server.v1_12_R1.NBTTagByte"));
        load(V1_12_R1.class, "NBTBase:NBTTagByteArray", new NBTTagByteArray.NBTTagByteArrayClass("net.minecraft.server.v1_12_R1.NBTTagByteArray"));
        load(V1_12_R1.class, "NBTBase:NBTTagCompound", new NBTTagCompound.NBTTagCompoundClass("net.minecraft.server.v1_12_R1.NBTTagCompound"));
        load(V1_12_R1.class, "NBTBase:NBTTagDouble", new NBTTagDouble.NBTTagDoubleClass("net.minecraft.server.v1_12_R1.NBTTagDouble"));
        load(V1_12_R1.class, "NBTBase:NBTTagFloat", new NBTTagFloat.NBTTagFloatClass("net.minecraft.server.v1_12_R1.NBTTagFloat"));
        load(V1_12_R1.class, "NBTBase:NBTTagInt", new NBTTagInt.NBTTagIntClass("net.minecraft.server.v1_12_R1.NBTTagInt"));
        load(V1_12_R1.class, "NBTBase:NBTTagIntArray", new NBTTagIntArray.NBTTagIntArrayClass("net.minecraft.server.v1_12_R1.NBTTagIntArray"));
        load(V1_12_R1.class, "NBTBase:NBTTagList", new NBTTagList.NBTTagListClass("net.minecraft.server.v1_12_R1.NBTTagList"));
        load(V1_12_R1.class, "NBTBase:NBTTagLong", new NBTTagLong.NBTTagLongClass("net.minecraft.server.v1_12_R1.NBTTagLong"));
        load(V1_12_R1.class, "NBTBase:NBTTagShort", new NBTTagShort.NBTTagShortClass("net.minecraft.server.v1_12_R1.NBTTagShort"));
        load(V1_12_R1.class, "NBTBase:NBTTagString", new NBTTagString.NBTTagStringClass("net.minecraft.server.v1_12_R1.NBTTagString"));
        //

        // Packets
        load(V1_12_R1.class, "Packet", new Packet.PacketClass("net.minecraft.server.v1_12_R1.Packet"));
        load(V1_12_R1.class, "PacketPlayOutSpawnEntity", new EntitySpawnPacket.EntitySpawnPacketClass("net.minecraft.server.v1_12_R1.PacketPlayOutSpawnEntity"));
        load(V1_12_R1.class, "PacketPlayOutEntityDestroy", new EntityDestroyPacket.EntityDestroyPacketClass("net.minecraft.server.v1_12_R1.PacketPlayOutEntityDestroy"));
        load(V1_12_R1.class, "PacketPlayOutAnimation", new EntityAnimationPacket.EntityAnimationPacketClass("net.minecraft.server.v1_12_R1.PacketPlayOutAnimation"));
        load(V1_12_R1.class, "PacketPlayOutSpawnEntityLiving", new EntityLivingSpawnPacket.EntityLivingSpawnPacketClass("net.minecraft.server.v1_12_R1.PacketPlayOutSpawnEntityLiving"));
        load(V1_12_R1.class, "PacketPlayOutEntityMetadata", new EntityMetadataPacket.EntityMetadataPacketClass("net.minecraft.server.v1_12_R1.PacketPlayOutEntityMetadata"));
        load(V1_12_R1.class, "PacketPlayOutNamedEntitySpawn", new EntityNamedSpawnPacket.EntityNamedSpawnPacketClass("net.minecraft.server.v1_12_R1.PacketPlayOutNamedEntitySpawn"));
        load(V1_12_R1.class, "PacketPlayOutPlayerInfo", new PlayerInfoPacket.PlayerInfoPacketClass("net.minecraft.server.v1_12_R1.PacketPlayOutPlayerInfo"));
        load(V1_12_R1.class, "PacketPlayOutPlayerInfo:EnumPlayerInfoAction", new EnumPlayerInfoActionEnum.EnumPlayerInfoActionClass("net.minecraft.server.v1_12_R1.PacketPlayOutPlayerInfo$EnumPlayerInfoAction"));
        load(V1_12_R1.class, "PacketPlayOutScoreboardTeam", new ScoreboardTeamPacket.ScoreboardTeamPacketClass("net.minecraft.server.v1_12_R1.PacketPlayOutScoreboardTeam"));
        load(V1_12_R1.class, "PacketPlayOutEntityEquipment", new EntityEquipmentPacket.EntityEquipmentPacketClass("net.minecraft.server.v1_12_R1.PacketPlayOutEntityEquipment"));
        load(V1_12_R1.class, "PacketPlayOutEntityTeleport", new EntityTeleportPacket.EntityTeleportPacketClass("net.minecraft.server.v1_12_R1.PacketPlayOutEntityTeleport"));
        load(V1_12_R1.class, "PacketPlayOutEntityHeadRotation", new EntityHeadRotationPacket.EntityHeadRotationPacketClass("net.minecraft.server.v1_12_R1.PacketPlayOutEntityHeadRotation"));
        load(V1_12_R1.class, "PacketPlayOutEntityLook", new EntityLookPacket.EntityLookPacketClass("net.minecraft.server.v1_12_R1.PacketPlayOutEntity$PacketPlayOutEntityLook"));

        load(V1_12_R1.class, "PacketPlayInUseEntity", new EntityUseInPacket.EntityUseInPacketClass("net.minecraft.server.v1_12_R1.PacketPlayInUseEntity"));
        load(V1_12_R1.class, "PacketPlayInUseEntity:EnumEntityUseAction", new EntityUseInPacket.ActionEnum.ActionClass("net.minecraft.server.v1_12_R1.PacketPlayInUseEntity$EnumEntityUseAction"));
        //

        // Server
        load(V1_12_R1.class, "MinecraftServer", new MinecraftServer.MinecraftServerClass("net.minecraft.server.v1_12_R1.MinecraftServer"));
        load(V1_12_R1.class, "WorldServer", new WorldServer.WorldServerClass("net.minecraft.server.v1_12_R1.WorldServer"));
        load(V1_12_R1.class, "CraftServer", new CraftServer.CraftServerClass("org.bukkit.craftbukkit.v1_12_R1.CraftServer"));
        //

        // Entity
        load(V1_12_R1.class, "Entity", new Entity.EntityClass("net.minecraft.server.v1_12_R1.Entity"));
        load(V1_12_R1.class, "EntityLiving", new EntityLiving.EntityLivingClass("net.minecraft.server.v1_12_R1.EntityLiving"));
        load(V1_12_R1.class, "Entity:Human", new Entity.EntityClass("net.minecraft.server.v1_12_R1.EntityHuman"));
        load(V1_12_R1.class, "CraftPlayer", new CraftPlayer.CraftPlayerClass("org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer"));
        load(V1_12_R1.class, "EntityPlayer", new EntityPlayer.EntityPlayerClass("net.minecraft.server.v1_12_R1.EntityPlayer"));

        load(V1_12_R1.class, "Entity:ArmorStand", new ArmorStand.ArmorStandClass("net.minecraft.server.v1_12_R1.EntityArmorStand"));
        load(V1_12_R1.class, "Entity:Pig", new Pig.PigClass("net.minecraft.server.v1_12_R1.EntityPig"));
        load(V1_12_R1.class, "Entity:Cow", new Cow.CowClass("net.minecraft.server.v1_12_R1.EntityCow"));
        load(V1_12_R1.class, "Entity:Ocelot", new Ocelot.OcelotClass("net.minecraft.server.v1_12_R1.EntityOcelot"));
        load(V1_12_R1.class, "Entity:Bat", new Bat.BatClass("net.minecraft.server.v1_12_R1.EntityBat"));
        load(V1_12_R1.class, "Entity:Egg", new Egg.EggClass("net.minecraft.server.v1_12_R1.EntityEgg"));
        load(V1_12_R1.class, "Entity:Chicken", new Chicken.ChickenClass("net.minecraft.server.v1_12_R1.EntityChicken"));
        load(V1_12_R1.class, "Entity:Horse", new AbstractHorse.AbstractHorseClass("net.minecraft.server.v1_12_R1.EntityHorse"));
        load(V1_12_R1.class, "Entity:IronGolem", new IronGolem.IronGolemClass("net.minecraft.server.v1_12_R1.EntityIronGolem"));
        load(V1_12_R1.class, "Entity:Rabbit", new Rabbit.RabbitClass("net.minecraft.server.v1_12_R1.EntityRabbit"));
        load(V1_12_R1.class, "Entity:Sheep", new Sheep.SheepClass("net.minecraft.server.v1_12_R1.EntitySheep"));
        load(V1_12_R1.class, "Entity:Snowman", new Snowman.SnowmanClass("net.minecraft.server.v1_12_R1.EntitySnowman"));
        load(V1_12_R1.class, "Entity:Squid", new Squid.SquidClass("net.minecraft.server.v1_12_R1.EntitySquid"));
        load(V1_12_R1.class, "Entity:Wolf", new Wolf.WolfClass("net.minecraft.server.v1_12_R1.EntityWolf"));
        load(V1_12_R1.class, "Entity:ItemFrame", new ItemFrame.ItemFrameClass("net.minecraft.server.v1_12_R1.EntityItemFrame"));
        load(V1_12_R1.class, "Entity:LeashKnot", new LeashKnot.LeashKnotClass("net.minecraft.server.v1_12_R1.EntityLeash"));
        load(V1_12_R1.class, "Entity:FallingBlock", new FallingBlock.FallingBlockClass("net.minecraft.server.v1_12_R1.EntityFallingBlock"));
        load(V1_12_R1.class, "Entity:Item", new Item.ItemClass("net.minecraft.server.v1_12_R1.EntityItem"));
        load(V1_12_R1.class, "Entity:EnderDragon", new EnderDragon.EnderDragonClass("net.minecraft.server.v1_12_R1.EntityEnderDragon"));
        load(V1_12_R1.class, "Entity:EnderSignal", new EnderSignal.EnderSignalClass("net.minecraft.server.v1_12_R1.EntityEnderSignal"));
        load(V1_12_R1.class, "Entity:Wither", new Wither.WitherClass("net.minecraft.server.v1_12_R1.EntityWither"));
        load(V1_12_R1.class, "Entity:WitherSkull", new WitherSkull.WitherSkullClass("net.minecraft.server.v1_12_R1.EntityWitherSkull"));
        load(V1_12_R1.class, "Entity:Blaze", new Blaze.BlazeClass("net.minecraft.server.v1_12_R1.EntityBlaze"));
        load(V1_12_R1.class, "Entity:Creeper", new Creeper.CreeperClass("net.minecraft.server.v1_12_R1.EntityCreeper"));
        load(V1_12_R1.class, "Entity:Enderman", new Enderman.EndermanClass("net.minecraft.server.v1_12_R1.EntityEnderman"));
        load(V1_12_R1.class, "Entity:Ghast", new Ghast.GhastClass("net.minecraft.server.v1_12_R1.EntityGhast"));
        load(V1_12_R1.class, "Entity:Guardian", new Guardian.GuardianClass("net.minecraft.server.v1_12_R1.EntityGuardian"));
        load(V1_12_R1.class, "Entity:Silverfish", new Silverfish.SilverfishClass("net.minecraft.server.v1_12_R1.EntitySilverfish"));
        load(V1_12_R1.class, "Entity:Skeleton", new Skeleton.SkeletonClass("net.minecraft.server.v1_12_R1.EntitySkeleton"));
        load(V1_12_R1.class, "Entity:Slime", new Slime.SlimeClass("net.minecraft.server.v1_12_R1.EntitySlime"));
        load(V1_12_R1.class, "Entity:Spider", new Spider.SpiderClass("net.minecraft.server.v1_12_R1.EntitySpider"));
        load(V1_12_R1.class, "Entity:Witch", new Witch.WitchClass("net.minecraft.server.v1_12_R1.EntityWitch"));
        load(V1_12_R1.class, "Entity:Zombie", new Zombie.ZombieClass("net.minecraft.server.v1_12_R1.EntityZombie"));
        load(V1_12_R1.class, "Entity:Villager", new Villager.VillagerClass("net.minecraft.server.v1_12_R1.EntityVillager"));
        load(V1_12_R1.class, "Entity:Shulker", new Shulker.ShulkerClass("net.minecraft.server.v1_12_R1.EntityShulker"));
        load(V1_12_R1.class, "Entity:PolarBear", new PolarBear.PolarBearClass("net.minecraft.server.v1_12_R1.EntityPolarBear"));
        load(V1_12_R1.class, "Entity:Boat", new Boat.BoatClass("net.minecraft.server.v1_12_R1.EntityBoat"));
        load(V1_12_R1.class, "Entity:CaveSpider", new CaveSpider.CaveSpiderClass("net.minecraft.server.v1_12_R1.EntityCaveSpider"));

        load(V1_12_R1.class, "Entity:Ageable", new AgeableEntityLiving.AgeableEntityLivingClass("net.minecraft.server.v1_12_R1.EntityAgeable"));
        load(V1_12_R1.class, "Entity:Tameable", new TameableEntityLiving.TameableEntityLivingClass("net.minecraft.server.v1_12_R1.EntityTameableAnimal"));
        // EntityPlayer
        load(V1_12_R1.class, "GameProfile", new GameProfile.GameProfileClass("com.mojang.authlib.GameProfile"));
        load(V1_12_R1.class, "PropertyMap", new PropertyMap.PropertyMapClass("com.mojang.authlib.properties.PropertyMap"));
        load(V1_12_R1.class, "Property", new Property.PropertyClass("com.mojang.authlib.properties.Property"));
        //

        // Managers
        load(V1_12_R1.class, "PlayerInteractManager", new PlayerInteractManager.PlayerInteractManagerClass("net.minecraft.server.v1_12_R1.PlayerInteractManager"));
        //

        // DataWatcher
        load(V1_12_R1.class, "DataWatcher", new DataWatcher.DataWatcherClass("net.minecraft.server.v1_12_R1.DataWatcher"));
        load(V1_12_R1.class, "DataWatcherObject", new DataWatcherObject.DataWatcherObjectClass("net.minecraft.server.v1_12_R1.DataWatcherObject"));
        load(V1_12_R1.class, "DataWatcher:Item", new DataWatcherItem.DataWatcherItemClass("net.minecraft.server.v1_12_R1.DataWatcher$Item"));
        //

        // Scoreboard
        load(V1_12_R1.class, "CraftScoreboard", new CraftScoreboard.CraftScoreboardClass("org.bukkit.craftbukkit.v1_12_R1.scoreboard.CraftScoreboard"));
        load(V1_12_R1.class, "Scoreboard", new Scoreboard.ScoreboardClass("net.minecraft.server.v1_12_R1.Scoreboard"));

        load(V1_12_R1.class, "ScoreboardTeam", new ScoreboardTeam.ScoreboardTeamClass("net.minecraft.server.v1_12_R1.ScoreboardTeam"));
        load(V1_12_R1.class, "ScoreboardTeam:EnumTeamPush", new EnumTeamPushEnum.EnumTeamPushClass("net.minecraft.server.v1_12_R1.ScoreboardTeamBase$EnumTeamPush"));

        load(V1_12_R1.class, "ScoreboardTeamBase:EnumNameTagVisibility", new EnumNameTagVisibilityEnum.EnumNameTagVisibilityClass("net.minecraft.server.v1_12_R1.ScoreboardTeamBase$EnumNameTagVisibility"));
        //

        // Others
        load(V1_12_R1.class, "PlayerConnection", new PlayerConnection.PlayerConnectionClass("net.minecraft.server.v1_12_R1.PlayerConnection"));
        load(V1_12_R1.class, "NetworkManager", new NetworkManager.NetworkManagerClass("net.minecraft.server.v1_12_R1.NetworkManager"));

        load(V1_12_R1.class, "EnumChatFormat", new EnumChatFormatEnum.EnumChatFormatClass("net.minecraft.server.v1_12_R1.EnumChatFormat"));
        load(V1_12_R1.class, "EnumColor", new EnumColorEnum.EnumColorClass("net.minecraft.server.v1_12_R1.EnumColor"));
        load(V1_12_R1.class, "EnumItemSlot", new EnumItemSlotEnum.EnumItemSlotClass("net.minecraft.server.v1_12_R1.EnumItemSlot"));
        load(V1_12_R1.class, "EnumDirection", new EnumDirectionEnum.EnumDirectionClass("net.minecraft.server.v1_12_R1.EnumDirection"));
        //

        // Chat
        load(V1_12_R1.class, "IChatBaseComponent", new IChatBaseComponent.IChatBaseComponentClass("net.minecraft.server.v1_12_R1.IChatBaseComponent"));
        load(V1_12_R1.class, "ChatSerializer", new IChatBaseComponent.ChatSerializerClass("net.minecraft.server.v1_12_R1.IChatBaseComponent$ChatSerializer"));
        //

        // Objects
        load(V1_12_R1.class, "CraftWorld", new CraftWorld.CraftWorldClass("org.bukkit.craftbukkit.v1_12_R1.CraftWorld"));
        load(V1_12_R1.class, "World", new World.WorldClass("net.minecraft.server.v1_12_R1.World"));
        load(V1_12_R1.class, "Vector3f", new Vector3f.Vector3fClass("net.minecraft.server.v1_12_R1.Vector3f"));
        load(V1_12_R1.class, "Vec3D", new Vec3D.Vec3DClass("net.minecraft.server.v1_12_R1.Vec3D"));
        load(V1_12_R1.class, "BlockPosition", new BlockPosition.BlockPositionClass("net.minecraft.server.v1_12_R1.BlockPosition"));
        load(V1_12_R1.class, "CraftBlock", new CraftBlock.CraftBlockClass("org.bukkit.craftbukkit.v1_12_R1.block.CraftBlock"));
        load(V1_12_R1.class, "IBlockData", new IBlockData.IBlockDataClass("net.minecraft.server.v1_12_R1.IBlockData"));
        load(V1_12_R1.class, "Block", new Block.BlockClass("net.minecraft.server.v1_12_R1.Block"));
        load(V1_12_R1.class, "CraftMagicNumbers", new ClassExecutor("org.bukkit.craftbukkit.v1_12_R1.util.CraftMagicNumbers"));
        //

        // Items
        load(V1_12_R1.class, "ItemStack", new ItemStack.ItemStackClass("net.minecraft.server.v1_12_R1.ItemStack"));
        load(V1_12_R1.class, "CraftItemStack", new CraftItemStack.CraftItemStackClass("org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack"));
        //

        // Entity horse
        load(V1_12_R1.class, "Entity:Horse:Abstract", new AbstractHorse.AbstractHorseClass("net.minecraft.server.v1_12_R1.EntityHorseAbstract"));
        load(V1_12_R1.class, "Entity:Horse:Abstract:Chested", new AbstractChestedHorse.AbstractChestedHorseClass("net.minecraft.server.v1_12_R1.EntityHorseChestedAbstract"));
        load(V1_12_R1.class, "Entity:Horse:Donkey", new HorseDonkey.HorseDonkeyClass("net.minecraft.server.v1_12_R1.EntityHorseDonkey"));
        load(V1_12_R1.class, "Entity:Horse:Mule", new HorseMule.HorseMuleClass("net.minecraft.server.v1_12_R1.EntityHorseMule"));
        load(V1_12_R1.class, "Entity:Horse:Skeleton", new HorseSkeleton.HorseSkeletonClass("net.minecraft.server.v1_12_R1.EntityHorseSkeleton"));
        load(V1_12_R1.class, "Entity:Horse:Zombie", new HorseZombie.HorseZombieClass("net.minecraft.server.v1_12_R1.EntityHorseZombie"));
        // Entity skeleton
        load(V1_12_R1.class, "Entity:Skeleton:Wither", new SkeletonWither.SkeletonWitherClass("net.minecraft.server.v1_12_R1.EntitySkeletonWither"));
        load(V1_12_R1.class, "Entity:Skeleton:Stray", new SkeletonStray.SkeletonStrayClass("net.minecraft.server.v1_12_R1.EntitySkeletonStray"));
        // Entity zombie
        load(V1_12_R1.class, "Entity:Zombie:Villager", new ZombieVillager.ZombieVillagerClass("net.minecraft.server.v1_12_R1.EntityZombieVillager"));
        load(V1_12_R1.class, "Entity:Zombie:Husk", new ZombieHusk.ZombieHuskClass("net.minecraft.server.v1_12_R1.EntityZombieHusk"));
        // Entity vindicator
        load(V1_12_R1.class, "Entity:Vindicator", new Vindicator.VindicatorClass("net.minecraft.server.v1_12_R1.EntityVindicator"));
        // Entity evoker
        load(V1_12_R1.class, "Entity:Evoker", new Evoker.EvokerClass("net.minecraft.server.v1_12_R1.EntityEvoker"));
        // Entity vex
        load(V1_12_R1.class, "Entity:Vex", new Vex.VexClass("net.minecraft.server.v1_12_R1.EntityVex"));
        // Entity llama
        load(V1_12_R1.class, "Entity:Llama", new Llama.LlamaClass("net.minecraft.server.v1_12_R1.EntityLlama"));
        // Entity illager illusioner
        load(V1_12_R1.class, "Entity:Illusioner", new Illusioner.IllusionerClass("net.minecraft.server.v1_12_R1.EntityIllagerIllusioner"));
        // Entity illager wizard
        load(V1_12_R1.class, "Entity:IllagerWizard", new IllagerWizard.IllagerWizardClass("net.minecraft.server.v1_12_R1.EntityIllagerWizard"));
        load(V1_12_R1.class, "Entity:IllagerWizard:Spell", new EnumSpellEnum.EnumSpellClass("net.minecraft.server.v1_12_R1.EntityIllagerWizard$Spell"));
        // Entity parrot
        load(V1_12_R1.class, "Entity:Parrot", new Parrot.ParrotClass("net.minecraft.server.v1_12_R1.EntityParrot"));
        //
    }

    @Override
    public void loadMethods() {
        super.loadMethods();

        load(V1_12_R1.class, "Entity:IllagerWizard:setSpell", new MethodExecutor(getClassExec("Entity:IllagerWizard"), ClassExecutor.VOID, "setSpell", "Sets the spell of a wizard illusioner", getClassExec("Entity:IllagerWizard:Spell")));
        load(V1_12_R1.class, "Entity:IllagerWizard:getSpell", new MethodExecutor(getClassExec("Entity:IllagerWizard"), getClassExec("Entity:IllagerWizard:Spell"), "getSpell", "Gets the spell of a wizard illusioner"));

        load(V1_12_R1.class, "Entity:Parrot:setVariant", new MethodExecutor(getClassExec("Entity:Parrot"), ClassExecutor.VOID, "setVariant", "Sets the variant of a parrot", ClassExecutor.INT));
        load(V1_12_R1.class, "Entity:Parrot:getVariant", new MethodExecutor(getClassExec("Entity:Parrot"), ClassExecutor.INT, "getVariant", "Gets the variant of a parrot"));
    }

    @Override
    public void loadEnums() {
        super.loadEnums();

        load(V1_12_R1.class, "Entity:IllagerWizard:Spell", new EnumSpellEnum(getClassExec("Entity:IllagerWizard:Spell")));
    }

    @Override
    public @NotNull String getName() {
        return "v1_12_R1";
    }

}
