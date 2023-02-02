package codes.laivy.npc.mappings.utils;

import codes.laivy.npc.mappings.utils.classes.datawatcher.DataWatcher;
import codes.laivy.npc.mappings.utils.classes.entity.Entity;
import codes.laivy.npc.mappings.utils.classes.entity.EntityHuman;
import codes.laivy.npc.mappings.utils.classes.entity.EntityLiving;
import codes.laivy.npc.mappings.utils.classes.entity.EntityPlayer;
import codes.laivy.npc.mappings.utils.classes.enums.EnumItemSlot;
import codes.laivy.npc.mappings.utils.classes.enums.EnumPlayerInfoActionEnum;
import codes.laivy.npc.mappings.utils.classes.others.objects.PlayerConnection;
import codes.laivy.npc.mappings.utils.classes.packets.*;
import codes.laivy.npc.mappings.utils.classes.scoreboard.ScoreboardTeam;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface VersionPacket {

    /**
     * Sends a packet to a list of PlayerConnections
     * @param packet the packet that will be send
     * @param connections the connections
     */
    void sendPacket(@NotNull Packet packet, @NotNull PlayerConnection... connections);

    @NotNull EntitySpawnPacket createSpawnPacket(@NotNull Entity entity);
    @NotNull EntityLivingSpawnPacket createSpawnLivingPacket(@NotNull EntityLiving entity);
    @NotNull EntityNamedSpawnPacket createSpawnNamedPacket(@NotNull EntityHuman entity);

    @NotNull EntityDestroyPacket createDestroyPacket(@NotNull Entity... entity);
    @NotNull EntityMetadataPacket createMetadataPacket(@NotNull Entity entity, @NotNull DataWatcher dataWatcher, boolean b);
    @NotNull PlayerInfoPacket createPlayerInfoPacket(@NotNull EnumPlayerInfoActionEnum.EnumPlayerInfoAction action, @NotNull EntityPlayer player);
    @NotNull ScoreboardTeamPacket createScoreboardTeamPacket(@NotNull ScoreboardTeam team, boolean b);
    @NotNull EntityEquipmentPacket createEquipmentPacket(@NotNull Entity entity, @NotNull EnumItemSlot slot, @NotNull ItemStack item);

    @NotNull EntityTeleportPacket createTeleportPacket(@NotNull Entity entity);
    @NotNull EntityHeadRotationPacket createHeadRotationPacket(@NotNull Entity entity, int yaw);
    @NotNull EntityLookPacket createLookPacket(@NotNull Entity entity, double yaw, double pitch);

    @NotNull EntityUseInPacket.ActionEnum.Action getEntityUseInPacketAction(@NotNull EntityUseInPacket packet);
    
}
