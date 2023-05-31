package codes.laivy.npc.mappings.defaults.classes.entity;

import codes.laivy.npc.mappings.Version;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.fish.Cod;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.fish.Pufferfish;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.fish.Salmon;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.fish.Tropicalfish;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.illagers.Evoker;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.illagers.Illusioner;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.illagers.Vindicator;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.zombie.ZombieDrowned;
import codes.laivy.npc.mappings.defaults.classes.entity.player.EntityPlayer;
import codes.laivy.npc.mappings.defaults.classes.entity.vehicle.Boat;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import codes.laivy.npc.mappings.defaults.classes.datawatcher.DataWatcher;
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
import codes.laivy.npc.mappings.defaults.classes.enums.EntityPose;
import codes.laivy.npc.mappings.defaults.classes.java.BooleanObjExec;
import codes.laivy.npc.mappings.defaults.classes.others.location.World;
import codes.laivy.npc.mappings.versions.*;
import codes.laivy.npc.types.EntityNPC;
import codes.laivy.npc.types.NPC;
import codes.laivy.npc.types.list.ambient.BatNPC;
import codes.laivy.npc.types.list.ambient.EggNPC;
import codes.laivy.npc.types.list.animal.*;
import codes.laivy.npc.types.list.animal.fish.CodNPC;
import codes.laivy.npc.types.list.animal.fish.PufferfishNPC;
import codes.laivy.npc.types.list.animal.fish.SalmonNPC;
import codes.laivy.npc.types.list.animal.fish.TropicalfishNPC;
import codes.laivy.npc.types.list.animal.horse.*;
import codes.laivy.npc.types.list.boss.dragon.EnderDragonNPC;
import codes.laivy.npc.types.list.boss.dragon.EnderSignalNPC;
import codes.laivy.npc.types.list.boss.wither.WitherNPC;
import codes.laivy.npc.types.list.boss.wither.WitherSkullNPC;
import codes.laivy.npc.types.list.decoration.ArmorStandNPC;
import codes.laivy.npc.types.list.decoration.ItemFrameNPC;
import codes.laivy.npc.types.list.decoration.LeashKnotNPC;
import codes.laivy.npc.types.list.item.FallingBlockNPC;
import codes.laivy.npc.types.list.item.ItemNPC;
import codes.laivy.npc.types.list.monster.*;
import codes.laivy.npc.types.list.monster.illagers.EvokerNPC;
import codes.laivy.npc.types.list.monster.illagers.IllusionerNPC;
import codes.laivy.npc.types.list.monster.illagers.VindicatorNPC;
import codes.laivy.npc.types.list.monster.skeleton.SkeletonNPC;
import codes.laivy.npc.types.list.monster.skeleton.SkeletonStrayNPC;
import codes.laivy.npc.types.list.monster.skeleton.SkeletonWitherNPC;
import codes.laivy.npc.types.list.monster.zombie.ZombieDrownedNPC;
import codes.laivy.npc.types.list.monster.zombie.ZombieHuskNPC;
import codes.laivy.npc.types.list.monster.zombie.ZombieNPC;
import codes.laivy.npc.types.list.monster.zombie.ZombieVillagerNPC;
import codes.laivy.npc.types.list.npc.VillagerNPC;
import codes.laivy.npc.types.list.vehicle.BoatNPC;
import codes.laivy.npc.utils.ClassUtils;
import codes.laivy.npc.utils.ReflectionUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Entity extends ObjectExecutor {
    public Entity(@Nullable Object value) {
        super(value);
    }

    public int getId() {
        Object id = laivynpc().getVersion().getMethodExec("Entity:Entity:getId").invokeInstance(this);
        if (id == null) {
            throw new NullPointerException("Cannot get Entity ID because its null");
        }

        return (int) id;
    }
    public DataWatcher getDataWatcher() {
        Object dataWatcher = laivynpc().getVersion().getMethodExec("Entity:Entity:getDataWatcher").invokeInstance(this);
        return new DataWatcher(dataWatcher);
    }

    public @Nullable String getCustomName() {
        return laivynpc().getVersion().getEntityCustomName(this);
    }
    public void setCustomName(@Nullable String customName) {
        laivynpc().getVersion().setEntityCustomName(this, customName);
    }

    public boolean isGlowing() {
        if (ReflectionUtils.isCompatible(V1_9_R1.class)) {
            //noinspection DataFlowIssue
            return (boolean) laivynpc().getVersion().getMethodExec("Entity:isGlowing").invokeInstance(this);
        }
        throw new IllegalStateException("The glowing state of a entity is compatible only with 1.9+");
    }
    public void setGlowing(boolean flag) {
        if (ReflectionUtils.isCompatible(V1_9_R1.class)) {
            laivynpc().getVersion().getMethodExec("Entity:setGlowing").invokeInstance(this, new BooleanObjExec(flag));
        } else {
            throw new IllegalStateException("The glowing state of a entity is compatible only with 1.9+");
        }
    }

    public boolean isCustomNameVisible() {
        //noinspection ConstantConditions
        return (boolean) laivynpc().getVersion().getMethodExec("Entity:Entity:isCustomNameVisible").invokeInstance(this);
    }
    public void setCustomNameVisible(boolean customNameVisible) {
        laivynpc().getVersion().getMethodExec("Entity:Entity:setCustomNameVisible").invokeInstance(this, new BooleanObjExec(customNameVisible));
    }

    public @NotNull EntityPose getPose() {
        return laivynpc().getVersion().getPose(this);
    }
    public void setPose(@NotNull EntityPose pose) {
        if (pose.isPlayerPose() && !(this instanceof EntityPlayer)) {
            throw new UnsupportedOperationException("This pose is exclusive for players entities!");
        }
        laivynpc().getVersion().setPose(this, pose);
    }

    public @NotNull World getWorld() {
        return new World(laivynpc().getVersion().getFieldExec("Entity:world").invokeInstance(this));
    }

    public boolean isInvisible() {
        return laivynpc().getVersion().isEntityInvisible(this);
    }
    public void setInvisible(boolean invisible) {
        laivynpc().getVersion().setEntityInvisible(this, invisible);
    }

    /**
     * As know as 'absMoveTo' in the newer versions
     * @param location the new x, y, z, yaw and pitch
     */
    public void setLocation(@NotNull Location location) {
        laivynpc().getVersion().setEntityLocation(this, location);
    }

    public @NotNull Location getLocation() {
        return laivynpc().getVersion().getEntityLocation(this);
    }

    @Override
    public @NotNull EntityClass getClassExecutor() {
        return (EntityClass) laivynpc().getVersion().getClassExec("Entity");
    }

    public static class EntityClass extends ClassExecutor {
        public EntityClass(@NotNull String className) {
            super(className);
        }
    }

    public enum EntityType {
        ARMOR_STAND(ArmorStand.class, ArmorStandNPC.class, V1_8_R1.class),
        PIG(Pig.class, PigNPC.class, V1_8_R1.class),
        COW(Cow.class, CowNPC.class, V1_8_R1.class),

        OCELOT(Ocelot.class, OcelotNPC.class, V1_8_R1.class),
        CAT(Cat.class, CatNPC.class, V1_8_R1.class),

        BAT(Bat.class, BatNPC.class, V1_8_R1.class),
        EGG(Egg.class, EggNPC.class, V1_8_R1.class),
        CHICKEN(Chicken.class, ChickenNPC.class, V1_8_R1.class),

        HORSE(Horse.class, HorseNPC.class, V1_8_R1.class),
        HORSE_DONKEY(HorseDonkey.class, HorseDonkeyNPC.class, V1_8_R1.class),
        HORSE_MULE(HorseMule.class, HorseMuleNPC.class, V1_8_R1.class),
        HORSE_ZOMBIE(HorseZombie.class, HorseZombieNPC.class, V1_8_R1.class),
        HORSE_SKELETON(HorseSkeleton.class, HorseSkeletonNPC.class, V1_8_R1.class),
        LLAMA(Llama.class, LlamaNPC.class, V1_11_R1.class),

        IRON_GOLEM(IronGolem.class, IronGolemNPC.class, V1_8_R1.class),
        RABBIT(Rabbit.class, RabbitNPC.class, V1_8_R1.class),
        SHEEP(Sheep.class, SheepNPC.class, V1_8_R1.class),
        SNOWMAN(Snowman.class, SnowmanNPC.class, V1_8_R1.class),
        SQUID(Squid.class, SquidNPC.class, V1_8_R1.class),
        WOLF(Wolf.class, WolfNPC.class, V1_8_R1.class),
        ITEM_FRAME(ItemFrame.class, ItemFrameNPC.class, V1_8_R1.class),
        LEASH_KNOT(LeashKnot.class, LeashKnotNPC.class, V1_8_R1.class),
        FALLING_BLOCK(FallingBlock.class, FallingBlockNPC.class, V1_8_R1.class, Material.DIAMOND_BLOCK),
        ITEM(Item.class, ItemNPC.class, V1_8_R1.class),
        ENDER_DRAGON(EnderDragon.class, EnderDragonNPC.class, V1_8_R1.class),
        ENDER_SIGNAL(EnderSignal.class, EnderSignalNPC.class, V1_8_R1.class),
        WITHER(Wither.class, WitherNPC.class, V1_8_R1.class),
        WITHER_SKULL(WitherSkull.class, WitherSkullNPC.class, V1_8_R1.class),
        BLAZE(Blaze.class, BlazeNPC.class, V1_8_R1.class),
        CREEPER(Creeper.class, CreeperNPC.class, V1_8_R1.class),
        ENDERMAN(Enderman.class, EndermanNPC.class, V1_8_R1.class),
        GHAST(Ghast.class, GhastNPC.class, V1_8_R1.class),
        GUARDIAN(Guardian.class, GuardianNPC.class, V1_8_R1.class),
        SILVERFISH(Silverfish.class, SilverfishNPC.class, V1_8_R1.class),

        SKELETON(Skeleton.class, SkeletonNPC.class, V1_8_R1.class),
        SKELETON_WITHER(SkeletonWither.class, SkeletonWitherNPC.class, V1_8_R1.class),
        SKELETON_STRAY(SkeletonStray.class, SkeletonStrayNPC.class, V1_10_R1.class),

        SLIME(Slime.class, SlimeNPC.class, V1_8_R1.class),
        SPIDER(Spider.class, SpiderNPC.class, V1_8_R1.class),
        WITCH(Witch.class, WitchNPC.class, V1_8_R1.class),

        ZOMBIE(Zombie.class, ZombieNPC.class, V1_8_R1.class),
        ZOMBIE_HUSK(ZombieHusk.class, ZombieHuskNPC.class, V1_10_R1.class),
        ZOMBIE_VILLAGER(ZombieVillager.class, ZombieVillagerNPC.class, V1_8_R1.class),
        ZOMBIE_DROWNED(ZombieDrowned.class, ZombieDrownedNPC.class, V1_13_R1.class),

        VILLAGER(Villager.class, VillagerNPC.class, V1_8_R1.class),
        SHULKER(Shulker.class, ShulkerNPC.class, V1_9_R1.class),
        POLAR_BEAR(PolarBear.class, PolarBearNPC.class, V1_10_R1.class),

        VINDICATOR(Vindicator.class, VindicatorNPC.class, V1_11_R1.class),
        EVOKER(Evoker.class, EvokerNPC.class, V1_11_R1.class),
        VEX(Vex.class, VexNPC.class, V1_11_R1.class),

        ILLUSIONER(Illusioner.class, IllusionerNPC.class, V1_12_R1.class),
        PARROT(Parrot.class, ParrotNPC.class, V1_12_R1.class),
        DOLPHIN(Dolphin.class, DolphinNPC.class, V1_13_R1.class),

        COD(Cod.class, CodNPC.class, V1_13_R1.class),
        SALMON(Salmon.class, SalmonNPC.class, V1_13_R1.class),
        PUFFERFISH(Pufferfish.class, PufferfishNPC.class, V1_13_R1.class),
        TROPICALFISH(Tropicalfish.class, TropicalfishNPC.class, V1_13_R1.class),

        PHANTOM(Phantom.class, PhantomNPC.class, V1_13_R1.class),
        TURTLE(Turtle.class, TurtleNPC.class, V1_13_R1.class),

        BOAT(Boat.class, BoatNPC.class, V1_8_R1.class),
        CAVE_SPIDER(CaveSpider.class, CaveSpiderNPC.class, V1_8_R1.class),
        ;

        private final @NotNull Class<? extends Entity> entityClass;
        private final @NotNull Class<? extends EntityNPC> npcClass;
        private final @NotNull Class<? extends Version> since;
        private final @Nullable Object data;

        EntityType(@NotNull Class<? extends Entity> entityClass, @NotNull Class<? extends EntityNPC> npcClass, @NotNull Class<? extends Version> since) {
            this(entityClass, npcClass, since, null);
        }
        EntityType(@NotNull Class<? extends Entity> entityClass, @NotNull Class<? extends EntityNPC> npcClass, @NotNull Class<? extends Version> since, @Nullable Object data) {
            this.entityClass = entityClass;
            this.npcClass = npcClass;
            this.since = since;
            this.data = data;
        }

        @NotNull
        public Class<? extends Entity> getEntityClass() {
            return entityClass;
        }

        public @NotNull Class<? extends EntityNPC> getNPCClass() {
            return npcClass;
        }

        public @NotNull Class<? extends Version> getSince() {
            return since;
        }

        public @Nullable Object getData() {
            return data;
        }

        public boolean canFastInstance() {
            for (Method method : getNPCClass().getDeclaredMethods()) {
                if (method.getName().equals("fastInstance")) {
                    return true;
                }
            }
            return false;
        }
        public @NotNull NPC fastInstance(int id, @NotNull List<@NotNull OfflinePlayer> players, @NotNull Location location) {
            if (canFastInstance()) {
                try {
                    Method method = null;
                    for (Method fM : getNPCClass().getDeclaredMethods()) {
                        if (fM.getName().equals("fastInstance")) {
                            method = fM;
                        }
                    }

                    return (NPC) Objects.requireNonNull(method).invoke(null, id, players, location, this.getData());
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e.getCause().getMessage(), e);
                }
            } else {
                throw new IllegalStateException("This type of NPC doesn't supports fast instances!");
            }
        }

        public boolean isEntityLiving() {
            return ClassUtils.isInstanceOf(getEntityClass(), EntityLiving.class);
        }
        public boolean isAgeableEntityLiving() {
            return ClassUtils.isInstanceOf(getEntityClass(), AgeableEntityLiving.class);
        }
    }

}
