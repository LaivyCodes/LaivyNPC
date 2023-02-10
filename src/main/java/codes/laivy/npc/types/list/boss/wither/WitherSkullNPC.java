package codes.laivy.npc.types.list.boss.wither;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.entity.boss.wither.WitherSkull;
import codes.laivy.npc.types.EntityNPC;
import codes.laivy.npc.types.NPC;
import codes.laivy.npc.types.commands.NPCConfiguration;
import codes.laivy.npc.types.list.animal.WolfNPC;
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

public class WitherSkullNPC extends EntityNPC {

    public static @NotNull WitherSkullNPC fastInstance(@NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new WitherSkullNPC(players, location);
    }

    public static void debug(@NotNull Location location) {
        WitherSkullNPC witherNPC = new WitherSkullNPC(new ArrayList<>(), location);
        witherNPC.debug();
        witherNPC.destroy();
    }

    @Override
    protected void debug() {
        super.debug();
        setCharged(!isCharged());
    }

    public WitherSkullNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, Entity.EntityType.WITHER_SKULL, location);
        getHolograms().setDistanceFromNPC(-1.25D);
    }

    public boolean isCharged() {
        return getEntity().isCharged();
    }
    public void setCharged(boolean flag) {
        getEntity().setCharged(flag);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    @Override
    public void lookTo(@NotNull Location to) {
        Location npcLoc = getLocation();

        if (to.getWorld() != npcLoc.getWorld()) {
            throw new IllegalStateException("Different worlds");
        }

        double xDiff = to.getX() - npcLoc.getX();
        double yDiff = to.getY() - npcLoc.getY();
        double zDiff = to.getZ() - npcLoc.getZ();

        double distanceXZ = Math.sqrt(xDiff * xDiff + zDiff * zDiff);
        double distanceY = Math.sqrt(distanceXZ * distanceXZ + yDiff * yDiff);

        double yaw = Math.toDegrees(Math.acos(xDiff / distanceXZ));
        double pitch = Math.toDegrees(Math.acos(yDiff / distanceY)) - 90.0D;
        if (zDiff < 0.0D) {
            yaw += Math.abs(180.0D - yaw) * 2.0D;
        }

        npcLoc.setYaw((float) (yaw - 90.0F));
        npcLoc.setPitch((float) (pitch - 90.0F));

        setHeadRotation(npcLoc.getYaw() + 180, npcLoc.getPitch() + 90);
    }

    @Override
    public @NotNull WitherSkull getEntity() {
        return (WitherSkull) super.getEntity();
    }

    @Override
    public List<NPCConfiguration> getByCommandConfigurations() {
        List<NPCConfiguration> list = super.getByCommandConfigurations();
        list.add(new NPCConfiguration("charged", "/laivynpc config charged") {
            @Override
            public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                WitherSkullNPC witherSkullNPC = (WitherSkullNPC) npc;
                witherSkullNPC.setCharged(!witherSkullNPC.isCharged());
                sender.sendMessage(translate(sender, "npc.commands.general.flag_changed"));
            }
        });
        return list;
    }

    // Serializators
    @Override
    public @NotNull Map<@NotNull String, @NotNull Object> serialize() {
        Map<String, Object> map = super.serialize();
        map.put("WitherSkullNPC Configuration", new HashMap<String, Object>() {{
            put("Charged", isCharged());
        }});

        return map;
    }

    public static @NotNull WitherSkullNPC deserialize(@NotNull ConfigurationSection section) {
        WitherSkullNPC npc = (WitherSkullNPC) EntityNPC.deserialize(section);

        section = section.getConfigurationSection("WitherSkullNPC Configuration");
        npc.setCharged(section.getBoolean("Charged"));

        return npc;
    }
    //
}
