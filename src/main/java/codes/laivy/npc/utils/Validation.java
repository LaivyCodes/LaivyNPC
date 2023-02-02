package codes.laivy.npc.utils;

public class Validation {

    public static void notNull(Object object, RuntimeException exception) {
        if (object == null) {
            throw exception;
        }
    }
    public static void isTrue(boolean condition, RuntimeException exception) {
        if (condition) {
            throw exception;
        }
    }

}
