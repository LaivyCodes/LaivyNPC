package codes.laivy.npc.utils;

import codes.laivy.npc.exceptions.NPCIllegalSkinException;
import com.google.gson.JsonObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class SkinUtils {

    public static String[] getSkinFromName(String name) throws NPCIllegalSkinException {
        try {
            String texture;
            String signature;

            URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
            InputStreamReader reader = new InputStreamReader(url.openStream());

            String userUID;
            try {
                userUID = JsonParser.parse(reader).getAsJsonObject().get("id").getAsString();
            } catch (IllegalStateException ignore) {
                throw new NPCIllegalSkinException("This name '" + name + "' doesn't have a original mojang account.");
            }

            url = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + userUID + "?unsigned=false");
            reader = new InputStreamReader(url.openStream());
            JsonObject textureProperty = JsonParser.parse(reader).getAsJsonObject().get("properties").getAsJsonArray().get(0).getAsJsonObject();

            texture = textureProperty.get("value").getAsString();
            signature = textureProperty.get("signature").getAsString();

            return new String[]{
                    texture,
                    signature
            };
        } catch (FileNotFoundException e) {
            throw new NPCIllegalSkinException("This name '" + name + "' doesn't have a original mojang account.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
