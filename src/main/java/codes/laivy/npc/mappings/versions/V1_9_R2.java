package codes.laivy.npc.mappings.versions;

import codes.laivy.npc.mappings.Version;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.AbstractHorse;
import codes.laivy.npc.mappings.defaults.classes.entity.vehicle.Boat;
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
import codes.laivy.npc.mappings.defaults.classes.entity.monster.zombie.Zombie;
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

import java.util.Map;

public class V1_9_R2 extends V1_9_R1 {

    @Override
    protected boolean onLoad(@NotNull Class<? extends Version> version, @NotNull String key, @NotNull Executor executor) {
        if (version == V1_9_R1.class) {
            if (executor instanceof ClassExecutor && !(executor instanceof EnumExecutor)) {
                return false;
            } else if (executor instanceof MethodExecutor) {
                switch (key) {
                    case "ScoreboardTeam:getCollision":
                        load(V1_9_R2.class, key, new MethodExecutor(getClassExec("ScoreboardTeam"), getClassExec("ScoreboardTeam:EnumTeamPush"), "getCollisionRule", "Gets the collision state of the ScoreboardTeam"));
                        return false;
                    case "ScoreboardTeam:setCollision":
                        load(V1_9_R2.class, key, new MethodExecutor(getClassExec("ScoreboardTeam"), ClassExecutor.VOID, "setCollisionRule", "Sets the collision state of the ScoreboardTeam", getClassExec("ScoreboardTeam:EnumTeamPush")));
                        return false;
                    default:
                        break;

                }
            } else if (executor instanceof FieldExecutor) {
                switch (key) {
                    case "Metadata:Enderman:screaming":
                        load(V1_9_R2.class, key, new FieldExecutor(getClassExec("Entity:Enderman"), getClassExec("DataWatcherObject"), "bx", "Gets the enderman's screaming datawatcher object"));
                        return false;
                    case "Metadata:Horse:Armor":
                        load(V1_9_R2.class, key, new FieldExecutor(getClassExec("Entity:Horse"), getClassExec("DataWatcherObject"), "bJ", "Gets the horse armor DataWatcherObject"));
                        return false;
                    case "Metadata:Horse:Variant":
                        load(V1_9_R2.class, key, new FieldExecutor(getClassExec("Entity:Horse"), getClassExec("DataWatcherObject"), "bH", "Gets the horse variant DataWatcherObject"));
                        return false;
                    case "Metadata:Zombie:Baby":
                        load(V1_9_R2.class, key, new FieldExecutor(getClassExec("Entity:Zombie"), getClassExec("DataWatcherObject"), "bw", "Gets the zombie baby DataWatcherObject"));
                        return false;
                    case "Metadata:Zombie:Villager:Profession":
                        load(V1_9_R2.class, key, new FieldExecutor(getClassExec("Entity:Zombie"), getClassExec("DataWatcherObject"), "bx", "Gets the zombie villager profession DataWatcherObject"));
                        return false;
                    case "Metadata:Pig:Saddle":
                        load(V1_9_R2.class, key, new FieldExecutor(getClassExec("Entity:Pig"), getClassExec("DataWatcherObject"), "bw", "Gets the pig saddle DataWatcherObject"));
                        return false;
                    case "Metadata:Wolf:Angry":
                        load(V1_9_R2.class, key, new FieldExecutor(getClassExec("Entity:Wolf"), getClassExec("DataWatcherObject"), "bB", "Gets the wolf angry DataWatcherObject"));
                        return false;
                    case "Metadata:Wolf:CollarColor":
                        load(V1_9_R2.class, key, new FieldExecutor(getClassExec("Entity:Wolf"), getClassExec("DataWatcherObject"), "bC", "Gets the wolf collar color DataWatcherObject"));
                        return false;
                    default:
                        break;
                }
            }
        }

        return super.onLoad(version, key, executor);
    }

