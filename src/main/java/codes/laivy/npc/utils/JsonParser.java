package codes.laivy.npc.utils;

import com.google.gson.JsonElement;
import org.jetbrains.annotations.NotNull;

import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JsonParser {

    public static @NotNull JsonElement parse(@NotNull String string) {
        Class<com.google.gson.JsonParser> clazz = com.google.gson.JsonParser.class;
        try {
            Method m = clazz.getMethod("parseString", String.class);
            return (JsonElement) m.invoke(null, string);
        } catch (NoSuchMethodException ignore) {
            try {
                Object parser = clazz.getConstructor().newInstance();

                Method m = clazz.getMethod("parse", String.class);
                return (JsonElement) m.invoke(parser, string);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    public static @NotNull JsonElement parse(@NotNull Reader reader) {
        Class<com.google.gson.JsonParser> clazz = com.google.gson.JsonParser.class;
        try {
            Method m = clazz.getMethod("parseReader", Reader.class);
            return (JsonElement) m.invoke(null, reader);
        } catch (NoSuchMethodException ignore) {
            try {
                Object parser = clazz.getConstructor().newInstance();

                Method m = clazz.getMethod("parse", Reader.class);
                return (JsonElement) m.invoke(parser, reader);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
