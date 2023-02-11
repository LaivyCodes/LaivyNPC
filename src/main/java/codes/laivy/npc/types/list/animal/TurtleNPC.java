package codes.laivy.npc.types.list.animal;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.Turtle;
import codes.laivy.npc.types.AgeableEntityLivingNPC;
import codes.laivy.npc.types.NPC;
import codes.laivy.npc.types.commands.NPCConfiguration;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static codes.laivy.npc.config.Translate.translate;

public class TurtleNPC extends AgeableEntityLivingNPC {

    public static @NotNull TurtleNPC fastInstance(@NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new TurtleNPC(players, location);
    }

    public static void debug(@NotNull Location location) {
        TurtleNPC turtleNPC = new TurtleNPC(new ArrayList<>(), location);
        turtleNPC.debug();
        turtleNPC.destroy();
    }

    @Override
    public void debug() {
        super.debug();
        setEgg(!hasEgg());
    }

    protected TurtleNPC(@NotNull List<OfflinePlayer> players, @NotNull Entity.EntityType type, @NotNull Location location) {
        super(players, type, location);
    }
    public TurtleNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, Entity.EntityType.TURTLE, location);
    }

    public boolean hasEgg() {
        return getEntity().hasEgg();
    }
    public void setEgg(boolean egg) {
        getEntity().setEgg(egg);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    @Override
    public List<NPCConfiguration> getByCommandConfigurations() {
        List<NPCConfiguration> list = super.getByCommandConfigurations();

        list.add(new NPCConfiguration("egg", "/laivynpc config egg") {
            @Override
            public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                TurtleNPC turtleNPC = (TurtleNPC) npc;
                turtleNPC.setEgg(!turtleNPC.hasEgg());
                sender.sendMessage(translate(sender, "npc.commands.general.flag_changed"));
            }
        });

        return list;
    }

    @Override
    public @NotNull Turtle getEntity() {
        return (Turtle) super.getEntity();
    }

    // Serializators
    @Override
    public @NotNull Map<@NotNull String, @NotNull Object> serialize() {
        Map<String, Object> map = super.serialize();
        map.put("TurtleNPC Configuration", new HashMap<String, Object>() {{
            put("Has egg", hasEgg());
        }});
        return map;
    }

    public static @NotNull TurtleNPC deserialize(@NotNull ConfigurationSection section) {
        TurtleNPC npc = (TurtleNPC) AgeableEntityLivingNPC.deserialize(section);

        ConfigurationSection config = section.getConfigurationSection("TurtleNPC Configuration");
        npc.setEgg(config.getBoolean("Has egg"));
        return npc;
    }
    //

}
