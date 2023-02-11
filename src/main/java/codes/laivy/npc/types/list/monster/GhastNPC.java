package codes.laivy.npc.types.list.monster;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.Ghast;
import codes.laivy.npc.types.EntityLivingNPC;
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

public class GhastNPC extends EntityLivingNPC {

    public static @NotNull GhastNPC fastInstance(@NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new GhastNPC(players, location);
    }

    public static void debug(@NotNull Location location) {
        GhastNPC ghastNPC = new GhastNPC(new ArrayList<>(), location);
        ghastNPC.debug();
        ghastNPC.destroy();
    }

    @Override
    protected void debug() {
        super.debug();
        setAttacking(!isAttacking());
    }

    protected GhastNPC(@NotNull List<OfflinePlayer> players, @NotNull Entity.EntityType type, @NotNull Location location) {
        super(players, type, location);
    }
    public GhastNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, Entity.EntityType.GHAST, location);
        getHolograms().setDistanceFromNPC(3D);
    }

    public boolean isAttacking() {
        return getEntity().isAttacking();
    }
    public void setAttacking(boolean flag) {
        getEntity().setAttacking(flag);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    @Override
    public List<NPCConfiguration> getByCommandConfigurations() {
        List<NPCConfiguration> list = super.getByCommandConfigurations();

        list.add(new NPCConfiguration("attacking", "/laivynpc config attacking") {
            @Override
            public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                GhastNPC ghastNPC = (GhastNPC) npc;
                ghastNPC.setAttacking(!ghastNPC.isAttacking());
                sender.sendMessage(translate(sender, "npc.commands.general.flag_changed"));
            }
        });

        return list;
    }

    @Override
    public @NotNull Ghast getEntity() {
        return (Ghast) super.getEntity();
    }

    // Serializators
    @Override
    public @NotNull Map<@NotNull String, @NotNull Object> serialize() {
        Map<String, Object> map = super.serialize();
        map.put("GhastNPC Configuration", new HashMap<String, Object>() {{
            put("Attacking", isAttacking());
        }});

        return map;
    }

    public static @NotNull GhastNPC deserialize(@NotNull ConfigurationSection section) {
        GhastNPC npc = (GhastNPC) EntityLivingNPC.deserialize(section);

        section = section.getConfigurationSection("GhastNPC Configuration");
        npc.setAttacking(section.getBoolean("Attacking"));

        return npc;
    }
    //
}
