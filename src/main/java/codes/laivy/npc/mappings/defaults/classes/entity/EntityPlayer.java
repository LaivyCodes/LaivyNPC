package codes.laivy.npc.mappings.defaults.classes.entity;

import codes.laivy.npc.mappings.defaults.classes.gameprofile.GameProfile;
import codes.laivy.npc.mappings.defaults.classes.others.objects.PlayerConnection;
import codes.laivy.npc.mappings.defaults.classes.scoreboard.Scoreboard;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class EntityPlayer extends EntityHuman {

    @NotNull
    public static EntityPlayer getEntityPlayer(@NotNull Player player) {
        return new CraftPlayer(player).getHandle();
    }

    // ---/-/--- //

    public EntityPlayer(@Nullable Object value) {
        super(value);
    }

    @NotNull
    public PlayerConnection getPlayerConnection() {
        return new PlayerConnection(Objects.requireNonNull(laivynpc().getVersion().getFieldExec("EntityPlayer:playerConnection").invokeInstance(this)));
    }

    @NotNull
    public Scoreboard getScoreboard() {
        return new Scoreboard(laivynpc().getVersion().getMethodExec("Entity:EntityPlayer:getScoreboard").invokeInstance(this));
    }

    @NotNull
    public GameProfile getProfile() {
        return new GameProfile(laivynpc().getVersion().getMethodExec("Entity:EntityPlayer:getProfile").invokeInstance(this));
    }

    public @Nullable String getTablistName() {
        return laivynpc().getVersion().getPlayerTablistName(this);
    }
    public void setTablistName(@Nullable String name) {
        laivynpc().getVersion().setPlayerTablistName(this, name);
    }

    @Override
    public @NotNull EntityPlayerClass getClassExecutor() {
        return (EntityPlayerClass) laivynpc().getVersion().getClassExec("EntityPlayer");
    }

    public static class EntityPlayerClass extends EntityHumanClass {
        public EntityPlayerClass(@NotNull String className) {
            super(className);
        }
    }

}
