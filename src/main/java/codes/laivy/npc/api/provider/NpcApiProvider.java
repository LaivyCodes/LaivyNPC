package codes.laivy.npc.api.provider;

import codes.laivy.npc.LaivyNPC;
import codes.laivy.npc.api.NpcApi;
import codes.laivy.npc.modules.Module;
import codes.laivy.npc.utils.ModuleUtils;
import codes.laivy.npc.utils.ReflectionUtils;
import codes.laivy.npc.utils.Version;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnknownNullability;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarFile;
import java.util.regex.Pattern;

public class NpcApiProvider implements NpcApi {

    private final @NotNull LaivyNPC plugin;
    private final @NotNull Version version;

    private @UnknownNullability Module[] modules; // Loaded modules
    private @UnknownNullability Module module;

    @ApiStatus.Internal
    public boolean loaded = false;

    public NpcApiProvider(@NotNull LaivyNPC plugin, @NotNull Version version) {
        this.plugin = plugin;
        this.version = version;
    }
    public NpcApiProvider(@NotNull LaivyNPC plugin) {
        this.plugin = plugin;

        // Version properties
        @NotNull Pattern pattern = Pattern.compile("^0+(?!$)");
        @NotNull String[] versionParts = getPlugin().getDescription().getVersion().split("\\.");
        if (versionParts.length != 2) {
            throw new IllegalArgumentException("Cannot retrieve version");
        } else if (versionParts[0].matches(pattern.pattern()) || versionParts[1].matches(pattern.pattern())) {
            throw new IllegalArgumentException("Unknown version major/minor format");
        }

        this.version = Version.of("https://laivynpc.com/", Integer.parseInt(versionParts[0]), Integer.parseInt(versionParts[1]));

        // Folders
        @NotNull File[] files = new File[] {
                getPlugin().getDataFolder(),
                new File(getPlugin().getDataFolder(), "modules")
        };

        for (@NotNull File file : files) {
            if (!file.exists() && !file.mkdirs()) {
                throw new IllegalStateException("Cannot create \"" + file + "\" folder");
            }
        }

        // Modules loading
        @NotNull Set<Module> moduleSet = new LinkedHashSet<>();
        for (File file : ModuleUtils.listJarFiles(files[1])) {
            try {
                @NotNull JarFile jarFile = new JarFile(file);
                @NotNull Module module = ModuleUtils.getModule(jarFile);

                moduleSet.add(module);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        this.modules = moduleSet.toArray(new Module[0]);
        // Modules configuration
        @NotNull String version = ReflectionUtils.getVersionName();
        for (Module module : this.modules) {
            if (Arrays.stream(module.getCompatibleVersions()).anyMatch(v -> v.equalsIgnoreCase(version))) {
                this.module = module;
            }
        }

        if (this.module == null) {
            // TODO: Download from Metadotis servers a new compatible version
            throw new IllegalStateException("Incompatible version!");
        }
    }

    public @NotNull LaivyNPC getPlugin() {
        return plugin;
    }

    @Override
    public @NotNull Version getVersion() {
        return this.version;
    }

    public @NotNull Module @NotNull [] getModules() {
        if (module == null) {
            throw new IllegalStateException("The LaivyNPC  api aren't loaded.");
        }

        return modules;
    }

    @Override
    public @Nullable Module getModule() {
        return module;
    }

    @Override
    public void load() {
        loaded = true;
    }

    @Override
    public void unload() {
        loaded = false;
    }

    @Override
    public boolean isLoaded() {
        return loaded;
    }
}
