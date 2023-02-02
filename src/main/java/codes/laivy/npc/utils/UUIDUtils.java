package codes.laivy.npc.utils;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;

import java.util.UUID;

public class UUIDUtils {

    public static UUID genRandomUUID() {
        UUID finalUUID = null;

        int row = 0;
        w:
        while (finalUUID == null) {
            if (row > 5) {
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
