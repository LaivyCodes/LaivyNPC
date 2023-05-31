package codes.laivy.npc.types.list.animal;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.Ocelot;
import codes.laivy.npc.types.NPC;
import codes.laivy.npc.types.TameableEntityLivingNPC;
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

public class OcelotNPC extends TameableEntityLivingNPC {

    public static @NotNull OcelotNPC fastInstance(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new OcelotNPC(id, players, location);
    }

    public static void debug(@NotNull Location location) {
        OcelotNPC ocelotNPC = new OcelotNPC(new ArrayList<>(), location);
        ocelotNPC.debug();
        ocelotNPC.destroy();
    }

    protected OcelotNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull Entity.EntityType type, @NotNull Location location) {
        super(id, players, type, location);
    }
    public OcelotNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        this(NPC.getNextNpcId(), players, location);
    }
    public OcelotNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(id, players, Entity.EntityType.OCELOT, location);
        getHolograms().setDistanceFromNPC(-1.25D);
    }

    @Override
    public List<NPCConfiguration> getByCommandConfigurations() {
        List<NPCConfiguration> list = super.getByCommandConfigurations();

        list.removeIf(configuration -> configuration.getName().equals("tamed"));

        return list;
    }

    @Override
    public @NotNull Ocelot getEntity() {
        return (Ocelot) super.getEntity();
    }

}
