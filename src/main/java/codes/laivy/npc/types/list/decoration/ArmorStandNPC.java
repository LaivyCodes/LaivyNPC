package codes.laivy.npc.types.list.decoration;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.entity.decoration.ArmorStand;
import codes.laivy.npc.mappings.defaults.classes.others.location.Vector3f;
import codes.laivy.npc.types.EntityNPC;
import codes.laivy.npc.types.NPC;
import codes.laivy.npc.types.commands.NPCConfiguration;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.util.EulerAngle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

import static codes.laivy.npc.config.Translate.translate;

public class ArmorStandNPC extends EntityNPC {

    public static @NotNull ArmorStandNPC fastInstance(@NotNull List<OfflinePlayer> players, @NotNull Location location, @Nullable Object object) {
        return new ArmorStandNPC(players, location);
    }

    public static void debug(@NotNull Location location) {
        ArmorStandNPC stand = new ArmorStandNPC(new ArrayList<>(), location);
        stand.debug();
        stand.destroy();
    }

    @Override
    protected void debug() {
        super.debug();
        setBasePlate(!hasBasePlate());
        setArms(!hasArms());
        setSmall(!isSmall());

        setHeadPose(getHeadPose());
        setBodyPose(getBodyPose());
        setLeftArmPose(getLeftArmPose());
        setRightArmPose(getRightArmPose());
        setLeftLegPose(getLeftLegPose());
        setRightLegPose(getRightLegPose());
    }

    protected ArmorStandNPC(@NotNull List<OfflinePlayer> players, @NotNull Entity.EntityType type, @NotNull Location location) {
        super(players, type, location);
    }
    public ArmorStandNPC(@NotNull List<OfflinePlayer> players, @NotNull Location location) {
        super(players, Entity.EntityType.ARMOR_STAND, location);
    }

    @Override
    public @NotNull ArmorStand getEntity() {
        return (ArmorStand) super.getEntity();
    }

    public void setHeadPose(@NotNull EulerAngle angle) {
        getEntity().setHeadPose(Vector3f.getByEulerAngle(angle));
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }
    public @NotNull EulerAngle getHeadPose() {
        return getEntity().getHeadPose().getEulerAngle();
    }

    public void setBodyPose(@NotNull EulerAngle angle) {
        getEntity().setBodyPose(Vector3f.getByEulerAngle(angle));
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }
    public @NotNull EulerAngle getBodyPose() {
        return getEntity().getBodyPose().getEulerAngle();
    }

    public void setLeftArmPose(@NotNull EulerAngle angle) {
        getEntity().setLeftArmPose(Vector3f.getByEulerAngle(angle));
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }
    public @NotNull EulerAngle getLeftArmPose() {
        return getEntity().getLeftArmPose().getEulerAngle();
    }

    public void setRightArmPose(@NotNull EulerAngle angle) {
        getEntity().setRightArmPose(Vector3f.getByEulerAngle(angle));
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }
    public @NotNull EulerAngle getRightArmPose() {
        return getEntity().getRightArmPose().getEulerAngle();
    }

    public void setLeftLegPose(@NotNull EulerAngle angle) {
        getEntity().setLeftLegPose(Vector3f.getByEulerAngle(angle));
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }
    public @NotNull EulerAngle getLeftLegPose() {
        return getEntity().getLeftLegPose().getEulerAngle();
    }

    public void setRightLegPose(@NotNull EulerAngle angle) {
        getEntity().setRightLegPose(Vector3f.getByEulerAngle(angle));
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }
    public @NotNull EulerAngle getRightLegPose() {
        return getEntity().getRightLegPose().getEulerAngle();
    }

    public void setBasePlate(boolean flag) {
        getEntity().setBasePlate(flag);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }
    public boolean hasBasePlate() {
        return getEntity().hasBasePlate();
    }

    public void setArms(boolean flag) {
        getEntity().setArms(flag);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }
    public boolean hasArms() {
        return getEntity().hasArms();
    }

    public void setSmall(boolean flag) {
        getEntity().setSmall(flag);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }
    public boolean isSmall() {
        return getEntity().isSmall();
    }

    @Override
    public List<NPCConfiguration> getByCommandConfigurations() {
        List<NPCConfiguration> list = super.getByCommandConfigurations();
        list.add(SMALL_CONFIG);
        list.add(POSES_CONFIG);
        list.add(BASE_CONFIG);
        list.add(ARMS_CONFIG);
        return list;
    }

