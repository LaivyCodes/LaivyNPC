package codes.laivy.npc.mappings.versions;

import codes.laivy.npc.mappings.Version;
import codes.laivy.npc.mappings.defaults.classes.datawatcher.DataWatcher;
import codes.laivy.npc.mappings.defaults.classes.datawatcher.DataWatcherItem;
import codes.laivy.npc.mappings.defaults.classes.datawatcher.DataWatcherObject;
import codes.laivy.npc.mappings.defaults.classes.entity.*;
import codes.laivy.npc.mappings.defaults.classes.entity.ambient.Bat;
import codes.laivy.npc.mappings.defaults.classes.entity.ambient.Egg;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.*;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.fish.*;
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
import codes.laivy.npc.mappings.defaults.classes.entity.monster.zombie.ZombieDrowned;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.zombie.ZombieHusk;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.zombie.ZombieVillager;
import codes.laivy.npc.mappings.defaults.classes.entity.npc.Villager;
import codes.laivy.npc.mappings.defaults.classes.entity.npc.objs.VillagerData;
import codes.laivy.npc.mappings.defaults.classes.entity.npc.objs.VillagerProfessionExec;
import codes.laivy.npc.mappings.defaults.classes.entity.npc.objs.VillagerType;
import codes.laivy.npc.mappings.defaults.classes.entity.vehicle.Boat;
import codes.laivy.npc.mappings.defaults.classes.enums.*;
import codes.laivy.npc.mappings.defaults.classes.gameprofile.GameProfile;
import codes.laivy.npc.mappings.defaults.classes.gameprofile.Property;
import codes.laivy.npc.mappings.defaults.classes.gameprofile.PropertyMap;
import codes.laivy.npc.mappings.defaults.classes.nbt.NBTBase;
import codes.laivy.npc.mappings.defaults.classes.nbt.tags.*;
import codes.laivy.npc.mappings.defaults.classes.others.chat.IChatBaseComponent;
import codes.laivy.npc.mappings.defaults.classes.others.inventories.InventorySubcontainer;
import codes.laivy.npc.mappings.defaults.classes.others.location.*;
import codes.laivy.npc.mappings.defaults.classes.others.managers.PlayerInteractManager;
import codes.laivy.npc.mappings.defaults.classes.others.objects.*;
import codes.laivy.npc.mappings.defaults.classes.others.server.CraftServer;
import codes.laivy.npc.mappings.defaults.classes.others.server.MinecraftServer;
import codes.laivy.npc.mappings.defaults.classes.packets.*;
import codes.laivy.npc.mappings.defaults.classes.scoreboard.CraftScoreboard;
import codes.laivy.npc.mappings.defaults.classes.scoreboard.Scoreboard;
import codes.laivy.npc.mappings.defaults.classes.scoreboard.ScoreboardTeam;
import codes.laivy.npc.mappings.instances.EnumExecutor;
import codes.laivy.npc.mappings.instances.Executor;
import codes.laivy.npc.mappings.instances.MethodExecutor;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public class V1_16_R3 extends V1_16_R2 {

    @Override
    protected boolean onLoad(@NotNull Class<? extends Version> version, @NotNull String key, @NotNull Executor executor) {
        if (version == V1_16_R2.class) {
            if (executor instanceof ClassExecutor && !(executor instanceof EnumExecutor)) {
                return false;
            } else if (executor instanceof MethodExecutor) {
                if (key.equals("Entity:isGlowing")) {
                    load(V1_16_R3.class, key, new MethodExecutor(getClassExec("Entity"), ClassExecutor.BOOLEAN, "bE", "Gets the glowing state of a Entity"));
                    return false;
                }
            }
        }
        
        return super.onLoad(version, key, executor);
    }

    @Override
    public @NotNull EntityPlayer createPlayer(@NotNull GameProfile profile, @NotNull org.bukkit.Location location) {
        if (!Bukkit.isPrimaryThread()) {
            throw new UnsupportedOperationException("Since 1.16.5, you need to create players async.");
        }

        return super.createPlayer(profile, location);
    }

    @Override
    public void loadClasses() {
        load(V1_16_R3.class, "WatchableObject", new ClassExecutor.BrokenClassExecutor());
        load(V1_16_R3.class, "EnumSkeletonType", new ClassExecutor.BrokenClassExecutor());
        load(V1_16_R3.class, "EnumHorseType", new ClassExecutor.BrokenClassExecutor());
        load(V1_16_R3.class, "EnumZombieType", new ClassExecutor.BrokenClassExecutor());

        load(V1_16_R3.class, "NBTBase", new NBTBase.NBTBaseClass("net.minecraft.server.v1_16_R3.NBTBase"));

        load(V1_16_R3.class, "NBTBase:NBTTagByte", new NBTTagByte.NBTTagByteClass("net.minecraft.server.v1_16_R3.NBTTagByte"));
        load(V1_16_R3.class, "NBTBase:NBTTagByteArray", new NBTTagByteArray.NBTTagByteArrayClass("net.minecraft.server.v1_16_R3.NBTTagByteArray"));
        load(V1_16_R3.class, "NBTBase:NBTTagCompound", new NBTTagCompound.NBTTagCompoundClass("net.minecraft.server.v1_16_R3.NBTTagCompound"));
        load(V1_16_R3.class, "NBTBase:NBTTagDouble", new NBTTagDouble.NBTTagDoubleClass("net.minecraft.server.v1_16_R3.NBTTagDouble"));
        load(V1_16_R3.class, "NBTBase:NBTTagFloat", new NBTTagFloat.NBTTagFloatClass("net.minecraft.server.v1_16_R3.NBTTagFloat"));
        load(V1_16_R3.class, "NBTBase:NBTTagInt", new NBTTagInt.NBTTagIntClass("net.minecraft.server.v1_16_R3.NBTTagInt"));
        load(V1_16_R3.class, "NBTBase:NBTTagIntArray", new NBTTagIntArray.NBTTagIntArrayClass("net.minecraft.server.v1_16_R3.NBTTagIntArray"));
        load(V1_16_R3.class, "NBTBase:NBTTagList", new NBTTagList.NBTTagListClass("net.minecraft.server.v1_16_R3.NBTTagList"));
        load(V1_16_R3.class, "NBTBase:NBTTagLong", new NBTTagLong.NBTTagLongClass("net.minecraft.server.v1_16_R3.NBTTagLong"));
        load(V1_16_R3.class, "NBTBase:NBTTagShort", new NBTTagShort.NBTTagShortClass("net.minecraft.server.v1_16_R3.NBTTagShort"));
        load(V1_16_R3.class, "NBTBase:NBTTagString", new NBTTagString.NBTTagStringClass("net.minecraft.server.v1_16_R3.NBTTagString"));
        //

        // Packets
        load(V1_16_R3.class, "Packet", new Packet.PacketClass("net.minecraft.server.v1_16_R3.Packet"));
        load(V1_16_R3.class, "PacketPlayOutSpawnEntity", new EntitySpawnPacket.EntitySpawnPacketClass("net.minecraft.server.v1_16_R3.PacketPlayOutSpawnEntity"));
        load(V1_16_R3.class, "PacketPlayOutEntityDestroy", new EntityDestroyPacket.EntityDestroyPacketClass("net.minecraft.server.v1_16_R3.PacketPlayOutEntityDestroy"));
        load(V1_16_R3.class, "PacketPlayOutAnimation", new EntityAnimationPacket.EntityAnimationPacketClass("net.minecraft.server.v1_16_R3.PacketPlayOutAnimation"));
        load(V1_16_R3.class, "PacketPlayOutSpawnEntityLiving", new EntityLivingSpawnPacket.EntityLivingSpawnPacketClass("net.minecraft.server.v1_16_R3.PacketPlayOutSpawnEntityLiving"));
        load(V1_16_R3.class, "PacketPlayOutEntityMetadata", new EntityMetadataPacket.EntityMetadataPacketClass("net.minecraft.server.v1_16_R3.PacketPlayOutEntityMetadata"));
        load(V1_16_R3.class, "PacketPlayOutNamedEntitySpawn", new EntityNamedSpawnPacket.EntityNamedSpawnPacketClass("net.minecraft.server.v1_16_R3.PacketPlayOutNamedEntitySpawn"));
        load(V1_16_R3.class, "PacketPlayOutPlayerInfo", new PlayerInfoPacket.PlayerInfoPacketClass("net.minecraft.server.v1_16_R3.PacketPlayOutPlayerInfo"));
        load(V1_16_R3.class, "PacketPlayOutPlayerInfo:EnumPlayerInfoAction", new EnumPlayerInfoActionEnum.EnumPlayerInfoActionClass("net.minecraft.server.v1_16_R3.PacketPlayOutPlayerInfo$EnumPlayerInfoAction"));
        load(V1_16_R3.class, "PacketPlayOutScoreboardTeam", new ScoreboardTeamPacket.ScoreboardTeamPacketClass("net.minecraft.server.v1_16_R3.PacketPlayOutScoreboardTeam"));
        load(V1_16_R3.class, "PacketPlayOutEntityEquipment", new EntityEquipmentPacket.EntityEquipmentPacketClass("net.minecraft.server.v1_16_R3.PacketPlayOutEntityEquipment"));
        load(V1_16_R3.class, "PacketPlayOutEntityTeleport", new EntityTeleportPacket.EntityTeleportPacketClass("net.minecraft.server.v1_16_R3.PacketPlayOutEntityTeleport"));
        load(V1_16_R3.class, "PacketPlayOutEntityHeadRotation", new EntityHeadRotationPacket.EntityHeadRotationPacketClass("net.minecraft.server.v1_16_R3.PacketPlayOutEntityHeadRotation"));
        load(V1_16_R3.class, "PacketPlayOutEntityLook", new EntityLookPacket.EntityLookPacketClass("net.minecraft.server.v1_16_R3.PacketPlayOutEntity$PacketPlayOutEntityLook"));

        load(V1_16_R3.class, "PacketPlayInUseEntity", new EntityUseInPacket.EntityUseInPacketClass("net.minecraft.server.v1_16_R3.PacketPlayInUseEntity"));
        load(V1_16_R3.class, "PacketPlayInUseEntity:EnumEntityUseAction", new EntityUseInPacket.ActionEnum.ActionClass("net.minecraft.server.v1_16_R3.PacketPlayInUseEntity$EnumEntityUseAction"));
        //

        // Server
        load(V1_16_R3.class, "MinecraftServer", new MinecraftServer.MinecraftServerClass("net.minecraft.server.v1_16_R3.MinecraftServer"));
        load(V1_16_R3.class, "WorldServer", new WorldServer.WorldServerClass("net.minecraft.server.v1_16_R3.WorldServer"));
        load(V1_16_R3.class, "CraftServer", new CraftServer.CraftServerClass("org.bukkit.craftbukkit.v1_16_R3.CraftServer"));
        //

        // Entity
        load(V1_16_R3.class, "EntityTypes", new EntityTypes.EntityTypesClass("net.minecraft.server.v1_16_R3.EntityTypes"));

        load(V1_16_R3.class, "Entity", new Entity.EntityClass("net.minecraft.server.v1_16_R3.Entity"));
        load(V1_16_R3.class, "EntityLiving", new EntityLiving.EntityLivingClass("net.minecraft.server.v1_16_R3.EntityLiving"));
        load(V1_16_R3.class, "Entity:Human", new Entity.EntityClass("net.minecraft.server.v1_16_R3.EntityHuman"));
        load(V1_16_R3.class, "CraftPlayer", new CraftPlayer.CraftPlayerClass("org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer"));
        load(V1_16_R3.class, "EntityPlayer", new EntityPlayer.EntityPlayerClass("net.minecraft.server.v1_16_R3.EntityPlayer"));

        load(V1_16_R3.class, "Entity:ArmorStand", new ArmorStand.ArmorStandClass("net.minecraft.server.v1_16_R3.EntityArmorStand"));
        load(V1_16_R3.class, "Entity:Pig", new Pig.PigClass("net.minecraft.server.v1_16_R3.EntityPig"));
        load(V1_16_R3.class, "Entity:Cow", new Cow.CowClass("net.minecraft.server.v1_16_R3.EntityCow"));
        load(V1_16_R3.class, "Entity:Ocelot", new Ocelot.OcelotClass("net.minecraft.server.v1_16_R3.EntityOcelot"));
        load(V1_16_R3.class, "Entity:Bat", new Bat.BatClass("net.minecraft.server.v1_16_R3.EntityBat"));
        load(V1_16_R3.class, "Entity:Egg", new Egg.EggClass("net.minecraft.server.v1_16_R3.EntityEgg"));
        load(V1_16_R3.class, "Entity:Chicken", new Chicken.ChickenClass("net.minecraft.server.v1_16_R3.EntityChicken"));
        load(V1_16_R3.class, "Entity:Horse", new AbstractHorse.AbstractHorseClass("net.minecraft.server.v1_16_R3.EntityHorse"));
        load(V1_16_R3.class, "Entity:IronGolem", new IronGolem.IronGolemClass("net.minecraft.server.v1_16_R3.EntityIronGolem"));
        load(V1_16_R3.class, "Entity:Rabbit", new Rabbit.RabbitClass("net.minecraft.server.v1_16_R3.EntityRabbit"));
        load(V1_16_R3.class, "Entity:Sheep", new Sheep.SheepClass("net.minecraft.server.v1_16_R3.EntitySheep"));
        load(V1_16_R3.class, "Entity:Snowman", new Snowman.SnowmanClass("net.minecraft.server.v1_16_R3.EntitySnowman"));
        load(V1_16_R3.class, "Entity:Squid", new Squid.SquidClass("net.minecraft.server.v1_16_R3.EntitySquid"));
        load(V1_16_R3.class, "Entity:Wolf", new Wolf.WolfClass("net.minecraft.server.v1_16_R3.EntityWolf"));
        load(V1_16_R3.class, "Entity:ItemFrame", new ItemFrame.ItemFrameClass("net.minecraft.server.v1_16_R3.EntityItemFrame"));
        load(V1_16_R3.class, "Entity:LeashKnot", new LeashKnot.LeashKnotClass("net.minecraft.server.v1_16_R3.EntityLeash"));
        load(V1_16_R3.class, "Entity:FallingBlock", new FallingBlock.FallingBlockClass("net.minecraft.server.v1_16_R3.EntityFallingBlock"));
        load(V1_16_R3.class, "Entity:Item", new Item.ItemClass("net.minecraft.server.v1_16_R3.EntityItem"));
        load(V1_16_R3.class, "Entity:EnderDragon", new EnderDragon.EnderDragonClass("net.minecraft.server.v1_16_R3.EntityEnderDragon"));
        load(V1_16_R3.class, "Entity:EnderSignal", new EnderSignal.EnderSignalClass("net.minecraft.server.v1_16_R3.EntityEnderSignal"));
        load(V1_16_R3.class, "Entity:Wither", new Wither.WitherClass("net.minecraft.server.v1_16_R3.EntityWither"));
        load(V1_16_R3.class, "Entity:WitherSkull", new WitherSkull.WitherSkullClass("net.minecraft.server.v1_16_R3.EntityWitherSkull"));
        load(V1_16_R3.class, "Entity:Blaze", new Blaze.BlazeClass("net.minecraft.server.v1_16_R3.EntityBlaze"));
        load(V1_16_R3.class, "Entity:Creeper", new Creeper.CreeperClass("net.minecraft.server.v1_16_R3.EntityCreeper"));
        load(V1_16_R3.class, "Entity:Enderman", new Enderman.EndermanClass("net.minecraft.server.v1_16_R3.EntityEnderman"));
        load(V1_16_R3.class, "Entity:Ghast", new Ghast.GhastClass("net.minecraft.server.v1_16_R3.EntityGhast"));
        load(V1_16_R3.class, "Entity:Guardian", new Guardian.GuardianClass("net.minecraft.server.v1_16_R3.EntityGuardian"));
        load(V1_16_R3.class, "Entity:Silverfish", new Silverfish.SilverfishClass("net.minecraft.server.v1_16_R3.EntitySilverfish"));
        load(V1_16_R3.class, "Entity:Skeleton", new Skeleton.SkeletonClass("net.minecraft.server.v1_16_R3.EntitySkeleton"));
        load(V1_16_R3.class, "Entity:Slime", new Slime.SlimeClass("net.minecraft.server.v1_16_R3.EntitySlime"));
        load(V1_16_R3.class, "Entity:Spider", new Spider.SpiderClass("net.minecraft.server.v1_16_R3.EntitySpider"));
        load(V1_16_R3.class, "Entity:Witch", new Witch.WitchClass("net.minecraft.server.v1_16_R3.EntityWitch"));
        load(V1_16_R3.class, "Entity:Zombie", new Zombie.ZombieClass("net.minecraft.server.v1_16_R3.EntityZombie"));
        load(V1_16_R3.class, "Entity:Villager", new Villager.VillagerClass("net.minecraft.server.v1_16_R3.EntityVillager"));
        load(V1_16_R3.class, "Entity:Shulker", new Shulker.ShulkerClass("net.minecraft.server.v1_16_R3.EntityShulker"));
        load(V1_16_R3.class, "Entity:PolarBear", new PolarBear.PolarBearClass("net.minecraft.server.v1_16_R3.EntityPolarBear"));
        load(V1_16_R3.class, "Entity:Boat", new Boat.BoatClass("net.minecraft.server.v1_16_R3.EntityBoat"));
        load(V1_16_R3.class, "Entity:CaveSpider", new CaveSpider.CaveSpiderClass("net.minecraft.server.v1_16_R3.EntityCaveSpider"));

        load(V1_16_R3.class, "Entity:Ageable", new AgeableEntityLiving.AgeableEntityLivingClass("net.minecraft.server.v1_16_R3.EntityAgeable"));
        load(V1_16_R3.class, "Entity:Tameable", new TameableEntityLiving.TameableEntityLivingClass("net.minecraft.server.v1_16_R3.EntityTameableAnimal"));
        // EntityPlayer
        load(V1_16_R3.class, "GameProfile", new GameProfile.GameProfileClass("com.mojang.authlib.GameProfile"));
        load(V1_16_R3.class, "PropertyMap", new PropertyMap.PropertyMapClass("com.mojang.authlib.properties.PropertyMap"));
        load(V1_16_R3.class, "Property", new Property.PropertyClass("com.mojang.authlib.properties.Property"));
        //

        // Managers
        load(V1_16_R3.class, "PlayerInteractManager", new PlayerInteractManager.PlayerInteractManagerClass("net.minecraft.server.v1_16_R3.PlayerInteractManager"));
        //

        // DataWatcher
        load(V1_16_R3.class, "DataWatcher", new DataWatcher.DataWatcherClass("net.minecraft.server.v1_16_R3.DataWatcher"));
        load(V1_16_R3.class, "DataWatcherObject", new DataWatcherObject.DataWatcherObjectClass("net.minecraft.server.v1_16_R3.DataWatcherObject"));
        load(V1_16_R3.class, "DataWatcher:Item", new DataWatcherItem.DataWatcherItemClass("net.minecraft.server.v1_16_R3.DataWatcher$Item"));
        //

        // Scoreboard
        load(V1_16_R3.class, "CraftScoreboard", new CraftScoreboard.CraftScoreboardClass("org.bukkit.craftbukkit.v1_16_R3.scoreboard.CraftScoreboard"));
        load(V1_16_R3.class, "Scoreboard", new Scoreboard.ScoreboardClass("net.minecraft.server.v1_16_R3.Scoreboard"));

        load(V1_16_R3.class, "ScoreboardTeam", new ScoreboardTeam.ScoreboardTeamClass("net.minecraft.server.v1_16_R3.ScoreboardTeam"));
        load(V1_16_R3.class, "ScoreboardTeam:EnumTeamPush", new EnumTeamPushEnum.EnumTeamPushClass("net.minecraft.server.v1_16_R3.ScoreboardTeamBase$EnumTeamPush"));

        load(V1_16_R3.class, "ScoreboardTeamBase:EnumNameTagVisibility", new EnumNameTagVisibilityEnum.EnumNameTagVisibilityClass("net.minecraft.server.v1_16_R3.ScoreboardTeamBase$EnumNameTagVisibility"));
        //

        // Others
        load(V1_16_R3.class, "PlayerConnection", new PlayerConnection.PlayerConnectionClass("net.minecraft.server.v1_16_R3.PlayerConnection"));
        load(V1_16_R3.class, "NetworkManager", new NetworkManager.NetworkManagerClass("net.minecraft.server.v1_16_R3.NetworkManager"));

        load(V1_16_R3.class, "EnumChatFormat", new EnumChatFormatEnum.EnumChatFormatClass("net.minecraft.server.v1_16_R3.EnumChatFormat"));
        load(V1_16_R3.class, "EnumColor", new EnumColorEnum.EnumColorClass("net.minecraft.server.v1_16_R3.EnumColor"));
        load(V1_16_R3.class, "EnumItemSlot", new EnumItemSlotEnum.EnumItemSlotClass("net.minecraft.server.v1_16_R3.EnumItemSlot"));
        load(V1_16_R3.class, "EnumDirection", new EnumDirectionEnum.EnumDirectionClass("net.minecraft.server.v1_16_R3.EnumDirection"));
        //

        // Chat
        load(V1_16_R3.class, "IChatBaseComponent", new IChatBaseComponent.IChatBaseComponentClass("net.minecraft.server.v1_16_R3.IChatBaseComponent"));
        load(V1_16_R3.class, "ChatSerializer", new IChatBaseComponent.ChatSerializerClass("net.minecraft.server.v1_16_R3.IChatBaseComponent$ChatSerializer"));
        //

        // Objects
        load(V1_16_R3.class, "CraftWorld", new CraftWorld.CraftWorldClass("org.bukkit.craftbukkit.v1_16_R3.CraftWorld"));
        load(V1_16_R3.class, "World", new World.WorldClass("net.minecraft.server.v1_16_R3.World"));
        load(V1_16_R3.class, "Vector3f", new Vector3f.Vector3fClass("net.minecraft.server.v1_16_R3.Vector3f"));
        load(V1_16_R3.class, "Vec3D", new Vec3D.Vec3DClass("net.minecraft.server.v1_16_R3.Vec3D"));
        load(V1_16_R3.class, "BlockPosition", new BlockPosition.BlockPositionClass("net.minecraft.server.v1_16_R3.BlockPosition"));
        load(V1_16_R3.class, "CraftBlock", new CraftBlock.CraftBlockClass("org.bukkit.craftbukkit.v1_16_R3.block.CraftBlock"));
        load(V1_16_R3.class, "IBlockData", new IBlockData.IBlockDataClass("net.minecraft.server.v1_16_R3.IBlockData"));
        load(V1_16_R3.class, "Block", new Block.BlockClass("net.minecraft.server.v1_16_R3.Block"));
        load(V1_16_R3.class, "CraftMagicNumbers", new ClassExecutor("org.bukkit.craftbukkit.v1_16_R3.util.CraftMagicNumbers"));
        load(V1_16_R3.class, "Pair", new Pair.PairClass("com.mojang.datafixers.util.Pair"));

        load(V1_16_R3.class, "InventorySubcontainer", new InventorySubcontainer.InventorySubcontainerClass("net.minecraft.server.v1_16_R3.InventorySubcontainer"));
        //

        // Items
        load(V1_16_R3.class, "ItemStack", new ItemStack.ItemStackClass("net.minecraft.server.v1_16_R3.ItemStack"));
        load(V1_16_R3.class, "CraftItemStack", new CraftItemStack.CraftItemStackClass("org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack"));
        //

        // Entity horse
        load(V1_16_R3.class, "Entity:Horse:Abstract", new AbstractHorse.AbstractHorseClass("net.minecraft.server.v1_16_R3.EntityHorseAbstract"));
        load(V1_16_R3.class, "Entity:Horse:Abstract:Chested", new AbstractChestedHorse.AbstractChestedHorseClass("net.minecraft.server.v1_16_R3.EntityHorseChestedAbstract"));
        load(V1_16_R3.class, "Entity:Horse:Donkey", new HorseDonkey.HorseDonkeyClass("net.minecraft.server.v1_16_R3.EntityHorseDonkey"));
        load(V1_16_R3.class, "Entity:Horse:Mule", new HorseMule.HorseMuleClass("net.minecraft.server.v1_16_R3.EntityHorseMule"));
        load(V1_16_R3.class, "Entity:Horse:Skeleton", new HorseSkeleton.HorseSkeletonClass("net.minecraft.server.v1_16_R3.EntityHorseSkeleton"));
        load(V1_16_R3.class, "Entity:Horse:Zombie", new HorseZombie.HorseZombieClass("net.minecraft.server.v1_16_R3.EntityHorseZombie"));
        // Entity skeleton
        load(V1_16_R3.class, "Entity:Skeleton:Wither", new SkeletonWither.SkeletonWitherClass("net.minecraft.server.v1_16_R3.EntitySkeletonWither"));
        load(V1_16_R3.class, "Entity:Skeleton:Stray", new SkeletonStray.SkeletonStrayClass("net.minecraft.server.v1_16_R3.EntitySkeletonStray"));
        // Entity zombie
        load(V1_16_R3.class, "Entity:Zombie:Villager", new ZombieVillager.ZombieVillagerClass("net.minecraft.server.v1_16_R3.EntityZombieVillager"));
        load(V1_16_R3.class, "Entity:Zombie:Husk", new ZombieHusk.ZombieHuskClass("net.minecraft.server.v1_16_R3.EntityZombieHusk"));
        load(V1_16_R3.class, "Entity:Zombie:Drowned", new ZombieDrowned.ZombieDrownedClass("net.minecraft.server.v1_16_R3.EntityDrowned"));
        // Entity vindicator
        load(V1_16_R3.class, "Entity:Vindicator", new Vindicator.VindicatorClass("net.minecraft.server.v1_16_R3.EntityVindicator"));
        // Entity evoker
        load(V1_16_R3.class, "Entity:Evoker", new Evoker.EvokerClass("net.minecraft.server.v1_16_R3.EntityEvoker"));
        // Entity vex
        load(V1_16_R3.class, "Entity:Vex", new Vex.VexClass("net.minecraft.server.v1_16_R3.EntityVex"));
        // Entity llama
        load(V1_16_R3.class, "Entity:Llama", new Llama.LlamaClass("net.minecraft.server.v1_16_R3.EntityLlama"));
        // Entity illager illusioner
        load(V1_16_R3.class, "Entity:Illusioner", new Illusioner.IllusionerClass("net.minecraft.server.v1_16_R3.EntityIllagerIllusioner"));
        // Entity illager wizard
        load(V1_16_R3.class, "Entity:IllagerWizard", new IllagerWizard.IllagerWizardClass("net.minecraft.server.v1_16_R3.EntityIllagerWizard"));
        load(V1_16_R3.class, "Entity:IllagerWizard:Spell", new EnumSpellEnum.EnumSpellClass("net.minecraft.server.v1_16_R3.EntityIllagerWizard$Spell"));
        // Entity parrot
        load(V1_16_R3.class, "Entity:Parrot", new Parrot.ParrotClass("net.minecraft.server.v1_16_R3.EntityParrot"));
        // Entity dolphin
        load(V1_16_R3.class, "Entity:Dolphin", new Dolphin.DolphinClass("net.minecraft.server.v1_16_R3.EntityDolphin"));
        // Entity fish
        load(V1_16_R3.class, "Entity:Fish", new Fish.FishClass("net.minecraft.server.v1_16_R3.EntityFish"));
        load(V1_16_R3.class, "Entity:Cod", new Cod.CodClass("net.minecraft.server.v1_16_R3.EntityCod"));
        load(V1_16_R3.class, "Entity:Salmon", new Salmon.SalmonClass("net.minecraft.server.v1_16_R3.EntitySalmon"));
        load(V1_16_R3.class, "Entity:PufferFish", new Pufferfish.PufferfishClass("net.minecraft.server.v1_16_R3.EntityPufferFish"));
        load(V1_16_R3.class, "Entity:TropicalFish", new Tropicalfish.TropicalfishClass("net.minecraft.server.v1_16_R3.EntityTropicalFish"));
        // Entity phantom
        load(V1_16_R3.class, "Entity:Phantom", new Phantom.PhantomClass("net.minecraft.server.v1_16_R3.EntityPhantom"));
        // Entity turtle
        load(V1_16_R3.class, "Entity:Turtle", new Turtle.TurtleClass("net.minecraft.server.v1_16_R3.EntityTurtle"));
        // Entity cat
        load(V1_16_R3.class, "Entity:Cat", new Cat.CatClass("net.minecraft.server.v1_16_R3.EntityCat"));
        // Entity villager
        load(V1_16_R3.class, "VillagerData", new VillagerData.VillagerDataClass("net.minecraft.server.v1_16_R3.VillagerData"));
        load(V1_16_R3.class, "VillagerProfession", new VillagerProfessionExec.VillagerProfessionExecClass("net.minecraft.server.v1_16_R3.VillagerProfession"));
        load(V1_16_R3.class, "VillagerType", new VillagerType.VillagerTypeClass("net.minecraft.server.v1_16_R3.VillagerType"));
        //
    }

    @Override
    public @NotNull String getName() {
        return "v1_16_R3";
    }

}
