package codes.laivy.npc.types.list.monster;

import codes.laivy.npc.mappings.utils.classes.entity.Entity;
import codes.laivy.npc.mappings.utils.classes.entity.monster.Skeleton;
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

public class SkeletonNPC extends EntityLivingNPC {

    public static @NotNull SkeletonNPC fastInstance(@NotNull List<OfflinePlayer> player, @NotNull Location location, @Nullable Object object) {
        return new SkeletonNPC(player, Skeleton.SkeletonType.NORMAL, location);
    }

    public static void debug(@NotNull Location location) {
        SkeletonNPC skeletonNPC = new SkeletonNPC(new ArrayList<>(), Skeleton.SkeletonType.NORMAL, location);
        skeletonNPC.debug();
        skeletonNPC.destroy();
    }

    @Override
    protected void debug() {
        super.debug();
        setSkeletonType(getSkeletonType());
    }

    public SkeletonNPC(@NotNull List<OfflinePlayer> players, Skeleton.SkeletonType type, @NotNull Location location) {
        super(players, Entity.EntityType.SKELETON, location);
        setSkeletonType(type);
    }

    public @NotNull Skeleton.SkeletonType getSkeletonType() {
        return getEntity().getType();
    }
    public void setSkeletonType(@NotNull Skeleton.SkeletonType type) {
        getEntity().setType(type);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    @Override
    public @NotNull Skeleton getEntity() {
        return (Skeleton) super.getEntity();
    }

    @Override
    public List<NPCConfiguration> getByCommandConfigurations() {
        List<NPCConfiguration> list = super.getByCommandConfigurations();

        list.add(new NPCConfiguration("type", "/laivynpc config type (type)") {
            @Override
            public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                SkeletonNPC skeletonNPC = (SkeletonNPC) npc;

                if (args.length > 0) {
                    try {
                        Skeleton.SkeletonType type = Skeleton.SkeletonType.valueOf(args[0].toUpperCase());
                        skeletonNPC.setSkeletonType(type);
                        sender.sendMessage(translate(sender, "npc.commands.general.flag_changed"));
                    } catch (IllegalArgumentException ignore) {
                        sender.performCommand("laivynpc config " + getName());
                        return;
                    }
                    return;
                }

                StringBuilder builder = new StringBuilder();
                int row = 0;
                for (Skeleton.SkeletonType type : Skeleton.SkeletonType.values()) {
                    if (!type.isCompatible()) continue;

                    if (row != 0) builder.append("§7, ");
                    builder.append("§f").append(type.name());
                    row++;
                }

                sender.sendMessage("§cUse " + getSyntax());
                sender.sendMessage(translate(sender, "npc.commands.general.available_options", builder));
            }
        });

        return list;
    }

    // Serializators
    @Override
    public @NotNull Map<@NotNull String, @NotNull Object> serialize() {
        Map<String, Object> map = super.serialize();

        map.put("SkeletonNPC Configuration", new HashMap<String, Object>() {{
            put("Type", getSkeletonType().name());
        }});

        return map;
    }

    public static @NotNull SkeletonNPC deserialize(@NotNull ConfigurationSection section) {
        SkeletonNPC npc = (SkeletonNPC) EntityLivingNPC.deserialize(section);

        section = section.getConfigurationSection("SkeletonNPC Configuration");
        npc.setSkeletonType(Skeleton.SkeletonType.valueOf(section.getString("Type")));

        return npc;
    }
    //

}
