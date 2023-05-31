package codes.laivy.npc.types;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.entity.EntityLiving;
import codes.laivy.npc.mappings.defaults.classes.packets.IPacket;
import codes.laivy.npc.utils.Validation;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public abstract class EntityLivingNPC extends EntityNPC {

    public EntityLivingNPC(@NotNull List<OfflinePlayer> players, @NotNull Entity.EntityType entityType, @NotNull Location location) {
        this(getNextNpcId(), players, entityType, location);
    }
    public EntityLivingNPC(int id, @NotNull List<OfflinePlayer> players, @NotNull Entity.EntityType entityType, @NotNull Location location) {
        super(id, players, entityType, location);
        Validation.isTrue(!entityType.isEntityLiving(), new IllegalArgumentException("This EntityType isn't a LivingEntity."));
    }

    public @NotNull EntityLiving getEntity() {
        return (EntityLiving) super.getEntity();
    }

    @Override
    public @NotNull List<IPacket> getSpawnPackets(@NotNull Player player) {
        List<@NotNull IPacket> packets = new ArrayList<>();

        packets.add(laivynpc().getVersion().createSpawnLivingPacket(getEntity()));
        packets.addAll(getMetadataUpdatePackets(player));

        return packets;
    }
}
