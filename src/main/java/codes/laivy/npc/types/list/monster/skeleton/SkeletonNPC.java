package codes.laivy.npc.types.list.monster.skeleton;

import codes.laivy.npc.mappings.defaults.classes.entity.monster.skeleton.Skeleton;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.skeleton.Skeleton.Type;
import codes.laivy.npc.mappings.versions.V1_11_R1;
import codes.laivy.npc.types.EntityLivingNPC;
import codes.laivy.npc.types.NPC;
import codes.laivy.npc.types.commands.NPCConfiguration;
import codes.laivy.npc.utils.ReflectionUtils;
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

import static codes.laivy.npc.LaivyNPC.laivynpc;
import static codes.laivy.npc.config.Translate.translate;

public class SkeletonNPC extends EntityLivingNPC {

    public static @NotNull SkeletonNPC fastInstance(@NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new SkeletonNPC(players, location);
    }

    public static void debug(@NotNull Location location) {
        SkeletonNPC skeletonNPC = new SkeletonNPC(new ArrayList<>(), location);
        skeletonNPC.debug();
        skeletonNPC.destroy();
    }

    protected SkeletonNPC(@NotNull List<OfflinePlayer> players, @NotNull Type type, @NotNull Location location) {
        super(players, type.getEntityType(), location);
    }
    public SkeletonNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        this(players, Type.NORMAL, location);
    }

    public @NotNull Type getType() {
        return getEntity().getType();
    }

    @Override
    public @NotNull Skeleton getEntity() {
        return (Skeleton) super.getEntity();
    }

    @Override
    public List<NPCConfiguration> getByCommandConfigurations() {
        List<NPCConfiguration> list = super.getByCommandConfigurations();

        if (!ReflectionUtils.isCompatible(V1_11_R1.class)) {
            list.add(new NPCConfiguration("type", "/laivynpc config type (type)") {
                @Override
                public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                    SkeletonNPC skeletonNPC = (SkeletonNPC) npc;

                    if (args.length > 0) {
                        try {
                            Type type = Type.valueOf(args[0].toUpperCase());
                            laivynpc().getVersion().setEntitySkeletonType(skeletonNPC.getEntity(), type);
                            sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
                            sender.sendMessage(translate(sender, "npc.commands.general.flag_changed"));
                        } catch (IllegalArgumentException ignore) {
                            sender.performCommand("laivynpc config " + getName());
                            return;
                        }
                        return;
                    }

                    StringBuilder builder = new StringBuilder();
                    int row = 0;
                    for (Type type : Type.values()) {
                        if (!type.isCompatible()) continue;

                        if (row != 0) builder.append("§7, ");
                        builder.append("§f").append(type.name());
                        row++;
                    }

                    sender.sendMessage("§cUse " + getSyntax());
                    sender.sendMessage(translate(sender, "npc.commands.general.available_options", builder));
                }
            });
        }

        return list;
    }

    // Serializators
    @Override
    public @NotNull Map<@NotNull String, @NotNull Object> serialize() {
        Map<String, Object> map = super.serialize();

        if (!ReflectionUtils.isCompatible(V1_11_R1.class)) {
            map.put("SkeletonNPC Configuration", new HashMap<String, Object>() {{
                put("Type", getType().name());
            }});
        }

        return map;
    }

    public static @NotNull SkeletonNPC deserialize(@NotNull ConfigurationSection section) {
        SkeletonNPC npc = (SkeletonNPC) EntityLivingNPC.deserialize(section);

        if (!ReflectionUtils.isCompatible(V1_11_R1.class)) {
            section = section.getConfigurationSection("SkeletonNPC Configuration");
            Type type = Type.valueOf(section.getString("Type"));
            if (type.isCompatible()) {
                laivynpc().getVersion().setEntitySkeletonType(npc.getEntity(), type);
                npc.sendUpdatePackets(npc.getVisiblePlayers(), false, false, true, false, false, false);
            }
        }

        return npc;
    }
    //

}
