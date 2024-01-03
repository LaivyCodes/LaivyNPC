package com.laivynpc.core.modules;

import com.laivynpc.core.utils.Version;
import org.bukkit.Art;
import org.bukkit.DyeColor;
import org.bukkit.Rotation;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Skeleton.SkeletonType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.util.EulerAngle;
import org.jetbrains.annotations.NotNull;

/**
 * The Module interface is responsible for translating inventories, signs, chats, items, and entity names.
 */
public interface Module {

    @NotNull ClassLoader getClassLoader();

    @NotNull String @NotNull [] getCompatibleVersions();

    @NotNull Version getVersion();

    @NotNull EntityType getEntiyType(@NotNull Object object);

    interface DroppedItem {
        @NotNull ItemStack getItemStack(@NotNull Entity.DroppedItem entity);
        void setItemStack(@NotNull Entity.DroppedItem entity, @NotNull ItemStack item);
    }
    interface ExperienceOrb {
        int getExperience(@NotNull Entity.ExperienceOrb entity);
        void setExperience(@NotNull Entity.ExperienceOrb entity, int experience);
    }
    interface LeashKnot {
    }
    interface Painting {
        @NotNull Art getArt(@NotNull Entity.Painting entity);
        boolean setArt(@NotNull Entity.Painting entity, @NotNull Art art);
    }
    interface Arrow {
    }
    interface Snowball {
    }
    interface Fireball {
    }
    interface SmallFireball {
    }
    interface EnderPearl {
    }
    interface EnderSignal {
    }
    interface ThrownExpBottle {
    }
    interface ItemFrame {
        @NotNull ItemStack getItemStack(@NotNull Entity.ItemFrame entity);
        void setItemStack(@NotNull Entity.ItemFrame entity, @NotNull ItemStack item);

        @NotNull Rotation getRotation();
        void setRotation(@NotNull Entity.ItemFrame entity, @NotNull Rotation rotation);
    }
    interface WitherSkull {
        boolean isCharged();
        void setCharged(boolean charged);
    }
    interface PrimedDynamite {
    }
    interface FallingBlock {
        @NotNull MaterialData getMaterial();
        void setMaterial(@NotNull MaterialData material);
    }
    interface Firework {
        @NotNull FireworkMeta getFireworkMeta();
        void setFireworkMeta(@NotNull FireworkMeta meta);
        void detonate();
    }
    interface ArmorStand {
        @NotNull ItemStack getItemInHand(@NotNull Entity.ArmorStand entity);
        void setItemInHand(@NotNull Entity.ArmorStand entity, @NotNull ItemStack item);

        @NotNull ItemStack getBoots(@NotNull Entity.ArmorStand entity);
        void setBoots(@NotNull Entity.ArmorStand entity, @NotNull ItemStack item);

        @NotNull ItemStack getLeggings(@NotNull Entity.ArmorStand entity);
        void setLeggings(@NotNull Entity.ArmorStand entity, @NotNull ItemStack item);

        @NotNull ItemStack getChestplate(@NotNull Entity.ArmorStand entity);
        void setChestplate(@NotNull Entity.ArmorStand entity, @NotNull ItemStack item);

        @NotNull ItemStack getHelmet(@NotNull Entity.ArmorStand entity);
        void setHelmet(@NotNull Entity.ArmorStand entity, @NotNull ItemStack item);

        @NotNull EulerAngle getBodyPose(@NotNull Entity.ArmorStand entity);
        void setBodyPose(@NotNull Entity.ArmorStand entity, @NotNull EulerAngle pose);

        @NotNull EulerAngle getLeftArmPose(@NotNull Entity.ArmorStand entity);
        void setLeftArmPose(@NotNull Entity.ArmorStand entity, @NotNull EulerAngle pose);

        @NotNull EulerAngle getRightArmPose(@NotNull Entity.ArmorStand entity);
        void setRightArmPose(@NotNull Entity.ArmorStand entity, @NotNull EulerAngle pose);

