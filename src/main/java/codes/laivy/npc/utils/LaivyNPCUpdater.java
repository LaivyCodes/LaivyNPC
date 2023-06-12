package codes.laivy.npc.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import static codes.laivy.npc.LaivyNPC.laivynpc;
import static codes.laivy.npc.config.Translate.*;

public class LaivyNPCUpdater implements Runnable {

    private static String updateAvailable = null;
    private static boolean error = false;

    @Override
    public void run() {
        try {
            if (checkUpdates()) {
                StringBuilder content = new StringBuilder();
                try {
                    URL url = new URL("https://api.github.com/repos/ItsLaivy/LaivyNPC/releases");
                    URLConnection urlConnection = url.openConnection();

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    String line;

                    while ((line = bufferedReader.readLine()) != null) {
                        content.append(line).append("\n");
                    }

                    bufferedReader.close();
                } catch (Exception e) {
                    return;
                }

                List<String> releases = new ArrayList<>();
                JsonArray dataJson = JsonParser.parse(content.toString()).getAsJsonArray();
                for (JsonElement e : dataJson) {
                    JsonObject obj = e.getAsJsonObject();
                    if (!obj.get("prerelease").getAsBoolean()) {
                        releases.add(obj.get("tag_name").getAsString());
                    }
                }

                if (!releases.get(0).equals(laivynpc().getDescription().getVersion())) {
                    laivynpc().log(translate(null, "updater.new_version_available", releases.get(0), releases.get(0), laivynpc().getDescription().getVersion()));
                    updateAvailable = releases.get(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            laivynpc().log(translate(null, "updater.cannot_search_updates"));
            error = true;
        }
    }

    public static boolean errorCheckUpdates() {
        return error;
    }
    public static boolean checkUpdates() {
        return laivynpc().getConfig().getBoolean("check-updates", true);
    }

    public static boolean hasNewVersion() {
        return updateAvailable != null;
    }
    @NotNull
    public static String getNewVersion() {
        return updateAvailable;
    }

    public static int versionNumber() {
        return 400;
    }

}
