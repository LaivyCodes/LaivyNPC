package com.laivynpc.core.utils;

import org.jetbrains.annotations.NotNull;

import static org.bukkit.Bukkit.getServer;

public final class ReflectionUtils {

    private ReflectionUtils() {
        throw new UnsupportedOperationException();
    }

    public static @NotNull String getVersionName() {
        final String[] packageName = getServer().getClass().getPackage().getName().split("\\.");
        for (String p : packageName) if (p.contains("v1_")) {
            return p;
        }
        throw new NullPointerException("Cannot identify the version's name");
    }

}
