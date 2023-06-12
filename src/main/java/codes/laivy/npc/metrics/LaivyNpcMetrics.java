package codes.laivy.npc.metrics;

import codes.laivy.npc.LaivyNPC;
import codes.laivy.npc.types.NPC;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.Map;

@ApiStatus.Internal
public class LaivyNpcMetrics {

    private final @NotNull Metrics metrics;

    public LaivyNpcMetrics(@NotNull LaivyNPC plugin) {
        metrics = new Metrics(plugin, 17155);

        metrics.addCustomChart(new Metrics.AdvancedPie("npcs_created", () -> {
            Map<String, Integer> npcs = new LinkedHashMap<>();
            for (NPC npc : NPC.NPCS_ID.values()) {
                String name = "Other";

                if (NPC.REGISTERED_NPCS_CLASSES.containsValue(npc.getClass())) {
                    for (Map.Entry<@NotNull String, @NotNull Class<? extends NPC>> entry : NPC.REGISTERED_NPCS_CLASSES.entrySet()) {
                        if (entry.getValue().equals(npc.getClass())) {
                            name = entry.getKey();
                            break;
                        }
                    }
                }

                npcs.putIfAbsent(name, 0);
                npcs.put(name, npcs.get(name) + 1);
            }
            return npcs;
        }));
    }

    public @NotNull Metrics getMetrics() {
        return metrics;
    }
}
