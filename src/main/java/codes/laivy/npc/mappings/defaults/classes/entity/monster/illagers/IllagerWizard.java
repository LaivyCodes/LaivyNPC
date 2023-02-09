package codes.laivy.npc.mappings.defaults.classes.entity.monster.illagers;

import codes.laivy.npc.mappings.defaults.classes.entity.EntityLiving;
import codes.laivy.npc.mappings.defaults.classes.enums.EnumSpellEnum;
import codes.laivy.npc.mappings.versions.V1_12_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public abstract class IllagerWizard extends EntityLiving {

    public IllagerWizard(@Nullable Object value) {
        super(value);
    }

    public @NotNull EnumSpellEnum.Spell getSpell() {
        return laivynpc().getVersion().getEntityWizardSpell(this);
    }
    public void setSpell(@NotNull EnumSpellEnum.Spell spell) {
        laivynpc().getVersion().setEntityWizardSpell(this, spell);
    }

    @Override
    public @NotNull EntityLivingClass getClassExecutor() {
        if (ReflectionUtils.isCompatible(V1_12_R1.class)) {
            return (IllagerWizardClass) laivynpc().getVersion().getClassExec("Entity:IllagerWizard");
        } else {
            return (EntityLivingClass) laivynpc().getVersion().getClassExec("Entity:Evoker");
        }
    }

    public static class IllagerWizardClass extends EntityLivingClass {
        public IllagerWizardClass(@NotNull String className) {
            super(className);
        }
    }
}
