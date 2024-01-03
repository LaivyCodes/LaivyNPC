package com.laivynpc.core.utils;

import com.laivynpc.core.LaivyNPC;
import com.laivynpc.core.modules.Module;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.jar.JarFile;

public class ModuleUtils {

    private ModuleUtils() {
        throw new UnsupportedOperationException();
    }

    public static @NotNull String getMainClass(@NotNull JarFile file) {
        try {
            // TODO: Verify this
            return file.getManifest().getMainAttributes().getValue("Main-Class");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static @NotNull Module getModule(@NotNull JarFile file) {
        try {
            @NotNull String mainClassName = getMainClass(file);
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{ new URL("file:" + file.getName()) }, LaivyNPC.class.getClassLoader());
            Class<?> mainClass = classLoader.loadClass(mainClassName);

            if (Module.class.isAssignableFrom(mainClass)) {
                //noinspection unchecked
                @NotNull Class<Module> moduleClass = (Class<Module>) mainClass;
                return moduleClass.getConstructor(ClassLoader.class).newInstance(classLoader);
            } else {
                throw new RuntimeException("The main class of module jar file '" + file.getName() + "' hasn't an instance of '" + Module.class.getName() + "'");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static @NotNull File @NotNull [] listJarFiles(@NotNull File folder) {
        @NotNull List<File> jars = new LinkedList<>();

        if (folder.exists() && folder.isDirectory()) {
            @NotNull File @NotNull [] directoryFiles = Objects.requireNonNull(folder.listFiles());

            for (File file : directoryFiles) {
                if (file.isFile() && file.getName().toLowerCase().endsWith(".jar")) {
                    jars.add(file);
                }
            }
        } else if (!folder.isDirectory()) {
            throw new IllegalArgumentException("The file '" + folder + "' isn't a folder!");
        }

        return jars.toArray(new File[0]);
    }

}
