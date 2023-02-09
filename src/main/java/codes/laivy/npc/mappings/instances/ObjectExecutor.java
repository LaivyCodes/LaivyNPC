package codes.laivy.npc.mappings.instances;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.utils.ClassUtils;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Array;

/**
 * Nothing special yet...
 */
@ApiStatus.Experimental
public abstract class ObjectExecutor implements Executor {

    @Override
    public boolean isLoaded() {
        return true;
    }

    @Override
    public void load() {
    }

    public static @NotNull Object[] toObjectArray(@NotNull ObjectExecutor[] objectExecutors) {
        Object[] objects = new Object[objectExecutors.length];
        int row = 0;
        for (ObjectExecutor exec : objectExecutors) {
            objects[row] = exec.getValue();
            row++;
        }
        return objects;
    }

    @NotNull
    public abstract ClassExecutor getClassExecutor();

    private Object value;

    public ObjectExecutor(@Nullable Object value) {
        setValue(value);
    }

    @Nullable
    public Object getValue() {
        return value;
    }
    public void setValue(@Nullable Object value) {
        if (value != null && !ClassUtils.isInstanceOf(getClassExecutor().getReflectionClass(), value.getClass())) {
            throw new IllegalArgumentException("The specified object '" + value.getClass().getName() + "' isn't an instance of '" + getClassExecutor().getName() + "'");
        }

        this.value = value;
    }

    @Nullable
    public Object getValueAsArray() {
        if (getValue() == null) {
            return getValue();
        }

        Object array = Array.newInstance(getClassExecutor().getReflectionClass(), 1);
        Array.set(array, 0, getValue());

        return array;
    }
}
