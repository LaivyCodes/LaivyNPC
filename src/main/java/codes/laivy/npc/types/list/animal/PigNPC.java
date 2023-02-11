package codes.laivy.npc.types.list.animal;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.Pig;
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

public class PigNPC extends AgeableEntityLivingNPC {

    public static @NotNull PigNPC fastInstance(@NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new PigNPC(players, location);
    }

    public static void debug(@NotNull Location location) {
        PigNPC pig = new PigNPC(new ArrayList<>(), location);
        pig.debug();
        pig.destroy();
    }

    @Override
    public void debug() {
        super.debug();
        setSaddle(!hasSaddle());
    }

    protected PigNPC(@NotNull List<OfflinePlayer> players, @NotNull Entity.EntityType type, @NotNull Location location) {
        super(players, type, location);
    }
    public PigNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, Entity.EntityType.PIG, location);
        getHolograms().setDistanceFromNPC(-1.25D);
    }

    public boolean hasSaddle() {
        return getEntity().hasSaddle();
    }
    public void setSaddle(boolean flag) {
        getEntity().setSaddle(flag);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    @Override
    public List<NPCConfiguration> getByCommandConfigurations() {
        List<NPCConfiguration> list = super.getByCommandConfigurations();

        list.add(new NPCConfiguration("saddle", "/laivynpc config saddle") {
            @Override
            public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                PigNPC pigNPC = (PigNPC) npc;

                if (pigNPC.hasSaddle()) sender.sendMessage(translate(sender, "npc.commands.pig.saddle.disallowed"));
                else sender.sendMessage(translate(sender, "npc.commands.pig.saddle.allowed"));

                pigNPC.setSaddle(!pigNPC.hasSaddle());
            }
        });

        return list;
    }

    @Override
    public @NotNull Pig getEntity() {
        return (Pig) super.getEntity();
    }

    // Serializators
    @Override
    public @NotNull Map<@NotNull String, @NotNull Object> serialize() {
        Map<String, Object> map = super.serialize();
        map.put("PigNPC Configuration", new HashMap<String, Object>() {{
            put("Saddle", hasSaddle());
        }});
        return map;
    }

    public static @NotNull PigNPC deserialize(@NotNull ConfigurationSection section) {
        PigNPC npc = (PigNPC) AgeableEntityLivingNPC.deserialize(section);
        npc.setSaddle(section.getConfigurationSection("PigNPC Configuration").getBoolean("Saddle"));
        return npc;
    }
    //

}
