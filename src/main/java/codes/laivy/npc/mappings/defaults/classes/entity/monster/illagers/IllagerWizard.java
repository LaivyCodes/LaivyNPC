package codes.laivy.npc.mappings.defaults.classes.entity.monster.illagers;

import codes.laivy.npc.mappings.defaults.classes.datawatcher.DataWatcherObject;
import codes.laivy.npc.mappings.defaults.classes.entity.EntityLiving;
import codes.laivy.npc.mappings.defaults.classes.enums.EnumSpellEnum;
import codes.laivy.npc.mappings.versions.V1_11_R1;
import codes.laivy.npc.mappings.versions.V1_12_R1;
import codes.laivy.npc.mappings.versions.V1_18_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public abstract class IllagerWizard extends EntityLiving {

    public static @NotNull DataWatcherObject SPELL_METADATA() {
        if (laivynpc().getVersion().getClass().equals(V1_11_R1.class) || ReflectionUtils.isCompatible(V1_18_R1.class)) {
            return new DataWatcherObject(laivynpc().getVersion().getFieldExec("Metadata:IllagerWizard:Spell").invokeStatic());
        } else {
            throw new IllegalStateException("This metadata object is compatible only at 1.11 or 1.18+");
        }
    }

    public IllagerWizard(@Nullable Object value) {
        super(value);
    }

    public @NotNull EnumSpellEnum.Spell getSpell() {
        return laivynpc().getVersion().getEntityIllagerWizardSpell(this);
    }
    public void setSpell(@NotNull EnumSpellEnum.Spell spell) {
        laivynpc().getVersion().setEntityIllagerWizardSpell(this, spell);
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
