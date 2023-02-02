package codes.laivy.npc.utils;

import codes.laivy.npc.mappings.utils.classes.entity.Entity;
import org.jetbrains.annotations.NotNull;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public enum NonLivingEntityType {
    BOAT("Entity:Boat", 1),
    ITEM("Entity:Item", 2),
    AREA_EFFECT_CLOUD("Entity:AreaEffectCloud", 3),
    MINECART("Entity:Minecart", 10),
    ACTIVATED_TNT("Entity:ActivatedTNT", 50),
    ENDER_CRYSTAL("Entity:EnderSignal", 51),
    ARROW("Entity:Arrow", 60),
    SNOWBALL("Entity:Snowball", 61),
    EGG("Entity:Egg", 62),
    LARGE_FIREBALL("Entity:LargeFireball", 63),
    SMALL_FIREBALL("Entity:SmallFireball", 64),
    ENDER_PEARL("Entity:EnderPearl", 65),
    WITHER_SKULL("Entity:WitherSkull", 66),
    SHULKER_BULLET("Entity:ShulkerBullet", 67),
    LLAMA_SPIT("Entity:LlamaSpit", 68),
    FALLING_BLOCK("Entity:FallingBlock", 70),
    ITEM_FRAME("Entity:ItemFrame", 71),
    EYE_OF_ENDER("Entity:EyeOfEnder", 72),
    POTION("Entity:Potion", 73),
    EXPERIENCE_ORB("Entity:ExperienceOrb", 75),
    FIREWORKS("Entity:Fireworks", 76),
    LEASH_KNOT("Entity:LeashKnot", 77),
    ARMOR_STAND("Entity:ArmorStand", 78),
    EVOKER_FANGS("Entity:EvokerFangs", 79),
    FISHING_HOOK("Entity:FishingHook", 90),
    SPECTRAL_ARROW("Entity:SpectralArrow", 91),
    DRAGON_FIREBALL("Entity:DragonFireball", 93),
    ;

    private final @NotNull String id;
    private final int typeId;

    NonLivingEntityType(@NotNull String id, int typeId) {
        this.id = id;
        this.typeId = typeId;
    }


    public @NotNull String getId() {
        return id;
    }

    public @NotNull Entity.EntityClass getEntityClass() {
        return (Entity.EntityClass) laivynpc().getVersion().getClassExec(id);
    }

    public int getTypeId() {
        return typeId;
    }

    public static boolean isLivingEntity(@NotNull Entity entity) {
        return entity.getClassExecutor().isReflectiveInstance(laivynpc().getVersion().getClassExec("EntityLiving"));
    }

    public static @NotNull NonLivingEntityType getByEntity(@NotNull Entity entity) {
        if (!isLivingEntity(entity)) {
            for (NonLivingEntityType type : NonLivingEntityType.values()) {
                // Checks if the version has this class
                if (laivynpc().getVersion().getClasses().containsKey(type.getId())) {
                    if (type.getEntityClass().isReflectiveInstance(entity)) {
                        return type;
                    }
                }
            }
        }
        throw new IllegalArgumentException("This Entity '" + entity + "' isn't registered at the NonLivingEntity's database");
    }
}