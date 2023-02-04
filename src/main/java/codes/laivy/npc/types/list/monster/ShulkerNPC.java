package codes.laivy.npc.types.list.monster;

import codes.laivy.npc.mappings.utils.classes.entity.Entity;
import codes.laivy.npc.mappings.utils.classes.entity.monster.Shulker;
import codes.laivy.npc.mappings.utils.classes.enums.EnumDirectionEnum;
import codes.laivy.npc.mappings.utils.classes.java.EnumObjExec;
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

import static codes.laivy.npc.LaivyNPC.laivynpc;
import static codes.laivy.npc.config.Translate.translate;

public class ShulkerNPC extends EntityLivingNPC {

    public static @NotNull ShulkerNPC fastInstance(@NotNull List<OfflinePlayer> player, @NotNull Location location, @Nullable Object object) {
        return new ShulkerNPC(player, location);
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
    }

    public ShulkerNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, Entity.EntityType.SHULKER, location);
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
                EnumDirectionEnum dEnum = (EnumDirectionEnum) laivynpc().getVersion().getEnumExec("EnumDirection");

                if (args.length > 0) {
                    EnumDirectionEnum.EnumDirection type;
                    try {
                        type = new EnumDirectionEnum.EnumDirection(dEnum.valueOf(args[0].toUpperCase()).getValue());
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

        return list;
    }

    public int getPeek() {
        return getEntity().getPeek();
    }
    public void setPeek(int peek) {
        getEntity().setPeek(peek);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    public @NotNull EnumDirectionEnum.EnumDirection getDirection() {
        return getEntity().getDirection();
    }
    public void setDirection(@NotNull EnumDirectionEnum.EnumDirection direction) {
        getEntity().setDirection(direction);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    @Override
    public @NotNull Shulker getEntity() {
        return (Shulker) super.getEntity();
    }

    // Serializators
    @Override
    public @NotNull Map<@NotNull String, @NotNull Object> serialize() {
        Map<String, Object> map = super.serialize();
        map.put("ShulkerNPC Configuration", new HashMap<String, Object>() {{
            put("Peek", getPeek());
            put("Direction", getDirection().name());
        }});

        return map;
    }

    public static @NotNull ShulkerNPC deserialize(@NotNull ConfigurationSection section) {
        ShulkerNPC npc = (ShulkerNPC) EntityLivingNPC.deserialize(section);

        section = section.getConfigurationSection("ShulkerNPC Configuration");
        npc.setPeek(section.getInt("Peek"));
        npc.setDirection(new EnumDirectionEnum.EnumDirection(laivynpc().getVersion().getEnumExec("EnumDirection").valueOf(section.getString("Direction").toUpperCase()).getValue()));

        return npc;
    }
    //
}
