package codes.laivy.npc.debug;

import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.MethodExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.entity.EntityPlayer;
import codes.laivy.npc.mappings.defaults.classes.entity.animal.Pig;
import codes.laivy.npc.mappings.defaults.classes.entity.decoration.ArmorStand;
import codes.laivy.npc.mappings.defaults.classes.enums.EnumItemSlotEnum;
import codes.laivy.npc.mappings.defaults.classes.enums.EnumPlayerInfoActionEnum;
import codes.laivy.npc.mappings.defaults.classes.nbt.tags.*;
import codes.laivy.npc.mappings.defaults.classes.packets.*;
import codes.laivy.npc.mappings.defaults.classes.scoreboard.ScoreboardTeam;
import codes.laivy.npc.types.NPC;
import codes.laivy.npc.utils.ReflectionUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Debug {

    public static final @NotNull Collection<@NotNull Class<? extends NPC>> DEBUG_NPCS = new LinkedList<Class<? extends NPC>>() {{
        for (Entity.EntityType type : Entity.EntityType.values()) {
           if (ReflectionUtils.isCompatible(type.getSince())) {
               add(type.getNPCClass());
           }
        }
    }};

    private final @NotNull DebugResult result;

    public Debug(@NotNull Player player) {
        result = new DebugResult();
        result.getLogs().add(version());
        result.getLogs().add(nbt());
        result.getLogs().add(packets(player));
        result.getLogs().add(npcs(player));
        result.finish();
    }

    @NotNull
    public DebugResult getResult() {
        return result;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @NotNull
    private DebugLog version() {
        DebugLog log = new DebugLog("Version Debug");
        StringBuilder message = new StringBuilder("§8-----\n§7Debugging Version mappings:\n");

        try {
            laivynpc().getVersion().getClasses();
            laivynpc().getVersion().getMethods();
            laivynpc().getVersion().getFields();
            laivynpc().getVersion().getEnums();
            laivynpc().getVersion().getTexts();
            message.append("§aSuccess!\n");
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        log.setMessage(message.toString());
        return log;
    }

    @NotNull
    private DebugLog nbt() {
        DebugLog log = new DebugLog("NBT Debug");
        StringBuilder message = new StringBuilder("§8-----\n§7Debugging NBT values:\n");

        try {
            NBTTagCompound debugCompound = new NBTTagCompound();
            debugCompound.set("DebugValue", new NBTTagString("TestDebugValue"));

            NBTTagCompound compound = new NBTTagCompound();
            compound.set("byte", new NBTTagByte(Byte.MAX_VALUE));
            compound.set("byte_array", new NBTTagByteArray(new byte[]{Byte.MIN_VALUE, Byte.MAX_VALUE}));
            compound.set("compound", debugCompound);
            compound.set("double", new NBTTagDouble(Double.MAX_VALUE));
            compound.set("float", new NBTTagFloat(Float.MAX_VALUE));
            compound.set("int", new NBTTagInt(Integer.MAX_VALUE));
            compound.set("int_array", new NBTTagInt(Integer.MAX_VALUE));
            compound.set("list", new NBTTagList(Arrays.asList(new NBTTagDouble(10D), new NBTTagString("TestValueAtList"))));
            compound.set("long", new NBTTagLong(Long.MAX_VALUE));
            compound.set("short", new NBTTagShort(Short.MAX_VALUE));
            compound.set("string", new NBTTagString("TestValue"));
            message.append("§aSuccess!\n");
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        log.setMessage(message.toString());
        return log;
    }

    @NotNull
    private DebugLog packets(@NotNull Player player) {
        DebugLog log = new DebugLog("Packets Debug");
        StringBuilder message = new StringBuilder("§8-----\n§7Debugging Packets:\n");

        try {
            message.append("§7Trying to debug EntitySpawnPacket...\n");
            ArmorStand stand = (ArmorStand) laivynpc().getVersion().createEntity(Entity.EntityType.ARMOR_STAND, player.getLocation());
            EntitySpawnPacket entitySpawnPacket = laivynpc().getVersion().createSpawnPacket(stand);
            entitySpawnPacket.send(player);

            message.append("§7Trying to debug EntityMetadataPacket...\n");
            EntityMetadataPacket entityMetadataPacket = laivynpc().getVersion().createMetadataPacket(stand, stand.getDataWatcher(), false);
            entityMetadataPacket.send(player);
            entityMetadataPacket = laivynpc().getVersion().createMetadataPacket(stand, stand.getDataWatcher(), true);
            entityMetadataPacket.send(player);

            message.append("§7Trying to debug EntityEquipmentPacket...\n");
            EntityEquipmentPacket entityEquipmentPacket = laivynpc().getVersion().createEquipmentPacket(stand, EnumItemSlotEnum.EnumItemSlot.HEAD, new ItemStack(Material.DIAMOND_HELMET));
            entityEquipmentPacket.send(player);

            message.append("§7Trying to debug EntityLivingSpawnPacket...\n");
            Pig pig = (Pig) laivynpc().getVersion().createEntity(Entity.EntityType.PIG, player.getLocation());
            EntityLivingSpawnPacket entityLivingSpawnPacket = laivynpc().getVersion().createSpawnLivingPacket(pig);
            entityLivingSpawnPacket.send(player);
            EntityDestroyPacket entityDestroyPacket = laivynpc().getVersion().createDestroyPacket(pig);
            entityDestroyPacket.send(player);

            // Spawn and remove EntityPlayer
            EntityPlayer entityPlayer = laivynpc().getVersion().createPlayer(laivynpc().getVersion().createGameProfile(UUID.randomUUID(), "Laivy"), player.getLocation());

            message.append("§7Trying to debug PlayerInfoPacket ADD_PLAYER...\n");
            PlayerInfoPacket playerInfoPacket = laivynpc().getVersion().createPlayerInfoPacket(EnumPlayerInfoActionEnum.ADD_PLAYER(), entityPlayer);
            playerInfoPacket.send(player);
            message.append("§7Trying to debug EntityNamedSpawnPacket...\n");
            EntityNamedSpawnPacket entityNamedSpawnPacket = laivynpc().getVersion().createSpawnNamedPacket(entityPlayer);
            entityNamedSpawnPacket.send(player);
            message.append("§7Trying to debug ScoreboardTeamPacket...\n");
            ScoreboardTeam team = entityPlayer.getScoreboard().getTeam("Tests");
            if (team == null) {
                team = laivynpc().getVersion().createScoreboardTeam(entityPlayer.getScoreboard(), "Tests");
            }
            ScoreboardTeamPacket scoreboardTeamPacket = laivynpc().getVersion().createScoreboardTeamPacket(team, false);
            scoreboardTeamPacket.send(player);

            entityDestroyPacket = laivynpc().getVersion().createDestroyPacket(entityPlayer);
            entityDestroyPacket.send(player);
            message.append("§7Trying to debug PlayerInfoPacket REMOVE_PLAYER...\n");
            playerInfoPacket = laivynpc().getVersion().createPlayerInfoPacket(EnumPlayerInfoActionEnum.REMOVE_PLAYER(), entityPlayer);
            playerInfoPacket.send(player);
            //

            message.append("§7Trying to debug EntityDestroyPacket...\n");
            entityDestroyPacket = laivynpc().getVersion().createDestroyPacket(stand);
            entityDestroyPacket.send(player);
            message.append("§aSuccess!\n");
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        log.setMessage(message.toString());
        return log;
    }

    @NotNull
    private DebugLog npcs(@NotNull Player player) {
        DebugLog log = new DebugLog("NPCs Debug");
        StringBuilder message = new StringBuilder("§8-----\n§7Debugging NPCs:\n");

        try {
//            new Thread(() -> {
//                for (Class<? extends NPC> npc : DEBUG_NPCS) {
//                    message.append("§7Trying to debug ").append(npc.getSimpleName()).append("...\n");
//                    Bukkit.broadcastMessage("Debugging '" + npc.getSimpleName() + "'");
//
//                    Bukkit.getScheduler().runTask(laivynpc(), () -> {
//                        if (MethodExecutor.hasMethodWithReturn(npc, "debug", void.class, Location.class)) {
//                            MethodExecutor method = new MethodExecutor(new ClassExecutor(npc), ClassExecutor.VOID, "debug", "Gets the debug method from a NPC class", new ClassExecutor(Location.class));
//                            method.load();
//
//                            method.invokeStatic(new ObjectExecutor(player.getLocation()) {
//                                @Override
//                                public @NotNull ClassExecutor getClassExecutor() {
//                                    return new ClassExecutor(Location.class);
//                                }
//                            });
//                        } else {
//                            message.append("§cCannot debug ").append(npc.getSimpleName()).append(" because this NPC class doesn't have a debug method");
//                        }
//                    });
//
//                    Bukkit.broadcastMessage("Success");
//                    try {
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            }).start();
            for (Class<? extends NPC> npc : DEBUG_NPCS) {
                message.append("§7Trying to debug ").append(npc.getSimpleName()).append("...\n");

                if (MethodExecutor.hasMethodWithReturn(npc, "debug", void.class, Location.class)) {
                    MethodExecutor method = new MethodExecutor(new ClassExecutor(npc), ClassExecutor.VOID, "debug", "Gets the debug method from a NPC class", new ClassExecutor(Location.class));
                    method.load();

                    method.invokeStatic(new ObjectExecutor(player.getLocation()) {
                        @Override
                        public @NotNull ClassExecutor getClassExecutor() {
                            return new ClassExecutor(Location.class);
                        }
                    });
                } else {
                    message.append("§cCannot debug ").append(npc.getSimpleName()).append(" because this NPC class doesn't have a debug method");
                }
            }
            message.append("§aSuccess!\n");
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        log.setMessage(message.toString());
        return log;
    }

}
