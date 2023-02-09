package codes.laivy.npc.types.list.animal.horse;

import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.AbstractHorse;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.Horse;
import codes.laivy.npc.types.AgeableEntityLivingNPC;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AbstractHorseNPC extends AgeableEntityLivingNPC {
    protected AbstractHorseNPC(@NotNull List<OfflinePlayer> players, @NotNull AbstractHorse.Type type, @NotNull Location location) {
        super(players, type.getEntityType(), location);
        if (!type.isCompatible()) {
            throw new IllegalArgumentException("This horse type '" + type.name() + "' is only compatible with '" + type.getSince().getSimpleName() + "' or higher");
        }
    }

    public @NotNull Horse.Type getType() {
        return getEntity().getType();
    }

    @Override
    public @NotNull AbstractHorse getEntity() {
        return (AbstractHorse) super.getEntity();
    }
}
