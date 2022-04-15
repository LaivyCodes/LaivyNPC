package codes.laivy.npc.api.player;

import codes.laivy.npc.utils.Validation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayerNPCSkin {

    public static final PlayerNPCSkin DEFAULT_SKIN = new PlayerNPCSkin(
            "ewogICJ0aW1lc3RhbXAiIDogMTYyMTcxNTMxMjI5MCwKICAicHJvZmlsZUlkIiA6ICJiNTM5NTkyMjMwY2I0MmE0OWY5YTRlYmYxNmRlOTYwYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJtYXJpYW5hZmFnIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzFhNGFmNzE4NDU1ZDRhYWI1MjhlN2E2MWY4NmZhMjVlNmEzNjlkMTc2OGRjYjEzZjdkZjMxOWE3MTNlYjgxMGIiCiAgICB9CiAgfQp9",
            "otpbxDm9B+opW7jEzZF8BVDeZSqaqdF0dyLlnlyMh7Q5ysJFDL48/9J/IOHp8JqNm1oarmVdvxrroy9dlNI2Mz4BVuJM2pcCOJwk2h+aZ4dzNZGxst+MYNPSw+i4sMoYu7OV07UVHrQffolFF7MiaBUst1hFwM07IpTE6UtIQz4rqWisXe9Iz5+ooqX4wj0IB3dPntsh6u5nVlL8acWCBDAW4YqcPt2Y4CKK+KtskjzusjqGAdEO+4lRcW1S0ldo2RNtUHEzZADWQcADjg9KKiKq9QIpIpYURIoIAA+pDGb5Q8L5O6CGI+i1+FxqXbgdBvcm1EG0OPdw9WpSqAxGGeXSwlzjILvlvBzYbd6gnHFBhFO+X7iwRJYNd+qQakjUa6ZwR8NbkpbN3ABb9+6YqVkabaEmgfky3HdORE+bTp/AT6LHqEMQo0xdNkvF9gtFci7RWhFwuTLDvQ1esby1IhlgT+X32CPuVHuxEvPCjN7+lmRz2OyOZ4REo2tAIFUKakqu3nZ0NcF98b87wAdA9B9Qyd2H/rEtUToQhpBjP732Sov6TlJkb8echGYiLL5bu/Q7hum72y4+j2GNnuRiOJtJidPgDqrYMg81GfenfPyS6Ynw6KhdEhnwmJ1FJlJhYvXZyqZwLAV1c26DNYkrTMcFcv3VXmcd5/2Zn9FnZtw="
    );

    private final String texture;
    private final String signature;

    private Parts parts;

    public PlayerNPCSkin(String texture, String signature) {
        Validation.notNull(texture, new NullPointerException("A textura não podem ser nula"));
        Validation.notNull(signature, new NullPointerException("A assinatura não podem ser nula"));

        this.texture = texture;
        this.signature = signature;
        this.parts = new Parts();
    }

    public String getTexture() {
        return texture;
    }

    public String getSignature() {
        return signature;
    }

    public Parts getParts() {
        return parts;
    }

    public void setParts(Parts parts) {
        Validation.notNull(parts, new NullPointerException("As partes não podem ser nulas"));
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

        public List<Part> getVisibleParts() {
            return Arrays.stream(Part.values()).filter(this::isVisible).collect(Collectors.toList());
        }

        public List<Part> getInvisibleParts() {
            return Arrays.stream(Part.values()).filter(x -> !isVisible(x)).collect(Collectors.toList());
        }

        public void setVisible(Part part, boolean visible) {
            Validation.notNull(part, new NullPointerException("Parametro \"part\" não pode ser nulo"));
            this.parts.put(part, visible);
        }

        public boolean isVisible(Part part) {
            Validation.notNull(part, new NullPointerException("Parametro \"part\" não pode ser nulo"));
            return this.parts.get(part);
        }

        public boolean isCape() {
            return isVisible(Part.CAPE);
        }

        public void setCape(boolean cape) {
            setVisible(Part.CAPE, cape);
        }

        public boolean isJacket() {
            return isVisible(Part.JACKET);
        }

        public void setJacket(boolean jacket) {
            setVisible(Part.JACKET, jacket);
        }

        public boolean isLeftSleeve() {
            return this.parts.get(Part.LEFT_SLEEVE);
        }

        public void setLeftSleeve(boolean leftSleeve) {
            setVisible(Part.LEFT_SLEEVE, leftSleeve);
        }

        public boolean isRightSleeve() {
            return isVisible(Part.RIGHT_SLEEVE);
        }

        public void setRightSleeve(boolean rightSleeve) {
            setVisible(Part.RIGHT_SLEEVE, rightSleeve);
        }

        public boolean isLeftPants() {
            return isVisible(Part.LEFT_PANTS);
        }

        public void setLeftPants(boolean leftPants) {
            setVisible(Part.LEFT_PANTS, leftPants);
        }

        public boolean isRightPants() {
            return isVisible(Part.RIGHT_PANTS);
        }

        public void setRightPants(boolean rightPants) {
            setVisible(Part.RIGHT_PANTS, rightPants);
        }

        public boolean isHat() {
            return isVisible(Part.HAT);
        }

        public void setHat(boolean hat) {
            setVisible(Part.HAT, hat);
        }
    }
}