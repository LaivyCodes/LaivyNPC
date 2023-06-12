package codes.laivy.npc.utils;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class UUIDUtils {

    @ApiStatus.Internal
    public static @NotNull UUID getRandomUniqueId() {
        UUID finalUUID = null;

        int row = 0;
        w:
        while (finalUUID == null) {
            if (row > 30) {
                throw new IllegalStateException("Couldn't generate a random UUID");
            }
            row++;

            UUID tempUUID = UUID.randomUUID();
            for (World world : Bukkit.getWorlds()) {
                for (Entity entity : world.getEntities()) {
                    if (entity.getUniqueId().equals(tempUUID)) {
                        continue w;
                    }
                }
            }

            finalUUID = tempUUID;
        }

        return finalUUID;
    }

}
