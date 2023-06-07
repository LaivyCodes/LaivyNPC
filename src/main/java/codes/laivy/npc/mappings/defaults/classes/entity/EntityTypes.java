package codes.laivy.npc.mappings.defaults.classes.entity;

import codes.laivy.npc.mappings.instances.FieldExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.versions.V1_14_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class EntityTypes extends ObjectExecutor {

    static {
        if (!ReflectionUtils.isCompatible(V1_14_R1.class)) {
            throw new UnsupportedOperationException("This class is only available since 1.14+");
        }
    }

    public static final @NotNull EntityTypes ARMOR_STAND;
    public static final @NotNull EntityTypes BAT;
    public static final @NotNull EntityTypes EGG;
    public static final @NotNull EntityTypes BLAZE;
    public static final @NotNull EntityTypes BOAT;
    public static final @NotNull EntityTypes CAT;
    public static final @NotNull EntityTypes CAVE_SPIDER;
    public static final @NotNull EntityTypes CHICKEN;
    public static final @NotNull EntityTypes COD;
    public static final @NotNull EntityTypes COW;
    public static final @NotNull EntityTypes CREEPER;
    public static final @NotNull EntityTypes DONKEY;
    public static final @NotNull EntityTypes DOLPHIN;
    public static final @NotNull EntityTypes DRAGON_FIREBALL;
    public static final @NotNull EntityTypes DROWNED;
    public static final @NotNull EntityTypes ELDER_GUARDIAN;
    public static final @NotNull EntityTypes END_CRYSTAL;
    public static final @NotNull EntityTypes ENDER_DRAGON;
    public static final @NotNull EntityTypes ENDERMAN;
    public static final @NotNull EntityTypes ENDERMITE;
    public static final @NotNull EntityTypes EVOKER_FANGS;
    public static final @NotNull EntityTypes EVOKER;
    public static final @NotNull EntityTypes EYE_OF_ENDER;
    public static final @NotNull EntityTypes FALLING_BLOCK;
    public static final @NotNull EntityTypes FOX;
    public static final @NotNull EntityTypes GHAST;
    public static final @NotNull EntityTypes GIANT;
    public static final @NotNull EntityTypes GUARDIAN;
    public static final @NotNull EntityTypes HORSE;
    public static final @NotNull EntityTypes HUSK;
    public static final @NotNull EntityTypes ILLUSIONER;
    public static final @NotNull EntityTypes ITEM;
    public static final @NotNull EntityTypes ITEM_FRAME;
    public static final @NotNull EntityTypes FIREBALL;
    public static final @NotNull EntityTypes LEASH_KNOT;
    public static final @NotNull EntityTypes LLAMA;
    public static final @NotNull EntityTypes LLAMA_SPIT;
    public static final @NotNull EntityTypes MAGMA_CUBE;
    public static final @NotNull EntityTypes MINECART;
    public static final @NotNull EntityTypes CHEST_MINECART;
    public static final @NotNull EntityTypes COMMAND_BLOCK_MINECART;
    public static final @NotNull EntityTypes FURNACE_MINECART;
    public static final @NotNull EntityTypes HOPPER_MINECART;
    public static final @NotNull EntityTypes SPAWNER_MINECART;
    public static final @NotNull EntityTypes TNT_MINECART;
    public static final @NotNull EntityTypes MULE;
    public static final @NotNull EntityTypes MOOSHROOM;
    public static final @NotNull EntityTypes OCELOT;
    public static final @NotNull EntityTypes PAINTING;
    public static final @NotNull EntityTypes PANDA;
    public static final @NotNull EntityTypes PARROT;
    public static final @NotNull EntityTypes PIG;
    public static final @NotNull EntityTypes PUFFERFISH;
    public static final @NotNull EntityTypes ZOMBIE_PIGMAN;
    public static final @NotNull EntityTypes POLAR_BEAR;
    public static final @NotNull EntityTypes TNT;
    public static final @NotNull EntityTypes RABBIT;
    public static final @NotNull EntityTypes SALMON;
    public static final @NotNull EntityTypes SHEEP;
    public static final @NotNull EntityTypes SHULKER;
    public static final @NotNull EntityTypes SHULKER_BULLET;
    public static final @NotNull EntityTypes SILVERFISH;
    public static final @NotNull EntityTypes SKELETON;
    public static final @NotNull EntityTypes SKELETON_HORSE;
    public static final @NotNull EntityTypes SLIME;
    public static final @NotNull EntityTypes SMALL_FIREBALL;
    public static final @NotNull EntityTypes SNOW_GOLEM;
    public static final @NotNull EntityTypes SNOWBALL;
    public static final @NotNull EntityTypes SPIDER;
    public static final @NotNull EntityTypes SQUID;
    public static final @NotNull EntityTypes STRAY;
    public static final @NotNull EntityTypes TRADER_LLAMA;
    public static final @NotNull EntityTypes TROPICAL_FISH;
    public static final @NotNull EntityTypes TURTLE;
    public static final @NotNull EntityTypes ENDER_PEARL;
    public static final @NotNull EntityTypes VEX;
    public static final @NotNull EntityTypes VILLAGER;
    public static final @NotNull EntityTypes IRON_GOLEM;
    public static final @NotNull EntityTypes VINDICATOR;
    public static final @NotNull EntityTypes PILLAGER;
    public static final @NotNull EntityTypes WANDERING_TRADER;
    public static final @NotNull EntityTypes WITCH;
    public static final @NotNull EntityTypes WITHER;
    public static final @NotNull EntityTypes WITHER_SKELETON;
    public static final @NotNull EntityTypes WITHER_SKULL;
    public static final @NotNull EntityTypes WOLF;
    public static final @NotNull EntityTypes ZOMBIE;
    public static final @NotNull EntityTypes ZOMBIE_HORSE;
    public static final @NotNull EntityTypes ZOMBIE_VILLAGER;
    public static final @NotNull EntityTypes PHANTOM;
    public static final @NotNull EntityTypes RAVAGER;

    static {
        ARMOR_STAND = a(laivynpc().getVersion().getText("EntityTypes:ARMOR_STAND"));
        BAT = a(laivynpc().getVersion().getText("EntityTypes:BAT"));
        EGG = a(laivynpc().getVersion().getText("EntityTypes:EGG"));
        BLAZE = a(laivynpc().getVersion().getText("EntityTypes:BLAZE"));
        BOAT = a(laivynpc().getVersion().getText("EntityTypes:BOAT"));
        CAT = a(laivynpc().getVersion().getText("EntityTypes:CAT"));
        CAVE_SPIDER = a(laivynpc().getVersion().getText("EntityTypes:CAVE_SPIDER"));
        CHICKEN = a(laivynpc().getVersion().getText("EntityTypes:CHICKEN"));
        COD = a(laivynpc().getVersion().getText("EntityTypes:COD"));
        COW = a(laivynpc().getVersion().getText("EntityTypes:COW"));
        CREEPER = a(laivynpc().getVersion().getText("EntityTypes:CREEPER"));
        DONKEY = a(laivynpc().getVersion().getText("EntityTypes:DONKEY"));
        DOLPHIN = a(laivynpc().getVersion().getText("EntityTypes:DOLPHIN"));
        DRAGON_FIREBALL = a(laivynpc().getVersion().getText("EntityTypes:DRAGON_FIREBALL"));
        DROWNED = a(laivynpc().getVersion().getText("EntityTypes:DROWNED"));
        ELDER_GUARDIAN = a(laivynpc().getVersion().getText("EntityTypes:ELDER_GUARDIAN"));
        END_CRYSTAL = a(laivynpc().getVersion().getText("EntityTypes:END_CRYSTAL"));
        ENDER_DRAGON = a(laivynpc().getVersion().getText("EntityTypes:ENDER_DRAGON"));
        ENDERMAN = a(laivynpc().getVersion().getText("EntityTypes:ENDERMAN"));
        ENDERMITE = a(laivynpc().getVersion().getText("EntityTypes:ENDERMITE"));
        EVOKER_FANGS = a(laivynpc().getVersion().getText("EntityTypes:EVOKER_FANGS"));
        EVOKER = a(laivynpc().getVersion().getText("EntityTypes:EVOKER"));
        EYE_OF_ENDER = a(laivynpc().getVersion().getText("EntityTypes:EYE_OF_ENDER"));
        FALLING_BLOCK = a(laivynpc().getVersion().getText("EntityTypes:FALLING_BLOCK"));
        FOX = a(laivynpc().getVersion().getText("EntityTypes:FOX"));
        GHAST = a(laivynpc().getVersion().getText("EntityTypes:GHAST"));
        GIANT = a(laivynpc().getVersion().getText("EntityTypes:GIANT"));
        GUARDIAN = a(laivynpc().getVersion().getText("EntityTypes:GUARDIAN"));
        HORSE = a(laivynpc().getVersion().getText("EntityTypes:HORSE"));
        HUSK = a(laivynpc().getVersion().getText("EntityTypes:HUSK"));
        ILLUSIONER = a(laivynpc().getVersion().getText("EntityTypes:ILLUSIONER"));
        ITEM = a(laivynpc().getVersion().getText("EntityTypes:ITEM"));
        ITEM_FRAME = a(laivynpc().getVersion().getText("EntityTypes:ITEM_FRAME"));
        FIREBALL = a(laivynpc().getVersion().getText("EntityTypes:FIREBALL"));
        LEASH_KNOT = a(laivynpc().getVersion().getText("EntityTypes:LEASH_KNOT"));
        LLAMA = a(laivynpc().getVersion().getText("EntityTypes:LLAMA"));
        LLAMA_SPIT = a(laivynpc().getVersion().getText("EntityTypes:LLAMA_SPIT"));
        MAGMA_CUBE = a(laivynpc().getVersion().getText("EntityTypes:MAGMA_CUBE"));
        MINECART = a(laivynpc().getVersion().getText("EntityTypes:MINECART"));
        CHEST_MINECART = a(laivynpc().getVersion().getText("EntityTypes:CHEST_MINECART"));
        COMMAND_BLOCK_MINECART = a(laivynpc().getVersion().getText("EntityTypes:COMMAND_BLOCK_MINECART"));
        FURNACE_MINECART = a(laivynpc().getVersion().getText("EntityTypes:FURNACE_MINECART"));
        HOPPER_MINECART = a(laivynpc().getVersion().getText("EntityTypes:HOPPER_MINECART"));
        SPAWNER_MINECART = a(laivynpc().getVersion().getText("EntityTypes:SPAWNER_MINECART"));
        TNT_MINECART = a(laivynpc().getVersion().getText("EntityTypes:TNT_MINECART"));
        MULE = a(laivynpc().getVersion().getText("EntityTypes:MULE"));
        MOOSHROOM = a(laivynpc().getVersion().getText("EntityTypes:MOOSHROOM"));
        OCELOT = a(laivynpc().getVersion().getText("EntityTypes:OCELOT"));
        PAINTING = a(laivynpc().getVersion().getText("EntityTypes:PAINTING"));
        PANDA = a(laivynpc().getVersion().getText("EntityTypes:PANDA"));
        PARROT = a(laivynpc().getVersion().getText("EntityTypes:PARROT"));
        PIG = a(laivynpc().getVersion().getText("EntityTypes:PIG"));
        PUFFERFISH = a(laivynpc().getVersion().getText("EntityTypes:PUFFERFISH"));
        ZOMBIE_PIGMAN = a(laivynpc().getVersion().getText("EntityTypes:ZOMBIE_PIGMAN"));
        POLAR_BEAR = a(laivynpc().getVersion().getText("EntityTypes:POLAR_BEAR"));
        TNT = a(laivynpc().getVersion().getText("EntityTypes:TNT"));
        RABBIT = a(laivynpc().getVersion().getText("EntityTypes:RABBIT"));
        SALMON = a(laivynpc().getVersion().getText("EntityTypes:SALMON"));
        SHEEP = a(laivynpc().getVersion().getText("EntityTypes:SHEEP"));
        SHULKER = a(laivynpc().getVersion().getText("EntityTypes:SHULKER"));
        SHULKER_BULLET = a(laivynpc().getVersion().getText("EntityTypes:SHULKER_BULLET"));
        SILVERFISH = a(laivynpc().getVersion().getText("EntityTypes:SILVERFISH"));
        SKELETON = a(laivynpc().getVersion().getText("EntityTypes:SKELETON"));
        SKELETON_HORSE = a(laivynpc().getVersion().getText("EntityTypes:SKELETON_HORSE"));
        SLIME = a(laivynpc().getVersion().getText("EntityTypes:SLIME"));
        SMALL_FIREBALL = a(laivynpc().getVersion().getText("EntityTypes:SMALL_FIREBALL"));
        SNOW_GOLEM = a(laivynpc().getVersion().getText("EntityTypes:SNOW_GOLEM"));
        SNOWBALL = a(laivynpc().getVersion().getText("EntityTypes:SNOWBALL"));
        SPIDER = a(laivynpc().getVersion().getText("EntityTypes:SPIDER"));
        SQUID = a(laivynpc().getVersion().getText("EntityTypes:SQUID"));
        STRAY = a(laivynpc().getVersion().getText("EntityTypes:STRAY"));
        TRADER_LLAMA = a(laivynpc().getVersion().getText("EntityTypes:TRADER_LLAMA"));
        TROPICAL_FISH = a(laivynpc().getVersion().getText("EntityTypes:TROPICAL_FISH"));
        TURTLE = a(laivynpc().getVersion().getText("EntityTypes:TURTLE"));
        ENDER_PEARL = a(laivynpc().getVersion().getText("EntityTypes:ENDER_PEARL"));
        VEX = a(laivynpc().getVersion().getText("EntityTypes:VEX"));
        VILLAGER = a(laivynpc().getVersion().getText("EntityTypes:VILLAGER"));
        IRON_GOLEM = a(laivynpc().getVersion().getText("EntityTypes:IRON_GOLEM"));
        VINDICATOR = a(laivynpc().getVersion().getText("EntityTypes:VINDICATOR"));
        PILLAGER = a(laivynpc().getVersion().getText("EntityTypes:PILLAGER"));
        WANDERING_TRADER = a(laivynpc().getVersion().getText("EntityTypes:WANDERING_TRADER"));
        WITCH = a(laivynpc().getVersion().getText("EntityTypes:WITCH"));
        WITHER = a(laivynpc().getVersion().getText("EntityTypes:WITHER"));
        WITHER_SKELETON = a(laivynpc().getVersion().getText("EntityTypes:WITHER_SKELETON"));
        WITHER_SKULL = a(laivynpc().getVersion().getText("EntityTypes:WITHER_SKULL"));
        WOLF = a(laivynpc().getVersion().getText("EntityTypes:WOLF"));
        ZOMBIE = a(laivynpc().getVersion().getText("EntityTypes:ZOMBIE"));
        ZOMBIE_HORSE = a(laivynpc().getVersion().getText("EntityTypes:ZOMBIE_HORSE"));
        ZOMBIE_VILLAGER = a(laivynpc().getVersion().getText("EntityTypes:ZOMBIE_VILLAGER"));
        PHANTOM = a(laivynpc().getVersion().getText("EntityTypes:PHANTOM"));
        RAVAGER = a(laivynpc().getVersion().getText("EntityTypes:RAVAGER"));
    }

    private static @NotNull EntityTypes a(@NotNull String name) {
        try {
            FieldExecutor executor = new FieldExecutor(laivynpc().getVersion().getClassExec("EntityTypes"), laivynpc().getVersion().getClassExec("EntityTypes"), name, "Gets the '" + name + "' entity type");
            executor.load();

            return new EntityTypes(executor.invokeStatic());
        } catch (Exception e) {
            StringBuilder builder = new StringBuilder();

            int row = 0;
            for (Field field : laivynpc().getVersion().getClassExec("EntityTypes").getReflectionClass().getDeclaredFields()) {
                if (row > 0) builder.append(", ");
                builder.append(field.getName());
                row++;
            }

            throw new RuntimeException("Available fields: '" + builder + "'", e);
        }
    }

    public EntityTypes(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull EntityTypesClass getClassExecutor() {
        return (EntityTypesClass) laivynpc().getVersion().getClassExec("EntityTypes");
    }

    public static class EntityTypesClass extends ClassExecutor {
        public EntityTypesClass(@NotNull String className) {
            super(className);
        }
    }
}
