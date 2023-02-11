package codes.laivy.npc.mappings.defaults.classes.entity.monster;

import codes.laivy.npc.mappings.defaults.classes.datawatcher.DataWatcherObject;
import codes.laivy.npc.mappings.instances.classes.ClassExecutor;
import codes.laivy.npc.mappings.instances.ObjectExecutor;
import codes.laivy.npc.mappings.defaults.classes.entity.EntityLiving;
import codes.laivy.npc.mappings.defaults.classes.others.objects.Block;
import codes.laivy.npc.mappings.defaults.classes.others.objects.IBlockData;
import codes.laivy.npc.mappings.versions.V1_9_R1;
import codes.laivy.npc.utils.ReflectionUtils;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static codes.laivy.npc.LaivyNPC.laivynpc;

public class Enderman extends EntityLiving {

    public static @NotNull DataWatcherObject SCREAMING_METADATA() {
        if (ReflectionUtils.isCompatible(V1_9_R1.class)) {
            return new DataWatcherObject(laivynpc().getVersion().getFieldExec("Metadata:Enderman:screaming").invokeStatic());
        } else {
            throw new IllegalStateException("Metadata objects is compatible only at 1.9+");
        }
    }

    public Enderman(@Nullable Object value) {
        super(value);
    }

    public @NotNull Material getCarried() {
        return new IBlockData(laivynpc().getVersion().getMethodExec("Entity:Enderman:getCarried").invokeInstance(this)).getBlock().getMaterial();
    }
    public void setCarried(@NotNull Material material) {
        if (!material.isBlock()) {
            throw new IllegalArgumentException("This material isn't a block");
        }

        laivynpc().getVersion().getMethodExec("Entity:Enderman:setCarried").invokeInstance(this, new Block(laivynpc().getVersion().getMethodExec("CraftMagicNumbers:getBlock").invokeStatic(new ObjectExecutor(material) {
            @Override
            public @NotNull ClassExecutor getClassExecutor() {
                return new ClassExecutor(Material.class);
            }
        })).getData());
    }

    public boolean isScreaming() {
        return laivynpc().getVersion().isEntityEndermanScreaming(this);
    }
    public void setScreaming(boolean flag) {
        laivynpc().getVersion().setEntityEndermanScreaming(this, flag);
    }

    @Override
    public @NotNull EndermanClass getClassExecutor() {
        return (EndermanClass) laivynpc().getVersion().getClassExec("Entity:Enderman");
    }

    public static class EndermanClass extends EntityLivingClass {
        public EndermanClass(@NotNull String className) {
            super(className);
        }
    }
}