    @Override
    public @NotNull Map<String, ClassExecutor> getClasses() {
        if (super.getClasses().isEmpty()) {
            load(V1_9_R2.class, "WatchableObject", new ClassExecutor.BrokenClassExecutor());

            load(V1_9_R2.class, "NBTBase", new NBTBase.NBTBaseClass("net.minecraft.server.v1_9_R2.NBTBase"));

            load(V1_9_R2.class, "NBTBase:NBTTagByte", new NBTTagByte.NBTTagByteClass("net.minecraft.server.v1_9_R2.NBTTagByte"));
            load(V1_9_R2.class, "NBTBase:NBTTagByteArray", new NBTTagByteArray.NBTTagByteArrayClass("net.minecraft.server.v1_9_R2.NBTTagByteArray"));
            load(V1_9_R2.class, "NBTBase:NBTTagCompound", new NBTTagCompound.NBTTagCompoundClass("net.minecraft.server.v1_9_R2.NBTTagCompound"));
            load(V1_9_R2.class, "NBTBase:NBTTagDouble", new NBTTagDouble.NBTTagDoubleClass("net.minecraft.server.v1_9_R2.NBTTagDouble"));
            load(V1_9_R2.class, "NBTBase:NBTTagFloat", new NBTTagFloat.NBTTagFloatClass("net.minecraft.server.v1_9_R2.NBTTagFloat"));
            load(V1_9_R2.class, "NBTBase:NBTTagInt", new NBTTagInt.NBTTagIntClass("net.minecraft.server.v1_9_R2.NBTTagInt"));
            load(V1_9_R2.class, "NBTBase:NBTTagIntArray", new NBTTagIntArray.NBTTagIntArrayClass("net.minecraft.server.v1_9_R2.NBTTagIntArray"));
            load(V1_9_R2.class, "NBTBase:NBTTagList", new NBTTagList.NBTTagListClass("net.minecraft.server.v1_9_R2.NBTTagList"));
            load(V1_9_R2.class, "NBTBase:NBTTagLong", new NBTTagLong.NBTTagLongClass("net.minecraft.server.v1_9_R2.NBTTagLong"));
            load(V1_9_R2.class, "NBTBase:NBTTagShort", new NBTTagShort.NBTTagShortClass("net.minecraft.server.v1_9_R2.NBTTagShort"));
            load(V1_9_R2.class, "NBTBase:NBTTagString", new NBTTagString.NBTTagStringClass("net.minecraft.server.v1_9_R2.NBTTagString"));
            //

            // Packets
            load(V1_9_R2.class, "Packet", new Packet.PacketClass("net.minecraft.server.v1_9_R2.Packet"));
            load(V1_9_R2.class, "PacketPlayOutSpawnEntity", new EntitySpawnPacket.EntitySpawnPacketClass("net.minecraft.server.v1_9_R2.PacketPlayOutSpawnEntity"));
            load(V1_9_R2.class, "PacketPlayOutEntityDestroy", new EntityDestroyPacket.EntityDestroyPacketClass("net.minecraft.server.v1_9_R2.PacketPlayOutEntityDestroy"));
            load(V1_9_R2.class, "PacketPlayOutAnimation", new EntityAnimationPacket.EntityAnimationPacketClass("net.minecraft.server.v1_9_R2.PacketPlayOutAnimation"));
            load(V1_9_R2.class, "PacketPlayOutSpawnEntityLiving", new EntityLivingSpawnPacket.EntityLivingSpawnPacketClass("net.minecraft.server.v1_9_R2.PacketPlayOutSpawnEntityLiving"));
            load(V1_9_R2.class, "PacketPlayOutEntityMetadata", new EntityMetadataPacket.EntityMetadataPacketClass("net.minecraft.server.v1_9_R2.PacketPlayOutEntityMetadata"));
            load(V1_9_R2.class, "PacketPlayOutNamedEntitySpawn", new EntityNamedSpawnPacket.EntityNamedSpawnPacketClass("net.minecraft.server.v1_9_R2.PacketPlayOutNamedEntitySpawn"));
            load(V1_9_R2.class, "PacketPlayOutPlayerInfo", new PlayerInfoPacket.PlayerInfoPacketClass("net.minecraft.server.v1_9_R2.PacketPlayOutPlayerInfo"));
            load(V1_9_R2.class, "PacketPlayOutPlayerInfo:EnumPlayerInfoAction", new EnumPlayerInfoActionEnum.EnumPlayerInfoActionClass("net.minecraft.server.v1_9_R2.PacketPlayOutPlayerInfo$EnumPlayerInfoAction"));
            load(V1_9_R2.class, "PacketPlayOutScoreboardTeam", new ScoreboardTeamPacket.ScoreboardTeamPacketClass("net.minecraft.server.v1_9_R2.PacketPlayOutScoreboardTeam"));
            load(V1_9_R2.class, "PacketPlayOutEntityEquipment", new EntityEquipmentPacket.EntityEquipmentPacketClass("net.minecraft.server.v1_9_R2.PacketPlayOutEntityEquipment"));
            load(V1_9_R2.class, "PacketPlayOutEntityTeleport", new EntityTeleportPacket.EntityTeleportPacketClass("net.minecraft.server.v1_9_R2.PacketPlayOutEntityTeleport"));
            load(V1_9_R2.class, "PacketPlayOutEntityHeadRotation", new EntityHeadRotationPacket.EntityHeadRotationPacketClass("net.minecraft.server.v1_9_R2.PacketPlayOutEntityHeadRotation"));
            load(V1_9_R2.class, "PacketPlayOutEntityLook", new EntityLookPacket.EntityLookPacketClass("net.minecraft.server.v1_9_R2.PacketPlayOutEntity$PacketPlayOutEntityLook"));

            load(V1_9_R2.class, "PacketPlayInUseEntity", new EntityUseInPacket.EntityUseInPacketClass("net.minecraft.server.v1_9_R2.PacketPlayInUseEntity"));
            load(V1_9_R2.class, "PacketPlayInUseEntity:EnumEntityUseAction", new EntityUseInPacket.ActionEnum.ActionClass("net.minecraft.server.v1_9_R2.PacketPlayInUseEntity$EnumEntityUseAction"));
            //

            // Server
            load(V1_9_R2.class, "MinecraftServer", new MinecraftServer.MinecraftServerClass("net.minecraft.server.v1_9_R2.MinecraftServer"));
            load(V1_9_R2.class, "WorldServer", new WorldServer.WorldServerClass("net.minecraft.server.v1_9_R2.WorldServer"));
            load(V1_9_R2.class, "CraftServer", new CraftServer.CraftServerClass("org.bukkit.craftbukkit.v1_9_R2.CraftServer"));
            //

            // Entity
            load(V1_9_R2.class, "Entity", new Entity.EntityClass("net.minecraft.server.v1_9_R2.Entity"));
            load(V1_9_R2.class, "EntityLiving", new EntityLiving.EntityLivingClass("net.minecraft.server.v1_9_R2.EntityLiving"));
            load(V1_9_R2.class, "Entity:Human", new Entity.EntityClass("net.minecraft.server.v1_9_R2.EntityHuman"));
            load(V1_9_R2.class, "CraftPlayer", new CraftPlayer.CraftPlayerClass("org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer"));
            load(V1_9_R2.class, "EntityPlayer", new EntityPlayer.EntityPlayerClass("net.minecraft.server.v1_9_R2.EntityPlayer"));

            load(V1_9_R2.class, "Entity:ArmorStand", new ArmorStand.ArmorStandClass("net.minecraft.server.v1_9_R2.EntityArmorStand"));
            load(V1_9_R2.class, "Entity:Pig", new Pig.PigClass("net.minecraft.server.v1_9_R2.EntityPig"));
            load(V1_9_R2.class, "Entity:Cow", new Cow.CowClass("net.minecraft.server.v1_9_R2.EntityCow"));
            load(V1_9_R2.class, "Entity:Ocelot", new Ocelot.OcelotClass("net.minecraft.server.v1_9_R2.EntityOcelot"));
            load(V1_9_R2.class, "Entity:Bat", new Bat.BatClass("net.minecraft.server.v1_9_R2.EntityBat"));
            load(V1_9_R2.class, "Entity:Egg", new Egg.EggClass("net.minecraft.server.v1_9_R2.EntityEgg"));
            load(V1_9_R2.class, "Entity:Chicken", new Chicken.ChickenClass("net.minecraft.server.v1_9_R2.EntityChicken"));
            load(V1_9_R2.class, "Entity:Horse", new AbstractHorse.AbstractHorseClass("net.minecraft.server.v1_9_R2.EntityHorse"));
            load(V1_9_R2.class, "Entity:IronGolem", new IronGolem.IronGolemClass("net.minecraft.server.v1_9_R2.EntityIronGolem"));
            load(V1_9_R2.class, "Entity:Rabbit", new Rabbit.RabbitClass("net.minecraft.server.v1_9_R2.EntityRabbit"));
            load(V1_9_R2.class, "Entity:Sheep", new Sheep.SheepClass("net.minecraft.server.v1_9_R2.EntitySheep"));
            load(V1_9_R2.class, "Entity:Snowman", new Snowman.SnowmanClass("net.minecraft.server.v1_9_R2.EntitySnowman"));
            load(V1_9_R2.class, "Entity:Squid", new Squid.SquidClass("net.minecraft.server.v1_9_R2.EntitySquid"));
            load(V1_9_R2.class, "Entity:Wolf", new Wolf.WolfClass("net.minecraft.server.v1_9_R2.EntityWolf"));
            load(V1_9_R2.class, "Entity:ItemFrame", new ItemFrame.ItemFrameClass("net.minecraft.server.v1_9_R2.EntityItemFrame"));
            load(V1_9_R2.class, "Entity:LeashKnot", new LeashKnot.LeashKnotClass("net.minecraft.server.v1_9_R2.EntityLeash"));
            load(V1_9_R2.class, "Entity:FallingBlock", new FallingBlock.FallingBlockClass("net.minecraft.server.v1_9_R2.EntityFallingBlock"));
            load(V1_9_R2.class, "Entity:Item", new Item.ItemClass("net.minecraft.server.v1_9_R2.EntityItem"));
            load(V1_9_R2.class, "Entity:EnderDragon", new EnderDragon.EnderDragonClass("net.minecraft.server.v1_9_R2.EntityEnderDragon"));
            load(V1_9_R2.class, "Entity:EnderSignal", new EnderSignal.EnderSignalClass("net.minecraft.server.v1_9_R2.EntityEnderSignal"));
            load(V1_9_R2.class, "Entity:Wither", new Wither.WitherClass("net.minecraft.server.v1_9_R2.EntityWither"));
            load(V1_9_R2.class, "Entity:WitherSkull", new WitherSkull.WitherSkullClass("net.minecraft.server.v1_9_R2.EntityWitherSkull"));
            load(V1_9_R2.class, "Entity:Blaze", new Blaze.BlazeClass("net.minecraft.server.v1_9_R2.EntityBlaze"));
            load(V1_9_R2.class, "Entity:Creeper", new Creeper.CreeperClass("net.minecraft.server.v1_9_R2.EntityCreeper"));
            load(V1_9_R2.class, "Entity:Enderman", new Enderman.EndermanClass("net.minecraft.server.v1_9_R2.EntityEnderman"));
            load(V1_9_R2.class, "Entity:Ghast", new Ghast.GhastClass("net.minecraft.server.v1_9_R2.EntityGhast"));
            load(V1_9_R2.class, "Entity:Guardian", new Guardian.GuardianClass("net.minecraft.server.v1_9_R2.EntityGuardian"));
            load(V1_9_R2.class, "Entity:Silverfish", new Silverfish.SilverfishClass("net.minecraft.server.v1_9_R2.EntitySilverfish"));
            load(V1_9_R2.class, "Entity:Skeleton", new Skeleton.SkeletonClass("net.minecraft.server.v1_9_R2.EntitySkeleton"));
            load(V1_9_R2.class, "Entity:Slime", new Slime.SlimeClass("net.minecraft.server.v1_9_R2.EntitySlime"));
            load(V1_9_R2.class, "Entity:Spider", new Spider.SpiderClass("net.minecraft.server.v1_9_R2.EntitySpider"));
            load(V1_9_R2.class, "Entity:Witch", new Witch.WitchClass("net.minecraft.server.v1_9_R2.EntityWitch"));
            load(V1_9_R2.class, "Entity:Zombie", new Zombie.ZombieClass("net.minecraft.server.v1_9_R2.EntityZombie"));
            load(V1_9_R2.class, "Entity:Villager", new Villager.VillagerClass("net.minecraft.server.v1_9_R2.EntityVillager"));
            load(V1_9_R2.class, "Entity:Shulker", new Shulker.ShulkerClass("net.minecraft.server.v1_9_R2.EntityShulker"));
            load(V1_9_R2.class, "Entity:Boat", new Boat.BoatClass("net.minecraft.server.v1_9_R2.EntityBoat"));
            load(V1_9_R2.class, "Entity:CaveSpider", new CaveSpider.CaveSpiderClass("net.minecraft.server.v1_9_R2.EntityCaveSpider"));

            load(V1_9_R2.class, "Entity:Ageable", new AgeableEntityLiving.AgeableEntityLivingClass("net.minecraft.server.v1_9_R2.EntityAgeable"));
            load(V1_9_R2.class, "Entity:Tameable", new TameableEntityLiving.TameableEntityLivingClass("net.minecraft.server.v1_9_R2.EntityTameableAnimal"));
            // EntityPlayer
            load(V1_9_R2.class, "GameProfile", new GameProfile.GameProfileClass("com.mojang.authlib.GameProfile"));
            load(V1_9_R2.class, "PropertyMap", new PropertyMap.PropertyMapClass("com.mojang.authlib.properties.PropertyMap"));
            load(V1_9_R2.class, "Property", new Property.PropertyClass("com.mojang.authlib.properties.Property"));
            //

            // Managers
            load(V1_9_R2.class, "PlayerInteractManager", new PlayerInteractManager.PlayerInteractManagerClass("net.minecraft.server.v1_9_R2.PlayerInteractManager"));
            //

            // DataWatcher
            load(V1_9_R2.class, "DataWatcher", new DataWatcher.DataWatcherClass("net.minecraft.server.v1_9_R2.DataWatcher"));
            load(V1_9_R2.class, "DataWatcherObject", new DataWatcherObject.DataWatcherObjectClass("net.minecraft.server.v1_9_R2.DataWatcherObject"));
            load(V1_9_R2.class, "DataWatcher:Item", new DataWatcherItem.DataWatcherItemClass("net.minecraft.server.v1_9_R2.DataWatcher$Item"));
            //

            // Scoreboard
            load(V1_9_R2.class, "CraftScoreboard", new CraftScoreboard.CraftScoreboardClass("org.bukkit.craftbukkit.v1_9_R2.scoreboard.CraftScoreboard"));
            load(V1_9_R2.class, "Scoreboard", new Scoreboard.ScoreboardClass("net.minecraft.server.v1_9_R2.Scoreboard"));

            load(V1_9_R2.class, "ScoreboardTeam", new ScoreboardTeam.ScoreboardTeamClass("net.minecraft.server.v1_9_R2.ScoreboardTeam"));
            load(V1_9_R2.class, "ScoreboardTeam:EnumTeamPush", new EnumTeamPushEnum.EnumTeamPushClass("net.minecraft.server.v1_9_R2.ScoreboardTeamBase$EnumTeamPush"));

            load(V1_9_R2.class, "ScoreboardTeamBase:EnumNameTagVisibility", new EnumNameTagVisibilityEnum.EnumNameTagVisibilityClass("net.minecraft.server.v1_9_R2.ScoreboardTeamBase$EnumNameTagVisibility"));
            //

            // Others
            load(V1_9_R2.class, "PlayerConnection", new PlayerConnection.PlayerConnectionClass("net.minecraft.server.v1_9_R2.PlayerConnection"));
            load(V1_9_R2.class, "NetworkManager", new NetworkManager.NetworkManagerClass("net.minecraft.server.v1_9_R2.NetworkManager"));

            load(V1_9_R2.class, "EnumChatFormat", new EnumChatFormatEnum.EnumChatFormatClass("net.minecraft.server.v1_9_R2.EnumChatFormat"));
            load(V1_9_R2.class, "EnumColor", new EnumColorEnum.EnumColorClass("net.minecraft.server.v1_9_R2.EnumColor"));
            load(V1_9_R2.class, "EnumItemSlot", new EnumItemSlotEnum.EnumItemSlotClass("net.minecraft.server.v1_9_R2.EnumItemSlot"));
            load(V1_9_R2.class, "EnumDirection", new EnumDirectionEnum.EnumDirectionClass("net.minecraft.server.v1_9_R2.EnumDirection"));
            //

            // Chat
            load(V1_9_R2.class, "IChatBaseComponent", new IChatBaseComponent.IChatBaseComponentClass("net.minecraft.server.v1_9_R2.IChatBaseComponent"));
            load(V1_9_R2.class, "ChatSerializer", new IChatBaseComponent.ChatSerializerClass("net.minecraft.server.v1_9_R2.IChatBaseComponent$ChatSerializer"));
            //

            // Objects
            load(V1_9_R2.class, "CraftWorld", new CraftWorld.CraftWorldClass("org.bukkit.craftbukkit.v1_9_R2.CraftWorld"));
            load(V1_9_R2.class, "World", new World.WorldClass("net.minecraft.server.v1_9_R2.World"));
            load(V1_9_R2.class, "Vector3f", new Vector3f.Vector3fClass("net.minecraft.server.v1_9_R2.Vector3f"));
            load(V1_9_R2.class, "Vec3D", new Vec3D.Vec3DClass("net.minecraft.server.v1_9_R2.Vec3D"));
            load(V1_9_R2.class, "BlockPosition", new BlockPosition.BlockPositionClass("net.minecraft.server.v1_9_R2.BlockPosition"));
            load(V1_9_R2.class, "CraftBlock", new CraftBlock.CraftBlockClass("org.bukkit.craftbukkit.v1_9_R2.block.CraftBlock"));
            load(V1_9_R2.class, "IBlockData", new IBlockData.IBlockDataClass("net.minecraft.server.v1_9_R2.IBlockData"));
            load(V1_9_R2.class, "Block", new Block.BlockClass("net.minecraft.server.v1_9_R2.Block"));
            load(V1_9_R2.class, "CraftMagicNumbers", new ClassExecutor("org.bukkit.craftbukkit.v1_9_R2.util.CraftMagicNumbers"));
            //

            // Items
            load(V1_9_R2.class, "ItemStack", new ItemStack.ItemStackClass("net.minecraft.server.v1_9_R2.ItemStack"));
            load(V1_9_R2.class, "CraftItemStack", new CraftItemStack.CraftItemStackClass("org.bukkit.craftbukkit.v1_9_R2.inventory.CraftItemStack"));
            //

            // Entity horse
            load(V1_9_R2.class, "EnumHorseType", new EnumHorseTypeEnum.EnumHorseTypeClass("net.minecraft.server.v1_9_R2.EnumHorseType"));
            //
        }
        
        return super.getClasses();
    }

    @Override
    public @NotNull String getName() {
        return "v1_9_R2";
    }

}
