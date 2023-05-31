package codes.laivy.npc.types.list.monster;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.Shulker;
import codes.laivy.npc.mappings.defaults.classes.enums.EnumColorEnum;
import codes.laivy.npc.mappings.defaults.classes.enums.EnumDirectionEnum;
import codes.laivy.npc.mappings.defaults.classes.enums.EnumDirectionEnum.EnumDirection;
import codes.laivy.npc.mappings.defaults.classes.java.EnumObjExec;
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

import static codes.laivy.npc.config.Translate.translate;

public class ShulkerNPC extends EntityLivingNPC {

    public static @NotNull ShulkerNPC fastInstance(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new ShulkerNPC(id, players, location);
    }

    public static void debug(@NotNull Location location) {
        ShulkerNPC shulkerNPC = new ShulkerNPC(new ArrayList<>(), location);
        shulkerNPC.debug();
        shulkerNPC.destroy();
    }

    @Override
    protected void debug() {
        super.debug();
        setDirection(getDirection());
        setPeek(getPeek());
        if (ReflectionUtils.isCompatible(V1_11_R1.class)) {
            setColor(getColor());
        }
    }

    protected ShulkerNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull Entity.EntityType type, @NotNull Location location) {
        super(id, players, type, location);
    }
    public ShulkerNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        this(NPC.getNextNpcId(), players, location);
    }
    public ShulkerNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(id, players, Entity.EntityType.SHULKER, location);
    }

    public int getPeek() {
        return getEntity().getPeek();
    }
    public void setPeek(int peek) {
        getEntity().setPeek(peek);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    public @NotNull EnumDirection getDirection() {
        return getEntity().getDirection();
    }
    public void setDirection(@NotNull EnumDirection direction) {
        getEntity().setDirection(direction);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    public @NotNull EnumColorEnum.EnumColor getColor() {
        return getEntity().getColor();
    }
    public void setColor(@NotNull EnumColorEnum.EnumColor color) {
        getEntity().setColor(color);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    @Override
    public @NotNull Shulker getEntity() {
        return (Shulker) super.getEntity();
    }

    @Override
    public List<NPCConfiguration> getByCommandConfigurations() {
        List<NPCConfiguration> list = super.getByCommandConfigurations();
        list.add(new NPCConfiguration("peek", "/laivynpc config peek (peek)") {
            @Override
            public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                ShulkerNPC shulkerNPC = (ShulkerNPC) npc;

                if (args.length > 0) {
                    int peek;
                    try {
                        peek = Integer.parseInt(args[0].replace(",", "."));
                        shulkerNPC.setPeek(peek);
                        sender.sendMessage(translate(sender, "npc.commands.general.flag_changed"));
                        return;
                    } catch (NumberFormatException ignore) {
                    }
                }

                sender.sendMessage("§cUse " + getSyntax());
            }
        });
        list.add(new NPCConfiguration("direction", "/laivynpc config direction (direction)") {
            @Override
            public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                ShulkerNPC shulkerNPC = (ShulkerNPC) npc;
                EnumDirectionEnum dEnum = EnumDirectionEnum.getInstance();

                if (args.length > 0) {
                    EnumDirection type;
                    try {
                        type = new EnumDirection(dEnum.valueOf(args[0].toUpperCase()).getValue());
                        shulkerNPC.setDirection(type);
                        sender.sendMessage(translate(sender, "npc.commands.general.flag_changed"));
                        return;
                    } catch (IllegalArgumentException ignore) {
                    }
                }

                StringBuilder builder = new StringBuilder();
                int row = 0;
                for (@NotNull EnumObjExec type : dEnum.values()) {
                    if (row != 0) builder.append("§7, ");
                    builder.append("§f").append(type.name());
                    row++;
                }

                sender.sendMessage("§cUse " + getSyntax());
                sender.sendMessage(translate(sender, "npc.commands.general.available_options", builder));
            }
        });

        if (ReflectionUtils.isCompatible(V1_11_R1.class)) {
            list.add(new NPCConfiguration("color", "/laivynpc config color (color)") {
                @Override
                public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                    ShulkerNPC shulkerNPC = (ShulkerNPC) npc;
                    EnumColorEnum cEnum = EnumColorEnum.getInstance();

                    if (args.length > 0) {
                        EnumColorEnum.EnumColor type;
                        try {
                            type = new EnumColorEnum.EnumColor(cEnum.valueOf(args[0].toUpperCase()).getValue());
                            shulkerNPC.setColor(type);
                            sender.sendMessage(translate(sender, "npc.commands.general.flag_changed"));
                            return;
                        } catch (IllegalArgumentException ignore) {
                        }
                    }

                    StringBuilder builder = new StringBuilder();
                    int row = 0;
                    for (@NotNull EnumObjExec type : cEnum.values()) {
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
        map.put("ShulkerNPC Configuration", new HashMap<String, Object>() {{
            put("Peek", getPeek());
            put("Direction", getDirection().name());
            if (ReflectionUtils.isCompatible(V1_11_R1.class)) {
                put("Color", getColor().name());
            }
        }});

        return map;
    }

    public static @NotNull ShulkerNPC deserialize(@NotNull ConfigurationSection section) {
        ShulkerNPC npc = (ShulkerNPC) EntityLivingNPC.deserialize(section);

        section = section.getConfigurationSection("ShulkerNPC Configuration");
        npc.setPeek(section.getInt("Peek"));
        npc.setDirection(new EnumDirection(EnumDirectionEnum.getInstance().valueOf(section.getString("Direction").toUpperCase()).getValue()));
        if (ReflectionUtils.isCompatible(V1_11_R1.class) && section.contains("Color")) {
            npc.setColor(new EnumColorEnum.EnumColor(EnumColorEnum.getInstance().valueOf(section.getString("Color").toUpperCase()).getValue()));
        }

        return npc;
    }
    //
}
