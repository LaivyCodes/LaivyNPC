package codes.laivy.npc.types.player;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayerNPCSkin {

    public static final PlayerNPCSkin DEFAULT_SKIN = new PlayerNPCSkin(
            "ewogICJ0aW1lc3RhbXAiIDogMTYyMTcxNTMxMjI5MCwKICAicHJvZmlsZUlkIiA6ICJiNTM5NTkyMjMwY2I0MmE0OWY5YTRlYmYxNmRlOTYwYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJtYXJpYW5hZmFnIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzFhNGFmNzE4NDU1ZDRhYWI1MjhlN2E2MWY4NmZhMjVlNmEzNjlkMTc2OGRjYjEzZjdkZjMxOWE3MTNlYjgxMGIiCiAgICB9CiAgfQp9",
            "otpbxDm9B+opW7jEzZF8BVDeZSqaqdF0dyLlnlyMh7Q5ysJFDL48/9J/IOHp8JqNm1oarmVdvxrroy9dlNI2Mz4BVuJM2pcCOJwk2h+aZ4dzNZGxst+MYNPSw+i4sMoYu7OV07UVHrQffolFF7MiaBUst1hFwM07IpTE6UtIQz4rqWisXe9Iz5+ooqX4wj0IB3dPntsh6u5nVlL8acWCBDAW4YqcPt2Y4CKK+KtskjzusjqGAdEO+4lRcW1S0ldo2RNtUHEzZADWQcADjg9KKiKq9QIpIpYURIoIAA+pDGb5Q8L5O6CGI+i1+FxqXbgdBvcm1EG0OPdw9WpSqAxGGeXSwlzjILvlvBzYbd6gnHFBhFO+X7iwRJYNd+qQakjUa6ZwR8NbkpbN3ABb9+6YqVkabaEmgfky3HdORE+bTp/AT6LHqEMQo0xdNkvF9gtFci7RWhFwuTLDvQ1esby1IhlgT+X32CPuVHuxEvPCjN7+lmRz2OyOZ4REo2tAIFUKakqu3nZ0NcF98b87wAdA9B9Qyd2H/rEtUToQhpBjP732Sov6TlJkb8echGYiLL5bu/Q7hum72y4+j2GNnuRiOJtJidPgDqrYMg81GfenfPyS6Ynw6KhdEhnwmJ1FJlJhYvXZyqZwLAV1c26DNYkrTMcFcv3VXmcd5/2Zn9FnZtw=",
            "Steve"
    );

    private final String texture;
    private final String signature;
    private final String nickname;

    private Parts parts;

    public PlayerNPCSkin(@NotNull String texture, @NotNull String signature, @Nullable String nickname) {
        this.texture = texture;
        this.signature = signature;
        this.nickname = nickname;
        this.parts = new Parts();
    }

    @NotNull
    public String getTexture() {
        return texture;
    }

    @NotNull
    public String getSignature() {
        return signature;
    }

    @Nullable
    public String getNickname() {
        return nickname;
    }

    @NotNull
    public Parts getParts() {
        return parts;
    }

    public void setParts(@NotNull Parts parts) {
        this.parts = parts;
    }

    public enum Part {
        CAPE, JACKET, LEFT_SLEEVE, RIGHT_SLEEVE, LEFT_PANTS, RIGHT_PANTS, HAT;
    }

    public static class Parts {
        private final Map<Part, Boolean> parts = new HashMap<>();

        protected Parts() {
            enableAll();
        }

        public void enableAll() {
            Arrays.stream(Part.values()).forEach(x -> this.parts.put(x, Boolean.TRUE));
        }

        public void disableAll() {
            Arrays.stream(Part.values()).forEach(x -> this.parts.put(x, Boolean.FALSE));
        }

        @NotNull
        public List<Part> getVisibleParts() {
            return Arrays.stream(Part.values()).filter(this::isVisible).collect(Collectors.toList());
        }

        @NotNull
        public List<Part> getInvisibleParts() {
            return Arrays.stream(Part.values()).filter(x -> !isVisible(x)).collect(Collectors.toList());
        }

        public void setVisible(@NotNull Part part, boolean visible) {
            this.parts.put(part, visible);
        }

        public boolean isVisible(@NotNull Part part) {
            return this.parts.get(part);
        }

        public boolean hasCape() {
            return isVisible(Part.CAPE);
        }

        public void setCape(boolean cape) {
            setVisible(Part.CAPE, cape);
        }

        public boolean hasJacket() {
            return isVisible(Part.JACKET);
        }

        public void setJacket(boolean jacket) {
            setVisible(Part.JACKET, jacket);
        }

        public boolean hasLeftSleeve() {
            return this.parts.get(Part.LEFT_SLEEVE);
        }

        public void setLeftSleeve(boolean leftSleeve) {
            setVisible(Part.LEFT_SLEEVE, leftSleeve);
        }

        public boolean hasRightSleeve() {
            return isVisible(Part.RIGHT_SLEEVE);
        }

        public void setRightSleeve(boolean rightSleeve) {
            setVisible(Part.RIGHT_SLEEVE, rightSleeve);
        }

        public boolean hasLeftPants() {
            return isVisible(Part.LEFT_PANTS);
        }

        public void setLeftPants(boolean leftPants) {
            setVisible(Part.LEFT_PANTS, leftPants);
        }

        public boolean hasRightPants() {
            return isVisible(Part.RIGHT_PANTS);
        }

        public void setRightPants(boolean rightPants) {
            setVisible(Part.RIGHT_PANTS, rightPants);
        }

        public boolean hasHat() {
            return isVisible(Part.HAT);
        }

        public void setHat(boolean hat) {
            setVisible(Part.HAT, hat);
        }
    }

}