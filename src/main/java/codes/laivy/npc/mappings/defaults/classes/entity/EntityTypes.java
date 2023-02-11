package codes.laivy.npc.mappings.defaults.classes.entity;

import codes.laivy.npc.mappings.instances.FieldExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.versions.V1_14_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class EntityTypes extends ObjectExecutor {

    static {
        if (!ReflectionUtils.isCompatible(V1_14_R1.class)) {
            throw new UnsupportedOperationException("This class is only available on 1.14+");
        }
    }

    public static @NotNull EntityTypes ARMOR_STAND() {
        return a("ARMOR_STAND");
    }
    public static @NotNull EntityTypes BAT() {
        return a("BAT");
    }
    public static @NotNull EntityTypes EGG() {
        return a("EGG");
    }
    public static @NotNull EntityTypes BLAZE() {
        return a("BLAZE");
    }
    public static @NotNull EntityTypes BOAT() {
        return a("BOAT");
    }
    public static @NotNull EntityTypes CAT() {
        return a("CAT");
    }
    public static @NotNull EntityTypes CAVE_SPIDER() {
        return a("CAVE_SPIDER");
    }
    public static @NotNull EntityTypes CHICKEN() {
        return a("CHICKEN");
    }
    public static @NotNull EntityTypes COD() {
        return a("COD");
    }
    public static @NotNull EntityTypes COW() {
        return a("COW");
    }
    public static @NotNull EntityTypes CREEPER() {
        return a("CREEPER");
    }
    public static @NotNull EntityTypes DONKEY() {
        return a("DONKEY");
    }
    public static @NotNull EntityTypes DOLPHIN() {
        return a("DOLPHIN");
    }
    public static @NotNull EntityTypes DRAGON_FIREBALL() {
        return a("DRAGON_FIREBALL");
    }
    public static @NotNull EntityTypes DROWNED() {
        return a("DROWNED");
    }
    public static @NotNull EntityTypes ELDER_GUARDIAN() {
        return a("ELDER_GUARDIAN");
    }
    public static @NotNull EntityTypes END_CRYSTAL() {
        return a("END_CRYSTAL");
    }
    public static @NotNull EntityTypes ENDER_DRAGON() {
        return a("ENDER_DRAGON");
    }
    public static @NotNull EntityTypes ENDERMAN() {
        return a("ENDERMAN");
    }
    public static @NotNull EntityTypes ENDERMITE() {
        return a("ENDERMITE");
    }
    public static @NotNull EntityTypes EVOKER_FANGS() {
        return a("EVOKER_FANGS");
    }
    public static @NotNull EntityTypes EVOKER() {
        return a("EVOKER");
    }
    public static @NotNull EntityTypes EYE_OF_ENDER() {
        return a("EYE_OF_ENDER");
    }
    public static @NotNull EntityTypes FALLING_BLOCK() {
        return a("FALLING_BLOCK");
    }
    public static @NotNull EntityTypes FOX() {
        return a("FOX");
    }
    public static @NotNull EntityTypes GHAST() {
        return a("GHAST");
    }
    public static @NotNull EntityTypes GIANT() {
        return a("GIANT");
    }
    public static @NotNull EntityTypes GUARDIAN() {
        return a("GUARDIAN");
    }
    public static @NotNull EntityTypes HORSE() {
        return a("HORSE");
    }
    public static @NotNull EntityTypes HUSK() {
        return a("HUSK");
    }
    public static @NotNull EntityTypes ILLUSIONER() {
        return a("ILLUSIONER");
    }
    public static @NotNull EntityTypes ITEM() {
        return a("ITEM");
    }
    public static @NotNull EntityTypes ITEM_FRAME() {
        return a("ITEM_FRAME");
    }
    public static @NotNull EntityTypes FIREBALL() {
        return a("FIREBALL");
    }
    public static @NotNull EntityTypes LEASH_KNOT() {
        return a("LEASH_KNOT");
    }
    public static @NotNull EntityTypes LLAMA() {
        return a("LLAMA");
    }
    public static @NotNull EntityTypes LLAMA_SPIT() {
        return a("LLAMA_SPIT");
    }
    public static @NotNull EntityTypes MAGMA_CUBE() {
        return a("MAGMA_CUBE");
    }
    public static @NotNull EntityTypes MINECART() {
        return a("MINECART");
    }
    public static @NotNull EntityTypes CHEST_MINECART() {
        return a("CHEST_MINECART");
    }
    public static @NotNull EntityTypes COMMAND_BLOCK_MINECART() {
        return a("COMMAND_BLOCK_MINECART");
    }
    public static @NotNull EntityTypes FURNACE_MINECART() {
        return a("FURNACE_MINECART");
    }
    public static @NotNull EntityTypes HOPPER_MINECART() {
        return a("HOPPER_MINECART");
    }
    public static @NotNull EntityTypes SPAWNER_MINECART() {
        return a("SPAWNER_MINECART");
    }
    public static @NotNull EntityTypes TNT_MINECART() {
        return a("TNT_MINECART");
    }
    public static @NotNull EntityTypes MULE() {
        return a("MULE");
    }
    public static @NotNull EntityTypes MOOSHROOM() {
        return a("MOOSHROOM");
    }
    public static @NotNull EntityTypes OCELOT() {
        return a("OCELOT");
    }
    public static @NotNull EntityTypes PAINTING() {
        return a("PAINTING");
    }
    public static @NotNull EntityTypes PANDA() {
        return a("PANDA");
    }
    public static @NotNull EntityTypes PARROT() {
        return a("PARROT");
    }
    public static @NotNull EntityTypes PIG() {
        return a("PIG");
    }
    public static @NotNull EntityTypes PUFFERFISH() {
        return a("PUFFERFISH");
    }
    public static @NotNull EntityTypes ZOMBIE_PIGMAN() {
        return a("ZOMBIE_PIGMAN");
    }
    public static @NotNull EntityTypes POLAR_BEAR() {
        return a("POLAR_BEAR");
    }
    public static @NotNull EntityTypes TNT() {
        return a("TNT");
    }
    public static @NotNull EntityTypes RABBIT() {
        return a("RABBIT");
    }
    public static @NotNull EntityTypes SALMON() {
        return a("SALMON");
    }
    public static @NotNull EntityTypes SHEEP() {
        return a("SHEEP");
    }
    public static @NotNull EntityTypes SHULKER() {
        return a("SHULKER");
    }
    public static @NotNull EntityTypes SHULKER_BULLET() {
        return a("SHULKER_BULLET");
    }
    public static @NotNull EntityTypes SILVERFISH() {
        return a("SILVERFISH");
    }
    public static @NotNull EntityTypes SKELETON() {
        return a("SKELETON");
    }
    public static @NotNull EntityTypes SKELETON_HORSE() {
        return a("SKELETON_HORSE");
    }
    public static @NotNull EntityTypes SLIME() {
        return a("SLIME");
    }
    public static @NotNull EntityTypes SMALL_FIREBALL() {
        return a("SMALL_FIREBALL");
    }
    public static @NotNull EntityTypes SNOW_GOLEM() {
        return a("SNOW_GOLEM");
    }
    public static @NotNull EntityTypes SNOWBALL() {
        return a("SNOWBALL");
    }
    public static @NotNull EntityTypes SPIDER() {
        return a("SPIDER");
    }
    public static @NotNull EntityTypes SQUID() {
        return a("SQUID");
    }
    public static @NotNull EntityTypes STRAY() {
        return a("STRAY");
    }
    public static @NotNull EntityTypes TRADER_LLAMA() {
        return a("TRADER_LLAMA");
    }
    public static @NotNull EntityTypes TROPICAL_FISH() {
        return a("TROPICAL_FISH");
    }
    public static @NotNull EntityTypes TURTLE() {
        return a("TURTLE");
    }
    public static @NotNull EntityTypes ENDER_PEARL() {
        return a("ENDER_PEARL");
    }
    public static @NotNull EntityTypes VEX() {
        return a("VEX");
    }
    public static @NotNull EntityTypes VILLAGER() {
        return a("VILLAGER");
    }
    public static @NotNull EntityTypes IRON_GOLEM() {
        return a("IRON_GOLEM");
    }
    public static @NotNull EntityTypes VINDICATOR() {
        return a("VINDICATOR");
    }
    public static @NotNull EntityTypes PILLAGER() {
        return a("PILLAGER");
    }
    public static @NotNull EntityTypes WANDERING_TRADER() {
        return a("WANDERING_TRADER");
    }
    public static @NotNull EntityTypes WITCH() {
        return a("WITCH");
    }
    public static @NotNull EntityTypes WITHER() {
        return a("WITHER");
    }
    public static @NotNull EntityTypes WITHER_SKELETON() {
        return a("WITHER_SKELETON");
    }
    public static @NotNull EntityTypes WITHER_SKULL() {
        return a("WITHER_SKULL");
    }
    public static @NotNull EntityTypes WOLF() {
        return a("WOLF");
    }
    public static @NotNull EntityTypes ZOMBIE() {
        return a("ZOMBIE");
    }
    public static @NotNull EntityTypes ZOMBIE_HORSE() {
        return a("ZOMBIE_HORSE");
    }
    public static @NotNull EntityTypes ZOMBIE_VILLAGER() {
        return a("ZOMBIE_VILLAGER");
    }
    public static @NotNull EntityTypes PHANTOM() {
        return a("PHANTOM");
    }
    public static @NotNull EntityTypes RAVAGER() {
        return a("RAVAGER");
    }
    public static @NotNull EntityTypes PLAYER() {
        return a("PLAYER");
    }

    private static @NotNull EntityTypes a(@NotNull String name) {
        FieldExecutor executor = new FieldExecutor(laivynpc().getVersion().getClassExec("EntityTypes"), laivynpc().getVersion().getClassExec("EntityTypes"), name, "Gets the '" + name + "' entity type");
        executor.load();

        return new EntityTypes(executor.invokeStatic());
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