        @NotNull EulerAngle getLeftLegPose(@NotNull Entity.ArmorStand entity);
        void setLeftLegPose(@NotNull Entity.ArmorStand entity, @NotNull EulerAngle pose);

        @NotNull EulerAngle getRightLegPose(@NotNull Entity.ArmorStand entity);
        void setRightLegPose(@NotNull Entity.ArmorStand entity, @NotNull EulerAngle pose);

        @NotNull EulerAngle getHeadPose(@NotNull Entity.ArmorStand entity);
        void setHeadPose(@NotNull Entity.ArmorStand entity, @NotNull EulerAngle pose);

        boolean hasBasePlate(@NotNull Entity.ArmorStand entity);
        void setBasePlate(@NotNull Entity.ArmorStand entity, boolean basePlate);

        boolean hasArms(@NotNull Entity.ArmorStand entity);
        void setArms(@NotNull Entity.ArmorStand entity, boolean arms);

        boolean isSmall(@NotNull Entity.ArmorStand entity);
        void setSmall(@NotNull Entity.ArmorStand entity, boolean small);
    }
    interface MinecartCommand {
    }
    interface Minecart {
    }
    interface MinecartChest {
    }
    interface MinecartFurnace {
    }
    interface MinecartDynamite {
    }
    interface MinecartHopper {
    }
    interface MinecraftMobSpawner {
    }
    interface Creeper {
        boolean isPowered(@NotNull Entity.Creeper entity);
        void setPowered(@NotNull Entity.Creeper entity, boolean powered);
    }
    interface Skeleton {
        @NotNull SkeletonType getSkeletonType(@NotNull Entity.Skeleton entity);
        void setSkeletonType(@NotNull Entity.Skeleton entity, @NotNull SkeletonType type);
    }
    interface Spider {
    }
    interface Giant {
    }
    interface Zombie {
        boolean isBaby(@NotNull Entity.Zombie entity);
        void setBaby(@NotNull Entity.Zombie entity, boolean baby);

        boolean isVillager(@NotNull Entity.Zombie entity);
        boolean setVillager(@NotNull Entity.Zombie entity, boolean villager);
    }
    interface Slime {
        int getSize(@NotNull Entity.Slime entity);
        void setSize(@NotNull Entity.Slime entity, int size);
    }
    interface PigZombie {
        boolean isAngry(@NotNull Entity.PigZombie entity);
        boolean setAngry(@NotNull Entity.PigZombie entity, boolean angry);
    }
    interface Enderman {
        @NotNull MaterialData getCarry(@NotNull Entity.Enderman entity);
        void setCarry(@NotNull Entity.Enderman entity, @NotNull MaterialData carry);
    }
    interface CaveSpider {
    }
    interface Silverfish {
    }
    interface Blaze {
    }
    interface MagmaCube {
    }
    interface EnderDragon {
    }
    interface Wither {
    }
    interface Bat {
        boolean isAwake();
        void setAwake(boolean awake);
    }
    interface Witch {
    }
    interface Endermite {
    }
    interface Guardian {
        boolean isElder();
        void setElder(boolean elder);
    }
    interface Pig {
        boolean hasSaddle();
        void setSaddle(boolean saddle);
    }
    interface Sheep {
        boolean isSheared();
        void setSheared(boolean sheared);
    }
    interface Cow {
    }
    interface Chicken {
    }
    interface Squid {
    }
    interface Wolf {
        boolean isAngry();
        void setAngry(boolean angry);

        boolean isSitting();
        void setSitting(boolean sitting);

        @NotNull DyeColor getCollarColor();
        void setCollarColor(@NotNull DyeColor color);
    }
    interface MushroomCow {
    }
    interface Snowman {
    }
    interface Ocelot {
        @NotNull org.bukkit.entity.Ocelot.Type getCatType();
        void setCatType(@NotNull org.bukkit.entity.Ocelot.Type type);

        boolean isSitting();
        void setSitting(boolean sitting);
    }
    interface Snowman {
    }

}
