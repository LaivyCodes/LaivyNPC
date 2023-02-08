package codes.laivy.npc.mappings.defaults.classes.others.location;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import org.bukkit.util.EulerAngle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Vector3f extends ObjectExecutor {
    public Vector3f(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull Vector3fClass getClassExecutor() {
        return (Vector3fClass) laivynpc().getVersion().getClassExec("Vector3f");
    }

    public float getX() {
        //noinspection DataFlowIssue
        return (float) laivynpc().getVersion().getMethodExec("Vector3f:getX").invokeInstance(this);
    }
    public float getY() {
        //noinspection DataFlowIssue
        return (float) laivynpc().getVersion().getMethodExec("Vector3f:getY").invokeInstance(this);
    }
    public float getZ() {
        //noinspection DataFlowIssue
        return (float) laivynpc().getVersion().getMethodExec("Vector3f:getZ").invokeInstance(this);
    }

    public @NotNull EulerAngle getEulerAngle() {
        return new EulerAngle(
                Math.toRadians(getX()),
                Math.toRadians(getY()),
                Math.toRadians(getZ())
        );
    }
    public static @NotNull Vector3f getByEulerAngle(@NotNull EulerAngle angle) {
        return laivynpc().getVersion().createVector3f(
                (float) Math.toDegrees(angle.getX()),
                (float) Math.toDegrees(angle.getY()),
                (float) Math.toDegrees(angle.getZ())
        );
    }

    public @NotNull Map<@NotNull String, @NotNull Object> serialize() {
        return new LinkedHashMap<String, Object>() {{
            put("x", getX());
            put("y", getY());
            put("z", getZ());
        }};
    }
    public static @NotNull Vector3f deserialize(@NotNull Map<@NotNull String, @NotNull Object> map) {
        return laivynpc().getVersion().createVector3f(((Number) map.get("x")).floatValue(), ((Number) map.get("y")).floatValue(), ((Number) map.get("z")).floatValue());
    }

    public static class Vector3fClass extends ClassExecutor {
        public Vector3fClass(@NotNull String className) {
            super(className);
        }
    }
}
