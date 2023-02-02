package codes.laivy.npc.mappings.utils.classes.entity.decoration;

import codes.laivy.npc.mappings.utils.classes.entity.Entity;
import codes.laivy.npc.mappings.utils.classes.java.BooleanObjExec;
import codes.laivy.npc.mappings.utils.classes.others.location.Vector3f;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class ArmorStand extends Entity {
    public ArmorStand(@Nullable Object value) {
        super(value);
    }

    public void setHeadPose(@NotNull Vector3f angle) {
        laivynpc().getVersion().setArmorStandPose(Pose.HEAD, this, angle);
    }
    public @NotNull Vector3f getHeadPose() {
        return laivynpc().getVersion().getArmorStandPose(Pose.HEAD, this);
    }

    public void setBodyPose(@NotNull Vector3f angle) {
        laivynpc().getVersion().setArmorStandPose(Pose.BODY, this, angle);
    }
    public @NotNull Vector3f getBodyPose() {
        return laivynpc().getVersion().getArmorStandPose(Pose.BODY, this);
    }

    public void setLeftArmPose(@NotNull Vector3f angle) {
        laivynpc().getVersion().setArmorStandPose(Pose.LEFT_ARM, this, angle);
    }
    public @NotNull Vector3f getLeftArmPose() {
        return laivynpc().getVersion().getArmorStandPose(Pose.LEFT_ARM, this);
    }

    public void setRightArmPose(@NotNull Vector3f angle) {
        laivynpc().getVersion().setArmorStandPose(Pose.RIGHT_ARM, this, angle);
    }
    public @NotNull Vector3f getRightArmPose() {
        return laivynpc().getVersion().getArmorStandPose(Pose.RIGHT_ARM, this);
    }

    public void setLeftLegPose(@NotNull Vector3f angle) {
        laivynpc().getVersion().setArmorStandPose(Pose.LEFT_LEG, this, angle);
    }
    public @NotNull Vector3f getLeftLegPose() {
        return laivynpc().getVersion().getArmorStandPose(Pose.LEFT_LEG, this);
    }

    public void setRightLegPose(@NotNull Vector3f angle) {
        laivynpc().getVersion().setArmorStandPose(Pose.RIGHT_LEG, this, angle);
    }
    public @NotNull Vector3f getRightLegPose() {
        return laivynpc().getVersion().getArmorStandPose(Pose.RIGHT_LEG, this);
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