package codes.laivy.npc.types.list.animal;

import codes.laivy.npc.mappings.utils.classes.entity.animal.horse.Horse;
import codes.laivy.npc.types.AgeableEntityLivingNPC;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class HorseNPC extends AgeableEntityLivingNPC {

    public static @NotNull HorseNPC fastInstance(@NotNull List<OfflinePlayer> player, @NotNull Location location, @NotNull Object object) {
        Horse.Type type;
        type = Horse.Type.valueOf(object.toString().toUpperCase());

        return new HorseNPC(player, type, location);
    }

    public static void debug(@NotNull Location location) {
        HorseNPC horse = new HorseNPC(new ArrayList<>(), Horse.Type.HORSE, location);
        horse.debug();
        horse.destroy();
        horse = new HorseNPC(new ArrayList<>(), Horse.Type.DONKEY, location);
        horse.debug();
        horse.destroy();
        horse = new HorseNPC(new ArrayList<>(), Horse.Type.MULE, location);
        horse.debug();
        horse.destroy();
        horse = new HorseNPC(new ArrayList<>(), Horse.Type.ZOMBIE, location);
        horse.debug();
        horse.destroy();
        horse = new HorseNPC(new ArrayList<>(), Horse.Type.SKELETON, location);
        horse.debug();
        horse.destroy();
    }

    public HorseNPC(@NotNull List<OfflinePlayer> players, @NotNull Horse.Type type, @NotNull Location location) {
        super(players, type.getType(), location);
    }

    public @NotNull Horse.Type getType() {
        return getEntity().getType();
    }
    public void setType(@NotNull Horse.Type type) {
        getEntity().setType(type);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
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
