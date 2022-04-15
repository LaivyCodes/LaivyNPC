package codes.laivy.npc.utils;

import codes.laivy.npc.exceptions.LaivyNPCException;

public class Validation {

    public static void notNull(Object object, RuntimeException exception) {
        if (object == null) {
            throw new LaivyNPCException(exception);
        }
    }
    public static void isTrue(boolean condition, RuntimeException exception) {
        if (condition) {
            throw new LaivyNPCException(exception);
        }
    }
    public static void isFalse(boolean condition, RuntimeException exception) {
        if (!condition) {
            throw new LaivyNPCException(exception);
        }
    }

}
