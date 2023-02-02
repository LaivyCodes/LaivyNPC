package codes.laivy.npc.mappings.utils.classes.entity.npc;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.Map;

public class VillagerProfession {

    private final @NotNull Type type;
    private int level;

    public VillagerProfession(@NotNull Type type, int level) {
        this.type = type;
        this.level = level;
    }

    public @NotNull Type getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public @NotNull Map<String, Object> serialize() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("Type", getType().name());
        map.put("Level", getLevel());
        return map;
    }
    public static @NotNull VillagerProfession deserialize(@NotNull Map<String, Object> map) {
        Type type = Type.valueOf((String) map.get("Type"));
        int level = (int) map.get("Level");

        return new VillagerProfession(type, level);
    }

    public enum Type {
        NONE(0),
        ARMORER(1),
        BUTCHER(2),
        CARTOGRAPHER(3),
        CLERIC(4),
        FARMER(5),
        FISHERMAN(6),
        FLETCHER(7),
        LEATHERWORKER(8),
        LIBRARIAN(9),
        MASON(10),
        NITWIT(11),
        SHEPHERD(12),
        TOOLSMITH(13),
        WEAPONSMITH(14);

        private final int id;

        Type(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public static @NotNull Type getById(int id) {
            for (Type type : values()) {
                if (type.getId() == id) {
                    return type;
                }
            }
            throw new NullPointerException("Couldn't find a villager profession type with id '" + id + "'");
        }
    }

}
