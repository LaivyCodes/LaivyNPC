package codes.laivy.npc.mappings.defaults;

import codes.laivy.npc.mappings.defaults.classes.others.location.Vec3D;
import codes.laivy.npc.mappings.defaults.classes.others.location.Vector3f;
import org.jetbrains.annotations.NotNull;

public interface VersionLocation {

    @NotNull Vector3f createVector3f(float x, float y, float z);

}
