package codes.laivy.npc.types.utils;

import codes.laivy.npc.LaivyNPC;
import codes.laivy.npc.mappings.utils.classes.entity.Entity;
import codes.laivy.npc.mappings.utils.classes.entity.decoration.ArmorStand;
import codes.laivy.npc.types.NPC;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class NPCHologramText {

    private final @NotNull String text;
    private final @NotNull TextOpacity opacity;

    private final @NotNull Set<ArmorStand> armorStands = new LinkedHashSet<>();

    private final @NotNull NPC npc;

    public NPCHologramText(@NotNull String text, @NotNull NPC npc) {
        this(text, TextOpacity.LOWEST, npc);
    }
    public NPCHologramText(@NotNull String text, @NotNull TextOpacity opacity, @NotNull NPC npc) {
        try {
            this.text = text;
            this.opacity = opacity;
            this.npc = npc;

            for (int row = 0; row < getOpacity().getSize(); row++) {
                ArmorStand stand = (ArmorStand) LaivyNPC.laivynpc().getVersion().createEntity(Entity.EntityType.ARMOR_STAND, npc.getLocation());
                stand.setInvisible(true);
                stand.setCustomNameVisible(true);
                stand.setCustomName(text);

                getArmorStands().add(stand);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public @NotNull String getText() {
        return text;
    }
    public @NotNull TextOpacity getOpacity() {
        return opacity;
    }
    public @NotNull Set<@NotNull ArmorStand> getArmorStands() {
        return armorStands;
    }

    public @NotNull NPC getNPC() {
        return npc;
    }

    public enum TextOpacity {

        LOWEST(1),
        LOW(2),
        MEDIUM(3),
        HARD(4),
        HARDER(6),
        FULL(10),
        ;

        private final int size;

        TextOpacity(int size) {
            this.size = size;
        }

        public int getSize() {
            return size;
        }
    }

    public @NotNull Map<@NotNull String, @NotNull Object> serialize() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("Text", getText().replace("§", "&"));
        map.put("Opacity", getOpacity().name());
        return map;
    }

    public static @NotNull NPCHologramText deserialize(@NotNull NPC npc, @NotNull Map<@NotNull String, @NotNull Object> map) {
        String text = ChatColor.translateAlternateColorCodes('&', (String) map.get("Text"));
        TextOpacity opacity;

        try {
            opacity = TextOpacity.valueOf((String) map.get("Opacity"));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Hologram text opacity deserialization", e);
        }

        return new NPCHologramText(text, opacity, npc);
    }

}
