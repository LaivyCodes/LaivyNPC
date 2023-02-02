package codes.laivy.npc.utils;

import codes.laivy.npc.exceptions.NPCIllegalSkinException;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class SkinUtils {

    public static String[] getSkinFromName(String name) throws NPCIllegalSkinException, IOException {
        String texture;
        String signature;

        URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
        InputStreamReader reader = new InputStreamReader(url.openStream());

        String userUID;
        try {
            userUID = JsonParser.parse(reader).getAsJsonObject().get("id").getAsString();
        } catch (IllegalStateException ignore) {
            throw new NPCIllegalSkinException("O nome informado não pertence à uma conta original da mojang");
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
    }

}
