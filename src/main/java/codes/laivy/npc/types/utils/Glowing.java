package codes.laivy.npc.types.utils;

import codes.laivy.npc.mappings.defaults.classes.enums.EnumChatFormatEnum;
import codes.laivy.npc.mappings.defaults.classes.enums.EnumChatFormatEnum.EnumChatFormat;
import codes.laivy.npc.mappings.defaults.classes.java.EnumObjExec;
import codes.laivy.npc.types.NPC;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.util.Objects;
import java.util.Random;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Glowing {

    private final @NotNull NPC npc;

    private @NotNull EnumChatFormat color;
    private boolean active;

    private @Nullable Rainbow rainbow;

    public Glowing(@NotNull NPC npc, @NotNull EnumChatFormat color, boolean active) {
        this.npc = npc;
        this.color = color;
        this.active = active;
    }

    public @NotNull NPC getNPC() {
        return npc;
    }

    public @Nullable Rainbow getRainbow() {
        return rainbow;
    }
    public void setRainbow(@Nullable Rainbow rainbow) {
        if (this.rainbow != null && this.rainbow.task != null) {
            Bukkit.getScheduler().cancelTask(this.rainbow.task);
        }
        if (rainbow != null) {
            rainbow.task = new BukkitRunnable() {
                @Override
                public void run() {
                    if (!Objects.equals(npc.getGlowing(), Glowing.this)) {
                        this.cancel();
                    } else {
                        setColor(rainbow.getColors()[new Random().nextInt(rainbow.getColors().length)]);
                    }
                }
            }.runTaskTimer(laivynpc(), rainbow.getUpdateTime(), rainbow.getUpdateTime()).getTaskId();
        }

        this.rainbow = rainbow;
    }

    public @NotNull EnumChatFormat getColor() {
        return color;
    }

    public void setColor(@NotNull EnumChatFormat color) {
        this.color = color;
        getNPC().sendUpdatePackets(getNPC().getSpawnedPlayers(), true, false, true, false, false, false);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
        getNPC().sendUpdatePackets(getNPC().getSpawnedPlayers(), true, false, true, false, false, false);
    }

    public static class Rainbow {

        protected @Nullable Integer task;
        protected final @Range(from = 1, to = 100) int updateTime;
        protected @NotNull EnumChatFormat[] colors;

        public Rainbow(int updateTime) {
            EnumChatFormatEnum enumClass = EnumChatFormatEnum.getInstance();
            EnumChatFormat[] colors = new EnumChatFormat[enumClass.values().length];
            // TODO: 12/06/2023 Only colors, not strikethrough, underline, ect...

            int row = 0;
            for (EnumObjExec obj : enumClass.values()) {
                colors[row] = new EnumChatFormat(obj.getValue());
                row++;
            }

            this.updateTime = updateTime;
            this.colors = colors;
        }
        public Rainbow(int updateTime, @NotNull EnumChatFormat[] colors) {
            this.updateTime = updateTime;
            this.colors = colors;
        }

        public @Range(from = 1, to = 100) int getUpdateTime() {
            return updateTime;
        }

        public @Nullable Integer getTask() {
            return task;
        }

        public @NotNull EnumChatFormat[] getColors() {
            return colors;
        }
    }
}
