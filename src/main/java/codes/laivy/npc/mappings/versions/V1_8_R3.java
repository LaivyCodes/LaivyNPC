package codes.laivy.npc.mappings.versions;

import codes.laivy.npc.mappings.Version;
import codes.laivy.npc.mappings.instances.EnumExecutor;
import codes.laivy.npc.mappings.instances.Executor;
import codes.laivy.npc.mappings.instances.FieldExecutor;
import codes.laivy.npc.mappings.instances.MethodExecutor;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.utils.classes.datawatcher.DataWatcher;
import codes.laivy.npc.mappings.utils.classes.datawatcher.WatchableObject;
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
import codes.laivy.npc.mappings.utils.classes.enums.EnumChatFormatEnum;
import codes.laivy.npc.mappings.utils.classes.enums.EnumColorEnum;
import codes.laivy.npc.mappings.utils.classes.enums.EnumNameTagVisibilityEnum;
import codes.laivy.npc.mappings.utils.classes.enums.EnumPlayerInfoActionEnum;
import codes.laivy.npc.mappings.utils.classes.gameprofile.GameProfile;
import codes.laivy.npc.mappings.utils.classes.gameprofile.Property;
import codes.laivy.npc.mappings.utils.classes.gameprofile.PropertyMap;
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
import io.netty.channel.Channel;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class V1_8_R3 extends V1_8_R2 {

    @Override
    protected boolean onLoad(@NotNull Class<? extends Version> version, @NotNull String key, @NotNull Executor executor) {
        if (version == V1_8_R2.class) {
            if (executor instanceof ClassExecutor && !(executor instanceof EnumExecutor)) {
                return false;
            } else if (executor instanceof MethodExecutor) {
            } else if (executor instanceof FieldExecutor) {
                switch (key) {
                    case "NetworkManager:channel":
                        load(V1_8_R3.class, key, new FieldExecutor(getClassExec("NetworkManager"), new ClassExecutor(Channel.class) { }, "channel", "Gets the channel of the player"));
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
            load(V1_8_R3.class, "NBTBase", new NBTBase.NBTBaseClass("net.minecraft.server.v1_8_R3.NBTBase"));

            load(V1_8_R3.class, "NBTBase:NBTTagByte", new NBTTagByte.NBTTagByteClass("net.minecraft.server.v1_8_R3.NBTTagByte"));
            load(V1_8_R3.class, "NBTBase:NBTTagByteArray", new NBTTagByteArray.NBTTagByteArrayClass("net.minecraft.server.v1_8_R3.NBTTagByteArray"));
            load(V1_8_R3.class, "NBTBase:NBTTagCompound", new NBTTagCompound.NBTTagCompoundClass("net.minecraft.server.v1_8_R3.NBTTagCompound"));
            load(V1_8_R3.class, "NBTBase:NBTTagDouble", new NBTTagDouble.NBTTagDoubleClass("net.minecraft.server.v1_8_R3.NBTTagDouble"));
            load(V1_8_R3.class, "NBTBase:NBTTagFloat", new NBTTagFloat.NBTTagFloatClass("net.minecraft.server.v1_8_R3.NBTTagFloat"));
            load(V1_8_R3.class, "NBTBase:NBTTagInt", new NBTTagInt.NBTTagIntClass("net.minecraft.server.v1_8_R3.NBTTagInt"));
            load(V1_8_R3.class, "NBTBase:NBTTagIntArray", new NBTTagIntArray.NBTTagIntArrayClass("net.minecraft.server.v1_8_R3.NBTTagIntArray"));
            load(V1_8_R3.class, "NBTBase:NBTTagList", new NBTTagList.NBTTagListClass("net.minecraft.server.v1_8_R3.NBTTagList"));
            load(V1_8_R3.class, "NBTBase:NBTTagLong", new NBTTagLong.NBTTagLongClass("net.minecraft.server.v1_8_R3.NBTTagLong"));
            load(V1_8_R3.class, "NBTBase:NBTTagShort", new NBTTagShort.NBTTagShortClass("net.minecraft.server.v1_8_R3.NBTTagShort"));
            load(V1_8_R3.class, "NBTBase:NBTTagString", new NBTTagString.NBTTagStringClass("net.minecraft.server.v1_8_R3.NBTTagString"));
            //

            // Packets
            load(V1_8_R3.class, "Packet", new Packet.PacketClass("net.minecraft.server.v1_8_R3.Packet"));
            load(V1_8_R3.class, "PacketPlayOutSpawnEntity", new EntitySpawnPacket.EntitySpawnPacketClass("net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntity"));
            load(V1_8_R3.class, "PacketPlayOutEntityDestroy", new EntityDestroyPacket.EntityDestroyPacketClass("net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy"));
            load(V1_8_R3.class, "PacketPlayOutAnimation", new EntityAnimationPacket.EntityAnimationPacketClass("net.minecraft.server.v1_8_R3.PacketPlayOutAnimation"));
            load(V1_8_R3.class, "PacketPlayOutSpawnEntityLiving", new EntityLivingSpawnPacket.EntityLivingSpawnPacketClass("net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving"));
            load(V1_8_R3.class, "PacketPlayOutEntityMetadata", new EntityMetadataPacket.EntityMetadataPacketClass("net.minecraft.server.v1_8_R3.PacketPlayOutEntityMetadata"));
            load(V1_8_R3.class, "PacketPlayOutNamedEntitySpawn", new EntityNamedSpawnPacket.EntityNamedSpawnPacketClass("net.minecraft.server.v1_8_R3.PacketPlayOutNamedEntitySpawn"));
            load(V1_8_R3.class, "PacketPlayOutPlayerInfo", new PlayerInfoPacket.PlayerInfoPacketClass("net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo"));
            load(V1_8_R3.class, "PacketPlayOutPlayerInfo:EnumPlayerInfoAction", new EnumPlayerInfoActionEnum.EnumPlayerInfoActionClass("net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo$EnumPlayerInfoAction"));
            load(V1_8_R3.class, "PacketPlayOutScoreboardTeam", new ScoreboardTeamPacket.ScoreboardTeamPacketClass("net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardTeam"));
            load(V1_8_R3.class, "PacketPlayOutEntityEquipment", new EntityEquipmentPacket.EntityEquipmentPacketClass("net.minecraft.server.v1_8_R3.PacketPlayOutEntityEquipment"));
            load(V1_8_R3.class, "PacketPlayOutEntityTeleport", new EntityTeleportPacket.EntityTeleportPacketClass("net.minecraft.server.v1_8_R3.PacketPlayOutEntityTeleport"));
            load(V1_8_R3.class, "PacketPlayOutEntityHeadRotation", new EntityHeadRotationPacket.EntityHeadRotationPacketClass("net.minecraft.server.v1_8_R3.PacketPlayOutEntityHeadRotation"));
            load(V1_8_R3.class, "PacketPlayOutEntityLook", new EntityLookPacket.EntityLookPacketClass("net.minecraft.server.v1_8_R3.PacketPlayOutEntity$PacketPlayOutEntityLook"));

            load(V1_8_R3.class, "PacketPlayInUseEntity", new EntityUseInPacket.EntityUseInPacketClass("net.minecraft.server.v1_8_R3.PacketPlayInUseEntity"));
            load(V1_8_R3.class, "PacketPlayInUseEntity:EnumEntityUseAction", new EntityUseInPacket.ActionEnum.ActionClass("net.minecraft.server.v1_8_R3.PacketPlayInUseEntity$EnumEntityUseAction"));
            //

            // Server
            load(V1_8_R3.class, "MinecraftServer", new MinecraftServer.MinecraftServerClass("net.minecraft.server.v1_8_R3.MinecraftServer"));
            load(V1_8_R3.class, "WorldServer", new WorldServer.WorldServerClass("net.minecraft.server.v1_8_R3.WorldServer"));
            load(V1_8_R3.class, "CraftServer", new CraftServer.CraftServerClass("org.bukkit.craftbukkit.v1_8_R3.CraftServer"));
            //

            // Entity
            load(V1_8_R3.class, "Entity", new Entity.EntityClass("net.minecraft.server.v1_8_R3.Entity"));
            load(V1_8_R3.class, "EntityLiving", new EntityLiving.EntityLivingClass("net.minecraft.server.v1_8_R3.EntityLiving"));
            load(V1_8_R3.class, "Entity:Human", new Entity.EntityClass("net.minecraft.server.v1_8_R3.EntityHuman"));
            load(V1_8_R3.class, "CraftPlayer", new CraftPlayer.CraftPlayerClass("org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer"));
            load(V1_8_R3.class, "EntityPlayer", new EntityPlayer.EntityPlayerClass("net.minecraft.server.v1_8_R3.EntityPlayer"));

            load(V1_8_R3.class, "Entity:ArmorStand", new ArmorStand.ArmorStandClass("net.minecraft.server.v1_8_R3.EntityArmorStand"));
            load(V1_8_R3.class, "Entity:Pig", new Pig.PigClass("net.minecraft.server.v1_8_R3.EntityPig"));
            load(V1_8_R3.class, "Entity:Cow", new Cow.CowClass("net.minecraft.server.v1_8_R3.EntityCow"));
            load(V1_8_R3.class, "Entity:Ocelot", new Ocelot.OcelotClass("net.minecraft.server.v1_8_R3.EntityOcelot"));
            load(V1_8_R3.class, "Entity:Bat", new Bat.BatClass("net.minecraft.server.v1_8_R3.EntityBat"));
            load(V1_8_R3.class, "Entity:Egg", new Egg.EggClass("net.minecraft.server.v1_8_R3.EntityEgg"));
            load(V1_8_R3.class, "Entity:Chicken", new Chicken.ChickenClass("net.minecraft.server.v1_8_R3.EntityChicken"));
            load(V1_8_R3.class, "Entity:Horse", new Horse.HorseClass("net.minecraft.server.v1_8_R3.EntityHorse"));
            load(V1_8_R3.class, "Entity:IronGolem", new IronGolem.IronGolemClass("net.minecraft.server.v1_8_R3.EntityIronGolem"));
            load(V1_8_R3.class, "Entity:Rabbit", new Rabbit.RabbitClass("net.minecraft.server.v1_8_R3.EntityRabbit"));
            load(V1_8_R3.class, "Entity:Sheep", new Sheep.SheepClass("net.minecraft.server.v1_8_R3.EntitySheep"));
            load(V1_8_R3.class, "Entity:Snowman", new Snowman.SnowmanClass("net.minecraft.server.v1_8_R3.EntitySnowman"));
            load(V1_8_R3.class, "Entity:Squid", new Squid.SquidClass("net.minecraft.server.v1_8_R3.EntitySquid"));
            load(V1_8_R3.class, "Entity:Wolf", new Wolf.WolfClass("net.minecraft.server.v1_8_R3.EntityWolf"));
            load(V1_8_R3.class, "Entity:ItemFrame", new ItemFrame.ItemFrameClass("net.minecraft.server.v1_8_R3.EntityItemFrame"));
            load(V1_8_R3.class, "Entity:LeashKnot", new LeashKnot.LeashKnotClass("net.minecraft.server.v1_8_R3.EntityLeash"));
            load(V1_8_R3.class, "Entity:FallingBlock", new FallingBlock.FallingBlockClass("net.minecraft.server.v1_8_R3.EntityFallingBlock"));
            load(V1_8_R3.class, "Entity:Item", new Item.ItemClass("net.minecraft.server.v1_8_R3.EntityItem"));
            load(V1_8_R3.class, "Entity:EnderDragon", new EnderDragon.EnderDragonClass("net.minecraft.server.v1_8_R3.EntityEnderDragon"));
            load(V1_8_R3.class, "Entity:EnderSignal", new EnderSignal.EnderSignalClass("net.minecraft.server.v1_8_R3.EntityEnderSignal"));
            load(V1_8_R3.class, "Entity:Wither", new Wither.WitherClass("net.minecraft.server.v1_8_R3.EntityWither"));
            load(V1_8_R3.class, "Entity:WitherSkull", new WitherSkull.WitherSkullClass("net.minecraft.server.v1_8_R3.EntityWitherSkull"));
            load(V1_8_R3.class, "Entity:Blaze", new Blaze.BlazeClass("net.minecraft.server.v1_8_R3.EntityBlaze"));
            load(V1_8_R3.class, "Entity:Creeper", new Creeper.CreeperClass("net.minecraft.server.v1_8_R3.EntityCreeper"));
            load(V1_8_R3.class, "Entity:Enderman", new Enderman.EndermanClass("net.minecraft.server.v1_8_R3.EntityEnderman"));
            load(V1_8_R3.class, "Entity:Ghast", new Ghast.GhastClass("net.minecraft.server.v1_8_R3.EntityGhast"));
            load(V1_8_R3.class, "Entity:Guardian", new Guardian.GuardianClass("net.minecraft.server.v1_8_R3.EntityGuardian"));
            load(V1_8_R3.class, "Entity:Silverfish", new Silverfish.SilverfishClass("net.minecraft.server.v1_8_R3.EntitySilverfish"));
            load(V1_8_R3.class, "Entity:Skeleton", new Skeleton.SkeletonClass("net.minecraft.server.v1_8_R3.EntitySkeleton"));
            load(V1_8_R3.class, "Entity:Slime", new Slime.SlimeClass("net.minecraft.server.v1_8_R3.EntitySlime"));
            load(V1_8_R3.class, "Entity:Spider", new Spider.SpiderClass("net.minecraft.server.v1_8_R3.EntitySpider"));
            load(V1_8_R3.class, "Entity:Witch", new Witch.WitchClass("net.minecraft.server.v1_8_R3.EntityWitch"));
            load(V1_8_R3.class, "Entity:Zombie", new Zombie.ZombieClass("net.minecraft.server.v1_8_R3.EntityZombie"));
            load(V1_8_R3.class, "Entity:Villager", new Villager.VillagerClass("net.minecraft.server.v1_8_R3.EntityVillager"));

            load(V1_8_R3.class, "Entity:Ageable", new AgeableLivingEntity.AgeableLivingEntityClass("net.minecraft.server.v1_8_R3.EntityAgeable"));
            load(V1_8_R3.class, "Entity:Tameable", new TameableLivingEntity.TameableLivingEntityClass("net.minecraft.server.v1_8_R3.EntityTameableAnimal"));
            // EntityPlayer
            load(V1_8_R3.class, "GameProfile", new GameProfile.GameProfileClass("com.mojang.authlib.GameProfile"));
            load(V1_8_R3.class, "PropertyMap", new PropertyMap.PropertyMapClass("com.mojang.authlib.properties.PropertyMap"));
            load(V1_8_R3.class, "Property", new Property.PropertyClass("com.mojang.authlib.properties.Property"));
            //

            // Managers
            load(V1_8_R3.class, "PlayerInteractManager", new PlayerInteractManager.PlayerInteractManagerClass("net.minecraft.server.v1_8_R3.PlayerInteractManager"));
            //

            // DataWatcher
            load(V1_8_R3.class, "DataWatcher", new DataWatcher.DataWatcherClass("net.minecraft.server.v1_8_R3.DataWatcher"));
            load(V1_8_R3.class, "WatchableObject", new WatchableObject.WatchableObjectClass("net.minecraft.server.v1_8_R3.DataWatcher$WatchableObject"));
            //

            // Scoreboard
            load(V1_8_R3.class, "CraftScoreboard", new CraftScoreboard.CraftScoreboardClass("org.bukkit.craftbukkit.v1_8_R3.scoreboard.CraftScoreboard"));
            load(V1_8_R3.class, "Scoreboard", new Scoreboard.ScoreboardClass("net.minecraft.server.v1_8_R3.Scoreboard"));
            load(V1_8_R3.class, "ScoreboardTeam", new ScoreboardTeam.ScoreboardTeamClass("net.minecraft.server.v1_8_R3.ScoreboardTeam"));

            load(V1_8_R3.class, "ScoreboardTeamBase:EnumNameTagVisibility", new EnumNameTagVisibilityEnum.EnumNameTagVisibilityClass("net.minecraft.server.v1_8_R3.ScoreboardTeamBase$EnumNameTagVisibility"));
            //

            // Others
            load(V1_8_R3.class, "PlayerConnection", new PlayerConnection.PlayerConnectionClass("net.minecraft.server.v1_8_R3.PlayerConnection"));
            load(V1_8_R3.class, "NetworkManager", new NetworkManager.NetworkManagerClass("net.minecraft.server.v1_8_R3.NetworkManager"));

            load(V1_8_R3.class, "EnumChatFormat", new EnumChatFormatEnum.EnumChatFormatClass("net.minecraft.server.v1_8_R3.EnumChatFormat"));
            load(V1_8_R3.class, "EnumColor", new EnumColorEnum.EnumColorClass("net.minecraft.server.v1_8_R3.EnumColor"));
            //

            // Chat
            load(V1_8_R3.class, "IChatBaseComponent", new IChatBaseComponent.IChatBaseComponentClass("net.minecraft.server.v1_8_R3.IChatBaseComponent"));
            load(V1_8_R3.class, "ChatSerializer", new IChatBaseComponent.ChatSerializerClass("net.minecraft.server.v1_8_R3.IChatBaseComponent$ChatSerializer"));
            //

            // Objects
            load(V1_8_R3.class, "CraftWorld", new CraftWorld.CraftWorldClass("org.bukkit.craftbukkit.v1_8_R3.CraftWorld"));
            load(V1_8_R3.class, "World", new World.WorldClass("net.minecraft.server.v1_8_R3.World"));
            load(V1_8_R3.class, "Vector3f", new Vector3f.Vector3fClass("net.minecraft.server.v1_8_R3.Vector3f"));
            load(V1_8_R3.class, "BlockPosition", new BlockPosition.BlockPositionClass("net.minecraft.server.v1_8_R3.BlockPosition"));
            load(V1_8_R3.class, "CraftBlock", new CraftBlock.CraftBlockClass("org.bukkit.craftbukkit.v1_8_R3.block.CraftBlock"));
            load(V1_8_R3.class, "IBlockData", new IBlockData.IBlockDataClass("net.minecraft.server.v1_8_R3.IBlockData"));
            load(V1_8_R3.class, "Block", new Block.BlockClass("net.minecraft.server.v1_8_R3.Block"));
            load(V1_8_R3.class, "CraftMagicNumbers", new ClassExecutor("org.bukkit.craftbukkit.v1_8_R3.util.CraftMagicNumbers"));
            //

            // Items
            load(V1_8_R3.class, "ItemStack", new ItemStack.ItemStackClass("net.minecraft.server.v1_8_R3.ItemStack"));
            load(V1_8_R3.class, "CraftItemStack", new CraftItemStack.CraftItemStackClass("org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack"));
            //
        }
        
        return super.getClasses();
    }

    @Override
    public @NotNull String getName() {
        return "v1_8_R3";
    }

}
