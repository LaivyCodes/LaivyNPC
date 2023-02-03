package codes.laivy.npc.mappings.versions;

import codes.laivy.npc.mappings.Version;
import codes.laivy.npc.mappings.instances.*;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.EnumExecutor;
import codes.laivy.npc.mappings.instances.FieldExecutor;
import codes.laivy.npc.mappings.instances.MethodExecutor;
import codes.laivy.npc.mappings.utils.VersionCompound;
import codes.laivy.npc.mappings.utils.classes.datawatcher.VersionedDataWatcherObject;
import codes.laivy.npc.mappings.utils.classes.entity.animal.*;
import codes.laivy.npc.mappings.utils.classes.entity.animal.horse.Horse;
import codes.laivy.npc.mappings.utils.classes.entity.boss.dragon.EnderDragon;
import codes.laivy.npc.mappings.utils.classes.entity.boss.dragon.EnderSignal;
import codes.laivy.npc.mappings.utils.classes.entity.boss.wither.Wither;
import codes.laivy.npc.mappings.utils.classes.entity.boss.wither.WitherSkull;
import codes.laivy.npc.mappings.utils.classes.entity.decoration.ItemFrame;
import codes.laivy.npc.mappings.utils.classes.entity.decoration.LeashKnot;
import codes.laivy.npc.mappings.utils.classes.entity.item.FallingBlock;
import codes.laivy.npc.mappings.utils.classes.entity.item.Item;
import codes.laivy.npc.mappings.utils.classes.entity.monster.*;
import codes.laivy.npc.mappings.utils.classes.entity.npc.Villager;
import codes.laivy.npc.mappings.utils.classes.entity.npc.VillagerProfession;
import codes.laivy.npc.mappings.utils.classes.enums.*;
import codes.laivy.npc.mappings.utils.classes.datawatcher.DataWatcher;
import codes.laivy.npc.mappings.utils.classes.datawatcher.WatchableObject;
import codes.laivy.npc.mappings.utils.classes.entity.*;
import codes.laivy.npc.mappings.utils.classes.entity.ambient.Bat;
import codes.laivy.npc.mappings.utils.classes.entity.ambient.Egg;
import codes.laivy.npc.mappings.utils.classes.entity.decoration.ArmorStand;
import codes.laivy.npc.mappings.utils.classes.gameprofile.GameProfile;
import codes.laivy.npc.mappings.utils.classes.gameprofile.Property;
import codes.laivy.npc.mappings.utils.classes.gameprofile.PropertyMap;
import codes.laivy.npc.mappings.utils.classes.java.*;
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
import static codes.laivy.npc.mappings.utils.classes.others.objects.ItemStack.*;

public class V1_8_R1 extends Version {

    @Override
    protected boolean onLoad(@NotNull Class<? extends Version> version, @NotNull String key, @NotNull Executor executor) {
        return true;
    }

    @Override
    public @NotNull Map<String, ClassExecutor> getClasses() {
        if (super.getClasses().isEmpty()) {
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
            load(V1_8_R1.class, "Entity:Horse", new Horse.HorseClass("net.minecraft.server.v1_8_R1.EntityHorse"));
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

            load(V1_8_R1.class, "Entity:Ageable", new AgeableLivingEntity.AgeableLivingEntityClass("net.minecraft.server.v1_8_R1.EntityAgeable"));
            load(V1_8_R1.class, "Entity:Tameable", new TameableLivingEntity.TameableLivingEntityClass("net.minecraft.server.v1_8_R1.EntityTameableAnimal"));
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
            load(V1_8_R1.class, "BlockPosition", new BlockPosition.BlockPositionClass("net.minecraft.server.v1_8_R1.BlockPosition"));
            load(V1_8_R1.class, "CraftBlock", new CraftBlock.CraftBlockClass("org.bukkit.craftbukkit.v1_8_R1.block.CraftBlock"));
            load(V1_8_R1.class, "IBlockData", new IBlockData.IBlockDataClass("net.minecraft.server.v1_8_R1.IBlockData"));
            load(V1_8_R1.class, "Block", new codes.laivy.npc.mappings.utils.classes.others.objects.Block.BlockClass("net.minecraft.server.v1_8_R1.Block"));
            load(V1_8_R1.class, "CraftMagicNumbers", new ClassExecutor("org.bukkit.craftbukkit.v1_8_R1.util.CraftMagicNumbers"));
            //

            // Items
            load(V1_8_R1.class, "ItemStack", new ItemStackClass("net.minecraft.server.v1_8_R1.ItemStack"));
            load(V1_8_R1.class, "CraftItemStack", new CraftItemStack.CraftItemStackClass("org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack"));
            //
        }

        return super.getClasses();
    }

