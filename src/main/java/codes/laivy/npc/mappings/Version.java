package codes.laivy.npc.mappings;

import codes.laivy.npc.mappings.defaults.classes.entity.animal.*;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.fish.Fish;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.fish.Pufferfish;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.fish.Tropicalfish;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.AbstractChestedHorse;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.AbstractHorse;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.Llama;
import codes.laivy.npc.mappings.defaults.classes.entity.decoration.ArmorStand;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.illagers.IllagerWizard;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.zombie.ZombieVillager;
import codes.laivy.npc.mappings.defaults.classes.enums.EnumSpellEnum;
import codes.laivy.npc.mappings.defaults.classes.enums.HorseArmor;
import codes.laivy.npc.mappings.defaults.classes.others.location.Vector3f;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.EnumExecutor;
import codes.laivy.npc.mappings.instances.Executor;
import codes.laivy.npc.mappings.instances.FieldExecutor;
import codes.laivy.npc.mappings.instances.MethodExecutor;
import codes.laivy.npc.mappings.defaults.VersionCompound;
import codes.laivy.npc.mappings.defaults.VersionPacket;
import codes.laivy.npc.mappings.defaults.VersionLocation;
import codes.laivy.npc.mappings.defaults.classes.datawatcher.DataWatcher;
import codes.laivy.npc.mappings.defaults.classes.datawatcher.VersionedDataWatcherObject;
import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.entity.EntityLiving;
import codes.laivy.npc.mappings.defaults.classes.entity.EntityPlayer;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.Horse;
import codes.laivy.npc.mappings.defaults.classes.entity.item.FallingBlock;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.*;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.skeleton.Skeleton;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.zombie.Zombie;
import codes.laivy.npc.mappings.defaults.classes.entity.npc.Villager;
import codes.laivy.npc.mappings.defaults.classes.entity.npc.VillagerProfession;
import codes.laivy.npc.mappings.defaults.classes.enums.EntityPose;
import codes.laivy.npc.mappings.defaults.classes.enums.EnumColorEnum;
import codes.laivy.npc.mappings.defaults.classes.gameprofile.GameProfile;
import codes.laivy.npc.mappings.defaults.classes.gameprofile.Property;
import codes.laivy.npc.mappings.defaults.classes.gameprofile.PropertyMap;
import codes.laivy.npc.mappings.defaults.classes.others.chat.IChatBaseComponent;
import codes.laivy.npc.mappings.defaults.classes.others.location.BlockPosition;
import codes.laivy.npc.mappings.defaults.classes.scoreboard.Scoreboard;
import codes.laivy.npc.mappings.defaults.classes.scoreboard.ScoreboardTeam;
import org.bukkit.Location;
import org.bukkit.Material;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public abstract class Version implements VersionCompound, VersionPacket, VersionLocation {

    public static final @NotNull Map<@NotNull String, @NotNull Version> LOADED_VERSIONS = new HashMap<>();

    private final @NotNull Map<@NotNull String, @NotNull ClassExecutor> classes = new HashMap<>();
    private final @NotNull Map<@NotNull String, @NotNull MethodExecutor> methods = new HashMap<>();
    private final @NotNull Map<@NotNull String, @NotNull FieldExecutor> fields = new HashMap<>();
    private final @NotNull Map<@NotNull String, @NotNull EnumExecutor> enums = new HashMap<>();
    private final @NotNull Map<@NotNull String, @NotNull String> texts = new HashMap<>();
    private final @NotNull Map<@NotNull String, @NotNull Object> objects = new HashMap<>();

    protected abstract boolean onLoad(@NotNull Class<? extends Version> version, @NotNull String key, @NotNull Executor executor);

    public abstract void loadClasses();
    public abstract void loadMethods();
    public abstract void loadFields();
    public abstract void loadEnums();
    public abstract void loadTexts();
    public abstract void loadObjects();

    public Version() {
        loadClasses();
        loadEnums();
        loadMethods();
        loadFields();
        loadTexts();
        loadObjects();

        laivynpc().log("§7Loaded: " + getClasses().size() + " classes, " + getMethods().size() + " methods, " + getFields().size() + " fields, " + getEnums().size() + " enums, " + getTexts().size() + " texts and " + getObjects().size() + " objects.");
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
    public @NotNull Entity createEntity(@NotNull Entity.EntityType type, @NotNull Location location) {
        if (location.getWorld() == null) {
            throw new NullPointerException("This location's world is null!");
        }
        Entity entity = getEntityInstance(type, location);
        if (entity == null) {
            throw new IllegalArgumentException("Cannot get this entity type '" + type.name() + "' for this version!");
        }
        entity.setLocation(location);
        return entity;
    }

    protected abstract @Nullable Entity getEntityInstance(@NotNull Entity.EntityType type, @NotNull Location location);
    public abstract @NotNull FallingBlock createFallingBlockEntity(@NotNull Location location, @NotNull Material material);

    public abstract @NotNull EntityPose getPose(@NotNull Entity entity);
    public abstract void setPose(@NotNull Entity entity, @NotNull EntityPose pose);

    public abstract @NotNull Location getEntityLocation(@NotNull Entity entity);
    public abstract void setEntityLocation(@NotNull Entity entity, @NotNull Location location);
    //
    // ENTITY

    // LOCATION
    public abstract @NotNull BlockPosition createBlockPosition(int x, int y, int z);
    //

    // ENTITY
    public abstract @NotNull String getEntityCustomName(@NotNull Entity entity);
    public abstract void setEntityCustomName(@NotNull Entity entity, @NotNull String customName);
    // ENTITY CAT
    public abstract @NotNull Cat.CatVariant getEntityCatVariant(@NotNull Cat cat);
    public abstract void setEntityCatVariant(@NotNull Cat cat, @NotNull Cat.CatVariant variant);
    // ENTITY HORSE
    public abstract @NotNull Horse.Type getEntityAbstractHorseType(@NotNull AbstractHorse horse);
    public abstract void setEntityAbstractHorseType(@NotNull AbstractHorse horse, @NotNull Horse.Type type);
    public abstract @NotNull HorseArmor getEntityHorseArmor(@NotNull Horse horse);
    public abstract void setEntityHorseArmor(@NotNull Horse horse, @NotNull HorseArmor armor);
    @ApiStatus.Experimental
    public abstract int getEntityHorseVariant(@NotNull Horse horse);
    @ApiStatus.Experimental
    public abstract void setEntityHorseVariant(@NotNull Horse horse, int variant);
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
    public abstract @NotNull Villager.Type getEntityVillagerType(@NotNull Villager villager);
    public abstract void setEntityVillagerType(@NotNull Villager villager, @NotNull Villager.Type type);
    // ENTITY ZOMBIE VILLAGER
    public abstract @NotNull VillagerProfession getEntityZombieVillagerProfession(@NotNull ZombieVillager zombieVillager);
    public abstract void setEntityZombieVillagerProfession(@NotNull ZombieVillager zombieVillager, @NotNull VillagerProfession profession);
    public abstract @NotNull Villager.Type getEntityZombieVillagerType(@NotNull ZombieVillager zombieVillager);
    public abstract void setEntityZombieVillagerType(@NotNull ZombieVillager zombieVillager, @NotNull Villager.Type type);
    // ENTITY ENDERMAN
    public abstract boolean isEntityEndermanScreaming(@NotNull Enderman enderman);
    public abstract void setEntityEndermanScreaming(@NotNull Enderman enderman, boolean screaming);
    // ENTITY ZOMBIE
    public abstract @Nullable Zombie.Type getEntityZombieType(@NotNull Zombie zombie);
    public abstract void setEntityZombieType(@NotNull Zombie zombie, @Nullable Zombie.Type type);
    public abstract boolean isEntityZombieBaby(@NotNull Zombie zombie);
    public abstract void setEntityZombieBaby(@NotNull Zombie zombie, boolean baby);
    // ENTITY CREEPER
    public abstract boolean isEntityCreeperIgnited(@NotNull Creeper creeper);
    public abstract void setEntityCreeperIgnited(@NotNull Creeper creeper, boolean ignited);
    public abstract boolean isEntityCreeperPowered(@NotNull Creeper creeper);
    public abstract void setEntityCreeperPowered(@NotNull Creeper creeper, boolean powered);
    // ENTITY GHAST
    public abstract boolean isEntityGhastAttacking(@NotNull Ghast ghast);
    public abstract void setEntityGhastAttacking(@NotNull Ghast ghast, boolean attacking);
    // ENTITY GUARDIAN
    public abstract void setEntityGuardianTarget(@NotNull Guardian guardian, @Nullable EntityLiving entity);
    // ENTITY SLIME
    public abstract int getEntitySlimeSize(@NotNull Slime slime);
    public abstract void setEntitySlimeSize(@NotNull Slime slime, int size);
    // ENTITY SNOWMAN
    public abstract boolean hasEntitySnowmanHat(@NotNull Snowman snowman);
    public abstract void setEntitySnowmanHat(@NotNull Snowman snowman, boolean hat);
    // ENTITY CHESTED HORSE
    public abstract boolean hasEntityChestedHorseChest(@NotNull AbstractChestedHorse horse);
    public abstract void setEntityChestedHorseChest(@NotNull AbstractChestedHorse horse, boolean chest);
    // ENTITY LLAMA
    public abstract @NotNull Llama.Variant getEntityLlamaVariant(@NotNull Llama llama);
    public abstract void setEntityLlamaVariant(@NotNull Llama llama, @NotNull Llama.Variant variant);
    public abstract @Nullable EnumColorEnum.EnumColor getEntityLlamaCarpetColor(@NotNull Llama llama);
    public abstract void setEntityLlamaCarpetColor(@NotNull Llama llama, @Nullable EnumColorEnum.EnumColor color);
    // ENTITY WIZARD
    public abstract @NotNull EnumSpellEnum.Spell getEntityWizardSpell(@NotNull IllagerWizard wizard);
    public abstract void setEntityWizardSpell(@NotNull IllagerWizard wizard, @NotNull EnumSpellEnum.Spell spell);
    // ENTITY PARROT
    public abstract @NotNull Parrot.Variant getEntityParrotVariant(@NotNull Parrot parrot);
    public abstract void setEntityParrotVariant(@NotNull Parrot parrot, @NotNull Parrot.Variant variant);
    // ENTITY SHULKER
    public abstract @NotNull EnumColorEnum.EnumColor getEntityShulkerColor(@NotNull Shulker shulker);
    public abstract void setEntityShulkerColor(@NotNull Shulker shulker, @NotNull EnumColorEnum.EnumColor variant);
    // ENTITY SPIDER
    public abstract boolean isEntitySpiderClimbing(@NotNull Spider spider);
    public abstract void setEntitySpiderClimbing(@NotNull Spider spider, boolean climbing);
    // ENTITY DOLPHIN
    public abstract boolean hasEntityDolphinFish(@NotNull Dolphin dolphin);
    public abstract void setEntityDolphinFish(@NotNull Dolphin dolphin, boolean fish);
    // ENTITY FISH
    public abstract @NotNull Fish.Type getEntityFishType(@NotNull Fish fish);
    // ENTITY PUFFERFISH
    public abstract int getEntityPufferFishPuff(@NotNull Pufferfish fish);
    public abstract void setEntityPufferFishPuff(@NotNull Pufferfish fish, int puff);
    // ENTITY TROPICALFISH
    public abstract int getEntityTropicalFishVariant(@NotNull Tropicalfish fish);
    public abstract void setEntityTropicalFishVariant(@NotNull Tropicalfish fish, int variant);
    // ENTITY PHANTOM
    public abstract int getEntityPhantomSize(@NotNull Phantom phantom);
    public abstract void setEntityPhantomSize(@NotNull Phantom phantom, int variant);
    // ENTITY TURTLE
    public abstract boolean hasEntityTurtleEgg(@NotNull Turtle turtle);
    public abstract void setEntityTurtleEgg(@NotNull Turtle turtle, boolean egg);
    // ENTITY BLAZE
    public abstract boolean isEntityBlazeCharging(@NotNull Blaze blaze);
    public abstract void setEntityBlazeCharging(@NotNull Blaze blaze, boolean charging);
    // ENTITY POLAR BEAR
    public abstract boolean isEntityPolarBearStanding(@NotNull PolarBear bear);
    public abstract void setEntityPolarBearStanding(@NotNull PolarBear bear, boolean standing);
    // ENTITY PIG
    public abstract boolean hasEntityPigSaddle(@NotNull Pig pig);
    public abstract void setEntityPigSaddle(@NotNull Pig pig, boolean saddle);
    // ENTITY WOLF
    public abstract boolean isEntityWolfAngry(@NotNull Wolf wolf);
    public abstract void setEntityWolfAngry(@NotNull Wolf wolf, boolean angry);
    public abstract @NotNull EnumColorEnum.EnumColor getEntityWolfCollarColor(@NotNull Wolf wolf);
    public abstract void setEntityWolfCollarColor(@NotNull Wolf wolf, @NotNull EnumColorEnum.EnumColor color);
    //

    // ENTITY PLAYER
    //
    public abstract @NotNull EntityPlayer createPlayer(@NotNull GameProfile profile, @NotNull Location location);
    public abstract @NotNull GameProfile createGameProfile(@NotNull UUID uuid, @NotNull String name);
    //
    // ENTITY PLAYER

    // ARMOR STAND
    public abstract @NotNull Vector3f getArmorStandPose(ArmorStand.@NotNull Pose pose, @NotNull ArmorStand stand);
    public abstract void setArmorStandPose(ArmorStand.@NotNull Pose pose, @NotNull ArmorStand stand, @NotNull Vector3f vector3f);
    //

    // MOJANG
    public abstract boolean putInPropertyMap(@NotNull PropertyMap propertyMap, @NotNull String string, @NotNull Property property);
    //

    // SCOREBOARD
    //
    public abstract @NotNull Scoreboard getScoreboardFrom(@NotNull org.bukkit.scoreboard.Scoreboard scoreboard);
    public abstract @NotNull ScoreboardTeam createScoreboardTeam(@NotNull Scoreboard scoreboard, @NotNull String name);
    public abstract boolean addToTeam(@NotNull Scoreboard scoreboard, @NotNull ScoreboardTeam team, @NotNull Entity entity);
    public abstract void setPrefix(@NotNull ScoreboardTeam team, @NotNull String prefix);
    //
    // SCOREBOARD

    // IChatBaseComponent
    public abstract @NotNull IChatBaseComponent stringToBaseComponent(@NotNull String string);
    public abstract @NotNull String baseComponentToString(@NotNull IChatBaseComponent iChatBaseComponent);
    //

    // DataWatcher
    public abstract @NotNull Map<@NotNull Integer, @NotNull VersionedDataWatcherObject> dataWatcherGetValues(@NotNull DataWatcher dataWatcher);
    //

}
