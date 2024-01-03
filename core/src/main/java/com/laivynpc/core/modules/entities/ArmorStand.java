package com.laivynpc.core.modules.entities;

import com.laivynpc.core.modules.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;
import org.jetbrains.annotations.NotNull;

public interface ArmorStand extends Entity {
    @NotNull ItemStack getItemInHand();
    void setItemInHand(@NotNull ItemStack item);

    @NotNull ItemStack getBoots();
    void setBoots(@NotNull ItemStack item);

    @NotNull ItemStack getLeggings();
    void setLeggings(@NotNull ItemStack item);

    @NotNull ItemStack getChestplate();
    void setChestplate(@NotNull ItemStack item);

    @NotNull ItemStack getHelmet();
    void setHelmet(@NotNull ItemStack item);

    @NotNull EulerAngle getBodyPose();
    void setBodyPose(@NotNull EulerAngle pose);

    @NotNull EulerAngle getLeftArmPose();
    void setLeftArmPose(@NotNull EulerAngle pose);

    @NotNull EulerAngle getRightArmPose();
    void setRightArmPose(@NotNull EulerAngle pose);

    @NotNull EulerAngle getLeftLegPose();
    void setLeftLegPose(@NotNull EulerAngle pose);

    @NotNull EulerAngle getRightLegPose();
    void setRightLegPose(@NotNull EulerAngle pose);

    @NotNull EulerAngle getHeadPose();
    void setHeadPose(@NotNull EulerAngle pose);

    boolean hasBasePlate();
    void setBasePlate(boolean basePlate);

    boolean hasArms();
    void setArms(boolean arms);

    boolean isSmall();
    void setSmall(boolean small);
}
