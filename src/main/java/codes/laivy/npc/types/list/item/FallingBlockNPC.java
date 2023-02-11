package codes.laivy.npc.types.list.item;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.types.EntityNPC;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static codes.laivy.npc.LaivyNPC.laivynpc;

@ApiStatus.Experimental
public class FallingBlockNPC extends EntityNPC {

    public static @NotNull FallingBlockNPC fastInstance(@NotNull List<OfflinePlayer> players, @NotNull Location location, @NotNull Object object) {
        return new FallingBlockNPC(players, Material.valueOf(object.toString()), location);
    }

    public static void debug(@NotNull Location location) {
        FallingBlockNPC fallingBlockNPC = new FallingBlockNPC(new ArrayList<>(), Material.DIAMOND_BLOCK, location);
        fallingBlockNPC.debug();
        fallingBlockNPC.destroy();
    }

    @SuppressWarnings("NullableProblems")
    private final @Nullable Material material;

    protected FallingBlockNPC(@NotNull List<OfflinePlayer> players, @NotNull Entity.EntityType type, @NotNull Location location, @NotNull Material material) {
        super(players, type, location);
        this.material = material;
    }
    public FallingBlockNPC(@NotNull List<OfflinePlayer> players, @NotNull Material material, @NotNull Location location) {
        super(players, Entity.EntityType.FALLING_BLOCK, location);
        this.material = material;
    }

    @Override
    protected @NotNull Entity getNewEntity() {
        if (material != null) {
            return laivynpc().getVersion().createFallingBlockEntity(getLocation(), material);
        } else {
            return laivynpc().getVersion().createFallingBlockEntity(getLocation(), Material.DIAMOND_BLOCK);
        }
    }
}
