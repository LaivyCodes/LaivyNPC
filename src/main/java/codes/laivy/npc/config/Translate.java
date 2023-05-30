package codes.laivy.npc.config;

import codes.laivy.npc.utils.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Translate {

    private static final @NotNull String[] DEFAULT_LANGUAGES_FILES = new String[] {
            "EN_US.json"
    };

    private static final @NotNull Map<@NotNull String, @NotNull String> messages = new HashMap<>();

    static {
        Map<String, LangInfo> infos = new LinkedHashMap<>();

        for (String str : DEFAULT_LANGUAGES_FILES) {
            LinkedHashMap<String, String> messages = new LinkedHashMap<>();
            String packName = null;

            InputStream stream = laivynpc().getResource("languages/" + str);
            JsonObject object = JsonParser.parse(new InputStreamReader(stream)).getAsJsonObject();

            for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
                String key = entry.getKey();

                if (key.equals("info:pack_name")) {
                    packName = object.get(key).getAsString();
                } else {
                    messages.put(key, object.get(key).getAsString());
                }
            }

            infos.put(str, new LangInfo(Objects.requireNonNull(packName), messages));
        }

        messages.putAll(infos.get(DEFAULT_LANGUAGES_FILES[0]).getMessages());
    }

    public static @NotNull String translate(@Nullable OfflinePlayer player, @NotNull String code, @NotNull Object... replaces) {
        String message = null;

        if (messages.containsKey(code)) {
            message = messages.get(code);
        }

        if (message == null) {
            throw new NullPointerException("Couldn't get message '" + code + "'");
        }

        for (Object replace : replaces) {
            message = message.replaceFirst("%s", replace.toString());
        }

        return message;
    }

    private static final class LangInfo {

        private final @NotNull String packName;
        private final @NotNull LinkedHashMap<@NotNull String, @NotNull String> messages;

        public LangInfo(@NotNull String packName, @NotNull LinkedHashMap<@NotNull String, @NotNull String> messages) {
            this.packName = packName;
            this.messages = messages;
        }

        public @NotNull String getPackName() {
            return packName;
        }

        public @NotNull LinkedHashMap<String, String> getMessages() {
            return messages;
        }
    }

}
