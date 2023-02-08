package codes.laivy.npc.mappings.defaults;

import codes.laivy.npc.mappings.defaults.classes.entity.decoration.ArmorStand;
import codes.laivy.npc.mappings.defaults.classes.others.location.Vector3f;
import org.jetbrains.annotations.NotNull;

public interface VersionVector3f {

    @NotNull Vector3f getArmorStandPose(ArmorStand.@NotNull Pose pose, @NotNull ArmorStand stand);
    void setArmorStandPose(ArmorStand.@NotNull Pose pose, @NotNull ArmorStand stand, @NotNull Vector3f vector3f);

    @NotNull Vector3f createVector3f(float x, float y, float z);

}
