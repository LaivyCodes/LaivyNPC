package codes.laivy.npc.mappings.defaults.classes.packets;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface IPacket {

    void send(@NotNull Player... players);

    @NotNull Object getPacket();

}
