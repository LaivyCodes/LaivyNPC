package codes.laivy.npc.mappings.utils.classes.gameprofile;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import codes.laivy.npc.mappings.utils.classes.java.StringObjExec;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Property extends ObjectExecutor {

    @NotNull
    public static Property createTextureProperty(@NotNull String texture, @NotNull String signature) {
        return new Property(laivynpc().getVersion().getClassExec("Property").getConstructor(ClassExecutor.STRING, ClassExecutor.STRING, ClassExecutor.STRING).newInstance(new StringObjExec("textures"), new StringObjExec(texture), new StringObjExec(signature)));
    }

    // ---/-/--- //

    public Property(@Nullable Object value) {
        super(value);
    }

    @Override
    public @NotNull PropertyClass getClassExecutor() {
        return (PropertyClass) laivynpc().getVersion().getClassExec("Property");
    }

    public static class PropertyClass extends ClassExecutor {
        public PropertyClass(@NotNull String className) {
            super(className);
        }
    }
}
