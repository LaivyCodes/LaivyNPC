package codes.laivy.npc.mappings.defaults.classes.packets.info;

import codes.laivy.npc.mappings.defaults.classes.packets.IPacket;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface IPlayerInfoPacket extends IPacket {

    void send(@NotNull Player... players);

    @NotNull Object getPacket();

}
