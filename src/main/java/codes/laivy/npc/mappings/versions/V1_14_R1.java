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
import codes.laivy.npc.mappings.defaults.classes.entity.npc.VillagerProfession;
import codes.laivy.npc.mappings.defaults.classes.entity.npc.objs.VillagerProfessionExec;
import codes.laivy.npc.mappings.defaults.classes.entity.npc.objs.VillagerType;
import codes.laivy.npc.mappings.defaults.classes.entity.vehicle.Boat;
import codes.laivy.npc.mappings.defaults.classes.enums.*;
import codes.laivy.npc.mappings.defaults.classes.gameprofile.GameProfile;
import codes.laivy.npc.mappings.defaults.classes.gameprofile.Property;
import codes.laivy.npc.mappings.defaults.classes.gameprofile.PropertyMap;
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
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class V1_14_R1 extends V1_13_R2 {

    @Override
    protected boolean onLoad(@NotNull Class<? extends Version> version, @NotNull String key, @NotNull Executor executor) {
        if (version == V1_13_R2.class) {
            if (executor instanceof ClassExecutor && !(executor instanceof EnumExecutor)) {
                return false;
            } else if (executor instanceof FieldExecutor) {
                if (key.equals("Metadata:PufferFish:PuffState")) {
                    load(V1_14_R1.class, key, new FieldExecutor(getClassExec("Entity:PufferFish"), getClassExec("DataWatcherObject"), "b", "Gets the puffer fish's puff state DataWatcherObject"));
                    return false;
                }
            }
        } else if (version == V1_13_R1.class) {
            if (executor instanceof FieldExecutor) {
                switch (key) {
                    case "Metadata:TropicalFish:Variant":
                        load(V1_14_R1.class, key, new FieldExecutor(getClassExec("Entity:TropicalFish"), getClassExec("DataWatcherObject"), "c", "Gets the tropical fish's variant DataWatcherObject"));
                        return false;
                    case "Metadata:Dolphin:hasFish":
                        load(V1_14_R1.class, key, new FieldExecutor(getClassExec("Entity:Dolphin"), getClassExec("DataWatcherObject"), "d", "Gets the dolphin's hasFish DataWatcherObject"));
                        return false;
                    case "Metadata:Phantom:Size":
                        load(V1_14_R1.class, key, new FieldExecutor(getClassExec("Entity:Phantom"), getClassExec("DataWatcherObject"), "b", "Gets the phantom's size DataWatcherObject"));
                        return false;
                    case "Metadata:Turtle:Egg":
                        load(V1_14_R1.class, key, new FieldExecutor(getClassExec("Entity:Turtle"), getClassExec("DataWatcherObject"), "bB", "Gets the turtle's egg DataWatcherObject", false, true));
                        return false;
                    case "Metadata:Zombie:Baby":
                        load(V1_14_R1.class, key, new FieldExecutor(getClassExec("Entity:Zombie"), getClassExec("DataWatcherObject"), "bz", "Gets the zombie baby DataWatcherObject"));
                        return false;
                    case "Metadata:Llama:Variant":
                        load(V1_14_R1.class, key, new FieldExecutor(getClassExec("Entity:Llama"), getClassExec("DataWatcherObject"), "bL", "Gets the llama's variant DataWatcherObject"));
                        return false;
                    case "Metadata:Llama:CarpetColor":
                        load(V1_14_R1.class, key, new FieldExecutor(getClassExec("Entity:Llama"), getClassExec("DataWatcherObject"), "bK", "Gets the llama's carpet color DataWatcherObject"));
                        return false;
                    case "Metadata:Guardian:Target":
                        load(V1_14_R1.class, key, new FieldExecutor(getClassExec("Entity:Guardian"), getClassExec("DataWatcherObject"), "bD", "Gets the Guardian target DataWatcherObject"));
                        return false;
                    case "Metadata:Enderman:screaming":
                        load(V1_14_R1.class, key, new FieldExecutor(getClassExec("Entity:Enderman"), getClassExec("DataWatcherObject"), "bz", "Gets the enderman's screaming datawatcher object"));
                        return false;
                    case "Metadata:PolarBear:Standing":
                        load(V1_14_R1.class, key, new FieldExecutor(getClassExec("Entity:PolarBear"), getClassExec("DataWatcherObject"), "bz", "Gets the polar bear's standing DataWatcherObject"));
                        return false;
                    case "Metadata:Pig:Saddle":
                        load(V1_14_R1.class, key, new FieldExecutor(getClassExec("Entity:Pig"), getClassExec("DataWatcherObject"), "bz", "Gets the pig saddle DataWatcherObject"));
                        return false;
                    case "Metadata:Wolf:Angry":
                        load(V1_14_R1.class, key, new FieldExecutor(getClassExec("Entity:Wolf"), getClassExec("DataWatcherObject"), "bF", "Gets the wolf angry DataWatcherObject"));
                        return false;
                    case "Metadata:Wolf:CollarColor":
                        load(V1_14_R1.class, key, new FieldExecutor(getClassExec("Entity:Wolf"), getClassExec("DataWatcherObject"), "bG", "Gets the wolf collar color DataWatcherObject"));
                        return false;
                    case "Metadata:Horse:Variant":
                        load(V1_14_R1.class, key, new FieldExecutor(getClassExec("Entity:Horse"), getClassExec("DataWatcherObject"), "bK", "Gets the horse variant DataWatcherObject"));
                        return false;
                    case "Metadata:Zombie:Villager:Profession":
                    case "Metadata:Horse:Armor":
                        return false;
                    default:
                        break;
                }
            } else if (executor instanceof MethodExecutor) {
                if (key.equals("Entity:isGlowing")) {
                    load(V1_14_R1.class, key, new MethodExecutor(getClassExec("Entity"), ClassExecutor.BOOLEAN, "bl", "Gets the glowing state of a Entity"));
                    return false;
                }
            }
        } else if (version == V1_8_R1.class) {
            if (executor instanceof MethodExecutor) {
                switch (key) {
                    case "NBTTagCompound:set":
                        load(V1_14_R1.class, key, new MethodExecutor(getClassExec("NBTBase:NBTTagCompound"), getClassExec("NBTBase"), "set", "Sets a value inside a NBTTagCompound", ClassExecutor.STRING, getClassExec("NBTBase")));
                        return false;
                    case "Entity:Villager:getProfession":
                    case "Entity:Villager:setProfession":
                    case "Entity:Ocelot:getCatType":
                    case "Entity:Ocelot:setCatType":
                        return false;
                    default:
                        break;
                }
            }
        } else if (version == V1_9_R1.class) {
            if (executor instanceof FieldExecutor) {
                switch (key) {
                    case "Metadata:Blaze:Charging":
                        load(V1_14_R1.class, key, new FieldExecutor(getClassExec("Entity:Blaze"), getClassExec("DataWatcherObject"), "d", "Gets the blaze charging DataWatcherObject"));
                        return false;
                    case "Metadata:Shulker:Direction":
                        load(V1_14_R1.class, key, new FieldExecutor(getClassExec("Entity:Shulker"), getClassExec("DataWatcherObject"), "b", "Get shulker's direction DataWatcher's object"));
                        return false;
                    case "Metadata:Shulker:Peek":
                        load(V1_14_R1.class, key, new FieldExecutor(getClassExec("Entity:Shulker"), getClassExec("DataWatcherObject"), "d", "Get shulker's peek DataWatcher's object"));
                        return false;
                    case "Metadata:Ghast:Attacking":
                        load(V1_14_R1.class, key, new FieldExecutor(getClassExec("Entity:Ghast"), getClassExec("DataWatcherObject"), "b", "Gets the ghast attacking DataWatcherObject"));
                        return false;
                    case "Metadata:Creeper:Ignited":
                        load(V1_14_R1.class, key, new FieldExecutor(getClassExec("Entity:Creeper"), getClassExec("DataWatcherObject"), "d", "Gets the creeper ignited DataWatcherObject"));
                        return false;
                    case "Metadata:Creeper:Powered":
                        load(V1_14_R1.class, key, new FieldExecutor(getClassExec("Entity:Creeper"), getClassExec("DataWatcherObject"), "POWERED", "Gets the creeper ignited DataWatcherObject"));
                        return false;
                    case "Metadata:Spider:Climbing":
                        load(V1_14_R1.class, key, new FieldExecutor(getClassExec("Entity:Spider"), getClassExec("DataWatcherObject"), "b", "Gets the spider climbing DataWatcherObject"));
                        return false;
                    default:
                        break;
                }
            }
        }

        return super.onLoad(version, key, executor);
    }

    @Override
    public @Nullable Entity getEntityInstance(Entity.@NotNull EntityType type, org.bukkit.@NotNull Location location) {
        Entity entity;
        Object object;

        World world = CraftWorld.getCraftWorld(location.getWorld()).getHandle();

        if (type == Entity.EntityType.ARMOR_STAND) {
            object = getClassExec("Entity:ArmorStand").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.ARMOR_STAND, world);
            entity = new ArmorStand(object);
        } else if (type == Entity.EntityType.PIG) {
            object = getClassExec("Entity:Pig").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.PIG, world);
            entity = new Pig(object);
        } else if (type == Entity.EntityType.COW) {
            object = getClassExec("Entity:Cow").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.COW, world);
            entity = new Cow(object);
        } else if (type == Entity.EntityType.OCELOT) {
            object = getClassExec("Entity:Ocelot").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.OCELOT, world);
            entity = new Ocelot(object);
        } else if (type == Entity.EntityType.BAT) {
            object = getClassExec("Entity:Bat").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.BAT, world);
            entity = new Bat(object);
        } else if (type == Entity.EntityType.EGG) {
            object = getClassExec("Entity:Egg").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.EGG, world);
            entity = new Egg(object);
        } else if (type == Entity.EntityType.CHICKEN) {
            object = getClassExec("Entity:Chicken").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.CHICKEN, world);
            entity = new Chicken(object);
        } else if (type == Entity.EntityType.HORSE) {
            object = getClassExec("Entity:Horse").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.HORSE, world);
            entity = new Horse(object);
        } else if (type == Entity.EntityType.HORSE_DONKEY) {
            object = getClassExec("Entity:Horse:Donkey").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.DONKEY, world);
            entity = new HorseDonkey(object);
        } else if (type == Entity.EntityType.HORSE_MULE) {
            object = getClassExec("Entity:Horse:Mule").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.MULE, world);
            entity = new HorseMule(object);
        } else if (type == Entity.EntityType.HORSE_ZOMBIE) {
            object = getClassExec("Entity:Horse:Zombie").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.ZOMBIE_HORSE, world);
            entity = new HorseZombie(object);
        } else if (type == Entity.EntityType.HORSE_SKELETON) {
            object = getClassExec("Entity:Horse:Skeleton").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.SKELETON_HORSE, world);
            entity = new HorseSkeleton(object);
        } else if (type == Entity.EntityType.LLAMA) {
            object = getClassExec("Entity:Llama").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.LLAMA, world);
            entity = new Llama(object);
        } else if (type == Entity.EntityType.IRON_GOLEM) {
            object = getClassExec("Entity:IronGolem").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.IRON_GOLEM, world);
            entity = new IronGolem(object);
        } else if (type == Entity.EntityType.RABBIT) {
            object = getClassExec("Entity:Rabbit").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.RABBIT, world);
            entity = new Rabbit(object);
        } else if (type == Entity.EntityType.SHEEP) {
            object = getClassExec("Entity:Sheep").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.SHEEP, world);
            entity = new Sheep(object);
        } else if (type == Entity.EntityType.SNOWMAN) {
            object = getClassExec("Entity:Snowman").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.SNOW_GOLEM, world);
            entity = new Snowman(object);
        } else if (type == Entity.EntityType.SQUID) {
            object = getClassExec("Entity:Squid").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.SQUID, world);
            entity = new Squid(object);
        } else if (type == Entity.EntityType.WOLF) {
            object = getClassExec("Entity:Wolf").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.WOLF, world);
            entity = new Wolf(object);
        } else if (type == Entity.EntityType.ITEM_FRAME) {
            object = getClassExec("Entity:ItemFrame").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.ITEM_FRAME, world);
            entity = new ItemFrame(object);
        } else if (type == Entity.EntityType.LEASH_KNOT) {
            object = getClassExec("Entity:LeashKnot").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.LEASH_KNOT, world);
            entity = new LeashKnot(object);
        } else if (type == Entity.EntityType.FALLING_BLOCK) {
            object = getClassExec("Entity:FallingBlock").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.FALLING_BLOCK, world);
            entity = new FallingBlock(object);
        } else if (type == Entity.EntityType.ITEM) {
            object = getClassExec("Entity:Item").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.ITEM, world);
            entity = new Item(object);
        } else if (type == Entity.EntityType.ENDER_DRAGON) {
            object = getClassExec("Entity:EnderDragon").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.ENDER_DRAGON, world);
            entity = new EnderDragon(object);
        } else if (type == Entity.EntityType.ENDER_SIGNAL) {
            object = getClassExec("Entity:EnderSignal").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.EYE_OF_ENDER, world);
            entity = new EnderSignal(object);
        } else if (type == Entity.EntityType.WITHER) {
            object = getClassExec("Entity:Wither").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.WITHER, world);
            entity = new Wither(object);
        } else if (type == Entity.EntityType.WITHER_SKULL) {
            object = getClassExec("Entity:WitherSkull").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.WITHER_SKULL, world);
            entity = new WitherSkull(object);
        } else if (type == Entity.EntityType.BLAZE) {
            object = getClassExec("Entity:Blaze").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.BLAZE, world);
            entity = new Blaze(object);
        } else if (type == Entity.EntityType.CREEPER) {
            object = getClassExec("Entity:Creeper").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.CREEPER, world);
            entity = new Creeper(object);
        } else if (type == Entity.EntityType.ENDERMAN) {
            object = getClassExec("Entity:Enderman").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.ENDERMAN, world);
            entity = new Enderman(object);
        } else if (type == Entity.EntityType.GHAST) {
            object = getClassExec("Entity:Ghast").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.GHAST, world);
            entity = new Ghast(object);
        } else if (type == Entity.EntityType.GUARDIAN) {
            object = getClassExec("Entity:Guardian").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.GUARDIAN, world);
            entity = new Guardian(object);
        } else if (type == Entity.EntityType.SILVERFISH) {
            object = getClassExec("Entity:Silverfish").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.SILVERFISH, world);
            entity = new Silverfish(object);
        } else if (type == Entity.EntityType.SKELETON) {
            object = getClassExec("Entity:Skeleton").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.SKELETON, world);
            entity = new Skeleton(object);
        } else if (type == Entity.EntityType.SKELETON_WITHER) {
            object = getClassExec("Entity:Skeleton:Wither").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.WITHER_SKELETON, world);
            entity = new SkeletonWither(object);
        } else if (type == Entity.EntityType.SKELETON_STRAY) {
            object = getClassExec("Entity:Skeleton:Stray").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.STRAY, world);
            entity = new SkeletonStray(object);
        } else if (type == Entity.EntityType.SLIME) {
            object = getClassExec("Entity:Slime").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.SLIME, world);
            entity = new Slime(object);
        } else if (type == Entity.EntityType.SPIDER) {
            object = getClassExec("Entity:Spider").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.SPIDER, world);
            entity = new Spider(object);
        } else if (type == Entity.EntityType.WITCH) {
            object = getClassExec("Entity:Witch").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.WITCH, world);
            entity = new Witch(object);
        } else if (type == Entity.EntityType.ZOMBIE) {
            object = getClassExec("Entity:Zombie").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.ZOMBIE, world);
            entity = new Zombie(object);
        } else if (type == Entity.EntityType.ZOMBIE_HUSK) {
            object = getClassExec("Entity:Zombie:Husk").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.HUSK, world);
            entity = new ZombieHusk(object);
        } else if (type == Entity.EntityType.ZOMBIE_VILLAGER) {
            object = getClassExec("Entity:Zombie:Villager").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.ZOMBIE_VILLAGER, world);
            entity = new ZombieVillager(object);
        } else if (type == Entity.EntityType.ZOMBIE_DROWNED) {
            object = getClassExec("Entity:Zombie:Drowned").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.DROWNED, world);
            entity = new ZombieDrowned(object);
        } else if (type == Entity.EntityType.VILLAGER) {
            object = getClassExec("Entity:Villager").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.VILLAGER, world);
            entity = new Villager(object);
        } else if (type == Entity.EntityType.SHULKER) {
            object = getClassExec("Entity:Shulker").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.SHULKER, world);
            entity = new Shulker(object);
        } else if (type == Entity.EntityType.POLAR_BEAR) {
            object = getClassExec("Entity:PolarBear").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.POLAR_BEAR, world);
            entity = new PolarBear(object);
        } else if (type == Entity.EntityType.VINDICATOR) {
            object = getClassExec("Entity:Vindicator").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.VINDICATOR, world);
            entity = new Vindicator(object);
        } else if (type == Entity.EntityType.EVOKER) {
            object = getClassExec("Entity:Evoker").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.EVOKER, world);
            entity = new Evoker(object);
        } else if (type == Entity.EntityType.VEX) {
            object = getClassExec("Entity:Vex").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.VEX, world);
            entity = new Vex(object);
        } else if (type == Entity.EntityType.ILLUSIONER) {
            object = getClassExec("Entity:Illusioner").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.ILLUSIONER, world);
            entity = new Illusioner(object);
        } else if (type == Entity.EntityType.PARROT) {
            object = getClassExec("Entity:Parrot").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.PARROT, world);
            entity = new Parrot(object);
        } else if (type == Entity.EntityType.DOLPHIN) {
            object = getClassExec("Entity:Dolphin").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.DOLPHIN, world);
            entity = new Dolphin(object);
        } else if (type == Entity.EntityType.COD) {
            object = getClassExec("Entity:Cod").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.COD, world);
            entity = new Cod(object);
        } else if (type == Entity.EntityType.SALMON) {
            object = getClassExec("Entity:Salmon").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.SALMON, world);
            entity = new Salmon(object);
        } else if (type == Entity.EntityType.PUFFERFISH) {
            object = getClassExec("Entity:PufferFish").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.PUFFERFISH, world);
            entity = new Pufferfish(object);
        } else if (type == Entity.EntityType.TROPICALFISH) {
            object = getClassExec("Entity:TropicalFish").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.TROPICAL_FISH, world);
            entity = new Tropicalfish(object);
        } else if (type == Entity.EntityType.PHANTOM) {
            object = getClassExec("Entity:Phantom").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.PHANTOM, world);
            entity = new Phantom(object);
        } else if (type == Entity.EntityType.TURTLE) {
            object = getClassExec("Entity:Turtle").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.TURTLE, world);
            entity = new Turtle(object);
        } else if (type == Entity.EntityType.CAT) {
            object = getClassExec("Entity:Cat").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.CAT, world);
            entity = new Cat(object);
        } else if (type == Entity.EntityType.BOAT) {
            object = getClassExec("Entity:Boat").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.BOAT, world);
            entity = new Boat(object);
        } else if (type == Entity.EntityType.CAVE_SPIDER) {
            object = getClassExec("Entity:CaveSpider").getConstructor(getClassExec("EntityTypes"), getClassExec("World")).newInstance(EntityTypes.CAVE_SPIDER, world);
            entity = new CaveSpider(object);
        } else {
            throw new IllegalArgumentException("Couldn't get this entity type '" + type.name() + "' instance");
        }

        return entity;
    }

    @Override
    public @NotNull EntityPlayer createPlayer(@NotNull GameProfile profile, @NotNull org.bukkit.Location location) {
        if (location.getWorld() == null) {
            throw new NullPointerException("This location's world is null!");
        }

        PlayerInteractManager manager = new PlayerInteractManager(getClassExec("PlayerInteractManager").getConstructor(getClassExec("WorldServer")).newInstance(CraftWorld.getCraftWorld(location.getWorld()).getHandle()));

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
    public @NotNull HorseArmor getEntityHorseArmor(@NotNull Horse horse) {
        switch (horse.getInventory().getItem(1).getType().toString()) {
            case "IRON_BARDING":
                return HorseArmor.IRON;
            case "GOLD_BARDING":
                return HorseArmor.GOLD;
            case "DIAMOND_BARDING":
                return HorseArmor.DIAMOND;
            default:
                return HorseArmor.NONE;
        }
    }

    @Override
    public void setEntityHorseArmor(@NotNull Horse horse, @NotNull HorseArmor armor) {
        if (!armor.isCompatible()) {
            throw new UnsupportedOperationException("This horse armor type isn't compatible with that version '" + armor.name() + "'");
        }

        ItemStack item;
        switch (armor) {
            case NONE:
                item = ItemStack.getNMSItemStack(new org.bukkit.inventory.ItemStack(Material.AIR));
                break;
            case IRON:
                item = ItemStack.getNMSItemStack(new org.bukkit.inventory.ItemStack(Material.IRON_BARDING));
                break;
            case GOLD:
                item = ItemStack.getNMSItemStack(new org.bukkit.inventory.ItemStack(Material.GOLD_BARDING));
                break;
            case DIAMOND:
                item = ItemStack.getNMSItemStack(new org.bukkit.inventory.ItemStack(Material.DIAMOND_BARDING));
                break;
            default:
                throw new UnsupportedOperationException("This armor type '" + armor.name() + "' isn't available at this version.");
        }

        horse.getInventory().setItem(1, item.getCraftItemStack().getItemStack());
    }

    public @NotNull ItemStack inventorySubcontainerGetItem(@NotNull InventorySubcontainer inventory, int slot) {
        return new ItemStack(getMethodExec("InventorySubcontainer:getItem").invokeInstance(inventory, new IntegerObjExec(slot)));
    }
    public void inventorySubcontainerSetItem(@NotNull InventorySubcontainer inventory, int slot, @NotNull ItemStack item) {
        getMethodExec("InventorySubcontainer:setItem").invokeInstance(inventory, new IntegerObjExec(slot), item);
    }
    public @NotNull InventorySubcontainer getHorseInventory(@NotNull AbstractHorse horse) {
        return new InventorySubcontainer(laivynpc().getVersion().getFieldExec("Entity:Horse:Abstract:horseInventory").invokeInstance(horse));
    }

    @Override
    public @NotNull VillagerProfession getEntityVillagerProfession(@NotNull Villager villager) {
        VillagerData data = getEntityVillagerData(villager);

        VillagerProfessionExec professionExec = data.getProfession();
        int level = data.getLevel();

        VillagerProfession.Type type;
        if (professionExec.getName().equalsIgnoreCase("none")) {
            type = VillagerProfession.Type.NONE;
        } else if (professionExec.getName().equalsIgnoreCase("armorer")) {
            type = VillagerProfession.Type.ARMORER;
        } else if (professionExec.getName().equalsIgnoreCase("butcher")) {
            type = VillagerProfession.Type.BUTCHER;
        } else if (professionExec.getName().equalsIgnoreCase("cartographer")) {
            type = VillagerProfession.Type.CARTOGRAPHER;
        } else if (professionExec.getName().equalsIgnoreCase("cleric")) {
            type = VillagerProfession.Type.CLERIC;
        } else if (professionExec.getName().equalsIgnoreCase("farmer")) {
            type = VillagerProfession.Type.FARMER;
        } else if (professionExec.getName().equalsIgnoreCase("fisherman")) {
            type = VillagerProfession.Type.FISHERMAN;
        } else if (professionExec.getName().equalsIgnoreCase("fletcher")) {
            type = VillagerProfession.Type.FLETCHER;
        } else if (professionExec.getName().equalsIgnoreCase("leatherworker")) {
            type = VillagerProfession.Type.LEATHERWORKER;
        } else if (professionExec.getName().equalsIgnoreCase("librarian")) {
            type = VillagerProfession.Type.LIBRARIAN;
        } else if (professionExec.getName().equalsIgnoreCase("mason")) {
            type = VillagerProfession.Type.MASON;
        } else if (professionExec.getName().equalsIgnoreCase("nitwit")) {
            type = VillagerProfession.Type.NITWIT;
        } else if (professionExec.getName().equalsIgnoreCase("shepherd")) {
            type = VillagerProfession.Type.SHEPHERD;
        } else if (professionExec.getName().equalsIgnoreCase("toolsmith")) {
            type = VillagerProfession.Type.TOOLSMITH;
        } else if (professionExec.getName().equalsIgnoreCase("weaponsmith")) {
            type = VillagerProfession.Type.WEAPONSMITH;
        } else {
            throw new IllegalArgumentException("Couldn't find this villager profession type '" + professionExec.getName() + "'");
        }

        return new VillagerProfession(type, level);
    }
    @Override
    public void setEntityVillagerProfession(@NotNull Villager villager, @NotNull VillagerProfession profession) {
        villager.getDataWatcher().set(Villager.DATA_METADATA(), createVillagerData(getEntityVillagerType(villager).getVillagerType(), profession));
    }
    @Override
    public @NotNull Villager.Type getEntityVillagerType(@NotNull Villager villager) {
        VillagerData data = getEntityVillagerData(villager);
        return Villager.Type.fromVillagerType(data.getType());
    }
    @Override
    public void setEntityVillagerType(@NotNull Villager villager, Villager.@NotNull Type type) {
        villager.getDataWatcher().set(Villager.DATA_METADATA(), createVillagerData(type.getVillagerType(), getEntityVillagerProfession(villager)));
    }

    @Override
    public @NotNull Villager.Type getEntityZombieVillagerType(@NotNull ZombieVillager zombieVillager) {
        VillagerData data = getEntityZombieVillagerData(zombieVillager);
        return Villager.Type.fromVillagerType(data.getType());
    }
    @Override
    public void setEntityZombieVillagerType(@NotNull ZombieVillager zombieVillager, Villager.@NotNull Type type) {
        zombieVillager.getDataWatcher().set(ZombieVillager.DATA_METADATA(), createVillagerData(type.getVillagerType(), getEntityZombieVillagerProfession(zombieVillager)));
    }

    @Override
    public @NotNull VillagerProfession getEntityZombieVillagerProfession(@NotNull ZombieVillager zombieVillager) {
        VillagerData data = getEntityZombieVillagerData(zombieVillager);

        VillagerProfessionExec professionExec = data.getProfession();
        int level = data.getLevel();

        VillagerProfession.Type type;
        if (professionExec.getName().equalsIgnoreCase("none")) {
            type = VillagerProfession.Type.NONE;
        } else if (professionExec.getName().equalsIgnoreCase("armorer")) {
            type = VillagerProfession.Type.ARMORER;
        } else if (professionExec.getName().equalsIgnoreCase("butcher")) {
            type = VillagerProfession.Type.BUTCHER;
        } else if (professionExec.getName().equalsIgnoreCase("cartographer")) {
            type = VillagerProfession.Type.CARTOGRAPHER;
        } else if (professionExec.getName().equalsIgnoreCase("cleric")) {
            type = VillagerProfession.Type.CLERIC;
        } else if (professionExec.getName().equalsIgnoreCase("farmer")) {
            type = VillagerProfession.Type.FARMER;
        } else if (professionExec.getName().equalsIgnoreCase("fisherman")) {
            type = VillagerProfession.Type.FISHERMAN;
        } else if (professionExec.getName().equalsIgnoreCase("fletcher")) {
            type = VillagerProfession.Type.FLETCHER;
        } else if (professionExec.getName().equalsIgnoreCase("leatherworker")) {
            type = VillagerProfession.Type.LEATHERWORKER;
        } else if (professionExec.getName().equalsIgnoreCase("librarian")) {
            type = VillagerProfession.Type.LIBRARIAN;
        } else if (professionExec.getName().equalsIgnoreCase("mason")) {
            type = VillagerProfession.Type.MASON;
        } else if (professionExec.getName().equalsIgnoreCase("nitwit")) {
            type = VillagerProfession.Type.NITWIT;
        } else if (professionExec.getName().equalsIgnoreCase("shepherd")) {
            type = VillagerProfession.Type.SHEPHERD;
        } else if (professionExec.getName().equalsIgnoreCase("toolsmith")) {
            type = VillagerProfession.Type.TOOLSMITH;
        } else if (professionExec.getName().equalsIgnoreCase("weaponsmith")) {
            type = VillagerProfession.Type.WEAPONSMITH;
        } else {
            throw new IllegalArgumentException("Couldn't find this villager profession type '" + professionExec.getName() + "'");
        }

        return new VillagerProfession(type, level);
    }
    @Override
    public void setEntityZombieVillagerProfession(@NotNull ZombieVillager zombieVillager, @NotNull VillagerProfession profession) {
        zombieVillager.getDataWatcher().set(ZombieVillager.DATA_METADATA(), createVillagerData(getEntityZombieVillagerType(zombieVillager).getVillagerType(), profession));
    }

    @Override
    public @NotNull Cat.CatVariant getEntityCatVariant(@NotNull Cat cat) {
        //noinspection DataFlowIssue
        return Cat.CatVariant.getById((Integer) cat.getDataWatcher().get(Cat.VARIANT_METADATA()));
    }
    @Override
    public void setEntityCatVariant(@NotNull Cat cat, Cat.@NotNull CatVariant variant) {
        cat.getDataWatcher().set(Cat.VARIANT_METADATA(), new IntegerObjExec(variant.getId()));
    }

    public @NotNull VillagerProfessionExec getVillagerDataProfession(@NotNull VillagerData villagerData) {
        return new VillagerProfessionExec(getMethodExec("VillagerData:getProfession").invokeInstance(villagerData));
    }
    public int getVillagerDataLevel(@NotNull VillagerData villagerData) {
        //noinspection DataFlowIssue
        return (int) getMethodExec("VillagerData:getLevel").invokeInstance(villagerData);
    }
    public @NotNull VillagerType getVillagerDataType(@NotNull VillagerData data) {
        return new VillagerType(getMethodExec("VillagerData:getType").invokeInstance(data));
    }

    protected @NotNull VillagerProfessionExec createVillagerProfessionExec(@NotNull VillagerProfession profession) {
        return new VillagerProfessionExec(getFieldExec("VillagerProfession:PROFESSIONS:" + profession.getType().name()).invokeStatic());
    }
    protected @NotNull VillagerData createVillagerData(@NotNull VillagerType type, @NotNull VillagerProfession profession) {
        Object instance = getClassExec("VillagerData").getConstructor(getClassExec("VillagerType"), getClassExec("VillagerProfession"), ClassExecutor.INT).newInstance(type, createVillagerProfessionExec(profession), new IntegerObjExec(profession.getLevel()));
        return new VillagerData(instance);
    }
    protected @NotNull VillagerData getEntityVillagerData(@NotNull Villager villager) {
        return new VillagerData(villager.getDataWatcher().get(Villager.DATA_METADATA()));
    }

    // ZOMBIE VILLAGER
    //
    protected @NotNull VillagerData getEntityZombieVillagerData(@NotNull ZombieVillager zombieVillager) {
        return new VillagerData(zombieVillager.getDataWatcher().get(ZombieVillager.DATA_METADATA()));
    }
    //
    // ZOMBIE VILLAGER


    @Override
    public void loadClasses() {
        load(V1_14_R1.class, "WatchableObject", new ClassExecutor.BrokenClassExecutor());
        load(V1_14_R1.class, "EnumSkeletonType", new ClassExecutor.BrokenClassExecutor());
        load(V1_14_R1.class, "EnumHorseType", new ClassExecutor.BrokenClassExecutor());
        load(V1_14_R1.class, "EnumZombieType", new ClassExecutor.BrokenClassExecutor());

        load(V1_14_R1.class, "NBTBase", new NBTBase.NBTBaseClass("net.minecraft.server.v1_14_R1.NBTBase"));

        load(V1_14_R1.class, "NBTBase:NBTTagByte", new NBTTagByte.NBTTagByteClass("net.minecraft.server.v1_14_R1.NBTTagByte"));
        load(V1_14_R1.class, "NBTBase:NBTTagByteArray", new NBTTagByteArray.NBTTagByteArrayClass("net.minecraft.server.v1_14_R1.NBTTagByteArray"));
        load(V1_14_R1.class, "NBTBase:NBTTagCompound", new NBTTagCompound.NBTTagCompoundClass("net.minecraft.server.v1_14_R1.NBTTagCompound"));
        load(V1_14_R1.class, "NBTBase:NBTTagDouble", new NBTTagDouble.NBTTagDoubleClass("net.minecraft.server.v1_14_R1.NBTTagDouble"));
        load(V1_14_R1.class, "NBTBase:NBTTagFloat", new NBTTagFloat.NBTTagFloatClass("net.minecraft.server.v1_14_R1.NBTTagFloat"));
        load(V1_14_R1.class, "NBTBase:NBTTagInt", new NBTTagInt.NBTTagIntClass("net.minecraft.server.v1_14_R1.NBTTagInt"));
        load(V1_14_R1.class, "NBTBase:NBTTagIntArray", new NBTTagIntArray.NBTTagIntArrayClass("net.minecraft.server.v1_14_R1.NBTTagIntArray"));
        load(V1_14_R1.class, "NBTBase:NBTTagList", new NBTTagList.NBTTagListClass("net.minecraft.server.v1_14_R1.NBTTagList"));
        load(V1_14_R1.class, "NBTBase:NBTTagLong", new NBTTagLong.NBTTagLongClass("net.minecraft.server.v1_14_R1.NBTTagLong"));
        load(V1_14_R1.class, "NBTBase:NBTTagShort", new NBTTagShort.NBTTagShortClass("net.minecraft.server.v1_14_R1.NBTTagShort"));
        load(V1_14_R1.class, "NBTBase:NBTTagString", new NBTTagString.NBTTagStringClass("net.minecraft.server.v1_14_R1.NBTTagString"));
        //

        // Packets
        load(V1_14_R1.class, "Packet", new Packet.PacketClass("net.minecraft.server.v1_14_R1.Packet"));
        load(V1_14_R1.class, "PacketPlayOutSpawnEntity", new EntitySpawnPacket.EntitySpawnPacketClass("net.minecraft.server.v1_14_R1.PacketPlayOutSpawnEntity"));
        load(V1_14_R1.class, "PacketPlayOutEntityDestroy", new EntityDestroyPacket.EntityDestroyPacketClass("net.minecraft.server.v1_14_R1.PacketPlayOutEntityDestroy"));
        load(V1_14_R1.class, "PacketPlayOutAnimation", new EntityAnimationPacket.EntityAnimationPacketClass("net.minecraft.server.v1_14_R1.PacketPlayOutAnimation"));
        load(V1_14_R1.class, "PacketPlayOutSpawnEntityLiving", new EntityLivingSpawnPacket.EntityLivingSpawnPacketClass("net.minecraft.server.v1_14_R1.PacketPlayOutSpawnEntityLiving"));
        load(V1_14_R1.class, "PacketPlayOutEntityMetadata", new EntityMetadataPacket.EntityMetadataPacketClass("net.minecraft.server.v1_14_R1.PacketPlayOutEntityMetadata"));
        load(V1_14_R1.class, "PacketPlayOutNamedEntitySpawn", new EntityNamedSpawnPacket.EntityNamedSpawnPacketClass("net.minecraft.server.v1_14_R1.PacketPlayOutNamedEntitySpawn"));
        load(V1_14_R1.class, "PacketPlayOutPlayerInfo", new PlayerInfoPacket.PlayerInfoPacketClass("net.minecraft.server.v1_14_R1.PacketPlayOutPlayerInfo"));
        load(V1_14_R1.class, "PacketPlayOutPlayerInfo:EnumPlayerInfoAction", new EnumPlayerInfoActionEnum.EnumPlayerInfoActionClass("net.minecraft.server.v1_14_R1.PacketPlayOutPlayerInfo$EnumPlayerInfoAction"));
        load(V1_14_R1.class, "PacketPlayOutScoreboardTeam", new ScoreboardTeamPacket.ScoreboardTeamPacketClass("net.minecraft.server.v1_14_R1.PacketPlayOutScoreboardTeam"));
        load(V1_14_R1.class, "PacketPlayOutEntityEquipment", new EntityEquipmentPacket.EntityEquipmentPacketClass("net.minecraft.server.v1_14_R1.PacketPlayOutEntityEquipment"));
        load(V1_14_R1.class, "PacketPlayOutEntityTeleport", new EntityTeleportPacket.EntityTeleportPacketClass("net.minecraft.server.v1_14_R1.PacketPlayOutEntityTeleport"));
        load(V1_14_R1.class, "PacketPlayOutEntityHeadRotation", new EntityHeadRotationPacket.EntityHeadRotationPacketClass("net.minecraft.server.v1_14_R1.PacketPlayOutEntityHeadRotation"));
        load(V1_14_R1.class, "PacketPlayOutEntityLook", new EntityLookPacket.EntityLookPacketClass("net.minecraft.server.v1_14_R1.PacketPlayOutEntity$PacketPlayOutEntityLook"));

        load(V1_14_R1.class, "PacketPlayInUseEntity", new EntityUseInPacket.EntityUseInPacketClass("net.minecraft.server.v1_14_R1.PacketPlayInUseEntity"));
        load(V1_14_R1.class, "PacketPlayInUseEntity:EnumEntityUseAction", new EntityUseInPacket.ActionEnum.ActionClass("net.minecraft.server.v1_14_R1.PacketPlayInUseEntity$EnumEntityUseAction"));
        //

        // Server
        load(V1_14_R1.class, "MinecraftServer", new MinecraftServer.MinecraftServerClass("net.minecraft.server.v1_14_R1.MinecraftServer"));
        load(V1_14_R1.class, "WorldServer", new WorldServer.WorldServerClass("net.minecraft.server.v1_14_R1.WorldServer"));
        load(V1_14_R1.class, "CraftServer", new CraftServer.CraftServerClass("org.bukkit.craftbukkit.v1_14_R1.CraftServer"));
        //

        // Entity
        load(V1_14_R1.class, "EntityTypes", new EntityTypes.EntityTypesClass("net.minecraft.server.v1_14_R1.EntityTypes"));

        load(V1_14_R1.class, "Entity", new Entity.EntityClass("net.minecraft.server.v1_14_R1.Entity"));
        load(V1_14_R1.class, "EntityLiving", new EntityLiving.EntityLivingClass("net.minecraft.server.v1_14_R1.EntityLiving"));
        load(V1_14_R1.class, "Entity:Human", new Entity.EntityClass("net.minecraft.server.v1_14_R1.EntityHuman"));
        load(V1_14_R1.class, "CraftPlayer", new CraftPlayer.CraftPlayerClass("org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer"));
        load(V1_14_R1.class, "EntityPlayer", new EntityPlayer.EntityPlayerClass("net.minecraft.server.v1_14_R1.EntityPlayer"));

        load(V1_14_R1.class, "Entity:ArmorStand", new ArmorStand.ArmorStandClass("net.minecraft.server.v1_14_R1.EntityArmorStand"));
        load(V1_14_R1.class, "Entity:Pig", new Pig.PigClass("net.minecraft.server.v1_14_R1.EntityPig"));
        load(V1_14_R1.class, "Entity:Cow", new Cow.CowClass("net.minecraft.server.v1_14_R1.EntityCow"));
        load(V1_14_R1.class, "Entity:Ocelot", new Ocelot.OcelotClass("net.minecraft.server.v1_14_R1.EntityOcelot"));
        load(V1_14_R1.class, "Entity:Bat", new Bat.BatClass("net.minecraft.server.v1_14_R1.EntityBat"));
        load(V1_14_R1.class, "Entity:Egg", new Egg.EggClass("net.minecraft.server.v1_14_R1.EntityEgg"));
        load(V1_14_R1.class, "Entity:Chicken", new Chicken.ChickenClass("net.minecraft.server.v1_14_R1.EntityChicken"));
        load(V1_14_R1.class, "Entity:Horse", new AbstractHorse.AbstractHorseClass("net.minecraft.server.v1_14_R1.EntityHorse"));
        load(V1_14_R1.class, "Entity:IronGolem", new IronGolem.IronGolemClass("net.minecraft.server.v1_14_R1.EntityIronGolem"));
        load(V1_14_R1.class, "Entity:Rabbit", new Rabbit.RabbitClass("net.minecraft.server.v1_14_R1.EntityRabbit"));
        load(V1_14_R1.class, "Entity:Sheep", new Sheep.SheepClass("net.minecraft.server.v1_14_R1.EntitySheep"));
        load(V1_14_R1.class, "Entity:Snowman", new Snowman.SnowmanClass("net.minecraft.server.v1_14_R1.EntitySnowman"));
        load(V1_14_R1.class, "Entity:Squid", new Squid.SquidClass("net.minecraft.server.v1_14_R1.EntitySquid"));
        load(V1_14_R1.class, "Entity:Wolf", new Wolf.WolfClass("net.minecraft.server.v1_14_R1.EntityWolf"));
        load(V1_14_R1.class, "Entity:ItemFrame", new ItemFrame.ItemFrameClass("net.minecraft.server.v1_14_R1.EntityItemFrame"));
        load(V1_14_R1.class, "Entity:LeashKnot", new LeashKnot.LeashKnotClass("net.minecraft.server.v1_14_R1.EntityLeash"));
        load(V1_14_R1.class, "Entity:FallingBlock", new FallingBlock.FallingBlockClass("net.minecraft.server.v1_14_R1.EntityFallingBlock"));
        load(V1_14_R1.class, "Entity:Item", new Item.ItemClass("net.minecraft.server.v1_14_R1.EntityItem"));
        load(V1_14_R1.class, "Entity:EnderDragon", new EnderDragon.EnderDragonClass("net.minecraft.server.v1_14_R1.EntityEnderDragon"));
        load(V1_14_R1.class, "Entity:EnderSignal", new EnderSignal.EnderSignalClass("net.minecraft.server.v1_14_R1.EntityEnderSignal"));
        load(V1_14_R1.class, "Entity:Wither", new Wither.WitherClass("net.minecraft.server.v1_14_R1.EntityWither"));
        load(V1_14_R1.class, "Entity:WitherSkull", new WitherSkull.WitherSkullClass("net.minecraft.server.v1_14_R1.EntityWitherSkull"));
        load(V1_14_R1.class, "Entity:Blaze", new Blaze.BlazeClass("net.minecraft.server.v1_14_R1.EntityBlaze"));
        load(V1_14_R1.class, "Entity:Creeper", new Creeper.CreeperClass("net.minecraft.server.v1_14_R1.EntityCreeper"));
        load(V1_14_R1.class, "Entity:Enderman", new Enderman.EndermanClass("net.minecraft.server.v1_14_R1.EntityEnderman"));
        load(V1_14_R1.class, "Entity:Ghast", new Ghast.GhastClass("net.minecraft.server.v1_14_R1.EntityGhast"));
        load(V1_14_R1.class, "Entity:Guardian", new Guardian.GuardianClass("net.minecraft.server.v1_14_R1.EntityGuardian"));
        load(V1_14_R1.class, "Entity:Silverfish", new Silverfish.SilverfishClass("net.minecraft.server.v1_14_R1.EntitySilverfish"));
        load(V1_14_R1.class, "Entity:Skeleton", new Skeleton.SkeletonClass("net.minecraft.server.v1_14_R1.EntitySkeleton"));
        load(V1_14_R1.class, "Entity:Slime", new Slime.SlimeClass("net.minecraft.server.v1_14_R1.EntitySlime"));
        load(V1_14_R1.class, "Entity:Spider", new Spider.SpiderClass("net.minecraft.server.v1_14_R1.EntitySpider"));
        load(V1_14_R1.class, "Entity:Witch", new Witch.WitchClass("net.minecraft.server.v1_14_R1.EntityWitch"));
        load(V1_14_R1.class, "Entity:Zombie", new Zombie.ZombieClass("net.minecraft.server.v1_14_R1.EntityZombie"));
        load(V1_14_R1.class, "Entity:Villager", new Villager.VillagerClass("net.minecraft.server.v1_14_R1.EntityVillager"));
        load(V1_14_R1.class, "Entity:Shulker", new Shulker.ShulkerClass("net.minecraft.server.v1_14_R1.EntityShulker"));
        load(V1_14_R1.class, "Entity:PolarBear", new PolarBear.PolarBearClass("net.minecraft.server.v1_14_R1.EntityPolarBear"));
        load(V1_14_R1.class, "Entity:Boat", new Boat.BoatClass("net.minecraft.server.v1_14_R1.EntityBoat"));
        load(V1_14_R1.class, "Entity:CaveSpider", new CaveSpider.CaveSpiderClass("net.minecraft.server.v1_14_R1.EntityCaveSpider"));

        load(V1_14_R1.class, "Entity:Ageable", new AgeableEntityLiving.AgeableEntityLivingClass("net.minecraft.server.v1_14_R1.EntityAgeable"));
        load(V1_14_R1.class, "Entity:Tameable", new TameableEntityLiving.TameableEntityLivingClass("net.minecraft.server.v1_14_R1.EntityTameableAnimal"));
        // EntityPlayer
        load(V1_14_R1.class, "GameProfile", new GameProfile.GameProfileClass("com.mojang.authlib.GameProfile"));
        load(V1_14_R1.class, "PropertyMap", new PropertyMap.PropertyMapClass("com.mojang.authlib.properties.PropertyMap"));
        load(V1_14_R1.class, "Property", new Property.PropertyClass("com.mojang.authlib.properties.Property"));
        //

        // Managers
        load(V1_14_R1.class, "PlayerInteractManager", new PlayerInteractManager.PlayerInteractManagerClass("net.minecraft.server.v1_14_R1.PlayerInteractManager"));
        //

        // DataWatcher
        load(V1_14_R1.class, "DataWatcher", new DataWatcher.DataWatcherClass("net.minecraft.server.v1_14_R1.DataWatcher"));
        load(V1_14_R1.class, "DataWatcherObject", new DataWatcherObject.DataWatcherObjectClass("net.minecraft.server.v1_14_R1.DataWatcherObject"));
        load(V1_14_R1.class, "DataWatcher:Item", new DataWatcherItem.DataWatcherItemClass("net.minecraft.server.v1_14_R1.DataWatcher$Item"));
        //

        // Scoreboard
        load(V1_14_R1.class, "CraftScoreboard", new CraftScoreboard.CraftScoreboardClass("org.bukkit.craftbukkit.v1_14_R1.scoreboard.CraftScoreboard"));
        load(V1_14_R1.class, "Scoreboard", new Scoreboard.ScoreboardClass("net.minecraft.server.v1_14_R1.Scoreboard"));

        load(V1_14_R1.class, "ScoreboardTeam", new ScoreboardTeam.ScoreboardTeamClass("net.minecraft.server.v1_14_R1.ScoreboardTeam"));
        load(V1_14_R1.class, "ScoreboardTeam:EnumTeamPush", new EnumTeamPushEnum.EnumTeamPushClass("net.minecraft.server.v1_14_R1.ScoreboardTeamBase$EnumTeamPush"));

        load(V1_14_R1.class, "ScoreboardTeamBase:EnumNameTagVisibility", new EnumNameTagVisibilityEnum.EnumNameTagVisibilityClass("net.minecraft.server.v1_14_R1.ScoreboardTeamBase$EnumNameTagVisibility"));
        //

        // Others
        load(V1_14_R1.class, "PlayerConnection", new PlayerConnection.PlayerConnectionClass("net.minecraft.server.v1_14_R1.PlayerConnection"));
        load(V1_14_R1.class, "NetworkManager", new NetworkManager.NetworkManagerClass("net.minecraft.server.v1_14_R1.NetworkManager"));

        load(V1_14_R1.class, "EnumChatFormat", new EnumChatFormatEnum.EnumChatFormatClass("net.minecraft.server.v1_14_R1.EnumChatFormat"));
        load(V1_14_R1.class, "EnumColor", new EnumColorEnum.EnumColorClass("net.minecraft.server.v1_14_R1.EnumColor"));
        load(V1_14_R1.class, "EnumItemSlot", new EnumItemSlotEnum.EnumItemSlotClass("net.minecraft.server.v1_14_R1.EnumItemSlot"));
        load(V1_14_R1.class, "EnumDirection", new EnumDirectionEnum.EnumDirectionClass("net.minecraft.server.v1_14_R1.EnumDirection"));
        //

        // Chat
        load(V1_14_R1.class, "IChatBaseComponent", new IChatBaseComponent.IChatBaseComponentClass("net.minecraft.server.v1_14_R1.IChatBaseComponent"));
        load(V1_14_R1.class, "ChatSerializer", new IChatBaseComponent.ChatSerializerClass("net.minecraft.server.v1_14_R1.IChatBaseComponent$ChatSerializer"));
        //

        // Objects
        load(V1_14_R1.class, "CraftWorld", new CraftWorld.CraftWorldClass("org.bukkit.craftbukkit.v1_14_R1.CraftWorld"));
        load(V1_14_R1.class, "World", new World.WorldClass("net.minecraft.server.v1_14_R1.World"));
        load(V1_14_R1.class, "Vector3f", new Vector3f.Vector3fClass("net.minecraft.server.v1_14_R1.Vector3f"));
        load(V1_14_R1.class, "Vec3D", new Vec3D.Vec3DClass("net.minecraft.server.v1_14_R1.Vec3D"));
        load(V1_14_R1.class, "BlockPosition", new BlockPosition.BlockPositionClass("net.minecraft.server.v1_14_R1.BlockPosition"));
        load(V1_14_R1.class, "CraftBlock", new CraftBlock.CraftBlockClass("org.bukkit.craftbukkit.v1_14_R1.block.CraftBlock"));
        load(V1_14_R1.class, "IBlockData", new IBlockData.IBlockDataClass("net.minecraft.server.v1_14_R1.IBlockData"));
        load(V1_14_R1.class, "Block", new Block.BlockClass("net.minecraft.server.v1_14_R1.Block"));
        load(V1_14_R1.class, "CraftMagicNumbers", new ClassExecutor("org.bukkit.craftbukkit.v1_14_R1.util.CraftMagicNumbers"));

        load(V1_14_R1.class, "InventorySubcontainer", new InventorySubcontainer.InventorySubcontainerClass("net.minecraft.server.v1_14_R1.InventorySubcontainer"));
        //

        // Items
        load(V1_14_R1.class, "ItemStack", new ItemStack.ItemStackClass("net.minecraft.server.v1_14_R1.ItemStack"));
        load(V1_14_R1.class, "CraftItemStack", new CraftItemStack.CraftItemStackClass("org.bukkit.craftbukkit.v1_14_R1.inventory.CraftItemStack"));
        //

        // Entity horse
        load(V1_14_R1.class, "Entity:Horse:Abstract", new AbstractHorse.AbstractHorseClass("net.minecraft.server.v1_14_R1.EntityHorseAbstract"));
        load(V1_14_R1.class, "Entity:Horse:Abstract:Chested", new AbstractChestedHorse.AbstractChestedHorseClass("net.minecraft.server.v1_14_R1.EntityHorseChestedAbstract"));
        load(V1_14_R1.class, "Entity:Horse:Donkey", new HorseDonkey.HorseDonkeyClass("net.minecraft.server.v1_14_R1.EntityHorseDonkey"));
        load(V1_14_R1.class, "Entity:Horse:Mule", new HorseMule.HorseMuleClass("net.minecraft.server.v1_14_R1.EntityHorseMule"));
        load(V1_14_R1.class, "Entity:Horse:Skeleton", new HorseSkeleton.HorseSkeletonClass("net.minecraft.server.v1_14_R1.EntityHorseSkeleton"));
        load(V1_14_R1.class, "Entity:Horse:Zombie", new HorseZombie.HorseZombieClass("net.minecraft.server.v1_14_R1.EntityHorseZombie"));
        // Entity skeleton
        load(V1_14_R1.class, "Entity:Skeleton:Wither", new SkeletonWither.SkeletonWitherClass("net.minecraft.server.v1_14_R1.EntitySkeletonWither"));
        load(V1_14_R1.class, "Entity:Skeleton:Stray", new SkeletonStray.SkeletonStrayClass("net.minecraft.server.v1_14_R1.EntitySkeletonStray"));
        // Entity zombie
        load(V1_14_R1.class, "Entity:Zombie:Villager", new ZombieVillager.ZombieVillagerClass("net.minecraft.server.v1_14_R1.EntityZombieVillager"));
        load(V1_14_R1.class, "Entity:Zombie:Husk", new ZombieHusk.ZombieHuskClass("net.minecraft.server.v1_14_R1.EntityZombieHusk"));
        load(V1_14_R1.class, "Entity:Zombie:Drowned", new ZombieDrowned.ZombieDrownedClass("net.minecraft.server.v1_14_R1.EntityDrowned"));
        // Entity vindicator
        load(V1_14_R1.class, "Entity:Vindicator", new Vindicator.VindicatorClass("net.minecraft.server.v1_14_R1.EntityVindicator"));
        // Entity evoker
        load(V1_14_R1.class, "Entity:Evoker", new Evoker.EvokerClass("net.minecraft.server.v1_14_R1.EntityEvoker"));
        // Entity vex
        load(V1_14_R1.class, "Entity:Vex", new Vex.VexClass("net.minecraft.server.v1_14_R1.EntityVex"));
        // Entity llama
        load(V1_14_R1.class, "Entity:Llama", new Llama.LlamaClass("net.minecraft.server.v1_14_R1.EntityLlama"));
        // Entity illager illusioner
        load(V1_14_R1.class, "Entity:Illusioner", new Illusioner.IllusionerClass("net.minecraft.server.v1_14_R1.EntityIllagerIllusioner"));
        // Entity illager wizard
        load(V1_14_R1.class, "Entity:IllagerWizard", new IllagerWizard.IllagerWizardClass("net.minecraft.server.v1_14_R1.EntityIllagerWizard"));
        load(V1_14_R1.class, "Entity:IllagerWizard:Spell", new EnumSpellEnum.EnumSpellClass("net.minecraft.server.v1_14_R1.EntityIllagerWizard$Spell"));
        // Entity parrot
        load(V1_14_R1.class, "Entity:Parrot", new Parrot.ParrotClass("net.minecraft.server.v1_14_R1.EntityParrot"));
        // Entity dolphin
        load(V1_14_R1.class, "Entity:Dolphin", new Dolphin.DolphinClass("net.minecraft.server.v1_14_R1.EntityDolphin"));
        // Entity fish
        load(V1_14_R1.class, "Entity:Fish", new Fish.FishClass("net.minecraft.server.v1_14_R1.EntityFish"));
        load(V1_14_R1.class, "Entity:Cod", new Cod.CodClass("net.minecraft.server.v1_14_R1.EntityCod"));
        load(V1_14_R1.class, "Entity:Salmon", new Salmon.SalmonClass("net.minecraft.server.v1_14_R1.EntitySalmon"));
        load(V1_14_R1.class, "Entity:PufferFish", new Pufferfish.PufferfishClass("net.minecraft.server.v1_14_R1.EntityPufferFish"));
        load(V1_14_R1.class, "Entity:TropicalFish", new Tropicalfish.TropicalfishClass("net.minecraft.server.v1_14_R1.EntityTropicalFish"));
        // Entity phantom
        load(V1_14_R1.class, "Entity:Phantom", new Phantom.PhantomClass("net.minecraft.server.v1_14_R1.EntityPhantom"));
        // Entity turtle
        load(V1_14_R1.class, "Entity:Turtle", new Turtle.TurtleClass("net.minecraft.server.v1_14_R1.EntityTurtle"));
        // Entity cat
        load(V1_14_R1.class, "Entity:Cat", new Cat.CatClass("net.minecraft.server.v1_14_R1.EntityCat"));
        // Entity villager
        load(V1_14_R1.class, "VillagerData", new VillagerData.VillagerDataClass("net.minecraft.server.v1_14_R1.VillagerData"));
        load(V1_14_R1.class, "VillagerProfession", new VillagerProfessionExec.VillagerProfessionExecClass("net.minecraft.server.v1_14_R1.VillagerProfession"));
        load(V1_14_R1.class, "VillagerType", new VillagerType.VillagerTypeClass("net.minecraft.server.v1_14_R1.VillagerType"));
        //
    }

    @Override
    public void loadFields() {
        super.loadFields();

        load(V1_14_R1.class, "Metadata:Cat:Type", new FieldExecutor(getClassExec("Entity:Cat"), getClassExec("DataWatcherObject"), "bF", "Gets the cat's variant DataWatcherObject"));
        load(V1_14_R1.class, "Metadata:Villager:Data", new FieldExecutor(getClassExec("Entity:Villager"), getClassExec("DataWatcherObject"), "bC", "Gets the villager's data DataWatcherObject"));
        load(V1_14_R1.class, "Metadata:ZombieVillager:Data", new FieldExecutor(getClassExec("Entity:Zombie:Villager"), getClassExec("DataWatcherObject"), "c", "Gets the zombie villager's data DataWatcherObject"));

        load(V1_14_R1.class, "VillagerProfession:Name", new FieldExecutor(getClassExec("VillagerProfession"), ClassExecutor.STRING, "p", "Gets the name of VillagerProfession"));

        load(V1_14_R1.class, "VillagerType:Desert", new FieldExecutor(getClassExec("VillagerType"), getClassExec("VillagerType"), "a", "Gets the villager's desert type"));
        load(V1_14_R1.class, "VillagerType:Jungle", new FieldExecutor(getClassExec("VillagerType"), getClassExec("VillagerType"), "b", "Gets the villager's jungle type"));
        load(V1_14_R1.class, "VillagerType:Plains", new FieldExecutor(getClassExec("VillagerType"), getClassExec("VillagerType"), "c", "Gets the villager's plains type"));
        load(V1_14_R1.class, "VillagerType:Savanna", new FieldExecutor(getClassExec("VillagerType"), getClassExec("VillagerType"), "d", "Gets the villager's savanna type"));
        load(V1_14_R1.class, "VillagerType:Snow", new FieldExecutor(getClassExec("VillagerType"), getClassExec("VillagerType"), "e", "Gets the villager's snow type"));
        load(V1_14_R1.class, "VillagerType:Swamp", new FieldExecutor(getClassExec("VillagerType"), getClassExec("VillagerType"), "f", "Gets the villager's swamp type"));
        load(V1_14_R1.class, "VillagerType:Taiga", new FieldExecutor(getClassExec("VillagerType"), getClassExec("VillagerType"), "g", "Gets the villager's taiga type"));

        load(V1_14_R1.class, "Entity:Horse:Abstract:horseInventory", new FieldExecutor(getClassExec("Entity:Horse:Abstract"), getClassExec("InventorySubcontainer"), "inventoryChest", "Gets the horse's inventoryChest"));

        load(V1_14_R1.class, "VillagerProfession:PROFESSIONS:NONE", new FieldExecutor(getClassExec("VillagerProfession"), getClassExec("VillagerProfession"), "NONE", "Gets the NONE villager profession"));
        load(V1_14_R1.class, "VillagerProfession:PROFESSIONS:ARMORER", new FieldExecutor(getClassExec("VillagerProfession"), getClassExec("VillagerProfession"), "ARMORER", "Gets the ARMORER villager profession"));
        load(V1_14_R1.class, "VillagerProfession:PROFESSIONS:BUTCHER", new FieldExecutor(getClassExec("VillagerProfession"), getClassExec("VillagerProfession"), "BUTCHER", "Gets the BUTCHER villager profession"));
        load(V1_14_R1.class, "VillagerProfession:PROFESSIONS:CARTOGRAPHER", new FieldExecutor(getClassExec("VillagerProfession"), getClassExec("VillagerProfession"), "CARTOGRAPHER", "Gets the CARTOGRAPHER villager profession"));
        load(V1_14_R1.class, "VillagerProfession:PROFESSIONS:CLERIC", new FieldExecutor(getClassExec("VillagerProfession"), getClassExec("VillagerProfession"), "CLERIC", "Gets the CLERIC villager profession"));
        load(V1_14_R1.class, "VillagerProfession:PROFESSIONS:FARMER", new FieldExecutor(getClassExec("VillagerProfession"), getClassExec("VillagerProfession"), "FARMER", "Gets the FARMER villager profession"));
        load(V1_14_R1.class, "VillagerProfession:PROFESSIONS:FISHERMAN", new FieldExecutor(getClassExec("VillagerProfession"), getClassExec("VillagerProfession"), "FISHERMAN", "Gets the FISHERMAN villager profession"));
        load(V1_14_R1.class, "VillagerProfession:PROFESSIONS:FLETCHER", new FieldExecutor(getClassExec("VillagerProfession"), getClassExec("VillagerProfession"), "FLETCHER", "Gets the FLETCHER villager profession"));
        load(V1_14_R1.class, "VillagerProfession:PROFESSIONS:LEATHERWORKER", new FieldExecutor(getClassExec("VillagerProfession"), getClassExec("VillagerProfession"), "LEATHERWORKER", "Gets the LEATHERWORKER villager profession"));
        load(V1_14_R1.class, "VillagerProfession:PROFESSIONS:LIBRARIAN", new FieldExecutor(getClassExec("VillagerProfession"), getClassExec("VillagerProfession"), "LIBRARIAN", "Gets the LIBRARIAN villager profession"));
        load(V1_14_R1.class, "VillagerProfession:PROFESSIONS:MASON", new FieldExecutor(getClassExec("VillagerProfession"), getClassExec("VillagerProfession"), "MASON", "Gets the MASON villager profession"));
        load(V1_14_R1.class, "VillagerProfession:PROFESSIONS:NITWIT", new FieldExecutor(getClassExec("VillagerProfession"), getClassExec("VillagerProfession"), "NITWIT", "Gets the NITWIT villager profession"));
        load(V1_14_R1.class, "VillagerProfession:PROFESSIONS:SHEPHERD", new FieldExecutor(getClassExec("VillagerProfession"), getClassExec("VillagerProfession"), "SHEPHERD", "Gets the SHEPHERD villager profession"));
        load(V1_14_R1.class, "VillagerProfession:PROFESSIONS:TOOLSMITH", new FieldExecutor(getClassExec("VillagerProfession"), getClassExec("VillagerProfession"), "TOOLSMITH", "Gets the TOOLSMITH villager profession"));
        load(V1_14_R1.class, "VillagerProfession:PROFESSIONS:WEAPONSMITH", new FieldExecutor(getClassExec("VillagerProfession"), getClassExec("VillagerProfession"), "WEAPONSMITH", "Gets the WEAPONSMITH villager profession"));
    }

    @Override
    public void loadMethods() {
        super.loadMethods();

        load(V1_14_R1.class, "Vec3D:getX", new MethodExecutor(getClassExec("Vec3D"), ClassExecutor.DOUBLE, "getX", "Gets the X align of a Vec3D"));
        load(V1_14_R1.class, "Vec3D:getY", new MethodExecutor(getClassExec("Vec3D"), ClassExecutor.DOUBLE, "getY", "Gets the Y align of a Vec3D"));
        load(V1_14_R1.class, "Vec3D:getZ", new MethodExecutor(getClassExec("Vec3D"), ClassExecutor.DOUBLE, "getZ", "Gets the Z align of a Vec3D"));

        load(V1_14_R1.class, "VillagerData:getType", new MethodExecutor(getClassExec("VillagerData"), getClassExec("VillagerType"), "getType", "Gets the type of a villager data"));
        load(V1_14_R1.class, "VillagerData:getProfession", new MethodExecutor(getClassExec("VillagerData"), getClassExec("VillagerProfession"), "getProfession", "Gets the profession of a villager data"));
        load(V1_14_R1.class, "VillagerData:getLevel", new MethodExecutor(getClassExec("VillagerData"), ClassExecutor.INT, "getLevel", "Gets the level of a villager data"));

        load(V1_14_R1.class, "InventorySubcontainer:getItem", new MethodExecutor(getClassExec("InventorySubcontainer"), getClassExec("ItemStack"), "getItem", "Gets a item from a InventorySubcontainer", ClassExecutor.INT));
        load(V1_14_R1.class, "InventorySubcontainer:setItem", new MethodExecutor(getClassExec("InventorySubcontainer"), ClassExecutor.VOID, "setItem", "Sets a item of slot at a InventorySubcontainer", ClassExecutor.INT, getClassExec("ItemStack")));
    }

    @Override
    public @NotNull Map<String, String> getTexts() {
        if (!super.getTexts().containsKey("EntityTypes:ARMOR_STAND")) {
            super.getTexts().put("EntityTypes:ARMOR_STAND", "ARMOR_STAND");
            super.getTexts().put("EntityTypes:BAT", "BAT");
            super.getTexts().put("EntityTypes:EGG", "EGG");
            super.getTexts().put("EntityTypes:BLAZE", "BLAZE");
            super.getTexts().put("EntityTypes:BOAT", "BOAT");
            super.getTexts().put("EntityTypes:CAT", "CAT");
            super.getTexts().put("EntityTypes:CAVE_SPIDER", "CAVE_SPIDER");
            super.getTexts().put("EntityTypes:CHICKEN", "CHICKEN");
            super.getTexts().put("EntityTypes:COD", "COD");
            super.getTexts().put("EntityTypes:COW", "COW");
            super.getTexts().put("EntityTypes:CREEPER", "CREEPER");
            super.getTexts().put("EntityTypes:DONKEY", "DONKEY");
            super.getTexts().put("EntityTypes:DOLPHIN", "DOLPHIN");
            super.getTexts().put("EntityTypes:DRAGON_FIREBALL", "DRAGON_FIREBALL");
            super.getTexts().put("EntityTypes:DROWNED", "DROWNED");
            super.getTexts().put("EntityTypes:ELDER_GUARDIAN", "ELDER_GUARDIAN");
            super.getTexts().put("EntityTypes:END_CRYSTAL", "END_CRYSTAL");
            super.getTexts().put("EntityTypes:ENDER_DRAGON", "ENDER_DRAGON");
            super.getTexts().put("EntityTypes:ENDERMAN", "ENDERMAN");
            super.getTexts().put("EntityTypes:ENDERMITE", "ENDERMITE");
            super.getTexts().put("EntityTypes:EVOKER_FANGS", "EVOKER_FANGS");
            super.getTexts().put("EntityTypes:EVOKER", "EVOKER");
            super.getTexts().put("EntityTypes:EYE_OF_ENDER", "EYE_OF_ENDER");
            super.getTexts().put("EntityTypes:FALLING_BLOCK", "FALLING_BLOCK");
            super.getTexts().put("EntityTypes:FOX", "FOX");
            super.getTexts().put("EntityTypes:GHAST", "GHAST");
            super.getTexts().put("EntityTypes:GIANT", "GIANT");
            super.getTexts().put("EntityTypes:GUARDIAN", "GUARDIAN");
            super.getTexts().put("EntityTypes:HORSE", "HORSE");
            super.getTexts().put("EntityTypes:HUSK", "HUSK");
            super.getTexts().put("EntityTypes:ILLUSIONER", "ILLUSIONER");
            super.getTexts().put("EntityTypes:ITEM", "ITEM");
            super.getTexts().put("EntityTypes:ITEM_FRAME", "ITEM_FRAME");
            super.getTexts().put("EntityTypes:FIREBALL", "FIREBALL");
            super.getTexts().put("EntityTypes:LEASH_KNOT", "LEASH_KNOT");
            super.getTexts().put("EntityTypes:LLAMA", "LLAMA");
            super.getTexts().put("EntityTypes:LLAMA_SPIT", "LLAMA_SPIT");
            super.getTexts().put("EntityTypes:MAGMA_CUBE", "MAGMA_CUBE");
            super.getTexts().put("EntityTypes:MINECART", "MINECART");
            super.getTexts().put("EntityTypes:CHEST_MINECART", "CHEST_MINECART");
            super.getTexts().put("EntityTypes:COMMAND_BLOCK_MINECART", "COMMAND_BLOCK_MINECART");
            super.getTexts().put("EntityTypes:FURNACE_MINECART", "FURNACE_MINECART");
            super.getTexts().put("EntityTypes:HOPPER_MINECART", "HOPPER_MINECART");
            super.getTexts().put("EntityTypes:SPAWNER_MINECART", "SPAWNER_MINECART");
            super.getTexts().put("EntityTypes:TNT_MINECART", "TNT_MINECART");
            super.getTexts().put("EntityTypes:MULE", "MULE");
            super.getTexts().put("EntityTypes:MOOSHROOM", "MOOSHROOM");
            super.getTexts().put("EntityTypes:OCELOT", "OCELOT");
            super.getTexts().put("EntityTypes:PAINTING", "PAINTING");
            super.getTexts().put("EntityTypes:PANDA", "PANDA");
            super.getTexts().put("EntityTypes:PARROT", "PARROT");
            super.getTexts().put("EntityTypes:PIG", "PIG");
            super.getTexts().put("EntityTypes:PUFFERFISH", "PUFFERFISH");
            super.getTexts().put("EntityTypes:ZOMBIE_PIGMAN", "ZOMBIE_PIGMAN");
            super.getTexts().put("EntityTypes:POLAR_BEAR", "POLAR_BEAR");
            super.getTexts().put("EntityTypes:TNT", "TNT");
            super.getTexts().put("EntityTypes:RABBIT", "RABBIT");
            super.getTexts().put("EntityTypes:SALMON", "SALMON");
            super.getTexts().put("EntityTypes:SHEEP", "SHEEP");
            super.getTexts().put("EntityTypes:SHULKER", "SHULKER");
            super.getTexts().put("EntityTypes:SHULKER_BULLET", "SHULKER_BULLET");
            super.getTexts().put("EntityTypes:SILVERFISH", "SILVERFISH");
            super.getTexts().put("EntityTypes:SKELETON", "SKELETON");
            super.getTexts().put("EntityTypes:SKELETON_HORSE", "SKELETON_HORSE");
            super.getTexts().put("EntityTypes:SLIME", "SLIME");
            super.getTexts().put("EntityTypes:SMALL_FIREBALL", "SMALL_FIREBALL");
            super.getTexts().put("EntityTypes:SNOW_GOLEM", "SNOW_GOLEM");
            super.getTexts().put("EntityTypes:SNOWBALL", "SNOWBALL");
            super.getTexts().put("EntityTypes:SPIDER", "SPIDER");
            super.getTexts().put("EntityTypes:SQUID", "SQUID");
            super.getTexts().put("EntityTypes:STRAY", "STRAY");
            super.getTexts().put("EntityTypes:TRADER_LLAMA", "TRADER_LLAMA");
            super.getTexts().put("EntityTypes:TROPICAL_FISH", "TROPICAL_FISH");
            super.getTexts().put("EntityTypes:TURTLE", "TURTLE");
            super.getTexts().put("EntityTypes:ENDER_PEARL", "ENDER_PEARL");
            super.getTexts().put("EntityTypes:VEX", "VEX");
            super.getTexts().put("EntityTypes:VILLAGER", "VILLAGER");
            super.getTexts().put("EntityTypes:IRON_GOLEM", "IRON_GOLEM");
            super.getTexts().put("EntityTypes:VINDICATOR", "VINDICATOR");
            super.getTexts().put("EntityTypes:PILLAGER", "PILLAGER");
            super.getTexts().put("EntityTypes:WANDERING_TRADER", "WANDERING_TRADER");
            super.getTexts().put("EntityTypes:WITCH", "WITCH");
            super.getTexts().put("EntityTypes:WITHER", "WITHER");
            super.getTexts().put("EntityTypes:WITHER_SKELETON", "WITHER_SKELETON");
            super.getTexts().put("EntityTypes:WITHER_SKULL", "WITHER_SKULL");
            super.getTexts().put("EntityTypes:WOLF", "WOLF");
            super.getTexts().put("EntityTypes:ZOMBIE", "ZOMBIE");
            super.getTexts().put("EntityTypes:ZOMBIE_HORSE", "ZOMBIE_HORSE");
            super.getTexts().put("EntityTypes:ZOMBIE_VILLAGER", "ZOMBIE_VILLAGER");
            super.getTexts().put("EntityTypes:PHANTOM", "PHANTOM");
            super.getTexts().put("EntityTypes:RAVAGER", "RAVAGER");
        }

        return super.getTexts();
    }

    @Override
    public @NotNull Map<String, Object> getObjects() {
        Map<String, Object> map = super.getObjects();

        super.getObjects().put("Metadata:Player:SkinParts", 15);

        return map;
    }

    @Override
    public @NotNull String getName() {
        return "v1_14_R1";
    }

}
