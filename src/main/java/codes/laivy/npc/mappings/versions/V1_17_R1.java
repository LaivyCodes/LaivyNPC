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
import codes.laivy.npc.mappings.defaults.classes.java.BooleanObjExec;
import codes.laivy.npc.mappings.defaults.classes.java.DoubleObjExec;
import codes.laivy.npc.mappings.defaults.classes.java.FloatObjExec;
import codes.laivy.npc.mappings.defaults.classes.java.IntegerObjExec;
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
import codes.laivy.npc.mappings.instances.FieldExecutor;
import codes.laivy.npc.mappings.instances.MethodExecutor;
import codes.laivy.npc.mappings.instances.classes.ClassConstructor;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import io.netty.channel.Channel;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class V1_17_R1 extends V1_16_R3 {

    @Override
    protected boolean onLoad(@NotNull Class<? extends Version> version, @NotNull String key, @NotNull Executor executor) {
        if (version == V1_16_R3.class) {
            if (executor instanceof ClassExecutor && !(executor instanceof EnumExecutor)) {
                return false;
            } else if (executor instanceof MethodExecutor) {
                if (key.equals("Entity:isGlowing")) {
                    load(V1_17_R1.class, key, new MethodExecutor(getClassExec("Entity"), ClassExecutor.BOOLEAN, "hasGlowingTag", "Gets the glowing state of a Entity"));
                    return false;
                }
            }
        } else if (version == V1_16_R2.class) {
            if (executor instanceof FieldExecutor) {
                switch (key) {
                    case "Metadata:Enderman:screaming":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("Entity:Enderman"), getClassExec("DataWatcherObject"), "bU", "Gets the enderman's screaming datawatcher object"));
                        return false;
                    case "Metadata:Horse:Variant":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("Entity:Horse"), getClassExec("DataWatcherObject"), "ci", "Gets the horse variant DataWatcherObject"));
                        return false;
                    case "Metadata:Pig:Saddle":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("Entity:Pig"), getClassExec("DataWatcherObject"), "bS", "Gets the pig saddle DataWatcherObject"));
                        return false;
                    case "Metadata:Turtle:Egg":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("Entity:Turtle"), getClassExec("DataWatcherObject"), "bW", "Gets the turtle's egg DataWatcherObject", false, true));
                        return false;
                    case "Metadata:Llama:Variant":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("Entity:Llama"), getClassExec("DataWatcherObject"), "cn", "Gets the llama's variant DataWatcherObject"));
                        return false;
                    case "Metadata:Llama:CarpetColor":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("Entity:Llama"), getClassExec("DataWatcherObject"), "cm", "Gets the llama's carpet color DataWatcherObject"));
                        return false;
                    case "Metadata:PolarBear:Standing":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("Entity:PolarBear"), getClassExec("DataWatcherObject"), "bS", "Gets the polar bear's standing DataWatcherObject"));
                        return false;
                    case "Metadata:Villager:Data":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("Entity:Villager"), getClassExec("DataWatcherObject"), "bY", "Gets the villager's data DataWatcherObject"));
                        return false;
                    case "Metadata:Cat:Type":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("Entity:Cat"), getClassExec("DataWatcherObject"), "cl", "Gets the cat's variant DataWatcherObject"));
                        return false;
                    case "Metadata:Wolf:Angry":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("Entity:Wolf"), getClassExec("DataWatcherObject"), "bW", "Gets the wolf angry DataWatcherObject"));
                        return false;
                    case "Metadata:Wolf:CollarColor":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("Entity:Wolf"), getClassExec("DataWatcherObject"), "bX", "Gets the wolf collar color DataWatcherObject"));
                        return false;
                    default:
                        break;
                }
            }
        } else if (version == V1_16_R1.class) {
            if (executor instanceof MethodExecutor) {
                if (key.equals("Entity:setGlowing")) {
                    load(V1_17_R1.class, key, new MethodExecutor(getClassExec("Entity"), ClassExecutor.VOID, "setGlowingTag", "Sets the glowing state of a Entity", ClassExecutor.BOOLEAN));
                    return false;
                }
            } else if (executor instanceof FieldExecutor) {
                switch (key) {
                    case "Metadata:Guardian:Target":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("Entity:Guardian"), getClassExec("DataWatcherObject"), "e", "Gets the Guardian target DataWatcherObject"));
                        return false;
                    case "Entity:loc":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("Entity"), getClassExec("Vec3D"), "av", "Gets the Vec3D loc of an entity"));
                        return false;
                    default:
                        break;
                }
            }
        } else if (version == V1_15_R1.class) {
            if (executor instanceof FieldExecutor) {
                switch (key) {
                    case "DataWatcher:map":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("DataWatcher"), new ClassExecutor(Map.class), "f", "Gets the values of the data"));
                        return false;

                    case "VillagerType:Desert":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("VillagerType"), getClassExec("VillagerType"), "a", "Gets the villager's desert type"));
                        return false;
                    case "VillagerType:Jungle":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("VillagerType"), getClassExec("VillagerType"), "b", "Gets the villager's jungle type"));
                        return false;
                    case "VillagerType:Plains":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("VillagerType"), getClassExec("VillagerType"), "c", "Gets the villager's plains type"));
                        return false;
                    case "VillagerType:Savanna":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("VillagerType"), getClassExec("VillagerType"), "d", "Gets the villager's savanna type"));
                        return false;
                    case "VillagerType:Snow":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("VillagerType"), getClassExec("VillagerType"), "e", "Gets the villager's snow type"));
                        return false;
                    case "VillagerType:Swamp":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("VillagerType"), getClassExec("VillagerType"), "f", "Gets the villager's swamp type"));
                        return false;
                    case "VillagerType:Taiga":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("VillagerType"), getClassExec("VillagerType"), "g", "Gets the villager's taiga type"));
                        return false;
                    default:
                        break;
                }
            }
        } else if (version == V1_14_R1.class) {
            if (executor instanceof FieldExecutor) {
                switch (key) {
                    case "Metadata:Creeper:Powered":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("Entity:Creeper"), getClassExec("DataWatcherObject"), "c", "Gets the creeper ignited DataWatcherObject"));
                        return false;
                    case "Metadata:PufferFish:PuffState":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("Entity:PufferFish"), getClassExec("DataWatcherObject"), "e", "Gets the puffer fish's puff state DataWatcherObject"));
                        return false;
                    case "Metadata:TropicalFish:Variant":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("Entity:TropicalFish"), getClassExec("DataWatcherObject"), "bS", "Gets the tropical fish's variant DataWatcherObject"));
                        return false;
                    case "Metadata:Phantom:Size":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("Entity:Phantom"), getClassExec("DataWatcherObject"), "d", "Gets the phantom's size DataWatcherObject"));
                        return false;
                    case "Metadata:Dolphin:hasFish":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("Entity:Dolphin"), getClassExec("DataWatcherObject"), "e", "Gets the dolphin's hasFish DataWatcherObject"));
                        return false;

                    case "Entity:Horse:Abstract:horseInventory":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("Entity:Horse:Abstract"), getClassExec("InventorySubcontainer"), "cd", "Gets the horse's inventoryChest"));
                        return false;

                    case "VillagerProfession:PROFESSIONS:NONE":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("VillagerProfession"), getClassExec("VillagerProfession"), "a", "Gets the NONE villager profession"));
                        return false;
                    case "VillagerProfession:PROFESSIONS:ARMORER":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("VillagerProfession"), getClassExec("VillagerProfession"), "b", "Gets the ARMORER villager profession"));
                        return false;
                    case "VillagerProfession:PROFESSIONS:BUTCHER":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("VillagerProfession"), getClassExec("VillagerProfession"), "c", "Gets the BUTCHER villager profession"));
                        return false;
                    case "VillagerProfession:PROFESSIONS:CARTOGRAPHER":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("VillagerProfession"), getClassExec("VillagerProfession"), "d", "Gets the CARTOGRAPHER villager profession"));
                        return false;
                    case "VillagerProfession:PROFESSIONS:CLERIC":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("VillagerProfession"), getClassExec("VillagerProfession"), "e", "Gets the CLERIC villager profession"));
                        return false;
                    case "VillagerProfession:PROFESSIONS:FARMER":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("VillagerProfession"), getClassExec("VillagerProfession"), "f", "Gets the FARMER villager profession"));
                        return false;
                    case "VillagerProfession:PROFESSIONS:FISHERMAN":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("VillagerProfession"), getClassExec("VillagerProfession"), "g", "Gets the FISHERMAN villager profession"));
                        return false;
                    case "VillagerProfession:PROFESSIONS:FLETCHER":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("VillagerProfession"), getClassExec("VillagerProfession"), "h", "Gets the FLETCHER villager profession"));
                        return false;
                    case "VillagerProfession:PROFESSIONS:LEATHERWORKER":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("VillagerProfession"), getClassExec("VillagerProfession"), "i", "Gets the LEATHERWORKER villager profession"));
                        return false;
                    case "VillagerProfession:PROFESSIONS:LIBRARIAN":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("VillagerProfession"), getClassExec("VillagerProfession"), "j", "Gets the LIBRARIAN villager profession"));
                        return false;
                    case "VillagerProfession:PROFESSIONS:MASON":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("VillagerProfession"), getClassExec("VillagerProfession"), "k", "Gets the MASON villager profession"));
                        return false;
                    case "VillagerProfession:PROFESSIONS:NITWIT":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("VillagerProfession"), getClassExec("VillagerProfession"), "l", "Gets the NITWIT villager profession"));
                        return false;
                    case "VillagerProfession:PROFESSIONS:SHEPHERD":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("VillagerProfession"), getClassExec("VillagerProfession"), "m", "Gets the SHEPHERD villager profession"));
                        return false;
                    case "VillagerProfession:PROFESSIONS:TOOLSMITH":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("VillagerProfession"), getClassExec("VillagerProfession"), "n", "Gets the TOOLSMITH villager profession"));
                        return false;
                    case "VillagerProfession:PROFESSIONS:WEAPONSMITH":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("VillagerProfession"), getClassExec("VillagerProfession"), "o", "Gets the WEAPONSMITH villager profession"));
                        return false;
                    default:
                        break;
                }
            } else if (executor instanceof MethodExecutor) {
                switch (key) {
                    case "VillagerData:getType":
                        load(V1_17_R1.class, key, new MethodExecutor(getClassExec("VillagerData"), getClassExec("VillagerType"), "getType", "Gets the type of a villager data"));
                        return false;
                    default:
                        break;
                }
            }
        } else if (version == V1_12_R1.class) {
            if (executor instanceof FieldExecutor) {
                if (key.equals("Metadata:Shulker:Color")) {
                    load(V1_17_R1.class, key, new FieldExecutor(getClassExec("Entity:Shulker"), getClassExec("DataWatcherObject"), "d", "Gets the shulker's color DataWatcherObject"));
                    return false;
                }
            }
        } else if (version == V1_8_R3.class) {
            if (executor instanceof FieldExecutor) {
                if (key.equals("NetworkManager:channel")) {
                    load(V1_17_R1.class, key, new FieldExecutor(getClassExec("NetworkManager"), new ClassExecutor(Channel.class) { }, "k", "Gets the channel of the player"));
                    return false;
                }
            }
        } else if (version == V1_8_R1.class) {
            if (executor instanceof FieldExecutor) {
                switch (key) {
                    case "NBTTagList:list":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("NBTBase:NBTTagList"), new ClassExecutor(List.class) {}, "c", "Gets the list of a NBTTagList"));
                        return false;
                    case "EntityPlayer:playerConnection":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("EntityPlayer"), getClassExec("PlayerConnection"), "b", "Gets the PlayerConnection from the player"));
                        return false;

                    case "Entity:ArmorStand:headPose":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("Entity:ArmorStand"), getClassExec("Vector3f"), "cg", "Gets the head pose of an ArmorStand"));
                        return false;
                    case "Entity:ArmorStand:bodyPose":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("Entity:ArmorStand"), getClassExec("Vector3f"), "ch", "Gets the body pose of an ArmorStand"));
                        return false;
                    case "Entity:ArmorStand:leftArmPose":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("Entity:ArmorStand"), getClassExec("Vector3f"), "ch", "Gets the left arm of an ArmorStand"));
                        return false;
                    case "Entity:ArmorStand:rightArmPose":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("Entity:ArmorStand"), getClassExec("Vector3f"), "cj", "Gets the right arm pose of an ArmorStand"));
                        return false;
                    case "Entity:ArmorStand:leftLegPose":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("Entity:ArmorStand"), getClassExec("Vector3f"), "ck", "Gets the left leg pose of an ArmorStand"));
                        return false;
                    case "Entity:ArmorStand:rightLegPose":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("Entity:ArmorStand"), getClassExec("Vector3f"), "cl", "Gets the right leg pose of an ArmorStand"));
                        return false;

                    case "Entity:world":
                        load(V1_17_R1.class, key, new FieldExecutor(getClassExec("Entity"), getClassExec("World"), "t", "Gets the world of an Entity"));
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
            load(V1_17_R1.class, "WatchableObject", new ClassExecutor.BrokenClassExecutor());
            load(V1_17_R1.class, "EnumSkeletonType", new ClassExecutor.BrokenClassExecutor());
            load(V1_17_R1.class, "EnumHorseType", new ClassExecutor.BrokenClassExecutor());
            load(V1_17_R1.class, "EnumZombieType", new ClassExecutor.BrokenClassExecutor());

            load(V1_17_R1.class, "NBTBase", new NBTBase.NBTBaseClass("net.minecraft.nbt.NBTBase"));

            load(V1_17_R1.class, "NBTBase:NBTTagByte", new NBTTagByte.NBTTagByteClass("net.minecraft.nbt.NBTTagByte"));
            load(V1_17_R1.class, "NBTBase:NBTTagByteArray", new NBTTagByteArray.NBTTagByteArrayClass("net.minecraft.nbt.NBTTagByteArray"));
            load(V1_17_R1.class, "NBTBase:NBTTagCompound", new NBTTagCompound.NBTTagCompoundClass("net.minecraft.nbt.NBTTagCompound"));
            load(V1_17_R1.class, "NBTBase:NBTTagDouble", new NBTTagDouble.NBTTagDoubleClass("net.minecraft.nbt.NBTTagDouble"));
            load(V1_17_R1.class, "NBTBase:NBTTagFloat", new NBTTagFloat.NBTTagFloatClass("net.minecraft.nbt.NBTTagFloat"));
            load(V1_17_R1.class, "NBTBase:NBTTagInt", new NBTTagInt.NBTTagIntClass("net.minecraft.nbt.NBTTagInt"));
            load(V1_17_R1.class, "NBTBase:NBTTagIntArray", new NBTTagIntArray.NBTTagIntArrayClass("net.minecraft.nbt.NBTTagIntArray"));
            load(V1_17_R1.class, "NBTBase:NBTTagList", new NBTTagList.NBTTagListClass("net.minecraft.nbt.NBTTagList"));
            load(V1_17_R1.class, "NBTBase:NBTTagLong", new NBTTagLong.NBTTagLongClass("net.minecraft.nbt.NBTTagLong"));
            load(V1_17_R1.class, "NBTBase:NBTTagShort", new NBTTagShort.NBTTagShortClass("net.minecraft.nbt.NBTTagShort"));
            load(V1_17_R1.class, "NBTBase:NBTTagString", new NBTTagString.NBTTagStringClass("net.minecraft.nbt.NBTTagString"));
            //

            // Packets
            load(V1_17_R1.class, "Packet", new Packet.PacketClass("net.minecraft.network.protocol.Packet"));
            load(V1_17_R1.class, "PacketPlayOutSpawnEntity", new EntitySpawnPacket.EntitySpawnPacketClass("net.minecraft.network.protocol.game.PacketPlayOutSpawnEntity"));
            load(V1_17_R1.class, "PacketPlayOutEntityDestroy", new EntityDestroyPacket.EntityDestroyPacketClass("net.minecraft.network.protocol.game.PacketPlayOutEntityDestroy"));
            load(V1_17_R1.class, "PacketPlayOutAnimation", new EntityAnimationPacket.EntityAnimationPacketClass("net.minecraft.network.protocol.game.PacketPlayOutAnimation"));
            load(V1_17_R1.class, "PacketPlayOutSpawnEntityLiving", new EntityLivingSpawnPacket.EntityLivingSpawnPacketClass("net.minecraft.network.protocol.game.PacketPlayOutSpawnEntityLiving"));
            load(V1_17_R1.class, "PacketPlayOutEntityMetadata", new EntityMetadataPacket.EntityMetadataPacketClass("net.minecraft.network.protocol.game.PacketPlayOutEntityMetadata"));
            load(V1_17_R1.class, "PacketPlayOutNamedEntitySpawn", new EntityNamedSpawnPacket.EntityNamedSpawnPacketClass("net.minecraft.network.protocol.game.PacketPlayOutNamedEntitySpawn"));
            load(V1_17_R1.class, "PacketPlayOutPlayerInfo", new PlayerInfoPacket.PlayerInfoPacketClass("net.minecraft.network.protocol.game.PacketPlayOutPlayerInfo"));
            load(V1_17_R1.class, "PacketPlayOutPlayerInfo:EnumPlayerInfoAction", new EnumPlayerInfoActionEnum.EnumPlayerInfoActionClass("net.minecraft.network.protocol.game.PacketPlayOutPlayerInfo$EnumPlayerInfoAction"));
            load(V1_17_R1.class, "PacketPlayOutScoreboardTeam", new ScoreboardTeamPacket.ScoreboardTeamPacketClass("net.minecraft.network.protocol.game.PacketPlayOutScoreboardTeam"));
            load(V1_17_R1.class, "PacketPlayOutEntityEquipment", new EntityEquipmentPacket.EntityEquipmentPacketClass("net.minecraft.network.protocol.game.PacketPlayOutEntityEquipment"));
            load(V1_17_R1.class, "PacketPlayOutEntityTeleport", new EntityTeleportPacket.EntityTeleportPacketClass("net.minecraft.network.protocol.game.PacketPlayOutEntityTeleport"));
            load(V1_17_R1.class, "PacketPlayOutEntityHeadRotation", new EntityHeadRotationPacket.EntityHeadRotationPacketClass("net.minecraft.network.protocol.game.PacketPlayOutEntityHeadRotation"));
            load(V1_17_R1.class, "PacketPlayOutEntityLook", new EntityLookPacket.EntityLookPacketClass("net.minecraft.network.protocol.game.PacketPlayOutEntity$PacketPlayOutEntityLook"));

            load(V1_17_R1.class, "PacketPlayInUseEntity", new EntityUseInPacket.EntityUseInPacketClass("net.minecraft.network.protocol.game.PacketPlayInUseEntity"));
            load(V1_17_R1.class, "PacketPlayInUseEntity:EnumEntityUseAction", new EntityUseInPacket.ActionEnum.ActionClass("net.minecraft.network.protocol.game.PacketPlayInUseEntity$b"));
            //

            // Server
            load(V1_17_R1.class, "MinecraftServer", new MinecraftServer.MinecraftServerClass("net.minecraft.server.MinecraftServer"));
            load(V1_17_R1.class, "WorldServer", new WorldServer.WorldServerClass("net.minecraft.server.level.WorldServer"));
            load(V1_17_R1.class, "CraftServer", new CraftServer.CraftServerClass("org.bukkit.craftbukkit.v1_17_R1.CraftServer"));
            //

            // Entity
            load(V1_17_R1.class, "EntityTypes", new EntityTypes.EntityTypesClass("net.minecraft.world.entity.EntityTypes"));

            load(V1_17_R1.class, "Entity", new Entity.EntityClass("net.minecraft.world.entity.Entity"));
            load(V1_17_R1.class, "EntityLiving", new EntityLiving.EntityLivingClass("net.minecraft.world.entity.EntityLiving"));
            load(V1_17_R1.class, "Entity:Human", new Entity.EntityClass("net.minecraft.world.entity.player.EntityHuman"));
            load(V1_17_R1.class, "CraftPlayer", new CraftPlayer.CraftPlayerClass("org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer"));
            load(V1_17_R1.class, "EntityPlayer", new EntityPlayer.EntityPlayerClass("net.minecraft.server.level.EntityPlayer"));

            load(V1_17_R1.class, "Entity:ArmorStand", new ArmorStand.ArmorStandClass("net.minecraft.world.entity.decoration.EntityArmorStand"));
            load(V1_17_R1.class, "Entity:Pig", new Pig.PigClass("net.minecraft.world.entity.animal.EntityPig"));
            load(V1_17_R1.class, "Entity:Cow", new Cow.CowClass("net.minecraft.world.entity.animal.EntityCow"));
            load(V1_17_R1.class, "Entity:Ocelot", new Ocelot.OcelotClass("net.minecraft.world.entity.animal.EntityOcelot"));
            load(V1_17_R1.class, "Entity:Bat", new Bat.BatClass("net.minecraft.world.entity.ambient.EntityBat"));
            load(V1_17_R1.class, "Entity:Egg", new Egg.EggClass("net.minecraft.world.entity.projectile.EntityEgg"));
            load(V1_17_R1.class, "Entity:Chicken", new Chicken.ChickenClass("net.minecraft.world.entity.animal.EntityChicken"));
            load(V1_17_R1.class, "Entity:Horse", new AbstractHorse.AbstractHorseClass("net.minecraft.world.entity.animal.horse.EntityHorse"));
            load(V1_17_R1.class, "Entity:IronGolem", new IronGolem.IronGolemClass("net.minecraft.world.entity.animal.EntityIronGolem"));
            load(V1_17_R1.class, "Entity:Rabbit", new Rabbit.RabbitClass("net.minecraft.world.entity.animal.EntityRabbit"));
            load(V1_17_R1.class, "Entity:Sheep", new Sheep.SheepClass("net.minecraft.world.entity.animal.EntitySheep"));
            load(V1_17_R1.class, "Entity:Snowman", new Snowman.SnowmanClass("net.minecraft.world.entity.animal.EntitySnowman"));
            load(V1_17_R1.class, "Entity:Squid", new Squid.SquidClass("net.minecraft.world.entity.animal.EntitySquid"));
            load(V1_17_R1.class, "Entity:Wolf", new Wolf.WolfClass("net.minecraft.world.entity.animal.EntityWolf"));
            load(V1_17_R1.class, "Entity:ItemFrame", new ItemFrame.ItemFrameClass("net.minecraft.world.entity.decoration.EntityItemFrame"));
            load(V1_17_R1.class, "Entity:LeashKnot", new LeashKnot.LeashKnotClass("net.minecraft.world.entity.decoration.EntityLeash"));
            load(V1_17_R1.class, "Entity:FallingBlock", new FallingBlock.FallingBlockClass("net.minecraft.world.entity.item.EntityFallingBlock"));
            load(V1_17_R1.class, "Entity:Item", new Item.ItemClass("net.minecraft.world.entity.item.EntityItem"));
            load(V1_17_R1.class, "Entity:EnderDragon", new EnderDragon.EnderDragonClass("net.minecraft.world.entity.boss.enderdragon.EntityEnderDragon"));
            load(V1_17_R1.class, "Entity:EnderSignal", new EnderSignal.EnderSignalClass("net.minecraft.world.entity.projectile.EntityEnderSignal"));
            load(V1_17_R1.class, "Entity:Wither", new Wither.WitherClass("net.minecraft.world.entity.boss.wither.EntityWither"));
            load(V1_17_R1.class, "Entity:WitherSkull", new WitherSkull.WitherSkullClass("net.minecraft.world.entity.projectile.EntityWitherSkull"));
            load(V1_17_R1.class, "Entity:Blaze", new Blaze.BlazeClass("net.minecraft.world.entity.monster.EntityBlaze"));
            load(V1_17_R1.class, "Entity:Creeper", new Creeper.CreeperClass("net.minecraft.world.entity.monster.EntityCreeper"));
            load(V1_17_R1.class, "Entity:Enderman", new Enderman.EndermanClass("net.minecraft.world.entity.monster.EntityEnderman"));
            load(V1_17_R1.class, "Entity:Ghast", new Ghast.GhastClass("net.minecraft.world.entity.monster.EntityGhast"));
            load(V1_17_R1.class, "Entity:Guardian", new Guardian.GuardianClass("net.minecraft.world.entity.monster.EntityGuardian"));
            load(V1_17_R1.class, "Entity:Silverfish", new Silverfish.SilverfishClass("net.minecraft.world.entity.monster.EntitySilverfish"));
            load(V1_17_R1.class, "Entity:Skeleton", new Skeleton.SkeletonClass("net.minecraft.world.entity.monster.EntitySkeleton"));
            load(V1_17_R1.class, "Entity:Slime", new Slime.SlimeClass("net.minecraft.world.entity.monster.EntitySlime"));
            load(V1_17_R1.class, "Entity:Spider", new Spider.SpiderClass("net.minecraft.world.entity.monster.EntitySpider"));
            load(V1_17_R1.class, "Entity:Witch", new Witch.WitchClass("net.minecraft.world.entity.monster.EntityWitch"));
            load(V1_17_R1.class, "Entity:Zombie", new Zombie.ZombieClass("net.minecraft.world.entity.monster.EntityZombie"));
            load(V1_17_R1.class, "Entity:Villager", new Villager.VillagerClass("net.minecraft.world.entity.npc.EntityVillager"));
            load(V1_17_R1.class, "Entity:Shulker", new Shulker.ShulkerClass("net.minecraft.world.entity.monster.EntityShulker"));
            load(V1_17_R1.class, "Entity:PolarBear", new PolarBear.PolarBearClass("net.minecraft.world.entity.animal.EntityPolarBear"));
            load(V1_17_R1.class, "Entity:Boat", new Boat.BoatClass("net.minecraft.world.entity.vehicle.EntityBoat"));
            load(V1_17_R1.class, "Entity:CaveSpider", new CaveSpider.CaveSpiderClass("net.minecraft.world.entity.monster.EntityCaveSpider"));

            load(V1_17_R1.class, "Entity:Ageable", new AgeableEntityLiving.AgeableEntityLivingClass("net.minecraft.world.entity.EntityAgeable"));
            load(V1_17_R1.class, "Entity:Tameable", new TameableEntityLiving.TameableEntityLivingClass("net.minecraft.world.entity.EntityTameableAnimal"));
            // EntityPlayer
            load(V1_17_R1.class, "GameProfile", new GameProfile.GameProfileClass("com.mojang.authlib.GameProfile"));
            load(V1_17_R1.class, "PropertyMap", new PropertyMap.PropertyMapClass("com.mojang.authlib.properties.PropertyMap"));
            load(V1_17_R1.class, "Property", new Property.PropertyClass("com.mojang.authlib.properties.Property"));
            //

            // Managers
            load(V1_17_R1.class, "PlayerInteractManager", new PlayerInteractManager.PlayerInteractManagerClass("net.minecraft.server.level.PlayerInteractManager"));
            //

            // DataWatcher
            load(V1_17_R1.class, "DataWatcher", new DataWatcher.DataWatcherClass("net.minecraft.network.syncher.DataWatcher"));
            load(V1_17_R1.class, "DataWatcherObject", new DataWatcherObject.DataWatcherObjectClass("net.minecraft.network.syncher.DataWatcherObject"));
            load(V1_17_R1.class, "DataWatcher:Item", new DataWatcherItem.DataWatcherItemClass("net.minecraft.network.syncher.DataWatcher$Item"));
            //

            // Scoreboard
            load(V1_17_R1.class, "CraftScoreboard", new CraftScoreboard.CraftScoreboardClass("org.bukkit.craftbukkit.v1_17_R1.scoreboard.CraftScoreboard"));
            load(V1_17_R1.class, "Scoreboard", new Scoreboard.ScoreboardClass("net.minecraft.world.scores.Scoreboard"));

            load(V1_17_R1.class, "ScoreboardTeam", new ScoreboardTeam.ScoreboardTeamClass("net.minecraft.world.scores.ScoreboardTeam"));
            load(V1_17_R1.class, "ScoreboardTeam:EnumTeamPush", new EnumTeamPushEnum.EnumTeamPushClass("net.minecraft.world.scores.ScoreboardTeamBase$EnumTeamPush"));

            load(V1_17_R1.class, "ScoreboardTeamBase:EnumNameTagVisibility", new EnumNameTagVisibilityEnum.EnumNameTagVisibilityClass("net.minecraft.world.scores.ScoreboardTeamBase$EnumNameTagVisibility"));
            //

            // Others
            load(V1_17_R1.class, "PlayerConnection", new PlayerConnection.PlayerConnectionClass("net.minecraft.server.network.PlayerConnection"));
            load(V1_17_R1.class, "NetworkManager", new NetworkManager.NetworkManagerClass("net.minecraft.network.NetworkManager"));

            load(V1_17_R1.class, "EnumChatFormat", new EnumChatFormatEnum.EnumChatFormatClass("net.minecraft.EnumChatFormat"));
            load(V1_17_R1.class, "EnumColor", new EnumColorEnum.EnumColorClass("net.minecraft.world.item.EnumColor"));
            load(V1_17_R1.class, "EnumItemSlot", new EnumItemSlotEnum.EnumItemSlotClass("net.minecraft.world.entity.EnumItemSlot"));
            load(V1_17_R1.class, "EnumDirection", new EnumDirectionEnum.EnumDirectionClass("net.minecraft.core.EnumDirection"));
            //

            // Chat
            load(V1_17_R1.class, "IChatBaseComponent", new IChatBaseComponent.IChatBaseComponentClass("net.minecraft.network.chat.IChatBaseComponent"));
            load(V1_17_R1.class, "ChatSerializer", new IChatBaseComponent.ChatSerializerClass("net.minecraft.network.chat.IChatBaseComponent$ChatSerializer"));
            //

            // Objects
            load(V1_17_R1.class, "CraftWorld", new CraftWorld.CraftWorldClass("org.bukkit.craftbukkit.v1_17_R1.CraftWorld"));
            load(V1_17_R1.class, "World", new World.WorldClass("net.minecraft.world.level.World"));
            load(V1_17_R1.class, "Vector3f", new Vector3f.Vector3fClass("net.minecraft.core.Vector3f"));
            load(V1_17_R1.class, "Vec3D", new Vec3D.Vec3DClass("net.minecraft.world.phys.Vec3D"));
            load(V1_17_R1.class, "BlockPosition", new BlockPosition.BlockPositionClass("net.minecraft.core.BlockPosition"));
            load(V1_17_R1.class, "CraftBlock", new CraftBlock.CraftBlockClass("org.bukkit.craftbukkit.v1_17_R1.block.CraftBlock"));
            load(V1_17_R1.class, "IBlockData", new IBlockData.IBlockDataClass("net.minecraft.world.level.block.state.IBlockData"));
            load(V1_17_R1.class, "Block", new Block.BlockClass("net.minecraft.world.level.block.Block"));
            load(V1_17_R1.class, "CraftMagicNumbers", new ClassExecutor("org.bukkit.craftbukkit.v1_17_R1.util.CraftMagicNumbers"));
            load(V1_17_R1.class, "Pair", new Pair.PairClass("com.mojang.datafixers.util.Pair"));

            load(V1_17_R1.class, "InventorySubcontainer", new InventorySubcontainer.InventorySubcontainerClass("net.minecraft.world.InventorySubcontainer"));
            //

            // Items
            load(V1_17_R1.class, "ItemStack", new ItemStack.ItemStackClass("net.minecraft.world.item.ItemStack"));
            load(V1_17_R1.class, "CraftItemStack", new CraftItemStack.CraftItemStackClass("org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack"));
            //

            // Entity horse
            load(V1_17_R1.class, "Entity:Horse:Abstract", new AbstractHorse.AbstractHorseClass("net.minecraft.world.entity.animal.horse.EntityHorseAbstract"));
            load(V1_17_R1.class, "Entity:Horse:Abstract:Chested", new AbstractChestedHorse.AbstractChestedHorseClass("net.minecraft.world.entity.animal.horse.EntityHorseChestedAbstract"));
            load(V1_17_R1.class, "Entity:Horse:Donkey", new HorseDonkey.HorseDonkeyClass("net.minecraft.world.entity.animal.horse.EntityHorseDonkey"));
            load(V1_17_R1.class, "Entity:Horse:Mule", new HorseMule.HorseMuleClass("net.minecraft.world.entity.animal.horse.EntityHorseMule"));
            load(V1_17_R1.class, "Entity:Horse:Skeleton", new HorseSkeleton.HorseSkeletonClass("net.minecraft.world.entity.animal.horse.EntityHorseSkeleton"));
            load(V1_17_R1.class, "Entity:Horse:Zombie", new HorseZombie.HorseZombieClass("net.minecraft.world.entity.animal.horse.EntityHorseZombie"));
            // Entity skeleton
            load(V1_17_R1.class, "Entity:Skeleton:Wither", new SkeletonWither.SkeletonWitherClass("net.minecraft.world.entity.monster.EntitySkeletonWither"));
            load(V1_17_R1.class, "Entity:Skeleton:Stray", new SkeletonStray.SkeletonStrayClass("net.minecraft.world.entity.monster.EntitySkeletonStray"));
            // Entity zombie
            load(V1_17_R1.class, "Entity:Zombie:Villager", new ZombieVillager.ZombieVillagerClass("net.minecraft.world.entity.monster.EntityZombieVillager"));
            load(V1_17_R1.class, "Entity:Zombie:Husk", new ZombieHusk.ZombieHuskClass("net.minecraft.world.entity.monster.EntityZombieHusk"));
            load(V1_17_R1.class, "Entity:Zombie:Drowned", new ZombieDrowned.ZombieDrownedClass("net.minecraft.world.entity.monster.EntityDrowned"));
            // Entity vindicator
            load(V1_17_R1.class, "Entity:Vindicator", new Vindicator.VindicatorClass("net.minecraft.world.entity.monster.EntityVindicator"));
            // Entity evoker
            load(V1_17_R1.class, "Entity:Evoker", new Evoker.EvokerClass("net.minecraft.world.entity.monster.EntityEvoker"));
            // Entity vex
            load(V1_17_R1.class, "Entity:Vex", new Vex.VexClass("net.minecraft.world.entity.monster.EntityVex"));
            // Entity llama
            load(V1_17_R1.class, "Entity:Llama", new Llama.LlamaClass("net.minecraft.world.entity.animal.horse.EntityLlama"));
            // Entity illager illusioner
            load(V1_17_R1.class, "Entity:Illusioner", new Illusioner.IllusionerClass("net.minecraft.world.entity.monster.EntityIllagerIllusioner"));
            // Entity illager wizard
            load(V1_17_R1.class, "Entity:IllagerWizard", new IllagerWizard.IllagerWizardClass("net.minecraft.world.entity.monster.EntityIllagerWizard"));
            load(V1_17_R1.class, "Entity:IllagerWizard:Spell", new EnumSpellEnum.EnumSpellClass("net.minecraft.world.entity.monster.EntityIllagerWizard$Spell"));
            // Entity parrot
            load(V1_17_R1.class, "Entity:Parrot", new Parrot.ParrotClass("net.minecraft.world.entity.animal.EntityParrot"));
            // Entity dolphin
            load(V1_17_R1.class, "Entity:Dolphin", new Dolphin.DolphinClass("net.minecraft.world.entity.animal.EntityDolphin"));
            // Entity fish
            load(V1_17_R1.class, "Entity:Fish", new Fish.FishClass("net.minecraft.world.entity.animal.EntityFish"));
            load(V1_17_R1.class, "Entity:Cod", new Cod.CodClass("net.minecraft.world.entity.animal.EntityCod"));
            load(V1_17_R1.class, "Entity:Salmon", new Salmon.SalmonClass("net.minecraft.world.entity.animal.EntitySalmon"));
            load(V1_17_R1.class, "Entity:PufferFish", new Pufferfish.PufferfishClass("net.minecraft.world.entity.animal.EntityPufferFish"));
            load(V1_17_R1.class, "Entity:TropicalFish", new Tropicalfish.TropicalfishClass("net.minecraft.world.entity.animal.EntityTropicalFish"));
            // Entity phantom
            load(V1_17_R1.class, "Entity:Phantom", new Phantom.PhantomClass("net.minecraft.world.entity.monster.EntityPhantom"));
            // Entity turtle
            load(V1_17_R1.class, "Entity:Turtle", new Turtle.TurtleClass("net.minecraft.world.entity.animal.EntityTurtle"));
            // Entity cat
            load(V1_17_R1.class, "Entity:Cat", new Cat.CatClass("net.minecraft.world.entity.animal.EntityCat"));
            // Entity villager
            load(V1_17_R1.class, "VillagerData", new VillagerData.VillagerDataClass("net.minecraft.world.entity.npc.VillagerData"));
            load(V1_17_R1.class, "VillagerProfession", new VillagerProfessionExec.VillagerProfessionExecClass("net.minecraft.world.entity.npc.VillagerProfession"));
            load(V1_17_R1.class, "VillagerType", new VillagerType.VillagerTypeClass("net.minecraft.world.entity.npc.VillagerType"));
            //
        }

        return super.getClasses();
    }

    @Override
    public @NotNull Map<String, String> getTexts() {
        if (super.getTexts().get("EntityTypes:ARMOR_STAND").equals("ARMOR_STAND")) {
            super.getTexts().put("EntityTypes:ARMOR_STAND", "c");
            super.getTexts().put("EntityTypes:BAT", "f");
            super.getTexts().put("EntityTypes:EGG", "aM");
            super.getTexts().put("EntityTypes:BLAZE", "h");
            super.getTexts().put("EntityTypes:BOAT", "i");
            super.getTexts().put("EntityTypes:CAT", "j");
            super.getTexts().put("EntityTypes:CAVE_SPIDER", "k");
            super.getTexts().put("EntityTypes:CHICKEN", "l");
            super.getTexts().put("EntityTypes:COD", "m");
            super.getTexts().put("EntityTypes:COW", "n");
            super.getTexts().put("EntityTypes:CREEPER", "o");
            super.getTexts().put("EntityTypes:DONKEY", "q");
            super.getTexts().put("EntityTypes:DOLPHIN", "p");
            super.getTexts().put("EntityTypes:DRAGON_FIREBALL", "r");
            super.getTexts().put("EntityTypes:DROWNED", "s");
            super.getTexts().put("EntityTypes:ELDER_GUARDIAN", "t");
            super.getTexts().put("EntityTypes:END_CRYSTAL", "u");
            super.getTexts().put("EntityTypes:ENDER_DRAGON", "v");
            super.getTexts().put("EntityTypes:ENDERMAN", "w");
            super.getTexts().put("EntityTypes:ENDERMITE", "x");
            super.getTexts().put("EntityTypes:EVOKER_FANGS", "z");
            super.getTexts().put("EntityTypes:EVOKER", "y");
            super.getTexts().put("EntityTypes:EYE_OF_ENDER", "B");
            super.getTexts().put("EntityTypes:FALLING_BLOCK", "C");
            super.getTexts().put("EntityTypes:FOX", "E");
            super.getTexts().put("EntityTypes:GHAST", "F");
            super.getTexts().put("EntityTypes:GIANT", "G");
            super.getTexts().put("EntityTypes:GUARDIAN", "K");
            super.getTexts().put("EntityTypes:HORSE", "M");
            super.getTexts().put("EntityTypes:HUSK", "N");
            super.getTexts().put("EntityTypes:ILLUSIONER", "O");
            super.getTexts().put("EntityTypes:ITEM", "Q");
            super.getTexts().put("EntityTypes:ITEM_FRAME", "R");
            super.getTexts().put("EntityTypes:FIREBALL", "S");
            super.getTexts().put("EntityTypes:LEASH_KNOT", "T");
            super.getTexts().put("EntityTypes:LLAMA", "V");
            super.getTexts().put("EntityTypes:LLAMA_SPIT", "W");
            super.getTexts().put("EntityTypes:MAGMA_CUBE", "X");
            super.getTexts().put("EntityTypes:MINECART", "Z");
            super.getTexts().put("EntityTypes:CHEST_MINECART", "aa");
            super.getTexts().put("EntityTypes:COMMAND_BLOCK_MINECART", "ab");
            super.getTexts().put("EntityTypes:FURNACE_MINECART", "ac");
            super.getTexts().put("EntityTypes:HOPPER_MINECART", "ad");
            super.getTexts().put("EntityTypes:SPAWNER_MINECART", "ae");
            super.getTexts().put("EntityTypes:TNT_MINECART", "af");
            super.getTexts().put("EntityTypes:MULE", "ag");
            super.getTexts().put("EntityTypes:MOOSHROOM", "ah");
            super.getTexts().put("EntityTypes:OCELOT", "ai");
            super.getTexts().put("EntityTypes:PAINTING", "aj");
            super.getTexts().put("EntityTypes:PANDA", "ak");
            super.getTexts().put("EntityTypes:PARROT", "al");
            super.getTexts().put("EntityTypes:PIG", "an");
            super.getTexts().put("EntityTypes:PUFFERFISH", "at");
            super.getTexts().put("EntityTypes:ZOMBIE_PIGMAN", "ao");
            super.getTexts().put("EntityTypes:POLAR_BEAR", "ar");
            super.getTexts().put("EntityTypes:TNT", "as");
            super.getTexts().put("EntityTypes:RABBIT", "au");
            super.getTexts().put("EntityTypes:SALMON", "aw");
            super.getTexts().put("EntityTypes:SHEEP", "ax");
            super.getTexts().put("EntityTypes:SHULKER", "ay");
            super.getTexts().put("EntityTypes:SHULKER_BULLET", "az");
            super.getTexts().put("EntityTypes:SILVERFISH", "aA");
            super.getTexts().put("EntityTypes:SKELETON", "aB");
            super.getTexts().put("EntityTypes:SKELETON_HORSE", "aC");
            super.getTexts().put("EntityTypes:SLIME", "aD");
            super.getTexts().put("EntityTypes:SMALL_FIREBALL", "aE");
            super.getTexts().put("EntityTypes:SNOW_GOLEM", "aF");
            super.getTexts().put("EntityTypes:SNOWBALL", "aG");
            super.getTexts().put("EntityTypes:SPIDER", "aI");
            super.getTexts().put("EntityTypes:SQUID", "aJ");
            super.getTexts().put("EntityTypes:STRAY", "aK");
            super.getTexts().put("EntityTypes:TRADER_LLAMA", "aR");
            super.getTexts().put("EntityTypes:TROPICAL_FISH", "aS");
            super.getTexts().put("EntityTypes:TURTLE", "aT");
            super.getTexts().put("EntityTypes:ENDER_PEARL", "aN");
            super.getTexts().put("EntityTypes:VEX", "aU");
            super.getTexts().put("EntityTypes:VILLAGER", "aV");
            super.getTexts().put("EntityTypes:IRON_GOLEM", "P");
            super.getTexts().put("EntityTypes:VINDICATOR", "aW");
            super.getTexts().put("EntityTypes:PILLAGER", "aq");
            super.getTexts().put("EntityTypes:WANDERING_TRADER", "aX");
            super.getTexts().put("EntityTypes:WITCH", "aY");
            super.getTexts().put("EntityTypes:WITHER", "aZ");
            super.getTexts().put("EntityTypes:WITHER_SKELETON", "ba");
            super.getTexts().put("EntityTypes:WITHER_SKULL", "bb");
            super.getTexts().put("EntityTypes:WOLF", "bc");
            super.getTexts().put("EntityTypes:ZOMBIE", "be");
            super.getTexts().put("EntityTypes:ZOMBIE_HORSE", "bf");
            super.getTexts().put("EntityTypes:ZOMBIE_VILLAGER", "bg");
            super.getTexts().put("EntityTypes:PHANTOM", "am");
            super.getTexts().put("EntityTypes:RAVAGER", "av");
        }

        return super.getTexts();
    }

    @Override
    public void setEntityLocation(@NotNull Entity entity, @NotNull org.bukkit.Location location) {
        laivynpc().getVersion().getMethodExec("Entity:Entity:setLocation").invokeInstance(entity, new DoubleObjExec(location.getX()), new DoubleObjExec(location.getY()), new DoubleObjExec(location.getZ()), new FloatObjExec(location.getYaw()), new FloatObjExec(location.getPitch()));
    }

    @Override
    public @NotNull Set<EntityDestroyPacket> createDestroyPacket(@NotNull Entity... entities) {
        Set<EntityDestroyPacket> packets = new HashSet<>();
        ClassConstructor constructor = getClassExec("PacketPlayOutEntityDestroy").getConstructor(ClassExecutor.INT);

        for (Entity entity : entities) {
            packets.add(new EntityDestroyPacket(constructor.newInstance(new IntegerObjExec(entity.getId()))));
        }

        return packets;
    }

    @Override
    public @NotNull ScoreboardTeamPacket createScoreboardTeamPacket(@NotNull ScoreboardTeam team, boolean b) {
        MethodExecutor method = new MethodExecutor(getClassExec("PacketPlayOutScoreboardTeam"), getClassExec("PacketPlayOutScoreboardTeam"), "a", "Gets a new scoreboard team packet instance", getClassExec("ScoreboardTeam"), ClassExecutor.BOOLEAN) {{
            load();
        }};

        return new ScoreboardTeamPacket(method.invokeStatic(team, new BooleanObjExec(b)));
    }

    @Override
    public @NotNull EntityPlayer createPlayer(@NotNull GameProfile profile, @NotNull org.bukkit.Location location) {
        if (location.getWorld() == null) {
            throw new NullPointerException("This location's world is null!");
        }

        EntityPlayer player = new EntityPlayer(getClassExec("EntityPlayer").getConstructor(
                getClassExec("MinecraftServer"),
                getClassExec("WorldServer"),
                getClassExec("GameProfile")
        ).newInstance(
                CraftServer.getCraftServer(Bukkit.getServer()).getServer(),
                CraftWorld.getCraftWorld(location.getWorld()).getHandle(),
                profile
        ));
        player.setLocation(location);
        return player;
    }

}
