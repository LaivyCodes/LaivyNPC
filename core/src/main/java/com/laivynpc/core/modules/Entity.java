package com.laivynpc.core.modules;

import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

import static com.laivynpc.core.LaivyNPC.laivynpc;

public class Entity {

    // Static initializers



    // Object

    private final @NotNull Object handle;
    private final @NotNull EntityType type;

    private Entity(@NotNull Object handle) {
        this.handle = handle;
        this.type = laivynpc().getApi().getModule().getEntiyType(handle);
    }

    public final @NotNull Object getHandle() {
        return handle;
    }

    public final @NotNull EntityType getType() {
        return type;
    }

    //
    // 1.8.X Entities
    //

    public static class DroppedItem extends Entity {
        private DroppedItem(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class ExperienceOrb extends Entity {
        private ExperienceOrb(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class LeashKnot extends Entity {
        private LeashKnot(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class Painting extends Entity {
        private Painting(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class Arrow extends Entity {
        private Arrow(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class Snowball extends Entity {
        private Snowball(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class Fireball extends Entity {
        private Fireball(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class SmallFireball extends Entity {
        private SmallFireball(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class EnderPearl extends Entity {
        private EnderPearl(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class EnderSignal extends Entity {
        private EnderSignal(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class ExperienceBottle extends Entity {
        private ExperienceBottle(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class ItemFrame extends Entity {
        private ItemFrame(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class WitherSkull extends Entity {
        private WitherSkull(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class PrimedDynamite extends Entity {
        private PrimedDynamite(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class FallingBlock extends Entity {
        private FallingBlock(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class Firework extends Entity {
        private Firework(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class ArmorStand extends Entity {
        private ArmorStand(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class Minecart extends Entity {
        private Minecart(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class MinecartCommand extends Entity {
        private MinecartCommand(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class MinecartChest extends Entity {
        private MinecartChest(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class MinecartFurnace extends Entity {
        private MinecartFurnace(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class MinecartDynamite extends Entity {
        private MinecartDynamite(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class MinecartHopper extends Entity {
        private MinecartHopper(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class MinecartMobSpawner extends Entity {
        private MinecartMobSpawner(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class Boat extends Entity {
        private Boat(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class Creeper extends Entity {
        private Creeper(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class Skeleton extends Entity {
        private Skeleton(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class Spider extends Entity {
        private Spider(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class Giant extends Entity {
        private Giant(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class Zombie extends Entity {
        private Zombie(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class Slime extends Entity {
        private Slime(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class Ghast extends Entity {
        private Ghast(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class PigZombie extends Entity {
        private PigZombie(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class Enderman extends Entity {
        private Enderman(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class CaveSpider extends Entity {
        private CaveSpider(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class Silverfish extends Entity {
        private Silverfish(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class Blaze extends Entity {
        private Blaze(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class MagmaCube extends Entity {
        private MagmaCube(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class EnderDragon extends Entity {
        private EnderDragon(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class Wither extends Entity {
        private Wither(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class Bat extends Entity {
        private Bat(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class Endermite extends Entity {
        private Endermite(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class Guardian extends Entity {
        private Guardian(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class Pig extends Entity {
        private Pig(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class Sheep extends Entity {
        private Sheep(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class Cow extends Entity {
        private Cow(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class Chicken extends Entity {
        private Chicken(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class Squid extends Entity {
        private Squid(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class Wolf extends Entity {
        private Wolf(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class MushroomCow extends Entity {
        private MushroomCow(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class Snowman extends Entity {
        private Snowman(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class Ocelot extends Entity {
        private Ocelot(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class IronGolem extends Entity {
        private IronGolem(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class Horse extends Entity {
        private Horse(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class Rabbit extends Entity {
        private Rabbit(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class Villager extends Entity {
        private Villager(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class EnderCrystal extends Entity {
        private EnderCrystal(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class SplashPotion extends Entity {
        private SplashPotion(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class Egg extends Entity {
        private Egg(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class FishingHook extends Entity {
        private FishingHook(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class Lightning extends Entity {
        private Lightning(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class Weather extends Entity {
        private Weather(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class Player extends Entity {
        private Player(@NotNull Object handle) {
            super(handle);
        }
    }
    public static class ComplexPart extends Entity {
        private ComplexPart(@NotNull Object handle) {
            super(handle);
        }
    }

    //
    // 1.8.X Entities
    //

}
