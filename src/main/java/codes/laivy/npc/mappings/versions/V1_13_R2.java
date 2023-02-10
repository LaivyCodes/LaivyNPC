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
import codes.laivy.npc.mappings.defaults.classes.enums.*;
import codes.laivy.npc.mappings.defaults.classes.gameprofile.GameProfile;
import codes.laivy.npc.mappings.defaults.classes.gameprofile.Property;
import codes.laivy.npc.mappings.defaults.classes.gameprofile.PropertyMap;
import codes.laivy.npc.mappings.defaults.classes.java.StringObjExec;
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
import codes.laivy.npc.mappings.instances.EnumExecutor;
import codes.laivy.npc.mappings.instances.Executor;
import codes.laivy.npc.mappings.instances.FieldExecutor;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Objects;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class V1_13_R2 extends V1_13_R1 {

    @Override
    protected boolean onLoad(@NotNull Class<? extends Version> version, @NotNull String key, @NotNull Executor executor) {
        if (version == V1_13_R1.class) {
            if (executor instanceof ClassExecutor && !(executor instanceof EnumExecutor)) {
                return false;
            } else if (executor instanceof FieldExecutor) {
                switch (key) {
                    case "Entity:PufferFish:DataWatcher:PuffState":
                        load(V1_13_R2.class, key, new FieldExecutor(getClassExec("Entity:PufferFish"), getClassExec("DataWatcherObject"), "a", "Gets the puffer fish's puff state DataWatcherObject"));
                        return false;
                    default:
                        break;
                }
            }
        }

        return super.onLoad(version, key, executor);
    }

    @Override
    public void setPrefix(@NotNull ScoreboardTeam team, @NotNull String prefix) {
        laivynpc().getVersion().getMethodExec("ScoreboardTeam:setPrefix").invokeInstance(team, IChatBaseComponent.convert(prefix));
    }

    @Override
    public boolean addToTeam(@NotNull Scoreboard scoreboard, @NotNull ScoreboardTeam team, @NotNull Entity entity) {
        String name;
        if (entity instanceof EntityPlayer) name = entity.getName();
        else name = String.valueOf(entity.getId());

        //noinspection DataFlowIssue
        return (boolean) getMethodExec("Scoreboard:addToTeam").invokeInstance(scoreboard, new StringObjExec(name), team);
    }

    @Override
    public @NotNull String getEntityCustomName(@NotNull Entity entity) {
        return new IChatBaseComponent(Objects.requireNonNull(getMethodExec("Entity:Entity:getCustomName").invokeInstance(entity))).getString();
    }
    @Override
    public void setEntityCustomName(@NotNull Entity entity, @NotNull String customName) {
        getMethodExec("Entity:Entity:setCustomName").invokeInstance(entity, stringToBaseComponent(customName));
    }

    @Override
    public @NotNull Map<String, ClassExecutor> getClasses() {
        if (super.getClasses().isEmpty()) {
            load(V1_13_R2.class, "WatchableObject", new ClassExecutor.BrokenClassExecutor());
            load(V1_13_R2.class, "EnumSkeletonType", new ClassExecutor.BrokenClassExecutor());
            load(V1_13_R2.class, "EnumHorseType", new ClassExecutor.BrokenClassExecutor());
            load(V1_13_R2.class, "EnumZombieType", new ClassExecutor.BrokenClassExecutor());

            load(V1_13_R2.class, "NBTBase", new NBTBase.NBTBaseClass("net.minecraft.server.v1_13_R2.NBTBase"));

            load(V1_13_R2.class, "NBTBase:NBTTagByte", new NBTTagByte.NBTTagByteClass("net.minecraft.server.v1_13_R2.NBTTagByte"));
            load(V1_13_R2.class, "NBTBase:NBTTagByteArray", new NBTTagByteArray.NBTTagByteArrayClass("net.minecraft.server.v1_13_R2.NBTTagByteArray"));
            load(V1_13_R2.class, "NBTBase:NBTTagCompound", new NBTTagCompound.NBTTagCompoundClass("net.minecraft.server.v1_13_R2.NBTTagCompound"));
            load(V1_13_R2.class, "NBTBase:NBTTagDouble", new NBTTagDouble.NBTTagDoubleClass("net.minecraft.server.v1_13_R2.NBTTagDouble"));
            load(V1_13_R2.class, "NBTBase:NBTTagFloat", new NBTTagFloat.NBTTagFloatClass("net.minecraft.server.v1_13_R2.NBTTagFloat"));
            load(V1_13_R2.class, "NBTBase:NBTTagInt", new NBTTagInt.NBTTagIntClass("net.minecraft.server.v1_13_R2.NBTTagInt"));
            load(V1_13_R2.class, "NBTBase:NBTTagIntArray", new NBTTagIntArray.NBTTagIntArrayClass("net.minecraft.server.v1_13_R2.NBTTagIntArray"));
            load(V1_13_R2.class, "NBTBase:NBTTagList", new NBTTagList.NBTTagListClass("net.minecraft.server.v1_13_R2.NBTTagList"));
            load(V1_13_R2.class, "NBTBase:NBTTagLong", new NBTTagLong.NBTTagLongClass("net.minecraft.server.v1_13_R2.NBTTagLong"));
            load(V1_13_R2.class, "NBTBase:NBTTagShort", new NBTTagShort.NBTTagShortClass("net.minecraft.server.v1_13_R2.NBTTagShort"));
            load(V1_13_R2.class, "NBTBase:NBTTagString", new NBTTagString.NBTTagStringClass("net.minecraft.server.v1_13_R2.NBTTagString"));
            //

            // Packets
            load(V1_13_R2.class, "Packet", new Packet.PacketClass("net.minecraft.server.v1_13_R2.Packet"));
            load(V1_13_R2.class, "PacketPlayOutSpawnEntity", new EntitySpawnPacket.EntitySpawnPacketClass("net.minecraft.server.v1_13_R2.PacketPlayOutSpawnEntity"));
            load(V1_13_R2.class, "PacketPlayOutEntityDestroy", new EntityDestroyPacket.EntityDestroyPacketClass("net.minecraft.server.v1_13_R2.PacketPlayOutEntityDestroy"));
            load(V1_13_R2.class, "PacketPlayOutAnimation", new EntityAnimationPacket.EntityAnimationPacketClass("net.minecraft.server.v1_13_R2.PacketPlayOutAnimation"));
            load(V1_13_R2.class, "PacketPlayOutSpawnEntityLiving", new EntityLivingSpawnPacket.EntityLivingSpawnPacketClass("net.minecraft.server.v1_13_R2.PacketPlayOutSpawnEntityLiving"));
            load(V1_13_R2.class, "PacketPlayOutEntityMetadata", new EntityMetadataPacket.EntityMetadataPacketClass("net.minecraft.server.v1_13_R2.PacketPlayOutEntityMetadata"));
            load(V1_13_R2.class, "PacketPlayOutNamedEntitySpawn", new EntityNamedSpawnPacket.EntityNamedSpawnPacketClass("net.minecraft.server.v1_13_R2.PacketPlayOutNamedEntitySpawn"));
            load(V1_13_R2.class, "PacketPlayOutPlayerInfo", new PlayerInfoPacket.PlayerInfoPacketClass("net.minecraft.server.v1_13_R2.PacketPlayOutPlayerInfo"));
            load(V1_13_R2.class, "PacketPlayOutPlayerInfo:EnumPlayerInfoAction", new EnumPlayerInfoActionEnum.EnumPlayerInfoActionClass("net.minecraft.server.v1_13_R2.PacketPlayOutPlayerInfo$EnumPlayerInfoAction"));
            load(V1_13_R2.class, "PacketPlayOutScoreboardTeam", new ScoreboardTeamPacket.ScoreboardTeamPacketClass("net.minecraft.server.v1_13_R2.PacketPlayOutScoreboardTeam"));
            load(V1_13_R2.class, "PacketPlayOutEntityEquipment", new EntityEquipmentPacket.EntityEquipmentPacketClass("net.minecraft.server.v1_13_R2.PacketPlayOutEntityEquipment"));
            load(V1_13_R2.class, "PacketPlayOutEntityTeleport", new EntityTeleportPacket.EntityTeleportPacketClass("net.minecraft.server.v1_13_R2.PacketPlayOutEntityTeleport"));
            load(V1_13_R2.class, "PacketPlayOutEntityHeadRotation", new EntityHeadRotationPacket.EntityHeadRotationPacketClass("net.minecraft.server.v1_13_R2.PacketPlayOutEntityHeadRotation"));
            load(V1_13_R2.class, "PacketPlayOutEntityLook", new EntityLookPacket.EntityLookPacketClass("net.minecraft.server.v1_13_R2.PacketPlayOutEntity$PacketPlayOutEntityLook"));

            load(V1_13_R2.class, "PacketPlayInUseEntity", new EntityUseInPacket.EntityUseInPacketClass("net.minecraft.server.v1_13_R2.PacketPlayInUseEntity"));
            load(V1_13_R2.class, "PacketPlayInUseEntity:EnumEntityUseAction", new EntityUseInPacket.ActionEnum.ActionClass("net.minecraft.server.v1_13_R2.PacketPlayInUseEntity$EnumEntityUseAction"));
            //

            // Server
            load(V1_13_R2.class, "MinecraftServer", new MinecraftServer.MinecraftServerClass("net.minecraft.server.v1_13_R2.MinecraftServer"));
            load(V1_13_R2.class, "WorldServer", new WorldServer.WorldServerClass("net.minecraft.server.v1_13_R2.WorldServer"));
            load(V1_13_R2.class, "CraftServer", new CraftServer.CraftServerClass("org.bukkit.craftbukkit.v1_13_R2.CraftServer"));
            //

            // Entity
            load(V1_13_R2.class, "Entity", new Entity.EntityClass("net.minecraft.server.v1_13_R2.Entity"));
            load(V1_13_R2.class, "EntityLiving", new EntityLiving.EntityLivingClass("net.minecraft.server.v1_13_R2.EntityLiving"));
            load(V1_13_R2.class, "Entity:Human", new Entity.EntityClass("net.minecraft.server.v1_13_R2.EntityHuman"));
            load(V1_13_R2.class, "CraftPlayer", new CraftPlayer.CraftPlayerClass("org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer"));
            load(V1_13_R2.class, "EntityPlayer", new EntityPlayer.EntityPlayerClass("net.minecraft.server.v1_13_R2.EntityPlayer"));

            load(V1_13_R2.class, "Entity:ArmorStand", new ArmorStand.ArmorStandClass("net.minecraft.server.v1_13_R2.EntityArmorStand"));
            load(V1_13_R2.class, "Entity:Pig", new Pig.PigClass("net.minecraft.server.v1_13_R2.EntityPig"));
            load(V1_13_R2.class, "Entity:Cow", new Cow.CowClass("net.minecraft.server.v1_13_R2.EntityCow"));
            load(V1_13_R2.class, "Entity:Ocelot", new Ocelot.OcelotClass("net.minecraft.server.v1_13_R2.EntityOcelot"));
            load(V1_13_R2.class, "Entity:Bat", new Bat.BatClass("net.minecraft.server.v1_13_R2.EntityBat"));
            load(V1_13_R2.class, "Entity:Egg", new Egg.EggClass("net.minecraft.server.v1_13_R2.EntityEgg"));
            load(V1_13_R2.class, "Entity:Chicken", new Chicken.ChickenClass("net.minecraft.server.v1_13_R2.EntityChicken"));
            load(V1_13_R2.class, "Entity:Horse", new AbstractHorse.AbstractHorseClass("net.minecraft.server.v1_13_R2.EntityHorse"));
            load(V1_13_R2.class, "Entity:IronGolem", new IronGolem.IronGolemClass("net.minecraft.server.v1_13_R2.EntityIronGolem"));
            load(V1_13_R2.class, "Entity:Rabbit", new Rabbit.RabbitClass("net.minecraft.server.v1_13_R2.EntityRabbit"));
            load(V1_13_R2.class, "Entity:Sheep", new Sheep.SheepClass("net.minecraft.server.v1_13_R2.EntitySheep"));
            load(V1_13_R2.class, "Entity:Snowman", new Snowman.SnowmanClass("net.minecraft.server.v1_13_R2.EntitySnowman"));
            load(V1_13_R2.class, "Entity:Squid", new Squid.SquidClass("net.minecraft.server.v1_13_R2.EntitySquid"));
            load(V1_13_R2.class, "Entity:Wolf", new Wolf.WolfClass("net.minecraft.server.v1_13_R2.EntityWolf"));
            load(V1_13_R2.class, "Entity:ItemFrame", new ItemFrame.ItemFrameClass("net.minecraft.server.v1_13_R2.EntityItemFrame"));
            load(V1_13_R2.class, "Entity:LeashKnot", new LeashKnot.LeashKnotClass("net.minecraft.server.v1_13_R2.EntityLeash"));
            load(V1_13_R2.class, "Entity:FallingBlock", new FallingBlock.FallingBlockClass("net.minecraft.server.v1_13_R2.EntityFallingBlock"));
            load(V1_13_R2.class, "Entity:Item", new Item.ItemClass("net.minecraft.server.v1_13_R2.EntityItem"));
            load(V1_13_R2.class, "Entity:EnderDragon", new EnderDragon.EnderDragonClass("net.minecraft.server.v1_13_R2.EntityEnderDragon"));
            load(V1_13_R2.class, "Entity:EnderSignal", new EnderSignal.EnderSignalClass("net.minecraft.server.v1_13_R2.EntityEnderSignal"));
            load(V1_13_R2.class, "Entity:Wither", new Wither.WitherClass("net.minecraft.server.v1_13_R2.EntityWither"));
            load(V1_13_R2.class, "Entity:WitherSkull", new WitherSkull.WitherSkullClass("net.minecraft.server.v1_13_R2.EntityWitherSkull"));
            load(V1_13_R2.class, "Entity:Blaze", new Blaze.BlazeClass("net.minecraft.server.v1_13_R2.EntityBlaze"));
            load(V1_13_R2.class, "Entity:Creeper", new Creeper.CreeperClass("net.minecraft.server.v1_13_R2.EntityCreeper"));
            load(V1_13_R2.class, "Entity:Enderman", new Enderman.EndermanClass("net.minecraft.server.v1_13_R2.EntityEnderman"));
            load(V1_13_R2.class, "Entity:Ghast", new Ghast.GhastClass("net.minecraft.server.v1_13_R2.EntityGhast"));
            load(V1_13_R2.class, "Entity:Guardian", new Guardian.GuardianClass("net.minecraft.server.v1_13_R2.EntityGuardian"));
            load(V1_13_R2.class, "Entity:Silverfish", new Silverfish.SilverfishClass("net.minecraft.server.v1_13_R2.EntitySilverfish"));
            load(V1_13_R2.class, "Entity:Skeleton", new Skeleton.SkeletonClass("net.minecraft.server.v1_13_R2.EntitySkeleton"));
            load(V1_13_R2.class, "Entity:Slime", new Slime.SlimeClass("net.minecraft.server.v1_13_R2.EntitySlime"));
            load(V1_13_R2.class, "Entity:Spider", new Spider.SpiderClass("net.minecraft.server.v1_13_R2.EntitySpider"));
            load(V1_13_R2.class, "Entity:Witch", new Witch.WitchClass("net.minecraft.server.v1_13_R2.EntityWitch"));
            load(V1_13_R2.class, "Entity:Zombie", new Zombie.ZombieClass("net.minecraft.server.v1_13_R2.EntityZombie"));
            load(V1_13_R2.class, "Entity:Villager", new Villager.VillagerClass("net.minecraft.server.v1_13_R2.EntityVillager"));
            load(V1_13_R2.class, "Entity:Shulker", new Shulker.ShulkerClass("net.minecraft.server.v1_13_R2.EntityShulker"));
            load(V1_13_R2.class, "Entity:PolarBear", new PolarBear.PolarBearClass("net.minecraft.server.v1_13_R2.EntityPolarBear"));

            load(V1_13_R2.class, "Entity:Ageable", new AgeableEntityLiving.AgeableLivingEntityClass("net.minecraft.server.v1_13_R2.EntityAgeable"));
            load(V1_13_R2.class, "Entity:Tameable", new TameableEntityLiving.TameableLivingEntityClass("net.minecraft.server.v1_13_R2.EntityTameableAnimal"));
            // EntityPlayer
            load(V1_13_R2.class, "GameProfile", new GameProfile.GameProfileClass("com.mojang.authlib.GameProfile"));
            load(V1_13_R2.class, "PropertyMap", new PropertyMap.PropertyMapClass("com.mojang.authlib.properties.PropertyMap"));
            load(V1_13_R2.class, "Property", new Property.PropertyClass("com.mojang.authlib.properties.Property"));
            //

            // Managers
            load(V1_13_R2.class, "PlayerInteractManager", new PlayerInteractManager.PlayerInteractManagerClass("net.minecraft.server.v1_13_R2.PlayerInteractManager"));
            //

            // DataWatcher
            load(V1_13_R2.class, "DataWatcher", new DataWatcher.DataWatcherClass("net.minecraft.server.v1_13_R2.DataWatcher"));
            load(V1_13_R2.class, "DataWatcherObject", new DataWatcherObject.DataWatcherObjectClass("net.minecraft.server.v1_13_R2.DataWatcherObject"));
            load(V1_13_R2.class, "DataWatcher:Item", new DataWatcherItem.DataWatcherItemClass("net.minecraft.server.v1_13_R2.DataWatcher$Item"));
            //

            // Scoreboard
            load(V1_13_R2.class, "CraftScoreboard", new CraftScoreboard.CraftScoreboardClass("org.bukkit.craftbukkit.v1_13_R2.scoreboard.CraftScoreboard"));
            load(V1_13_R2.class, "Scoreboard", new Scoreboard.ScoreboardClass("net.minecraft.server.v1_13_R2.Scoreboard"));

            load(V1_13_R2.class, "ScoreboardTeam", new ScoreboardTeam.ScoreboardTeamClass("net.minecraft.server.v1_13_R2.ScoreboardTeam"));
            load(V1_13_R2.class, "ScoreboardTeam:EnumTeamPush", new EnumTeamPushEnum.EnumTeamPushClass("net.minecraft.server.v1_13_R2.ScoreboardTeamBase$EnumTeamPush"));

            load(V1_13_R2.class, "ScoreboardTeamBase:EnumNameTagVisibility", new EnumNameTagVisibilityEnum.EnumNameTagVisibilityClass("net.minecraft.server.v1_13_R2.ScoreboardTeamBase$EnumNameTagVisibility"));
            //

            // Others
            load(V1_13_R2.class, "PlayerConnection", new PlayerConnection.PlayerConnectionClass("net.minecraft.server.v1_13_R2.PlayerConnection"));
            load(V1_13_R2.class, "NetworkManager", new NetworkManager.NetworkManagerClass("net.minecraft.server.v1_13_R2.NetworkManager"));

            load(V1_13_R2.class, "EnumChatFormat", new EnumChatFormatEnum.EnumChatFormatClass("net.minecraft.server.v1_13_R2.EnumChatFormat"));
            load(V1_13_R2.class, "EnumColor", new EnumColorEnum.EnumColorClass("net.minecraft.server.v1_13_R2.EnumColor"));
            load(V1_13_R2.class, "EnumItemSlot", new EnumItemSlotEnum.EnumItemSlotClass("net.minecraft.server.v1_13_R2.EnumItemSlot"));
            load(V1_13_R2.class, "EnumDirection", new EnumDirectionEnum.EnumDirectionClass("net.minecraft.server.v1_13_R2.EnumDirection"));
            //

            // Chat
            load(V1_13_R2.class, "IChatBaseComponent", new IChatBaseComponent.IChatBaseComponentClass("net.minecraft.server.v1_13_R2.IChatBaseComponent"));
            load(V1_13_R2.class, "ChatSerializer", new IChatBaseComponent.ChatSerializerClass("net.minecraft.server.v1_13_R2.IChatBaseComponent$ChatSerializer"));
            //

            // Objects
            load(V1_13_R2.class, "CraftWorld", new CraftWorld.CraftWorldClass("org.bukkit.craftbukkit.v1_13_R2.CraftWorld"));
            load(V1_13_R2.class, "World", new World.WorldClass("net.minecraft.server.v1_13_R2.World"));
            load(V1_13_R2.class, "Vector3f", new Vector3f.Vector3fClass("net.minecraft.server.v1_13_R2.Vector3f"));
            load(V1_13_R2.class, "BlockPosition", new BlockPosition.BlockPositionClass("net.minecraft.server.v1_13_R2.BlockPosition"));
            load(V1_13_R2.class, "CraftBlock", new CraftBlock.CraftBlockClass("org.bukkit.craftbukkit.v1_13_R2.block.CraftBlock"));
            load(V1_13_R2.class, "IBlockData", new IBlockData.IBlockDataClass("net.minecraft.server.v1_13_R2.IBlockData"));
            load(V1_13_R2.class, "Block", new Block.BlockClass("net.minecraft.server.v1_13_R2.Block"));
            load(V1_13_R2.class, "CraftMagicNumbers", new ClassExecutor("org.bukkit.craftbukkit.v1_13_R2.util.CraftMagicNumbers"));
            //

            // Items
            load(V1_13_R2.class, "ItemStack", new ItemStack.ItemStackClass("net.minecraft.server.v1_13_R2.ItemStack"));
            load(V1_13_R2.class, "CraftItemStack", new CraftItemStack.CraftItemStackClass("org.bukkit.craftbukkit.v1_13_R2.inventory.CraftItemStack"));
            //

            // Entity horse
            load(V1_13_R2.class, "Entity:Horse:Abstract", new AbstractHorse.AbstractHorseClass("net.minecraft.server.v1_13_R2.EntityHorseAbstract"));
            load(V1_13_R2.class, "Entity:Horse:Abstract:Chested", new AbstractChestedHorse.AbstractChestedHorseClass("net.minecraft.server.v1_13_R2.EntityHorseChestedAbstract"));
            load(V1_13_R2.class, "Entity:Horse:Donkey", new HorseDonkey.HorseDonkeyClass("net.minecraft.server.v1_13_R2.EntityHorseDonkey"));
            load(V1_13_R2.class, "Entity:Horse:Mule", new HorseMule.HorseMuleClass("net.minecraft.server.v1_13_R2.EntityHorseMule"));
            load(V1_13_R2.class, "Entity:Horse:Skeleton", new HorseSkeleton.HorseSkeletonClass("net.minecraft.server.v1_13_R2.EntityHorseSkeleton"));
            load(V1_13_R2.class, "Entity:Horse:Zombie", new HorseZombie.HorseZombieClass("net.minecraft.server.v1_13_R2.EntityHorseZombie"));
            // Entity skeleton
            load(V1_13_R2.class, "Entity:Skeleton:Wither", new SkeletonWither.SkeletonWitherClass("net.minecraft.server.v1_13_R2.EntitySkeletonWither"));
            load(V1_13_R2.class, "Entity:Skeleton:Stray", new SkeletonStray.SkeletonStrayClass("net.minecraft.server.v1_13_R2.EntitySkeletonStray"));
            // Entity zombie
            load(V1_13_R2.class, "Entity:Zombie:Villager", new ZombieVillager.ZombieVillagerClass("net.minecraft.server.v1_13_R2.EntityZombieVillager"));
            load(V1_13_R2.class, "Entity:Zombie:Husk", new ZombieHusk.ZombieHuskClass("net.minecraft.server.v1_13_R2.EntityZombieHusk"));
            load(V1_13_R2.class, "Entity:Zombie:Drowned", new ZombieDrowned.ZombieDrownedClass("net.minecraft.server.v1_13_R2.EntityDrowned"));
            // Entity vindicator
            load(V1_13_R2.class, "Entity:Vindicator", new Vindicator.VindicatorClass("net.minecraft.server.v1_13_R2.EntityVindicator"));
            // Entity evoker
            load(V1_13_R2.class, "Entity:Evoker", new Evoker.EvokerClass("net.minecraft.server.v1_13_R2.EntityEvoker"));
            // Entity vex
            load(V1_13_R2.class, "Entity:Vex", new Vex.VexClass("net.minecraft.server.v1_13_R2.EntityVex"));
            // Entity llama
            load(V1_13_R2.class, "Entity:Llama", new Llama.LlamaClass("net.minecraft.server.v1_13_R2.EntityLlama"));
            // Entity illager illusioner
            load(V1_13_R2.class, "Entity:Illusioner", new Illusioner.IllusionerClass("net.minecraft.server.v1_13_R2.EntityIllagerIllusioner"));
            // Entity illager wizard
            load(V1_13_R2.class, "Entity:IllagerWizard", new IllagerWizard.IllagerWizardClass("net.minecraft.server.v1_13_R2.EntityIllagerWizard"));
            load(V1_13_R2.class, "Entity:IllagerWizard:Spell", new EnumSpellEnum.EnumSpellClass("net.minecraft.server.v1_13_R2.EntityIllagerWizard$Spell"));
            // Entity parrot
            load(V1_13_R2.class, "Entity:Parrot", new Parrot.ParrotClass("net.minecraft.server.v1_13_R2.EntityParrot"));
            // Entity dolphin
            load(V1_13_R2.class, "Entity:Dolphin", new Dolphin.DolphinClass("net.minecraft.server.v1_13_R2.EntityDolphin"));
            // Entity fish
            load(V1_13_R2.class, "Entity:Fish", new Fish.FishClass("net.minecraft.server.v1_13_R2.EntityFish"));
            load(V1_13_R2.class, "Entity:Cod", new Cod.CodClass("net.minecraft.server.v1_13_R2.EntityCod"));
            load(V1_13_R2.class, "Entity:Salmon", new Salmon.SalmonClass("net.minecraft.server.v1_13_R2.EntitySalmon"));
            load(V1_13_R2.class, "Entity:PufferFish", new PufferFish.PufferFishClass("net.minecraft.server.v1_13_R2.EntityPufferFish"));
            load(V1_13_R2.class, "Entity:TropicalFish", new TropicalFish.TropicalFishClass("net.minecraft.server.v1_13_R2.EntityTropicalFish"));
            // Entity phantom
            load(V1_13_R2.class, "Entity:Phantom", new Phantom.PhantomClass("net.minecraft.server.v1_13_R2.EntityPhantom"));
            // Entity turtle
            load(V1_13_R2.class, "Entity:Turtle", new Turtle.TurtleClass("net.minecraft.server.v1_13_R2.EntityTurtle"));
            //
        }
        
        return super.getClasses();
    }

    @Override
    public @NotNull String getName() {
        return "v1_13_R2";
    }

}
