package codes.laivy.npc.mappings.defaults.classes.packets.listeners;

import codes.laivy.npc.developers.events.NPCClickEvent;
import codes.laivy.npc.mappings.defaults.classes.entity.EntityPlayer;
import codes.laivy.npc.mappings.defaults.classes.others.objects.PlayerConnection;
import codes.laivy.npc.mappings.defaults.classes.packets.EntityUseInPacket;
import codes.laivy.npc.types.NPC;
import io.netty.channel.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class InjectionUtils {

    private static @NotNull Channel getPlayerChannel(@NotNull Player player) {
        try {
            PlayerConnection conn = EntityPlayer.getEntityPlayer(player).getPlayerConnection();
            return conn.getNetworkManager().getChannel();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void removePlayer(@NotNull Player player) {
        try {
            Channel channel = getPlayerChannel(player);

            channel.eventLoop().submit(() -> {
                channel.pipeline().remove(player.getUniqueId().toString());
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void injectPlayer(@NotNull Player player) {
        ChannelDuplexHandler channelDuplexHandler = new ChannelDuplexHandler() {

            public final Map<NPC, Long> interval = new HashMap<>();

            @Override
            public void channelRead(ChannelHandlerContext channelHandlerContext, Object packet) throws Exception {
                try {
                    if (laivynpc().getVersion().getClassExec("PacketPlayInUseEntity").isReflectiveInstance(packet)) {
                        EntityUseInPacket oPacket = new EntityUseInPacket(packet);

                        if (NPC.ALL_NPCS.containsKey(player.getUniqueId())) {
                            for (NPC npc : NPC.ALL_NPCS.get(player.getUniqueId())) {
                                if (npc.getAllIDs().contains(oPacket.getEntityId())) {
                                    EntityUseInPacket.ActionEnum.Action clickActionEnum = oPacket.getClickAction();
                                    NPC.ClickType type = (clickActionEnum.name().equalsIgnoreCase("INTERACT_AT") ? (player.isSneaking() ? NPC.ClickType.RIGHT_SHIFT_CLICK : NPC.ClickType.RIGHT_CLICK) : (player.isSneaking() ? NPC.ClickType.LEFT_SHIFT_CLICK : NPC.ClickType.LEFT_CLICK));

                                    Bukkit.getScheduler().runTask(laivynpc(), () -> {
                                        long interval = npc.getInteractIntervalMs();
                                        if (this.interval.containsKey(npc)) {
                                            if ((System.currentTimeMillis() - this.interval.get(npc)) < interval) {
                                                return;
                                            }
                                        }

                                        this.interval.put(npc, System.currentTimeMillis());

                                        NPCClickEvent event = new NPCClickEvent(!Bukkit.isPrimaryThread(), npc, player, type);
                                        Bukkit.getPluginManager().callEvent(event);
                                        if (!event.isCancelled()) {
                                            npc.getClickAction().run(player, type);
                                        }
                                    });
                                    break;
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                super.channelRead(channelHandlerContext, packet);
            }

            @Override
            public void write(ChannelHandlerContext channelHandlerContext, Object packet, ChannelPromise channelPromise) throws Exception {
                super.write(channelHandlerContext, packet, channelPromise);
            }

        };

        ChannelPipeline pipeline = getPlayerChannel(player).pipeline();
        pipeline.addBefore("packet_handler", player.getUniqueId().toString(), channelDuplexHandler);
    }

}
