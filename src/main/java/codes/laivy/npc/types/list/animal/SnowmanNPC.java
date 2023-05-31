package codes.laivy.npc.types.list.animal;

import codes.laivy.npc.config.Translate;
import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.Snowman;
import codes.laivy.npc.mappings.versions.V1_9_R1;
import codes.laivy.npc.types.EntityLivingNPC;
import codes.laivy.npc.types.NPC;
import codes.laivy.npc.types.commands.NPCConfiguration;
import codes.laivy.npc.utils.ReflectionUtils;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static codes.laivy.npc.config.Translate.translate;

public class SnowmanNPC extends EntityLivingNPC {

    public static @NotNull SnowmanNPC fastInstance(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new SnowmanNPC(id, players, location);
    }

    public static void debug(@NotNull Location location) {
        SnowmanNPC snowmanNPC = new SnowmanNPC(new ArrayList<>(), location);
        snowmanNPC.debug();
        snowmanNPC.destroy();
    }

    @Override
    protected void debug() {
        super.debug();

        if (ReflectionUtils.isCompatible(V1_9_R1.class)) {
            setPumpkinHat(!hasPumpkinHat());
        }
    }

    protected SnowmanNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull Entity.EntityType type, @NotNull Location location) {
        super(id, players, type, location);
    }
    public SnowmanNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        this(NPC.getNextNpcId(), players, location);
    }
    public SnowmanNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(id, players, Entity.EntityType.SNOWMAN, location);
    }

    public boolean hasPumpkinHat() {
        return getEntity().hasPumpkinHat();
    }
    public void setPumpkinHat(boolean flag) {
        getEntity().setPumpkinHat(flag);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    @Override
    public List<NPCConfiguration> getByCommandConfigurations() {
        List<NPCConfiguration> list = super.getByCommandConfigurations();

        if (ReflectionUtils.isCompatible(V1_9_R1.class)) {
            list.add(new NPCConfiguration("pumpkin", "/laivynpc config pumpkin") {
                @Override
                public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                    SnowmanNPC snowmanNPC = (SnowmanNPC) npc;
                    snowmanNPC.setPumpkinHat(!snowmanNPC.hasPumpkinHat());
                    sender.sendMessage(translate(sender, "npc.commands.general.flag_changed"));
                }
            });
        }

        return list;
    }

    @Override
    public @NotNull Snowman getEntity() {
        return (Snowman) super.getEntity();
    }
}
