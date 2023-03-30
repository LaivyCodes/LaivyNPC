package codes.laivy.npc.mappings.instances;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.defaults.classes.java.EnumObjExec;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;

public class EnumExecutor extends ClassExecutor {

    private final Map<String, EnumObjExec> enums = new LinkedHashMap<>();
    private boolean loaded = false;

    public EnumExecutor(@Nullable ClassExecutor classExecutor) {
        super(classExecutor != null ? classExecutor.getReflectionClass().getName() : "");
    }

    @NotNull
    public Map<String, EnumObjExec> getEnums() {
        if (!isLoaded()) {
            throw new IllegalStateException("This EnumExecutor isn't loaded yet.");
        }

        return enums;
    }

    public @NotNull EnumObjExec[] values() {
        return getEnums().values().toArray(new EnumObjExec[0]);
    }

    public @NotNull EnumObjExec valueOf(@NotNull String name) {
        if (getEnums().containsKey(name)) {
            return getEnums().get(name);
        } else {
            throw new IllegalArgumentException("Couldn't find a enum with that name '" + name + "'");
        }
    }

    @Override
    public void load() {
        if (isLoaded()) {
            return;
        }
        if (!super.isLoaded()) {
            super.load();
        }

        try {
            loaded = true;
            if (Enum.class.isAssignableFrom(this.getReflectionClass())) {
                MethodExecutor method = new MethodExecutor(this, ClassExecutor.ENUM_ARRAY, "values", "Gets the enum values");
                method.load();

                Object enumValues = method.invokeStatic();

                if (enumValues == null) {
                    throw new NullPointerException("Cannot get enum class values");
                }

                Enum<?>[] enums = (Enum<?>[]) enumValues;

                for (Enum<?> fEnum : enums) {
                    getEnums().put(fEnum.name(), new EnumObjExec(fEnum));
                }
            } else {
                throw new IllegalArgumentException("This class executor '" + this.getName() + "' isn't a Enum!");
            }
        } catch (Throwable e) {
            loaded = false;
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isLoaded() {
        return super.isLoaded() && loaded;
    }
}
