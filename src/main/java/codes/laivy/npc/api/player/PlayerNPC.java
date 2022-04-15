package codes.laivy.npc.api.player;

import codes.laivy.npc.api.types.INPC;
import codes.laivy.npc.api.workers.NPCAnimation;
import codes.laivy.npc.reflection.ReflectionUtils;
import codes.laivy.npc.utils.Validation;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

import static codes.laivy.npc.reflection.ReflectionUtils.*;

public class PlayerNPC implements INPC {

    private static final Map<Player, List<PlayerNPC>> PLAYER_NPCS = new HashMap<>();
    public static Map<Player, List<PlayerNPC>> getPlayerNPCs() {
        return PLAYER_NPCS;
    }

    private final Player player;
    private final String npcName;
    private final UUID npcUUID;
    private final Location npcLocation;

    protected Object profile;
    protected Object npcEntity;

    private final PlayerNPCAttributes playerNPCAttributes;
    private final PlayerNPCController playerNPCController;
    private final PlayerNPCAppearance playerNPCAppearance;
    private final codes.laivy.npc.api.workers.NPCAnimation NPCAnimation;

    public PlayerNPC(@NotNull Player player, @NotNull String npcName, @Nullable UUID npcUUID, @NotNull Location npcLocation) {
        Validation.notNull(player, new NullPointerException("O jogador não pode ser nulo"));
        Validation.notNull(npcName, new NullPointerException("O nome do NPC não pode ser nulo"));
        Validation.notNull(npcLocation, new NullPointerException("O local do NPC não pode ser nulo"));

        this.player = player;
        this.npcName = npcName;
        this.npcLocation = npcLocation;

        if (npcUUID == null) this.npcUUID = UUID.randomUUID();
        else this.npcUUID = npcUUID;

        this.profile = ReflectionUtils.createGameProfile(this.npcUUID, npcName);
        this.npcEntity = ReflectionUtils.createEntityPlayer(Bukkit.getServer(), profile, npcLocation);

        this.playerNPCAttributes = new PlayerNPCAttributes(this);
        this.playerNPCController = new PlayerNPCController(this);
        this.playerNPCAppearance = new PlayerNPCAppearance(this);
        this.NPCAnimation = new NPCAnimation(this);

        sendPacketToPlayer(player, PlayerNPCController.Developers.getUpdateScoreboardPackets(this.playerNPCController));

        if (!getPlayerNPCs().containsKey(player)) {
            getPlayerNPCs().put(player, new ArrayList<>());
        }
        getPlayerNPCs().get(player).add(this);
    }

    public Player getPlayer() {
        return player;
    }

    @NotNull
    @Override
    public String getNPCName() {
        return npcName;
    }

    @NotNull
    @Override
    public Location getNPCLocation() {
        return npcLocation;
    }

    @NotNull
    @Override
    public Object getNPCEntity() {
        return npcEntity;
    }

    public UUID getNPCUniqueId() {
        return npcUUID;
    }

    public Object getProfile() {
        return profile;
    }

    public PlayerNPCAttributes getAttributes() {
        return playerNPCAttributes;
    }
    public PlayerNPCController getController() {
        return playerNPCController;
    }
    public PlayerNPCAppearance getAppearance() {
        return playerNPCAppearance;
    }
    public NPCAnimation getAnimation() {
        return NPCAnimation;
    }
}