    // Commands
    public static @NotNull NPCConfiguration SMALL_CONFIG = new NPCConfiguration("small", "/laivynpc config small") {
        @Override
        public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
            ArmorStandNPC standNPC = (ArmorStandNPC) npc;

            standNPC.setSmall(!standNPC.isSmall());
            sender.sendMessage(translate(sender, "npc.commands.armor_stand.size.changed"));
        }
    };
    public static @NotNull NPCConfiguration POSES_CONFIG = new NPCConfiguration("pose", "/laivynpc config pose (part) (x) (y) (z)") {
        @Override
        public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
            ArmorStandNPC standNPC = (ArmorStandNPC) npc;

            if (args.length >= 4) {
                try {
                    ArmorStand.Pose pose = ArmorStand.Pose.valueOf(args[0].toUpperCase());
                    double x = Double.parseDouble(args[1].replace(",", "."));
                    double y = Double.parseDouble(args[2].replace(",", "."));
                    double z = Double.parseDouble(args[3].replace(",", "."));

                    EulerAngle angle = new EulerAngle(x, y, z);
                    if (pose == ArmorStand.Pose.HEAD) {
                        standNPC.setHeadPose(angle);
                    } else if (pose == ArmorStand.Pose.BODY) {
                        standNPC.setBodyPose(angle);
                    } else if (pose == ArmorStand.Pose.LEFT_ARM) {
                        standNPC.setLeftArmPose(angle);
                    } else if (pose == ArmorStand.Pose.RIGHT_ARM) {
                        standNPC.setRightArmPose(angle);
                    } else if (pose == ArmorStand.Pose.LEFT_LEG) {
                        standNPC.setLeftLegPose(angle);
                    } else if (pose == ArmorStand.Pose.RIGHT_LEG) {
                        standNPC.setRightLegPose(angle);
                    } else {
                        throw new UnsupportedOperationException("Couldn't proccess this pose '" + pose + "'");
                    }

                    sender.sendMessage(translate(sender, "npc.commands.armor_stand.poses.changed", pose));
                    return;
                } catch (NumberFormatException ignore) {
                    sender.sendMessage(translate(sender, "npc.commands.armor_stand.poses.invalid_number"));
                } catch (IllegalArgumentException ignore) {
                    sender.sendMessage(translate(sender, "npc.commands.armor_stand.poses.invalid_pose", args[0]));
                }
            }

            StringBuilder parts = new StringBuilder();
            int row = 0;
            for (ArmorStand.Pose pose : ArmorStand.Pose.values()) {
                parts.append("§6").append(pose.name());
                row++;
                if (row != ArmorStand.Pose.values().length) parts.append("§c, ");
            }

            sender.sendMessage("§cUse /laivynpc config pose (part) (x) (y) (z)");
            sender.sendMessage("§c" + translate(sender, "npc.commands.armor_stand.poses.available_parts") + " " + parts);
        }
    };
    public static @NotNull NPCConfiguration BASE_CONFIG = new NPCConfiguration("base", "/laivynpc config base") {
        @Override
        public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
            ArmorStandNPC standNPC = (ArmorStandNPC) npc;
            standNPC.setBasePlate(!standNPC.hasBasePlate());
            sender.sendMessage(translate(sender, "npc.commands.armor_stand.base.changed"));
        }
    };
    public static @NotNull NPCConfiguration ARMS_CONFIG = new NPCConfiguration("arms", "/laivynpc config arms") {
        @Override
        public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
            ArmorStandNPC standNPC = (ArmorStandNPC) npc;
            standNPC.setArms(!standNPC.hasArms());
            sender.sendMessage(translate(sender, "npc.commands.armor_stand.arms.changed"));
        }
    };
    //

    // Serializators
    @Override
    public @NotNull Map<@NotNull String, @NotNull Object> serialize() {
        Map<String, Object> map = super.serialize();
        map.put("ArmorStandNPC Configuration", new HashMap<String, Object>() {{
            put("Base Plate", hasBasePlate());
            put("Arms", hasArms());
            put("Small", isSmall());

            put("Poses", new LinkedHashMap<String, Object>() {{
                put("Head", getEntity().getHeadPose().serialize());
                put("Body", getEntity().getBodyPose().serialize());
                put("Left Arm", getEntity().getLeftArmPose().serialize());
                put("Right Arm", getEntity().getRightArmPose().serialize());
                put("Left Leg", getEntity().getLeftLegPose().serialize());
                put("Right Leg", getEntity().getRightLegPose().serialize());
            }});
        }});

        return map;
    }

    public static @NotNull ArmorStandNPC deserialize(@NotNull ConfigurationSection section) {
        ArmorStandNPC npc = (ArmorStandNPC) EntityNPC.deserialize(section);
        section = section.getConfigurationSection("ArmorStandNPC Configuration");

        npc.setBasePlate(section.getBoolean("Base Plate"));
        npc.setArms(section.getBoolean("Arms"));
        npc.setSmall(section.getBoolean("Small"));

        ConfigurationSection poses = section.getConfigurationSection("Poses");
        npc.getEntity().setHeadPose(Vector3f.deserialize(poses.getConfigurationSection("Head").getValues(true)));
        npc.getEntity().setBodyPose(Vector3f.deserialize(poses.getConfigurationSection("Body").getValues(true)));
        npc.getEntity().setLeftArmPose(Vector3f.deserialize(poses.getConfigurationSection("Left Arm").getValues(true)));
        npc.getEntity().setRightArmPose(Vector3f.deserialize(poses.getConfigurationSection("Right Arm").getValues(true)));
        npc.getEntity().setLeftLegPose(Vector3f.deserialize(poses.getConfigurationSection("Left Leg").getValues(true)));
        npc.getEntity().setRightLegPose(Vector3f.deserialize(poses.getConfigurationSection("Right Leg").getValues(true)));

        return npc;
    }
    //

}
