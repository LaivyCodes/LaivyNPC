package codes.laivy.npc.mappings.defaults.classes.entity.decoration;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.java.BooleanObjExec;
import codes.laivy.npc.mappings.defaults.classes.others.location.Vector3f;
import org.bukkit.util.EulerAngle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class ArmorStand extends Entity {
    public ArmorStand(@Nullable Object value) {
        super(value);
    }

    public void setHeadPose(@NotNull EulerAngle angle) {
        laivynpc().getVersion().setArmorStandPose(Pose.HEAD, this, Vector3f.getByEulerAngle(angle));
    }
    public @NotNull EulerAngle getHeadPose() {
        return laivynpc().getVersion().getArmorStandPose(Pose.HEAD, this).getEulerAngle();
    }

    public void setBodyPose(@NotNull EulerAngle angle) {
        laivynpc().getVersion().setArmorStandPose(Pose.BODY, this, Vector3f.getByEulerAngle(angle));
    }
    public @NotNull EulerAngle getBodyPose() {
        return laivynpc().getVersion().getArmorStandPose(Pose.BODY, this).getEulerAngle();
    }

    public void setLeftArmPose(@NotNull EulerAngle angle) {
        laivynpc().getVersion().setArmorStandPose(Pose.LEFT_ARM, this, Vector3f.getByEulerAngle(angle));
    }
    public @NotNull EulerAngle getLeftArmPose() {
        return laivynpc().getVersion().getArmorStandPose(Pose.LEFT_ARM, this).getEulerAngle();
    }

    public void setRightArmPose(@NotNull EulerAngle angle) {
        laivynpc().getVersion().setArmorStandPose(Pose.RIGHT_ARM, this, Vector3f.getByEulerAngle(angle));
    }
    public @NotNull EulerAngle getRightArmPose() {
        return laivynpc().getVersion().getArmorStandPose(Pose.RIGHT_ARM, this).getEulerAngle();
    }

    public void setLeftLegPose(@NotNull EulerAngle angle) {
        laivynpc().getVersion().setArmorStandPose(Pose.LEFT_LEG, this, Vector3f.getByEulerAngle(angle));
    }
    public @NotNull EulerAngle getLeftLegPose() {
        return laivynpc().getVersion().getArmorStandPose(Pose.LEFT_LEG, this).getEulerAngle();
    }

    public void setRightLegPose(@NotNull EulerAngle angle) {
        laivynpc().getVersion().setArmorStandPose(Pose.RIGHT_LEG, this, Vector3f.getByEulerAngle(angle));
    }
    public @NotNull EulerAngle getRightLegPose() {
        return laivynpc().getVersion().getArmorStandPose(Pose.RIGHT_LEG, this).getEulerAngle();
    }

    public void setBasePlate(boolean flag) {
        laivynpc().getVersion().getMethodExec("Entity:ArmorStand:setBasePlate").invokeInstance(this, new BooleanObjExec(flag));
    }
    public boolean hasBasePlate() {
        //noinspection ConstantConditions
        return (boolean) laivynpc().getVersion().getMethodExec("Entity:ArmorStand:hasBasePlate").invokeInstance(this);
    }

    public void setArms(boolean flag) {
        laivynpc().getVersion().getMethodExec("Entity:ArmorStand:setArms").invokeInstance(this, new BooleanObjExec(flag));
    }
    public boolean hasArms() {
        //noinspection ConstantConditions
        return (boolean) laivynpc().getVersion().getMethodExec("Entity:ArmorStand:hasArms").invokeInstance(this);
    }

    public void setSmall(boolean flag) {
        laivynpc().getVersion().getMethodExec("Entity:ArmorStand:setSmall").invokeInstance(this, new BooleanObjExec(flag));
    }
    public boolean isSmall() {
        //noinspection ConstantConditions
        return (boolean) laivynpc().getVersion().getMethodExec("Entity:ArmorStand:isSmall").invokeInstance(this);
    }

    @Override
    public @NotNull ArmorStandClass getClassExecutor() {
        return (ArmorStandClass) laivynpc().getVersion().getClassExec("Entity:ArmorStand");
    }

    public static class ArmorStandClass extends Entity.EntityClass {
        public ArmorStandClass(@NotNull String className) {
            super(className);
        }
    }

    public enum Pose {
        HEAD,
        BODY,
        LEFT_ARM,
        RIGHT_ARM,
        LEFT_LEG,
        RIGHT_LEG
        ;
    }

}