package codes.laivy.npc.types.workers;

import codes.laivy.npc.mappings.utils.classes.entity.Entity;
import codes.laivy.npc.mappings.utils.classes.entity.decoration.ArmorStand;
import codes.laivy.npc.mappings.utils.classes.packets.EntityDestroyPacket;
import codes.laivy.npc.mappings.utils.classes.packets.Packet;
import codes.laivy.npc.types.NPC;
import codes.laivy.npc.types.utils.NPCHologramText;
import codes.laivy.npc.utils.Validation;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.MemorySection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.util.*;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class NPCHolograms {

    private double distanceBetweenHolograms = 0.26D;
    private double distanceFromNPC = 0.3D;

    private final @NotNull Map<@NotNull Integer, @NotNull NPCHologramText> npcHologramTextMap = new HashMap<>();

    private final @NotNull NPC npc;

    public NPCHolograms(@NotNull NPC npc) {
        this.npc = npc;
    }

    public @NotNull Set<Integer> getLines() {
        return npcHologramTextMap.keySet();
    }

    public boolean hasLine(int line) {
        return npcHologramTextMap.containsKey(line);
    }
    public @Nullable NPCHologramText getLine(int line) {
        if (!npcHologramTextMap.containsKey(line)) {
            return null;
        }

        return npcHologramTextMap.get(line);
    }
    public void setLine(@Range(from = 0, to = 999) int line, @Nullable NPCHologramText hologramText) {
        removeLine(line);
        if (hologramText == null) {
            return;
        }

        Validation.isTrue(hologramText.getNPC() != getNPC(), new IllegalArgumentException("This hologram's NPC aren't the same of the NPC's controller"));

        npcHologramTextMap.put(line, hologramText);

        getNPC().sendUpdatePackets(getNPC().getSpawnedPlayers(), false, false, false, false, true, false);
    }
    private void removeLine(@Range(from = 0, to = 999) int line) {
        if (npcHologramTextMap.containsKey(line)) {
            NPCHologramText text = npcHologramTextMap.get(line);
            for (@NotNull UUID uuid : getNPC().getSpawnedPlayers()) {
                //noinspection ConstantConditions
                EntityDestroyPacket packet = laivynpc().getVersion().createDestroyPacket(text.getArmorStands().toArray(new Entity[0]));
                packet.send(Bukkit.getPlayer(uuid));
            }
            npcHologramTextMap.remove(line);
        }
    }

    public @NotNull Map<@NotNull Integer, @NotNull NPCHologramText> getLinesMap() {
        return npcHologramTextMap;
    }

    public void hideHolograms(@NotNull List<Player> players) {
        List<ArmorStand> entities = new ArrayList<>();
        for (Map.Entry<Integer, NPCHologramText> map : npcHologramTextMap.entrySet()) {
            entities.addAll(map.getValue().getArmorStands());
        }

        if (entities.size() != 0) {
            laivynpc().getVersion().createDestroyPacket(entities.toArray(new ArmorStand[0])).send(players.toArray(new Player[0]));
        }
    }
    @NotNull
    public List<Packet> getHologramSpawnPackets(@NotNull Player player) {
        if (npcHologramTextMap.isEmpty() || !getNPC().isSpawned(player)) {
            return new LinkedList<>();
        }

        hideHolograms(Collections.singletonList(player));

        int last = 0;
        for (Integer v : npcHologramTextMap.keySet()) {
            if (v > last) last = v;
        }

        double actualHeight = (last * distanceBetweenHolograms) + distanceFromNPC;

        List<Packet> packets = new LinkedList<>();
        for (int row = last; row >= 0; row--) {
            if (npcHologramTextMap.containsKey(row)) {
                NPCHologramText text = npcHologramTextMap.get(row);

                try {
                    for (ArmorStand stand : text.getArmorStands()) {
                        Location l = getNPC().getLocation();
                        stand.setLocation(new Location(l.getWorld(), l.getX(), l.getY() + actualHeight, l.getZ(), 0F, 0F));
                        stand.setInvisible(true);

                        packets.add(laivynpc().getVersion().createSpawnPacket(stand));
                        packets.add(laivynpc().getVersion().createMetadataPacket(stand, stand.getDataWatcher(), true));
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            actualHeight -= distanceBetweenHolograms;
        }

        return packets;
    }

    public double getDistanceBetweenHolograms() {
        return distanceBetweenHolograms;
    }

    public void setDistanceBetweenHolograms(double distanceBetweenHolograms) {
        this.distanceBetweenHolograms = distanceBetweenHolograms;
    }

    public double getDistanceFromNPC() {
        return distanceFromNPC;
    }

    public void setDistanceFromNPC(double distanceFromNPC) {
        this.distanceFromNPC = distanceFromNPC;
    }

    @NotNull
    public NPC getNPC() {
        return npc;
    }

    public @NotNull Map<@NotNull String, @NotNull Object> serialize() {
        Map<String, Object> map = new LinkedHashMap<>();

        map.put("Distance between each hologram", getDistanceBetweenHolograms());
        map.put("Distance from NPC", getDistanceFromNPC());

        map.put("Lines", new LinkedHashMap<String, Object>() {{
            for (int line : getLines()) {
                NPCHologramText text = getLine(line);

                if (text != null) {
                    put(String.valueOf(line), text.serialize());
                }
            }
        }});

        return map;
    }

    public static @NotNull NPCHolograms deserialize(@NotNull NPC npc, @NotNull Map<@NotNull String, @NotNull Object> map) {
        double distanceBetweenHolograms = (double) map.get("Distance between each hologram");
        double distanceFromNPC = (double) map.get("Distance from NPC");

        NPCHolograms holograms = new NPCHolograms(npc);

        for (Map.Entry<String, Object> section : ((MemorySection) map.get("Lines")).getValues(false).entrySet()) {
            int line = Integer.parseInt(section.getKey());

            if (line < 0 || line > 999) {
                throw new NumberFormatException("'" + line + "' isn't a valid line (needs to be between 0 and 999). NPC '" + npc.getId() + "'");
            }

            holograms.setLine(line, NPCHologramText.deserialize(npc, ((MemorySection) section.getValue()).getValues(true)));
        }

        holograms.setDistanceBetweenHolograms(distanceBetweenHolograms);
        holograms.setDistanceFromNPC(distanceFromNPC);

        return holograms;
    }

}
