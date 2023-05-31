package codes.laivy.npc.types.list.monster.illagers;

import codes.laivy.npc.mappings.defaults.classes.entity.Entity;
import codes.laivy.npc.mappings.defaults.classes.entity.monster.illagers.IllagerWizard;
import codes.laivy.npc.mappings.defaults.classes.enums.EnumSpellEnum;
import codes.laivy.npc.types.EntityLivingNPC;
import codes.laivy.npc.types.NPC;
import codes.laivy.npc.types.commands.NPCConfiguration;
import codes.laivy.npc.utils.ClassUtils;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static codes.laivy.npc.config.Translate.translate;

public abstract class IllagerWizardNPC extends EntityLivingNPC {

    @Override
    protected void debug() {
        super.debug();
        setSpell(getSpell());
    }

    protected IllagerWizardNPC(int id, @NotNull List<OfflinePlayer> players, Entity.@NotNull EntityType entityType, @NotNull Location location) {
        super(id, players, entityType, location);
        if (!ClassUtils.isInstanceOf(entityType.getEntityClass(), IllagerWizard.class)) {
            throw new IllegalArgumentException("The NPC entity type's entity class needs to be an instance of IllagerWizard.");
        }
    }

    public @NotNull EnumSpellEnum.Spell getSpell() {
        return getEntity().getSpell();
    }
    public void setSpell(@NotNull EnumSpellEnum.Spell spell) {
        getEntity().setSpell(spell);
        sendUpdatePackets(getSpawnedPlayers(), false, false, true, false, false, false);
    }

    @Override
    public @NotNull IllagerWizard getEntity() {
        return (IllagerWizard) super.getEntity();
    }

    @Override
    public List<NPCConfiguration> getByCommandConfigurations() {
        List<NPCConfiguration> list = super.getByCommandConfigurations();

        list.add(new NPCConfiguration("spell", "/laivynpc config spell (spell)") {
            @Override
            public void execute(@NotNull NPC npc, @NotNull Player sender, @NotNull String[] args) {
                IllagerWizardNPC wizardNPC = (IllagerWizardNPC) npc;

                if (args.length > 0) {
                    try {
                        wizardNPC.setSpell(EnumSpellEnum.Spell.valueOf(args[0].toUpperCase()));
                        sender.sendMessage(translate(sender, "npc.commands.general.flag_changed"));
                    } catch (IllegalArgumentException ignore) {
                        sender.performCommand("laivynpc config " + getName());
                        return;
                    }
                    return;
                }

                StringBuilder builder = new StringBuilder();
                int row = 0;
                for (EnumSpellEnum.Spell spell : EnumSpellEnum.Spell.values()) {
                    if (row != 0) builder.append("§7, ");
                    builder.append("§f").append(spell.name());
                    row++;
                }

                sender.sendMessage("§cUse " + getSyntax());
                sender.sendMessage(translate(sender, "npc.commands.general.available_options", builder));
            }
        });

        return list;
    }

    // Serializators
    @Override
    public @NotNull Map<@NotNull String, @NotNull Object> serialize() {
        Map<String, Object> map = super.serialize();
        map.put("IllagerWizardNPC Configuration", new LinkedHashMap<String, Object>() {{
            put("Spell", getSpell().name());
        }});
        return map;
    }

    public static @NotNull IllagerWizardNPC deserialize(@NotNull ConfigurationSection section) {
        IllagerWizardNPC npc = (IllagerWizardNPC) EntityLivingNPC.deserialize(section);
        npc.setSpell(EnumSpellEnum.Spell.valueOf(section.getConfigurationSection("IllagerWizardNPC Configuration").getString("Spell").toUpperCase()));
        return npc;
    }
    //
}
