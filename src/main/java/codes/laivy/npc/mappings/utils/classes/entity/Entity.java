package codes.laivy.npc.mappings.utils.classes.entity;

import codes.laivy.npc.mappings.Version;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import codes.laivy.npc.mappings.utils.classes.datawatcher.DataWatcher;
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
import codes.laivy.npc.mappings.utils.classes.enums.EntityPose;
import codes.laivy.npc.mappings.utils.classes.java.BooleanObjExec;
import codes.laivy.npc.mappings.utils.classes.java.DoubleObjExec;
import codes.laivy.npc.mappings.utils.classes.java.FloatObjExec;
import codes.laivy.npc.mappings.utils.classes.java.StringObjExec;
import codes.laivy.npc.mappings.utils.classes.others.location.World;
import codes.laivy.npc.mappings.versions.V1_8_R1;
import codes.laivy.npc.mappings.versions.V1_9_R1;
import codes.laivy.npc.types.EntityNPC;
import codes.laivy.npc.types.NPC;
import codes.laivy.npc.types.list.ambient.BatNPC;
import codes.laivy.npc.types.list.ambient.EggNPC;
import codes.laivy.npc.types.list.animal.*;
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
import codes.laivy.npc.types.list.npc.VillagerNPC;
import codes.laivy.npc.utils.ClassUtils;
import codes.laivy.npc.utils.ReflectionUtils;
import com.avaje.ebean.validation.NotEmpty;
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

    @NotNull
    public String getName() {
        return (String) Objects.requireNonNull(laivynpc().getVersion().getMethodExec("Entity:Entity:getName").invokeInstance(this));
    }

    public @NotNull @NotEmpty String getCustomName() {
        return (String) Objects.requireNonNull(laivynpc().getVersion().getMethodExec("Entity:Entity:getCustomName").invokeInstance(this));
    }
    public void setCustomName(@NotNull String customName) {
        laivynpc().getVersion().getMethodExec("Entity:Entity:setCustomName").invokeInstance(this, new StringObjExec(customName));
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
        //noinspection ConstantConditions
        return (boolean) laivynpc().getVersion().getMethodExec("Entity:Entity:isInvisible").invokeInstance(this);
    }
    public void setInvisible(boolean invisible) {
        laivynpc().getVersion().getMethodExec("Entity:Entity:setInvisible").invokeInstance(this, new BooleanObjExec(invisible));
    }

    /**
     * As know as 'absMoveTo' in the newer versions
     * @param location the new x, y, z, yaw and pitch
     */
    public void setLocation(@NotNull Location location) {
        laivynpc().getVersion().getMethodExec("Entity:Entity:setLocation").invokeInstance(this, new DoubleObjExec(location.getX()), new DoubleObjExec(location.getY()), new DoubleObjExec(location.getZ()), new FloatObjExec(location.getYaw()), new FloatObjExec(location.getPitch()));
    }

    public @NotNull Location getLocation() {
        return laivynpc().getVersion().getLocation(this);
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
        BAT(Bat.class, BatNPC.class, V1_8_R1.class),
        EGG(Egg.class, EggNPC.class, V1_8_R1.class),
        CHICKEN(Chicken.class, ChickenNPC.class, V1_8_R1.class),

        HORSE(Horse.class, HorseNPC.class, V1_8_R1.class, "HORSE"),
        HORSE_DONKEY(Horse.class, HorseNPC.class, V1_8_R1.class, "DONKEY"),
        HORSE_MULE(Horse.class, HorseNPC.class, V1_8_R1.class, "MULE"),
        HORSE_ZOMBIE(Horse.class, HorseNPC.class, V1_8_R1.class, "ZOMBIE"),
        HORSE_SKELETON(Horse.class, HorseNPC.class, V1_8_R1.class, "SKELETON"),

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
        SLIME(Slime.class, SlimeNPC.class, V1_8_R1.class),
        SPIDER(Spider.class, SpiderNPC.class, V1_8_R1.class),
        WITCH(Witch.class, WitchNPC.class, V1_8_R1.class),
        ZOMBIE(Zombie.class, ZombieNPC.class, V1_8_R1.class),
        VILLAGER(Villager.class, VillagerNPC.class, V1_8_R1.class),
        SHULKER(Shulker.class, ShulkerNPC.class, V1_9_R1.class),
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
            for (Method method : getNPCClass().getMethods()) {
                if (method.getName().equals("fastInstance")) {
                    return true;
                }
            }
            return false;
        }
        public @NotNull NPC fastInstance(@NotNull List<@NotNull OfflinePlayer> player, @NotNull Location location) {
            if (canFastInstance()) {
                try {
                    Method method = null;
                    for (Method fM : getNPCClass().getMethods()) {
                        if (fM.getName().equals("fastInstance")) {
                            method = fM;
                        }
                    }

                    return (NPC) Objects.requireNonNull(method).invoke(null, player, location, this.getData());
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e.getCause().getMessage(), e);
                }
            } else {
                throw new IllegalStateException("This type of NPC doesn't supports fast instances!");
            }
        }

        public boolean isLivingEntity() {
            return ClassUtils.isInstanceOf(getEntityClass(), EntityLiving.class);
        }
        public boolean isAgeableLivingEntity() {
            return ClassUtils.isInstanceOf(getEntityClass(), AgeableLivingEntity.class);
        }
    }

}
