package codes.laivy.npc.mappings;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.EnumExecutor;
import codes.laivy.npc.mappings.instances.Executor;
import codes.laivy.npc.mappings.instances.FieldExecutor;
import codes.laivy.npc.mappings.instances.MethodExecutor;
import codes.laivy.npc.mappings.utils.VersionCompound;
import codes.laivy.npc.mappings.utils.VersionPacket;
import codes.laivy.npc.mappings.utils.VersionVector3f;
import codes.laivy.npc.mappings.utils.classes.datawatcher.DataWatcher;
import codes.laivy.npc.mappings.utils.classes.datawatcher.VersionedDataWatcherObject;
import codes.laivy.npc.mappings.utils.classes.entity.Entity;
import codes.laivy.npc.mappings.utils.classes.entity.EntityPlayer;
import codes.laivy.npc.mappings.utils.classes.entity.animal.Ocelot;
import codes.laivy.npc.mappings.utils.classes.entity.animal.Rabbit;
import codes.laivy.npc.mappings.utils.classes.entity.animal.horse.Horse;
import codes.laivy.npc.mappings.utils.classes.entity.item.FallingBlock;
import codes.laivy.npc.mappings.utils.classes.entity.monster.*;
import codes.laivy.npc.mappings.utils.classes.entity.npc.Villager;
import codes.laivy.npc.mappings.utils.classes.entity.npc.VillagerProfession;
import codes.laivy.npc.mappings.utils.classes.enums.EntityPose;
import codes.laivy.npc.mappings.utils.classes.enums.EnumColorEnum;
import codes.laivy.npc.mappings.utils.classes.gameprofile.GameProfile;
import codes.laivy.npc.mappings.utils.classes.gameprofile.Property;
import codes.laivy.npc.mappings.utils.classes.gameprofile.PropertyMap;
import codes.laivy.npc.mappings.utils.classes.others.chat.IChatBaseComponent;
import codes.laivy.npc.mappings.utils.classes.others.location.BlockPosition;
import codes.laivy.npc.mappings.utils.classes.scoreboard.Scoreboard;
import codes.laivy.npc.mappings.utils.classes.scoreboard.ScoreboardTeam;
import codes.laivy.npc.mappings.utils.classes.entity.animal.Sheep;
import org.bukkit.Location;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public abstract class Version implements VersionCompound, VersionPacket, VersionVector3f {

    public static final @NotNull Map<@NotNull String, @NotNull Version> LOADED_VERSIONS = new HashMap<>();

    private final @NotNull Map<@NotNull String, @NotNull ClassExecutor> classes = new LinkedHashMap<>();
    private final @NotNull Map<@NotNull String, @NotNull MethodExecutor> methods = new LinkedHashMap<>();
    private final @NotNull Map<@NotNull String, @NotNull FieldExecutor> fields = new LinkedHashMap<>();
    private final @NotNull Map<@NotNull String, @NotNull EnumExecutor> enums = new LinkedHashMap<>();
    private final @NotNull Map<@NotNull String, @NotNull String> texts = new LinkedHashMap<>();
    private final @NotNull Map<@NotNull String, @NotNull Object> objects = new LinkedHashMap<>();

    protected abstract boolean onLoad(@NotNull Class<? extends Version> version, @NotNull String key, @NotNull Executor executor);

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Version() {
        getClasses();
        getMethods();
        getFields();
        getEnums();
        getTexts();
        getObjects();
    }

    protected void load(@NotNull Class<? extends Version> version, @NotNull String key, @NotNull Executor executor) {
        if (onLoad(version, key, executor)) {
            executor.load();

            if (executor instanceof EnumExecutor) {
                enums.put(key, (EnumExecutor) executor);
            } else if (executor instanceof ClassExecutor) {
                classes.put(key, (ClassExecutor) executor);
            } else if (executor instanceof MethodExecutor) {
                methods.put(key, (MethodExecutor) executor);
            } else if (executor instanceof FieldExecutor) {
                fields.put(key, (FieldExecutor) executor);
            } else {
                throw new IllegalArgumentException("Couldn't find this executor's type");
            }
        }
    }

    public @NotNull Map<String, ClassExecutor> getClasses() {
        return classes;
    }

    public @NotNull Map<String, MethodExecutor> getMethods() {
        return methods;
    }

    public @NotNull Map<String, FieldExecutor> getFields() {
        return fields;
    }

    public @NotNull Map<String, EnumExecutor> getEnums() {
        return enums;
    }

    public @NotNull Map<String, String> getTexts() {
        return texts;
    }

    public @NotNull Map<String, Object> getObjects() {
        return objects;
    }

    public @NotNull abstract String getName();

    @NotNull
    public ClassExecutor getClassExec(@NotNull String name) {
        if (getClasses().containsKey(name)) {
            return getClasses().get(name);
        } else {
            throw new NullPointerException("Cannot find a ClassExecutor named '" + name + "' at the version loader '" + getClass().getName() + "'");
        }
    }
    @NotNull
    public ClassExecutor getClassExec(@NotNull String name, boolean array) {
        ClassExecutor e = getClassExec(name);

        return new ClassExecutor(e.getReflectionClass(), array) {{
            load();
        }};
    }

    @NotNull
    public MethodExecutor getMethodExec(@NotNull String name) {
        if (getMethods().containsKey(name)) {
            return getMethods().get(name);
        } else {
            throw new NullPointerException("Cannot find a MethodExecutor named '" + name + "' at the version loader '" + getClass().getName() + "'");
        }
    }

    @NotNull
    public FieldExecutor getFieldExec(@NotNull String name) {
        if (getFields().containsKey(name)) {
            return getFields().get(name);
        } else {
            throw new NullPointerException("Cannot find a FieldExecutor named '" + name + "' at the version loader '" + getClass().getName() + "'");
        }
    }

    @NotNull
    public EnumExecutor getEnumExec(@NotNull String name) {
        if (getEnums().containsKey(name)) {
            return getEnums().get(name);
        } else {
            throw new NullPointerException("Cannot find a EnumExecutor named '" + name + "' at the version loader '" + getClass().getName() + "'");
        }
    }

    @NotNull
    public String getText(@NotNull String name) {
        if (getTexts().containsKey(name)) {
            return getTexts().get(name);
        } else {
            throw new NullPointerException("Cannot find a String named '" + name + "' at the version loader '" + getClass().getName() + "'");
        }
    }
    @NotNull
    public Object getObject(@NotNull String name) {
        if (getObjects().containsKey(name)) {
            return getObjects().get(name);
        } else {
            throw new NullPointerException("Cannot find a Object named '" + name + "' at the version loader '" + getClass().getName() + "'");
        }
    }

    // VERSION
    public abstract boolean isEntityTypeSupported(@NotNull Entity.EntityType type);
    // VERSION

    // ENTITY
    //
    public abstract @NotNull Entity createEntity(@NotNull Entity.EntityType type, @NotNull Location location);
    public abstract @NotNull FallingBlock createFallingBlockEntity(@NotNull Location location, @NotNull Material material);

    public abstract @NotNull EntityPose getPose(@NotNull Entity entity);
    public abstract void setPose(@NotNull Entity entity, @NotNull EntityPose pose);

    public abstract @NotNull Location getLocation(@NotNull Entity entity);
    //
    // ENTITY

    // LOCATION
    public abstract @NotNull BlockPosition createBlockPosition(int x, int y, int z);
    //

    // ENTITY CAT
    public abstract @NotNull Ocelot.CatVariant getEntityCatVariant(@NotNull Ocelot ocelot);
    public abstract void setEntityCatVariant(@NotNull Ocelot ocelot, @NotNull Ocelot.CatVariant variant);
    // ENTITY HORSE
    public abstract @NotNull Horse.Type getEntityHorseType(@NotNull Horse horse);
    public abstract void setEntityHorseType(@NotNull Horse horse, @NotNull Horse.Type type);
    // ENTITY RABBIT
    public abstract @NotNull Rabbit.Variant getEntityRabbitType(@NotNull Rabbit rabbit);
    public abstract void setEntityRabbitType(@NotNull Rabbit rabbit, @NotNull Rabbit.Variant type);
    // ENTITY SHEEP
    public abstract @NotNull EnumColorEnum.EnumColor getEntitySheepColor(@NotNull Sheep sheep);
    public abstract void setEntitySheepColor(@NotNull Sheep sheep, @NotNull EnumColorEnum.EnumColor color);
    // ENTITY SKELETON
    public abstract @NotNull Skeleton.Type getEntitySkeletonType(@NotNull Skeleton skeleton);
    public abstract void setEntitySkeletonType(@NotNull Skeleton skeleton, @NotNull Skeleton.Type type);
    // ENTITY VILLAGER
    public abstract @NotNull VillagerProfession getEntityVillagerProfession(@NotNull Villager villager);
    public abstract void setEntityVillagerProfession(@NotNull Villager villager, @NotNull VillagerProfession profession);
    // ENTITY ENDERMAN
    public abstract boolean isEntityEndermanScreaming(@NotNull Enderman enderman);
    public abstract void setEntityEndermanScreaming(@NotNull Enderman enderman, boolean screaming);
    // ENTITY ZOMBIE
    public abstract @Nullable Zombie.Type getEntityZombieType(@NotNull Zombie zombie);
    public abstract void setEntityZombieType(@NotNull Zombie zombie, @Nullable Zombie.Type type);
    // ENTITY CREEPER
    public abstract boolean isEntityCreeperIgnited(@NotNull Creeper creeper);
    public abstract void setEntityCreeperIgnited(@NotNull Creeper creeper, boolean ignited);
    // ENTITY GHAST
    public abstract boolean isEntityGhastAttacking(@NotNull Ghast ghast);
    public abstract void setEntityGhastAttacking(@NotNull Ghast ghast, boolean attacking);
    //

    // ENTITY PLAYER
    //
    public abstract @NotNull EntityPlayer createPlayer(@NotNull GameProfile profile, @NotNull Location location);
    public abstract @NotNull GameProfile createGameProfile(@NotNull UUID uuid, @NotNull String name);
    //
    // ENTITY PLAYER

    // ARMOR STAND
    //

    // MOJANG
    public abstract boolean putInPropertyMap(@NotNull PropertyMap propertyMap, @NotNull String string, @NotNull Property property);
    //

    // SCOREBOARD
    //
    public abstract @NotNull Scoreboard getScoreboardFrom(@NotNull org.bukkit.scoreboard.Scoreboard scoreboard);
    public abstract @NotNull ScoreboardTeam createScoreboardTeam(@NotNull Scoreboard scoreboard, @NotNull String name);
    public abstract boolean addToTeam(@NotNull Scoreboard scoreboard, @NotNull ScoreboardTeam team, @NotNull Entity entity);
    //
    // SCOREBOARD

    // IChatBaseComponent
    //
    public abstract @NotNull IChatBaseComponent stringToBaseComponent(@NotNull String string);
    //
    // IChatBaseComponent

    // DataWatcher
    //
    public abstract @NotNull Map<@NotNull Integer, @NotNull VersionedDataWatcherObject> dataWatcherGetValues(@NotNull DataWatcher dataWatcher);
    //
    // DataWatcher

}
