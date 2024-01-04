package com.laivynpc.module;

import com.laivynpc.core.modules.Entity;
import com.laivynpc.core.modules.entities.*;
import net.minecraft.server.v1_8_R1.*;
import org.bukkit.Location;
import org.bukkit.Rotation;
import org.bukkit.craftbukkit.v1_8_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_8_R1.util.CraftMagicNumbers;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.util.EulerAngle;
import org.jetbrains.annotations.NotNull;

class EntityCreator {

    public static @NotNull Entity create(@NotNull Location location, @NotNull EntityType type) {
        @NotNull World world = ((CraftWorld) location.getWorld()).getHandle();
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();

        if (type.equals(EntityType.DROPPED_ITEM)) {
            @NotNull EntityItem handle = new EntityItem(world, x, y, z);

            return new DroppedItem() {
                @Override
                public @NotNull ItemStack getItemStack() {
                    return CraftItemStack.asBukkitCopy(handle.getItemStack());
                }
                @Override
                public void setItemStack(@NotNull ItemStack item) {
                    handle.setItemStack(CraftItemStack.asNMSCopy(item));
                }
                @Override
                public @NotNull Object getHandle() {
                    return handle;
                }
                @Override
                public @NotNull EntityType getType() {
                    return type;
                }
            };
        } else if (type.equals(EntityType.EXPERIENCE_ORB)) {
            @NotNull EntityExperienceOrb handle = new EntityExperienceOrb(world, x, y, z, 1);
            return new ExperienceOrb() {
                @Override
                public int getExperience() {
                    return handle.value;
                }
                @Override
                public void setExperience(int experience) {
                    handle.value = experience;
                }
                @Override
                public @NotNull Object getHandle() {
                    return handle;
                }
                @Override
                public @NotNull EntityType getType() {
                    return type;
                }
            };
        } else if (type.equals(EntityType.LEASH_HITCH)) {
            @NotNull EntityLeash handle = new EntityLeash(world, new BlockPosition(x, y, z));
            return new LeashKnot() {
                @Override
                public @NotNull Object getHandle() {
                    return handle;
                }
                @Override
                public @NotNull EntityType getType() {
                    return type;
                }
            };
        } else if (type.equals(EntityType.PAINTING)) {
            // todo: test this
            double pitchRad = Math.toRadians(location.getPitch());
            double yawRad = Math.toRadians(location.getYaw());
            double angle = Math.atan2(Math.sin(yawRad), Math.cos(yawRad) * Math.cos(pitchRad));

            angle = Math.toDegrees(angle);

            @NotNull EntityPainting handle = new EntityPainting(world, new BlockPosition(x, y, z), EnumDirection.fromAngle(angle));
            return new LeashKnot() {
                @Override
                public @NotNull Object getHandle() {
                    return handle;
                }
                @Override
                public @NotNull EntityType getType() {
                    return type;
                }
            };
        } else if (type.equals(EntityType.ARROW)) {
            @NotNull EntityArrow handle = new EntityArrow(world, x, y, z);
            return new Arrow() {
                @Override
                public @NotNull Object getHandle() {
                    return handle;
                }
                @Override
                public @NotNull EntityType getType() {
                    return type;
                }
            };
        } else if (type.equals(EntityType.SNOWBALL)) {
            @NotNull EntitySnowball handle = new EntitySnowball(world, x, y, z);
            return new Snowball() {
                @Override
                public @NotNull Object getHandle() {
                    return handle;
                }
                @Override
                public @NotNull EntityType getType() {
                    return type;
                }
            };
        } else if (type.equals(EntityType.FIREBALL)) {
            @NotNull EntityFireball handle = new EntityLargeFireball(world);
            handle.setPosition(x, y, z);
            handle.setPositionRotation(x, y, z, location.getYaw(), location.getPitch());
            return new Fireball() {
                @Override
                public @NotNull Object getHandle() {
                    return handle;
                }
                @Override
                public @NotNull EntityType getType() {
                    return type;
                }
            };
        } else if (type.equals(EntityType.SMALL_FIREBALL)) {
            @NotNull EntityFireball handle = new EntitySmallFireball(world);
            handle.setPosition(x, y, z);
            handle.setPositionRotation(x, y, z, location.getYaw(), location.getPitch());
            return new SmallFireball() {
                @Override
                public @NotNull Object getHandle() {
                    return handle;
                }
                @Override
                public @NotNull EntityType getType() {
                    return type;
                }
            };
        } else if (type.equals(EntityType.ENDER_PEARL)) {
            @NotNull EntityEnderPearl handle = new EntityEnderPearl(world);
            handle.setPosition(x, y, z);
            handle.setPositionRotation(x, y, z, location.getYaw(), location.getPitch());
            return new EnderPearl() {
                @Override
                public @NotNull Object getHandle() {
                    return handle;
                }
                @Override
                public @NotNull EntityType getType() {
                    return type;
                }
            };
        } else if (type.equals(EntityType.ENDER_SIGNAL)) {
            @NotNull EntityEnderSignal handle = new EntityEnderSignal(world, x, y, z);
            return new EnderSignal() {
                @Override
                public @NotNull Object getHandle() {
                    return handle;
                }
                @Override
                public @NotNull EntityType getType() {
                    return type;
                }
            };
        } else if (type.equals(EntityType.THROWN_EXP_BOTTLE)) {
            @NotNull EntityThrownExpBottle handle = new EntityThrownExpBottle(world, x, y, z);
            return new ThrownExpBottle() {
                @Override
                public @NotNull Object getHandle() {
                    return handle;
                }
                @Override
                public @NotNull EntityType getType() {
                    return type;
                }
            };
        } else if (type.equals(EntityType.ITEM_FRAME)) {
            // todo: test this
            double pitchRad = Math.toRadians(location.getPitch());
            double yawRad = Math.toRadians(location.getYaw());
            double angle = Math.atan2(Math.sin(yawRad), Math.cos(yawRad) * Math.cos(pitchRad));

            angle = Math.toDegrees(angle);

            @NotNull EntityItemFrame handle = new EntityItemFrame(world, new BlockPosition(x, y, z), EnumDirection.fromAngle(angle));
            return new ItemFrame() {
                @Override
                public @NotNull ItemStack getItemStack() {
                    return CraftItemStack.asBukkitCopy(handle.getItem());
                }

                @Override
                public void setItemStack(@NotNull ItemStack item) {
                    handle.setItem(CraftItemStack.asNMSCopy(item));
                }

                @Override
                public @NotNull Rotation getRotation() {
                    int index = handle.getRotation();

                    if (index == 0) return Rotation.NONE;
                    else if (index == 1) return Rotation.CLOCKWISE_45;
                    else if (index == 2) return Rotation.CLOCKWISE;
                    else if (index == 3) return Rotation.CLOCKWISE_135;
                    else if (index == 4) return Rotation.FLIPPED;
                    else if (index == 5) return Rotation.FLIPPED_45;
                    else if (index == 6) return Rotation.COUNTER_CLOCKWISE;
                    else if (index == 7) return Rotation.COUNTER_CLOCKWISE_45;
                    else throw new NullPointerException("unknown rotation index '" + index + "'");
                }

                @Override
                public void setRotation(@NotNull Rotation rotation) {
                    int index;

                    if (rotation == Rotation.NONE) index = 0;
                    else if (rotation == Rotation.CLOCKWISE_45) index = 1;
                    else if (rotation == Rotation.CLOCKWISE) index = 2;
                    else if (rotation == Rotation.CLOCKWISE_135) index = 3;
                    else if (rotation == Rotation.FLIPPED) index = 4;
                    else if (rotation == Rotation.FLIPPED_45) index = 5;
                    else if (rotation == Rotation.COUNTER_CLOCKWISE) index = 6;
                    else if (rotation == Rotation.COUNTER_CLOCKWISE_45) index = 7;
                    else throw new NullPointerException("unknown rotation '" + rotation + "'");

                    handle.setRotation(index);
                }

                @Override
                public @NotNull Object getHandle() {
                    return handle;
                }
                @Override
                public @NotNull EntityType getType() {
                    return type;
                }
            };
        } else if (type.equals(EntityType.WITHER_SKULL)) {
            @NotNull EntityWitherSkull handle = new EntityWitherSkull(world);
            handle.setLocation(x, y, z, location.getYaw(), location.getPitch());
            return new WitherSkull() {
                @Override
                public boolean isCharged() {
                    return handle.isCharged();
                }

                @Override
                public void setCharged(boolean charged) {
                    handle.setCharged(charged);
                }

                @Override
                public @NotNull Object getHandle() {
                    return handle;
                }
                @Override
                public @NotNull EntityType getType() {
                    return type;
                }
            };
        } else if (type.equals(EntityType.PRIMED_TNT)) {
            @NotNull EntityTNTPrimed handle = new EntityTNTPrimed(world, x, y, z, null);
            handle.setLocation(x, y, z, location.getYaw(), location.getPitch());
            return new PrimedDynamite() {
                @Override
                public int getFuseTicks() {
                    return handle.fuseTicks;
                }

                @Override
                public void setFuseTicks(int ticks) {
                    handle.fuseTicks = ticks;
                }

                @Override
                public @NotNull Object getHandle() {
                    return handle;
                }
                @Override
                public @NotNull EntityType getType() {
                    return type;
                }
            };
        } else if (type.equals(EntityType.FALLING_BLOCK)) {
            @NotNull EntityFallingBlock handle = new EntityFallingBlock(world, x, y, z, CraftMagicNumbers.getBlock(org.bukkit.Material.STONE).getBlockData());
            handle.setLocation(x, y, z, location.getYaw(), location.getPitch());
            return new FallingBlock() {
                @Override
                public @NotNull org.bukkit.Material getMaterial() {
                    return CraftMagicNumbers.getMaterial(handle.block.getBlock());
                }
                @Override
                public void setMaterial(@NotNull org.bukkit.Material material) {
                    handle.block = CraftMagicNumbers.getBlock(material).getBlockData();
                }
                @Override
                public @NotNull Object getHandle() {
                    return handle;
                }
                @Override
                public @NotNull EntityType getType() {
                    return type;
                }
            };
        } else if (type.equals(EntityType.FIREWORK)) {
            // todo: test this
            @NotNull EntityFireworks handle = new EntityFireworks(world, x, y, z, CraftItemStack.asNMSCopy(new ItemStack(org.bukkit.Material.FIREWORK_CHARGE)));
            handle.setLocation(x, y, z, location.getYaw(), location.getPitch());
            // todo: fireworks
            return new Firework() {
                @Override
                public @NotNull FireworkMeta getFireworkMeta() {
                    return null;
                }

                @Override
                public void setFireworkMeta(@NotNull FireworkMeta meta) {

                }

                @Override
                public void detonate() {

                }

                @Override
                public @NotNull Object getHandle() {
                    return handle;
                }
                @Override
                public @NotNull EntityType getType() {
                    return type;
                }
            };
        } else if (type.equals(EntityType.ARMOR_STAND)) {
            @NotNull EntityArmorStand handle = new EntityArmorStand(world, x, y, z);
            handle.setLocation(x, y, z, location.getYaw(), location.getPitch());
            return new ArmorStand() {
                @Override
                public @NotNull ItemStack getItemInHand() {
                }

                @Override
                public void setItemInHand(@NotNull ItemStack item) {
                }

                @Override
                public @NotNull ItemStack getBoots() {
                }

                @Override
                public void setBoots(@NotNull ItemStack item) {

                }

                @Override
                public @NotNull ItemStack getLeggings() {
                    return null;
                }

                @Override
                public void setLeggings(@NotNull ItemStack item) {

                }

                @Override
                public @NotNull ItemStack getChestplate() {
                    return null;
                }

                @Override
                public void setChestplate(@NotNull ItemStack item) {

                }

                @Override
                public @NotNull ItemStack getHelmet() {
                    return null;
                }

                @Override
                public void setHelmet(@NotNull ItemStack item) {

                }

                @Override
                public @NotNull EulerAngle getBodyPose() {
                    return handle.;
                }

                @Override
                public void setBodyPose(@NotNull EulerAngle pose) {

                }

                @Override
                public @NotNull EulerAngle getLeftArmPose() {
                    return handle.leftArmPose;
                }

                @Override
                public void setLeftArmPose(@NotNull EulerAngle pose) {

                }

                @Override
                public @NotNull EulerAngle getRightArmPose() {
                    return null;
                }

                @Override
                public void setRightArmPose(@NotNull EulerAngle pose) {

                }

                @Override
                public @NotNull EulerAngle getLeftLegPose() {
                    return null;
                }

                @Override
                public void setLeftLegPose(@NotNull EulerAngle pose) {

                }

                @Override
                public @NotNull EulerAngle getRightLegPose() {
                    return null;
                }

                @Override
                public void setRightLegPose(@NotNull EulerAngle pose) {

                }

                @Override
                public @NotNull EulerAngle getHeadPose() {
                    return null;
                }

                @Override
                public void setHeadPose(@NotNull EulerAngle pose) {

                }

                @Override
                public boolean hasBasePlate() {
                    return false;
                }

                @Override
                public void setBasePlate(boolean basePlate) {

                }

                @Override
                public boolean hasArms() {
                    return false;
                }

                @Override
                public void setArms(boolean arms) {

                }

                @Override
                public boolean isSmall() {
                    return false;
                }

                @Override
                public void setSmall(boolean small) {

                }

                @Override
                public @NotNull Object getHandle() {
                    return handle;
                }
                @Override
                public @NotNull EntityType getType() {
                    return type;
                }
            };
        }
    }

}
