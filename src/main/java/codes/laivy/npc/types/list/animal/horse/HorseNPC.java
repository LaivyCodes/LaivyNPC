package codes.laivy.npc.types.list.animal.horse;

import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.AbstractHorse;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.horse.Horse;
import codes.laivy.npc.types.AgeableEntityLivingNPC;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class HorseNPC extends AgeableEntityLivingNPC {

    public static @NotNull HorseNPC fastInstance(@NotNull List<OfflinePlayer> players, @NotNull Location location, @NotNull Object object) {
        return new HorseNPC(players, location);
    }

    public static void debug(@NotNull Location location) {
        HorseNPC horse = new HorseNPC(new ArrayList<>(), location);
        horse.debug();
        horse.destroy();
    }

    protected HorseNPC(@NotNull List<OfflinePlayer> players, @NotNull Horse.Type type, @NotNull Location location) {
        super(players, type.getType(), location);
    }
    public HorseNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        this(players, AbstractHorse.Type.HORSE, location);
    }

    public @NotNull Horse.Type getType() {
        return getEntity().getType();
    }

    @Override
    public @NotNull Horse getEntity() {
        return (Horse) super.getEntity();
    }

    @Override
    public @NotNull Map<@NotNull String, @NotNull Object> serialize() {
        Map<String, Object> map = super.serialize();

        //noinspection unchecked
        LinkedHashMap<String, Object> section = (LinkedHashMap<String, Object>) map.get("EntityNPC Configuration");
        section.put("Data", Objects.requireNonNull(getEntityType().getData()).toString());

        return map;
    }
}