    @Override
    public @NotNull Map<String, MethodExecutor> getMethods() {
        if (super.getMethods().isEmpty()) {
            // NBT
            load(V1_8_R1.class, "NBTTagCompound:set", new MethodExecutor(getClassExec("NBTBase:NBTTagCompound"), ClassExecutor.VOID, "set", "Sets a value inside a NBTTagCompound", ClassExecutor.STRING, getClassExec("NBTBase")));
            load(V1_8_R1.class, "NBTTagCompound:get", new MethodExecutor(getClassExec("NBTBase:NBTTagCompound"), getClassExec("NBTBase"), "get", "Gets a value inside a NBTTagCompound", ClassExecutor.STRING));
            load(V1_8_R1.class, "NBTTagCompound:remove", new MethodExecutor(getClassExec("NBTBase:NBTTagCompound"), ClassExecutor.VOID, "remove", "Removes a value from a NBTTagCompound", ClassExecutor.STRING));
            load(V1_8_R1.class, "NBTTagCompound:contains", new MethodExecutor(getClassExec("NBTBase:NBTTagCompound"), ClassExecutor.BOOLEAN, "hasKey", "Check if a NBTTagCompound contains a key", ClassExecutor.STRING));
            load(V1_8_R1.class, "NBTTagCompound:isEmpty", new MethodExecutor(getClassExec("NBTBase:NBTTagCompound"), ClassExecutor.BOOLEAN, "isEmpty", "Check if a NBTTagCompound is empty"));
            load(V1_8_R1.class, "NBTTagCompound:keySet", new MethodExecutor(getClassExec("NBTBase:NBTTagCompound"), new ClassExecutor("java.util.Set") {}, "c", "Gets a NBTTagCompound's keys"));
            //

            // Entity
            load(V1_8_R1.class, "Entity:CraftPlayer:getHandle", new MethodExecutor(getClassExec("CraftPlayer"), getClassExec("EntityPlayer"), "getHandle", "Gets the NMS EntityPlayer from a CraftPlayer"));
            load(V1_8_R1.class, "Entity:Entity:getId", new MethodExecutor(getClassExec("Entity"), ClassExecutor.INT, "getId", "Gets the entity id of a Entity"));
            load(V1_8_R1.class, "Entity:Entity:getDataWatcher", new MethodExecutor(getClassExec("Entity"), getClassExec("DataWatcher"), "getDataWatcher", "Gets the DataWatcher of a Entity"));
            load(V1_8_R1.class, "Entity:Entity:setLocation", new MethodExecutor(getClassExec("Entity"), ClassExecutor.VOID, "setLocation", "Sets the Entity's location", ClassExecutor.DOUBLE, ClassExecutor.DOUBLE, ClassExecutor.DOUBLE, ClassExecutor.FLOAT, ClassExecutor.FLOAT));
            load(V1_8_R1.class, "Entity:Entity:getName", new MethodExecutor(getClassExec("Entity"), ClassExecutor.STRING, "getName", "Gets the Entity's name"));
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
            // Ghast
            load(V1_8_R1.class, "Entity:Ghast:setAttacking", new MethodExecutor(getClassExec("Entity:Ghast"), ClassExecutor.VOID, "a", "Sets the attacking state of a Ghast", ClassExecutor.BOOLEAN));
            // Skeleton
            load(V1_8_R1.class, "Entity:Skeleton:getSkeletonType", new MethodExecutor(getClassExec("Entity:Skeleton"), ClassExecutor.INT, "getSkeletonType", "Gets the skeleton type of the Skeleton"));
            load(V1_8_R1.class, "Entity:Skeleton:setSkeletonType", new MethodExecutor(getClassExec("Entity:Skeleton"), ClassExecutor.VOID, "setSkeletonType", "Sets the skeleton type of a Skeleton", ClassExecutor.INT));
            // ArmorStand
            load(V1_8_R1.class, "Entity:ArmorStand:setHeadPose", new MethodExecutor(getClassExec("Entity:ArmorStand"), ClassExecutor.VOID, "setHeadPose", "Sets the head pose of the armor stand", getClassExec("Vector3f")));
            load(V1_8_R1.class, "Entity:ArmorStand:setBodyPose", new MethodExecutor(getClassExec("Entity:ArmorStand"), ClassExecutor.VOID, "setBodyPose", "Sets the body pose of the armor stand", getClassExec("Vector3f")));
            load(V1_8_R1.class, "Entity:ArmorStand:setLeftArmPose", new MethodExecutor(getClassExec("Entity:ArmorStand"), ClassExecutor.VOID, "setLeftArmPose", "Sets the left arm pose of the armor stand", getClassExec("Vector3f")));
            load(V1_8_R1.class, "Entity:ArmorStand:setRightArmPose", new MethodExecutor(getClassExec("Entity:ArmorStand"), ClassExecutor.VOID, "setRightArmPose", "Sets the right arm pose of the armor stand", getClassExec("Vector3f")));
            load(V1_8_R1.class, "Entity:ArmorStand:setLeftLegPose", new MethodExecutor(getClassExec("Entity:ArmorStand"), ClassExecutor.VOID, "setLeftLegPose", "Sets the head left leg of the armor stand", getClassExec("Vector3f")));
            load(V1_8_R1.class, "Entity:ArmorStand:setRightLegPose", new MethodExecutor(getClassExec("Entity:ArmorStand"), ClassExecutor.VOID, "setRightLegPose", "Sets the head right leg of the armor stand", getClassExec("Vector3f")));
            // Bat
            load(V1_8_R1.class, "Entity:Bat:isAsleep", new MethodExecutor(getClassExec("Entity:Bat"), ClassExecutor.BOOLEAN, "isAsleep", "Check if a bat is sleeping"));
            load(V1_8_R1.class, "Entity:Bat:setAsleep", new MethodExecutor(getClassExec("Entity:Bat"), ClassExecutor.VOID, "setAsleep", "Sets the sleeping state of a bat", ClassExecutor.BOOLEAN));
            // Horse
            load(V1_8_R1.class, "Entity:Horse:getType", new MethodExecutor(getClassExec("Entity:Horse"), ClassExecutor.INT, "getType", "Gets the horse type"));
            load(V1_8_R1.class, "Entity:Horse:setType", new MethodExecutor(getClassExec("Entity:Horse"), ClassExecutor.VOID, "setType", "Sets the horse type", ClassExecutor.INT));
            // Slime
            load(V1_8_R1.class, "Entity:Slime:getSize", new MethodExecutor(getClassExec("Entity:Slime"), ClassExecutor.INT, "getSize", "Gets the slime size"));
            load(V1_8_R1.class, "Entity:Slime:setSize", new MethodExecutor(getClassExec("Entity:Slime"), ClassExecutor.VOID, "setSize", "Sets the slime size", ClassExecutor.INT));
            // Spider
            load(V1_8_R1.class, "Entity:Spider:isClimbing", new MethodExecutor(getClassExec("Entity:Spider"), ClassExecutor.BOOLEAN, "n", "Gets the spider climbing state"));
            load(V1_8_R1.class, "Entity:Spider:setClimbing", new MethodExecutor(getClassExec("Entity:Spider"), ClassExecutor.VOID, "a", "Sets the spider climbing state", ClassExecutor.BOOLEAN));
            // Zombie
            load(V1_8_R1.class, "Entity:Zombie:isVillager", new MethodExecutor(getClassExec("Entity:Zombie"), ClassExecutor.BOOLEAN, "isVillager", "Gets the villager state of a Zombie"));
            load(V1_8_R1.class, "Entity:Zombie:setVillager", new MethodExecutor(getClassExec("Entity:Zombie"), ClassExecutor.VOID, "setVillager", "Sets the villager state of a Zombie", ClassExecutor.BOOLEAN));
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
            // Team
            load(V1_8_R1.class, "ScoreboardTeam:getPlayerNameSet", new MethodExecutor(getClassExec("ScoreboardTeam"), ClassExecutor.COLLECTION, "getPlayerNameSet", "Gets the player's list of this ScoreboardTeam"));
            load(V1_8_R1.class, "ScoreboardTeam:setNameTagVisibity", new MethodExecutor(getClassExec("ScoreboardTeam"), ClassExecutor.VOID, "a", "Sets the nametag visibility of a ScoreboardTeam", getClassExec("ScoreboardTeamBase:EnumNameTagVisibility")));
            load(V1_8_R1.class, "ScoreboardTeam:setColor", new MethodExecutor(getClassExec("ScoreboardTeam"), ClassExecutor.VOID, "a", "Sets the color of a ScoreboardTeam", getClassExec("EnumChatFormat")));
            load(V1_8_R1.class, "ScoreboardTeam:setPrefix", new MethodExecutor(getClassExec("ScoreboardTeam"), ClassExecutor.VOID, "setPrefix", "Sets the prefix of a ScoreboardTeam", ClassExecutor.STRING));
            load(V1_8_R1.class, "ScoreboardTeam:getName", new MethodExecutor(getClassExec("ScoreboardTeam"), ClassExecutor.STRING, "getName", "Gets the name of a ScoreboardTeam"));
            //

            // GameProfile
            load(V1_8_R1.class, "GameProfile:getProperties", new MethodExecutor(getClassExec("GameProfile"), getClassExec("PropertyMap"), "getProperties", "Gets the properties of a GameProfile"));
            load(V1_8_R1.class, "GameProfile:getName", new MethodExecutor(getClassExec("GameProfile"), ClassExecutor.STRING, "getName", "Gets the name of a GameProfile"));
            //

            // Location
            load(V1_8_R1.class, "CraftWorld:getHandle", new MethodExecutor(getClassExec("CraftWorld"), getClassExec("WorldServer"), "getHandle", "Gets the NMS WorldServer from a CraftWorld"));
            //

            // PlayerConnection & NetworkManager
            load(V1_8_R1.class, "PlayerConnection:sendPacket", new MethodExecutor(getClassExec("PlayerConnection"), ClassExecutor.VOID, "sendPacket", "Sends a packet to a player", getClassExec("Packet")));
            load(V1_8_R1.class, "NetworkManager:getNetworkManager", new MethodExecutor(getClassExec("PlayerConnection"), getClassExec("NetworkManager"), "a", "Gets the NetworkManager from a PlayerConnection"));
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
            //

            // World
            load(V1_8_R1.class, "World:getEntityById", new MethodExecutor(getClassExec("World"), getClassExec("Entity"), "a", "Gets a entity by its ID", ClassExecutor.INT));
            //

            // Vector3f
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

        return super.getMethods();
    }
    @Override
    public @NotNull Map<String, FieldExecutor> getFields() {
        if (super.getFields().isEmpty()) {
            // NBT
            load(V1_8_R1.class, "NBTTagList:list", new FieldExecutor(getClassExec("NBTBase:NBTTagList"), new ClassExecutor(List.class) {}, "list", "Gets the list of a NBTTagList"));
            //

            // CraftPlayer
            load(V1_8_R1.class, "EntityPlayer:playerConnection", new FieldExecutor(getClassExec("EntityPlayer"), getClassExec("PlayerConnection"), "playerConnection", "Gets the PlayerConnection from the player"));
            //

            // NetworkManager
            load(V1_8_R1.class, "NetworkManager:channel", new FieldExecutor(getClassExec("NetworkManager"), new ClassExecutor(Channel.class) {}, "i", "Gets the channel of the player"));
            //

            // PacketPlayInUseEntity
            load(V1_8_R1.class, "PacketPlayInUseEntity:entityId", new FieldExecutor(getClassExec("PacketPlayInUseEntity"), ClassExecutor.INT, "a", "Get the entity id of a PacketPlayInUseEntity"));
            //

            // ArmorStand
            load(V1_8_R1.class, "Entity:ArmorStand:headPose", new FieldExecutor(getClassExec("Entity:ArmorStand"), getClassExec("Vector3f"), "headPose", "Gets the head pose of a ArmorStand"));
            load(V1_8_R1.class, "Entity:ArmorStand:bodyPose", new FieldExecutor(getClassExec("Entity:ArmorStand"), getClassExec("Vector3f"), "bodyPose", "Gets the body pose of a ArmorStand"));
            load(V1_8_R1.class, "Entity:ArmorStand:leftArmPose", new FieldExecutor(getClassExec("Entity:ArmorStand"), getClassExec("Vector3f"), "leftArmPose", "Gets the left arm pose of a ArmorStand"));
            load(V1_8_R1.class, "Entity:ArmorStand:rightArmPose", new FieldExecutor(getClassExec("Entity:ArmorStand"), getClassExec("Vector3f"), "rightArmPose", "Gets the right arm pose of a ArmorStand"));
            load(V1_8_R1.class, "Entity:ArmorStand:leftLegPose", new FieldExecutor(getClassExec("Entity:ArmorStand"), getClassExec("Vector3f"), "rightLegPose", "Gets the left leg pose of a ArmorStand"));
            load(V1_8_R1.class, "Entity:ArmorStand:rightLegPose", new FieldExecutor(getClassExec("Entity:ArmorStand"), getClassExec("Vector3f"), "leftLegPose", "Gets the right leg pose of a ArmorStand"));
            //

            // Location
            load(V1_8_R1.class, "Entity:world", new FieldExecutor(getClassExec("Entity"), getClassExec("World"), "world", "Gets the world of a Entity"));
            load(V1_8_R1.class, "Entity:locX", new FieldExecutor(getClassExec("Entity"), ClassExecutor.DOUBLE, "locX", "Gets the X position of a Entity"));
            load(V1_8_R1.class, "Entity:locY", new FieldExecutor(getClassExec("Entity"), ClassExecutor.DOUBLE, "locY", "Gets the X position of a Entity"));
            load(V1_8_R1.class, "Entity:locZ", new FieldExecutor(getClassExec("Entity"), ClassExecutor.DOUBLE, "locZ", "Gets the X position of a Entity"));
            //

            // DataWatcher
            load(V1_8_R1.class, "DataWatcher:map", new FieldExecutor(getClassExec("DataWatcher"), new ClassExecutor(Map.class), "d", "Gets the values of the data"));
            //
        }

        return super.getFields();
    }
    @Override
    public @NotNull Map<String, EnumExecutor> getEnums() {
        if (super.getEnums().isEmpty()) {
            // EnumPlayerInfoAction
            load(V1_8_R1.class, "PacketPlayOutPlayerInfo:EnumPlayerInfoAction", new EnumPlayerInfoActionEnum(getClassExec("PacketPlayOutPlayerInfo:EnumPlayerInfoAction")));
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

        return super.getEnums();
    }
    @Override
    public @NotNull Map<String, String> getTexts() {
        if (super.getTexts().isEmpty()) {
            // EnumPlayerInfoAction
            super.getTexts().put("PacketPlayOutPlayerInfo:EnumPlayerInfoAction:ADD_PLAYER", "ADD_PLAYER");
            super.getTexts().put("PacketPlayOutPlayerInfo:EnumPlayerInfoAction:REMOVE_PLAYER", "REMOVE_PLAYER");
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
            //
        }

        return super.getTexts();
    }
    @Override
    public @NotNull Map<String, Object> getObjects() {
        if (super.getObjects().isEmpty()) {
            super.getObjects().put("Metadata:Player:SkinParts", 10);
            super.getObjects().put("Metadata:Ghast:Attacking", 16);
            super.getObjects().put("Metadata:Guardian:Target", 17);
            super.getObjects().put("Metadata:Creeper:Ignited", 18);
        }

        return super.getObjects();
    }

    @Override
    public @NotNull String getName() {
        return "v1_8_R1";
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
                    returnList.add(laivynpc().getVersion().nbtTag(NBTBase.getTagType(nbtbase), nbtbase));
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
                i.set(key, Objects.requireNonNull(laivynpc().getVersion().nbtCompound(VersionCompound.NBTCompoundAction.GET, f, new StringObjExec(key), null)));
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
    @SuppressWarnings("RedundantCast")
    public @NotNull NBTBase nbtTag(@NotNull NBTTag tag, @NotNull Object... objects) {
        try {
            Object object;
            Constructor<?> constructor = null;

            if (objects.length > 0 && getClassExec("NBTBase").isReflectiveInstance(objects[0])) {
                object = objects[0];

                if (tag == NBTTag.BYTE) {
                    if (!ClassUtils.isInstanceOf(getClassExec("NBTBase:NBTTagByte").getReflectionClass(), objects[0].getClass())) {
                        constructor = NBTTagByte.class.getDeclaredConstructor(Object.class);
                    }
                } else if (tag == NBTTag.BYTE_ARRAY) {
                    if (!ClassUtils.isInstanceOf(getClassExec("NBTBase:NBTTagByteArray").getReflectionClass(), objects[0].getClass())) {
                        constructor = NBTTagByteArray.class.getDeclaredConstructor(Object.class);
                    }
                } else if (tag == NBTTag.COMPOUND) {
                    if (!ClassUtils.isInstanceOf(getClassExec("NBTBase:NBTTagCompound").getReflectionClass(), objects[0].getClass())) {
                        constructor = NBTTagCompound.class.getDeclaredConstructor(Object.class);
                    }
                } else if (tag == NBTTag.DOUBLE) {
                    if (!ClassUtils.isInstanceOf(getClassExec("NBTBase:NBTTagDouble").getReflectionClass(), objects[0].getClass())) {
                        constructor = NBTTagDouble.class.getDeclaredConstructor(Object.class);
                    }
                } else if (tag == NBTTag.FLOAT) {
                    if (!ClassUtils.isInstanceOf(getClassExec("NBTBase:NBTTagFloat").getReflectionClass(), objects[0].getClass())) {
                        constructor = NBTTagFloat.class.getDeclaredConstructor(Object.class);
                    }
                } else if (tag == NBTTag.INT) {
                    if (!ClassUtils.isInstanceOf(getClassExec("NBTBase:NBTTagInt").getReflectionClass(), objects[0].getClass())) {
                        constructor = NBTTagInt.class.getDeclaredConstructor(Object.class);
                    }
                } else if (tag == NBTTag.INT_ARRAY) {
                    if (!ClassUtils.isInstanceOf(getClassExec("NBTBase:NBTTagIntArray").getReflectionClass(), objects[0].getClass())) {
                        constructor = NBTTagIntArray.class.getDeclaredConstructor(Object.class);
                    }
                } else if (tag == NBTTag.LIST) {
                    if (!ClassUtils.isInstanceOf(getClassExec("NBTBase:NBTTagList").getReflectionClass(), objects[0].getClass())) {
                        constructor = NBTTagList.class.getDeclaredConstructor(Object.class);
                    }
                } else if (tag == NBTTag.LONG) {
                    if (!ClassUtils.isInstanceOf(getClassExec("NBTBase:NBTTagLong").getReflectionClass(), objects[0].getClass())) {
                        constructor = NBTTagLong.class.getDeclaredConstructor(Object.class);
                    }
                } else if (tag == NBTTag.SHORT) {
                    if (!ClassUtils.isInstanceOf(getClassExec("NBTBase:NBTTagShort").getReflectionClass(), objects[0].getClass())) {
                        constructor = NBTTagShort.class.getDeclaredConstructor(Object.class);
                    }
                } else if (tag == NBTTag.STRING) {
                    if (!ClassUtils.isInstanceOf(getClassExec("NBTBase:NBTTagString").getReflectionClass(), objects[0].getClass())) {
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
                    constructor = getClassExec("NBTBase:NBTTagByte").getReflectionClass().getConstructor(byte.class);
                    object = constructor.newInstance((byte) objects[0]);
                    constructor = NBTTagByte.class.getDeclaredConstructor(Object.class);
                } else if (tag == NBTTag.BYTE_ARRAY) {
                    constructor = getClassExec("NBTBase:NBTTagByteArray").getReflectionClass().getConstructor(byte[].class);
                    //noinspection PrimitiveArrayArgumentToVarargsMethod
                    object = constructor.newInstance((byte[]) objects[0]);
                    constructor = NBTTagByteArray.class.getDeclaredConstructor(Object.class);
                } else if (tag == NBTTag.COMPOUND) {
                    constructor = getClassExec("NBTBase:NBTTagCompound").getReflectionClass().getConstructor();
                    object = constructor.newInstance();
                    constructor = NBTTagCompound.class.getDeclaredConstructor(Object.class);
                } else if (tag == NBTTag.DOUBLE) {
                    constructor = getClassExec("NBTBase:NBTTagDouble").getReflectionClass().getConstructor(double.class);
                    object = constructor.newInstance((double) objects[0]);
                    constructor = NBTTagDouble.class.getDeclaredConstructor(Object.class);
                } else if (tag == NBTTag.FLOAT) {
                    constructor = getClassExec("NBTBase:NBTTagFloat").getReflectionClass().getConstructor(float.class);
                    object = constructor.newInstance((float) objects[0]);
                    constructor = NBTTagFloat.class.getDeclaredConstructor(Object.class);
                } else if (tag == NBTTag.INT) {
                    constructor = getClassExec("NBTBase:NBTTagInt").getReflectionClass().getConstructor(int.class);
                    object = constructor.newInstance((int) objects[0]);
                    constructor = NBTTagInt.class.getDeclaredConstructor(Object.class);
                } else if (tag == NBTTag.INT_ARRAY) {
                    constructor = getClassExec("NBTBase:NBTTagIntArray").getReflectionClass().getConstructor(int[].class);
                    //noinspection PrimitiveArrayArgumentToVarargsMethod
                    object = constructor.newInstance((int[]) objects[0]);
                    constructor = NBTTagIntArray.class.getDeclaredConstructor(Object.class);
                } else if (tag == NBTTag.LIST) {
                    constructor = getClassExec("NBTBase:NBTTagList").getReflectionClass().getConstructor();
                    object = constructor.newInstance();
                    constructor = NBTTagList.class.getDeclaredConstructor(Object.class);
                } else if (tag == NBTTag.LONG) {
                    constructor = getClassExec("NBTBase:NBTTagLong").getReflectionClass().getConstructor(long.class);
                    object = constructor.newInstance((long) objects[0]);
                    constructor = NBTTagLong.class.getDeclaredConstructor(Object.class);
                } else if (tag == NBTTag.SHORT) {
                    constructor = getClassExec("NBTBase:NBTTagShort").getReflectionClass().getConstructor(short.class);
                    object = constructor.newInstance((short) objects[0]);
                    constructor = NBTTagShort.class.getDeclaredConstructor(Object.class);
                } else if (tag == NBTTag.STRING) {
                    constructor = getClassExec("NBTBase:NBTTagString").getReflectionClass().getConstructor(String.class);
                    object = constructor.newInstance((String) objects[0]);
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
    public @NotNull Entity createEntity(Entity.@NotNull EntityType type, @NotNull Location location) {
        if (location.getWorld() == null) {
            throw new NullPointerException("This location's world is null!");
        }

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
        } else if (type == Entity.EntityType.HORSE) {
            Object object = getClassExec("Entity:Horse").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new Horse(object);
            ((Horse) entity).setType(Horse.Type.HORSE);
        } else if (type == Entity.EntityType.HORSE_DONKEY) {
            Object object = getClassExec("Entity:Horse").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new Horse(object);
            ((Horse) entity).setType(Horse.Type.DONKEY);
        } else if (type == Entity.EntityType.HORSE_MULE) {
            Object object = getClassExec("Entity:Horse").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new Horse(object);
            ((Horse) entity).setType(Horse.Type.MULE);
        } else if (type == Entity.EntityType.HORSE_SKELETON) {
            Object object = getClassExec("Entity:Horse").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new Horse(object);
            ((Horse) entity).setType(Horse.Type.SKELETON);
        } else if (type == Entity.EntityType.HORSE_ZOMBIE) {
            Object object = getClassExec("Entity:Horse").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new Horse(object);
            ((Horse) entity).setType(Horse.Type.ZOMBIE);
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
        } else if (type == Entity.EntityType.VILLAGER) {
            Object object = getClassExec("Entity:Villager").getConstructor(getClassExec("World")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle());
            entity = new Villager(object);
        } else {
            throw new IllegalArgumentException("Cannot get this entity type '" + type.name() + "' for this version!");
        }

        entity.setLocation(location);
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
    public @NotNull Location getLocation(@NotNull Entity entity) {
        return new Location(
                (org.bukkit.World) new World(getFieldExec("Entity:world").invokeInstance(entity)).getCraftWorld().getValue(),
                (double) getFieldExec("Entity:locX").invokeInstance(entity),
                (double) getFieldExec("Entity:locY").invokeInstance(entity),
                (double) getFieldExec("Entity:locZ").invokeInstance(entity)
        );
    }

    @Override
    public @NotNull BlockPosition createBlockPosition(int x, int y, int z) {
        Object object = getClassExec("BlockPosition").getConstructor(ClassExecutor.INT, ClassExecutor.INT, ClassExecutor.INT).newInstance(new IntegerObjExec(x), new IntegerObjExec(y), new IntegerObjExec(z));
        return new BlockPosition(object);
    }

    @Override
    public @NotNull Ocelot.CatVariant getEntityCatVariant(@NotNull Ocelot ocelot) {
        //noinspection DataFlowIssue
        int id = (int) getMethodExec("Entity:Ocelot:getCatType").invokeInstance(ocelot);

        Ocelot.CatVariant variant = null;
        for (Ocelot.CatVariant v : Ocelot.CatVariant.values()) {
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
    public void setEntityCatVariant(@NotNull Ocelot ocelot, @NotNull Ocelot.CatVariant variant) {
        getMethodExec("Entity:Ocelot:setCatType").invokeInstance(ocelot, new IntegerObjExec(variant.getId()));
    }

    @Override
    public @NotNull Horse.Type getEntityHorseType(@NotNull Horse horse) {
        //noinspection DataFlowIssue
        return Horse.Type.getById((int) getMethodExec("Entity:Horse:getType").invokeInstance(horse));
    }
    @Override
    public void setEntityHorseType(@NotNull Horse horse, Horse.@NotNull Type type) {
        getMethodExec("Entity:Horse:setType").invokeInstance(horse, new IntegerObjExec(type.getId()));
    }

    @Override
    public @NotNull Rabbit.Variant getEntityRabbitType(@NotNull Rabbit rabbit) {
        //noinspection DataFlowIssue
        return Rabbit.Variant.getById((int) laivynpc().getVersion().getMethodExec("Entity:Rabbit:getVariant").invokeInstance(rabbit));
    }

    @Override
    public void setEntityRabbitType(@NotNull Rabbit rabbit, Rabbit.@NotNull Variant type) {
        laivynpc().getVersion().getMethodExec("Entity:Rabbit:setVariant").invokeInstance(rabbit, new IntegerObjExec(type.getId()));
    }

    @Override
    public @NotNull EnumColorEnum.EnumColor getEntitySheepColor(@NotNull Sheep sheep) {
        return new EnumColorEnum.EnumColor((Enum<?>) getMethodExec("Entity:Sheep:getColor").invokeInstance(sheep));
    }

    @Override
    public void setEntitySheepColor(@NotNull Sheep sheep, EnumColorEnum.@NotNull EnumColor color) {
        getMethodExec("Entity:Sheep:setColor").invokeInstance(sheep, color);
    }

    @Override
    public @NotNull Skeleton.SkeletonType getEntitySkeletonType(@NotNull Skeleton skeleton) {
        //noinspection DataFlowIssue
        int value = (int) getMethodExec("Entity:Skeleton:getSkeletonType").invokeInstance(skeleton);

        if (value == 0) {
            return Skeleton.SkeletonType.NORMAL;
        } else if (value == 1) {
            return Skeleton.SkeletonType.WITHER;
        } else {
            throw new IllegalStateException("Couldn't find this skeleton type '" + value + "'");
        }
    }

    @Override
    public void setEntitySkeletonType(@NotNull Skeleton skeleton, Skeleton.@NotNull SkeletonType type) {
        if (!type.isCompatible()) {
            throw new IllegalArgumentException("This skeleton type '" + type.name() + "' isn't compatible with that version!");
        }

        int value;
        if (type == Skeleton.SkeletonType.NORMAL) {
            value = 0;
        } else if (type == Skeleton.SkeletonType.WITHER) {
            value = 1;
        } else {
            throw new IllegalArgumentException("Couldn't find this skeleton type properties '" + type.name() + "'. Is it compatible with that version?");
        }

        getMethodExec("Entity:Skeleton:setSkeletonType").invokeInstance(skeleton, new IntegerObjExec(value));
    }

    @Override
    public @NotNull VillagerProfession getEntityVillagerProfession(@NotNull Villager villager) {
        //noinspection DataFlowIssue
        return new VillagerProfession(VillagerProfession.Type.getById((int) laivynpc().getVersion().getMethodExec("Entity:Villager:getProfession").invokeInstance(villager)), 1);
    }

    @Override
    public void setEntityVillagerProfession(@NotNull Villager villager, @NotNull VillagerProfession profession) {
        laivynpc().getVersion().getMethodExec("Entity:Villager:setProfession").invokeInstance(villager, new IntegerObjExec(profession.getType().getId()));
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
    public @Nullable Zombie.VillagerType getEntityZombieVillagerType(@NotNull Zombie zombie) {
        throw new IllegalArgumentException("This method is only compatible with 1.9+");
    }

    @Override
    public void setEntityZombieVillagerType(@NotNull Zombie zombie, Zombie.@Nullable VillagerType type) {
        laivynpc().getVersion().getMethodExec("Entity:Zombie:setVillager").invokeInstance(zombie, new BooleanObjExec(type != null));
    }

    @Override
    public boolean isEntityCreeperIgnited(@NotNull Creeper creeper) {
        //noinspection DataFlowIssue
        return ((byte) creeper.getDataWatcher().get((int) laivynpc().getVersion().getObject("Metadata:Creeper:Ignited"))) == 1;
    }

    @Override
    public void setEntityCreeperIgnited(@NotNull Creeper creeper, boolean flag) {
        creeper.getDataWatcher().set((int) laivynpc().getVersion().getObject("Metadata:Creeper:Ignited"), (byte) (flag ? 1 : 0));
    }

    @Override
    public boolean isEntityGhastAttacking(@NotNull Ghast ghast) {
        //noinspection DataFlowIssue
        return ((byte) ghast.getDataWatcher().get((int) laivynpc().getVersion().getObject("Metadata:Ghast:Attacking"))) == 1;
    }

    @Override
    public void setEntityGhastAttacking(@NotNull Ghast ghast, boolean flag) {
        ghast.getDataWatcher().set((int) laivynpc().getVersion().getObject("Metadata:Ghast:Attacking"), (byte) (flag ? 1 : 0));
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
    public boolean putInPropertyMap(@NotNull PropertyMap propertyMap, @NotNull String string, @NotNull Property property) {
        //noinspection unchecked,DataFlowIssue
        ((Multimap<String, Object>) new MethodExecutor(getClassExec("PropertyMap"), new ClassExecutor(Multimap.class), "delegate", "Gets the properties from a PropertyMap").invokeInstance(propertyMap)).put(string, property.getValue());
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
            getMethodExec("Entity:ArmorStand:setHeadPose").invokeInstance(stand, vector3f);
        } else if (pose == ArmorStand.Pose.BODY) {
            getMethodExec("Entity:ArmorStand:setBodyPose").invokeInstance(stand, vector3f);
        } else if (pose == ArmorStand.Pose.LEFT_ARM) {
            getMethodExec("Entity:ArmorStand:setLeftArmPose").invokeInstance(stand, vector3f);
        } else if (pose == ArmorStand.Pose.RIGHT_ARM) {
            getMethodExec("Entity:ArmorStand:setRightArmPose").invokeInstance(stand, vector3f);
        } else if (pose == ArmorStand.Pose.LEFT_LEG) {
            getMethodExec("Entity:ArmorStand:setLeftLegPose").invokeInstance(stand, vector3f);
        } else if (pose == ArmorStand.Pose.RIGHT_LEG) {
            getMethodExec("Entity:ArmorStand:setRightLegPose").invokeInstance(stand, vector3f);
        } else {
            throw new UnsupportedOperationException("Couldn't set this pose type '" + pose + "'");
        }
    }

    @Override
    public @NotNull Vector3f createVector3f(float x, float y, float z) {
        return new Vector3f(getClassExec("Vector3f").getConstructor(ClassExecutor.FLOAT, ClassExecutor.FLOAT, ClassExecutor.FLOAT).newInstance(new FloatObjExec(x), new FloatObjExec(y), new FloatObjExec(z)));
    }

    @Override
    public void sendPacket(@NotNull Packet packet, @NotNull PlayerConnection... connections) {
        for (PlayerConnection connection : connections) {
            getMethodExec("PlayerConnection:sendPacket").invokeInstance(connection, packet);
        }
    }

    @Override
    public @NotNull EntitySpawnPacket createSpawnPacket(@NotNull Entity entity) {
        Object packet = getClassExec("PacketPlayOutSpawnEntity").getConstructor(getClassExec("Entity"), ClassExecutor.INT).newInstance(entity, new IntegerObjExec(NonLivingEntityType.getByEntity(entity).getTypeId()));
        return new EntitySpawnPacket(packet);
    }

    @Override
    public @NotNull EntityDestroyPacket createDestroyPacket(@NotNull Entity... entities) {
        int[] ids = new int[entities.length];
        int row = 0;
        for (Entity entity : entities) {
            ids[row] = entity.getId();
            row++;
        }

        Object packet = getClassExec("PacketPlayOutEntityDestroy").getConstructor(ClassExecutor.INT_ARRAY).newInstance(new IntegerArrayObjExec(ids));
        return new EntityDestroyPacket(packet);
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
    public @NotNull PlayerInfoPacket createPlayerInfoPacket(@NotNull EnumPlayerInfoActionEnum.EnumPlayerInfoAction action, @NotNull EntityPlayer player) {
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
    public @NotNull EntityEquipmentPacket createEquipmentPacket(@NotNull Entity entity, @NotNull EnumItemSlotEnum.EnumItemSlot slot, @NotNull ItemStack item) {
        Object packet = getClassExec("PacketPlayOutEntityEquipment").getConstructor(ClassExecutor.INT, ClassExecutor.INT, getClassExec("ItemStack")).newInstance(new IntegerObjExec(entity.getId()), new IntegerObjExec(slot.getSlot()), getNMSItemStack(item));
        return new EntityEquipmentPacket(packet);
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
        FieldExecutor field = new FieldExecutor(getClassExec("PacketPlayInUseEntity"), getEnumExec("PacketPlayInUseEntity:EnumEntityUseAction"), "action", "Gets the action from entity in use packet");
        field.load();

        return new EntityUseInPacket.ActionEnum.Action((Enum<?>) field.invokeInstance(packet));
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public boolean addToTeam(@NotNull Scoreboard scoreboard, @NotNull ScoreboardTeam team, @NotNull Entity entity) {
        if (entity instanceof EntityPlayer) {
            return (boolean) getMethodExec("Scoreboard:addToTeam").invokeInstance(scoreboard, new StringObjExec(team.getName()), new StringObjExec(entity.getName()));
        } else {
            return (boolean) getMethodExec("Scoreboard:addToTeam").invokeInstance(scoreboard, new StringObjExec(team.getName()), new StringObjExec(entity.getId() + ""));
        }
    }

    @Override
    public @NotNull IChatBaseComponent stringToBaseComponent(@NotNull String string) {
        MethodExecutor method = new MethodExecutor(getClassExec("ChatSerializer"), getClassExec("IChatBaseComponent"), "a", "Converts a string to a IChatBaseComponent", ClassExecutor.STRING);
        method.load();

        return new IChatBaseComponent(method.invokeStatic(new StringObjExec("{\"text\":\"" + string + "\"}")));
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

}
